package com.network.lab3.controller;

import com.network.lab3.service.UserServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServerController {
    @Autowired
    private UserServerService userServerService;

    @GetMapping("/fibonacci")
    public ResponseEntity<String> getFibonacci(@RequestParam(name = "hostname") String hostname,
                                               @RequestParam(name = "fs_port") Integer fsPort,
                                               @RequestParam(name = "number") Integer number,
                                               @RequestParam(name = "as_ip") String asIP,
                                               @RequestParam(name = "as_port") Integer asPort) throws Exception {
        // parameters check
        if (hostname == null || hostname.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hostname is required");
        } else if (fsPort == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fs_port is required");
        } else if (number == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number is required");
        } else if (asIP == null || asIP.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("as_IP is required");
        } else if (asPort == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("as_port is required");
        }
        try {
            String result = userServerService.getFibonacci(hostname, fsPort, number, asIP, asPort);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

}
