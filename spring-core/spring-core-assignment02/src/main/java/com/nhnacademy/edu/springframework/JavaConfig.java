package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class JavaConfig {
    private static final String HOOK_URL =
            "https://hook.dooray.com/services/3204376758577275363/3707922642738216364/WdJUIozGSgGdnxhQnS1lSA";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DoorayHookSender doorayHookSender() {
        return new DoorayHookSender(restTemplate(), HOOK_URL);
    }

    @Bean
    public MessageSender doorayMessageSender(DoorayHookSender doorayHookSender) {
        return new DoorayMessageSender(doorayHookSender);
    }

    @Bean
    public MessageSendService messageSendService(MessageSender messageSender) {
        return new MessageSendService(messageSender);
    }

    @Bean
    ElapsedTimeAspect elapsedTimeAspect() {
        return new ElapsedTimeAspect();
    }
}
