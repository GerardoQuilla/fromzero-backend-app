package com.jgerardo.fromzeroapi.iam.domain.services;

import com.jgerardo.fromzeroapi.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
