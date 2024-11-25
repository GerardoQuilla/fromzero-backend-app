package com.jgerardo.fromzeroapi.projects.interfaces.rest.resources;

import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.DeliverableState;

import java.time.LocalDate;
import java.util.List;

public record DeliverableResource(
        Long id,
        String name,
        String description,
        LocalDate date,
        DeliverableState state,
        String developerMessage,
        // Project project,
        Long projectId,
        List<FileResource> files
) {
}
