package com.jgerardo.fromzeroapi.projects.interfaces.rest;

import com.jgerardo.fromzeroapi.projects.domain.model.commands.UpdateDeliverableStatusCommand;
import com.jgerardo.fromzeroapi.projects.domain.model.commands.UpdateDeveloperMessageCommand;
import com.jgerardo.fromzeroapi.projects.domain.model.queries.GetAllDeliverablesByProjectIdQuery;
import com.jgerardo.fromzeroapi.projects.domain.model.queries.GetDeliverableByIdQuery;
import com.jgerardo.fromzeroapi.projects.domain.services.DeliverableCommandService;
import com.jgerardo.fromzeroapi.projects.domain.services.DeliverableQueryService;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.resources.AcceptDeliverableResource;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.resources.CreateDeliverableResource;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.resources.DeliverableResource;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.transform.CreateDeliverableCommandFromResourceAssembler;
import com.jgerardo.fromzeroapi.projects.interfaces.rest.transform.DeliverableResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api/deliverables", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Deliverables", description = "Deliverables Management Endpoints")
public class DeliverableController {
    private final DeliverableCommandService deliverableCommandService;
    private final DeliverableQueryService deliverableQueryService;

    public DeliverableController(
            DeliverableCommandService deliverableCommandService,
            DeliverableQueryService deliverableQueryService) {
        this.deliverableCommandService = deliverableCommandService;
        this.deliverableQueryService = deliverableQueryService;
    }

    @Operation(summary = "Create deliverable")
    @PostMapping
    public ResponseEntity<DeliverableResource> createDeliverable(@RequestBody CreateDeliverableResource resource){
        var command = CreateDeliverableCommandFromResourceAssembler.toCommandFromResource(resource);
        var deliverable = deliverableCommandService.handle(command);
        if (deliverable.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return new ResponseEntity<>(deliverableResource, HttpStatus.CREATED);
    }

    @GetMapping(value = "/project/{projectId}")
    @Operation(summary = "Get All Deliverables By Project Id")
    public ResponseEntity<List<DeliverableResource>> getAllDeliverablesByProjectId(@PathVariable Long projectId) {

        var query = new GetAllDeliverablesByProjectIdQuery(projectId);
        var deliverables = this.deliverableQueryService.handle(query);
        var deliverablesResources = deliverables.stream()
                .map(DeliverableResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(deliverablesResources);
    }

    @GetMapping(value = "/{deliverableId}")
    @Operation(summary = "Get Deliverable By Id")
    public ResponseEntity<DeliverableResource> getDeliverableById(@PathVariable Long deliverableId) {
        var query = new GetDeliverableByIdQuery(deliverableId);
        var deliverable = this.deliverableQueryService.handle(query);
        if (deliverable.isEmpty()) return ResponseEntity.badRequest().build();
        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return ResponseEntity.ok(deliverableResource);
    }

    @Operation(summary = "Send Deliverable")
    @PatchMapping(
            value = "/{deliverableId}/send",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DeliverableResource> sendDeliverable(
            @PathVariable Long deliverableId,
            @RequestPart(value = "developerMessage") String developerMessage,
            @RequestPart(value = "files",required = false) List<MultipartFile> files) {

        var updateDeveloperMessageCommand = new UpdateDeveloperMessageCommand(deliverableId, developerMessage,files);
        var deliverable = this.deliverableCommandService.handle(updateDeveloperMessageCommand);
        if (deliverable.isEmpty()) return ResponseEntity.badRequest().build();
        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return ResponseEntity.ok(deliverableResource);
    }

    @Operation(summary = "Review Deliverable")
    @PatchMapping(value = "/{deliverableId}/review")
    public ResponseEntity<DeliverableResource> reviewDeliverable(
            @PathVariable Long deliverableId,
            @RequestBody AcceptDeliverableResource resource) {

        var updateDeliverableStatusCommand = new UpdateDeliverableStatusCommand(deliverableId, resource.accepted());
        var deliverable = this.deliverableCommandService.handle(updateDeliverableStatusCommand);
        if (deliverable.isEmpty()) return ResponseEntity.badRequest().build();
        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return ResponseEntity.ok(deliverableResource);
    }
}
