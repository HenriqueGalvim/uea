package com.example.projeto.uea.uea.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.projeto.uea.uea.dto.LoginRequestDTO;
import com.example.projeto.uea.uea.model.Usuario;
import com.example.projeto.uea.uea.repository.UsuarioRepository;

@Service
public class AuthenticationService {

    private final UsuarioRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UsuarioRepository userRepository,
        AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public Usuario authenticate(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(),
                loginRequestDTO.getPassword()
            )
        );

        return userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
