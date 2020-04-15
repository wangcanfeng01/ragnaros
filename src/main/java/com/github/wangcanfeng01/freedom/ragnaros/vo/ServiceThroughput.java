package com.github.wangcanfeng01.freedom.ragnaros.vo;

import java.util.List;

/**
 * 服务器的吞吐量情况
 * Created in 12:00-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
public class ServiceThroughput {

    /**
     * 服务地址，可以是host名称或者host的ip
     */
    private String host;
    /**
     * 状态
     */
    private Boolean status;

    /**
     * 具体数据集
     */
    private List<ThroughputData> dataList;

    /**
     * 历史吞吐量信息。当前实例历史记录的汇总
     */
    private ChartInfo historyTotalThroughput;

    public String getHost() {
        return host;
    }

    public ServiceThroughput setHost(String host) {
        this.host = host;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public ServiceThroughput setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public List<ThroughputData> getDataList() {
        return dataList;
    }

    public ServiceThroughput setDataList(List<ThroughputData> dataList) {
        this.dataList = dataList;
        return this;
    }

    public ChartInfo getHistoryTotalThroughput() {
        return historyTotalThroughput;
    }

    public void setHistoryTotalThroughput(ChartInfo historyTotalThroughput) {
        this.historyTotalThroughput = historyTotalThroughput;
    }
}
