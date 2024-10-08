package com.network.lab3.controller;

import com.network.lab3.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciController {
    @Autowired
    private FibonacciService fibonacciService;

    @GetMapping("/fibonacci")
    public ResponseEntity<String> getFibonacci(@RequestParam(name = "number") String number) {
        try {
            int num = Integer.parseInt(number);
            if (num < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number must be non-negative.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(fibonacciService.getFibonacci(num));
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid number format.");
        }
    }

}
