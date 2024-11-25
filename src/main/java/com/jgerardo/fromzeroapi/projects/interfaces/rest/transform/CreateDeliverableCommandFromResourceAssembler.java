package com.jgerardo.fromzeroapi.projects.interfaces.rest.transform;

import com.jgerardo.fromzeroapi.projects.domain.model.commands.CreateDeliverableCommand;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.resources.CreateDeliverableResource;

public class CreateDeliverableCommandFromResourceAssembler {
    public static CreateDeliverableCommand toCommandFromResource(CreateDeliverableResource resource/*, List<MultipartFile> files*/){
        //var safeFiles = files!=null?files:new ArrayList<MultipartFile>();
        return new CreateDeliverableCommand(
                resource.name(),
                resource.description(),
                resource.date(),
                resource.projectId()
                //safeFiles
        );
    }
}
