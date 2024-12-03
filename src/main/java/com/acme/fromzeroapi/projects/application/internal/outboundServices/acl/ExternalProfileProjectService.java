package com.acme.fromzeroapi.projects.application.internal.outboundServices.acl;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
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
