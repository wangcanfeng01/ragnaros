package com.github.wangcanfeng01.freedom.ragnaros.configuration;

import com.github.wangcanfeng01.freedom.ragnaros.constant.RagnarosConsts;
import com.github.wangcanfeng01.freedom.ragnaros.controller.RagnarosController;
import com.github.wangcanfeng01.freedom.ragnaros.service.DistributedService;
import com.github.wangcanfeng01.freedom.ragnaros.service.HostInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

/**
 * @author wangcanfeng
 * @description 通用配置类
 * @date Created in 10:50-2020/4/7
 * @since 2.0.0
 */
@Configuration
@ComponentScan(basePackages = {
        "com.github.wangcanfeng01.freedom.ragnaros.annotations",
        "com.github.wangcanfeng01.freedom.ragnaros.calculator",
        "com.github.wangcanfeng01.freedom.ragnaros.controller",
        "com.github.wangcanfeng01.freedom.ragnaros.service",
        "com.github.wangcanfeng01.freedom.ragnaros.example"
})
public class RagnarosCommonConfiguration {


    /**
     * 注入spring的上下文
     */
    @Autowired
    private ApplicationContext context;
    @Autowired(required = false)
    private HostInfoService hostInfoService;

    @Bean
    public HandlerMapping ragnarosControllerMapping(Environment environment) {
        Map<String, DistributedService> map = context.getBeansOfType(DistributedService.class);
        DistributedService defaultService = null;
        if (map.size() > 1) {
            for (String key : map.keySet()) {
                if (!RagnarosConsts.DEFAULT_DISTRIBUTED_SERVICE.equals(key)) {
                    defaultService = map.get(key);
                }
            }
        } else {
            defaultService = map.get(RagnarosConsts.DEFAULT_DISTRIBUTED_SERVICE);
        }
        return new ControllerMappingHandler(environment, new RagnarosController(defaultService, hostInfoService));
    }
}
