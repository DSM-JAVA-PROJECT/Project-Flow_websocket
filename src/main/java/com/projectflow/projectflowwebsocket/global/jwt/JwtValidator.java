package com.projectflow.projectflowwebsocket.global.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    @Value("${jwt.secret}")
    private String secret;

    public boolean isValid(String token) {
        try {
            String extractedToken = token.replace("Bearer ", "");
            Jwts.parser()
                    .setSigningKey(extractedToken)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
