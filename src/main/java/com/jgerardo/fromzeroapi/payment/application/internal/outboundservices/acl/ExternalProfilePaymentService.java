package com.jgerardo.fromzeroapi.payment.application.internal.outboundservices.acl;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfilePaymentService {
    private final ProfileContextFacade profileContextFacade;

    public ExternalProfilePaymentService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }
    public Optional<Developer> fetchDeveloper(String developerId){
        return profileContextFacade.getDeveloperById(developerId);
    }
}
