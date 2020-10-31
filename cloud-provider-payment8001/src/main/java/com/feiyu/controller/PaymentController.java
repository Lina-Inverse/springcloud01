package com.feiyu.controller;

import com.feiyu.entities.CommonResult;
import com.feiyu.entities.Payment;
import com.feiyu.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);

        if (result > 0) {
            return new CommonResult(200, "添加成功, serverPort = " + serverPort, result);
        } else {
            return new CommonResult(444, "添加失败, serverPort = " + serverPort, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id) {
       Payment payment = paymentService.getPaymentById(id);

       int a = 15 / 5;
        System.out.println(a);

       if (payment != null) {
           return new CommonResult(200, "查询成功，id=" + id + ", severPort = " + serverPort, payment );
       } else {
           return new CommonResult(444, "查询成功，id=" + id + ", severPort = " + serverPort, null );
       }

    }

    @GetMapping("/payment/discovery")
    public Object discovery() {

       List<String> services = discoveryClient.getServices();
       for (String service : services) {
           System.out.println(service.toString());
       }

       List<ServiceInstance> instances =  discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
       for (ServiceInstance instance : instances) {
           System.out.println(instance.getHost() + "," + instance.getInstanceId() + "," +instance.getPort());
       }

       return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentPort() {
        return serverPort;
    }
}
