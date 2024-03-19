package com.fiap.burger.usecase.misc.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.usecase.misc.secret.SecretUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenJwtUtils {
    private static final Long ADD_TIME_EXPIRATION = (1000L * 60L * 10L);

    private final SecretUtils secretUtils;

    public TokenJwtUtils(SecretUtils secretUtils) {
        this.secretUtils = secretUtils;
    }

    public String generateEmployeeToken(Employee employee) {
        TokenJwtSecret jwtSecret = secretUtils.getTokenJwtSecret();
        return JWT.create()
            .withIssuer(jwtSecret.getIssuer())
            .withSubject("Employee - Token")
            .withClaim("id", employee.getId())
            .withClaim("name", employee.getName())
            .withClaim("email", employee.getEmail())
            .withClaim("type", employee.getType().ordinal())
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + ADD_TIME_EXPIRATION))
            .withJWTId(UUID.randomUUID()
                .toString())
            .sign(buildAlgorithm(jwtSecret.getSecret()));
    }

    public String generatePasswordToken(String password) {
        TokenJwtSecret jwtSecret = secretUtils.getTokenJwtSecret();
        return JWT.create()
            .withIssuer(jwtSecret.getIssuer())
            .withSubject("Password - Token")
            .withClaim("password", password)
            .sign(buildAlgorithm(jwtSecret.getSecret()));
    }

    private static Algorithm buildAlgorithm(String secret) {
        return Algorithm.HMAC256(secret);
    }
}
