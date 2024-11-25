package com.jgerardo.fromzeroapi.projects.domain.model.events;

import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectType;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class CreateDefaultDeliverablesEvent extends ApplicationEvent {
    private final Long projectId;
    private final ProjectType projectType;

    public CreateDefaultDeliverablesEvent(Object source, Long projectId, ProjectType projectType) {
        super(source);
        this.projectId = projectId;
        this.projectType = projectType;
    }
}
