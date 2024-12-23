package com.jgerardo.fromzeroapi.projects.domain.services;

import com.jgerardo.fromzeroapi.projects.domain.model.commands.CreateDeliverableCommand;
import com.jgerardo.fromzeroapi.projects.domain.model.entities.Deliverable;
import com.jgerardo.fromzeroapi.projects.domain.model.commands.UpdateDeliverableStatusCommand;
import com.jgerardo.fromzeroapi.projects.domain.model.commands.UpdateDeveloperMessageCommand;

import java.util.List;
import java.util.Optional;

public interface DeliverableCommandService {
    Optional<Deliverable> handle(CreateDeliverableCommand command);
    Optional<Deliverable> handle(UpdateDeveloperMessageCommand command);
    Optional<Deliverable> handle(UpdateDeliverableStatusCommand command);
    void handle(List<CreateDeliverableCommand> commands);
}
