package com.jgerardo.fromzeroapi.projects.domain.model.commands;

import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.Frameworks;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProgrammingLanguages;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectCurrency;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectType;

import java.util.Set;

public record CreateProjectCommand(
        String name,
        String description,
        String companyId,
        Set<ProgrammingLanguages> languages,
        Set<Frameworks> frameworks,
        ProjectType type,
        /*String budget,*/
        Double budget,
        ProjectCurrency currency,
        String methodologies
) {

}
