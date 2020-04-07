package org.wcf.freedom.ragnaros.vo;

/**
 * @author wangcanfeng
 * @description 吞吐量数据
 * @date Created in 11:56-2020/4/7
 * @since 2.0.0
 */
public class ThroughputData {

    /**
     * 吞吐量统计项名称
     */
    private String itemName;
    /**
     * 单个时间窗内平均处理时间
     */
    private long averageCost;

    /**
     * 最后一次处理时间
     */
    private long lastCost;

    /**
     * 时间窗内的吞吐量
     */
    private long throughput;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(long averageCost) {
        this.averageCost = averageCost;
    }

    public long getLastCost() {
        return lastCost;
    }

    public void setLastCost(long lastCost) {
        this.lastCost = lastCost;
    }

    public long getThroughput() {
        return throughput;
    }

    public void setThroughput(long throughput) {
        this.throughput = throughput;
    }
}

