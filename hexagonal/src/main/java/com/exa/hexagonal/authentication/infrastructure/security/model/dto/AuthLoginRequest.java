package com.exa.hexagonal.authentication.infrastructure.security.model.dto;

public record AuthLoginRequest(String email, String password) {
}
