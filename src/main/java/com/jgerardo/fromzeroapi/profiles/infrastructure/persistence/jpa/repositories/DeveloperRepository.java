package com.jgerardo.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.profiles.domain.model.valueObjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByEmail(String email);
    Optional<Developer> findByProfileId(ProfileId profileId);
    Optional<Developer> findByUserId(Long userId);
}
