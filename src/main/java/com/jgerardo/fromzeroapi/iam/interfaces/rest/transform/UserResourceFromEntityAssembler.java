package com.jgerardo.fromzeroapi.iam.interfaces.rest.transform;


import com.jgerardo.fromzeroapi.iam.domain.model.aggregates.User;
import com.jgerardo.fromzeroapi.iam.domain.model.entities.Role;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getEmail(),roles);
    }
}
