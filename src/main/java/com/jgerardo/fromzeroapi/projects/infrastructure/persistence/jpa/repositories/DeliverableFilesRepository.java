package com.jgerardo.fromzeroapi.projects.infrastructure.persistence.jpa.repositories;

import com.jgerardo.fromzeroapi.projects.domain.model.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverableFilesRepository extends JpaRepository<File,Integer> {
}
