package com.nhnacademy.edu.springframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageSendService {

    private MessageSender messageSender;

    public MessageSendService() {

    }

    @Autowired
    public MessageSendService(@Qualifier("doorayMessageSender") MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public boolean sendMessage(User user, String message) {
        System.out.println("message = " + message);
        return messageSender.sendMessage(user, message);

    }

}
