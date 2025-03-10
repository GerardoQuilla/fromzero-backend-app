package com.jgerardo.fromzeroapi.profiles.interfaces.rest.resources;

public record DeveloperProfileResource(
        Long id,
        String ProfileId,
        String firstName,
        String lastName,
        String email,
        String description,
        String country,
        String phone,
        Integer completedProjects,
        Double averageRating,
        String specialties,
        String profileImgUrl,
        Long userId
) {
}
