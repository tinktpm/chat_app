package com.chat_app.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat_app.demo.model.Message;
import com.chat_app.demo.repository.MessageRepository;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;

    public Message getMessage(String id){
        return messageRepository.findBy_id(id).orElse(null);
    }

    public Message addMessage(Message message){
        try {
            return messageRepository.insert(message);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
