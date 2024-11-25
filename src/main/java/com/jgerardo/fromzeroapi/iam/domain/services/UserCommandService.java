package com.jgerardo.fromzeroapi.iam.domain.services;

import com.jgerardo.fromzeroapi.iam.domain.model.aggregates.User;
import com.jgerardo.fromzeroapi.iam.domain.model.commands.SignInCommand;
import com.jgerardo.fromzeroapi.iam.domain.model.commands.SignUpDeveloperCommand;
import com.jgerardo.fromzeroapi.iam.domain.model.commands.SignUpCompanyCommand;
import com.jgerardo.fromzeroapi.iam.domain.model.commands.SignUpSupportCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpDeveloperCommand command);
    Optional<User> handle(SignUpCompanyCommand command);
    Optional<User> handle(SignUpSupportCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}
