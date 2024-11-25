package com.jgerardo.fromzeroapi.iam.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.iam.domain.model.aggregates.User;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUsedResourcerFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(),user.getEmail(),token);
    }
}
