package com.jgerardo.fromzeroapi.projects.interfaces.rest.resources;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.Frameworks;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProgrammingLanguages;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectState;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectType;

import java.util.Set;

public record ProjectResource(
        Long id,
        String name,
        String description,
        ProjectState state,
        Double progress,
        Company company,
        Developer developer,
        Set<Developer> candidates,
        Set<ProgrammingLanguages> languages,
        Set<Frameworks> frameworks,
        ProjectType type,
        String budget
){
}
