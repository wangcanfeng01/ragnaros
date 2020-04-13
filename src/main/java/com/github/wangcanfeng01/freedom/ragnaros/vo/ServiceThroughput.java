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
     *
     */
    private Boolean status;

    private List<ThroughputData> dataList;

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
}
