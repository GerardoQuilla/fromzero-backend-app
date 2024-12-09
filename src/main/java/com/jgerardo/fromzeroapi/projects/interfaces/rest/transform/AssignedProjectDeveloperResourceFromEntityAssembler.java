package com.jgerardo.fromzeroapi.projects.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.resources.AssignProjectDeveloperResource;

public class AssignedProjectDeveloperResourceFromEntityAssembler {
    public static AssignProjectDeveloperResource toResourceFromEntity(Project entity){
        return new AssignProjectDeveloperResource(
                entity.getName(),
                entity.getState(),
                entity.getDeveloper(),
                entity.getCandidates()
        );
    }
}
