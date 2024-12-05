package com.acme.fromzeroapi.message.interfaces.rest.resources;

import java.util.Date;

public record MessageResource(
        Long id,
        String senderId,
        String content,
        Long chatId,
        Date createdAt
) {
}
