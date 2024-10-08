package com.network.lab3.service;

import com.network.lab3.cache.DNSCache;
import com.network.lab3.entity.DNSInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DNSService {

    public DNSInfo getDNS(String type, String name) {
        Map<String, List<String>> hostToInfos = DNSCache.getByType(type);
        if (hostToInfos == null) {
            return null;
        }
        List<String> infos = hostToInfos.get(name);
        String value = infos.get(0);
        String ttl = infos.get(1);

        DNSInfo dnsInfo = new DNSInfo(type, name, value, ttl);

        return dnsInfo;
    }

}
