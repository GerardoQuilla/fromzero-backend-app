package com.jgerardo.fromzeroapi.projects.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.resources.UpdateProjectCandidatesListResource;

public class UpdatedProjectResourceFromEntityAssembler {
    public static UpdateProjectCandidatesListResource toResourceFromEntity(Project entity){
        return new UpdateProjectCandidatesListResource(
                entity.getName(),
                entity.getCandidates()
        );
    }
}
