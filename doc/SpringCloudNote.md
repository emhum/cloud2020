SpringCloud和SpringBoot版本的选择

# **版本选择**

SpringCloud：Hoxton.SR1
SpringBoot：2.2.2.RELEASE
SpringCloud Alibaba：2.1.0.RELEASE
Java：Java8
Maven：3.5及以上
MySql：5.7及以上

# 参考资料

SpringCloud：
	https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/
	SpringCloud中文文档：
	https://www.bookstack.cn/read/spring-cloud-docs/docs-index.md

SpringBoot：
	https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/

# 微服务架构编码构建

## IDEA新建project工作空间

新建maven工程

![image-20200630183626417](C:\MyNote\LearnJava\SpringCloud\images\新建maven工程.png)

编写GroupId和ArtifactId

![image-20200630182528922](C:\MyNote\LearnJava\SpringCloud\images\新建工程设置groupid和artifactid.png)

设置编码

![image-20200630182938481](C:\MyNote\LearnJava\SpringCloud\images\新建工程设置编码.png)

设置注解生效激活

![image-20200630183154601](C:\MyNote\LearnJava\SpringCloud\images\新建工程设置支持注解.png)

设置java编译版本选8

![image-20200630184300836](C:\MyNote\LearnJava\SpringCloud\images\新建工程设置java编译版本8.png)

设置文件过滤

![image-20200630184623850](C:\MyNote\LearnJava\SpringCloud\images\新建工程设置文件过滤.png)

父工程pom.xml文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.lalala.springcloud</groupId>
  <artifactId>cloud2020</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>

  <name>Maven</name>
  <!-- FIXME change it to the project's website -->
  <url>http://maven.apache.org/</url>
  <inceptionYear>2001</inceptionYear>

  <distributionManagement>
    <site>
      <id>website</id>
      <url>scp://webhost.company.com/www/website</url>
    </site>
  </distributionManagement>

  <!-- 统一管理jar包版本 -->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <junit.version>4.12</junit.version>
    <log4j.version>1.2.17</log4j.version>
    <lombok.version>1.16.18</lombok.version>
    <mysql.version>5.1.47</mysql.version>
    <druid.version>1.1.16</druid.version>
    <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
  </properties>

  <!-- 子模块继承之后，提供作用：锁定版本 + 子module不用写groupId和version -->
  <dependencyManagement>
    <dependencies>
      <!--spring boot 2.2.2-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.2.2.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud Hoxton.SR1-->
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>Hoxton.SR1</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--Spring cloud alibaba 2.1.0.RELEASE-->
      <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-alibaba-dependencies</artifactId>
        <version>2.1.0.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql.version}</version>
      </dependency>
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>${druid.version}</version>
      </dependency>
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>${druid.spring.boot.starter.version}</version>
      </dependency>
      <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>${mybatis-spring-boot-starter.version}</version>
      </dependency>
      <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <fork>true</fork>
          <addResources>true</addResources>
        </configuration>
      </plugin>
    </plugins>
  </build>

</project>
```



## Cloud-provider-payment8001微服务提供者Module模块

在父工程上右键新建Module

![image-20200630192437122](C:\MyNote\LearnJava\SpringCloud\images\新建module cloud-provider-payment8001.png)



![image-20200630192652539](C:\MyNote\LearnJava\SpringCloud\images\cloud-provider-payment8001.png)

pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8001</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.2.2.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.14</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.19</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
            <version>2.2.2.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <version>2.2.2.RELEASE</version>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>2.2.2.RELEASE</version>
        </dependency>
    </dependencies>
</project>
```

新建数据库表

```mysql
CREATE TABLE `payment`  (  
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',  
	`serial` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付流水号',  
	PRIMARY KEY (`id`)) 
	ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付表' ROW_FORMAT = Dynamic;
```

子模块的yml配置文件

```yml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service #服务名称

  datasource:
    type: com.alibaba.druid.pool.DruidDataSource  #当前数据源操作类型
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springcloud2020?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true
    username: root
    password: 123456

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.lalala.springcloud.entities  #所有entity别名所在包
```

主应用类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
```

实体类

```java
package com.lalala.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
```

定义一个通用返回类

```java
package com.lalala.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
```

dao层

新建PaymentDao接口

```java
package com.lalala.springcloud.dao;

import com.lalala.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
```

resource下创建mapper文件夹，新建PaymentMapper.xml

```html
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.lalala.springcloud.dao.PaymentDao">
    <resultMap id="BaseResultMap" type="com.lalala.springcloud.entities.Payment">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <id column="serial" property="serial" jdbcType="VARCHAR"/>
    </resultMap>

    <insert id="create" parameterType="payment" useGeneratedKeys="true" keyProperty="id">
            insert into payment(serial) values (#{serial})
    </insert>

    <select id="getPaymentById" parameterType="Long" resultMap="BaseResultMap">
        select * from payment where id = #{id}
    </select>
</mapper>
```

service层

新建PaymentService接口

```java
package com.lalala.springcloud.service;

import com.lalala.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
```

新建PaymentService接口的实现类PaymentServiceImpl

```java
package com.lalala.springcloud.service.impl;

import com.lalala.springcloud.dao.PaymentDao;
import com.lalala.springcloud.entities.Payment;
import com.lalala.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        int i = paymentDao.create(payment);
        return i;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment paymentById = paymentDao.getPaymentById(id);
        return paymentById;
    }
}
```

controller层

新建PaymentController

```java
package com.lalala.springcloud.controller;


import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import com.lalala.springcloud.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("*******插入结果为: "+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功",result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("*********查找结果为: "+paymentById);
        if(paymentById!=null){
            return new CommonResult(200,"查找成功",paymentById);
        }else{
            return new CommonResult(444,"查找为空,查找ID为: "+id,null);
        }

    }
}
```

启动测试

使用postman测试

![image-20200702143253245](C:\MyNote\LearnJava\SpringCloud\images\cloud-provider-payment8001的启动测试结果.png)

完整的目录结构如下

![image-20200702143509277](C:\MyNote\LearnJava\SpringCloud\images\cloud-provider-payment8001目录结构.png)



## 热部署Devtools

在cloud-provider-payment8001工程的pom文件添加

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <version>2.2.2.RELEASE</version>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

在聚合父类总工程的pom中添加

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
                <addResources>true</addResources>
            </configuration>
        </plugin>
    </plugins>
</build>
```

开启自动编译

![image-20200702150203631](C:\MyNote\LearnJava\SpringCloud\images\开启自动编译.png)

更新值

按住ctrl+shift+/打开Registry

![image-20200702151211094](C:\MyNote\LearnJava\SpringCloud\images\IDEA设置更新值.png)

## cloud-consumer-order80微服务消费者订单Module模块

新建cloud-consumer-order80模块

pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-order80</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <version>2.2.2.RELEASE</version>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>2.2.2.RELEASE</version>
        </dependency>
    </dependencies>
</project>
```

新建application.yml

```yml
server:
  port: 80
```

新建启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
```

entities包下新建Payment和CommonResult，和上一节相同

**首先说下RestTemplate：** 

​	RestTemplate提供了多种便捷访问远程Http服务的方法，是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集，实现80到8001的远程调用。

使用：
	使用restTemplate访问restful接口非常的简单粗暴，（url、requestMap、ResponseBean.class）这三个参数分别代表REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。

在config包下新建ApplicationContextConfig类，将RestTemplate对象注册到容器中，代码如下

```java
package com.lalala.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
```

订单也需要Payment、CommonResult实体类，但是不需要操作数据库，没有Service、Dao，只需添加Controller即可。在controller包下新建OrderController，代码如下

```java
package com.lalala.springcloud.controller;

import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    private static final String PAYMENT_URL="http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

    }
}
```

启动80、8001服务，测试

注意：要在8001的PaymentController中添加@RequestBody注解。

```java
@PostMapping(value = "/payment/create")
public CommonResult create(@RequestBody Payment payment){
    int result = paymentService.create(payment);
    log.info("*******插入结果为: "+result);
    if(result>0){
        return new CommonResult(200,"插入数据库成功",result);
    }else{
        return new CommonResult(444,"插入数据库失败",null);
    }
}
```



## 工程重构

上面两个模块中存在相同的代码（entities包下的Payment.class和CommonResult.class）,造成代码冗余，可以进行重构。

把相同重复的代码移到公开公用的工程里面，还可以放第三方接口、工具类，统一调配使用。

新建cloud-api-commons模块

添加依赖pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-api-commons</artifactId>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.1.0</version>
        </dependency>
    </dependencies>
</project>
```

将Payment和CommonResult复制到该模块中

![image-20200702170526890](C:\MyNote\LearnJava\SpringCloud\images\公共模块.png)

使用Maven打包发布上传到本地库里

打开Maven窗口，执行clean测试一下，无误后出现BUILD SUCCESS，然后执行install

![image-20200702171547962](C:\MyNote\LearnJava\SpringCloud\images\maven clean和install.png)

删除其他两个模块重复的entities文件夹，并分别在pom中引入依赖

```xml
<dependency>
    <groupId>com.lalala</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
</dependency>
```

# Eureka服务注册与发现

## Eureka基础知识

### 什么是服务治理

SpringCloud封装了Netflix公司开发的Eureka模块来实现服务治理

在传统的rpc远程调用框架中，管理每个服务与服务之间依赖关系比较复杂，所以需要使用服务治理，管理服务之间依赖关系，可以实现服务调用、复杂均衡、容错等，实现服务发现与注册。

### 什么是服务注册

Eureka采用了CS的设计架构，Eureka Server作为服务注册功能的服务器，它是服务注册中心。而系统中的其他微服务，使用Eureka的客户端连接到Eureka Server并维持心跳连接。这样系统的维护人员就可以通过Eureka Server来监控系统中各个微服务是否正运行。



### Eureka两组件

Eureka包含两个组件：Eureka Server和Eureka Client

**Eureka Server提供服务注册服务**

各个微服务节点通过配置启动后，会在EurekaServer中进行注册，这样EurekaServer中的服务注册表中将会存储所有可用的服务节点的信息，服务注册节点的信息可以在界面中直观看到。

**Eureka Client通过注册中心进行访问**

是一个Java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的、使用轮询负载算法的负载均衡器。在启动后，将会向Eureka Server发送心跳（默认周期30秒）。如果Eureka Server在多个心跳周期内没有接受到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除。

## 单机Eureka构建步骤

### IDEA生成EurekaServer服务端注册中心

新建cloud-eureka-server7001模块

![image-20200702180601124](C:\MyNote\LearnJava\SpringCloud\images\新建cloud-eureka-server7001.png)

pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-eureka-server7001</artifactId>

    <dependencies>
        <!--eureka server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <!--引入自己定义的api通用包，可以使用payment支付Entity-->
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般通用配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
    </dependencies>

</project>
```

application.yml

```yml
server:
  port: 7001

eureka:
  instance:
    hostname: localhost #eureka服务端的实例名称
  client:
    #false表示不向注册中心注册自己
    register-with-eureka: false
    #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

主启动类如下

注意：Eureka有两个组件，一定要标明清楚哪个是Server，哪个是Client。@EnableEurekaServer注解代表服务注册中心

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class,args);
    }
}
```

启动测试

访问http://localhost:7001/

![image-20200702182634703](C:\MyNote\LearnJava\SpringCloud\images\Eureka界面.png)

出现上面界面，表示Eureka服务端安装成功。No instances available表示当前没有服务注册进来。

### EurekaClient端cloud-provider-payment8001注册进EurekaServer成为服务提供者provider

在cloud-provider-payment8001的pom中引入依赖

```xml
<!--eureka-client-->
<dependency>
	<groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

并在cloud-provider-payment8001模块的application.yml添加Eureka相关配置

```yml
eureka:
  client:
    #表示是否将自己注册进EurekaServer默认为true
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:7001/eureka
```

在cloud-provider-payment8001模块的主启动类添加注解@EnableEurekaClient

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
```

启动测试

要先启动EurekaServer

![image-20200702184746014](C:\MyNote\LearnJava\SpringCloud\images\cloud-provider-payment8001注册进Eureka Server.png)

注册进Eureka服务器的名称就是cloud-provider-payment8001中application.yml配置的spring.application.name，红色警告是Eureka的自我保护机制，后面会详细说。

```yml
spring:
  application:
    name: cloud-payment-service #服务名称
```

### EurekaClient端cloud-consumer-order80将注册进EurekaServer成为服务消费者

采用同样方法将cloud-consumer-order80模块注册进EurekaServer

在cloud-consumer-order80模块的pom添加Eureka-client依赖

```xml
<!--eureka-client-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

在cloud-consumer-order80模块的application.yml添加如下配置

```yml
server:
  port: 80

spring:
  application:
    name: cloud-order-service

eureka:
  client:
    #表示是否将自己注册进EurekaServer默认为true
    register-with-eureka: true
    # 是否从Eureka抓取已有的注册信息，默认为true，单节点无所谓集群必须为true才能配合ribbon使用负载均衡
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:7001/eureka
```

在cloud-consumer-order80模块的主启动类添加@EnableEurekaClient

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
```

启动测试

![image-20200702191945044](C:\MyNote\LearnJava\SpringCloud\images\cloud-consumer-order80模块注册进Eureka.png)



## 集群Eureka构建步骤

### Eureka集群原理说明

![image-20200703014437378](C:\MyNote\LearnJava\SpringCloud\images\eureka集群原理说明.png)

解决方法：搭建Eureka注册中心集群，实现负载均衡+故障容错

### eurekaserver集群环境构建

![](C:\MyNote\LearnJava\SpringCloud\images\eureka集群.png)

新建cloud-eureka-server7002模块

pom.xml如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-eureka-server7002</artifactId>

    <dependencies>
        <!--eureka-server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般通用配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
    </dependencies>
</project>
```

修改hosts文件

在C:\Windows\System32\drivers\etc下的hosts文件，添加如下配置

```
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
127.0.0.1 eureka7003.com
```

修改cloud-eureka-server7001模块和cloud-eureka-server7002模块的配置文件application.yml如下

![image-20200702212606646](C:\MyNote\LearnJava\SpringCloud\images\eureka7001和eureka7002的yml文件.png)

新建cloud-eureka-server7002模块的启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class,args);
    }
}
```

启动7001和7002测试

![image-20200702213154861](C:\MyNote\LearnJava\SpringCloud\images\eureka7001.png)



![image-20200702213252617](C:\MyNote\LearnJava\SpringCloud\images\eureka7002.png)

### 将两个微服务注册进eureka集群

只需要修改配置文件application.yml

cloud-provier-payment8001的配置文件application.yml修改如下

```yml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service #服务名称

  datasource:
    type: com.alibaba.druid.pool.DruidDataSource  #当前数据源操作类型
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springcloud2020?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true
    username: root
    password: 123456

eureka:
  client:
    #表示是否将自己注册进EurekaServer默认为true
    register-with-eureka: true
    fetch-registry: true
    service-url:
      #defaultZone: http://localhost:7001/eureka #单机版
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka #集群版

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.lalala.springcloud.entities  #所有entity别名所在包
```

cloud-consumer-order80的配置文件application.yml修改如下

```yml
server:
  port: 80

spring:
  application:
    name: cloud-order-service

eureka:
  client:
    #表示是否将自己注册进EurekaServer默认为true
    register-with-eureka: true
    # 是否从Eureka抓取已有的注册信息，默认为true，单节点无所谓集群必须为true才能配合ribbon使用负载均衡
    fetch-registry: true
    service-url:
      #defaultZone: http://localhost:7001/eureka
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka #集群版
```

启动

注意：要先启动cloud-eureka-server7001和7002服务；再启动服务提供者cloud-provider-payment8001；再启动服务消费者cloud-consumer-order80。



### 支付服务提供者8001集群环境搭建

新建cloud-provider-payment8002模块

![image-20200703002121558](C:\MyNote\LearnJava\SpringCloud\images\cloud-provider-payment8002模块.png)

pom文件和cloud-provider-payment8001模块的pom文件一致

application.yml如下，注意修改端口号为8002

```yml
server:
  port: 8002

spring:
  application:
    name: cloud-payment-service #服务名称

  datasource:
    type: com.alibaba.druid.pool.DruidDataSource  #当前数据源操作类型
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springcloud2020?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true
    username: root
    password: 123456

eureka:
  client:
    #表示是否将自己注册进EurekaServer默认为true
    register-with-eureka: true
    fetch-registry: true
    service-url:
      #defaultZone: http://localhost:7001/eureka #单机版
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka #集群版

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.lalala.springcloud.entities  #所有entity别名所在包
```

主启动类、业务类和mapper文件直接从cloud-provider-payment8001模块拷贝

![image-20200703002535951](C:\MyNote\LearnJava\SpringCloud\images\cloud-provider-payment8002的主启动类.png)

修改8001和8002的controller

cloud-provider-payment8001模块的controller如下

![image-20200703003352344](C:\MyNote\LearnJava\SpringCloud\images\cloud-provider-payment8001的controller.png)

cloud-provider-payment8002模块的controller如下

![image-20200703003624903](C:\MyNote\LearnJava\SpringCloud\images\cloud-provider-payment8002的controller.png)

启动：

注意启动顺序：7001、7002、8001、8002、80

启动后发现8001和8002也都访问正常，但是我们用80访问发现怎么刷新都是8001，这是因为我们的源程序地址是写死的：

![image-20200703004216088](C:\MyNote\LearnJava\SpringCloud\images\orderController.png)

因为现在有8001、8002，所以不应该在关注具体的IP和端口，而是写服务名称。代码修改为以下内容

```java
package com.lalala.springcloud.controller;

import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

//    private static final String PAYMENT_URL="http://localhost:8001";
    
    // 通过在eureka上注册过的微服务名称调用
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

    }
}
```

当我们再次访问时会发现报如下错误

![image-20200703004706580](C:\MyNote\LearnJava\SpringCloud\images\没有添加LoadBalanced注解报错.png)

我们需要使用@LoadBalanced注解开启RestTemplate负载均衡功能。

![](C:\MyNote\LearnJava\SpringCloud\images\添加@LoadBalanced注解.png)

![image-20200703005006247](C:\MyNote\LearnJava\SpringCloud\images\添加@LoadBalanced注解.png)

启动测试，多次刷新，就会发现8001和8002端口交替出现

![image-20200703005317122](C:\MyNote\LearnJava\SpringCloud\images\添加@LoadBalanced注解后8001和8002交替出现.png)



## actuator微服务信息完善

### 主机名称：服务名称修改

如下图所示这里的服务名称不是我们想要的，我们可以修改为我们想要的服务名称

![image-20200703011111486](C:\MyNote\LearnJava\SpringCloud\images\修改服务名称.png)

修改方式：

在cloud-provider-payment8001模块和cloud-provider-payment8001模块的配置文件application.yml中分别添加如下内容

![image-20200703011621992](C:\MyNote\LearnJava\SpringCloud\images\修改8001和8002的服务名称.png)

发现8001和8002的服务名称已经修改

![image-20200703011737323](C:\MyNote\LearnJava\SpringCloud\images\发现8001和8002的微服务名称已经修改.png)

### 访问信息有IP地址

分别在8001和8002的配置文件中添加如下内容

![image-20200703012214984](C:\MyNote\LearnJava\SpringCloud\images\访问路径有ip地址显示.png)



## 服务发现Discovery

对应注册进eureka里面的微服务，可以通过服务发现来获得该服务信息。

修改cloud-provider-payment8001模块的Controller

如下所示

```java
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
}
```

在cloud-provider-payment8001的启动类上添加注解 @EnableDiscoveryClient

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
```

启动

测试结果如下

![image-20200703020510116](C:\MyNote\LearnJava\SpringCloud\images\eureka的服务发现.png)



## eureka自我保护

### 概述

保护模式主要用于一组客户端和Eureka Server之间存在网络分区场景下的保护，一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会注销任何微服务。

如果在Eureka Server的首页看到以下这段红色提示，则说明Eureka进入进入了保护模式。

![image-20200703021454340](C:\MyNote\LearnJava\SpringCloud\images\eureka进入保护模式.png)

一句话：某时刻某一个微服务不可用了。Eureka不会立刻清理，依旧会对该微服务的信息进行保存。



### 怎么禁止自我保护

#### 注册中心eurekaServer端7001

在cloud-eureka-server7001模块的配置文件application.yml中添加如下配置

![image-20200703023703025](C:\MyNote\LearnJava\SpringCloud\images\cloud-eureka-server7001关闭自我保护.png)

#### 生产者客户端eurekaClient端8001

在cloud-provider-payment8001模块的配置文件applicatioin.yml添加如下配置

![image-20200703024752731](C:\MyNote\LearnJava\SpringCloud\images\cloud-provider-payment8001模块关闭自我保护.png)



# Zookeeper服务注册与发现

## 注册中心Zookeeper

Zookeeper是一个分布式协调工具,可以实现注册中心功能

Zookeeper服务器取代Eureka服务器，作为服务注册中心



## 服务提供者

新建模块 cloud-provider-payment8004

![image-20200703114321159](C:\MyNote\LearnJava\SpringCloud\images\新建模块 cloud-provider-payment8004.png)

pom如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8004</artifactId>

    <dependencies>
        <!--springboot整合zookeeper客户端-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
        </dependency>
        <!--Springboot整合web组件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--引入自定义的api通用包，可以使用payment支付Entity-->
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

配置文件application.yml如下

```yml
#8004表示注册到zookeeper服务器的支付服务提供者端口号
server:
  port: 8004

#服务别名----注册zookeeper到注册中心名称
spring:
  application:
    name: cloud-provider-payment
  cloud:
    zookeeper:
      connect-string: 192.168.20.202:2181
```

主启动类如下

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}
```

业务类

在controller包下新建PaymentController

```java
package com.lalala.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/zk")
    public String paymentzk(){
        return "springcloud with zookeeper："+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
```

启动zookeeper服务端

> ./zkServer.sh start

自动zookeeper客户端

> ./zkCli.sh

如果有pom依赖问题，排除3.5.3版本的zookeeper，并引入3.4.6版本的zookeeper

如果zookeeper遇到slf4j和log4j包冲突的问题，此时在zookeeper去掉slf4j的依赖

```xml
<!--springboot整合zookeeper客户端-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
    <!--先排除自带的zookeeper3.5.3-->
    <exclusions>
        <exclusion>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.4.6</version>
    <exclusions>
        <exclusion>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

启动

可以看到微服务提供者支付模块注册进zookeeper中

![image-20200703150942905](C:\MyNote\LearnJava\SpringCloud\images\zookeeper包含service.png)

访问：http://localhost:8004/payment/zk

![image-20200703150752078](C:\MyNote\LearnJava\SpringCloud\images\访问8004.png)

继续查看

![image-20200703151420216](C:\MyNote\LearnJava\SpringCloud\images\继续查看service下.png)

微服务作为一个znode节点放到zookeeper服务器上，红色框中的字符串就是在zookeeper上的基本信息json格式，能看到zookeeper上8004微服务提供者的相关信息。



思考：服务节点是临时节点还是持久节点

![image-20200703153327926](C:\MyNote\LearnJava\SpringCloud\images\判断是临时节点还是持久节点.png)

从上图可以看出，当我们把服务关闭时，发现zookeeper不会立刻踢出服务，而是过一会才踢出，所以是zookeeper的服务节点是临时节点。当再次启动8004时，zookeeper还会自动监听服务状态，但是流水号是一个新的流水号，如下图所示。

![image-20200703153733956](C:\MyNote\LearnJava\SpringCloud\images\再次启动服务zookeeper中节点的变化.png)



## 服务消费者

新建 cloud-consumerzk-order80模块

![image-20200703154231157](C:\MyNote\LearnJava\SpringCloud\images\新建cloud-consumerzk-order80模块.png)

pom如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumerzk-order80</artifactId>
    <dependencies>
        <!--springboot整合zookeeper客户端-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
            <!--先排除自带的zookeeper3.5.3-->
            <exclusions>
                <exclusion>
                    <groupId>org.apache.zookeeper</groupId>
                    <artifactId>zookeeper</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.4.6</version>
            <exclusions>
                <exclusion>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-log4j12</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--Springboot整合web组件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--引入自定义的api通用包，可以使用payment支付Entity-->
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

application.yml如下

```yml
server:
  port: 80
spring:
  application:
    name: cloud-consumer-order
  cloud:
    #注册到zookeeper地址
    zookeeper:
      connect-string: 192.168.20.202:2181
```

主启动类如下

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderZKMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZKMain80.class,args);
    }
}
```

业务类

```java
package com.lalala.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
```

controller

```java
package com.lalala.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZKController {
    public static final  String INVOKE_URL="http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "consumer/payment/zk")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return result;
    }
}
```

启动测试

![image-20200703160710618](C:\MyNote\LearnJava\SpringCloud\images\cloud-consumerzk-order80注册进zookeeper.png)



![image-20200703160803957](C:\MyNote\LearnJava\SpringCloud\images\80调用8004成功.png)



# Consul服务注册与发现

## Consul简介

Consul是一套开源的分布式服务发现和配置管理系统，由HashiCorp公司用Go语言开发

提供了微服务系统中的服务治理、配置中心、控制总线等功能。这些功能中的每一个都可以根据需要单独使用，也可以一起使用以构建全方位的服务网络，总之Consul提供了一种完整的服务网格解决方案。

它具有很多优点。包括：基于 raft 协议，比较简洁；支持健康检查，同时支持 HTTP 和 DNS 协议 支持跨数据中心的 WAN 集群 提供图形界面 跨平台，支持Linux、Mac、Windows

## 安装和运行

下载地址：

进入官网：https://www.consul.io/intro/index.html

![image-20200703162205119](C:\MyNote\LearnJava\SpringCloud\images\consul下载.png)

下载后解压即可

查看consul版本：consul --version

![image-20200703162738497](C:\MyNote\LearnJava\SpringCloud\images\查看consul版本.png)

使用开发模式启动

consul agent -dev

![image-20200703163121282](C:\MyNote\LearnJava\SpringCloud\images\consul开发模式启动.png)

通过以下地址可以访问 Consul 首页
http://localhost:8500

![image-20200703163400640](C:\MyNote\LearnJava\SpringCloud\images\consul首页.png)



## 服务提供者

新建模块 cloud-providerconsul-payment8006

pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-providerconsul-payment8006</artifactId>

    <dependencies>
        <!--springcloud consul-server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--日常通用jar包-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

配置文件application.yml如下

```yml
#consull服务端口号
server:
  port: 8006

spring:
  application:
    name: consul-provider-payment
  #consul注册中心地址
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        #hostname: 127.0.0.1
        service-name: ${spring.application.name}
```

主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8006.class,args);
    }
}
```

业务类

```java
package com.lalala.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/consul")
    public String paymentConsul(){
        return "springcloud with consul："+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
```

启动测试

![image-20200703181251212](C:\MyNote\LearnJava\SpringCloud\images\cloud-providerconsul-payment8006注册consul.png)



## 服务消费者

新建 cloud-consumerconsul-order80模块

![image-20200703173249386](C:\MyNote\LearnJava\SpringCloud\images\新建cloud-consumerconsul-order80模块.png)

pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumerconsul-order80</artifactId>

    <dependencies>
        <!--springcloud consul-server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
        <!--springboot整合web组件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--日常通用jar包配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
    </dependencies>
</project>
```

application.yml

```yml
### consul服务端口号
server:
  port: 80

spring:
  application:
    name: cloud-consumer-order
  ###consul服务注册中心
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        #hostname: 127.0.0.1
        service-name: ${spring.application.name}
```

主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain80.class,args);
    }
}
```

配置Bean

```java
package com.lalala.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
```

Controller

```java
package com.lalala.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderConsulController {
    public static final String INVOKE_URL = "http://consul-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        return result;
    }
}
```

启动测试

![image-20200703181632627](C:\MyNote\LearnJava\SpringCloud\images\cloud-consumerconsul-order80注册进consul.png)

访问测试地址

不加端口号测试

![image-20200703181819751](C:\MyNote\LearnJava\SpringCloud\images\consul测试.png)

加端口号测试

![image-20200703181854859](C:\MyNote\LearnJava\SpringCloud\images\consul测试2.png)

## 三个注册中心异同点

| 组件名    | 语言 | CAP  | 服务监控检查 | 对外暴露接口 | Springcloud集成 |
| --------- | ---- | ---- | ------------ | ------------ | --------------- |
| Eureka    | Java | AP   | 可配支持     | HTTP         | 已集成          |
| Consul    | Go   | CP   | 支持         | HTTP/DNS     | 已集成          |
| Zookeeper | Java | CP   | 支持         | 客户端       | 已集成          |



基于CAP理论介绍：

C：Consistency （强一致性）
A：Available （可用性）
P：Partition tolerance （分区容错性）

最多只能同时较好的满足两个。

CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求。因此，根据CAP原理将NoSQL数据库分成了满足CA原则、满足CP原则和满足AP原则三大类。

CA - 单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强

CP - 满足一致性，分区容错性的系统，通常性能不是特别高

AP - 满足可用性，分区容错性的系统，通常可能对一致性要求低一些

Eureka 采用的是AP架构，只满足可用性和分区容错性

Eureka有自我保护机制，更强调的是AP，保证服务的高可用，微服务就是偶尔宕机掉线了，一时半会不会立刻删除。

Zookeeper 和 Consul采用的是CP架构，满足一致性和分区容错性，当网络分区出现后，为了保证一致性，就必须拒绝请求，否则无法保证一致性。

# Ribbon负载均衡服务调用

## 概述

SpringCloud Ribbon 是基于Netflix Ribbon实现的一套客户端负载均衡的工具。

Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法和服务调用。Ribbon 客户端组件提供一系列完善的配置项如连接超时，重试等。简单的说，就是在配置文件中列出 Load Balancer（简称LB）后面所有的机器，Ribbon 会自动的帮助你基于某种规则（如简单轮询、随机连接等）去连接这些机器。我们很容易使用Ribbon实现自定义的负载均衡算法。

**ps：**Ribbon目前也进入维护模式，SpringCloud想用Spring Cloud LoadBalancer 替代 Netflix 的Ribbon，但现在 Ribbon 在生产环境中大规模部署，一时半会替代不掉。

**LB负载均衡（Load Balance）是什么**

简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA（高可用）。常见的负载均衡有软件 Nginx，LVS，硬件F5 等。

- 集中式LB
  即在服务的消费方和提供方之间使用独立的LB设施（可以是硬件，如F5，也可以是软件，如nginx）,由该设施负责把访问请求通过某种策略转发至服务的提供方。
- 进程内LB
  将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。Ribbon就属于进程内LB，它只是一个类库，集成与消费方进程，消费方通过它来获取到服务提供方的地址。

**Ribbon本地负载均衡客户端 和 Nginx 服务端负载均衡的区别：**

Nginx 是服务器负载均衡，客户端所有请求都会交给 nginx ，然后由 nginx 实现转发请求。即负载均衡是由服务端实现的。

Ribbon 本地负载均衡，在调用微服务接口时候，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现 RPC 远程服务调用技术。

**一句话：**Ribbon就是负载均衡 + RestTemplate调用，最终实现RPC的远程调用。

## Ribbon负载均衡演示

Ribbon在工作时分成两步：

- 第一步先选择 EurekaServer，它优先选择在同一个区域内负载较少的server。
- 第二步再根据用户指定的策略，在从server 取到的服务注册列表中选择一个地址。

其中Ribbon提供了多种策略：比如轮询、随机和根据响应时间加权

**RestTemplate使用：**

官网：https://docs.spring.io/spring-framework/docs/5.2.2.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html

getForObject方法和getForEntity方法：

![image-20200703193455378](C:\MyNote\LearnJava\SpringCloud\images\getForObject 方法和getForEntity方法.png)

## Ribbon核心组件IRule

**IRule：根据特定算法中存服务列表中选取一个要访问的服务。**

实现类：

![image-20200704051928994](C:\MyNote\LearnJava\SpringCloud\images\irule实现类.png)



- com.netflix.loadbalancer.RoundRobinRule 轮询
- com.netflix.loadbalancer.RandomRule 随机
- com.netflix.loadbalancer.RetryRule 先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务
- WeightedResponseTimeRule 对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择
- BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
- AvailabilityFilteringRule 先过滤掉故障实例，再选择并发较小的实例
- ZoneAvoidanceRule 默认规则，复合判断server所在区域的性能和server的可用性选择服务器
  

**如何替换Ribbon负载规则**

修改cloud-consumer-order80模块

1.添加规则类

注意配置细节

- 官方文档明确给出了警告：这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，达不到特殊化定制的目的了。

所以包结构如下

![image-20200704060830922](C:\MyNote\LearnJava\SpringCloud\images\新建myrule包.png)



```java
package com.lalala.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
```

2.主启动类添加 @RibbonClient注解

```java
package com.lalala.springcloud;

import com.lalala.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
```

3.测试

刷新界面可以看到serverPort是随机出现的，负载规则就更改为随机了。

![image-20200704062818830](C:\MyNote\LearnJava\SpringCloud\images\ribbon使用随机规则.png)



## Ribbon负载均衡算法

### 轮询负载均衡算法原理

负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务器重启后rest接口数从1开始。

```java
List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE")
```

如：

```java
List[0] instances = 127.0.0.1:8002
List[1] instances = 127.0.0.1:8001
```

8001 + 8002 组合成为集群，它们共计2台机器，集群总数为2，按照轮询算法原理：

当请求数为1时：	1%2=1 对应下标位置为1，	则获得服务地址为：127.0.0.1:8001

当请求数为2时：	2%2=0 对应下标位置为0，	则获得服务地址为：127.0.0.1:8002

当请求数为3时：	3%2=1 对应下标位置为1，	则获得服务地址为：127.0.0.1:8001

当请求数为4时：	4%2=0 对应下标位置为0，	则获得服务地址为：127.0.0.1:8002

如此类推...



### 手写轮询算法

启动cloud-eureka-server7001和cloud-eureka-server7002服务

修改cloud-provider-payment8001和cloud-provider-payment8002的controller

![image-20200704141828542](C:\MyNote\LearnJava\SpringCloud\images\修改cloud-provider-payment8001的controller.png)



![image-20200704142230397](C:\MyNote\LearnJava\SpringCloud\images\修改cloud-provider-payment8002的controller.png)



cloud-consumer-order80订单微服务改造

去掉注解@LoadBalanced，不使用Ribbon自带的负载均衡

![image-20200704144733291](C:\MyNote\LearnJava\SpringCloud\images\ApplicationContextConfig类中去掉LoadBalanced注解.png)

在lb包下新建LoadBalancer接口

![image-20200704145324312](C:\MyNote\LearnJava\SpringCloud\images\在lb包下新建LoadBalancer接口.png)

LoadBalancer接口的实现类MyLB

![image-20200704145024696](C:\MyNote\LearnJava\SpringCloud\images\LoadBalancer接口的实现类MyLB.png)



```java
package com.lalala.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {
    //原子类
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            //超过最大值，为0，重新计数 2147483647 Integer.MAX_VALUE
            next = current >= 2147483647 ? 0 : current + 1;
            //自旋锁
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*****第几次访问，次数next："+next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
```

修改OrderController如下

```java
package com.lalala.springcloud.controller;

import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import com.lalala.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

//    private static final String PAYMENT_URL="http://localhost:8001";

    // 通过在eureka上注册过的微服务名称调用
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        System.out.println("uri ==== "+uri);
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
```

启动测试

多次刷新，发现8001和8002交替出现

![image-20200704153937424](C:\MyNote\LearnJava\SpringCloud\images\手写轮询算法界面结果.png)

同时，控制台打印出消息

![image-20200704154036907](C:\MyNote\LearnJava\SpringCloud\images\控制台交替打印8001和8002.png)



# OpenFeign服务接口调用

## 概述

### Feign简介

Feign是一个声明式WebService客户端。使用Feign能让编写Web Service客户端更加简单。

### Feign能干什么

Feign旨在使编写Java Http客户端变得更容易。

前面在使用Ribbon+RestTemplate时，利用RestTemplate对http请求的封装处理，形成了一套模板化的调用方法。但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。所以，Feign 在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。在Feign的实现下，我们只需创建一个接口并使用注解的方式来配置它（以前是Dao接口上面标注Mapper注解，现在是一个微服务接口上面标注衣一个Feign注解即可），即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon时，自动封装服务调用客户端的开发量。

Feign集成了Ribbon
利用Ribbon维护了Payment的服务列表信息，并且通过轮询实现了客户端的负载均衡。而与Ribbon不同的是，通过feign 只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用。

### Feign和OpenFeign的区别

Feign已经被OpenFeign取代，两者的区别如下

- **Feign**

Feign是Springcloud组件中的一个轻量级RESTful的HTTP服务客户端，Feign内置了Ribbon，用来做客户端负载均衡，去调用服务注册中心的服务。Feign的使用方式是：使用Feign的注解定义接口，调用这个接口，就可以调用服务注册中心的服务。

```xml
<dependency>         
    <groupId>org.springframework.cloud</groupId> 
    <artifactId>spring-cloud-starter-feign</artifactId> 
</dependency>
```

- **OpenFeign**

OpenFeign是springcloud在Feign的基础上支持了SpringMVC的注解，如@RequestMapping等等。OpenFeign的@FeignClient可以解析SpringMVC的@RequestMapping注解下的接口，并通过动态代理的方式产生实现类，实现类中做负载均衡并调用其他服务。

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```



## OpenFeign使用步骤

新建 cloud-consumer-feign-order80模块

![image-20200704191532571](C:\MyNote\LearnJava\SpringCloud\images\cloud-consumer-feign-order80模块.png)

pom.xml如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-feign-order80</artifactId>

    <dependencies>
        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--引入自定义的api通用包，可以使用Payment支付Entity-->
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般基础通用配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

application.yml

```yml
server:
  port: 80

eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka
```

主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class,args);
    }
}
```

业务类

采用业务逻辑接口+@FeignClient配置调用provider服务

在service包下新建PaymentFeignService接口，并新增注解@FeignClient

![image-20200704201729386](C:\MyNote\LearnJava\SpringCloud\images\PaymentFeignService接口.png)

PaymentFeignService接口的代码如下

```java
package com.lalala.springcloud.service;

import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //指定调用哪个微服务
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")    //哪个地址
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
```

controller代码如下

```java
package com.lalala.springcloud.controller;

import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import com.lalala.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }
}
```

启动测试

先启动2个eureka集群 7001/7002
再启动2个微服务 8001/8002
再启动cloud-consumer-feign-order80模块

刷新界面，发现8001和8002交替出现

![image-20200704203321824](C:\MyNote\LearnJava\SpringCloud\images\OpenFeign测试.png)

总结

![image-20200704203543260](C:\MyNote\LearnJava\SpringCloud\images\OpenFeign总结.png)



## OpenFeign超时控制

什么是OpenFeign超时控制

默认Feign客户端只等待1秒钟，但是服务端处理需要超过1秒钟，导致Feign客户端不想等待了，直接返回报错。

为了避免这样的情况，有时候我们需要设置Feign客户端的超时控制。

### 设置超时演示出错情况

服务提供方8001故意写暂停程序

![image-20200704205718239](C:\MyNote\LearnJava\SpringCloud\images\服务提供方8001故意写暂停程序.png)

服务消费方80添加超时方法paymentFeignService

![image-20200704205929020](C:\MyNote\LearnJava\SpringCloud\images\服务消费方80设置超时方法PaymentFeignService.png)

服务消费方80添加超时方法OrderFeignController

![image-20200704210150367](C:\MyNote\LearnJava\SpringCloud\images\服务消费方80添加超时方法OrderFeignController.png)

启动测试

OpenFeign默认等待1秒，超过后报错

![image-20200704210317190](C:\MyNote\LearnJava\SpringCloud\images\OpenFeign 只等待1秒超过后报错.png)

openFeign内部与ribbon整合了，支持负载均衡，它的超时控制也由最底层的ribbon进行控制，

cloud-consumer-feign-order80的application.yml文件需要开启OpenFeign客户端超时控制

```yml
#设置feign 客户端超时时间（openFeign默认支持ribbon）
ribbon:
#指的是建立连接所用的时间，适用于网络状况正常的情况下，两端连接所用的时间
  ReadTimeout: 5000
#指的是建立连接后从服务器读取到可用资源所用的时间
  ConnectTimeout: 5000
```

## OpenFeign日志打印功能

### 日志打印功能

Feign提供了日志打印功能，可以通过配置来调整日志级别，从而了解Feign中Http请求的细节。说白了就是对接口的调用情况进行监控和输出

### 日志级别

- NONE：默认的，不显示任何日志
- BASIC：仅记录请求方法、URL、响应状态码及执行时间
- HEADERS：除了 BASIC 中定义的信息之外，还有请求和响应的头信息
- FULL：除了 HEADERS 中定义的信息之外，还有请求和响应的正文及元数据

### 配置日志bean

![image-20200704211958223](C:\MyNote\LearnJava\SpringCloud\images\配置日志bean.png)

### yml配置开启日志的Feign客户端

![image-20200704213215276](C:\MyNote\LearnJava\SpringCloud\images\yml配置开启日志的Feign客户端.png)

启动测试

调用：http://localhost/consumer/payment/get/2

控制台有输出

![image-20200704213425157](C:\MyNote\LearnJava\SpringCloud\images\feign日志输出.png)



# Hystrix熔断器

## 分布式系统面临的问题

复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某个时候将不可避免的失败。

**服务雪崩：**

多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所谓的 “ 扇出 ” 。
如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A 的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的 “ 雪崩效应 ”。

对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几秒钟内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障。这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

所以，通常当你发现一个模块下的某个实例失败后，这时候这个模块依然还会接受流量，然后这个有问题的模块还调用了其他的模块，这样就会发生级联故障，或者叫 雪崩。

要避免这样的级联故障，就需要有一种链路中断的方案：服务降级、服务熔断


## Hystrix概述

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，已提高分布式系统的弹性。

“断路器” 本身是一种开关装置，当某个服务单元发送故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或抛出调用方法无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

## Hystrix重要概念

1.服务降级：

服务器忙，请稍后再试，不让客户端等待并立刻返回一个友好提示，fallback

哪些情况会发出降级？

- 程序运行异常
- 超时
- 服务熔断触发服务降级
- 线程池 / 信号量打满也会导致服务降级

2.服务熔断

类似保险丝达到最大服务访问后，直接拒绝访问，拉闸限电，然后调用服务降级的方法并返回友好提示

3.服务限流

秒杀高并发等操作，严禁一窝蜂的过来拥挤，大家排队，一秒钟N个，有序进行



## Hystrix案例

### 构建

新建模块cloud-provider-hystrix-payment8001

![image-20200706121938080](C:\MyNote\LearnJava\SpringCloud\images\新建cloud-provider-hystrix-payment8001.png)

pom.xml如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-hystrix-payment8001</artifactId>

    <dependencies>
        <!--hystrix-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--引入自定义的api通用包，可用使用Payment支付Entity-->
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

application.yml

```yml
server:
  port: 8001

spring:
  application:
    name: cloud-provider-hystrix-payment

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      #defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
      defaultZone: http://eureka7001.com:7001/eureka
```

主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }
}
```

业务类

service

```java
package com.lalala.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id："+id+"\t"+"O(∩_∩)O哈哈~";
    }

    public String paymentInfo_Timeout(Integer id){
        int timeNumber = 3;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_Timeout,id："+id+"\t"+"O(∩_∩)O哈哈~"+"   耗时(秒)："+timeNumber;
    }
}
```

controller

```java
package com.lalala.springcloud.controller;

import com.lalala.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("*****result："+result);
        return result;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("*****result："+result);
        return result;
    }
}
```

启动正常测试

启动cloud-eureka-server7001

启动cloud-provider-hystrix-payment8001

访问测试：

http://eureka7001.com:7001/

http://localhost:8001/payment/hystrix/ok/1

http://localhost:8001/payment/hystrix/timeout/1 



### 高并发测试

上述在非高并发情形下,还能勉强满足 but...

开启Jmeter,来20000个并发压死8001,20000个请求都去访问paymentInfo_TimeOut服务

http://localhost:8001/payment/hystrix/timeout/1

演示结果：两个都在转圈圈

上面还只是服务提供者8001自己测试,假如此时外部的消费者80也来访问,那消费者只能干等,最终导致消费端80不满意,服务端8001直接被拖死



假如80也加入

新建模块 cloud-consumer-feign-hystrix-order80

![image-20200706160351397](C:\MyNote\LearnJava\SpringCloud\images\cloud-consumer-feign-hystrix-order80模块.png)

pom.xml如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-feign-hystrix-order80</artifactId>

    <dependencies>
        !--hystrix-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>        			</dependency>
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>

        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

application.yml

```yml
server:
  port: 80
eureka:
  client:
    register-with-eureka: false
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
```

主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class,args);
    }
}
```

业务类

service

```java
package com.lalala.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMEN")
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
```

controller

```java
package com.lalala.springcloud.controller;

import com.lalala.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.PrivilegedExceptionAction;

@RestController
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }
}
```

启动测试：
2w个线程压8001
消费者80微服务再去访问的OK服务8001地址
http://localhost/consumer/payment/hystrix/ok/1
会发生要么转圈圈要么消费端报超时错误

解决：
对方服务(8001)超时了,调用者(80)不能一直卡死等待,必须有服务降级
对方服务(8001)down机了,调用者(80)不能一直卡死等待,必须有服务降级
对方服务(8001)ok,调用者(80)自己有故障或有自我要求(自己的等待时间小于服务提供者)

### 服务降级

**8001先从自身找问题**

- 设置自身调用超时时间的峰值,峰值内可以正常运行,超过了需要有兜底的方法处理,做服务降级fallback

**8001fallback**

cloud-provider-hystrix-payment8001模块的业务类启用 @HystrixCommand注解，内容如下

```java
package com.lalala.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id："+id+"\t"+"O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000") //设置线程超时时间3秒
    })
    public String paymentInfo_Timeout(Integer id){

        int timeNumber = 5;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_Timeout,id："+id+"\t"+"O(∩_∩)O哈哈~"+"   耗时(秒)："+timeNumber;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeoutHandler,id："+id+"\t"+"/(ㄒoㄒ)/~~";
    }
}
```

cloud-provider-hystrix-payment8001模块的主启动类激活，添加注解@EnableCircuitBreaker，内容如下

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }
}
```

启动测试

访问：http://localhost:8001/payment/hystrix/timeout/1，界面如下

![image-20200706182643347](C:\MyNote\LearnJava\SpringCloud\images\8001服务降级.png)

当我们添加计算异常时

![image-20200706182949520](C:\MyNote\LearnJava\SpringCloud\images\制造异常.png)

发现依然会服务降级，兜底方案都是paymentInfo_TimeoutHanler

**80fallback**

80订单微服务，也可以进行客户端降级保护

在cloud-consumer-feign-hystrix-order80模块的配置文件中添加如下内容

![image-20200706184535070](C:\MyNote\LearnJava\SpringCloud\images\cloud-consumer-feign-hystrix-order80.png)

cloud-consumer-feign-hystrix-order80模块的主启动类添加注解 @EnableHystrix

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class,args);
    }
}
```

cloud-consumer-feign-hystrix-order80模块的业务类controller修改如下

```java
package com.lalala.springcloud.controller;

import com.lalala.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.PrivilegedExceptionAction;

@RestController
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int age = 10/0;
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒种后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }
}
```



**目前问题**

每个业务方法对应一个兜底的方法，代码膨胀

统一和自定义分开

**解决方法**

解决代码膨胀：

1：N 除了个别重要核心业务有专属，其他普通的可以通过注解 @DefaultProperties(defaultFallback="") 统一跳转到统一结果页面。 

```java
package com.lalala.springcloud.controller;

import com.lalala.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback="payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    //@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            //@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int age = 10/0;
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒种后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    //下面是全局的fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global 异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
```

解决代码混乱耦合度高：

当客户端去调用服务端，碰上服务端宕机或关闭这种特殊情况产生 服务降级。

本次案例服务降级处理是在客户端80实现完成,与服务端8001没有关系，只需要为Feign客户端定义的接口添加一个服务降级处理的实现类即可实现解耦

修改cloud-consumer-feign-hystrix-order80模块

根据cloud-consumer-feign-hystrix-order80已经有的PaymentHystrixService接口,重新新建一个类(PaymentFallbackService)实现该接口，统一为接口里面的方法进行异常处理，如下所示。

![image-20200706205255633](C:\MyNote\LearnJava\SpringCloud\images\PaymentFallbackService类.png)

PaymentHystrixService接口添加fallback = PaymentFallbackService.class

![image-20200706205738036](C:\MyNote\LearnJava\SpringCloud\images\PaymentHystrixService接口添加fallback.png)

启动

访问http://localhost/consumer/payment/hystrix/ok/1正常

当把8001关闭时，在访问，客户端自己调用自己提示，此时服务端已经挂了，但是我们做了服务降级处理，让客户端在服务端不可用时也会获得提示信息，而不会挂起耗死服务器。

![image-20200706205938313](C:\MyNote\LearnJava\SpringCloud\images\服务降级演示关闭客户端.png)





### 服务熔断

#### 熔断机制概述

熔断机制是应对雪崩效应的一种微服务链路保护机制。当扇出链路的某个微服务出错不可用或者响应时间太长时，会进行服务的降级，进而熔断该节点微服务的调用，快速返回错误的响应信息。当检测到该节点微服务调用响应正常后，恢复调用链路。

在SpringCloud框架里，熔断机制通过Hystrix实现，Hystrix会监控微服务间调用的状况，当失败的调用到一定阈值，缺省是5秒内20次调用失败，就会启动熔断机制。熔断机制的注解是@HystrixCommand

#### 实操

修改cloud-provider-hystrix-payment8001的service

```java
package com.lalala.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "   paymentInfo_OK,id：" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") //设置线程超时时间3秒
    })
    public String paymentInfo_Timeout(Integer id) {
//        int age = 10/0;
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "   paymentInfo_Timeout,id：" + id + "\t" + "O(∩_∩)O哈哈~" + "   耗时(秒)：";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "系统繁忙或运行错误，请稍后再试，id：" + id + "\t" + "/(ㄒoㄒ)/~~";
    }

    //===服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),    //失败率达到多少后跳闸

    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " + id;
    }
}
```

修改cloud-provider-hystrix-payment8001的controller

![image-20200711150127986](C:\MyNote\LearnJava\SpringCloud\images\服务熔断的controller.png)

启动测试：

请求数10次内出现6成以上错误，就会触发熔断，熔断后正确的请求也不能立刻被响应，而是缓慢、正确率高了才响应。

![image-20200711151246909](C:\MyNote\LearnJava\SpringCloud\images\服务熔断测试1.png)

![image-20200711151344771](C:\MyNote\LearnJava\SpringCloud\images\服务熔断测试2.png)

服务的降级 --> 进而熔断 --> 恢复调用链路

总结：

熔断类型

- 熔断打开
  请求不再进行调用当前服务，内部设置时钟一般为MTTR（平均故障处理时间），当打开时长达到所设时钟则进入半熔断状态。
- 熔断关闭
  熔断关闭不会对服务进行调用。
- 熔断半开
  部分请求根据规则调用当前服务，如果请求成功且符合规则则认为当前服务恢复正常，关闭熔断。

**断路器在什么情况下开始起作用**

![image-20200711152108908](C:\MyNote\LearnJava\SpringCloud\images\断路器的参数.png)

涉及到断路器的三个重要参数：快照时间窗、请求总数阈值、错误百分比阈值

1.快照时间窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。

2.请求总数阈值：在快照时间内，必须满足请求总数阈值才有资格熔断。默认为20，意味着在10秒内，如果该hystrix命令的调用次数不足20次，即使所有的请求都超时或其他原因失败，断路器都不会打开。

3.错误百分比阈值：当请求总数在快照时间窗内超过阈值，比如发生了30次调用，如果在这30次调用中，有15次发生了超时异常，也就是超过50%的错误百分比，在默认设定50%阈值情况下，这时候就会将断路器打开。

**断路器开启或关闭的条件**

1.当满足一定的阈值的时候（默认10秒内超过20个请求次数）
2.当失败率达到一定的时候（默认10秒内超过50%的请求失败）
3.到达以上阈值，断路器将会开启
4.当开启的时候，所有请求都不会进行转发
5.一段时间后（默认是5秒），这个时候断路器是半开状态，会让其中一个请求进行转发。如果成功，断路器会关闭，若失败，继续开启。重复4和5。

**断路器打开之后**

1.再有请求调用的时候，将不会调用主逻辑，而是直接调用降级fallback。通过断路器，实现了自动地发现错误并将降级逻辑切换为主逻辑，减少响应延迟的效果。

2.原来的主逻辑要如何恢复呢？

对于这一问题，hystrix也为我们实现了自动恢复功能
当断路器打开，对主逻辑进行熔断之后，hystrix会启动一个休眠时间窗，在这个时间窗内，降级逻辑是临时的成为主逻辑，当休眠时间窗到期，熔断器将进入半开状态，释放一次请求到原来的主逻辑上，如果此次请求正常返回，那么断路器将继续闭合，主逻辑恢复，如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时。

## 服务监控Hystrix Dashboard

### 概述

除了隔离依赖服务的调用以外，Hystrix还提供了准实时的调用监控（Hystrix Dashboard），Hystrix会持续的记录所有通过Hystrix发起的请求的执行信息，并以统计报表和图形的形式展示给用户，包括每秒执行多少请求多少成功，多少失败等。Netflix 通过 hystrix-metrics-event-stream项目实现了对以上指标的监控。SpringCloud也提供了Hystrix Dashboard的整合，对监控内容转化成可视化界面。

### 搭建环境

1.新建模块cloud-consumer-hystrix-dashboard9001

2.pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-hystrix-dashboard9001</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

3.application.yml

```yml
server:
  port: 9001
```

4.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class,args);
    }
}
```

启动测试

访问地址：http://localhost:9001/hystrix

![image-20200711163207174](C:\MyNote\LearnJava\SpringCloud\images\hystrixdashboard图形化.png)

### 断路器演示

修改cloud-provider-hystrix-payment8001

注意：新版本Hystrix需要在主启动类PaymentHystrixMain8001中指定监控路径

```java
package com.lalala.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }
    //主启动类添加代码
    /**
     * 此配置是为了服务监控而配置，与服务容错本身无关,SpringCloud升级后的坑
     * ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
     * 只要在自己的项目里配置上下面的servlet就可以了
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
```

监控测试

启动1个eureka或者3个eureka集群

9001监控8001，启动监控的PaymentHystrixMain8001

![image-20200711165125760](C:\MyNote\LearnJava\SpringCloud\images\断路器演示2.png)

1.填写监控地址
http://localhost:8001/hystrix.stream，点击Monitor Stream测试

![image-20200711165025957](C:\MyNote\LearnJava\SpringCloud\images\断路器演示1.png)



访问：http://localhost:8001/payment/circuit/1 发送请求

![image-20200711170253067](C:\MyNote\LearnJava\SpringCloud\images\断路器演示3.png)



# Gateway新一代网关

## 概述简介

SpringCloud Gateway是SpringCloud的一个全新项目，基于Spring5.0+SpringBoot2.0和Project Reactor等技术开发的网关，它旨在为微服务架构提供一种简单有效的统一的API路由管理方式。

SpringCloud Gateway作为Spring Cloud生态系统中的网关，目标是替代Zuul，在Spring Cloud2.0以上版本中，没有对新版本的Zuul2.0以上最新高性能版本进行集成，仍然还是使用Zuul 1.x非Reactor模式的老版本，而为了提示网关的性能，SpringCloud Gateway是基于WebFlux框架实现的，而WebFlux框架底层则使用了高性能的Reactor模式通信框架Netty。

SpringCloud Gateway的目标提供统一的路由方式且基于Filter链的方式提供了网关基本的功能，例如：安全，监控/指标，和限流。

**微服务架构中网关在哪里**

![image-20200711180036433](C:\MyNote\LearnJava\SpringCloud\images\微服务架构中的网关.png)

SpringCloud Gateway具有如下特性

- 基于Spring Framework 5，Project Reactor和Spring Boot 2.0构建
- 动态路由：能够匹配任何请求属性
- 可以对路由指定 Predicate（断言）和Filter（过滤器）
- 集成Hystrix的断路器功能
- 集成Spring Cloud 的服务发现功能
- 易于编写的Predicate（断言）和Filter（过滤器）
- 请求限流功能
- 支持路径重写
  

SpringCloud Gateway 与 zuul 的区别？

在SpringCloud Finchley 正式版之前，SpringCloud推荐的网关是Netflix提供的Zuul：

1.Zuul 1.x是一个基于阻塞 I/O 的API Gateway
2.Zuul 1.x基于servlet 2.5使用阻塞架构它不支持任何长连接（如websocket）Zuul的设计模式和Nginx较像，每次I/O 操作都是从工作线程中选择一个执行，请求线程被阻塞到工作线程完成，但是差别是Nginx用C++实现，Zuul用Java实现，而JVM本身会有一次加载较慢的情况，使得zuul的性能相对较差
3.Zuul 2.x理念更先进，向基于Netty非阻塞和支持长连接，但SpringCloud目前还没有整合。Zuul 2.x的性能较Zuul 1.x有较大提升。在性能方面，根据官方提供的基准测试，SpringCloud Gateway的RPS（每秒请求数）是Zuul的1.6倍
4.SpringCloud Gateway建立在Spring Framework5、Project Reactor和Spring Boot 2之上，使用非阻塞API
5.SpringCloud Gateway还支持WebSocket，并且与Spring紧密集成用于更好的开发体验

## 三大核心概念

Route（路由）：路由是构建网关的基本模块，它由ID、目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由。

Predicate(断言)：参考的是Java8的java.util.function.Predicate 开发人员可以匹配HTTP请求中的所有内容(例如请求头或请求参数)，如果请求与断言相匹配则进行路由。

Filter(过滤)：指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前或者之后对请求进行修改



总体：

![image-20200711182944560](C:\MyNote\LearnJava\SpringCloud\images\Gateway1.png)

web请求，通过一些匹配条件，定位到真正的服务节点。并在这个转发过程的前后，进行一些精细化控制。
predicate就是我们的匹配条件，而filter，就可以理解为一个无所不能的拦截器。有了这两个元素再加上目标uri，就可以实现一个具体的路由了。

## Gateway工作流程

官网总结：

![image-20200711183220297](C:\MyNote\LearnJava\SpringCloud\images\Gateway2.png)

客户端向Spring Cloud Gateway发出请求。然后在Gateway Handler Mapping中找到与请求相匹配的路由，将其发送到Gateway Web Handler。

Handler再通过指定的过滤器链来将请求发送到我们实际的服务执行业务逻辑，然后返回。
过滤器之间用虚线分开是因为过滤器可能会在发送代理请求之前（“pre”）或之后（“post”）执行业务逻辑

Filter 在 “pre” 类型的过滤器可以做参数校验、权限校验、流量监控、日志输出、协议转换等；在 “post” 类型的过滤器中可以做响应内容、响应头的修改，日志的输出，流量监控等，有着非常重要的作用

核心逻辑：**路由转发 + 执行过滤器链**



## 入门配置

1.新建模块 cloud-gateway-gateway9527

2.pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-gateway-gateway9527</artifactId>

    <dependencies>
        <!--gateway-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>

        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--引入自定义的api通用包，可使用Payment支付Entity-->
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

3.application.yml

```yml
server:
  port: 9527

spring:
  application:
    name: cloud-gateway
eureka:
  instance:
    hostname: cloud-gateway-service
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
```

4.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9527.class,args);
    }
}
```

9527如何做网关映射呢？

先来看看cloud-provider-payment8001 controller的访问地址

![image-20200712133445326](C:\MyNote\LearnJava\SpringCloud\images\gateway3.png)

我们目前不想暴露8001端口，希望在8001外面套一层9527

5.yml新增网关配置

9527的yml中新增配置如下

```yml
server:
  port: 9527

spring:
  application:
    name: cloud-gateway
  cloud:
    gateway:
      routes:
        - id: payment_routh #payment_routh    #路由的ID，没有固定规则但要求唯一，简易配合服务名
          uri: http://localhost:8001         #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**          #断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_routh   #路由的ID，没有固定规则但要求唯一，简易配合服务名
          uri: http://localhost:8001          #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/lb/**             #断言，路径相匹配的进行路由
eureka:
  instance:
    hostname: cloud-gateway-service
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
```

6.启动

启动7001，8001（cloud-provider-payment8001），9527

注意：9527不需要引入spring-boot-starter-web和spring-boot-starter-actuator。

![image-20200712134905163](C:\MyNote\LearnJava\SpringCloud\images\gateway4.png)

添加网关前访问：http://localhost:8001/payment/get/1

![image-20200712135124621](C:\MyNote\LearnJava\SpringCloud\images\gateway5.png)

添加网关后访问：http://localhost:9527/payment/get/1

![image-20200712135226236](C:\MyNote\LearnJava\SpringCloud\images\gateway6.png)



![image-20200712135459398](C:\MyNote\LearnJava\SpringCloud\images\gateway7.png)



7.yml配置说明

Gateway网关路由有两种配置方式：

（1）第一种就是上述在yml中配置。

（2）代码中注入RouteLocator的Bean

业务需求：通过9527网关访问到外网的百度新闻网址

测试：http://localhost:9527/guonei

![image-20200712142108461](C:\MyNote\LearnJava\SpringCloud\images\gateway8.png)



## 通过微服务名实现动态路由

默认情况下Gateway会根据注册中心注册的服务列表，以注册中心上微服务名为路径创建动态路由进行转发，从而实现动态路由的功能。

启动一个eureka7001+两个服务提供者8001/8002

修改pom.xml，将9527注册进eureka

```xml
<!--eureka client-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

修改yml
需要注意的是uri的协议为lb，表示启用Gateway的负载均衡功能。

```yml
server:
  port: 9527

spring:
  application:
    name: cloud-gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true #开启从注册中心动态创建路由的功能，利用微服务名进行路由。
      routes:
        - id: payment_routh #payment_routh    #路由的ID，没有固定规则但要求唯一，简易配合服务名
          #uri: http://localhost:8001         #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service   #匹配后提供服务的路由地址，lb后跟提供服务的微服务的名，不要写错
          predicates:
            - Path=/payment/get/**          #断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_routh   #路由的ID，没有固定规则但要求唯一，简易配合服务名
          #uri: http://localhost:8001          #匹配后提供服务的路由地址
          uri: lb://cloud-provider-service   #匹配后提供服务的路由地址，lb后跟提供服务的微服务的名，不要写错
          predicates:
            - Path=/payment/lb/**             #断言，路径相匹配的进行路由

eureka:
  instance:
    hostname: cloud-gateway-service
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
```

测试

通过9527访问服务：http://localhost:9527/payment/lb

多次刷新，8001/8002交替出现。

![image-20200712145427886](C:\MyNote\LearnJava\SpringCloud\images\gateway9.png)



## Predicate的使用

常见的Route Predicate。

![image-20200712152436714](C:\MyNote\LearnJava\SpringCloud\images\predicate的使用1.png)

1.After Route Predicate

![image-20200712161348425](C:\MyNote\LearnJava\SpringCloud\images\predicate的使用2.png)

After是在时间之后，Before是在时间之前，Between是在之间，需要传两个时间，就不详细写了

2.Cookie Route Predicate

![image-20200712162355317](C:\MyNote\LearnJava\SpringCloud\images\predicate的使用6.png)

使用curl命令测试（curl是postman图形化界面的命令）

测试访问结果如下

只有携带cookie才能访问。

![image-20200712162311469](C:\MyNote\LearnJava\SpringCloud\images\predicate的使用3.png)

3.Header Route Predicate Factory

![image-20200712163648117](C:\MyNote\LearnJava\SpringCloud\images\Predicate的使用Header.png)

访问测试结果如下

![image-20200712163744036](C:\MyNote\LearnJava\SpringCloud\images\Predicate的使用Header测试.png)

其他的几个不再演示

说白了，Predicate就是为了实现一组匹配规则，让请求过来找到对应的Route进行处理

## Filter的使用

路由过滤器可用于修改进入的HTTP请求和返回的HTTP响应，路由过滤器只能指定路由进行使用

### Spring Cloud Gateway的Filter

生命周期，两个：pre和post

种类，两个：GatewayFilter和GlobalFilter

常用的GatewayFilter有31种，GlobalFilter有10个

### 自定义全局GlobalFilter

需要实现两个接口：GlobalFilter、Ordered

能干吗：全局日志记录、统一网关授权 ...

**案例代码**

新建MyLogGateWayFilter

```java
package com.lalala.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("======MyLogGateWayFilter:" + new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");//每次进来后判断带不带uname这个key
        if(uname == null){
            log.info("*********用户名为null ，非法用户，o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);    //uname为null非法用户
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
```

只有带uname参数才能访问成功，不带或带的参数不是uname 都不能访问

![image-20200712170256438](C:\MyNote\LearnJava\SpringCloud\images\自定义全局GlobalFilter测试.png)



# SpringCloud config分布式配置中心

## 概述

**分布式系统面临的问题——配置问题**

微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务。由于每个服务都需要有配置信息才能运行，所以一套集中式的、动态的配置管理设施是必不可少的。SpringCloud提供了ConfigServer来解决这个问题。

![image-20200712192055614](C:\MyNote\LearnJava\SpringCloud\images\springcloud config分布式配置中心.png)

**是什么？**

SpringCloud Config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。

**怎么玩？**

SpringCloud Config分为服务端和客户端两部分

服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密/解密信息等访问接口。

客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息，配置服务器默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。

**能干嘛？**

集中管理配置文件
不同环境不同配置，动态化的配置更新，分环境部署比如dev/test/prod/beta/release
运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
当配置发生变动时，服务不需要重启即可感知到配置的变化并应用新的配置
将配置信息以REST接口的形式暴露，post/curl访问刷新即可

## Config服务端配置与测试

1.github上新建一个名为springcloud-config的新Repository

2.下载到本地

git clone git@github.com:zzyybs/spring-cloud-config.git

新建三个配置文件并重新上传github

config-dev.yml

```yml
config:
  info: "master branch,springcloud-config/config-dev.yml version=7" 
```

config-prod.yml

```yml
config:
  info: "master branch,springcloud-config/config-prod.yml version=1" 
```

config-test.yml

```yml
config:
  info: "master branch,springcloud-config/config-test.yml version=1" 
```

3.新建模块cloud-config-center-3344
它作为Cloud的配置中心模块cloudConfig Center

4.pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-config-center-3344</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
    </dependencies>

</project>
```

5.配置文件application.yml

```yml
server:
  port: 3344

spring:
  application:
    name: cloud-config-center #注册进Eureka服务器的微服务名
  cloud:
    config:
      server:
        git:
          uri: git@github.com:emhum/springcloud-config.git # gitbhub上面的仓库的名字
          #搜索目录
          search-paths:
            - springcloud-config
          #读取分支
      label: master

#服务注册到eureka地址
eureaka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka
```

6.主启动类

```java
package com.lalala.springlcoud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class,args);
    }
}
```

7.先启动7001，再启动3344，测试能够从github上获取配置内容

访问：http://localhost:3344/master/config-dev.yml

![image-20200714215254316](C:\MyNote\LearnJava\SpringCloud\images\springcloudconfig1.png)

配置读取规则

/{label}/{application}-{profile}.yml

- master分支

  http://config-3344.com:3344/master/config-dev.yml

  http://config-3344.com:3344/master/config-test.yml

  http://config-3344.com:3344/master/config-prod.yml

- dev分支

  http://config-3344.com:3344/dev/config-dev.yml

  http://config-3344.com:3344/dev/config-test.yml

  http://config-3344.com:3344/dev/config-prod.yml

/{application}-{profile}.yml

- http://config-3344.com:3344/config-dev.yml
- http://config-3344.com:3344/config-test.yml
- http://config-3344.com:3344/config-prod.yml

默认读取master分支，因为我们在application.yml中配置了 label: master

/{application}/{profile}/{label}

- http://config-3344.com:3344/config/dev/master
- http://config-3344.com:3344/config/test/master
- http://config-3344.com:3344/config/test/dev



## Config客户端配置与测试

SpringCloud Config分为服务端和客户端两部分

客户端不直接访问github，而是通过服务端访问。

1.新建模块 cloud-config-client-3355

2.pom.xml

这里使用bootstrap.yml

application.yml是用户级的资源配置项

bootstrap.yml是系统级的，优先级更加高

Spring Cloud会创建一个"Bootstrap Context"，作为Spring应用的'Application Context'的父上下文。初始化的时候，"Bootstrap Context"负责从外部源加载配置属性并解析配置，这两个上下文共享一个从外部获取的'Environment'。

'Boostrap'属性有高优先级，默认情况下，它们不会被本地配置覆盖。'Bootstrap Context'和'Application Context'有着不同的约定，所以新增了一个'bootstrap.yml'文件，保证'Bootstrap Context'和'Application Context'配置的分离。

因为bootstrap.yml是比application.yml先加载的，bootstrap.yml优先级高于application.yml

```yml
server:
  port: 3355

spring:
  application:
    name: config-client
  cloud:
    #Config客户端配置
    config:
      label: master #分支名称
      name: config #配置文件名称
      profile: dev #读取后缀名称 上述3个综合：master分支上config-dev.yml的配置文件被读取 http://config-3344.com:3344/master/config-dev.yml
      uri: http://localhost:3344 #配置中心地址 表示通过这个服务端访问

#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka
```

3.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConfigClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class,args);
    }
}
```

4.业务类

新建controller包

```java
package com.lalala.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;  //要访问的3344上的信息

    @GetMapping("/configInfo")	//请求地址
    public String getConfigInfo(){
        return configInfo;
    }
}
```

5.启动

先启动3344微服务并自测，再启动3355作为Client准备访问

访问地址：http://localhost:3355/configInfo

![image-20200715142805270](C:\MyNote\LearnJava\SpringCloud\images\config客户端1.png)

成功实现了客户端3355访问Config服务端3344，通过github获取配置信息。

**分布式配置的动态刷新问题**

修改了GitHub上的配置文件内容，刷新3344，发现ConfigServer配置中心立刻响应。刷新3355，发现ConfigClient客户端没有任何响应，只有重启3355才能获得最新的配置。这样就很麻烦，接下来将解决这个问题。

## Config客户端之动态刷新

避免每次更新配置都要重启客户端微服务3355

1.在3355的配置文件中添加暴露监控端点

```yml
server:
  port: 3355

spring:
  application:
    name: config-client
  cloud:
    #Config客户端配置
    config:
      label: master #分支名称
      name: config #配置文件名称
      profile: dev #读取后缀名称 上述3个综合：master分支上config-dev.yml的配置文件被读取 http://config-3344.com:3344/master/config-dev.yml
      uri: http://localhost:3344 #配置中心地址 表示通过这个服务端访问

#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka

# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

2.在业务类controller中添加@RefreshScope注解

```java
package com.lalala.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;  //要访问的3344上的信息

    @GetMapping("/configInfo")	//请求地址
    public String getConfigInfo(){
        return configInfo;
    }
}
```

3.需要运维人员发送Post请求刷新3355

必须是post请求：curl -X POST "http://localhost:3355/actuator/refresh"

![image-20200715152411132](C:\MyNote\LearnJava\SpringCloud\images\config动态刷新.png)

想想还有什么问题？

假如有多个微服务客户端3355/3366/3377/...

每个微服务都要执行一次post请求，手动刷新？

可否广播，一次通知，处处生效。

我们想大范围的自动刷新。

下一章消息总线Bus可以实现。

# SpringCloud Bus消息总线

## 概述

SpringCloud Bus配合SpringCloud Config使用可以实现配置的动态刷新

SpringCloud Bus是用来将分布式系统的节点与轻量级消息系统链接起来的框架，它整合了Java的事件处理机制和消息中间件的功能。
SpringCloud Bus目前支持RabbitMQ和Kafka。

什么是总线？
在微服务架构的系统中，通常会使用轻量级的消息代理来构建一个共用的消息主题，并让系统中所有微服务实例都连接上来。由于该主题中产生的消息会被所有实例监听和消费，所以称它为消息总线。在总线上的各个实例，都可以方便的广播一些需要让其他连接在该主题上的实例都知道的消息。

基本原理：
ConfigClient实例都监听MQ中同一个topic（默认是SpringcloudBus），当一个服务刷新数据的时候，它会把这个信息放入到Topic中，这样其他监听同一topic的服务就能得到通知，然后去更新自身的配置。

## RabbitMQ环境配置

启动后访问地址：http://localhost:15672

输入账号密码并登录：guest，guest



## SpringCloud Bus动态刷新全局广播

演示广播效果以3355为模板再创建一个3366

1.新建模块 cloud-config-client-3366

2.pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-config-client-3366</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

3.bootstrap.yml

```yml
server:
  port: 3366

spring:
  application:
    name: config-client
  cloud:
    #Config客户端配置
    config:
      label: master #分支名称
      name: config #配置文件名称
      profile: dev #读取后缀名称 上述3个综合：master分支上config-dev.yml的配置文件被读取 http://config-3344.com:3344/master/config-dev.yml
      uri: http://localhost:3344 #配置中心地址

#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka

#暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

4.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3366.class,args);
    }
}
```

5.业务类

```java
package com.lalala.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${server.port}")
    private String serverPort;
    @Value(("${config.info}"))
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return "serverPort: "+serverPort+"\t\n\n configInfo: "+configInfo;
    }
}
```



**设计思想**

（1）利用消息总线触发一个客户端/bus/refresh，而刷新所有客户端的配置

![image-20200715215518063](C:\MyNote\LearnJava\SpringCloud\images\springcloudbus1.png)



（2）利用消息总线触发一个服务端ConfigServer的/bus/refresh端点，而刷新所有客户端的配置

![image-20200715215614826](C:\MyNote\LearnJava\SpringCloud\images\springcloudbus2.png)



第二种的架构显然更加合适，第一种不合适的原因如下

- 打破了微服务的职责单一性，因为微服务本身是业务模块，它本不应该承担配置刷新的职责
- 破坏了微服务各节点的对等性
- 有一定的局限性。例如，微服务在迁移时，它的网络地址常常会发生变化，此时如果想要做到自动刷新，那就会增加更多的修改。



**给cloud-config-center-3344配置中心服务端添加消息总线支持**

pom添加依赖如下

```xml
<!--添加消息总线RabbitMQ支持-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>
```

yml添加配置如下

```yml
  #rabbit相关配置
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
  
#rabbitmq相关配置，暴露bus刷新配置的端点
management:
  endpoints:  #暴露bus刷新配置的端点
    web:
      exposure:
        include: 'bus-refresh'  #凡是暴露监控、刷新的都要有actuator依赖，bus-refresh就是actuator的刷新操作
```

**给cloud-config-client-3355客户端添加消息总线支持**

pom添加依赖如下

```xml
<!--添加消息总线RabbitMQ支持-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>
```

yml添加配置如下

```yml
  #rabbit相关配置
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
```

**给cloud-config-client-3366客户端添加消息总线支持**

pom添加依赖

```xml
<!--添加消息总线RabbitMQ支持-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>
```

yml添加配置如下

```yml
  #rabbit相关配置 15672是web管理界面的端口，5672是MQ访问的端口
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest	#这是客户端，不需要刷新
```

**启动7001、3344、3355、3366，测试**

修改GitHub版本号

我们只需给3344服务端发送一次Post请求，3355/3366就能自动的跟着更新。测试一下，给3344发送Post：

curl -X POST "http://localhost:3344/actuator/bus-refresh"

一次修改，广播通知，处处生效

## SpringCloud Bus动态刷新定点通知

不想全部通知，只想定点通知（例如，只通知3355，不通知3366）

简单一句话：

公式：http://localhost:配置中心的端口号/actuator/bus-refresh/{destination}

/bus/refresh请求不再发送到具体的服务实例上，而是发给config server并通过destination参数类指定需要更新配置的服务或实例。

curl -X -POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"
config-client就是3355的微服务名，3355即端口号



# SpringCloud Stream消息驱动

## 概述

如果系统里同时存在多种MQ，可以使用使用Cloud Stream，只需要和Stream交互就可以进行管理。
一句话，屏蔽底层消息中间件的差异，降低切换成本，统一消息的编程模型。

什么是SpringCloudStream？

官方定义Spring Cloud Stream是一个构建消息驱动微服务的框架

应用程序通过inputs或者outputs来与Spring Cloud Stream中binder对象交互。通过我们配置来binding（绑定），而Spring Cloud Stream的binder对象负责与消息中间件交互。所以，我们只需要搞清楚如何与Spring Cloud Stream交互就可以方便使用消息驱动的方式。

通过使用Spring Integration来连接消息代理中间件以实现消息事件驱动
Spring Cloud Stream 为一些供应商的消息中间件产品提供了个性化的自动化配置实现，引用了发布-订阅、消费组、分区的三个核心概念。

目前只支持RabbitMQ、Kafka

**设计思想**

标准的MQ：
生产者/消费者之间靠消息媒介传递信息内容——Message
消息必须走特定的通道——消息通道MessageChannel
消息通道里的消息如何被消费呢，谁负责收发处理——消息通道MessageChannel的子接口SubscribableChannel，由MessageHandler消息处理器所订阅。

比方说我们用到了RabbitMQ和Kafka，由于这两个消息中间件的架构上的不同，像RabbitMQ有exchange，Kafka有Topic和Partition分区

这些消息中间件的差异性导致我们实际项目开发给我们造成了一定的困扰，我们如果用了两个消息队列中的一种，后面的业务需求，我们想往另一种消息队列进行迁移，这时候无疑就是灾难性的，一大堆东西都要重新推倒重新做，因为它跟我们的系统耦合了，这时候SpringCloud Stream给我们提供了一种解耦合的方式

如何实现：

在没有绑定器这个概念的情况下，我们的SpringBoot应用要直接与消息中间件进行信息交互的时候，由于各消息中间件构建的初衷不同，它们的实现细节上会有较大的差异性，通过定义绑定器作为中间层，完美的实现了应用程序与消息中间件细节之间的隔离。通过向应用程序暴露统一的channel通道，使得应用程序不需要再考虑各种不同的消息中间件实现。

通过定义绑定器Binder作为中间层，实现了应用程序与消息中间件细节之间的隔离。

Binder：INPUT对应于消费者，OUTPUT对应于生产者


**Spring Cloud Stream标准流程套路**

![image-20200716152234051](C:\MyNote\LearnJava\SpringCloud\images\springcloudstream标准流程套路.png)

Binder：很方便的连接中间件，屏蔽差异。
Channel：通道，是队列Queue的一种抽象，在消息通讯系统中就是实现存储和转发的媒介，通过Channel对队列进行配置。
Source和Sink：简单的可以理解为参照对象是SpringCloud Stream自身，从Stream发布消息就是输出，接收消息就是输入。

**编码API和常用注解**

![image-20200716152512939](C:\MyNote\LearnJava\SpringCloud\images\springcloudstream编码api和常用注解.png)

## 案例说明

RabbitMQ环境已经ok

工程中新建3个子模块

（1）cloud-stream-rabbitmq-provider8801，作为生产者进行发消息模块
（2）cloud-stream-rabbitmq-consumer8802，作为消息接受模块
（3）cloud-stream-rabbitmq-consumer8803，作为消息接受模块



## 消息驱动之生产者

1.新建模块cloud-stream-rabbitmq-provider8801

2.pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-stream-rabbitmq-provider8801</artifactId>

    <dependencies>
        <!--stream-rabbit-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

3.yml

```yml
server:
  port: 8801

spring:
  application:
    name: cloud-stream-provider
  cloud:
    stream:
      binders:  #在此配置要绑定的rabbitmq的服务信息
        defaultRabbit:  #表示定义的名称，用于binding的整合
          type: rabbit  #消息组件类型
          environment:  #设置rabbitmq的相关环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: #服务的整合处理
        output: #这个名字是一个通道的名称
          destination: studyExchange  #表示要使用的Exchange名称定义
          content-type: application/json  #设置消息类型，本次为json，文本则设置“text/plain”
          binder: defaultRabbit #设置要绑定的消息服务的具体设置

eureka:
  client: #客户端进行eureka注册的配置
    service-url:
      defaultZone: http://localhost:7001/eureka
  instance:
    lease-renewal-interval-in-seconds: 2  #设置心跳的时间间隔（默认是30秒）
    lease-expiration-duration-in-seconds: 5 #如果现在超过了5秒的间隔（默认是90秒）
    instance-id: send-8801.com  #在信息列表时显示主机名称
    prefer-ip-address: true     #访问的路径变为IP地址
```

4.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamMQMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8801.class,args);
    }
}
```

5.业务类

service接口

```java
package com.lalala.springcloud.service;

public interface IMessageProvider {
    public String send();
}
```

实现类

```java
package com.lalala.springcloud.service.impl;

import com.lalala.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: " +serial);
        return null;
    }
}
```

controller

```java
package com.lalala.springcloud.controller;

import com.lalala.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }
}
```

6.启动7001eureka，rabbitmq，8801，测试

访问：http://localhost:8801/sendMessage

控制台打印

![image-20200716181905982](C:\MyNote\LearnJava\SpringCloud\images\springcloudstream生产者.png)

同时RabbitMQ出现波峰

![image-20200716182041436](C:\MyNote\LearnJava\SpringCloud\images\springcloudstream2.png)



## 消息驱动之消费者

1.新建模块 cloud-stream-rabbitmq-consumer8802

2.pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-stream-rabbitmq-consumer8802</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
        </dependency>
    </dependencies>
</project>
```

3.application.yml

```yml
server:
  port: 8802

spring:
  application:
    name: cloud-stream-consumer
  cloud:
    stream:
      binders: # 在此处配置要绑定的rabbitMQ的服务信息
        defaultRabbit: # 表示定义的名称，用于binding的整合
          type: rabbit # 消息中间件类型
          environment: # 设置rabbitMQ的相关环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: # 服务的整合处理
        input: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的exchange名称定义
          content-type: application/json # 设置消息类型，本次为json，文本则设为text/plain
          binder: defaultRabbit # 设置要绑定的消息服务的具体设置
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
  instance:
    lease-renewal-interval-in-seconds: 2 # 设置心跳的间隔时间，默认30
    lease-expiration-duration-in-seconds: 5 # 超过5秒间隔，默认90
    instance-id: receive-8802.com #主机名
    prefer-ip-address: true # 显示ip
```

4.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamMQmMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQmMain8802.class,args);
    }
}
```

5.业务类

```java
package com.lalala.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者1号，------->接收到的消息： "+message.getPayload()+"\t port: "+serverPort);
    }

}
```

6.启动7001，8801，8802

访问：http://localhost:8801/sendMessage

8802的控制台输出

![image-20200717075509571](C:\MyNote\前端\Vue\images\消费者控制台输出.png)



## 分组消费与持久化

1.新建模块cloud-stream-rabbitmq-consumer8803

2.pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-stream-rabbitmq-consumer8803</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
        </dependency>
    </dependencies>
</project>
```

3.application.yml

```yml
server:
  port: 8803

spring:
  application:
    name: cloud-stream-consumer
  cloud:
    stream:
      binders: # 在此处配置要绑定的rabbitMQ的服务信息
        defaultRabbit: # 表示定义的名称，用于binding的整合
          type: rabbit # 消息中间件类型
          environment: # 设置rabbitMQ的相关环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: # 服务的整合处理
        input: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的exchange名称定义
          content-type: application/json # 设置消息类型，本次为json，文本则设为text/plain
          binder: defaultRabbit # 设置要绑定的消息服务的具体设置
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
  instance:
    lease-renewal-interval-in-seconds: 2 # 设置心跳的间隔时间，默认30
    lease-expiration-duration-in-seconds: 5 # 超过5秒间隔，默认90
    instance-id: receive-8803.com #主机名
    prefer-ip-address: true # 显示ip
```

4.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamMQmMain8803 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQmMain8803.class,args);
    }
}
```

5.业务类

```java
package com.lalala.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者2号，------->接收到的消息： "+message.getPayload()+"\t port: "+serverPort);
    }
}
```

6.启动

分别启动rabbitMQ，7001，8801，8802，8803

访问：http://localhost:8801/sendMessage

8803的控制台输出

![image-20200717082315367](C:\MyNote\前端\Vue\images\消费者8803.png)

运行后有两个问题

- 重复消费问题
- 消息持久化问题

### 消费

目前是8802和8803同时都收到消息了，存在重复消费问题。

**先看生产实际案例**

比如在如下场景中，订单系统我们做集群部署，都会从RabbitMQ中获取订单信息，那如果一个订单同时被两个服务获取到，那么就会造成数据错误，我们得避免这种情况，这时我们就可以使用Stream中的消息分组来解决。

![image-20200717081334994](C:\MyNote\前端\Vue\images\stream分组消费与持久化.png)

注意：在Stream中处于同一个group中的多个消费者是竞争关系，就能够保证消息只会被其中一个应用消费一次。
不同组是可以全面消费的（重复消费），同一组内会发送竞争关系，只有其中一个可以消费。

### 分组

原理：微服务应用放置于同一个group中，就能够保证消息只会被其中一个应用消费一次。不同的组是可以消费的，同一个组内会发生竞争关系，只有一个可以消费。

8802和8803都变成不同组，只需要修改两个的配置文件如下

![image-20200717083804440](C:\MyNote\前端\Vue\images\8802和8803分组.png)

8802和8803都变成相同组，同样修改配置文件group两个相同即可

### 持久化

如果8802去掉group分组，而8803不去掉，当8802/8803都关闭服务，8801这时候发送消息，8802再启动的时候不会重新获得未曾获得的消息并消费，而8803重启后会获得8801之前发送的消息并消费。

所以group分组属性在消息重复消费和消息持久化消费中避免消息丢失是非常重要的属性

即默认的分组不会保留未曾获得的消息，自定义的分组会保留。

# SpringCloud Sleuth

## 概述

在微服务框架中，一个由客户端发起的请求在后端系统中会经过多个不同的服务节点调用来协调产生最后的请求结果，每一个前段请求都会形成一条复杂的分布式服务调用链路，链路中的任何一环出现高延迟或错误都会引起整个请求最后的失败。

SpringCloud Sleuth提供了一套完整的服务跟踪的解决方案
在分布式系统中提供追踪解决方案并且兼容支持了zipkin

Sleuth 负责收集整理，Zipkin负责展现。




## 搭建链路监控步骤

### zipkin

下载地址：https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/

SpringCloud从F版起已不需要自己构建Zipkin Server了，只需调用jar包

运行：

![image-20200717091229948](C:\MyNote\前端\Vue\images\运行zipkin的jar包.png)

访问前台地址：http://localhost:9411/zipkin/



### 服务提供者

在cloud-provider-payment8001的pom中添加依赖

```xml
<!--包含了sleuth+zipkin-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

application.yml

![image-20200717094211984](C:\MyNote\前端\Vue\images\搭建链路监控.png)

controller

![image-20200717094318982](C:\MyNote\前端\Vue\images\搭建链路步骤2.png)

### 服务消费方

在cloud-consumer-order80的pom添加依赖

```xml
<!--包含了sleuth+zipkin-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

yml和上一节8001添加同样依赖

controller

![image-20200717102423891](C:\MyNote\前端\Vue\images\服务消费方controller.png)

依次启动7001，cloud-provider-payment8001，cloud-consumer-order80

访问地址：http://localhost/consumer/payment/zipkin

查看zipkin界面

![image-20200717102309347](C:\MyNote\前端\Vue\images\链路监控4.png)



# SpringCloud Alibaba

文档地址：https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md

## 主要功能

- **服务限流降级**：默认支持 WebServlet、WebFlux, OpenFeign、RestTemplate、Spring Cloud Gateway, Zuul, Dubbo 和 RocketMQ 限流降级功能的接入，可以在运行时通过控制台实时修改限流降级规则，还支持查看限流降级 Metrics 监控。
- **服务注册与发现**：适配 Spring Cloud 服务注册与发现标准，默认集成了 Ribbon 的支持。
- **分布式配置管理**：支持分布式系统中的外部化配置，配置更改时自动刷新。
- **消息驱动能力**：基于 Spring Cloud Stream 为微服务应用构建消息驱动能力。
- **分布式事务**：使用 @GlobalTransactional 注解， 高效并且对业务零侵入地解决分布式事务问题。。
- **阿里云对象存储**：阿里云提供的海量、安全、低成本、高可靠的云存储服务。支持在任何应用、任何时间、任何地点存储和访问任意类型的数据。
- **分布式任务调度**：提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。同时提供分布式的任务执行模型，如网格任务。网格任务支持海量子任务均匀分配到所有 Worker（schedulerx-client）上执行。
- **阿里云短信服务**：覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。



## 组件

**[Sentinel](https://github.com/alibaba/Sentinel)**：把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

**[Nacos](https://github.com/alibaba/Nacos)**：一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

**[RocketMQ](https://rocketmq.apache.org/)**：一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务。

**[Dubbo](https://github.com/apache/dubbo)**：Apache Dubbo是一款高性能 Java RPC 框架。

**[Seata](https://github.com/seata/seata)**：阿里巴巴开源产品，一个易于使用的高性能微服务分布式事务解决方案。

**[Alibaba Cloud ACM](https://www.aliyun.com/product/acm)**：一款在分布式架构环境中对应用配置进行集中管理和推送的应用配置中心产品。

**[Alibaba Cloud OSS](https://www.aliyun.com/product/oss)**: 阿里云对象存储服务（Object Storage Service，简称 OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。您可以在任何应用、任何时间、任何地点存储和访问任意类型的数据。

**[Alibaba Cloud SchedulerX](https://help.aliyun.com/document_detail/43136.html)**: 阿里中间件团队开发的一款分布式任务调度产品，提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。

**[Alibaba Cloud SMS](https://www.aliyun.com/product/sms)**: 覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。

## 如何使用

引入依赖

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>2.2.0.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```



# SpringCloud Alibaba Nacos服务注册与配置中心

## 简介

Nacos：一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

Nacos就是 注册中心 + 配置中心的组合，Nacos = Eureka + Config + Bus

能干吗：替代Eureka做服务注册中心，替代Config做服务配置中心。

官网：https://nacos.io/zh-cn/

下载地址：https://github.com/alibaba/nacos/tags 选择对应的版本下载即可

## 安装并运行

解压安装包，直接运行bing目录下的startup.cmd。

命令启动成功后直接访问http://localhost:8848/nacos

默认登录名和密码都是nacos

登录后页面如图

![image-20200717184616945](C:\MyNote\前端\Vue\images\nacos登录后页面.png)



## Nacos作为服务注册中心演示

### 基于Nacos的服务提供者

1.新建模块cloudalibaba-provider-payment9001

2.pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba-provider-payment9001</artifactId>

    <dependencies>
        <!--springcloud alibaba nacos-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
	</dependencies>
</project>
```

3.yml

```yml
server:
  port: 9001

spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8850 #配置Nacos地址

management:
  endpoints:
    web:
      exposure:
        include: '*'  #监控
```

4.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9001.class,args);
    }
}
```

5.业务类

```java
package com.lalala.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return "nacos registry,serverPort: "+ serverPort+"\t id"+id;
    }
}
```

6.启动

先启动Nacos，启动9001

可以看到9001注册进nacos

![image-20200718140836765](C:\MyNote\LearnJava\SpringCloud\images\9001注册进nacos.png)

访问：http://localhost:9001/payment/nacos/1

![image-20200718141031142](C:\MyNote\LearnJava\SpringCloud\images\9001注册进nacos访问页面.png)



为了演示nacos的负载均衡，参照9001新建9002

1.新建模块cloudalibaba-provider-payment9002

2.pom（和9001一致）

3.yml（只需要更改下端口）

```yml
server:
  port: 9002

spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8850 #配置Nacos地址

management:
  endpoints:
    web:
      exposure:
        include: '*'  #监控
```

4.主启动类（复制9001更改下名字）

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9002.class,args);
    }
}
```

5.业务类（复制9001）

```java
package com.lalala.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return "nacos registry,serverPort: "+ serverPort+"\t id"+id;
    }

}

```

启动9002

![image-20200718142550120](C:\MyNote\LearnJava\SpringCloud\images\9002同样注册进nacos.png)







### 基于Nacos的服务消费者

1.新建模块cloudalibaba-consumer-nacos-order83

2.pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba-consumer-nacos-order83</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>

</project>
```

3.yml

```yml
server:
  port: 83

spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8850

#消费者将要去访问的微服务名称（成功注册进nacos的微服务提供者），在这配置了访问的服务，业务类就不用在定义常量了
service-url:
  nacos-user-service: http://nacos-payment-provider
```

4.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderNacosMain83 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain83.class,args);
    }
}
```

5.业务类

```java
package com.lalala.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced //RestTemplate结合Ribbon做负载均衡一定要加@LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
```

controller

```java
package com.lalala.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderNacosController {
    /*
   因为在yml中配置了service-url.nacos-user-service，
   这里不需要再定义要访问微服务名常量，而是通过boot直接读出来
    */
    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id){
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);
    }
}
```

6.启动83测试

现在服务提供者有两个，服务消费者有一个

![image-20200718232431191](C:\MyNote\LearnJava\SpringCloud\images\nacos的服务消费者注册进nacos.png)

访问：http://localhost:83/consumer/payment/nacos/1

刷新界面9001和9002交替出现

![image-20200718232546392](C:\MyNote\LearnJava\SpringCloud\images\nacos负载均衡.png)

## Nacos作为服务配置中心演示

### Nacos作为配置中心-基础配置

1.新建模块 cloudalibaba-config-nacos-client3377

2.pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba-config-nacos-client3377</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>
</project>
```

3.yml

Nacos同springcloud-config一样，在项目初始化时，要保证先从配置中心进行配置拉取，拉取配置后，才能保证项目的正常启动。

SpringCloud中配置文件的加载是存在优先级顺序的，bootstrap优先级高于application。

bootstrap.yml

```yml
#bootstrap.xml
server:
  port: 3377

spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #Nacos服务注册中心地址
      config:
        server-addr: localhost:8848 #Nacos作为配置中心地址
        file-extension: yml  #指定yaml格式的配置
        
#${spring.application.name}-${spring.profile.active}.${spring.cloud.nacos.config.file.extension}
#nacos-config-client-dev.yml
```

application.yml

```yml
#application.yml
spring:
  profiles:
    active: dev #开发环境
```

4.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClientMain3377 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain3377.class,args);
    }
}
```

5.业务类

```java
package com.lalala.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope   //SpringCloud原生注解 支持Nacos的动态刷新功能
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo(){
        return configInfo;
    }
}
```

6.在Nacos中添加配置信息

在 Nacos Spring Cloud 中，`dataId` 的完整格式如下：

```plain
${prefix}-${spring.profile.active}.${file-extension}
```

- prefix默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix来配置。
- spring.profile.active即为当前环境对应的 profile，详情可以参考 [Spring Boot文档](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html#boot-features-profiles)。 注意：当 spring.profile.active 为空时，对应的连接符 - 也将不存在，dataId 的拼接格式变成 `${prefix}.${file-extension}`
- file-exetension 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置。目前只支持 `properties` 和 `yaml` 类型。

nacos界面配置如下

![image-20200719101437159](C:\MyNote\LearnJava\SpringCloud\images\nacos前台配置如下.png)



7.启动测试

注意：由于版本不同，可能报错，修改bootstrap.yml中的 file-extension: yml 或者nacos界面的配置文件后缀

访问：http://localhost:3377/config/info

![image-20200719101717074](C:\MyNote\LearnJava\SpringCloud\images\nacos作为配置中心基础配置.png)

修改下Nacos界面中的yml文件，再次调用查看配置的接口，发现配置会自动刷新



### Nacos作为配置中心-分类配置

**三种方案加载配置**

DataID方案：指定Spring.profile.active和配置文件的DataID来使不同环境下读取不同的配置

默认空间+默认分组+新建dev和test两个DataID

![image-20200719124712916](C:\MyNote\LearnJava\SpringCloud\images\nacos分类配置.png)

通过spring.profile.active属性就能进行多环境下配置文件的读取

![image-20200719124924711](C:\MyNote\LearnJava\SpringCloud\images\nacos分类配置2.png)

重新启动，并访问：http://localhost:3377/config/info

![image-20200719125150260](C:\MyNote\LearnJava\SpringCloud\images\nacos分类配置3.png)

Group方案：通过Group实现环境区分

新建两个Group

DEV_GROUP

![image-20200719134230453](C:\MyNote\LearnJava\SpringCloud\images\DEV_GROUP.png)

TEST_GROUP

![image-20200719134607851](C:\MyNote\LearnJava\SpringCloud\images\TEST_GROUP.png)

修改bootstrap.yml和application.yml

![image-20200719134937901](C:\MyNote\LearnJava\SpringCloud\images\分组配置.png)

访问测试：http://localhost:3377/config/info

![image-20200719135147026](C:\MyNote\LearnJava\SpringCloud\images\group测试.png)

Namespace方案

新建dev和test命名空间

dev

![image-20200719135415724](C:\MyNote\LearnJava\SpringCloud\images\新建dev命名空间.png)

test

![image-20200719135711196](C:\MyNote\LearnJava\SpringCloud\images\test命名空间.png)

可以发现这两个命名空间会自动加上命名空间ID（在配置文件中用该ID来指定不同的命名空间）

![image-20200719142513297](C:\MyNote\LearnJava\SpringCloud\images\两个命名空间.png)

在dev命名空间下新建三个分组

![image-20200719142254480](C:\MyNote\LearnJava\SpringCloud\images\dev命名空间下的三个分组.png)

修改bootstrap.yml和application.yml

![image-20200719142412854](C:\MyNote\LearnJava\SpringCloud\images\命名空间的配置文件.png)

重新启动访问：http://localhost:3377/config/info

![image-20200719142710333](C:\MyNote\LearnJava\SpringCloud\images\命名空间测试.png)



## Nacos集群和持久化配置

文档：https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html

### 官网架构图

![image-20200719151317530](C:\MyNote\LearnJava\SpringCloud\images\Nacos集群部署架构图.png)

对上图的翻译

![image-20200719151009563](C:\MyNote\LearnJava\SpringCloud\images\Nacos集群.png)

### 说明

默认Nacos使用嵌入式数据库（derby）实现数据的存储。所以，如果启动多个默认配置下的Nacos节点，数据存储是存在一致性问题的。
为了解决这个问题，Nacos采用了集中式存储的方式来支持集群化部署，目前只支持Mysql的存储。

Nacos支持三种部署模式

1. 单机模式：用于测试和单机试用
2. 集群模式：用于生产环境，确保高可用
3. 多集群模式：用于多数据中心场景

Nacos默认自带的是嵌入式数据库derby
derby到mysql切换配置步骤:

1. nacos-server-1.1.4\nacos\conf 目录下执行sql脚本 `nacos-mysql.sql`
2. nacos-server-1.1.4\nacos\conf 目录下找到`application.properties`，添加配置：

```properties
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=123456
```

重新启动Nacos，访问后台可以看到是个全新的没有任何记录的页面。

新增一条配置

![image-20200719162621057](C:\MyNote\LearnJava\SpringCloud\images\nacos切换为mysql数据库.png)

可以看到将数据保存到MySQL数据库中

![image-20200719162743478](C:\MyNote\LearnJava\SpringCloud\images\nacos切换mysql数据库2.png)



### Linux版Nacos+MySql生产环境配置

预计需要，1个nginx+3个nacos注册中心+1个mysql

下载Linux版的nacos

![image-20200719163703150](C:\MyNote\LearnJava\SpringCloud\images\下载linux版的nacos.png)

剩下的省略...





# SpringCloud Alibaba Sentinel实现熔断与限流

用来代替Hystrix

文档：https://github.com/alibaba/Sentinel

## Sentinel是什么

随着微服务的流行，服务和服务之间的稳定性变得越来越重要。Sentinel 以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

下载地址：https://github.com/alibaba/Sentinel/releases

![image-20200719175552383](C:\MyNote\LearnJava\SpringCloud\images\sentinel下载地址.png)

## 安装Sentinel控制台

Sentinel 分为两个部分:

- 核心库（Java 客户端）不依赖任何框架/库，能够运行于所有 Java 运行时环境，同时对 Dubbo / Spring Cloud 等框架也有较好的支持。
- 控制台（Dashboard）基于 Spring Boot 开发，打包后可以直接运行，不需要额外的 Tomcat 等应用容器。

后台和前台8080



下载完jar包后运行命令：`java -jar sentinel-dashboard-1.7.2.jar`

![image-20200719194214256](C:\MyNote\LearnJava\SpringCloud\images\运行sentineljar包.png)

访问sentinel管理界面：http://localhost:8080/

用户名和密码都是：sentinel

![image-20200719194442844](C:\MyNote\LearnJava\SpringCloud\images\sentinel的前台页面.png)



## 初始化演示工程

1.启动nacos8850

2.新建模块cloudalibaba-sentinel-service8401

3.pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba-sentinel-service8401</artifactId>

    <dependencies>
        <!--springcloud alibaba nacos-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--springcloud alibaba sentinel-datasource-nacos 后续做持久化用到-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>
        <!--springcloud alibaba sentinel-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>

    </dependencies>

</project>
```

4.yml

```yml
#yml配置
server:
  port: 8401

spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        #Nacos服务注册中心地址
        server-addr: localhost:8850
    sentinel:
      transport:
        #配置sentinel dashboard地址
        dashboard: localhost:8080
        #默认8719端口，假如被占用会自动从8719开始依次+1扫描，直至找到未被占用的端口
        port: 8719

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

5.主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MainApp8401 {
    public static void main(String[] args) {
        SpringApplication.run(MainApp8401.class,args);
    }
}
```

6.业务类

```java
package com.lalala.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "--------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "--------testB";
    }
}
```

7.启动sentinel8080

java -jar sentinel-dashboard-1.7.2.jar

8.启动微服务8401

需要执行一次访问：http://localhost:8401/testA，查看sentinel控制台才会有内容，

![image-20200719211115234](C:\MyNote\LearnJava\SpringCloud\images\sentinel控制台2.png)

可以看出sentinel正在监控微服务8401



## 流控规则

### 基本介绍

![image-20200719211855096](C:\MyNote\LearnJava\SpringCloud\images\sentinel流控规则.png)

- 资源名：唯一名称，默认请求路径
- 针对来源：Sentinel可以针对调用者进行限流，填写微服务名，默认default（不区分来源）
- 阈值类型/单机阈值：
- QPS（每秒钟的请求数量）：当调用该api的QPS达到阈值的时候，进行限流
  - 线程数：当调用该api的线程数达到阈值的时候，进行限流
- 是否集群：（不）需要集群
- 流控模式：
  - 直接：api达到限流条件时，直接限流
  - 关联：当关联的资源达到阈值时，就限流自己
  - 链路：只记录指定链路上的流量（指定资源从入口资源进来的流量，如果达到阈值，就进行限流）【api级别的针对来源】

- 流控效果：
  - 快速失败：直接失败，抛异常
  - Warm Up：根据codeFactor（冷加载因子，默认3）的值，从阈值/codeFactor，经过预热时长，才达到设置的QPS阈值。
  - 排队等待：匀速排队，让请求以匀速的速度通过，阈值类型必须设置为QPS。否则无效。



### 流控模式

**直接（默认）**

QPS：

![image-20200719213910264](C:\MyNote\LearnJava\SpringCloud\images\sentinel流控模式直接（默认）.png)

当每秒内请求超过1次就会报错

![image-20200719214047577](C:\MyNote\LearnJava\SpringCloud\images\sentinel流控规则2.png)

线程数：

当调用该api的线程数达到设定值的时候，进行限流

![image-20200719220731993](C:\MyNote\LearnJava\SpringCloud\images\sentinel流控模式.png)



**关联**

当关联的资源达到阈值时，就限流自己（当与A关联的资源B达到阈值后，就限流自己）

设置效果：

当关联资源/testB的qps阈值超过1时，就限流/testA的Rest访问地址

![image-20200720151345923](C:\MyNote\LearnJava\SpringCloud\images\流控模式关联.png)

使用postman模拟并发密集访问testB

大批量线程高并发访问B，就会导致A失效。



### 流控效果

**直接->快速失败（默认的流控效果）**

直接失败，抛出异常，Blocked by Sentinel (flow limiting)



**预热**

Warm Up方式，即预热/冷启动方式。

公式：阈值除以coldFactor（默认值为3），经过预热时长后才会达到阈值

默认coldFactor为3，即请求QPS从threshold/3开始，经预热时长逐渐升至设定的QPS阈值。

![image-20200720161019204](C:\MyNote\LearnJava\SpringCloud\images\warmup配置.png)

**排队等待**

匀速排队，让请求以均匀的速度通过，阈值类型必须设成QPS，否则无效

![image-20200720163309161](C:\MyNote\LearnJava\SpringCloud\images\流控效果排队等待.png)



## 降级规则

![image-20200720170229056](C:\MyNote\LearnJava\SpringCloud\images\降级规则.png)

我们通常用以下几种方式来衡量资源是否处于稳定的状态：

- 平均响应时间 (`DEGRADE_GRADE_RT`)：当 1s 内持续进入 N 个请求，对应时刻的平均响应时间（秒级）均超过阈值（`count`，以 ms 为单位），那么在接下的时间窗口（`DegradeRule` 中的 `timeWindow`，以 s 为单位）之内，对这个方法的调用都会自动地熔断（抛出 `DegradeException`）。注意 Sentinel 默认统计的 RT 上限是 4900 ms，**超出此阈值的都会算作 4900 ms**，若需要变更此上限可以通过启动配置项 `-Dcsp.sentinel.statistic.max.rt=xxx` 来配置。
- 异常比例 (`DEGRADE_GRADE_EXCEPTION_RATIO`)：当资源的每秒请求量 >= N（可配置），并且每秒异常总数占通过量的比值超过阈值（`DegradeRule` 中的 `count`）之后，资源进入降级状态，即在接下的时间窗口（`DegradeRule` 中的 `timeWindow`，以 s 为单位）之内，对这个方法的调用都会自动地返回。异常比率的阈值范围是 `[0.0, 1.0]`，代表 0% - 100%。
- 异常数 (`DEGRADE_GRADE_EXCEPTION_COUNT`)：当资源近 1 分钟的异常数目超过阈值之后会进行熔断。注意由于统计时间窗口是分钟级别的，若 `timeWindow` 小于 60s，则结束熔断状态后仍可能再进入熔断状态。

**平均响应时间**

新增/testD代码

![image-20200720172744514](C:\MyNote\LearnJava\SpringCloud\images\新增testD.png)

配置testD的降级规则

![image-20200720172955835](C:\MyNote\LearnJava\SpringCloud\images\设置testD的降级规则.png)



![image-20200720173239193](C:\MyNote\LearnJava\SpringCloud\images\testD的降级规则测试.png)



**异常比例**

![image-20200720212603441](C:\MyNote\LearnJava\SpringCloud\images\配置异常比例.png)

使用jmeter测试

访问：localhost:8401/testD

![image-20200720214631423](C:\MyNote\LearnJava\SpringCloud\images\测试异常比例.png)

**异常数**

新增testE

![image-20200720215412663](C:\MyNote\LearnJava\SpringCloud\images\降级规则异常数testE.png)

配置异常数

![image-20200720215621133](C:\MyNote\LearnJava\SpringCloud\images\降级规则异常数.png)

访问：http://localhost:8401/testE，第一次访问报错，因为除数不能为0，达到5次报错后，进入熔断后降级。



## 热点规则

何为热点？热点即经常访问的数据。很多时候我们希望统计某个热点数据中访问频次最高的 Top K 数据，并对其访问进行限制。比如：

- 商品 ID 为参数，统计一段时间内最常购买的商品 ID 并进行限制
- 用户 ID 为参数，针对一段时间内频繁访问的用户 ID 进行限制

热点参数限流会统计传入参数中的热点参数，并根据配置的限流阈值与模式，对包含热点参数的资源调用进行限流。热点参数限流可以看做是一种特殊的流量控制，仅对包含热点参数的资源调用生效。

代码：

![image-20200721111706601](C:\MyNote\LearnJava\SpringCloud\images\热点规则代码.png)

配置热点规则

![image-20200721111620497](C:\MyNote\LearnJava\SpringCloud\images\热点规则1.png)

方法testHotKey里面第一个参数只要QPS超过每秒1次，马上降级处理，使用我们兜底的方法

![image-20200721114457235](C:\MyNote\LearnJava\SpringCloud\images\热点规则2.png)

上述案例演示了第一个参数p1，当QPS超过1秒1次点击后马上被限流

特殊情况：我们期望p1参数当它是某个特殊值时，它的限流值和平时不一样

例如：假如当p1的值等于5时，它的阈值可以达到200

![image-20200721115507381](C:\MyNote\LearnJava\SpringCloud\images\热点规则3.png)

测试：当p1等于5的时候，阈值变为200，当p1不等于5的时候，阈值就是平常的1



其他情况

![image-20200721120154899](C:\MyNote\LearnJava\SpringCloud\images\热点规则4.png)



## 系统规则

Sentinel 系统自适应限流从整体维度对应用入口流量进行控制，结合应用的 Load、CPU 使用率、总体平均 RT、入口 QPS 和并发线程数等几个维度的监控指标，通过自适应的流控策略，让系统的入口流量和系统的负载达到一个平衡，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。

系统保护规则是从应用级别的入口流量进行控制，从单台机器的 load、CPU 使用率、平均 RT、入口 QPS 和并发线程数等几个维度监控应用指标，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。

系统保护规则是应用整体维度的，而不是资源维度的，并且**仅对入口流量生效**。入口流量指的是进入应用的流量（`EntryType.IN`），比如 Web 服务或 Dubbo 服务端接收的请求，都属于入口流量。

系统规则支持以下的模式：

- **Load 自适应**（仅对 Linux/Unix-like 机器生效）：系统的 load1 作为启发指标，进行自适应系统保护。当系统 load1 超过设定的启发值，且系统当前的并发线程数超过估算的系统容量时才会触发系统保护（BBR 阶段）。系统容量由系统的 `maxQps * minRt` 估算得出。设定参考值一般是 `CPU cores * 2.5`。
- **CPU usage**（1.5.0+ 版本）：当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0），比较灵敏。
- **平均 RT**：当单台机器上所有入口流量的平均 RT 达到阈值即触发系统保护，单位是毫秒。
- **并发线程数**：当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护。
- **入口 QPS**：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护。



![image-20200721141326216](C:\MyNote\LearnJava\SpringCloud\images\系统规则.png)

## @SentinelResource

### 按资源名称限流+后续处理

1.启动nacos

2.启动sentinel

3.修改cloudalibaba-sentinel-service8401

pom添加

```xml
<dependency>
    <groupId>com.lalala</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
</dependency>
```

新建业务类

```java
package com.lalala.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }
}
```

4.测试

![image-20200721143417592](C:\MyNote\LearnJava\SpringCloud\images\sentinelresource1.png)

额外问题：测试关闭微服务8401，sentinel控制台流控规则就消失了

### 按url地址限流+后续处理

新增代码

![image-20200721152720793](C:\MyNote\LearnJava\SpringCloud\images\按url地址限流代码.png)

配置url流控规则

![image-20200721152631266](C:\MyNote\LearnJava\SpringCloud\images\按url地址限流.png)

测试结果

![image-20200721152940763](C:\MyNote\LearnJava\SpringCloud\images\按url地址限流测试结果.png)



### 客户自定义限流处理逻辑

创建customerBlockHandler类用于自定义限流处理逻辑

```java
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
```

RateLimitController

![image-20200721162835765](C:\MyNote\LearnJava\SpringCloud\images\自定义限流处理逻辑.png)

启动后调用：http://localhost:8401/rateLimit/customerBlockHandler

控制台配置

![image-20200721163457464](C:\MyNote\LearnJava\SpringCloud\images\自定义限流处理逻辑1.png)

测试后会响应我们自定义的

进一步说明

![image-20200721164527744](C:\MyNote\LearnJava\SpringCloud\images\自定义限流处理逻辑2.png)



## 服务熔断功能

sentinel整合ribbon+openFeign+fallback

### Ribbon系列

1.启动nacos和sentinel

2.新建模块 cloudalibaba-provider-payment9003和9004

pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba-provider-payment9003</artifactId>

    <dependencies>
        <!--SpringCloud ailibaba nacos -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

yml

```yml
server:
  port: 9003

Spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8850 #配置nocas地址

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9003.class,args);
    }
}
```

业务类

```java
package com.lalala.springcloud.controller;

import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;
    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "28a8c1e3bc2742d8848569891fb42181"));
        hashMap.put(2L, new Payment(2L, "bba8c1e3bc2742d8848569891ac32182"));
        hashMap.put(3L, new Payment(3L, "6ua8c1e3bc2742d8848569891xt92183"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult(200, "from mysql,serverPort:  " + serverPort, payment);
        return result;
    }
}
```

3.新建消费者cloudalibaba-consumer-nacos-order84

pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.lalala</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba-consumer-nacos-order84</artifactId>
    <dependencies>
        <!--SpringCloud ailibaba nacos -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
        <dependency>
            <groupId>com.lalala</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

yml

```yml
server:
  port: 84
spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
    sentinel:
      transport:
    #配置sentinel dashboard地址
    dashboard: localhost:8080
    port: 8719

#消费者将要去访问的微服务名称
service-url:
  nacos-user-service: http://nacos-payment-provider
```

主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderNacosMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain84.class,args);
    }
}
```

业务类

```java
package com.lalala.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
```

**只配置fallback**

```java
package com.lalala.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") 没有配置
    @SentinelResource(value = "fallback",fallback ="handlerFallback") //fallback只负责业务异常
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id,CommonResult.class,id);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgument ,非法参数异常...");
        }else if(result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return  result;
    }
    //本例是fallback
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"异常handlerFallback，exception内容： " + e.getMessage(), payment);
   }

}
```

**只配置blockHandler**

```java
package com.lalala.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") 没有配置
    //@SentinelResource(value = "fallback",fallback ="handlerFallback") //fallback只负责业务异常
    @SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockHandler只负责sentinel控制台违规
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id,CommonResult.class,id);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgument ,非法参数异常...");
        }else if(result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return  result;
    }

//  本例是blockHandler
    public CommonResult blockHandler(@PathVariable Long id, BlockException e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(445,"blockHandler-sentinel 限流，BlockException： " + e.getMessage(), payment);
    }
}
```

sentinel控制台配置

![image-20200721210018329](C:\MyNote\LearnJava\SpringCloud\images\演示blockhandler配置.png)



**fallback和blockHandler都配置**

```java
package com.lalala.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") 没有配置
    //@SentinelResource(value = "fallback",fallback ="handlerFallback") //fallback只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockHandler只负责sentinel控制台违规
    @SentinelResource(value = "fallback",fallback ="handlerFallback",blockHandler = "blockHandler")
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id,CommonResult.class,id);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgument ,非法参数异常...");
        }else if(result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return  result;
    }
    //本例是fallback
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"异常handlerFallback，exception内容： " + e.getMessage(), payment);
    }
    //本例是blockHandler
    public CommonResult blockHandler(@PathVariable Long id, BlockException e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(445,"blockHandler-sentinel 限流，BlockException： " + e.getMessage(), payment);
    }
}
```

sentinel控制台配置

![image-20200721210711064](C:\MyNote\LearnJava\SpringCloud\images\fallback和blockHandler配置.png)



**忽略属性**

![image-20200721211328421](C:\MyNote\LearnJava\SpringCloud\images\忽略属性.png)



### Feign系列

修改cloudalibaba-consumer-nacos-order84模块（84消费者调用提供者9003）

添加pom

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

修改application.yml

```yml
#激活Sentinel对Feign的支持
feign:
  sentinel:    
    enabled: true
```

主启动类

```java
package com.lalala.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderNacosMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain84.class,args);
    }
}
```

业务类

带有@FeignClient注解的业务接口

```java
package com.lalala.springcloud.service;

import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
```

PaymentFallbackService实现类

```java
package com.lalala.springcloud.service;

import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回，---PaymentFallbackService");
    }
}
```

controller

```java
package com.lalala.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lalala.springcloud.entities.CommonResult;
import com.lalala.springcloud.entities.Payment;
import com.lalala.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.security.PrivateKey;

@RestController
@Slf4j
public class CircleBreakerController {
    //========OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }
}
```

启动9003，84

访问：http://localhost:84/consumer/paymentSQL/1

![image-20200723144449347](C:\MyNote\LearnJava\SpringCloud\images\feign系列.png)

此时故意关闭9003微服务提供者，84消费者则自动降级

![image-20200723144754071](C:\MyNote\LearnJava\SpringCloud\images\feign系列微服务84消费者自动降级.png)



## 规则持久化

是什么：一旦我们重启应用，Sentinel规则将消失，生产环境需要将配置规则进行持久化

怎么玩：将限流配置规则持久化进Nacos保存，只要刷新8401某个rest地址，sentinel控制台的流控规则就能看到，只要Nacos里面的配置不删除，针对8401上Sentinel上的流控规则持续有效

修改cloudalibaba-sentinel-service8401

pom添加

```xml
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
```

修改yml（添加nacos数据源）

![image-20200723152907093](C:\MyNote\LearnJava\SpringCloud\images\规则持久化.png)

添加nacos业务规则配置

![image-20200723153033918](C:\MyNote\LearnJava\SpringCloud\images\规则持久化2.png)

启动8401后刷新sentinel发现业务规则有了

![image-20200723153302602](C:\MyNote\LearnJava\SpringCloud\images\规则持久化3.png)

快速访问：http://localhost:8401/rateLimit/byUrl

停止8401再看sentinel

重新启动8401再看sentinel



# SpringCloud Alibaba Seata处理分布式事务

## 分布式事务问题

![image-20200723155329368](C:\MyNote\LearnJava\SpringCloud\images\分布式事务1.png)

一句话：一次业务操作需要跨多个数据源或需要跨多个系统进行远程调用，就会产生分布式事务问题

## Seata简介

是什么？

Seata是一款开源的分布式事务解决方案，致力于在微服务架构下提供高性能和简单易用的分布式事务服务

官网地址：http://seata.io/zh-cn/

**分布式事务处理过程的-ID+三组件模型**

Transaction ID XID：全局唯一的事务ID

三组件概念：

- TC (Transaction Coordinator) - 事务协调者

维护全局和分支事务的状态，驱动全局事务提交或回滚。

- TM (Transaction Manager) - 事务管理器

定义全局事务的范围：开始全局事务、提交或回滚全局事务。

- RM (Resource Manager) - 资源管理器

管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚。

**处理过程**

![image-20200723161844015](C:\MyNote\LearnJava\SpringCloud\images\seata2.png)

## Seata-Server安装

下载地址：https://github.com/seata/seata/releases

1.下载完成后解压到指定目录，修改conf目录下的file.conf配置文件，主要修改：自定义事务组名称+事务日志存储模式为db+数据库连接信息

service模块：
vgroup_mapping.my_test_tx_group = "fsp_tx_group"

store模块：
mode = "db" 
url = "jdbc:mysql://127.0.0.1:3306/seata" 
user = "root" password = "你自己的密码"

2.新建数据库seata

3.在数据库seata中新建表，建表语句在/seata-server-0.9.0/seata/conf/db_store.sql

4.修改/seata-server-0.9.0/seata/conf目录下的registry.conf配置文件

type = "nacos"

serverAddr = "localhost:8850"

目的：指明注册中心是nacos，及修改nacos连接信息

5.先启动nacos





6.在启动seata-server：seata-server.bat



## 订单/库存/账户业务微服务准备

以下演示都需要先启动nacos后启动seata

### 分布式事务业务说明

业务说明：这里我们会创建三个服务，一个顶单服务，一个库存服务，一个账户服务。

当用户下单时，会在订单服务中创建一个订单，然后通过远程调用库存服务来扣减下单商品的库存，在通过远程调用账户服务来扣减用户账户里面的余额，最后在订单服务中修改订单状态为已完成。

该操作跨越三个数据库，有两次远程调用，很明显会有分布式事务问题。

### 创建业务数据库

seata_order：存储订单的数据库

seata_storage：存储库存的数据库

seata_account：存储账户信息的数据库

```sql
CREATE DATABASE seata_order;
CREATE DATABASE seata_storage;
CREATE DATABASE seata_account;
```

### 按照上述3库分别建对应业务表

seata_order库下建t_order表

```sql
CREATE TABLE t_order(    
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,   
    `user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',    
    `product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',    
    `count` INT(11) DEFAULT NULL COMMENT '数量',    
    `money` DECIMAL(11,0) DEFAULT NULL COMMENT '金额',    
    `status` INT(1) DEFAULT NULL COMMENT '订单状态：0：创建中; 1：已完结'
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8; 
SELECT * FROM t_order;
```

seata_storage库下建t_storage表

```sql
CREATE TABLE t_storage(    
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,    
    `product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',   
    `total` INT(11) DEFAULT NULL COMMENT '总库存',    
    `used` INT(11) DEFAULT NULL COMMENT '已用库存',    
    `residue` INT(11) DEFAULT NULL COMMENT '剩余库存'
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8; 

INSERT INTO seata_storage.t_storage(`id`,`product_id`,`total`,`used`,`residue`)
VALUES('1','1','100','0','100');  
SELECT * FROM t_storage;
```

seata_account库下建t_account表

```sql
CREATE TABLE t_account(    
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',    
    `user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',    
    `total` DECIMAL(10,0) DEFAULT NULL COMMENT '总额度',    
    `used` DECIMAL(10,0) DEFAULT NULL COMMENT '已用余额',    
    `residue` DECIMAL(10,0) DEFAULT '0' COMMENT '剩余可用额度'
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8; 

INSERT INTO seata_account.t_account(`id`,`user_id`,`total`,`used`,`residue`) VALUES('1','1','1000','0','1000');   
SELECT * FROM t_account;
```

### 按照上述3库分别建对应的回滚日志表

订单，库存，账户3个库下都需要建各自的回滚日志表

建表sql语句在seata-server-0.9.0/seata/conf/db_undo_log.sql

最终效果如下：

![image-20200723181036000](C:\MyNote\LearnJava\SpringCloud\images\分布式事务建表.png)

## 订单/库存/账户业务微服务准备

业务需求：下订单->减库存->扣余额->改（订单）状态

### 新建订单Order-Module

1.新建seata-order-service2001

2.pom

```xml

```

3.yml

```yml
server:
  port: 2001

spring:
  application:
    name: seata-order-service
  cloud:
    alibaba:
      seata:
        # 自定义事务组名称需要与seata-server中的对应
        tx-service-group: fsp_tx_group
    nacos:
      discovery:
        server-addr: 127.0.0.1:8850
  datasource:
    # 当前数据源操作类型
    type: com.alibaba.druid.pool.DruidDataSource
    # mysql驱动类
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/seata_order?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: 123456
feign:
  hystrix:
    enabled: false
logging:
  level:
    io:
      seata: info

mybatis:
  mapper-locations: classpath*:mapper/*.xml
```

4.file.conf

```
transport {
  # tcp udt unix-domain-socket
  type = "TCP"
  #NIO NATIVE
  server = "NIO"
  #enable heartbeat
  heartbeat = true
  #thread factory for netty
  thread-factory {
    boss-thread-prefix = "NettyBoss"
    worker-thread-prefix = "NettyServerNIOWorker"
    server-executor-thread-prefix = "NettyServerBizHandler"
    share-boss-worker = false
    client-selector-thread-prefix = "NettyClientSelector"
    client-selector-thread-size = 1
    client-worker-thread-prefix = "NettyClientWorkerThread"
    # netty boss thread size,will not be used for UDT
    boss-thread-size = 1
    #auto default pin or 8
    worker-thread-size = 8
  }
  shutdown {
    # when destroy server, wait seconds
    wait = 3
  }
  serialization = "seata"
  compressor = "none"
}
service {
  #vgroup->rgroup
  # 事务组名称
  vgroup_mapping.fsp_tx_group = "default"
  #only support single node
  default.grouplist = "127.0.0.1:8091"
  #degrade current not support
  enableDegrade = false
  #disable
  disable = false
  #unit ms,s,m,h,d represents milliseconds, seconds, minutes, hours, days, default permanent
  max.commit.retry.timeout = "-1"
  max.rollback.retry.timeout = "-1"
}

client {
  async.commit.buffer.limit = 10000
  lock {
    retry.internal = 10
    retry.times = 30
  }
  report.retry.count = 5
  tm.commit.retry.count = 1
  tm.rollback.retry.count = 1
}

## transaction log store
store {
  ## store mode: file、db
  #mode = "file"
  mode = "db"

  ## file store
  file {
    dir = "sessionStore"

    # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
    max-branch-session-size = 16384
    # globe session size , if exceeded throws exceptions
    max-global-session-size = 512
    # file buffer size , if exceeded allocate new buffer
    file-write-buffer-cache-size = 16384
    # when recover batch read size
    session.reload.read_size = 100
    # async, sync
    flush-disk-mode = async
  }

  ## database store
  db {
    ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
    datasource = "dbcp"
    ## mysql/oracle/h2/oceanbase etc.
    db-type = "mysql"
    driver-class-name = "com.mysql.jdbc.Driver"
    url = "jdbc:mysql://127.0.0.1:3306/seata"
    user = "root"
    password = "123456"
    min-conn = 1
    max-conn = 3
    global.table = "global_table"
    branch.table = "branch_table"
    lock-table = "lock_table"
    query-limit = 100
  }
}
lock {
  ## the lock store mode: local、remote
  mode = "remote"

  local {
    ## store locks in user's database
  }

  remote {
    ## store locks in the seata's server
  }
}
recovery {
  #schedule committing retry period in milliseconds
  committing-retry-period = 1000
  #schedule asyn committing retry period in milliseconds
  asyn-committing-retry-period = 1000
  #schedule rollbacking retry period in milliseconds
  rollbacking-retry-period = 1000
  #schedule timeout retry period in milliseconds
  timeout-retry-period = 1000
}

transaction {
  undo.data.validation = true
  undo.log.serialization = "jackson"
  undo.log.save.days = 7
  #schedule delete expired undo_log in milliseconds
  undo.log.delete.period = 86400000
  undo.log.table = "undo_log"
}

## metrics settings
metrics {
  enabled = false
  registry-type = "compact"
  # multi exporters use comma divided
  exporter-list = "prometheus"
  exporter-prometheus-port = 9898
}

support {
  ## spring
  spring {
    # auto proxy the DataSource bean
    datasource.autoproxy = false
  }
}
```

5.registry.conf

```
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "nacos"

  nacos {
    #serverAddr = "localhost"
    serverAddr = "localhost:8850"
    namespace = ""
    cluster = "default"
  }
  eureka {
    serviceUrl = "http://localhost:8761/eureka"
    application = "default"
    weight = "1"
  }
  redis {
    serverAddr = "localhost:6379"
    db = "0"
  }
  zk {
    cluster = "default"
    serverAddr = "127.0.0.1:2181"
    session.timeout = 6000
    connect.timeout = 2000
  }
  consul {
    cluster = "default"
    serverAddr = "127.0.0.1:8500"
  }
  etcd3 {
    cluster = "default"
    serverAddr = "http://localhost:2379"
  }
  sofa {
    serverAddr = "127.0.0.1:9603"
    application = "default"
    region = "DEFAULT_ZONE"
    datacenter = "DefaultDataCenter"
    cluster = "default"
    group = "SEATA_GROUP"
    addressWaitTime = "3000"
  }
  file {
    name = "file.conf"
  }
}

config {
  # file、nacos 、apollo、zk、consul、etcd3
  type = "file"

  nacos {
    serverAddr = "localhost"
    namespace = ""
  }
  consul {
    serverAddr = "127.0.0.1:8500"
  }
  apollo {
    app.id = "seata-server"
    apollo.meta = "http://192.168.1.204:8801"
  }
  zk {
    serverAddr = "127.0.0.1:2181"
    session.timeout = 6000
    connect.timeout = 2000
  }
  etcd3 {
    serverAddr = "http://localhost:2379"
  }
  file {
    name = "file.conf"
  }
}
```

6.domain

```java
package com.lalala.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
```



```java
package com.lalala.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    /**
     * 订单状态 0:创建中,1:已完结
     */
    private Integer status;
}
```

7.dao接口及实现

```java
package com.lalala.springcloud.alibaba.dao;

import com.lalala.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //1.新建订单
    void create(Order order);

    //2.修改订单状态，从零改为1
    void update(@Param("userId") Long userId,@Param("status") Integer status);
}
```

resources文件夹下新建mapper文件夹后添加

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lalala.springcloud.alibaba.dao.OrderDao">

    <resultMap id="BaseResultMap" type="com.lalala.springcloud.alibaba.domain.Order">
        <id column="id" property="id" jdbcType="BIGINT"></id>
        <result column="user_id" property="userId" jdbcType="BIGINT"></result>
        <result column="product_id" property="productId" jdbcType="BIGINT"></result>
        <result column="count" property="count" jdbcType="INTEGER"></result>
        <result column="money" property="money" jdbcType="DECIMAL"></result>
        <result column="status" property="status" jdbcType="INTEGER"></result>
    </resultMap>

    <insert id="create" parameterType="com.lalala.springcloud.alibaba.domain.Order" useGeneratedKeys="true"
            keyProperty="id">
        insert into t_order(null,user_id,product_id,count,money,status) values (#{userId},#{productId},#{count},#{money},0);
    </insert>

    <update id="update">
        update t_order set status =1 where user_id =#{userId} and status=#{status};
    </update>
</mapper>
```

8.service接口及实现

OrderService

```java
package com.lalala.springcloud.alibaba.service;

import com.lalala.springcloud.alibaba.domain.Order;

public interface OrderService {
    void create(Order order);
}
```

OrderServiceImpl

```java
package com.lalala.springcloud.alibaba.service.impl;

import com.lalala.springcloud.alibaba.dao.OrderDao;
import com.lalala.springcloud.alibaba.domain.Order;
import com.lalala.springcloud.alibaba.service.AccountService;
import com.lalala.springcloud.alibaba.service.OrderService;
import com.lalala.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    StorageService storageService;
    @Override
    public void create(Order order) {
        log.info("--->开始新建订单");
        //1.新建订单
        orderDao.create(order);

        log.info("--->订单微服务开始调用库存，做扣减count");
        //2.扣减库存
        storageService.decrease(order.getProductId(),order.getCount());

        log.info("---订单微服务开始调用账户，做扣减money");
        //3.扣减账户
        accountService.decrease(order.getUserId(),order.getMoney());

        //4.修改订单的状态，从0到1，1代表已完成
        log.info("--->修改订单状态开始");
        orderDao.update(order.getUserId(),0);

        log.info("--->下订单结束了");
    }
}
```

StorageService

```java
package com.lalala.springcloud.alibaba.service;

import com.lalala.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageService {
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
```

AccountService

```java
package com.lalala.springcloud.alibaba.service;


import com.lalala.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
```

9.controller

```java
package com.lalala.springcloud.alibaba.controller;

import com.lalala.springcloud.alibaba.domain.CommonResult;
import com.lalala.springcloud.alibaba.domain.Order;
import com.lalala.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
```

10.config

MyBatisConfig

```java
package com.lalala.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lalala.springcloud.alibaba.dao")
public class MyBatisConfig {
}
```

DataSourceProxyConfig

```java
package com.lalala.springcloud.alibaba.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;


@Configuration
public class DataSourceProxyConfig {
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceProxy);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources(mapperLocations));

        SqlSessionFactory factory;
        try {
            factory = bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return factory;
    }
}
```

11.主启动类

```java
package com.lalala.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //取消数据源自动创建
@EnableFeignClients
@EnableDiscoveryClient
public class SeataOrderMainApp2001 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMainApp2001.class,args);
    }
}
```

12.启动

记得启动nacos和seata-server



### 新建库存Storage-Module











### 新建账户Account-Module











# 大厂面试之雪花算法（上）

## 集群高并发情况下如何保证分布式全局唯一id生成

**为什么需要分布式全局唯一ID和以及分布式ID的需求**

在复杂分布式系统中，往往需要对大量的数据和消息进行唯一标识，如在美团点评的金融、支付、餐饮、酒店等产品中的数据日渐增长，对数据分库分表后需要有一个唯一ID来标识一条数据或消息；此时一个能够生成全局唯一ID的系统是非常重要的。

**ID生成规则部分硬性要求**

1.全局唯一：不能出现重复ID号，既然是唯一标识，这是最基本的要求

2.趋势递增：在MySql的InnoDB引擎中使用的是聚集索引，由于多数的RDBMS使用Btree的数据结构来存储索引数据，在主键的选择上面我们应该尽量使用有序的主键来保证性能。

3.单调递增：保证下一个ID一定大于上一个ID例如事务版本号、IM增量消息、排序等特殊需求

4.信息安全：如果ID是连续的，恶意用户的扒取工作就非常容易了，直接按照顺序下载指定的URL即可，如果是订单号就更危险了，竞争对手就可以直接知道我们一天的单量。所以在一些应用场景下，需要ID无规则不规则，让竞争对手不好猜

5.含时间戳：能够在开发中快速了解这个分布式id的生成时间











# 大厂面试之雪花算法（下）

