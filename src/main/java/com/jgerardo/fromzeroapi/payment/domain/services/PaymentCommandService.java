package com.jgerardo.fromzeroapi.payment.domain.services;

import com.jgerardo.fromzeroapi.payment.domain.model.aggregates.Payment;
import com.jgerardo.fromzeroapi.payment.domain.model.commands.CompletePaymentCommand;
import com.jgerardo.fromzeroapi.payment.domain.model.commands.CreatePaymentCommand;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Payment> handle(CreatePaymentCommand command);
    Optional<Payment> handle(CompletePaymentCommand command);
}
