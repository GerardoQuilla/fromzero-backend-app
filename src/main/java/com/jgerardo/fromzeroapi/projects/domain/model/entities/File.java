package com.jgerardo.fromzeroapi.projects.domain.model.entities;

import com.jgerardo.fromzeroapi.projects.domain.model.commands.CreateDeliverableFileCommand;
import com.jgerardo.fromzeroapi.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class File extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String url;

    public File(CreateDeliverableFileCommand command) {
        this.name=command.name();
        this.url=command.url();
    }


}
