package com.github.wangcanfeng01.freedom.ragnaros.calculator;

import com.github.wangcanfeng01.freedom.ragnaros.utils.CalculateUtils;
import com.github.wangcanfeng01.freedom.ragnaros.vo.ChartInfo;
import com.github.wangcanfeng01.freedom.ragnaros.vo.NameAndValue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能说明：吞吐量历史信息存放
 * Created in 19:50-2020/4/13
 *
 * @author wangcanfeng
 * @since 1.2
 */
final class HistoryThroughputContainer {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * 这里写明了用链表，方便数据移除和添加，减少数据拷贝的消耗
     */
    private final static Map<String, LinkedList<NameAndValue>> HISTORY_THROUGHPUT = new ConcurrentHashMap<>();
    /**
     * 默认链表长度为30
     */
    private static int historyCapacity = 30;

    private HistoryThroughputContainer() {
        // NO_OP
    }

    /**
     * 功能描述: 设置每个历史记录的长度
     * Created in 2020/4/13-20:11
     *
     * @param capacity 历史记录的容量
     * @author wangcanfeng
     * @since 1.2
     */
    static void setHistoryCapacity(int capacity) {
        historyCapacity = capacity;
    }

    /**
     * 功能描述: 初始化吞吐量项目，之所以不放在下面是为了减少数据刷新时的判断过程，以及不必要的多线程冲突
     * Created in 2020/4/13-20:11
     *
     * @param name 吞吐量名称
     * @author wangcanfeng
     * @since 1.2
     */
    static void initItem(String name) {
        LinkedList<NameAndValue> history = new LinkedList<>();
        for (int i = 0; i < historyCapacity; i++) {
            history.add(new NameAndValue(String.valueOf(i), "0.0000"));
        }
        HISTORY_THROUGHPUT.computeIfAbsent(name, k -> history);
    }

    /**
     * 功能描述: 刷新历史记录
     * Created in 2020/4/13-20:19
     *
     * @param name       吞吐量名称
     * @param throughput 当前吞吐量
     * @author wangcanfeng
     * @since 1.2
     */
    static void refreshData(String name, String throughput) {
        LinkedList<NameAndValue> list = HISTORY_THROUGHPUT.get(name);
        if (list.size() > historyCapacity) {
            list.removeFirst();
        }
        list.addLast(new NameAndValue(LocalTime.now().format(formatter), throughput));
    }

    /**
     * 功能简述：根据吞吐量名称获取吞吐量历史信息
     * Created in 2020/4/15-22:18
     *
     * @param name 吞吐率名称
     * @return 吞吐量历史信息
     * @author wangcanfeng
     * @since 1.2
     */
    static ChartInfo getData(String name) {
        ChartInfo chartInfo = new ChartInfo();
        List<String> names = new LinkedList<>();
        List<String> values = new LinkedList<>();
        chartInfo.setDataNames(names);
        chartInfo.setDataValues(values);
        HISTORY_THROUGHPUT.get(name).forEach(v -> {
            names.add(v.getName());
            values.add(v.getValue());
        });
        return chartInfo;
    }

    static ChartInfo getTotalData() {
        List<NameAndValue> total = new ArrayList<>(historyCapacity);
        for (int i = 0; i < historyCapacity; i++) {
            total.add(new NameAndValue("", "0.0000"));
        }
        HISTORY_THROUGHPUT.forEach((k, v) -> {
            for (int i = 0; i < historyCapacity; i++) {
                NameAndValue item = new NameAndValue();
                item.setValue(CalculateUtils.addition(total.get(i).getValue(), v.get(i).getValue(), CalculateUtils.DEFAULT_PRECISION));
                item.setName(v.get(i).getName());
                total.set(i, item);
            }
        });
        ChartInfo chartInfo = new ChartInfo();
        List<String> names = new LinkedList<>();
        List<String> values = new LinkedList<>();
        chartInfo.setDataNames(names);
        chartInfo.setDataValues(values);
        total.forEach(v -> {
            names.add(v.getName());
            values.add(v.getValue());
        });
        return chartInfo;
    }

    /**
     * 功能简述：重置数据
     * Created in 2020/4/15-22:10
     *
     * @author wangcanfeng
     * @since 1.2
     */
    static void resetItem() {
        HISTORY_THROUGHPUT.forEach((k, v) -> {
            v.forEach(data -> data.setValue("0.0000"));
        });
    }


}