package com.github.wangcanfeng01.freedom.ragnaros.service;

import com.github.wangcanfeng01.freedom.ragnaros.annotations.Throughput;
import com.github.wangcanfeng01.freedom.ragnaros.annotations.ThroughputScan;
import org.springframework.stereotype.Component;

/**
 * @author wangcanfeng
 * @description
 * @date Created in 20:38-2020/4/7
 * @since 2.0.0
 */
@Component
@ThroughputScan
public class TestClass {

    @Throughput(name = "wcf.test")
    public String getHost() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("dsadasdasdas");
        return null;
    }
}
