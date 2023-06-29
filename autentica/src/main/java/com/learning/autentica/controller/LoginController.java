package com.learning.autentica.controller;

import com.learning.autentica.dto.AuthDTO;
import com.learning.autentica.infra.dto.TokenResponse;
import com.learning.autentica.infra.service.JwtService;
import com.learning.autentica.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager am;

    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<TokenResponse> authUser(@RequestBody @Valid AuthDTO dto) {
        UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(dto.userName(), dto.password());
        var authentication = am.authenticate(loginData);
        var jwtToken = jwtService.createJwt((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenResponse(jwtToken));

    }
}
