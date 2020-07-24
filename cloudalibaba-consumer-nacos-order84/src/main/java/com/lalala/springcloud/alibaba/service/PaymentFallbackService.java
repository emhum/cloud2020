package com.lalala.springcloud.alibaba.service;

import com.lalala.springcloud.alibaba.entities.CommonResult;
import com.lalala.springcloud.alibaba.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回，---PaymentFallbackService");
    }
}
