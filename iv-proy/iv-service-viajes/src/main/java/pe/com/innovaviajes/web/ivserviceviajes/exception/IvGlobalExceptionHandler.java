/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pe.com.innovaviajes.web.ivserviceviajes.dto.ErrorResponseDto;


/**
 * @author Edwin
 *
 */
@RestControllerAdvice
public class IvGlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(IvGlobalExceptionHandler.class);

    // 1. Manejo de errores de negocio (tus excepciones personalizadas)
    @ExceptionHandler(IvServiceDestinoCiudadException.class)
    public ResponseEntity<ErrorResponseDto> handleBusiness(IvServiceDestinoCiudadException ex) {
        log.warn("Negocio: {}", ex.getMessage());
        return new ResponseEntity<>(
            new ErrorResponseDto(HttpStatus.CONFLICT.value(), ex.getMessage()), 
            HttpStatus.CONFLICT);
    }

    // 2. Manejo de errores de entrada (Formato de números, fechas o desencriptación fallida)
    @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponseDto> handleBadRequest(Exception ex) {
        log.error("Error en datos de entrada: {}", ex.getMessage());
        // Especificamos <ErrorResponse> explícitamente
        return new ResponseEntity<ErrorResponseDto>(
            new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), "Los datos enviados tienen un formato inválido"), 
            HttpStatus.BAD_REQUEST);
    }

    // 3. Manejo de errores fatales (El "paracaídas" para errores 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobal(Exception ex) {
        log.error("ERROR NO CONTROLADO: ", ex); // Aquí sí guardamos todo el stacktrace
        return new ResponseEntity<>(
            new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno en el servidor. Contacte a soporte."), 
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(UtilIvDtoException.class)
    public ResponseEntity<ErrorResponseDto> handleUtilIvDtoException(UtilIvDtoException ex) {
        log.error("Error en procesamiento de datos (UtilIvDto): {}", ex.getMessage());
        
        ErrorResponseDto error = new ErrorResponseDto(
            HttpStatus.BAD_REQUEST.value(), 
            "El formato de las fechas o datos de viaje es incorrecto."
        );

        return new ResponseEntity<ErrorResponseDto>(error, HttpStatus.BAD_REQUEST);
    }
}
