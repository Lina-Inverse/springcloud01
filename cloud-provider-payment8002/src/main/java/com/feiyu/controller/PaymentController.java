package com.feiyu.controller;

import com.feiyu.entities.CommonResult;
import com.feiyu.entities.Payment;
import com.feiyu.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

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
            return new CommonResult(444, "查询失败，id=" + id + ", severPort = " + serverPort, null );
        }

    }

    @GetMapping("/payment/lb")
    public String getPaymentPort() {
        return serverPort;
    }

}
