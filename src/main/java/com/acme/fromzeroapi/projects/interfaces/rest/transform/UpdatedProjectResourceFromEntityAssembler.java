package com.acme.fromzeroapi.projects.interfaces.rest.transform;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.UpdateProjectCandidatesListResource;

import java.util.stream.Collectors;

public class UpdatedProjectResourceFromEntityAssembler {
    public static UpdateProjectCandidatesListResource toResourceFromEntity(Project entity){

        return new UpdateProjectCandidatesListResource(
                entity.getName(),
                entity.getCandidates().stream().map(item->item.getProfileId().RecordId()).collect(Collectors.toList())
        );
    }
}
