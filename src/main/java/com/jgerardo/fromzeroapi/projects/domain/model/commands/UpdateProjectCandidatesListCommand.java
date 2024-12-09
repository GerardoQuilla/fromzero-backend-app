package com.jgerardo.fromzeroapi.projects.domain.model.commands;

//import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;

public record UpdateProjectCandidatesListCommand(
        String developerId,
        Long projectId
) {
}
