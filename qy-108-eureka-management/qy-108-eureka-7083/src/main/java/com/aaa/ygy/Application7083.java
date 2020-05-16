package com.aaa.ygy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @program: qy-108
 * @description: run
 * @author: ygy
 * @create: 2020-05-11-2020/5/11 21:00
 */
@SpringBootApplication
@EnableEurekaServer
public class Application7083 {
    public static void main(String[] args) {
        SpringApplication.run(Application7083.class,args);
    }
}
