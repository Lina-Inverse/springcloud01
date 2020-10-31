package com.feiyu.service;


import com.feiyu.entities.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(long id);

}
