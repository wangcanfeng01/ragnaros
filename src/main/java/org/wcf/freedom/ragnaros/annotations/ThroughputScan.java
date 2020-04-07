
package org.wcf.freedom.ragnaros.annotations;

import java.lang.annotation.*;

/**
 * @author wangcanfeng
 * @description 吞吐量注解，放在类上面帮助扫描，配合{@link Throughput}使用
 * @date Created in 20:13-2020/4/7
 * @since 2.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ThroughputScan {

}
