package com.jgerardo.fromzeroapi.profiles.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.profiles.interfaces.rest.resources.CompanyProfileResource;

public class CompanyProfileResourceFromEntityAssembler {
    public static CompanyProfileResource toResourceFromEntity(Company entity){
        return new CompanyProfileResource(
                entity.getId(),
                entity.getProfileId().RecordId(),
                entity.getCompanyName(),
                entity.getEmail(),
                entity.getDescription(),
                entity.getCountry(),
                entity.getRuc(),
                entity.getPhone(),
                entity.getWebsite(),
                entity.getProfileImgUrl(),
                entity.getSector(),
                entity.getUserId()
        );
    }
}
