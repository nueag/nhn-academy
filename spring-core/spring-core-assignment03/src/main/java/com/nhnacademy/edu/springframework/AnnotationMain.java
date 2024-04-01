package com.nhnacademy.edu.springframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationMain {

    public static void main(String[] args) {
        User user = new User("간3");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework");
        MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
        messageSendService.sendMessage(user, "안녕하세요");
    }


}
