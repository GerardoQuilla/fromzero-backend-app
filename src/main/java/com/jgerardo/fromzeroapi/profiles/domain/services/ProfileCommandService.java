package com.jgerardo.fromzeroapi.profiles.domain.services;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.profiles.domain.model.commands.*;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Developer> handle(UpdateDeveloperProjectsMetricSetCommand command);
    Optional<Developer> handle(UpdateDeveloperProfileCommand command);
    Optional<Company> handle(UpdateCompanyProfileCommand command);
    void handle(DeleteDeveloperProfileCommand command);
    void handle(DeleteCompanyProfileCommand command);

    void handle(CreateCompanyProfileCommand command);
    void handle(CreateDeveloperProfileCommand command);
}
