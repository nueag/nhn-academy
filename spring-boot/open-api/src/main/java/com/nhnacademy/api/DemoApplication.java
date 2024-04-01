package com.nhnacademy.api;

import com.nhnacademy.api.domain.Account;
import com.nhnacademy.api.domain.AccountClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@Slf4j
@ConfigurationPropertiesScan
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    AccountClientService accountClientService;


    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener() {
        return event -> {
            accountClientService.createAccount(new Account(1L, "이가은", 100000000));
            accountClientService.getAccounts()
                    .forEach(it -> log.info("multi: {}", it));

            log.info("single: {}", accountClientService.getAccount(1L));
        };
    }
}
