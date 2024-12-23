package com.jgerardo.fromzeroapi.projects.domain.model.aggregates;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.jgerardo.fromzeroapi.projects.domain.model.events.CreateDefaultDeliverablesEvent;
import com.jgerardo.fromzeroapi.projects.domain.model.events.CreateDeliverablesByMethodologiesEvent;
import com.jgerardo.fromzeroapi.projects.domain.model.events.SetProjectPaymentEvent;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.*;
import com.jgerardo.fromzeroapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Project extends AuditableAbstractAggregateRoot<Project> {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Setter
    @Enumerated(EnumType.STRING)
    private ProjectState state;

    @Setter
    private Double progress;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Setter
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_candidates",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private Set<Developer> candidates = new HashSet<>();

    @ElementCollection(targetClass = Frameworks.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "project_frameworks",
            joinColumns = @JoinColumn(name = "project_id")
    )
    @Enumerated(EnumType.STRING)
    private Set<Frameworks> frameworks = new HashSet<>();

    @ElementCollection(targetClass = ProgrammingLanguages.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "project_programming_languages",
            joinColumns = @JoinColumn(name = "project_id")
    )
    @Enumerated(EnumType.STRING)
    private Set<ProgrammingLanguages> languages = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectType type;

    @Embedded
    @Column(nullable = false)
    private ProjectBudget budget;

    public Project(CreateProjectCommand command, Company company) {
        this.name = command.name();
        this.description = command.description();
        this.state = ProjectState.EN_BUSQUEDA;
        this.progress = 0.0;
        this.company = company;
        this.frameworks = command.frameworks();
        this.languages = command.languages();
        this.developer = null;
        this.type = command.type();
        this.budget=new ProjectBudget(command.budget(),command.currency());
    }

    public Project() {

    }

    public void createDefaultDeliverables(Long projectId,ProjectType type){
        this.registerEvent(new CreateDefaultDeliverablesEvent(this,projectId,type));
    }

    @Override
    protected Collection<Object> domainEvents() {
        return super.domainEvents();
    }

    public Collection<Object> getDomainEvents(){
        return this.domainEvents();
    }

    public void setProjectPayment(Long projectId) {
        this.registerEvent(new SetProjectPaymentEvent(this,projectId));
    }
    public void createDeliverablesByMethodologies(Long projectId,String methodologies){
        this.registerEvent(new CreateDeliverablesByMethodologiesEvent(this,projectId,methodologies));
    }
}
