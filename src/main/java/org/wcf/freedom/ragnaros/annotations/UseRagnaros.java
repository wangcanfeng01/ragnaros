package org.wcf.freedom.ragnaros.annotations;

import org.springframework.context.annotation.Import;
import org.wcf.freedom.ragnaros.configuration.RagnarosCommonConfiguration;

import java.lang.annotation.*;

/**
 * @author wangcanfeng
 * @description 启用Ragnaros
 * @date Created in 10:42-2020/4/7
 * @since 2.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RagnarosCommonConfiguration.class)
@Documented
public @interface UseRagnaros {
}