package com.jgerardo.fromzeroapi.projects.application.internal.eventHandlers;

import com.jgerardo.fromzeroapi.projects.application.internal.outboundServices.acl.ExternalPaymentProjectService;
import com.jgerardo.fromzeroapi.projects.domain.model.events.SetProjectPaymentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SetProjectPaymentEventHandler {
    private final ExternalPaymentProjectService externalPaymentProjectService;

    public SetProjectPaymentEventHandler(ExternalPaymentProjectService externalPaymentProjectService) {
        this.externalPaymentProjectService = externalPaymentProjectService;
    }

    @EventListener(SetProjectPaymentEvent.class)
    public void createPayment(SetProjectPaymentEvent event) {
        externalPaymentProjectService.createProjectPayment(event.getProjectId());
    }
}
