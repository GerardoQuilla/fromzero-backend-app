package com.jgerardo.fromzeroapi.projects.interfaces.rest.resources;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectState;
import java.util.Set;

public record AssignProjectDeveloperResource(
        String name,
        ProjectState state,
        Developer developer,
        Set<Developer> candidates
) {
}
