package com.jgerardo.fromzeroapi.message.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.message.domain.model.commands.CreateChatCommand;
import com.jgerardo.fromzeroapi.message.interfaces.rest.resources.CreateChatCommandResource;

public class CreateChatCommandFromResourceAssembler {
    public static CreateChatCommand toCommandFromResource(CreateChatCommandResource resource) {
        return new CreateChatCommand(
                resource.developerId(),
                resource.companyId()
        );
    }
}
