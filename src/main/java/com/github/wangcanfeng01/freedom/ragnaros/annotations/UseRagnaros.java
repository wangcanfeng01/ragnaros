package com.github.wangcanfeng01.freedom.ragnaros.annotations;

import com.github.wangcanfeng01.freedom.ragnaros.configuration.RagnarosCommonConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用Ragnaros
 * Created in 10:42-2020/4/7
 * @author wangcanfeng
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RagnarosCommonConfiguration.class)
@Documented
public @interface UseRagnaros {
}
