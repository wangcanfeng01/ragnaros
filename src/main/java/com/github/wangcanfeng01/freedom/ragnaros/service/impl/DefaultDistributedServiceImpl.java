package com.github.wangcanfeng01.freedom.ragnaros.service.impl;

import com.github.wangcanfeng01.freedom.ragnaros.service.AbstractDistributedService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.github.wangcanfeng01.freedom.ragnaros.constant.RagnarosConsts.DEFAULT_DISTRIBUTED_SERVICE;

/**
 * 分布式接口实现类
 * Created in 17:03-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
@Service(DEFAULT_DISTRIBUTED_SERVICE)
public final class DefaultDistributedServiceImpl extends AbstractDistributedService {

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
     * 功能描述:如果需要配置额外的请求头信息
     * Created in 2020/4/13-15:56
     *
     * @return 自定义请求头信息
     * @author wangcanfeng
     * @since 2.0.0
     */
    @Override
    public Map<String, String> headers() {
        return null;
    }

}