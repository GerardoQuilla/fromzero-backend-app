package com.jgerardo.fromzeroapi.iam.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.iam.domain.model.entities.Role;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}