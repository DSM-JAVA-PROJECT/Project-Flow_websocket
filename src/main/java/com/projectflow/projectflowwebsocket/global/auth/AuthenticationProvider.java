package com.projectflow.projectflowwebsocket.global.auth;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationProvider {

    private final CustomUserDetailsService userDetailsService;

    public Authentication authorizeUser(String token) {
        try {
            String email = parseToken(token);
            return createAuthentication(email);
        } catch (Exception e) {
            return null;
        }
    }

    private Authentication createAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    private String parseToken(String token) {
        String extractedToken = parseBearer(token);
        return Jwts.parser()
                .setSigningKey(extractedToken)
                .parseClaimsJws(token).getBody().getSubject();
    }

    private String parseBearer(String pureToken) {
        return pureToken.replace("Bearer ", "");
    }

}
