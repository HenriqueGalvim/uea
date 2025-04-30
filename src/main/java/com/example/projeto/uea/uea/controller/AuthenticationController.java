package com.example.projeto.uea.uea.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.uea.uea.dto.LoginRequestDTO;
import com.example.projeto.uea.uea.dto.LoginResponseDTO;
import com.example.projeto.uea.uea.model.Usuario;
import com.example.projeto.uea.uea.services.AuthenticationService;
import com.example.projeto.uea.uea.services.JwtService;


import jakarta.validation.Valid;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@Valid @RequestBody LoginRequestDTO loginUserDto) {
        Usuario authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponseDTO loginResponse = new LoginResponseDTO(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
