/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.innovaviajes.cross.util.Constantes;
import pe.com.innovaviajes.cross.util.UtilIvDto;
import pe.com.innovaviajes.dto.AeropuertoWebMostrarDto;
import pe.com.innovaviajes.web.ivserviceviajes.exception.IvServiceDestinoCiudadException;
import pe.com.innovaviajes.web.ivserviceviajes.service.DestinoCiudadCatalogoService;


/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/destinoservice")
public class DestinoCiudadServiceController {

	private static final Logger log = LoggerFactory.getLogger(DestinoCiudadServiceController.class);
	
	@Autowired
	private DestinoCiudadCatalogoService destinoCiudadCatalogoService;
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/destinoCiudadService")
	public ResponseEntity<Map<String, Object>> obtenerDestinos(@RequestParam(name = "nombreDestino", required = false) String nombreDestino) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			status = HttpStatus.NO_CONTENT;
			log.debug("Recibiendo parametros obtenerDestinos "+this.getClass().getName());
			UtilIvDto.pintaLog(nombreDestino, "nombreDestino");
			
			List<AeropuertoWebMostrarDto> listaAeropuertos = destinoCiudadCatalogoService.consultaDestinos(nombreDestino);
			
			if (UtilIvDto.listaNoVacia(listaAeropuertos)) {
				status = HttpStatus.OK;
			}
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Existo");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaAeropuertos);

		} catch (IvServiceDestinoCiudadException e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}

		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}

}
