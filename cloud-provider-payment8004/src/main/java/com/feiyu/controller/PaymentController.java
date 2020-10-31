package com.feiyu.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/paymentZk")
    public String paymentZk() {
        return "spring cloud paymentZk service port = " + serverPort + ", UUID = " + UUID.randomUUID() ;
    }


}
