package com.github.wangcanfeng01.freedom.ragnaros.vo;

import java.util.List;

/**
 * @author wangcanfeng
 * @description 服务器的吞吐量情况
 * @date Created in 12:00-2020/4/7
 * @since 2.0.0
 */
public class ServiceThroughput {

    /**
     * 服务地址，可以是host名称或者host的ip
     */
    private String host;
    /**
     *
     */
    private Boolean status;

    private List<ThroughputData> dataList;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ThroughputData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ThroughputData> dataList) {
        this.dataList = dataList;
    }
}