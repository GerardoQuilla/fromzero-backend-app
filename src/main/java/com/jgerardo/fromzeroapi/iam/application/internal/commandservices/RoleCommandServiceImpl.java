package com.jgerardo.fromzeroapi.iam.application.internal.commandservices;

import com.jgerardo.fromzeroapi.iam.domain.model.commands.SeedRolesCommand;
import com.jgerardo.fromzeroapi.iam.domain.model.entities.Role;
import com.jgerardo.fromzeroapi.iam.domain.model.valueobjects.Roles;
import com.jgerardo.fromzeroapi.iam.domain.services.RoleCommandService;
import com.jgerardo.fromzeroapi.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl  implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}