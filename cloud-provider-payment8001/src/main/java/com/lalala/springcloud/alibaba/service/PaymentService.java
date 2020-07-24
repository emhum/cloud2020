package com.lalala.springcloud.alibaba.service;

import com.lalala.springcloud.alibaba.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
