package com.mmall.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.mmall.content.mapper")
@EnableSwagger2
public class ContenServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContenServiceApplication.class,args);
    }
}
