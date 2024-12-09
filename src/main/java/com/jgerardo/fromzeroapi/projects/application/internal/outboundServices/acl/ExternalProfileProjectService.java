package com.jgerardo.fromzeroapi.projects.application.internal.outboundServices.acl;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileProjectService {

    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileProjectService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Optional<Developer> getDeveloperById(String developerId){
        return profileContextFacade.getDeveloperById(developerId);
    }

    public Optional<Company> getCompanyById(String companyId){
        return profileContextFacade.getCompanyById(companyId);
    }

    public void updateDeveloperCompletedProjects(String developerId, Double rating){
        profileContextFacade.updateDeveloperCompletedProjects(developerId,rating);
    }
}
