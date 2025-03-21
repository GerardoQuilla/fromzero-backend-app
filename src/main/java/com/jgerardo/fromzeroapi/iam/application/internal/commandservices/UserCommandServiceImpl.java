package com.jgerardo.fromzeroapi.iam.application.internal.commandservices;

import com.jgerardo.fromzeroapi.iam.application.internal.outboundservices.acl.EmailService;
import com.jgerardo.fromzeroapi.iam.application.internal.outboundservices.acl.ExternalProfileService;
import com.jgerardo.fromzeroapi.iam.application.internal.outboundservices.hashing.HashingService;
import com.jgerardo.fromzeroapi.iam.application.internal.outboundservices.tokens.TokenService;
import com.jgerardo.fromzeroapi.iam.domain.exceptions.IncorrectPasswordException;
import com.jgerardo.fromzeroapi.iam.domain.exceptions.UserAlreadyExistsException;
import com.jgerardo.fromzeroapi.iam.domain.exceptions.UserNotFoundException;
import com.jgerardo.fromzeroapi.iam.domain.model.aggregates.User;
import com.jgerardo.fromzeroapi.iam.domain.model.commands.*;
import com.jgerardo.fromzeroapi.iam.domain.model.entities.Role;
import com.jgerardo.fromzeroapi.iam.domain.model.valueobjects.Roles;
import com.jgerardo.fromzeroapi.iam.domain.services.UserCommandService;
import com.jgerardo.fromzeroapi.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.jgerardo.fromzeroapi.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ExternalProfileService externalProfileService;
    private final TokenService tokenService;
    private final HashingService hashingService;

    private final EmailService emailService;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                                  ExternalProfileService externalProfileService,
                                  TokenService tokenService,
                                  HashingService hashingService,
                                  EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.externalProfileService = externalProfileService;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
        this.emailService = emailService;
    }

    private User createUser(String email, String password, String auxR) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new UserAlreadyExistsException("User already exists");
        });

        var roles = new ArrayList<Role>();
        Roles roleEnum;
        try {
            roleEnum = Roles.valueOf(auxR);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + auxR, e);
        }

        var storedRole = roleRepository.findByName(roleEnum)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + auxR));
        roles.add(storedRole);

        var user = new User(email, hashingService.encode(password), roles);
        return userRepository.save(user);
    }


    @Override
    public Optional<User> handle(SignUpDeveloperCommand command) {

        var user = createUser(command.email(), command.password(), "DEVELOPER");

        externalProfileService.createDeveloperProfile(
                command.firstName(),
                command.lastName(),
                command.email(),
                user.getId()
        );

        return Optional.of(user);
    }

    @Override
    public Optional<User> handle(SignUpCompanyCommand command) {

        var user = createUser(command.email(), command.password(), "COMPANY");

        externalProfileService.createCompanyProfile(
                command.companyName(),
                command.email(),
                user.getId()
        );

        return Optional.of(user);
    }

    /*@Override
    public Optional<User> handle(SignUpSupportCommand command) {
        String email = command.email();

        if (!email.equals("fromzero@support.com")) {
            throw new IllegalArgumentException("Invalid email. Support accounts can only be created for 'fromzero@support.com'");
        }

        userRepository.findByEmail(email).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        });

        var user = createUser(command.email(), command.password(), "SUPPORT");

        return Optional.of(user);
    }*/

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty()) {
            //throw new IllegalArgumentException("User not found");
            throw new UserNotFoundException("User not found");
        }
        if (!hashingService.matches(command.password(), user.get().getPassword())) {

            //throw new IllegalArgumentException("Invalid password");
            throw new IncorrectPasswordException("Incorrect password");
        }
        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public void handle(ForgotPasswordCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        var resetPasswordToken = tokenService.generateResetPasswordToken(command.email());
        emailService.sendEmail(command.email(),resetPasswordToken);
    }

    @Override
    public void handle(ResetPasswordCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        user.get().setEncryptedPassword(hashingService.encode(command.newPassword()));
        userRepository.save(user.get());
    }

    @Override
    public void handle(DeleteUserCommand command) {
        var user = userRepository.findById(command.id());
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        user.get().getRoles().stream().forEach(role -> {
            switch (role.getName()){
                case Roles.COMPANY -> externalProfileService.deleteCompanyProfile(command.id());
                case Roles.DEVELOPER -> externalProfileService.deleteDeveloperProfile(command.id());
            }
        });
        userRepository.delete(user.get());

    }
}

