package com.network.lab3.entity;

public class DNSInfo {
    private String type;
    private String name;
    private String value;
    private String ttl;

    public DNSInfo(String type, String name, String value, String ttl) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.ttl = ttl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
}
