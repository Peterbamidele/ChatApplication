package com.peter.chatapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessager {

    private MessageType type;

    private String content;
    private String sender;
}
