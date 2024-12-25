package com.exa.hexagonal.authentication.domain.model;

public record AuthLoginRequest(String email, String password) {
}
