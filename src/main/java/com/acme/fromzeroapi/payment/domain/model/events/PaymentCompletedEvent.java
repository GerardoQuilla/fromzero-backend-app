package com.acme.fromzeroapi.payment.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class PaymentCompletedEvent extends ApplicationEvent {
    private Long projectId;
    private Double developerRating;
    public PaymentCompletedEvent(Object source, Long projectId, Double developerRating) {
        super(source);
        this.projectId = projectId;
        this.developerRating = developerRating;
    }
}
