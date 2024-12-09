package com.jgerardo.fromzeroapi.projects.domain.model.commands;

public record UpdateDeliverableStatusCommand(Long deliverableId, Boolean accepted) {
}
