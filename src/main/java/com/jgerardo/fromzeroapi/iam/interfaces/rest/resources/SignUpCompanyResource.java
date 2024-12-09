package com.jgerardo.fromzeroapi.iam.interfaces.rest.resources;

public record SignUpCompanyResource(
        String email,
        String password,
        String companyName
) {
}
