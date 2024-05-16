package com.chat_app.demo.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("message")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    @Id
    private String _id;
    private String text;
    private LocalDateTime createdAt;

    @DocumentReference(collection = "user")
    private User user;
    private String chatID;

    public Message(String text, User user, String createdAt){
        this.text = text;
        this.user = user;
        this.createdAt = LocalDateTime.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("_id", _id);
        map.put("text", text);
        Date date = Date.from(this.createdAt.atZone(ZoneId.systemDefault()).toInstant());
        map.put("createdAt", date);
        map.put("user", user);
        map.put("chatID", chatID);

        return map;
    }
    
}
