package com.jgerardo.fromzeroapi.message.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.message.domain.model.entities.Message;
import com.jgerardo.fromzeroapi.message.interfaces.rest.resources.MessageResource;

public class MessageResourceFromEntityAssembler {

    public static MessageResource toResourceFromEntity(Message entity) {
        return new MessageResource(
                entity.getId(),
                entity.getSenderId(),
                entity.getContent(),
                entity.getChat().getId(),
                entity.getCreatedAt()
        );
    }
}
