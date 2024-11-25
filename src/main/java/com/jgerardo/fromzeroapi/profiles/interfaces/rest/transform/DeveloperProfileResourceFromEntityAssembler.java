package com.jgerardo.fromzeroapi.profiles.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.profiles.interfaces.rest.resources.DeveloperProfileResource;

public class DeveloperProfileResourceFromEntityAssembler {
    public static DeveloperProfileResource toResourceFromEntity(Developer entity){
        return new DeveloperProfileResource(
                entity.getId(),
                entity.getProfileId().RecordId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getDescription(),
                entity.getCountry(),
                entity.getPhone(),
                entity.getCompletedProjects(),
                entity.getSpecialties(),
                entity.getProfileImgUrl(),
                entity.getUserId()
        );
    }
}
