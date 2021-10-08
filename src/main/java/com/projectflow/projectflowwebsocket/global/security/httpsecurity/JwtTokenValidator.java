package com.projectflow.projectflowwebsocket.global.security.httpsecurity;

import com.projectflow.projectflowwebsocket.global.exception.UnExpectedException;
import com.projectflow.projectflowwebsocket.global.security.exceptions.JwtExpiredException;
import com.projectflow.projectflowwebsocket.global.security.exceptions.JwtValidatingFailedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.netty.handler.codec.base64.Base64Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
@Component
public class JwtTokenValidator {

    @Value("${jwt.secret}")
    private String secret;

    private static final String JWT_PREFIX = "Bearer ";
    private static final String AUTH_HEADER = "Authorization";

    private final AuthDetailsService authDetailsService;

    public Authentication createAuthentication(String token) {
        String subject = getClaims(token).get("email").toString();

        UserDetails userDetails = authDetailsService.loadUserByUsername(subject);
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public String parseToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);
        return bearerToken != null ? bearerToken.replaceFirst(JWT_PREFIX, "") : null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes())).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw JwtExpiredException.EXCEPTION;
        } catch (JwtException e) {
            throw JwtValidatingFailedException.EXCEPTION;
        } catch (Exception e) {
            throw UnExpectedException.EXCEPTION;
        }
    }
}
