package com.jgerardo.fromzeroapi.profiles.domain.model.commands;

public record UpdateDeveloperProfileCommand(
        String id,
        String description,
        String country,
        String phone,
        String specialties,
        String profileImgUrl
) {
}
