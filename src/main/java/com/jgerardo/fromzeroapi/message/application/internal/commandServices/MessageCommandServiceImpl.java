package com.jgerardo.fromzeroapi.message.application.internal.commandServices;

import com.jgerardo.fromzeroapi.message.domain.model.commands.AddMessageCommand;
import com.jgerardo.fromzeroapi.message.domain.model.entities.Message;
import com.jgerardo.fromzeroapi.message.domain.services.MessageCommandService;
import com.jgerardo.fromzeroapi.message.infrastructure.persistence.jpa.repositories.ChatRepository;
import com.jgerardo.fromzeroapi.message.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public MessageCommandServiceImpl(MessageRepository messageRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    @Override
    public Optional<Message> handle(AddMessageCommand command) {
        var chat = chatRepository.findById(command.chatId());
        if (chat.isEmpty()){
            return Optional.empty();
        }
        var message = new Message(command.senderId(), command.content(), chat.get());
        chat.get().addMessage(message);
        messageRepository.save(message);
        return Optional.of(message);
    }
}
