package com.network.lab3.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.network.lab3.entity.DNSInfo;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class UserServerService {
    public String getFibonacci(String hostname, Integer fsPort, Integer number, String asIP, Integer asPort) throws Exception {
        DNSInfo dnsInfo;

        URL urlAs = new URL("http://" + asIP + ":" + asPort + "/dns?type=A&name=" + hostname);
        HttpURLConnection connAs = (HttpURLConnection) urlAs.openConnection();
        connAs.setRequestMethod("GET");

        int responseCodeAs = connAs.getResponseCode();
        if (responseCodeAs == HttpURLConnection.HTTP_OK) {
            String response = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(connAs.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();
            ObjectMapper objectMapper = new ObjectMapper();
            dnsInfo = objectMapper.readValue(response, DNSInfo.class);
        } else {
            throw new Exception("DNS get request failed");
        }

        URL urlFs = new URL("http://" + dnsInfo.getValue() + ":" + fsPort + "/fibonacci?number=" + number);
        HttpURLConnection connFs = (HttpURLConnection) urlFs.openConnection();
        connFs.setRequestMethod("GET");

        int responseCodeFs = connFs.getResponseCode();
        if (responseCodeFs == HttpURLConnection.HTTP_OK) {
            String response = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(connFs.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();
            return response.trim();
        } else {
            throw new Exception("Fibonacci get request failed");
        }
    }
}
