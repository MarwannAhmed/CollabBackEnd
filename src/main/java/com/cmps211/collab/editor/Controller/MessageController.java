package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/application/{docID}")
    @SendTo("/all/messages/{docID}")
    public Message send(final Message message) throws Exception {
        return message;
    }

    // @MessageMapping("/private")
    // public void sendToSpecificUser(@Payload Message message) {
    // simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific",
    // message);
    // }
}