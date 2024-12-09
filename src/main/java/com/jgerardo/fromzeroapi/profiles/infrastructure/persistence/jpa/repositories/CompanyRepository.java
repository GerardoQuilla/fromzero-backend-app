package com.jgerardo.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.profiles.domain.model.valueObjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    //Optional<Company> findEnterpriseByUserUserId(Long userId);
    Optional<Company> findByEmail(String email);
    Optional<Company> findByProfileId(ProfileId profileId);
}
