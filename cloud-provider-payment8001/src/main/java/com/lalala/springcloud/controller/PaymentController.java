package com.lalala.springcloud.controller;


import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import com.lalala.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*******插入结果为: "+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功,serverPort："+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("*********查找结果为: "+paymentById);
        if(paymentById!=null){
            return new CommonResult(200,"查找成功,serverPort："+serverPort,paymentById);
        }else{
            return new CommonResult(444,"查找为空,查找ID为: "+id,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }


    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("***service："+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"\n"+instance.getHost()+"\n"+instance.getPort()+"\n"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipKin(){
        return "hi,i'am paymentzipkin server fall back...";
    }

}