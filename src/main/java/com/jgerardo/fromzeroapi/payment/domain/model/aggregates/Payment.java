package com.jgerardo.fromzeroapi.payment.domain.model.aggregates;

import com.jgerardo.fromzeroapi.payment.domain.model.commands.CompletePaymentCommand;
import com.jgerardo.fromzeroapi.payment.domain.model.events.PaymentCompletedEvent;
import com.jgerardo.fromzeroapi.payment.domain.model.valueObjects.Card;
import com.jgerardo.fromzeroapi.payment.domain.model.valueObjects.Currency;
import com.jgerardo.fromzeroapi.payment.domain.model.valueObjects.PaymentAmount;
import com.jgerardo.fromzeroapi.payment.domain.model.valueObjects.PaymentStatus;
import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import com.jgerardo.fromzeroapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
public class Payment extends AuditableAbstractAggregateRoot<Payment> {
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "project_id", unique = true, nullable = false
    )
    private Project project;

    @Embedded
    private PaymentAmount amount;

    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Embedded
    private Card card;

    public Payment(Project project) {
        this.developer = project.getDeveloper();
        this.project = project;
        this.amount=new PaymentAmount(
                project.getBudget().budget()
                , Currency.valueOf(project.getBudget().currency().toString())
        );
        this.status = PaymentStatus.PENDIENTE;
        this.card = new Card();
    }
    public void updateCard(CompletePaymentCommand command){
        this.card=new Card(command.cardNumber(),command.expirationDate(),command.cvv());
    }
    public void finishProject(Long projectId, Double developerRating){
        this.registerEvent(new PaymentCompletedEvent(this,projectId,developerRating));
    }
}
