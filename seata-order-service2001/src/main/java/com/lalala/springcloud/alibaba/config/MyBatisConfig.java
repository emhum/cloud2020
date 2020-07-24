package com.lalala.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lalala.springcloud.alibaba.dao")
public class MyBatisConfig {
}
