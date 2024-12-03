package com.acme.fromzeroapi.iam.interfaces.rest.transform;

import com.acme.fromzeroapi.iam.domain.model.commands.SignUpDeveloperCommand;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.SignUpDeveloperResource;

public class DeveloperCommandFromSignUpDeveloperResourceAssembler {
    public static SignUpDeveloperCommand toCommandFromResource(SignUpDeveloperResource resource) {
        return new SignUpDeveloperCommand(
                resource.email(),
                resource.password(),
                resource.firstName(),
                resource.lastName()
        );
    }
}
