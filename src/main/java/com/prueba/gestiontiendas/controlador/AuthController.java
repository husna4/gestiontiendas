package com.prueba.gestiontiendas.controlador;

import com.prueba.gestiontiendas.dto.AuthResponseDto;
import com.prueba.gestiontiendas.dto.CambiarPasswordDto;
import com.prueba.gestiontiendas.dto.LoginRequestDto;
import com.prueba.gestiontiendas.dto.RegisterRequestDto;
import com.prueba.gestiontiendas.servicio.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author Husnain
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registrar(request));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> cambiarPassword(@Valid @RequestBody CambiarPasswordDto dto, Authentication auth) {
        authService.cambiarPassword(auth.getName(), dto);
        return ResponseEntity.noContent().build();
    }
}
