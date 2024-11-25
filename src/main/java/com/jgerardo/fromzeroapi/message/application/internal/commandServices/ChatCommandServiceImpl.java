package com.jgerardo.fromzeroapi.message.application.internal.commandServices;

import com.jgerardo.fromzeroapi.message.domain.model.aggregates.Chat;
import com.jgerardo.fromzeroapi.message.domain.model.commands.CreateChatCommand;
import com.jgerardo.fromzeroapi.message.domain.services.ChatCommandService;
import com.jgerardo.fromzeroapi.message.infrastructure.persistence.jpa.repositories.ChatRepository;
import com.jgerardo.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
import com.jgerardo.fromzeroapi.shared.domain.exceptions.CompanyNotFoundException;
import com.jgerardo.fromzeroapi.shared.domain.exceptions.DeveloperNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatCommandServiceImpl implements ChatCommandService {
    private final ProfileContextFacade profileContextFacade;
    private final ChatRepository chatRepository;


    public ChatCommandServiceImpl(ProfileContextFacade profileContextFacade, ChatRepository chatRepository) {
        this.profileContextFacade = profileContextFacade;
        this.chatRepository = chatRepository;
    }

    @Override
    public Optional<Long> handle(CreateChatCommand command) {
        var company = profileContextFacade
                .getCompanyByProfileId(command.companyId())
                .orElseThrow(
                        ()->new CompanyNotFoundException(command.companyId())
                );
        var developer = profileContextFacade
                .getDeveloperByProfileId(command.developerId())
                .orElseThrow(
                        ()->new DeveloperNotFoundException(command.developerId())
                );
        if(chatRepository.existsByCompanyAndDeveloper(company,developer)){
           return Optional.empty();
        }
        var chat = new Chat(developer, company);
        chatRepository.save(chat);
        return Optional.of(chat.getId());
    }
}
