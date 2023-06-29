package com.learning.autentica.infra.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.learning.autentica.model.User;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private Long expiration;

    private final static String ISSUER = "api-autentica";

    public String createJwt(User user) {
      try {
          Algorithm algorithm = Algorithm.HMAC256(secretKey);
          return JWT.create()
                  .withIssuer(ISSUER)
                  .withExpiresAt(this.toExpirationDate())
                  .withSubject(user.getUsername())
                  .sign(algorithm);
      } catch (RuntimeException e) {
          throw new RuntimeException("Erro ao gerar o token", e);
      }
    }

    public String getSubject(String jwtToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(jwtToken)
                    .getSubject();
        } catch (JWTVerificationException e){
            throw new RuntimeException("Token Inválido ou expirado", e);
        }
    }

    private Date toExpirationDate(){
        return Date.from(LocalDateTime.now().plus(expiration, ChronoUnit.MINUTES).atZone(ZoneId.systemDefault()).toInstant());
    }

}
