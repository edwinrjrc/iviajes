/**
 * 
 */
package pe.com.innovaviajes.web.ivservicehoteles.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pe.com.innovaviajes.dto.ErrorResponseDto;

/**
 * @author Edwin
 *
 */
@RestControllerAdvice
public class IvGlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(IvGlobalExceptionHandler.class);

	@ExceptionHandler(IvServiceHotelesException.class)
	public ResponseEntity<ErrorResponseDto> handleBusiness(IvServiceHotelesException ex) {
		log.warn("Negocio: {}", ex.getMessage());
		return new ResponseEntity<>(
				new ErrorResponseDto(HttpStatus.CONFLICT.value(), ex.getMessage()),
				HttpStatus.CONFLICT);
	}

	@ExceptionHandler({ NumberFormatException.class, IllegalArgumentException.class })
	public ResponseEntity<ErrorResponseDto> handleBadRequest(Exception ex) {
		log.error("Error en datos de entrada: {}", ex.getMessage());
		return new ResponseEntity<ErrorResponseDto>(
				new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), "Los datos enviados tienen un formato inválido"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobal(Exception ex) {
		log.error("ERROR NO CONTROLADO: ", ex);
		return new ResponseEntity<>(
				new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"Error interno en el servidor. Contacte a soporte."),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
