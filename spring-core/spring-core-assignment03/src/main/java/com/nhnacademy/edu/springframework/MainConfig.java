package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;


@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework")
public class MainConfig {
    private final String hookUrl =
            "https://hook.dooray.com/services/3204376758577275363/3707922642738216364/WdJUIozGSgGdnxhQnS1lSA";

    @Bean
    DoorayHookSender doorayHookSender() {
        return new DoorayHookSender(new RestTemplate(), hookUrl);
    }
}
