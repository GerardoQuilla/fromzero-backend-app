package com.jgerardo.fromzeroapi.iam.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.iam.domain.model.commands.SignUpCompanyCommand;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.resources.SignUpCompanyResource;

public class CompanyCommandFromSignUpCompanyResourceAssembler {
    public static SignUpCompanyCommand toCommandFromResource(SignUpCompanyResource resource) {
        return new SignUpCompanyCommand(
                resource.email(),
                resource.password(),
                resource.companyName()
        );
    }
}
