package com.jgerardo.fromzeroapi.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String email, String token) {
}
