package com.jgerardo.fromzeroapi.iam.domain.model.commands;

public record SignUpSupportCommand(
        String email,
        String password,
        String role
) {
}
