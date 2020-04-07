package org.wcf.freedom.ragnaros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcf.freedom.ragnaros.annotations.CustomPath;
import org.wcf.freedom.ragnaros.calculator.ManagementService;
import org.wcf.freedom.ragnaros.constant.RagnarosConsts;
import org.wcf.freedom.ragnaros.service.DistributedService;
import org.wcf.freedom.ragnaros.service.HostInfoService;
import org.wcf.freedom.ragnaros.service.TestClass;
import org.wcf.freedom.ragnaros.utils.HostUtils;
import org.wcf.freedom.ragnaros.vo.RagnarosResponse;
import org.wcf.freedom.ragnaros.vo.ServiceThroughput;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;


/**
 * @author wangcanfeng
 * @description 面向用户的访问接口
 * @date Created in 10:56-2020/4/7
 * @since 2.0.0
 */
@RestController
public class RagnarosController {

    private final DistributedService distributedService;

    @Autowired(required = false)
    private HostInfoService hostInfoService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    public RagnarosController(DistributedService distributedService) {
        this.distributedService = distributedService;
    }


    /**
     * 功能描述: 获取所有实例的吞吐量详情
     *
     * @return 返回信息： 各个实例的吞吐量情况集合
     * @author wangcanfeng
     * @date 2020/4/7-16:11
     * @since 2.0.0
     */
    @GetMapping("api/throughput/watch/all")
    @CustomPath(pathKey = RagnarosConsts.API_THROUGHPUT_WATCH_ALL)
    public RagnarosResponse<List<ServiceThroughput>> watchAll() {
        List<ServiceThroughput> throughputList = new LinkedList<>();
        throughputList.add(managementService.watch());
        String localhost = getHost();
        throughputList.addAll(distributedService.watchOthers(localhost));
        return new RagnarosResponse<>(throughputList);
    }

    /**
     * 功能描述: 打开所有实例的吞吐量监控页面
     *
     * @author wangcanfeng
     * @date 2020/4/7-16:11
     * @since 2.0.0
     */
    @GetMapping("api/throughput/open/all")
    @CustomPath(pathKey = RagnarosConsts.API_THROUGHPUT_OPEN_ALL)
    public RagnarosResponse<String> openAll() {
        String localhost = getHost();
        managementService.open();
        distributedService.openOthers(localhost);
        return RagnarosResponse.ok();
    }

    /**
     * 功能描述: 关闭所有实例的吞吐量监控页面
     *
     * @author wangcanfeng
     * @date 2020/4/7-16:11
     * @since 2.0.0
     */
    @GetMapping("api/throughput/close/all")
    @CustomPath(pathKey = RagnarosConsts.API_THROUGHPUT_CLOSE_ALL)
    public RagnarosResponse<String> closeAll() {
        String localhost = getHost();
        managementService.close();
        distributedService.closeOthers(localhost);
        return RagnarosResponse.ok();
    }


    @GetMapping("api/throughput/watch/single")
    @CustomPath(pathKey = RagnarosConsts.API_THROUGHPUT_WATCH_SINGLE)
    public RagnarosResponse<ServiceThroughput> watchSingle() {
        return new RagnarosResponse<>(managementService.watch());
    }

    /**
     * 功能描述: 打开吞吐量观测器
     *
     * @author wangcanfeng
     * @date 2020/4/2-10:49
     * @since 2.0.0
     */
    @GetMapping("api/throughput/open/single")
    @CustomPath(pathKey = RagnarosConsts.API_THROUGHPUT_OPEN_SINGLE)
    public RagnarosResponse<String> openWatch() {
        managementService.open();
        return RagnarosResponse.ok();
    }

    /**
     * 功能描述: 关闭吞吐量观测器
     *
     * @author wangcanfeng
     * @date 2020/4/2-10:49
     * @since 2.0.0
     */
    @GetMapping("api/throughput/close/single")
    @CustomPath(pathKey = RagnarosConsts.API_THROUGHPUT_CLOSE_SINGLE)
    public RagnarosResponse<String> closeWatch() {
        managementService.close();
        return RagnarosResponse.ok();
    }

    private String getHost() {
        return hostInfoService == null ? HostUtils.getLocalhost() : hostInfoService.getHost();
    }
}
