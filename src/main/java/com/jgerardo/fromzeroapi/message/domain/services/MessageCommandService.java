package com.jgerardo.fromzeroapi.message.domain.services;

import com.jgerardo.fromzeroapi.message.domain.model.commands.AddMessageCommand;
import com.jgerardo.fromzeroapi.message.domain.model.entities.Message;

import java.util.Optional;

public interface MessageCommandService {
    Optional<Message> handle(AddMessageCommand command);
}
