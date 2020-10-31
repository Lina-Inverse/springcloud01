package com.feiyu.controller;

import com.feiyu.entities.CommonResult;
import com.feiyu.entities.Payment;
import com.feiyu.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class orderFeignController {

    @Resource
    private OrderService orderService;

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

        return orderService.getPaymentById(id);
    }

}
