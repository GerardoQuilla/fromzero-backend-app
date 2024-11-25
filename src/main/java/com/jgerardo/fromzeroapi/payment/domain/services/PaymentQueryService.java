package com.jgerardo.fromzeroapi.payment.domain.services;

import com.jgerardo.fromzeroapi.payment.domain.model.aggregates.Payment;
import com.jgerardo.fromzeroapi.payment.domain.model.queries.GetPaymentByProjectIdQuery;
import com.jgerardo.fromzeroapi.payment.domain.model.queries.GetPaymentsByDeveloperIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByProjectIdQuery query);
    List<Payment> handle(GetPaymentsByDeveloperIdQuery query);
}
