package com.github.wangcanfeng01.freedom.ragnaros.calculator;

import com.github.wangcanfeng01.freedom.ragnaros.service.HostInfoService;
import com.github.wangcanfeng01.freedom.ragnaros.utils.CalculateUtils;
import com.github.wangcanfeng01.freedom.ragnaros.utils.HostUtils;
import com.github.wangcanfeng01.freedom.ragnaros.vo.ServiceThroughput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import com.github.wangcanfeng01.freedom.ragnaros.annotations.Throughput;
import com.github.wangcanfeng01.freedom.ragnaros.annotations.ThroughputScan;
import com.github.wangcanfeng01.freedom.ragnaros.constant.RagnarosConsts;
import com.github.wangcanfeng01.freedom.ragnaros.vo.ThroughputData;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangcanfeng
 * @description 吞吐量管理器
 * @date Created in 18:50-2020/4/7
 * @since 2.0.0
 */
@Service
public class ThroughputManagementImpl implements ManagementService {

    private ApplicationContext applicationContext;

    @Autowired
    public ThroughputManagementImpl(ApplicationContext context) {
        this.applicationContext = context;
    }

    @Autowired(required = false)
    private HostInfoService hostInfoService;

    /**
     * 锁
     */
    private final ReentrantLock lock = new ReentrantLock();
    /**
     * 计算数据时的锁
     */
    private final Condition calculateLock = lock.newCondition();


    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(1);
    private final ExecutorService executor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS, workQueue, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 是否工作中
     */
    private volatile boolean working = false;

    /**
     * 当前时间窗的吞吐量
     */
    private Map<String, AtomicLong> totalThroughputMap = new ConcurrentHashMap<>();

    /**
     * 上个时间窗的吞吐量
     */
    private Map<String, AtomicLong> lastThroughputMap = new ConcurrentHashMap<>();

    /**
     * 每秒的吞吐量
     */
    private Map<String, String> throughputPerSecondMap = new ConcurrentHashMap<>();

    /**
     * 当前事件窗内处理的事件总耗时
     */
    private Map<String, AtomicLong> costWindowMap = new ConcurrentHashMap<>();
    /**
     * 当前处理的事件平均耗时
     */
    private Map<String, String> averageCostMap = new ConcurrentHashMap<>();
    /**
     * 最后一次处理的事件耗时
     */
    private Map<String, AtomicLong> lastCostMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(ThroughputScan.class);
        map.forEach((k, v) -> {
            Method[] methods = v.getClass().getMethods();
            // 过滤方法，只加载带有Throughput注解的
            for (Method method : methods) {
                Throughput function = AnnotationUtils.findAnnotation(method, Throughput.class);
                if (function != null) {
                    totalThroughputMap.put(function.name(), new AtomicLong(0L));
                    lastThroughputMap.put(function.name(), new AtomicLong(0L));
                    throughputPerSecondMap.put(function.name(), "0");
                    costWindowMap.put(function.name(), new AtomicLong(0L));
                    averageCostMap.put(function.name(), "0");
                    lastCostMap.put(function.name(), new AtomicLong(0L));
                }
            }
        });
    }

    /**
     * 功能描述: 打开监测管理器
     *
     * @author wangcanfeng
     * @date 2020/4/7-20:44
     * @since 2.0.0
     */
    @Override
    public void open() {
        if (working) {
            System.out.println("i am already at work");
            // 如果已经在工作中，就不做处理
            return;
        }
        executor.execute(() -> {
            resetData();
            working = true;
            System.out.println("start pipe management");
            for (; working; ) {
                lock.lock();
                try {
                    // 默认5秒执行一次
                    calculateLock.await(RagnarosConsts.DEFAULT_UNIT_TIME, TimeUnit.SECONDS);
                } catch (Exception e) {
                    System.err.println("An interrupt exception has occurred in task module,lock error");
                } finally {
                    lock.unlock();
                }
                for (String item : totalThroughputMap.keySet()) {
                    long currentTotal = totalThroughputMap.get(item).get();
                    long times = totalThroughputMap.get(item).get() - lastThroughputMap.get(item).get();
                    throughputPerSecondMap.put(item, CalculateUtils.division(times, RagnarosConsts.DEFAULT_UNIT_TIME, 4));
                    String average;
                    if (times == 0) {
                        average = costWindowMap.get(item).toString();
                    } else {
                        average = CalculateUtils.division(costWindowMap.get(item).get(), times, 4);
                    }
                    averageCostMap.put(item, average);
                    lastThroughputMap.get(item).set(currentTotal);
                    // 事件窗清空
                    costWindowMap.get(item).set(0L);
                }
            }
            System.out.println("pipe management stopped");
        });
    }

    /**
     * 功能描述: 关闭监测管理器
     *
     * @author wangcanfeng
     * @date 2020/4/7-20:44
     * @since 2.0.0
     */
    @Override
    public void close() {
        resetData();
        working = false;
        System.out.println("stopping pipe management");
    }

    /**
     * 功能描述: 设置花费时间
     *
     * @param name        吞吐量项目名称
     * @param currentCost 当前次处理花费
     * @author wangcanfeng
     * @date 2020/4/7-20:44
     * @since 2.0.0
     */
    @Override
    public void setCost(String name, long currentCost) {
        if (working) {
            lastCostMap.get(name).set(currentCost);
            costWindowMap.get(name).addAndGet(currentCost);
        }
    }

    /**
     * 功能描述: 增加吞吐量计数
     *
     * @param name 吞吐量项目名称
     * @author wangcanfeng
     * @date 2020/4/7-20:44
     * @since 2.0.0
     */
    @Override
    public void addTotalThroughput(String name) {
        if (working) {
            totalThroughputMap.get(name).incrementAndGet();
        }
    }


    /**
     * 功能描述: 获取所有需要要观测的管道信息
     *
     * @param
     * @return 返回信息： 管道检测信息
     * @author wangcanfeng
     * @date 2020/4/2-9:45
     * @since 2.0.0
     */
    @Override
    public ServiceThroughput watch() {
        ServiceThroughput data = new ServiceThroughput();
        // 如果有实现好的地址获取接口，就采用实现的，否则就从工具类中获取
        if (hostInfoService == null) {
            data.setHost(HostUtils.getLocalhost());
        } else {
            data.setHost(hostInfoService.getHost());
        }
        data.setStatus(working);
        List<ThroughputData> list = new ArrayList<>(totalThroughputMap.size());
        for (String item : totalThroughputMap.keySet()) {
            ThroughputData single = new ThroughputData();
            single.setAverageCost(averageCostMap.get(item));
            single.setItemName(item);
            single.setLastCost(lastCostMap.get(item).get());
            single.setThroughput(throughputPerSecondMap.get(item));
            list.add(single);
        }
        data.setDataList(list);
        return data;
    }

    /**
     * 功能描述: 重置数据
     *
     * @author wangcanfeng
     * @date 2020/4/2-11:10
     * @since 2.0.0
     */
    private void resetData() {
        totalThroughputMap.forEach((k, v) -> v.set(0L));
        lastThroughputMap.forEach((k, v) -> v.set(0L));
        throughputPerSecondMap.forEach((k, v) -> v = "0");
        costWindowMap.forEach((k, v) -> v.set(0L));
        averageCostMap.forEach((k, v) -> v = "0");
        lastCostMap.forEach((k, v) -> v.set(0L));
    }
}