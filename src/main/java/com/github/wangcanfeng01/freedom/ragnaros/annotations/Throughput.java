package com.github.wangcanfeng01.freedom.ragnaros.annotations;

import java.lang.annotation.*;

/**
 * 加在方法上表示要围绕这个方法计算吞吐量
 * Created in 11:51-2020/4/7
 *
 * @author wangcanfeng
 * 为了方便找到被标记的方法，还需要再类上加下面这个注释
 * @see ThroughputScan
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Throughput {
    /**
     * 吞吐量项目名称
     */
    String name();
}
