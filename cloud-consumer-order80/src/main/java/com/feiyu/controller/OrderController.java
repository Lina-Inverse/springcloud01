package com.feiyu.controller;

import com.feiyu.entities.CommonResult;
import com.feiyu.entities.Payment;
import com.feiyu.lb.MyLb;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;

@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MyLb myLb;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {

        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }


    @GetMapping("/consumer/payment/lb")
    public String getPaymentPort() {

        ServiceInstance serviceInstance = myLb.getServiceInstance(discoveryClient.getInstances("cloud-payment-service"));

        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri.toString() + "payment/lb" , String.class);
    }


}
