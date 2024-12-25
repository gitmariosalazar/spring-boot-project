
package com.exa.hexagonal.authentication.infrastructure.controllers;

import com.exa.hexagonal.authentication.infrastructure.security.model.dto.AuthLoginRequest;
import com.exa.hexagonal.authentication.infrastructure.security.model.dto.AuthResponse;
import com.exa.hexagonal.authentication.infrastructure.security.service.implementation.UserDetailServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/auth")
@Tag(name = "authentication-controller", description = "Authentication Controller")
public class AuthenticationController {

    private final UserDetailServiceImpl userDetailService;

    public AuthenticationController(UserDetailServiceImpl userDetailService){
        this.userDetailService=userDetailService;
    }

    @Operation(
            summary = "Login User",
            description = "Authenticate a user and return the authentication token along with user details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Authentication request with username and password",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthLoginRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Default Example",
                                            summary = "Example of user login",
                                            description = "Provide username and password for login.",
                                            value = "{ \"email\": \"mariosalazar.ms.10@gmail.com\", \"password\": \"password-mario\" }"
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful authentication",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Success Response",
                                                    summary = "Token and user details",
                                                    description = "Response includes the JWT token and user info.",
                                                    value = "{\"email\": \"mariosalazar.ms.10@gmail.com\", \"message\": \"User logged successfully\", \"status\": \"true\", \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\" }"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized - Invalid credentials",
                            content = @Content(
                                    examples = {
                                            @ExampleObject(
                                                    name = "Unauthorized Response",
                                                    summary = "Invalid credentials",
                                                    description = "Occurs when the username or password is incorrect.",
                                                    value = "{ \"error\": \"Unauthorized\", \"message\": \"Invalid username or password\" }"
                                            )
                                    }
                            )
                    )
            }
    )
    @PostMapping("/get-token")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest userRequest) {
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }
}
