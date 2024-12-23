package com.jgerardo.fromzeroapi.iam.interfaces.rest;

import com.jgerardo.fromzeroapi.iam.domain.model.queries.GetAllUsersQuery;
import com.jgerardo.fromzeroapi.iam.domain.model.queries.GetUserByEmailQuery;
import com.jgerardo.fromzeroapi.iam.domain.model.queries.GetUserByIdQuery;
import com.jgerardo.fromzeroapi.iam.domain.services.UserQueryService;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.resources.UserResource;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

    private final UserQueryService userQueryService;

    public UsersController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "Get user id by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<Long> getUserIdByEmail(@PathVariable String email) {
        var query=new GetUserByEmailQuery(email);
        var user = this.userQueryService.handle(query);
        if(user.isEmpty())return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.get().getId());
    }

    /**
     * This method returns all the users.
     * @return a list of user resources
     * @see UserResource
     */
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    /**
     * This method returns the user with the given id.
     * @param userId the user id
     * @return the user resource with the given id
     * @throws RuntimeException if the user is not found
     * @see UserResource
     */
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

}
