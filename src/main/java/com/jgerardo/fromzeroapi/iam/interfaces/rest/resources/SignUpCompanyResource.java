package com.jgerardo.fromzeroapi.iam.interfaces.rest.resources;

public record SignUpCompanyResource(
        String mail,
        String password,
        String companyName
) {
}
