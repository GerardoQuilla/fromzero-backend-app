package com.jgerardo.fromzeroapi.profiles.interfaces.rest.resources;

public record UpdateDeveloperProfileResource(
        String description,
        String country,
        String phone,
        String specialties,
        String profileImgUrl
) {
}
