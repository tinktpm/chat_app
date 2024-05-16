package com.chat_app.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat_app.demo.model.Chat;
import com.chat_app.demo.model.Message;
import com.chat_app.demo.repository.ChatRepository;

@Service
public class ChatService {
    
    @Autowired
    private ChatRepository chatRepository;

    public Chat getChat(String id){
        return chatRepository.findBy_id(id).orElse(null);
    }

    public Chat getChatByUsers(List<String> users){
        return chatRepository.findByUsers(users).orElse(null);
    }

    public Chat addChat(Chat chat){
        try {
            return chatRepository.insert(chat);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    public Chat updateChat(Message message){
        try {
            Chat chat = chatRepository.findBy_id(message.getChatID()).orElse(null);
            if(chat != null){
                chat.getMessages().add(message);
                return chatRepository.save(chat);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
}
