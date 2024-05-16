package com.chat_app.demo.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.chat_app.demo.model.Message;
import com.chat_app.demo.service.ChatService;
import com.chat_app.demo.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomWebSocketHandler implements WebSocketHandler{
    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        
        if(!sessions.contains(session)){
            sessions.add(session);
        }
        
        Flux<WebSocketMessage> flux = session.receive()
                .map(webSocketMessage -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.registerModule(new JavaTimeModule());

                        Message payload = objectMapper.readValue(
                                webSocketMessage.getPayloadAsText(),
                                Message.class);

                        Message addedMessage = messageService.addMessage(payload);

                        chatService.updateChat(addedMessage);

                        broadcastMessage(session, addedMessage.toMap());
                        return new ObjectMapper().writeValueAsString(addedMessage.toMap());
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Error processing WebSocket message", e);
                    }
                })
                .map(session::textMessage);
        return session.send(flux);
    }

    private void broadcastMessage(WebSocketSession currSession, Object message) {
        String jsonMessage;
        try {
            jsonMessage = new ObjectMapper().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        sessions.forEach(session -> {
            if(session.isOpen() && session != currSession){
                session.send(Mono.just(session.textMessage(jsonMessage)))
                        .subscribe();
            }
        });
    }
    
}
