package com.jgerardo.fromzeroapi.profiles.domain.model.commands;

public record CreateCompanyProfileCommand(
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
