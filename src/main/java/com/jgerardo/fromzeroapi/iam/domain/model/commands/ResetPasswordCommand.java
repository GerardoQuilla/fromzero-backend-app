package com.jgerardo.fromzeroapi.iam.domain.model.commands;

public record ResetPasswordCommand(
        String email,
        String newPassword
) {
}
