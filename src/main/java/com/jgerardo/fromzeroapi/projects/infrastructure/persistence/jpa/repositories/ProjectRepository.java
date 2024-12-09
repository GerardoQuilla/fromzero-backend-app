package com.jgerardo.fromzeroapi.projects.infrastructure.persistence.jpa.repositories;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findAllByState(ProjectState state);
    List<Project> findAllByDeveloper(Developer developer);
    List<Project> findAllByCompany(Company company);
}
