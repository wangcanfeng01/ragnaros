package org.wcf.freedom.ragnaros.service;

import org.wcf.freedom.ragnaros.vo.ServiceThroughput;

import java.util.List;

/**
 * @author wangcanfeng
 * @description 分布式的抽象接口
 * @date Created in 16:29-2020/4/7
 * @since 2.0.0
 */
public interface DistributedService {


    /**
     * 功能描述: 观察其他实例的情况
     *
     * @param localhost 当前服务器的地址
     * @return 返回信息：其他实例的吞吐量信息
     * @author wangcanfeng
     * @date 2020/4/7-17:03
     * @since 2.0.0
     */
    List<ServiceThroughput> watchOthers(String localhost);

    /**
     * 功能描述: 打开其他实例的观测器
     *
     * @param localhost 当前服务器的地址
     * @author wangcanfeng
     * @date 2020/4/7-17:03
     * @since 2.0.0
     */
    void openOthers(String localhost);

    /**
     * 功能描述: 关闭其他实例的观测器
     *
     * @param localhost 当前服务器的地址
     * @author wangcanfeng
     * @date 2020/4/7-17:03
     * @since 2.0.0
     */
    void closeOthers(String localhost);
}
