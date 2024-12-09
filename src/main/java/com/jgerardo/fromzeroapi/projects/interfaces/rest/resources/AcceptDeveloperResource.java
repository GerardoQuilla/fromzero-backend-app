package com.jgerardo.fromzeroapi.projects.interfaces.rest.resources;

public record AcceptDeveloperResource(
        String developerId,
        Boolean accepted
) {
}
