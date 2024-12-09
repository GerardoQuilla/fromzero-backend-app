package com.jgerardo.fromzeroapi.iam.domain.services;

import com.jgerardo.fromzeroapi.iam.domain.model.aggregates.User;
import com.jgerardo.fromzeroapi.iam.domain.model.queries.GetAllUsersQuery;
import com.jgerardo.fromzeroapi.iam.domain.model.queries.GetUserByEmailQuery;
import com.jgerardo.fromzeroapi.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByEmailQuery query);

}
