package com.jgerardo.fromzeroapi.iam.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.iam.domain.model.commands.SignUpDeveloperCommand;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.resources.SignUpDeveloperResource;

public class DeveloperCommandFromSignUpDeveloperResourceAssembler {
    public static SignUpDeveloperCommand toCommandFromResource(SignUpDeveloperResource signUpDeveloperResource) {
        return new SignUpDeveloperCommand(
                signUpDeveloperResource.email(),
                signUpDeveloperResource.password(),
                signUpDeveloperResource.firstName(),
                signUpDeveloperResource.lastName()
        );
    }
}
