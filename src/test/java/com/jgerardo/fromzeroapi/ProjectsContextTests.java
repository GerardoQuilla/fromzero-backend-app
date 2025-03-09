package com.jgerardo.fromzeroapi;

import com.jgerardo.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.jgerardo.fromzeroapi.profiles.domain.services.ProfileCommandService;
import com.jgerardo.fromzeroapi.profiles.domain.services.ProfileQueryService;
import com.jgerardo.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories.CompanyRepository;
import com.jgerardo.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import com.jgerardo.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
import com.jgerardo.fromzeroapi.projects.application.internal.commandServices.ProjectCommandServiceImpl;
import com.jgerardo.fromzeroapi.projects.application.internal.outboundServices.acl.ExternalProfileProjectService;
import com.jgerardo.fromzeroapi.projects.domain.model.aggregates.Project;
import com.jgerardo.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.jgerardo.fromzeroapi.projects.domain.model.events.CreateDefaultDeliverablesEvent;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectCurrency;
import com.jgerardo.fromzeroapi.projects.domain.model.valueObjects.ProjectType;
import com.jgerardo.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.jgerardo.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class) //Junit 5
@RunWith(MockitoJUnitRunner.class) //Junit 4
public class ProjectsContextTests {
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ExternalProfileProjectService externalProfileProjectService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private ProjectCommandServiceImpl projectCommandService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void projectCommandServiceCreateProjectTest(){
        // Arrange
        Company company = new Company(
                "Empresa 1",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                1L
        );
        CreateProjectCommand command = new CreateProjectCommand(
          "Proyecto 1",
          "Descripcion 1",
          "1",
          Collections.emptySet(),
          Collections.emptySet(),
          ProjectType.LANDING_PAGE,
          2000.0,
          ProjectCurrency.PEN,
          ""
        );

        Project createdProject = new Project(command,company);

        when(externalProfileProjectService.getCompanyById("1")).thenReturn(Optional.of(company));
        when(projectRepository.save(Mockito.any(Project.class))).thenReturn(createdProject);

        // Act
        var result = projectCommandService.handle(command);

        // Assert
        Assertions.assertNotNull(result);
        //verify(applicationEventPublisher,times(1)).publishEvent(any(CreateDefaultDeliverablesEvent.class));
        /*ArgumentCaptor<CreateDefaultDeliverablesEvent> eventCaptor = ArgumentCaptor.forClass(CreateDefaultDeliverablesEvent.class);

        // Verificar que se haya publicado el evento esperado
        verify(applicationEventPublisher, times(1)).publishEvent(eventCaptor.capture());

        // Verifica que el evento capturado sea del tipo esperado
        CreateDefaultDeliverablesEvent capturedEvent = eventCaptor.getValue();
        Assertions.assertNotNull(capturedEvent);
        Assertions.assertEquals(createdProject.getId(), capturedEvent.getProjectId());  // Verifica el ID del proyecto
        Assertions.assertEquals(createdProject.getType(), capturedEvent.getProjectType());  // Verifica el tipo de proyecto*/
        // Verificar que se publica el evento correcto desde la lista de eventos
    }
}
