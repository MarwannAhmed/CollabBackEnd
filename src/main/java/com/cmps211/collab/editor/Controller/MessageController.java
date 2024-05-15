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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Controller
public class MessageController {

    private final DocService docService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>(1);
    // private Object semaphore;
    // private boolean free = true;

    MessageController(DocService ds) {
        // semaphore = new Object();
        docService = ds;
        // free = true;
    }

    @MessageMapping("/application/{docID}")
    public void send(final Message message, @DestinationVariable String docID) throws Exception {
        // synchronized (semaphore) {
        // System.out.println(free);
        // if (free == true) {
        // free = false;
        // } else {
        // return;
        // }
        // }
        if (!messageQueue.offer(message)) {
            return;
        }
        simpMessagingTemplate.convertAndSend("/all/messages/" + docID, message);
        docService.changeContent(message, docID);
        messageQueue.poll();
        // synchronized (semaphore) {
        // free = true;
        // }
    }

    // @MessageMapping("/private")
    // public void sendToSpecificUser(@Payload Message message) {
    // simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific",
    // message);
    // }
}