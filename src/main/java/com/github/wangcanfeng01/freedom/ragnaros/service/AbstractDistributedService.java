package com.github.wangcanfeng01.freedom.ragnaros.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wangcanfeng01.freedom.ragnaros.constant.RagnarosConsts;
import com.github.wangcanfeng01.freedom.ragnaros.constant.UrlConsts;
import com.github.wangcanfeng01.freedom.ragnaros.vo.RagnarosResponse;
import com.github.wangcanfeng01.freedom.ragnaros.vo.ServiceThroughput;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 分布式的抽象接口
 * Created in 16:31-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
public abstract class AbstractDistributedService implements DistributedService {

    /**
     * 多个请求可以使用同一个client，提高并发效率，节省内存
     */
    protected CloseableHttpClient httpClient = HttpClients.createDefault();
    private RequestConfig config = RequestConfig.custom()
            .setConnectionRequestTimeout(15000).setConnectTimeout(15000)
            .setSocketTimeout(15000).build();
    /**
     * 环境变量，配置参数读取
     */
    private Environment environment;

    void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 功能描述: 获取其他服务实例的地址
     * 2020/4/7-17:08
     *
     * @param localhost 本实例的服务地址
     * @return 返回信息：实例地址集合
     * @author wangcanfeng
     * @since 1.0.0
     */
    public abstract List<String> otherServiceAddress(String localhost);

    /**
     * 功能描述:如果需要配置额外的请求头信息
     * Created in 2020/4/13-15:56
     *
     * @return 自定义请求头信息
     * @author wangcanfeng
     * @since 2.0.0
     */
    public abstract Map<String, String> headers();

    /**
     * 功能描述: 观察其他实例的情况
     * 2020/4/7-17:03
     *
     * @param localhost 当前服务器的地址
     * @return 返回信息：其他实例的吞吐量信息
     * @author wangcanfeng
     * @since 1.0.0
     */
    @Override
    public List<ServiceThroughput> watchOthers(String localhost) {
        List<String> addresses = otherServiceAddress(localhost);
        if (ObjectUtils.isEmpty(addresses)) {
            return Collections.emptyList();
        }
        List<ServiceThroughput> throughput = new ArrayList<>(addresses.size());
        addresses.forEach(address -> throughput.add(obtainThroughput(address)));
        return throughput;
    }

    /**
     * 功能描述: 打开其他实例的观测器
     * 2020/4/7-17:03
     *
     * @param localhost 当前服务器的地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    @Override
    public void openOthers(String localhost) {
        List<String> addresses = otherServiceAddress(localhost);
        if (ObjectUtils.isEmpty(addresses)) {
            return;
        }
        addresses.forEach(this::openThroughput);
    }

    /**
     * 功能描述: 关闭其他实例的观测器
     * 2020/4/7-17:03
     *
     * @param localhost 当前服务器的地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    @Override
    public void closeOthers(String localhost) {
        List<String> addresses = otherServiceAddress(localhost);
        if (ObjectUtils.isEmpty(addresses)) {
            return;
        }
        addresses.forEach(this::closeThroughput);
    }


    /**
     * 功能描述: 实例地址对应的吞吐量信息
     * 2020/4/7-17:09
     *
     * @param serviceAddress 需要请求的实例地址
     * @return 返回信息：实例地址对应的吞吐量信息
     * @author wangcanfeng
     * @since 1.0.0
     */
    private ServiceThroughput obtainThroughput(String serviceAddress) {
        HttpGet get = new HttpGet(serviceAddress + customizePath(
                UrlConsts.Customize.API_THROUGHPUT_WATCH_SINGLE, UrlConsts.Default.API_THROUGHPUT_WATCH_SINGLE));
        get.setConfig(config);
        Map<String, String> headers = headers();
        if (!ObjectUtils.isEmpty(headers)) {
            headers.forEach(get::setHeader);
        }
        try (CloseableHttpResponse response = httpClient.execute(get)) {
            if (!ObjectUtils.isEmpty(response)) {
                HttpEntity entity = response.getEntity();
                RagnarosResponse result = JSON.parseObject(EntityUtils.toString(entity), RagnarosResponse.class);
                return JSON.toJavaObject((JSONObject) result.getData(), ServiceThroughput.class);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return new ServiceThroughput().setHost(serviceAddress + "/error").setStatus(false);
    }

    /**
     * 功能描述:打开实例地址对应的吞吐量检测器
     * 2020/4/7-17:09
     *
     * @param serviceAddress 需要请求的实例地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    private void openThroughput(String serviceAddress) {
        HttpPost post = new HttpPost(serviceAddress + customizePath(
                UrlConsts.Customize.API_THROUGHPUT_OPEN_SINGLE, UrlConsts.Default.API_THROUGHPUT_OPEN_SINGLE));
        post.setConfig(config);
        try (CloseableHttpResponse response = httpClient.execute(post)) {
            if (!ObjectUtils.isEmpty(response)) {
                HttpEntity entity = response.getEntity();
                RagnarosResponse result = JSON.parseObject(EntityUtils.toString(entity), RagnarosResponse.class);
                if (!RagnarosConsts.SUCCESS_CODE.equalsIgnoreCase(result.getCode())) {
                    System.out.println("open failed" + serviceAddress);
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 功能描述:关闭实例地址对应的吞吐量检测器
     * 2020/4/7-17:09
     *
     * @param serviceAddress 需要请求的实例地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    private void closeThroughput(String serviceAddress) {
        HttpPost post = new HttpPost(serviceAddress + customizePath(
                UrlConsts.Customize.API_THROUGHPUT_CLOSE_SINGLE, UrlConsts.Default.API_THROUGHPUT_CLOSE_SINGLE));
        post.setConfig(config);
        try (CloseableHttpResponse response = httpClient.execute(post)) {
            if (!ObjectUtils.isEmpty(response)) {
                HttpEntity entity = response.getEntity();
                RagnarosResponse result = JSON.parseObject(EntityUtils.toString(entity), RagnarosResponse.class);
                if (!RagnarosConsts.SUCCESS_CODE.equalsIgnoreCase(result.getCode())) {
                    System.out.println("close failed" + serviceAddress);
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 功能描述: 获取配置中的自定义路径
     * Created in 2020/4/13-16:23
     *
     * @param key 自定义路径的配置键
     * @param def 默认值
     * @return
     * @author wangcanfeng
     * @since 2.0.0
     */
    private String customizePath(String key, String def) {
        if (this.environment != null) {
            String path = environment.getProperty(key);
            if (!ObjectUtils.isEmpty(path)) {
                return path;
            }
        }
        return def;
    }
}