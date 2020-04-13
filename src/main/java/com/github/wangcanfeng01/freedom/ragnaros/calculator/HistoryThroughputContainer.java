package com.github.wangcanfeng01.freedom.ragnaros.calculator;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能说明：吞吐量历史信息存放
 * Created in 19:50-2020/4/13
 *
 * @author wangcanfeng
 * @since 2.0.0
 */
public final class HistoryThroughputContainer {

    /**
     * 这里写明了用链表，方便数据移除和添加，减少数据拷贝的消耗
     */
    private final static Map<String, LinkedList<String>> HISTORY_THROUGHPUT = new ConcurrentHashMap<>();
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
     * @since 2.0.0
     */
    public static void setHistoryCapacity(int capacity) {
        historyCapacity = capacity;
    }

    /**
     * 功能描述: 初始化吞吐量项目，之所以不放在下面是为了减少数据刷新时的判断过程，以及不必要的多线程冲突
     * Created in 2020/4/13-20:11
     *
     * @param name 吞吐量名称
     * @author wangcanfeng
     * @since 2.0.0
     */
    public static void initItem(String name) {
        HISTORY_THROUGHPUT.computeIfAbsent(name, k -> new LinkedList<>());
    }

    /**
     * 功能描述: 刷新历史记录
     * Created in 2020/4/13-20:19
     *
     * @param name       吞吐量名称
     * @param throughput 当前吞吐量
     * @author wangcanfeng
     * @since 2.0.0
     */
    public static void refreshData(String name, String throughput) {
        LinkedList<String> list = HISTORY_THROUGHPUT.get(name);
        if (list.size() > historyCapacity) {
            list.removeFirst();
            list.addLast(throughput);
        } else {
            list.addLast(throughput);
        }
    }
}