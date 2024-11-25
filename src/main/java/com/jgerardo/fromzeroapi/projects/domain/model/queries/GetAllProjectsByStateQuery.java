package com.jgerardo.fromzeroapi.projects.domain.model.queries;

import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectState;

public record GetAllProjectsByStateQuery(ProjectState state) {

}
