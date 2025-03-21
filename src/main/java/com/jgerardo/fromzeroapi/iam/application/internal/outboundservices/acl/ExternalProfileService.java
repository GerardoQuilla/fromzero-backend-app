package com.jgerardo.fromzeroapi.iam.application.internal.outboundservices.acl;

import com.jgerardo.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalProfileService {
    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public void createDeveloperProfile(String firstName, String lastName, String email, Long userId){
        profileContextFacade.createDeveloperProfile(
                firstName,
                lastName,
                email,
                "No description provided.",
                "No country provided.",
                "No phone provided.",
                0,
                "No specialties provided.",
                "https://cdn-icons-png.flaticon.com/512/3237/3237472.png",
                userId
        );
    }

    public void createCompanyProfile(String companyName,String email, Long userId){
        profileContextFacade.createCompanyProfile(
                companyName,
                email,
                "No description provided.",
                "No country provided.",
                "No phone provided.",
                "999 999 999",
                "No website provided.",
                "https://cdn-icons-png.flaticon.com/512/3237/3237472.png",
                "No sector provided.",
                userId
        );
    }

    public void deleteCompanyProfile(Long id){
        profileContextFacade.deleteCompanyProfile(id);
    }

    public void deleteDeveloperProfile(Long id){
        profileContextFacade.deleteDeveloperProfile(id);
    }
}
