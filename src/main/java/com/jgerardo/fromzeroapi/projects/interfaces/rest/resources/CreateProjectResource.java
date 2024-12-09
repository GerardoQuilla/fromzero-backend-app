package com.jgerardo.fromzeroapi.projects.interfaces.rest.resources;

import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.Frameworks;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProgrammingLanguages;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectCurrency;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectType;

import java.util.List;
import java.util.Set;

public record CreateProjectResource(
        String name,
        String description,
        String ownerId,
        Set<ProgrammingLanguages> languages,
        Set<Frameworks> frameworks,
        ProjectType type,
        Double budget,
        ProjectCurrency currency,
        List<MethodologyResource> methodologies
) {

}
