package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Model.Message;
import com.cmps211.collab.editor.Service.DocService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private DocService docService;

    private final Object mutex = new Object();
    private int lock = 0;

    @MessageMapping("/application/{docID}")
    public void passMessage(final Message message, @DestinationVariable String docID) {
        synchronized (mutex) {
            if (lock == 0) {
                lock = 1;
            } else {
                return;
            }
        }
        messagingTemplate.convertAndSend("/all/messages/" + docID, message);
        docService.changeContent(message, docID);
        synchronized (mutex) {
            lock = 0;
        }

    }
}

// @MessageMapping("/private")
// public void sendToSpecificUser(@Payload Message message) {
// simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific",
// message);
// }
// }