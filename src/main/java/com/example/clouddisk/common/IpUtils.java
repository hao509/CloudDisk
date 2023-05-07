package com.example.clouddisk.common;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author:faryhao
 * @create: 2023-05-07 13:36
 * @Description: ip
 */
@Slf4j
public class IpUtils {
    public static String getIPAddress(HttpServletRequest request) throws UnknownHostException {

        String ip = null;
        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses))
        {
            //Proxy-Client-IP：apach 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if ( ipAddresses == null || ipAddresses. length() ==0 ||
                " unknown" .equalsIgnoreCase ( ipAddresses )) {
            //WL-Proxy-Client-IP: weblogic务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");  }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");  }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("X-Real-IP"); }

        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];  }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            InetAddress addr = InetAddress.getLocalHost();

            ip = addr.getHostAddress();;}
        if (ip.equals("0:0:0:0:0:0:0:1")) {

            InetAddress addr = InetAddress.getLocalHost();

            ip = addr.getHostAddress();
            log.info("ip ===="+ip);
        }
        return ip;
    }
}
