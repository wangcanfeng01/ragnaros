package com.github.wangcanfeng01.freedom.ragnaros.annotations;

import java.lang.annotation.*;

/**
 * 自定义路径
 * Created in 15:04-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomPath {
    String pathKey();
}
