package com.jgerardo.fromzeroapi.message.domain.model.entities;

import com.jgerardo.fromzeroapi.message.domain.model.aggregates.Chat;
import com.jgerardo.fromzeroapi.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Message extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Setter
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(name = "profile_id")
    private String senderId;

    @Column(columnDefinition = "TEXT")
    private String content;


    public Message(String senderId, String content, Chat chat) {
        this.senderId = senderId;
        this.content = content;
        this.chat = chat;
    }

    public Message() {

    }

    // Getters y otros métodos
}

