package com.tdt.wheel.rpc.server;/**
 * Created by rc on 2019/8/25.
 */

/**
 * @author qrc
 * @description 本机基本信息
 * @date 2019/8/25
 */
public class RegistryInfo {

    private String hostname;
    private String ip;
    private Integer port;

    public RegistryInfo(String hostname, String ip, Integer port) {
        this.hostname = hostname;
        this.ip = ip;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public RegistryInfo setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public RegistryInfo setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public RegistryInfo setPort(Integer port) {
        this.port = port;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RegistryInfo{");
        sb.append("hostname='").append(hostname).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", port=").append(port);
        sb.append('}');
        return sb.toString();
    }
}
