package com.jgerardo.fromzeroapi.profiles.domain.model.commands;

public record CreateDeveloperProfileCommand(
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
) {
}
