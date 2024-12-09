package com.jgerardo.fromzeroapi.projects.interfaces.acl;

import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import com.jgerardo.fromzeroapi.projects.domain.model.commands.FinishProjectCommand;
import com.jgerardo.fromzeroapi.projects.domain.model.queries.GetProjectByIdQuery;
import com.jgerardo.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.jgerardo.fromzeroapi.projects.domain.services.ProjectQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectContextFacade {
    private final ProjectQueryService projectQueryService;
    private final ProjectCommandService projectCommandService;

    public ProjectContextFacade(
            ProjectQueryService projectQueryService,
            ProjectCommandService projectCommandService
    ) {
        this.projectQueryService = projectQueryService;
        this.projectCommandService = projectCommandService;
    }

    public Optional<Project> getProjectById(Long projectId){
        return projectQueryService.handle(new GetProjectByIdQuery(projectId));
    }

    public void finishProject(Long projectId, Double developerRating){
        projectCommandService.handle(new FinishProjectCommand(projectId, developerRating));
    }
}
