package com.lalala.springcloud.myhandler;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lalala.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handleException(BlockException exception) {
        return new CommonResult(4444, "按客户自定义，global handlerException---1");
    }

    public static CommonResult handleException2(BlockException exception) {
        return new CommonResult(4444, "按客户自定义，global handlerException---2");
    }
}
