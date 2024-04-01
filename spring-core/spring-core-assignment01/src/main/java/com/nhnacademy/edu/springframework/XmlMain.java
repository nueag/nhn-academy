package com.nhnacademy.edu.springframework;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlMain {

    public static void main(String[] args) {
        User user = new User("이가은");

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        MessageSendService
                messageSendService = context.getBean("messageSendService", MessageSendService.class);
        messageSendService.sendMessage(user, "안녕하세요");
    }
}
