package com.network.lab3.service;

import com.network.lab3.cache.DNSCache;
import com.network.lab3.entity.DNSInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DNSService {

    public DNSInfo getDNS(String type, String name) {
        String hostToInfos = DNSCache.searchByTypeHost(type, name);
        if (hostToInfos == null) {
            return null;
        }

        String[] infos = hostToInfos.split(",");

        String value = infos[0];
        String ttl = infos[1];

        DNSInfo dnsInfo = new DNSInfo(type, name, value, ttl);

        return dnsInfo;
    }

}
