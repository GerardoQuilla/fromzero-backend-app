package com.jgerardo.fromzeroapi.iam.interfaces.rest;

import com.jgerardo.fromzeroapi.iam.domain.model.commands.ForgotPasswordCommand;
import com.jgerardo.fromzeroapi.iam.domain.model.commands.ResetPasswordCommand;
import com.jgerardo.fromzeroapi.iam.domain.services.UserCommandService;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.resources.*;
import com.jgerardo.fromzeroapi.iam.interfaces.rest.transform.*;
import com.jgerardo.fromzeroapi.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/auth")
@Tag(name = "Auth", description = "Operations related to users")
public class AuthController {
    private final UserCommandService userCommandService;

    public AuthController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @Operation(summary = "Create Developer")
    @PostMapping("/register-developer")
    public ResponseEntity<UserResource> createDeveloper(@RequestBody SignUpDeveloperResource resource) {
        var command = DeveloperCommandFromSignUpDeveloperResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(command);

        if (user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
    @Operation(summary = "Create Company")
    @PostMapping("/register-company")
    public ResponseEntity<UserResource> createCompany(@RequestBody SignUpCompanyResource resource) {
        var registerCommand = CompanyCommandFromSignUpCompanyResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(registerCommand);

        if (user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
    @Operation(summary = "sign in")
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource){
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);

        if(authenticatedUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var authenticatedUserResource = AuthenticatedUsedResourcerFromEntityAssembler.toResourceFromEntity(
                authenticatedUser.get().getLeft(),authenticatedUser.get().getRight());

        return ResponseEntity.ok(authenticatedUserResource);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<MessageResource> forgotPassword(@RequestBody ForgotPasswordResource resource){
        userCommandService.handle(new ForgotPasswordCommand(resource.email()));
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResource("Your reset password request was sent"));
    }

    @PutMapping("/reset-password")
    public ResponseEntity<MessageResource> changePassword(@RequestBody ResetPasswordResource resource, HttpServletRequest request) {
        var username = request.getAttribute("resetPasswordUsername");
        if(username==null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResource("Token inválido o expirado"));
        userCommandService.handle(new ResetPasswordCommand(username.toString(),resource.newPassword()));
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResource("Password reset successful"));
    }
}
