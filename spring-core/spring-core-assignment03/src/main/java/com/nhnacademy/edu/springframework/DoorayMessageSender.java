package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoorayMessageSender implements MessageSender {
    private DoorayHookSender doorayHookSender;

    @Autowired
    public DoorayMessageSender(DoorayHookSender doorayHookSender) {
        this.doorayHookSender = doorayHookSender;
    }

    @Override
    @Message
    public boolean sendMessage(User user, String message) {
        if (user == null) {
            return false;
        }
        doorayHookSender.send(DoorayHook.builder()
                .botName(user.getName())
                .text(message)
                .build());
        return true;
    }

}