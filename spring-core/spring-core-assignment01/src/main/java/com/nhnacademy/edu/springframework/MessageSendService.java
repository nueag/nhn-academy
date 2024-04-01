package com.nhnacademy.edu.springframework;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageSendService {

    private MessageSender messageSender;

    @Autowired
    public MessageSendService(@MessageKinds("doorayMessageSender") MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public boolean sendMessage(User user, String message) {
        System.out.println("message = " + message);
        return messageSender.sendMessage(user, message);

    }

}
