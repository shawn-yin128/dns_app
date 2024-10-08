package com.network.lab3.controller;

import com.network.lab3.entity.FSInformation;
import com.network.lab3.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PutMapping("/register")
    public ResponseEntity<String> putRegister(@RequestBody FSInformation fsInformation) throws Exception{
        String result = registerService.putRegister(fsInformation);

        if ("201".equals(result.substring(0, 3))) {
            return ResponseEntity.status(HttpStatus.CREATED).body("");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
}
