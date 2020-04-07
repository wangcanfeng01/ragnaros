package org.wcf.freedom.ragnaros.calculator;

import org.wcf.freedom.ragnaros.vo.ServiceThroughput;

/**
 * @author wangcanfeng
 * @description
 * @date Created in 20:42-2020/4/7
 * @since 2.0.0
 */
public interface ManagementService {


    /**
     * 功能描述: 获取所有需要要观测的管道信息
     *
     * @param
     * @return 返回信息： 管道检测信息
     * @author wangcanfeng
     * @date 2020/4/2-9:45
     * @since 2.0.0
     */
    ServiceThroughput watch();

    /**
     * 功能描述: 打开监测管理器
     *
     * @author wangcanfeng
     * @date 2020/4/7-20:44
     * @since 2.0.0
     */
    void open();

    /**
     * 功能描述: 关闭监测管理器
     *
     * @author wangcanfeng
     * @date 2020/4/7-20:44
     * @since 2.0.0
     */
    void close();

    /**
     * 功能描述: 设置花费时间
     *
     * @param name        吞吐量项目名称
     * @param currentCost 当前次处理花费
     * @author wangcanfeng
     * @date 2020/4/7-20:44
     * @since 2.0.0
     */
    void setCost(String name, long currentCost);

    /**
     * 功能描述: 增加吞吐量计数
     *
     * @param name 吞吐量项目名称
     * @author wangcanfeng
     * @date 2020/4/7-20:44
     * @since 2.0.0
     */
    void addTotalThroughput(String name);
}