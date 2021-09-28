package com.peter.chatapplication.controller;

import com.peter.chatapplication.model.ChatMessager;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessager sendMessage(@Payload ChatMessager chatMessager) {
        return chatMessager;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessager addUser(@Payload ChatMessager chatMessager, SimpMessageHeaderAccessor headerAccessor) {

// Added username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessager.getSender());
        return chatMessager;
    }
}