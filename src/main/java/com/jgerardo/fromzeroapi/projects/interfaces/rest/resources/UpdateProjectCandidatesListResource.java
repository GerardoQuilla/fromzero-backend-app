package com.jgerardo.fromzeroapi.projects.interfaces.rest.resources;

import java.util.List;

public record UpdateProjectCandidatesListResource(
        String name,
        List<String> candidates
) {
}
