package com.github.wangcanfeng01.freedom.ragnaros.annotations;

import com.github.wangcanfeng01.freedom.ragnaros.configuration.RagnarosCommonConfiguration;
import org.springframework.context.annotation.Import;

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