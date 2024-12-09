package com.jgerardo.fromzeroapi.payment.interfaces.rest.resources;

import com.jgerardo.fromzeroapi.payment.domain.model.valueObjects.PaymentStatus;
import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;

public record PaymentResource(
        Long id,
        Long developerId,
        Project project,
        String amount,
        PaymentStatus status,
        String createdAt,
        String completedAt
){

}
