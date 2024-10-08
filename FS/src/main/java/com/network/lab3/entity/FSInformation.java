package com.network.lab3.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FSInformation {
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("as_ip")
    private String asIp;
    @JsonProperty("as_port")
    private String asPort;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAsIp() {
        return asIp;
    }

    public void setAsIp(String asIp) {
        this.asIp = asIp;
    }

    public String getAsPort() {
        return asPort;
    }

    public void setAsPort(String asPort) {
        this.asPort = asPort;
    }
}
