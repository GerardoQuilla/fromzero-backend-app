package com.jgerardo.fromzeroapi.profiles.domain.model.aggregates;

import com.jgerardo.fromzeroapi.profiles.domain.model.commands.CreateDeveloperProfileCommand;
import com.jgerardo.fromzeroapi.profiles.domain.model.valueObjects.DeveloperProjectsMetricSet;
import com.jgerardo.fromzeroapi.profiles.domain.model.valueObjects.ProfileId;
import com.jgerardo.fromzeroapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Developer extends AuditableAbstractAggregateRoot<Developer> {

    @Embedded
    private final ProfileId profileId;

    @NotBlank
    @Setter
    private String firstName;

    @NotBlank
    @Setter
    private String lastName;

    private String email;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String description = "No description provided.";
    @Setter
    private String country = "No country provided.";
    @Setter
    private String phone = "999 999 999";

    @Embedded
    private DeveloperProjectsMetricSet projectsMetricSet;

    @Setter
    private String specialties = "No specialties provided.";
    @Setter
    private String profileImgUrl = "https://cdn-icons-png.flaticon.com/512/3237/3237472.png";

    private Long userId;

    public Developer(
            String firstName,
            String lastName,
            String description,
            String country,
            String phone,
            String specialties,
            String profileImgUrl,
            Long userId
            ) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.country = country;
        this.phone = phone;
        this.specialties = specialties;
        this.profileImgUrl = profileImgUrl;
        this.userId = userId;
    }

    public Developer(CreateDeveloperProfileCommand command){
        this();
        this.firstName=command.firstName();
        this.lastName=command.lastName();
        this.email=command.email();
        this.description=command.description();
        this.country=command.country();
        this.phone=command.phone();
        this.specialties=command.specialties();
        this.profileImgUrl=command.profileImgUrl();
        this.userId=command.userId();
    }

    public Developer() {
        this.profileId = new ProfileId();
        this.projectsMetricSet = new DeveloperProjectsMetricSet();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Developer developer)) return false;
        return getId() != null && getId().equals(developer.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }


    public void updateDeveloperMetrics(Double rating){
        this.projectsMetricSet=this.projectsMetricSet.increment(rating);
    }

}
