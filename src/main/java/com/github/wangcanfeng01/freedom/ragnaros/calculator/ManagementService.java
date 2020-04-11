package com.github.wangcanfeng01.freedom.ragnaros.calculator;

import com.github.wangcanfeng01.freedom.ragnaros.vo.ServiceThroughput;

/**
 * Created in 20:42-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
public interface ManagementService {


    /**
     * 功能描述: 获取所有需要要观测的管道信息
     * 2020/4/2-9:45
     * @return 返回信息： 管道检测信息
     * @author wangcanfeng
     * @since 1.0.0
     */
    ServiceThroughput watch();

    /**
     * 功能描述: 打开监测管理器
     * 2020/4/7-20:44
     *
     * @author wangcanfeng
     * @since 1.0.0
     */
    void open();

    /**
     * 功能描述: 关闭监测管理器
     * 2020/4/7-20:44
     *
     * @author wangcanfeng
     * @since 1.0.0
     */
    void close();

    /**
     * 功能描述: 设置花费时间
     * 2020/4/7-20:44
     *
     * @param name        吞吐量项目名称
     * @param currentCost 当前次处理花费
     * @author wangcanfeng
     * @since 1.0.0
     */
    void setCost(String name, long currentCost);

     /**
     * 功能描述: 增加吞吐量计数
     * 2020/4/7-20:44
     *
     * @param name 吞吐量项目名称
     * @author wangcanfeng
     * @since 1.0.0
     */
    void addTotalThroughput(String name);
}
