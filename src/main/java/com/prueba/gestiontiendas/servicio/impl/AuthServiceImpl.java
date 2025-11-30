package com.prueba.gestiontiendas.servicio.impl;

import com.prueba.gestiontiendas.dto.AuthResponseDto;
import com.prueba.gestiontiendas.dto.LoginRequestDto;
import com.prueba.gestiontiendas.dto.RegisterRequestDto;
import com.prueba.gestiontiendas.excepciones.DuplicadoException;
import com.prueba.gestiontiendas.excepciones.EntityNotFoundException;
import com.prueba.gestiontiendas.modelo.Usuario;
import com.prueba.gestiontiendas.repositorio.UsuarioRepository;
import com.prueba.gestiontiendas.servicio.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Husnain
 */

@Service
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(Usuario.class.getSimpleName(), "userName", request.getUsername()));

        UserDetails userDetails = User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);

        return AuthResponseDto.builder()
                .token(token)
                .username(usuario.getUsername())
                .role(usuario.getRole().name())
                .build();
    }

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new DuplicadoException("El usuario ya existe: " + request.getUsername()); //TODO poner en i18n
        }

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Usuario.Role.USER)
                .build();

        usuarioRepository.save(usuario);

        UserDetails userDetails = User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);

        return AuthResponseDto.builder()
                .token(token)
                .username(usuario.getUsername())
                .role(usuario.getRole().name())
                .build();
    }
}
