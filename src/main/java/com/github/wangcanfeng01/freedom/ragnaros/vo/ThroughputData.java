package com.github.wangcanfeng01.freedom.ragnaros.vo;

/**
 * 吞吐量数据
 * Created in 11:56-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
public class ThroughputData {

    /**
     * 吞吐量统计项名称
     */
    private String itemName;
    /**
     * 单个时间窗内平均处理时间
     */
    private String averageCost;

    /**
     * 最后一次处理时间
     */
    private long lastCost;

    /**
     * 时间窗内的吞吐量
     */
    private String throughput;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(String averageCost) {
        this.averageCost = averageCost;
    }

    public long getLastCost() {
        return lastCost;
    }

    public void setLastCost(long lastCost) {
        this.lastCost = lastCost;
    }

    public String getThroughput() {
        return throughput;
    }

    public void setThroughput(String throughput) {
        this.throughput = throughput;
    }
}

