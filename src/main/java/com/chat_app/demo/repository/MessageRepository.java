package com.chat_app.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chat_app.demo.model.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String>{
    Optional<Message> findBy_id(String _id);
}
