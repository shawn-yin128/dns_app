package com.network.lab3.controller;

import com.network.lab3.entity.DNSInfo;
import com.network.lab3.service.DNSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DNSController {
    @Autowired
    DNSService dnsService;

    @GetMapping("/dns")
    public ResponseEntity<DNSInfo> getDNS(@RequestParam(name = "type") String type,
                                          @RequestParam(name = "name") String name) {
        DNSInfo dnsInfo = dnsService.getDNS(type, name);
        if (dnsInfo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(dnsInfo);
        }
    }

}
