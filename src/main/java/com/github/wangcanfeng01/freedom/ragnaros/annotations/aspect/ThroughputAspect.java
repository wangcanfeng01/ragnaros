package com.github.wangcanfeng01.freedom.ragnaros.annotations.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import com.github.wangcanfeng01.freedom.ragnaros.annotations.Throughput;
import com.github.wangcanfeng01.freedom.ragnaros.calculator.ManagementService;

import java.lang.reflect.Method;

/**
 * 吞吐量统计切面
 * Created in 13:57-2020/4/7
 * @author wangcanfeng
 * @since 1.0.0
 */
@Aspect
@Component
public class ThroughputAspect {

    @Autowired
    private ManagementService managementService;

    @Pointcut("@annotation(com.github.wangcanfeng01.freedom.ragnaros.annotations.Throughput)")
    public void methodAspect() {

    }

    @Around("methodAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Throughput action = method.getAnnotation(Throughput.class);
        if (!ObjectUtils.isEmpty(action)) {
            managementService.addTotalThroughput(action.name());
        }
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            if (!ObjectUtils.isEmpty(action)) {
                managementService.setCost(action.name(), System.currentTimeMillis() - start);
            }
        }
    }
}
