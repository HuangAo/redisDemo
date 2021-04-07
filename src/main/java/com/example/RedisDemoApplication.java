package com.example;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @Author Real
 * @Data 2021/4/7 11:37
 */
@EnableDiscoveryClient
@RefreshScope
@Log4j2
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class RedisDemoApplication extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(RedisDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       log.info("redis demo started..");
    }
}
