package com.jgerardo.fromzeroapi.projects.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.resources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity){
        return new ProjectResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getState(),
                entity.getProgress(),
                entity.getCompany(),
                entity.getDeveloper(),
                entity.getCandidates(),
                entity.getLanguages(),
                entity.getFrameworks(),
                entity.getType(),
                entity.getBudget().projectBudgetToString()
        );
    }
}
