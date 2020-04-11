package com.github.wangcanfeng01.freedom.ragnaros.service.impl;

import org.springframework.stereotype.Service;
import com.github.wangcanfeng01.freedom.ragnaros.service.AbstractDistributedService;
import com.github.wangcanfeng01.freedom.ragnaros.vo.ServiceThroughput;

import java.util.Collections;
import java.util.List;

import static com.github.wangcanfeng01.freedom.ragnaros.constant.RagnarosConsts.DEFAULT_DISTRIBUTED_SERVICE;

/**
 * 分布式接口实现类
 * Created in 17:03-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
@Service(DEFAULT_DISTRIBUTED_SERVICE)
public class DefaultDistributedServiceImpl extends AbstractDistributedService {

    /**
     * 功能描述: 获取其他服务实例的地址
     * 2020/4/7-17:08
     *
     * @param localhost 本实例的服务地址
     * @return 返回信息：
     * @author wangcanfeng
     * @since 1.0.0
     */
    @Override
    public List<String> otherServiceAddress(String localhost) {
        return Collections.emptyList();
    }

    /**
     * 功能描述: 实例地址对应的吞吐量信息
     * 2020/4/7-17:09
     *
     * @param serviceAddress 需要请求的实例地址
     * @return 返回信息：实例地址对应的吞吐量信息
     * @author wangcanfeng
     * @since 1.0.0
     */
    @Override
    public ServiceThroughput obtainThroughput(String serviceAddress) {
        return null;
    }

    /**
     * 功能描述:打开实例地址对应的吞吐量检测器
     * 2020/4/7-17:09
     *
     * @param serviceAddress 需要请求的实例地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    @Override
    public void openThroughput(String serviceAddress) {
    }

    /**
     * 功能描述:关闭实例地址对应的吞吐量检测器
     * 2020/4/7-17:09
     *
     * @param serviceAddress 需要请求的实例地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    @Override
    public void closeThroughput(String serviceAddress) {

    }
}
