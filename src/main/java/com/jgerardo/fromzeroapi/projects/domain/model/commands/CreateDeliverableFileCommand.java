package com.jgerardo.fromzeroapi.projects.domain.model.commands;

public record CreateDeliverableFileCommand(
        String name,
        String url
) {
}
