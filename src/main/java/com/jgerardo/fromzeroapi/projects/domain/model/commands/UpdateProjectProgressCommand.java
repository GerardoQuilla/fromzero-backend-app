package com.jgerardo.fromzeroapi.projects.domain.model.commands;

public record UpdateProjectProgressCommand(
        Long projectId,
        Long completedDeliverables,
        Integer totalDeliverables
) {
}
