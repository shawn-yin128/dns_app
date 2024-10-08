package com.network.lab3.service;

import org.springframework.stereotype.Service;

@Service
public class FibonacciService {

    public String getFibonacci(Integer number) {
        if (number == 0) {
            return "0";
        } else if (number == 1) {
            return "1";
        }
        int prev_one = 0;
        int prev_two = 1;
        int current = 0;
        for (int i = 2; i <= number; i++) {
            current = prev_one + prev_two;
            prev_one = prev_two;
            prev_two = current;
        }
        return current + "";
    }

}
