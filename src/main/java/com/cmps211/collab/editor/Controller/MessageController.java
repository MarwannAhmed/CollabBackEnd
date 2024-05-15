package com.cmps211.collab.editor.Controller;

import com.cmps211.collab.editor.Model.Message;
import com.cmps211.collab.editor.Service.DocService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private DocService docService;
    private final Lock lock = new ReentrantLock();

    @MessageMapping("/application/{docID}")
    public void passMessage(final Message message, @DestinationVariable String docID) {
        if (lock.tryLock()) {
            try {
                messagingTemplate.convertAndSend("/all/messages/" + docID, message);
                docService.changeContent(message, docID);
            } finally {
                lock.unlock();
            }
        } else {
            return;
        }
    }
}