/**
 * 
 */
package pe.com.innovaviajes.data.ivjpadestinos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.innovaviajes.cross.util.Constantes;
import pe.com.innovaviajes.cross.util.UtilIvDto;
import pe.com.innovaviajes.data.ivjpadestinos.dao.DestinoCiudadRepository;
import pe.com.innovaviajes.data.ivjpadestinos.entity.DestinoCiudad;
import pe.com.innovaviajes.data.ivjpadestinos.util.Utilivjpa;
import pe.com.innovaviajes.dto.DestinoCiudadDto;



@RestController
@RequestMapping(value = "/DestinoCiudadJPAService")
public class DestinoCiudadController {
	
	private static final Logger log = LoggerFactory.getLogger(DestinoCiudadController.class);
	
	@Autowired
	private DestinoCiudadRepository destinoCiudadRepository;
	
	@GetMapping(value = "/destinosCiudad")
	public ResponseEntity<Map<String, Object>> destinos(@RequestParam(name = "nombreDestino", required = true) String nombreDestino) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("Parametros recibidos");
			UtilIvDto.pintaLog(nombreDestino, "nombreDestino");
			
			List<DestinoCiudad> listaDestinos = destinoCiudadRepository.findByDescripcionContainingIgnoreCaseOrderByDescripcionAsc(nombreDestino);
			
			List<DestinoCiudadDto> listaDestinoDto = null;
			if (UtilIvDto.listaNoVacia(listaDestinos)) {
				listaDestinoDto = new ArrayList<>();
				
				for (DestinoCiudad destinoCiudad : listaDestinos) {
					listaDestinoDto.add(Utilivjpa.parseDestino(destinoCiudad));
				}
				
				log.debug(UtilIvDto.escribeObjetoEnLog(listaDestinoDto));
			}
						
			mapeo = new HashMap<String, Object>();
			mapeo.put(Constantes.VALOR_DATA_MAP, listaDestinoDto);
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion completada");
			status = HttpStatus.OK;
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
