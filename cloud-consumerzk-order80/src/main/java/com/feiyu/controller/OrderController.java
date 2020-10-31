package com.feiyu.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    public RestTemplate restTemplate;

    @GetMapping("/consumer/paymentZK")
    public String getPayment() {
        return restTemplate.getForObject(PAYMENT_URL + "/paymentZk", String.class);
    }


}
