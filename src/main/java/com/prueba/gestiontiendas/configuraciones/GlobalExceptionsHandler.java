package com.prueba.gestiontiendas.configuraciones;

import com.prueba.gestiontiendas.excepciones.DuplicadoException;
import com.prueba.gestiontiendas.excepciones.EntityNotFoundException;
import com.prueba.gestiontiendas.excepciones.HorasInsuficientesException;
import com.prueba.gestiontiendas.util.MensajeUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.prueba.gestiontiendas.util.MensajeUtil.buildMensaje;

/**
 * @author Husnain
 */

@RestControllerAdvice
public class GlobalExceptionsHandler {

    private final MessageSource messageSource;

    public GlobalExceptionsHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Manejo de errores de entidad no encontrada
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleObjectNotFoundException(EntityNotFoundException ex) {
        String mensaje = buildMensajeEntityNotFound(ex);

        Map<String, Object> response = createResponse(mensaje, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private String buildMensajeEntityNotFound(EntityNotFoundException ex) {
        if(ex.getIdMensaje() != null) {
            return buildMensaje(messageSource, ex.getIdMensaje(), ex.getParams());
        }
        else if(ex.getNombreEntidad() != null) {
            return buildMensaje(messageSource, "exception.notfound.detalle", ex.getNombreEntidad(),
                    ex.getNombreCampo(), ex.getValorCampo());
        }
        else{
            return ex.getMessage();
        }
    }

    /**
     * Manejo de errores de validación de campos
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        String mensaje = buildMensaje(messageSource,"exception.validacion.errores");

        Map<String, Object> response = createResponse(mensaje, HttpStatus.BAD_REQUEST);

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage()));

        response.put("errors", errores);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejo de errores de horas insuficientes al asignar
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(HorasInsuficientesException.class)
    public ResponseEntity<Map<String, Object>> handleObjectNotFoundException(HorasInsuficientesException ex) {
        String mensaje = ex.getHorasDisponibles() != null
                ? buildMensaje(messageSource,"exception.horas.insuficientes", ex.getHorasDisponibles(),
                ex.getHorasSolicitadas())
                : ex.getMessage();
        Map<String, Object> response = createResponse(mensaje, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejo de errores de integridad de datos
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
        String message = buildMensaje(messageSource, "exception.integridad.datos");
        Map<String, Object> response = createResponse(message, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejo de errores de autenticación
     *
     * @param ex
     * @param request
     * @return
     */

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthentication(
            AuthenticationException ex,
            HttpServletRequest request) {

        String mensaje = buildMensaje(messageSource, "auth.credenciales.invalidas");

        Map<String, Object> response = createResponse(mensaje, HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleNombreUsuarioOPasswordIncorrecto(
            BadCredentialsException ex,
            HttpServletRequest request) {

        String mensaje = buildMensaje(messageSource, "auth.credenciales.invalidas");

        Map<String, Object> response = createResponse(mensaje, HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DuplicadoException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicados(
            DuplicadoException ex,
            HttpServletRequest request) {

        Map<String, Object> response = createResponse(buidMensajeExcepcionConMessageSource(ex, ex.getDatosClave()),
                HttpStatus.CONFLICT);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private String buidMensajeExcepcionConMessageSource(Exception ex, Object... params) {
        return MensajeUtil.buildMensaje(messageSource, ex.getMessage(), params);
    }

    private Map<String, Object> createResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status.value());
        response.put("timestamp", getTimestampNow());
        return response;
    }

    private LocalDateTime getTimestampNow() {
        return LocalDateTime.now();
    }
}
