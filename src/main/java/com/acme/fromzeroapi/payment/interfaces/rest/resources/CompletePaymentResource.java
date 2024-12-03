package com.acme.fromzeroapi.payment.interfaces.rest.resources;

public record CompletePaymentResource(
        String cardNumber,
        String expirationDate,
        String cvv,
        Double rating
) {
}
