package com.jgerardo.fromzeroapi.message.infrastructure.persistence.jpa.repositories;

import com.jgerardo.fromzeroapi.message.domain.model.aggregates.Chat;
import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByCompany(Company company);
    List<Chat> findAllByDeveloper(Developer developer);
    boolean existsByCompanyAndDeveloper(Company company, Developer developer);
}
