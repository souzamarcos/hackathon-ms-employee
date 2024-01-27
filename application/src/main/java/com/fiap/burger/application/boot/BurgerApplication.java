package com.fiap.burger.application.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.fiap.burger", exclude = {DataSourceAutoConfiguration.class})
public class BurgerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BurgerApplication.class, args);
    }
}
