package com.jgerardo.fromzeroapi.message.application.internal.queryServices;

import com.jgerardo.fromzeroapi.message.application.internal.outboundservices.ExternalProfileMesssageService;
import com.jgerardo.fromzeroapi.message.domain.model.aggregates.Chat;
import com.jgerardo.fromzeroapi.message.domain.model.queries.GetAllChatsByCompanyProfileIdQuery;
import com.jgerardo.fromzeroapi.message.domain.model.queries.GetAllChatsByDeveloperProfileIdQuery;
import com.jgerardo.fromzeroapi.message.domain.model.queries.GetChatByIdQuery;
import com.jgerardo.fromzeroapi.message.domain.services.ChatQueryService;
import com.jgerardo.fromzeroapi.message.infrastructure.persistence.jpa.repositories.ChatRepository;
import com.jgerardo.fromzeroapi.shared.domain.exceptions.CompanyNotFoundException;
import com.jgerardo.fromzeroapi.shared.domain.exceptions.DeveloperNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatQueryServiceImpl implements ChatQueryService {
    private final ChatRepository chatRepository;
    private final ExternalProfileMesssageService externalProfileMesssageService;

    public ChatQueryServiceImpl(ChatRepository chatRepository,  ExternalProfileMesssageService externalProfileMesssageService) {
        this.chatRepository = chatRepository;
        this.externalProfileMesssageService = externalProfileMesssageService;
    }
    @Override
    public List<Chat> handle(GetAllChatsByCompanyProfileIdQuery query) {
        var company = externalProfileMesssageService
                .getCompanyByProfileId(query.companyProfileId())
                .orElseThrow(
                        ()-> new CompanyNotFoundException(query.companyProfileId())
                );
        return chatRepository.findAllByCompany(company);
    }

    @Override
    public List<Chat> handle(GetAllChatsByDeveloperProfileIdQuery query) {
        var developer = externalProfileMesssageService
                .getDeveloperByProfileId(query.developerProfileId())
                .orElseThrow(
                        ()->new DeveloperNotFoundException(query.developerProfileId())
                );
        return chatRepository.findAllByDeveloper(developer);
    }

    @Override
    public Optional<Chat> handle(GetChatByIdQuery query) {
        return chatRepository.findById(query.chatId());
    }
}
