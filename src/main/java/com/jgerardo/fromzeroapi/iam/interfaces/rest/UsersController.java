package com.jgerardo.fromzeroapi.iam.interfaces.rest;

import com.jgerardo.fromzeroapi.iam.domain.model.commands.DeleteUserCommand;
import com.jgerardo.fromzeroapi.iam.domain.model.queries.GetAllUsersQuery;
import com.jgerardo.fromzeroapi.iam.domain.model.queries.GetUserByEmailQuery;
import com.jgerardo.fromzeroapi.iam.domain.model.queries.GetUserByIdQuery;
import com.jgerardo.fromzeroapi.iam.domain.services.UserCommandService;
import com.jgerardo.fromzeroapi.iam.domain.services.UserQueryService;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.resources.UserResource;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.jgerardo.fromzeroapi.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UsersController(
            UserQueryService userQueryService,
            UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
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

    @DeleteMapping("/{userId}")
    public ResponseEntity<MessageResource> deleteUserById(@PathVariable Long userId) {
        userCommandService.handle(new DeleteUserCommand(userId));
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResource("User deleted successfully"));
    }

}
