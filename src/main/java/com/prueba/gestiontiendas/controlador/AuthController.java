package com.prueba.gestiontiendas.controlador;

import com.prueba.gestiontiendas.dto.AuthResponseDto;
import com.prueba.gestiontiendas.dto.CambiarPasswordDto;
import com.prueba.gestiontiendas.dto.LoginRequestDto;
import com.prueba.gestiontiendas.dto.RegisterRequestDto;
import com.prueba.gestiontiendas.servicio.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/changePass/{username}")
    public ResponseEntity<Void> cambiarPassword(@PathVariable String username, @Valid @RequestBody CambiarPasswordDto dto) {
        authService.cambiarPassword(username, dto);
        return ResponseEntity.noContent().build();
    }
}
