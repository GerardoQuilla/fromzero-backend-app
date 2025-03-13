package com.jgerardo.fromzeroapi.profiles.interfaces.acl;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.profiles.domain.model.commands.*;
import com.jgerardo.fromzeroapi.profiles.domain.model.queries.GetCompanyProfileByIdOrRecordIdQuery;
import com.jgerardo.fromzeroapi.profiles.domain.model.queries.GetDeveloperProfileByIdOrRecordIdQuery;
import com.jgerardo.fromzeroapi.profiles.domain.services.ProfileCommandService;
import com.jgerardo.fromzeroapi.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileContextFacade {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileContextFacade(ProfileQueryService profileQueryService,
                                ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    public void createDeveloperProfile(
            String firstName,
            String lastName,
            String email,
            String description,
            String country,
            String phone,
            int completedProjects,
            String specialties,
            String profileImgUrl,
            Long userId
    ){
        var command = new CreateDeveloperProfileCommand(
                firstName,
                lastName,
                email,
                description,
                country,
                phone,
                completedProjects,
                specialties,
                profileImgUrl,
                userId
        );
        profileCommandService.handle(command);
    }

    public void createCompanyProfile(
            String companyName,
            String email,
            String description,
            String country,
            String ruc,
            String phone,
            String website,
            String profileImgUrl,
            String sector,
            Long userId
    ){
        var command = new CreateCompanyProfileCommand(
                companyName,
                email,
                description,
                country,
                ruc,
                phone,
                website,
                profileImgUrl,
                sector,
                userId
        );
        profileCommandService.handle(command);
    }

    public Optional<Developer> getDeveloperById(String id){
        return profileQueryService.handle(new GetDeveloperProfileByIdOrRecordIdQuery(id));
    }

    public Optional<Company> getCompanyById(String id){
        return profileQueryService.handle(new GetCompanyProfileByIdOrRecordIdQuery(id));
    }

    public void updateDeveloperCompletedProjects(String developerId,Double rating){
        var command = new UpdateDeveloperProjectsMetricSetCommand(developerId,rating);
        var updatedDeveloper = this.profileCommandService.handle(command);
    }

    public void deleteCompanyProfile(Long userId){
        profileCommandService.handle(new DeleteCompanyProfileCommand(userId));
    }

    public void deleteDeveloperProfile(Long userId){
        profileCommandService.handle(new DeleteDeveloperProfileCommand(userId));
    }

}
