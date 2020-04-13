package com.github.wangcanfeng01.freedom.ragnaros.controller;

import com.github.wangcanfeng01.freedom.ragnaros.annotations.CustomPath;
import com.github.wangcanfeng01.freedom.ragnaros.calculator.ManagementService;
import com.github.wangcanfeng01.freedom.ragnaros.constant.UrlConsts;
import com.github.wangcanfeng01.freedom.ragnaros.service.DistributedService;
import com.github.wangcanfeng01.freedom.ragnaros.service.HostInfoService;
import com.github.wangcanfeng01.freedom.ragnaros.utils.HostUtils;
import com.github.wangcanfeng01.freedom.ragnaros.vo.RagnarosResponse;
import com.github.wangcanfeng01.freedom.ragnaros.vo.ServiceThroughput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;


/**
 * 面向用户的访问接口
 * Created in 10:56-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
@RestController
public class RagnarosController {

    private final DistributedService distributedService;

    private final HostInfoService hostInfoService;

    private ManagementService managementService;

    @Autowired
    public RagnarosController(DistributedService distributedService, ManagementService managementService, @Nullable HostInfoService hostInfoService) {
        this.distributedService = distributedService;
        this.managementService = managementService;
        this.hostInfoService = hostInfoService;
    }


    /**
     * 功能描述: 获取所有实例的吞吐量详情
     * 2020/4/7-16:11
     *
     * @return 各个实例的吞吐量情况集合
     * @author wangcanfeng
     * @since 1.0.0
     */
    @GetMapping(UrlConsts.Default.API_THROUGHPUT_WATCH_ALL)
    @CustomPath(pathKey = UrlConsts.Customize.API_THROUGHPUT_WATCH_ALL)
    public RagnarosResponse<List<ServiceThroughput>> watchAll() {
        List<ServiceThroughput> throughputList = new LinkedList<>();
        throughputList.add(managementService.watch());
        String localhost = getHost();
        throughputList.addAll(distributedService.watchOthers(localhost));
        return new RagnarosResponse<>(throughputList);
    }

    /**
     * 功能描述: 打开所有实例的吞吐量监控页面
     * 2020/4/7-16:11
     *
     * @return 开启成功
     * @author wangcanfeng
     * @since 1.0.0
     */
    @PostMapping(UrlConsts.Default.API_THROUGHPUT_OPEN_ALL)
    @CustomPath(pathKey = UrlConsts.Customize.API_THROUGHPUT_OPEN_ALL)
    public RagnarosResponse<String> openAll() {
        String localhost = getHost();
        managementService.open();
        distributedService.openOthers(localhost);
        return RagnarosResponse.ok();
    }

    /**
     * 功能描述: 关闭所有实例的吞吐量监控页面
     * 2020/4/7-16:11
     *
     * @return 关闭成功
     * @author wangcanfeng
     * @since 1.0.0
     */
    @PostMapping(UrlConsts.Default.API_THROUGHPUT_CLOSE_ALL)
    @CustomPath(pathKey = UrlConsts.Customize.API_THROUGHPUT_CLOSE_ALL)
    public RagnarosResponse<String> closeAll() {
        String localhost = getHost();
        managementService.close();
        distributedService.closeOthers(localhost);
        return RagnarosResponse.ok();
    }


    /**
     * 功能描述: 查看当前实例的吞吐量情况
     * Created in 2020/4/11-10:25
     *
     * @return 当前实例的吞吐量情况
     * @author wangcanfeng
     * @since 1.0.0
     */
    @GetMapping(UrlConsts.Default.API_THROUGHPUT_WATCH_SINGLE)
    @CustomPath(pathKey = UrlConsts.Customize.API_THROUGHPUT_WATCH_SINGLE)
    public RagnarosResponse<ServiceThroughput> watchSingle() {
        return new RagnarosResponse<>(managementService.watch());
    }

    /**
     * 功能描述: 打开吞吐量观测器
     * 2020/4/2-10:49
     *
     * @return 打开成功
     * @author wangcanfeng
     * @since 1.0.0
     */
    @PostMapping(UrlConsts.Default.API_THROUGHPUT_OPEN_SINGLE)
    @CustomPath(pathKey = UrlConsts.Customize.API_THROUGHPUT_OPEN_SINGLE)
    public RagnarosResponse<String> openWatch() {
        managementService.open();
        return RagnarosResponse.ok();
    }

    /**
     * 功能描述: 关闭吞吐量观测器
     * 2020/4/2-10:49
     *
     * @return 关闭成功
     * @author wangcanfeng
     * @since 1.0.0
     */
    @PostMapping(UrlConsts.Default.API_THROUGHPUT_CLOSE_SINGLE)
    @CustomPath(pathKey = UrlConsts.Customize.API_THROUGHPUT_CLOSE_SINGLE)
    public RagnarosResponse<String> closeWatch() {
        managementService.close();
        return RagnarosResponse.ok();
    }

    private String getHost() {
        return hostInfoService == null ? HostUtils.getLocalhost() : hostInfoService.getHost();
    }
}