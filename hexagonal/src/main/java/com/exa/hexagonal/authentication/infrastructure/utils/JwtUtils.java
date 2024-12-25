package com.exa.hexagonal.authentication.infrastructure.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exa.hexagonal.authentication.infrastructure.repositories.repository.JPARepositoryUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    private final JPARepositoryUser jpaRepository;

    public JwtUtils(JPARepositoryUser jpaRepository){
        this.jpaRepository=jpaRepository;
    }

    public String createToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        String email = authentication.getPrincipal().toString();
        var user = jpaRepository.findByEmail(email);
        String fullname = user.get().getFirstname()+" "+user.get().getLastname();
        String phone = user.get().getPhone();
        String address = user.get().getAddress();
        Long id = user.get().getId();
        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(email)
                .withClaim("authorities", authorities)
                .withClaim("email", email)
                .withClaim("address", address)
                .withClaim("fullname", fullname)
                .withClaim("id", id)
                .withClaim("phone", phone)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+28800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verification = JWT.require(algorithm)
                    .withIssuer(this.userGenerator).build();
            return verification.verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token invalid, not authorized!");
        }
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }

}
