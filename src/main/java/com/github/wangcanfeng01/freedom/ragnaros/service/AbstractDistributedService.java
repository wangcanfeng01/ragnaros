package com.github.wangcanfeng01.freedom.ragnaros.service;

import org.springframework.util.ObjectUtils;
import com.github.wangcanfeng01.freedom.ragnaros.vo.ServiceThroughput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分布式的抽象接口
 * Created in 16:31-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
public abstract class AbstractDistributedService implements DistributedService {

    /**
     * 功能描述: 获取其他服务实例的地址
     * 2020/4/7-17:08
     *
     * @param localhost 本实例的服务地址
     * @return 返回信息：实例地址集合
     * @author wangcanfeng
     * @since 1.0.0
     */
    public abstract List<String> otherServiceAddress(String localhost);

    /**
     * 功能描述: 实例地址对应的吞吐量信息
     * 2020/4/7-17:09
     *
     * @param serviceAddress 需要请求的实例地址
     * @return 返回信息：实例地址对应的吞吐量信息
     * @author wangcanfeng
     * @since 1.0.0
     */
    public abstract ServiceThroughput obtainThroughput(String serviceAddress);

    /**
     * 功能描述:打开实例地址对应的吞吐量检测器
     * 2020/4/7-17:09
     *
     * @param serviceAddress 需要请求的实例地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    public abstract void openThroughput(String serviceAddress);

    /**
     * 功能描述:关闭实例地址对应的吞吐量检测器
     * 2020/4/7-17:09
     *
     * @param serviceAddress 需要请求的实例地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    public abstract void closeThroughput(String serviceAddress);

    /**
     * 功能描述: 观察其他实例的情况
     * 2020/4/7-17:03
     *
     * @param localhost 当前服务器的地址
     * @return 返回信息：其他实例的吞吐量信息
     * @author wangcanfeng
     * @since 1.0.0
     */
    @Override
    public List<ServiceThroughput> watchOthers(String localhost) {
        List<String> addresses = otherServiceAddress(localhost);
        if (ObjectUtils.isEmpty(addresses)) {
            return Collections.emptyList();
        }
        List<ServiceThroughput> throughput = new ArrayList<>(addresses.size());
        addresses.forEach(address -> throughput.add(obtainThroughput(address)));
        return throughput;
    }

    /**
     * 功能描述: 打开其他实例的观测器
     * 2020/4/7-17:03
     *
     * @param localhost 当前服务器的地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    @Override
    public void openOthers(String localhost) {
        List<String> addresses = otherServiceAddress(localhost);
        if (ObjectUtils.isEmpty(addresses)) {
            return;
        }
        addresses.forEach(this::openThroughput);
    }

    /**
     * 功能描述: 关闭其他实例的观测器
     * 2020/4/7-17:03
     *
     * @param localhost 当前服务器的地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    @Override
    public void closeOthers(String localhost) {
        List<String> addresses = otherServiceAddress(localhost);
        if (ObjectUtils.isEmpty(addresses)) {
            return;
        }
        addresses.forEach(this::closeThroughput);
    }
}
