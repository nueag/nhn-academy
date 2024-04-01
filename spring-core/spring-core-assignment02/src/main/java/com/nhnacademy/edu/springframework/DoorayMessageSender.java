package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;

public class DoorayMessageSender implements MessageSender {
    private final DoorayHookSender doorayHookSender;

    public DoorayMessageSender(DoorayHookSender doorayHookSender) {
        this.doorayHookSender = doorayHookSender;
    }

    @Override
    @Message
    public boolean sendMessage(User user, String message) {
        if(user == null) {
            return false;
        }
        doorayHookSender.send(DoorayHook.builder()
                .botName(user.getName())
                .text(message)
                .build());
        return true;
    }

}