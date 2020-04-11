package com.github.wangcanfeng01.freedom.ragnaros.utils;

import org.springframework.util.ObjectUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/**
 * Created in 21:12-2020/4/7
 *
 * @author wangcanfeng
 * @since 1.0.0
 */
public class HostUtils {
    /**
     * 功能描述: 获取本地ip地址，目前仅从回环地址和localhost中获取ip，优先选取非
     * 2020/4/3-12:19
     *
     * @return 返回信息： 本机ip地址
     * @author wangcanfeng
     * @since 1.0.0
     */
    public static String getLocalhost() {
        List<String> ips = new LinkedList<>();
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && !ip.isLoopbackAddress()
                            && !ip.getHostAddress().contains(":")) {
                        ips.add(ip.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            // NO_OP
        }
        // 真的没有就先用127.0.0.1代替吧
        if (ObjectUtils.isEmpty(ips)) {
            return "127.0.0.1";
        } else {
            // 我们也不是很想要拿到局域网的信息，最好是外网的
            String result = ips.get(0);
            for (String ip : ips) {
                if (!ip.contains("192")) {
                    result = ip;
                }
            }
            return result;
        }
    }
}
