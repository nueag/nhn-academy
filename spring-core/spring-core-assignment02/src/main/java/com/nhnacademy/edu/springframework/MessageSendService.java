package com.nhnacademy.edu.springframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MessageSendService {

    private MessageSender messageSender;

    @Autowired
    public MessageSendService(@Qualifier("doorayMessageSender") MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public boolean sendMessage(User user, String message) {
        System.out.println("message = " + message);
        return messageSender.sendMessage(user, message);

    }

}
