
package com.github.wangcanfeng01.freedom.ragnaros.annotations;

import java.lang.annotation.*;

/**
 * 吞吐量注解，放在类上面帮助扫描，配合{@link Throughput}使用
 * Created in 20:13-2020/4/7
 * @author wangcanfeng
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ThroughputScan {

}
