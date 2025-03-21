package com.jgerardo.fromzeroapi.message.application.internal.queryServices;

import com.jgerardo.fromzeroapi.message.domain.model.entities.Message;
import com.jgerardo.fromzeroapi.message.domain.model.queries.GetAllMessagesByChatIdQuery;
import com.jgerardo.fromzeroapi.message.domain.services.MessageQueryService;
import com.jgerardo.fromzeroapi.message.infrastructure.persistence.jpa.repositories.ChatRepository;
import com.jgerardo.fromzeroapi.message.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public MessageQueryServiceImpl(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> handle(GetAllMessagesByChatIdQuery query) {
        var chat = chatRepository.findById(query.chatId());
        if (chat.isEmpty()){
            return List.of();
        }
        return messageRepository.findAllByChatOrderByCreatedAt(chat.get());
    }
}
