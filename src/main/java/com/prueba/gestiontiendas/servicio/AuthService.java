package com.prueba.gestiontiendas.servicio;

import com.prueba.gestiontiendas.dto.AuthResponseDto;
import com.prueba.gestiontiendas.dto.CambiarPasswordDto;
import com.prueba.gestiontiendas.dto.LoginRequestDto;
import com.prueba.gestiontiendas.dto.RegisterRequestDto;

/**
 * @author Husnain
 */
public interface AuthService {
    AuthResponseDto login(LoginRequestDto request);
    AuthResponseDto registrar(RegisterRequestDto request);
    void cambiarPassword(String username, CambiarPasswordDto dto);
}
