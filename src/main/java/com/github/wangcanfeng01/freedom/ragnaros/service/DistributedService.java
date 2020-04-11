package com.github.wangcanfeng01.freedom.ragnaros.service;

import com.github.wangcanfeng01.freedom.ragnaros.vo.ServiceThroughput;

import java.util.List;

/**
 * 分布式的抽象接口
 * Created in 16:29-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
public interface DistributedService {


    /**
     * 功能描述: 观察其他实例的情况
     * 2020/4/7-17:03
     *
     * @param localhost 当前服务器的地址
     * @return 返回信息：其他实例的吞吐量信息
     * @author wangcanfeng
     * @since 1.0.0
     */
    List<ServiceThroughput> watchOthers(String localhost);

    /**
     * 功能描述: 打开其他实例的观测器
     * 2020/4/7-17:03
     *
     * @param localhost 当前服务器的地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    void openOthers(String localhost);

    /**
     * 功能描述: 关闭其他实例的观测器
     * 2020/4/7-17:03
     *
     * @param localhost 当前服务器的地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    void closeOthers(String localhost);
}
