package com.jgerardo.fromzeroapi.projects.domain.model.entities;

import com.jgerardo.fromzeroapi.projects.domain.model.commands.CreateDeliverableCommand;
import com.jgerardo.fromzeroapi.projects.domain.model.events.UpdateProjectProgressEvent;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.DeliverableState;
import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import com.jgerardo.fromzeroapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Entity
public class Deliverable extends AuditableAbstractAggregateRoot<Deliverable> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliverableState state;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String developerMessage;

    @Setter
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "deliverable_id")
    private List<File> files;

    public Deliverable(CreateDeliverableCommand command, Project project/*,List<File> files*/) {
        this.name=command.name();
        this.description=command.description();
        this.date=command.date();
        this.state=DeliverableState.PENDIENTE;
        this.developerMessage=null;
        this.project=project;
        this.files=new ArrayList<>();

    }

    public Deliverable() {

    }

    @Override
    protected Collection<Object> domainEvents() {
        return super.domainEvents();
    }

    public Collection<Object> getDomainEvents(){
        return domainEvents();
    }

    public void updateProjectProgress(Long projectId, Long completedDeliverables, Integer totalDeliverables){
        this.registerEvent(new UpdateProjectProgressEvent(this,projectId,completedDeliverables,totalDeliverables));
    }

}
