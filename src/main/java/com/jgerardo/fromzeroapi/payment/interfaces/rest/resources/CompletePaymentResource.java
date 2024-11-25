package com.jgerardo.fromzeroapi.payment.interfaces.rest.resources;

import java.time.LocalDate;

public record CompletePaymentResource(
        String cardNumber,
        String expirationDate,
        String cvv
) {
}
