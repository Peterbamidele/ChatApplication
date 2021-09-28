package com.peter.chatapplication.listener;

import com.peter.chatapplication.model.ChatMessager;
import com.peter.chatapplication.model.MessageType;
import org.apache.logging.log4j.message.SimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;



@Component
public class WebsocketEventListner {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketEventListner.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionConnectedEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null){
            logger.info("User Discounnected : " + username);

            ChatMessager chatMessager = new ChatMessager();

            chatMessager.setType(MessageType.LEAVE);

            chatMessager.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessager);
        }

    }
}