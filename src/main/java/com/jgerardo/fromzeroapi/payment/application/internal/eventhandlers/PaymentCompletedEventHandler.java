package com.jgerardo.fromzeroapi.payment.application.internal.eventhandlers;

import com.jgerardo.fromzeroapi.payment.application.internal.outboundservices.acl.ExternalProjectPaymentService;
import com.jgerardo.fromzeroapi.payment.domain.model.events.PaymentCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentCompletedEventHandler {
    private final ExternalProjectPaymentService externalProjectPaymentService;

    public PaymentCompletedEventHandler(ExternalProjectPaymentService externalProjectPaymentService) {
        this.externalProjectPaymentService = externalProjectPaymentService;
    }
    @EventListener(PaymentCompletedEvent.class)
    public void onPaymentCompleted(PaymentCompletedEvent event) {
        externalProjectPaymentService.updateProjectStatus(event.getProjectId(),event.getDeveloperRating());
    }
}
