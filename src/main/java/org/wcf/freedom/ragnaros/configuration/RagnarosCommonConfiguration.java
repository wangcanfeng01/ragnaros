package org.wcf.freedom.ragnaros.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerMapping;
import org.wcf.freedom.ragnaros.constant.RagnarosConsts;
import org.wcf.freedom.ragnaros.controller.RagnarosController;
import org.wcf.freedom.ragnaros.service.DistributedService;

import java.util.Map;

/**
 * @author wangcanfeng
 * @description 通用配置类
 * @date Created in 10:50-2020/4/7
 * @since 2.0.0
 */
@Configuration
@ComponentScan(basePackages = {
        "org.wcf.freedom.ragnaros.annotations",
        "org.wcf.freedom.ragnaros.calculator",
        "org.wcf.freedom.ragnaros.controller"
})
public class RagnarosCommonConfiguration {


    /**
     * 注入spring的上下文
     */
    @Autowired
    private ApplicationContext context;

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
        return new ControllerMappingHandler(environment, new RagnarosController(defaultService));
    }
}
