package com.jgerardo.fromzeroapi.payment.application.internal.outboundservices.acl;

import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import com.jgerardo.fromzeroapi.projects.interfaces.acl.ProjectContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProjectPaymentService {
    private final ProjectContextFacade projectContextFacade;

    public ExternalProjectPaymentService(ProjectContextFacade projectContextFacade) {
        this.projectContextFacade = projectContextFacade;
    }

    public Optional<Project> fetchProject(Long projectId) {
        return projectContextFacade.getProjectById(projectId);
    }

    public void updateProjectStatus(Long projectId){
        projectContextFacade.finishProject(projectId);
    }
}
