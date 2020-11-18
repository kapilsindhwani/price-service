package com.mizuho.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/message")
@Tag(name = "Message", description = "Message Service")
public class MessageController {

    @Autowired
    @Qualifier(value = "jmsTopicTemplate") JmsTemplate jmsTemplate;

    @GetMapping(value = "/browseDownstream1Queue")
    @ResponseStatus(HttpStatus.OK)
    public List<String> browseDownstream1Queue() {
        return getMessages(getTextMessage("downstream1-queue"));
    }

    @GetMapping(value = "/browseDownstream2Queue")
    @ResponseStatus(HttpStatus.OK)
    public List<String> browseDownstream2Queue() {
        return getMessages(getTextMessage("downstream2-queue"));
    }

    public List<TextMessage> getTextMessage(String queue) {
        List<TextMessage> messagesList = jmsTemplate.browse(queue, (session, queueBrowser) -> {
            List<TextMessage> messages = new ArrayList<>();
            Enumeration e = queueBrowser.getEnumeration();
            while (e.hasMoreElements()){
                TextMessage message = (TextMessage) e.nextElement();
                messages.add(message);
            }
            return messages;
        });
        return messagesList;
    }

    private List<String> getMessages(List<TextMessage> messageList){
        return messageList
                .stream()
                .map((textMessage -> {
                    try {
                        return textMessage.getText();
                    } catch (JMSException e) {
                        e.printStackTrace();
                        return "";
                    }
                })).collect(Collectors.toList());


    }


}
