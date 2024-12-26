package com.exa.hexagonal.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(title = "TODO CODE FINAL PROJECT - API REST",
                description = "This is my API REST",
                termsOfService = " Services and Terminus",
                version = "1.0.0",
                contact = @Contact(
                        name = "Mario Salazar",
                        email = "mariosalazar.ms.10@gmail.com",
                        url = "https://www.mssalazar.com"),
                license = @License(
                        name = "Standard Software Developer",
                        url = "myurl", identifier = "YBE2Y.MR5FR")),
        servers = {
                @Server(
                        description = "DEV Server",
                        url = "http://localhost:8080"),
                @Server(
                description = "PROD Server",
                url = "https://app-hexagonal-mariosalazar.onrender.com"),
               },
        security = @SecurityRequirement(
                name = "Security Token"
        )
)
@SecurityScheme(
        name = "Security Token",
        description = "Access Token for My App",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfiguration {

}
