package com.learning.autentica.infra.security;

import com.learning.autentica.infra.service.JwtService;
import com.learning.autentica.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Autowired
    private final UserRepository userRepository;

    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token= this.getJwtToken(request);
        if(nonNull(token)) {
            var subject = jwtService.getSubject(token);
            var user = userRepository.findByUserName(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (nonNull(authorizationHeader)) {
            return  authorizationHeader.replace(BEARER, "");
        }
        return null;
    }
}
