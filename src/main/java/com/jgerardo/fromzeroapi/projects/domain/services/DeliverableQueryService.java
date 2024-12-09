package com.jgerardo.fromzeroapi.projects.domain.services;

import com.jgerardo.fromzeroapi.projects.domain.model.entities.Deliverable;
import com.jgerardo.fromzeroapi.projects.domain.model.queries.GetAllDeliverablesByProjectIdQuery;
import com.jgerardo.fromzeroapi.projects.domain.model.queries.GetCompletedDeliverablesQuery;
import com.jgerardo.fromzeroapi.projects.domain.model.queries.GetDeliverableByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DeliverableQueryService {
    List<Deliverable> handle(GetAllDeliverablesByProjectIdQuery query);
    Optional<Deliverable> handle(GetDeliverableByIdQuery query);
    Long handle(GetCompletedDeliverablesQuery query);
}
