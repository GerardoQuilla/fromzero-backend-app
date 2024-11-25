package com.jgerardo.fromzeroapi.projects.interfaces.rest.resources;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;

import java.util.Set;

public record UpdateProjectCandidatesListResource(
        String name,
        Set<Developer> candidates
) {
}
