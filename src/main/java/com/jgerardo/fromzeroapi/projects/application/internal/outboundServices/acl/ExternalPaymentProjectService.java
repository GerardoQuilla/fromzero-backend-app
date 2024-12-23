package com.jgerardo.fromzeroapi.projects.application.internal.outboundServices.acl;

import com.jgerardo.fromzeroapi.payment.domain.model.aggregates.Payment;
import com.jgerardo.fromzeroapi.payment.interfaces.acl.PaymentContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalPaymentProjectService {
    private final PaymentContextFacade paymentContextFacade;

    public ExternalPaymentProjectService(PaymentContextFacade paymentContextFacade) {
        this.paymentContextFacade = paymentContextFacade;
    }

    public Optional<Payment> createProjectPayment(Long projectId) {
        return paymentContextFacade.createPayment(projectId);
    }
}
