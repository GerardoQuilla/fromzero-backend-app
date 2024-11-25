package com.jgerardo.fromzeroapi.projects.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.projects.domain.model.commands.AcceptProjectDeveloperCommand;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.resources.AcceptDeveloperResource;

public class AcceptProjectDeveloperCommandFromResourceAssembler {
    public static AcceptProjectDeveloperCommand toCommandFromResource(AcceptDeveloperResource resource, Long projectId) {
        return new AcceptProjectDeveloperCommand(
                projectId,
                resource.developerId(),
                resource.accepted()
        );
    }
}
