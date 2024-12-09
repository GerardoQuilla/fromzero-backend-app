package com.jgerardo.fromzeroapi.iam.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.iam.domain.model.commands.SignInCommand;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}
