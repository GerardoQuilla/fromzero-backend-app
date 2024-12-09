package com.jgerardo.fromzeroapi;

import com.jgerardo.fromzeroapi.profiles.domain.model.valueObjects.DeveloperProjectsMetricSet;
import org.junit.Assert;
import org.junit.Test;

public class DeveloperProjectsMetricsSetTests {
    @Test
    public void testIncrement(){
        // Arrange
        DeveloperProjectsMetricSet metrics = new DeveloperProjectsMetricSet();

        // Act
        double currenRating = 5d;
        System.out.println("Current Rating 1 is "+currenRating);
        metrics = metrics.increment(currenRating);

        currenRating = 4d;
        System.out.println("Current Rating 2 is "+currenRating);
        metrics = metrics.increment(currenRating);

        currenRating = 4d;
        System.out.println("Current Rating 3 is "+currenRating);

        metrics = metrics.increment(currenRating);

        currenRating = 0d;
        System.out.println("Current Rating 4 is "+currenRating);
        metrics = metrics.increment(currenRating);

        //Assert
        Assert.assertEquals(4,metrics.completedProjects().intValue());
        Assert.assertEquals(3.25, metrics.rating(), 0.01);
        System.out.println("Current average rating is "+metrics.rating());

    }
}
