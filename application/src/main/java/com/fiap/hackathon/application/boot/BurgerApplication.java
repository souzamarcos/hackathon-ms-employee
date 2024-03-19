package com.fiap.hackathon.application.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.fiap.hackathon", exclude = {DataSourceAutoConfiguration.class})
public class BurgerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BurgerApplication.class, args);
    }
}
