package com.chat_app.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chat_app.demo.model.Chat;
import java.util.List;
import java.util.Optional;


@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    Optional<Chat> findByUsers(List<String> users);

    Optional<Chat> findBy_id(String _id);
}
