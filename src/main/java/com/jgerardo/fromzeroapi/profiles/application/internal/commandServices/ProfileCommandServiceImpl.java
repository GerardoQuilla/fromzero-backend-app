package com.jgerardo.fromzeroapi.profiles.application.internal.commandServices;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.profiles.domain.model.commands.*;
import com.jgerardo.fromzeroapi.profiles.domain.model.valueObjects.ProfileId;
import com.jgerardo.fromzeroapi.profiles.domain.services.ProfileCommandService;
import com.jgerardo.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories.CompanyRepository;
import com.jgerardo.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final DeveloperRepository developerRepository;
    private final CompanyRepository companyRepository;

    public ProfileCommandServiceImpl(DeveloperRepository developerRepository, CompanyRepository enterpriseRepository) {
        this.developerRepository = developerRepository;
        this.companyRepository = enterpriseRepository;
    }

    private Optional<Developer> findDeveloperByIdOrProfileId(String id) {
        try {
            var parsedId = Long.parseLong(id);
            return developerRepository.findById(parsedId);
        }catch (Exception e) {
            return developerRepository.findByProfileId(new ProfileId(id));
        }
    }

    private Optional<Company> findCompanyByIdOrProfileId(String id) {
        try {
            var parsedId = Long.parseLong(id);
            return companyRepository.findById(parsedId);
        }catch (Exception e) {
            return companyRepository.findByProfileId(new ProfileId(id));
        }
    }

    @Override
    public Optional<Developer> handle(UpdateDeveloperProjectsMetricSetCommand command) {
        var developer = this.findDeveloperByIdOrProfileId(command.developerId());
        if (developer.isEmpty())return Optional.empty();

        developer.get().updateDeveloperMetrics(command.rating());

        this.developerRepository.save(developer.get());
        return developer;
    }

    @Override
    public Optional<Developer> handle(UpdateDeveloperProfileCommand command) {
        var developer = this.findDeveloperByIdOrProfileId(command.id());
        if (developer.isEmpty())return Optional.empty();
        developer.get().setDescription(command.description());
        developer.get().setCountry(command.country());
        developer.get().setPhone(command.phone());
        developer.get().setSpecialties(command.specialties());
        developer.get().setProfileImgUrl(command.profileImgUrl());

        developerRepository.save(developer.get());

        return developer;
    }

    @Override
    public Optional<Company> handle(UpdateCompanyProfileCommand command) {
        var company = this.findCompanyByIdOrProfileId(command.id());
        if (company.isEmpty())return Optional.empty();
        company.get().setDescription(command.description());
        company.get().setCountry(command.country());
        company.get().setRuc(command.ruc());
        company.get().setPhone(command.phone());
        company.get().setWebsite(command.website());
        company.get().setProfileImgUrl(command.profileImgUrl());
        company.get().setSector(command.sector());

        companyRepository.save(company.get());

        return company;
    }

    @Override
    public void handle(CreateCompanyProfileCommand command) {
        var company = new Company(command);
        companyRepository.save(company);
    }

    @Override
    public void handle(CreateDeveloperProfileCommand command) {
        var developer = new Developer(command);
        developerRepository.save(developer);
    }

    @Override
    public void handle(DeleteDeveloperProfileCommand command) {
        var developer = developerRepository.findByUserId(command.userId());
        if (developer.isEmpty())return;
        developerRepository.delete(developer.get());
    }

    @Override
    public void handle(DeleteCompanyProfileCommand command) {
        var company = companyRepository.findByUserId(command.userId());
        if (company.isEmpty())return;
        companyRepository.delete(company.get());
    }
}
