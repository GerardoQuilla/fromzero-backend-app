package com.acme.fromzeroapi.profiles.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
public record DeveloperProjectsMetricSet(Integer completedProjects, Double rating) {
    public DeveloperProjectsMetricSet(){
        this(0, 0.00d);
    }
    public DeveloperProjectsMetricSet increment(double currentRating) {

        double newAverageRating = ((this.completedProjects*this.rating)+currentRating)/(this.completedProjects + 1);

        BigDecimal roundedAverage = BigDecimal.valueOf(newAverageRating).setScale(2, RoundingMode.HALF_UP);

        return new DeveloperProjectsMetricSet(this.completedProjects+1, roundedAverage.doubleValue());
    }

}
