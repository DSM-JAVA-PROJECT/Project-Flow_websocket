package com.projectflow.projectflowwebsocket.global.security.httpsecurity;

import com.projectflow.projectflowwebsocket.global.exception.UnExpectedException;
import com.projectflow.projectflowwebsocket.global.security.exceptions.JwtValidatingFailedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class JwtTokenValidator {

    @Value("{jwt.secret}")
    private String secret;

    private static final String JWT_PREFIX = "Bearer ";
    private static final String AUTH_HEADER = "Authorization";

    private final AuthDetailsService authDetailsService;

    public Authentication createAuthentication(String token) {
        String subject = getClaims(token).getSubject();

        UserDetails userDetails = authDetailsService.loadUserByUsername(subject);
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public String parseToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);
        return bearerToken != null ? bearerToken.replaceFirst(JWT_PREFIX, "") : null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            throw JwtValidatingFailedException.EXCEPTION;
        } catch (Exception e) {
            throw UnExpectedException.EXCEPTION;
        }
    }
}
