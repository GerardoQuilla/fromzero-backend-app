package com.jgerardo.fromzeroapi.payment.domain.model.commands;

import java.time.LocalDate;

public record CompletePaymentCommand(
        Long projectId,
        String cardNumber,
        LocalDate expirationDate,
        String cvv,
        Double rating
) {
}
