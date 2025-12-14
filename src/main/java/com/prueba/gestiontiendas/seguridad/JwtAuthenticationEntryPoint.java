package com.prueba.gestiontiendas.seguridad;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.gestiontiendas.util.MensajeUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Husnain
 * <p>
 * Clase para manejar errores y devolver un mensaje
 * adecuado al autenticar
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final MessageSource messageSource;

    public JwtAuthenticationEntryPoint(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String message = MensajeUtil.buildMensaje(messageSource,
                "auth.token.requerido");

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorResponse.put("timestamp", LocalDateTime.now().toString());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }

}
