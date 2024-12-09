package com.jgerardo.fromzeroapi.projects.infrastructure.persistence.jpa.repositories;

import com.jgerardo.fromzeroapi.projects.domain.model.entities.Deliverable;
import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {
    List<Deliverable> findAllByProject(Project project);
}
