package com.jgerardo.fromzeroapi.message.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.message.domain.model.aggregates.Chat;
import com.jgerardo.fromzeroapi.message.interfaces.rest.resources.ChatResource;

public class ChatResourceFromEntityAssembler {
    public static ChatResource toResourceFromEntity(Chat entity) {
        return new ChatResource(
                entity.getId(),
                entity.getDeveloper(),
                entity.getCompany(),
                /*entity.getDeveloper().getProfileId().RecordId(),
                entity.getCompany().getProfileId().RecordId(),*/
                entity.getCreatedAt()
        );
    }
}
