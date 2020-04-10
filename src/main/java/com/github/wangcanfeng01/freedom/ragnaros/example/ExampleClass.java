package com.github.wangcanfeng01.freedom.ragnaros.example;

import org.springframework.stereotype.Component;
import com.github.wangcanfeng01.freedom.ragnaros.annotations.Throughput;
import com.github.wangcanfeng01.freedom.ragnaros.annotations.ThroughputScan;

import java.util.concurrent.TimeUnit;

/**
 * @author wangcanfeng
 * @time 2020/4/10-21:38
 * @function 注解使用示例
 **/
@Component
@ThroughputScan
public class ExampleClass {

    @Throughput(name = "wcf.test")
    public String test() {
        try {
            TimeUnit.MILLISECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "started";
    }
}
