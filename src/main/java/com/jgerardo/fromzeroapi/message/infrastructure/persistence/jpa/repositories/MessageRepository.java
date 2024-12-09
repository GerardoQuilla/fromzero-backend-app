package com.jgerardo.fromzeroapi.message.infrastructure.persistence.jpa.repositories;

import com.jgerardo.fromzeroapi.message.domain.model.aggregates.Chat;
import com.jgerardo.fromzeroapi.message.domain.model.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByChatOrderByCreatedAt(Chat chat);
}
