package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Model.Message;
import com.cmps211.collab.editor.Service.DocService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.Payload;
// import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private final DocService docService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    private Object mutex;
    private int lock;

    MessageController(DocService ds) {
        docService = ds;
        lock = 0;
    }

    @MessageMapping("/application/{docID}")
    public void send(final Message message, @DestinationVariable String docID) throws Exception {
        synchronized (mutex) {
            if (lock == 0) {
                lock = 1;
            } else {
                return;
            }
        }
        simpMessagingTemplate.convertAndSend("/all/messages/{docID}", message);
        docService.changeContent(message, docID);
        synchronized (mutex) {
            lock = 0;
        }
    }

    // @MessageMapping("/private")
    // public void sendToSpecificUser(@Payload Message message) {
    // simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific",
    // message);
    // }
}