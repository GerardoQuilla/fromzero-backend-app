package com.jgerardo.fromzeroapi.message.domain.services;

import com.jgerardo.fromzeroapi.message.domain.model.aggregates.Chat;
import com.jgerardo.fromzeroapi.message.domain.model.queries.GetAllChatsByCompanyProfileIdQuery;
import com.jgerardo.fromzeroapi.message.domain.model.queries.GetAllChatsByDeveloperProfileIdQuery;
import com.jgerardo.fromzeroapi.message.domain.model.queries.GetChatByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ChatQueryService {
    List<Chat> handle(GetAllChatsByCompanyProfileIdQuery query);
    List<Chat> handle(GetAllChatsByDeveloperProfileIdQuery query);
    Optional<Chat> handle(GetChatByIdQuery query);
}
