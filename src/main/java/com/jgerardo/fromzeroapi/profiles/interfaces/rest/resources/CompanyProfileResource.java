package com.jgerardo.fromzeroapi.profiles.interfaces.rest.resources;

public record CompanyProfileResource(
        Long id,
        String ProfileId,
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
) {
}
