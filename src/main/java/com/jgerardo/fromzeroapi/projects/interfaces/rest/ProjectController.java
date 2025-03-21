package com.jgerardo.fromzeroapi.projects.interfaces.rest;

import com.jgerardo.fromzeroapi.projects.domain.model.commands.UpdateProjectCandidatesListCommand;
import com.jgerardo.fromzeroapi.projects.domain.model.queries.*;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectState;
import com.jgerardo.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.jgerardo.fromzeroapi.projects.domain.services.ProjectQueryService;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.resources.*;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api/projects", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Projects", description = "Projects Management Endpoints")
public class ProjectController {
    private final ProjectCommandService projectCommandService;
    private final ProjectQueryService projectQueryService;

    public ProjectController(ProjectCommandService projectCommandService,
                             ProjectQueryService projectQueryService) {
        this.projectCommandService = projectCommandService;
        this.projectQueryService = projectQueryService;
    }

    @Operation(summary = "Create project")
    @PostMapping
    public ResponseEntity<ProjectResource> createProject(@RequestBody CreateProjectResource resource) {
        var command = CreateProjectCommandFromResourceAssembler.toCommandFromResource(resource);
        var project = projectCommandService.handle(command);
        if (project.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var projectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(project.get());
        return new ResponseEntity<>(projectResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Projects")
    @GetMapping
    public ResponseEntity<List<ProjectResource>> getAllProjects() {
        var getAllProjectsQuery = new GetAllProjectsQuery();
        var projects = this.projectQueryService.handle(getAllProjectsQuery);
        var projectsResources = projects.stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsResources);
    }

    @Operation(summary = "Get All Project By State")
    @GetMapping(value = "/by-state")
    public ResponseEntity<List<ProjectResource>> getAllProjectsByState(@RequestParam ProjectState state) {
        var getAllProjectsByStateQuery = new GetAllProjectsByStateQuery(state);
        var projects = this.projectQueryService.handle(getAllProjectsByStateQuery);
        var projectsResources = projects.stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsResources);
    }

    @Operation(summary = "Get Project By Id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResource> getProjectById(@PathVariable Long id) {
        var getProjectByIdQuery = new GetProjectByIdQuery(id);
        var project = this.projectQueryService.handle(getProjectByIdQuery);
        if (project.isEmpty()) return ResponseEntity.badRequest().build();
        var projectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(project.get());
        return ResponseEntity.ok(projectResource);
    }

    @Operation(summary = "Add Candidate to Project")
    @PatchMapping(value = "/{projectId}/add-candidate")
    public ResponseEntity<UpdateProjectCandidatesListResource> updateProjectCandidatesList(
            @PathVariable Long projectId,
            @RequestBody SelectDeveloperResource resource) {

        var command = new UpdateProjectCandidatesListCommand(resource.developerId(), projectId);
        var project = this.projectCommandService.handle(command);
        if (project.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var projectResource = UpdatedProjectResourceFromEntityAssembler.toResourceFromEntity(project.get());
        return ResponseEntity.ok(projectResource);
    }

    @Operation(summary = "Set Developer to a Project")
    @PatchMapping(value = "/{projectId}/set-developer")
    public ResponseEntity<AssignProjectDeveloperResource> setProjectDeveloper(
            @PathVariable Long projectId,
            @RequestBody AcceptDeveloperResource resource) {

        var command = AcceptProjectDeveloperCommandFromResourceAssembler.toCommandFromResource(resource,projectId);
        var project = projectCommandService.handle(command);
        if (project.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var projectResource = AssignedProjectDeveloperResourceFromEntityAssembler.toResourceFromEntity(project.get());
        return ResponseEntity.ok(projectResource);
    }

    @Operation(summary = "Get All Projects By Developer Id")
    @GetMapping(value = "/developer/{developerId}")
    public ResponseEntity<List<ProjectResource>> getAllProjectsByDeveloperId(@PathVariable String developerId){
        var query = new GetAllProjectsByDeveloperIdQuery(developerId);
        var projects=this.projectQueryService.handle(query);
        var projectsResources = projects.stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsResources);
    }

    @Operation(summary = "Get All Projects By Company Id")
    @GetMapping(value = "/company/{companyId}")
    public ResponseEntity<List<ProjectResource>> getAllProjectsByEnterpriseId(@PathVariable String companyId){
        var query = new GetAllProjectsByCompanyIdQuery(companyId);
        var projects =this.projectQueryService.handle(query);
        var projectResources = projects.stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectResources);
    }

}
