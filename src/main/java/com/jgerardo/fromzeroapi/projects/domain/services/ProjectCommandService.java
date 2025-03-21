package com.jgerardo.fromzeroapi.projects.domain.services;

import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import com.jgerardo.fromzeroapi.projects.domain.model.commands.*;

import java.util.Optional;

public interface ProjectCommandService {
    Optional<Project> handle(CreateProjectCommand command);
    Optional<Project> handle(UpdateProjectCandidatesListCommand command);
    Optional<Project> handle(AcceptProjectDeveloperCommand command);
    void handle(UpdateProjectProgressCommand command);
    void handle(FinishProjectCommand command);
}
