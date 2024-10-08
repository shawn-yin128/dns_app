package com.network.lab3.cache;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.network.lab3.AsApplication.DNS_FILE_NAME;

@Component
public class DNSCache {
    static Map<String, Map<String, List<String>>> DNSMap;
    
    static {
        File dnsFile = new File(DNS_FILE_NAME);
        if (!dnsFile.exists()) {
            try {
                dnsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        parse();
    }

    private static void parse() {
        synchronized (DNSCache.class) {
            try (BufferedReader br = new BufferedReader(new FileReader(DNS_FILE_NAME))) {
                DNSMap = new HashMap<>();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",");
                    String type = fields[0].trim();
                    String name = fields[1].trim();
                    String value = fields[2].trim();
                    String ttl = fields[3].trim();
                    Map<String, List<String>> hostToInfos = new HashMap<>();
                    hostToInfos.put(name, Arrays.asList(value, ttl));
                    DNSMap.put(type, hostToInfos);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void refresh() {
        parse();
    }

    public static Map<String, List<String>> getByType(String type) {
        return DNSMap.get(type);
    }
}
