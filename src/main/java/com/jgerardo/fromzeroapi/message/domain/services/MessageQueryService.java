package com.jgerardo.fromzeroapi.message.domain.services;

import com.jgerardo.fromzeroapi.message.domain.model.entities.Message;
import com.jgerardo.fromzeroapi.message.domain.model.queries.GetAllMessagesByChatIdQuery;

import java.util.List;

public interface MessageQueryService {
    List<Message> handle(GetAllMessagesByChatIdQuery query);
}
