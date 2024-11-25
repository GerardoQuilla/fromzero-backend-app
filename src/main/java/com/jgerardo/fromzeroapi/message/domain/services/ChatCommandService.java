package com.jgerardo.fromzeroapi.message.domain.services;

import com.jgerardo.fromzeroapi.message.domain.model.commands.CreateChatCommand;

import java.util.Optional;

public interface ChatCommandService {
    Optional<Long> handle(CreateChatCommand command);
}
