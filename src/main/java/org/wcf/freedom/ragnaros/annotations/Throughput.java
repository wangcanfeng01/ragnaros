package org.wcf.freedom.ragnaros.annotations;

import java.lang.annotation.*;

/**
 * @author wangcanfeng
 * @description 加在方法上表示要围绕这个方法计算吞吐量
 * @date Created in 11:51-2020/4/7
 * 为了方便找到被标记的方法，还需要再类上加下面这个注释
 * @see ThroughputScan
 * @since 2.0.0
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
