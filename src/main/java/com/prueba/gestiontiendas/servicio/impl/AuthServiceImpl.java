package com.prueba.gestiontiendas.servicio.impl;

import com.prueba.gestiontiendas.dto.AuthResponseDto;
import com.prueba.gestiontiendas.dto.CambiarPasswordDto;
import com.prueba.gestiontiendas.dto.LoginRequestDto;
import com.prueba.gestiontiendas.dto.RegisterRequestDto;
import com.prueba.gestiontiendas.excepciones.DuplicadoException;
import com.prueba.gestiontiendas.excepciones.EntityNotFoundException;
import com.prueba.gestiontiendas.modelo.Usuario;
import com.prueba.gestiontiendas.repositorio.UsuarioRepository;
import com.prueba.gestiontiendas.servicio.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Husnain
 */

@Service
@Transactional(readOnly = true)
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

    public Usuario getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(Usuario.class.getSimpleName(), "userName", username));
    }

    @Transactional
    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Usuario usuario = getUsuarioByUsername(request.getUsername());

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
    @Transactional
    public AuthResponseDto registrar(RegisterRequestDto request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new DuplicadoException("auth.user.yaExiste", request.getUsername());
        }

        Usuario usuario = saveUsuario(request);
        UserDetails userDetails = buildUserDetails(usuario);
        String token = jwtService.generateToken(userDetails);

        return AuthResponseDto.builder()
                .token(token)
                .username(usuario.getUsername())
                .role(usuario.getRole().name())
                .build();
    }

    private Usuario saveUsuario(RegisterRequestDto request) {
        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Usuario.Role.USER)
                .build();

        return usuarioRepository.save(usuario);
    }

    private UserDetails buildUserDetails(Usuario usuario) {
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();
    }

    @Override
    @Transactional
    public void cambiarPassword(String username, CambiarPasswordDto dto) {
        Usuario usuario = getUsuarioValidandoPassword(username, dto.getActualPassword());

        usuario.setPassword(passwordEncoder.encode(dto.getNuevoPassword()));
        usuarioRepository.save(usuario);
    }

    private Usuario getUsuarioValidandoPassword(String username, String password) {
        Usuario usuario = getUsuarioByUsername(username);

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new BadCredentialsException("Password incorrecto");
        }

        return usuario;
    }
}
