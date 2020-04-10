package com.github.wangcanfeng01.freedom.ragnaros.annotations;

import java.lang.annotation.*;

/**
 * @author wangcanfeng
 * @description 自定义路径
 * @date Created in 15:04-2020/4/7
 * @since 2.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomPath {
    String pathKey();
}
