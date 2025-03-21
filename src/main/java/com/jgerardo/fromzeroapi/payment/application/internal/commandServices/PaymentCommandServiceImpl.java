package com.jgerardo.fromzeroapi.payment.application.internal.commandServices;

import com.jgerardo.fromzeroapi.payment.application.internal.outboundservices.acl.ExternalProjectPaymentService;
import com.jgerardo.fromzeroapi.payment.domain.model.aggregates.Payment;
import com.jgerardo.fromzeroapi.payment.domain.model.commands.CompletePaymentCommand;
import com.jgerardo.fromzeroapi.payment.domain.model.commands.CreatePaymentCommand;
import com.jgerardo.fromzeroapi.payment.domain.model.valueObjects.PaymentStatus;
import com.jgerardo.fromzeroapi.payment.domain.services.PaymentCommandService;
import com.jgerardo.fromzeroapi.payment.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;
    private final ExternalProjectPaymentService externalProjectPaymentService;

    public PaymentCommandServiceImpl(
            PaymentRepository paymentRepository,
            ExternalProjectPaymentService externalProjectPaymentService
    ) {
        this.paymentRepository = paymentRepository;
        this.externalProjectPaymentService = externalProjectPaymentService;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {

        var project = externalProjectPaymentService.fetchProject(command.projectId());
        if (project.isEmpty()){
            return Optional.empty();
        }
        var payment = new Payment(project.get());
        paymentRepository.save(payment);
        return Optional.of(payment);
    }

    @Override
    public Optional<Payment> handle(CompletePaymentCommand command) {

        if (command.cardNumber().length()!=16 || command.cvv().length()!=3){
            return Optional.empty();
        }

        if(command.rating()<0 || command.rating()>5){
            return Optional.empty();
        }

        var project = externalProjectPaymentService.fetchProject(command.projectId());
        if (project.isEmpty()){
            return Optional.empty();
        }
        var payment = paymentRepository.findByProject(project.get());
        if (payment.isEmpty()){
            return Optional.empty();
        }
        payment.get().updateCard(command);
        payment.get().setStatus(PaymentStatus.COMPLETADO);

        payment.get().finishProject(payment.get().getProject().getId(),command.rating());

        paymentRepository.save(payment.get());
        return payment;
    }
}
