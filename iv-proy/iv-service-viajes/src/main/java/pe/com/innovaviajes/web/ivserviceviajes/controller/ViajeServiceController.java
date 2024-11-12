/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.controller;

import java.util.HashMap;
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
import pe.com.innovaviajes.dto.ConsultaViajeDto;
import pe.com.innovaviajes.dto.VuelosEncontrados;
import pe.com.innovaviajes.web.ivserviceviajes.service.DestinoCiudadCatalogoService;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/viajeService")
public class ViajeServiceController {

	private static final Logger log = LoggerFactory.getLogger(ViajeServiceController.class);
	
	@Autowired
	private DestinoCiudadCatalogoService destinoCiudadCatalogoService;

	/**
	 * 
	 * @param tipoViaje
	 * @param codigoIataOrigen
	 * @param codigoIataDestino
	 * @param claseVuelo
	 * @param fechaIda
	 * @param fechaVuelta
	 * @param adultos
	 * @param ninos
	 * @param infantes
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/vuelosCotizacion")
	public ResponseEntity<Map<String, Object>> consultarViaje(
			@RequestParam(name = "tipoViaje", required = false) String tipoViaje,
			@RequestParam(name = "codigoIataOrigen", required = true) String codigoIataOrigen,
			@RequestParam(name = "codigoIataDestino", required = true) String codigoIataDestino,
			@RequestParam(name = "claseVuelo", required = false) String claseVuelo,
			@RequestParam(name = "fechaIda", required = false) String fechaIda,
			@RequestParam(name = "fechaVuelta", required = false) String fechaVuelta,
			@RequestParam(name = "adultos", required = true) Integer adultos,
			@RequestParam(name = "ninos", required = false) Integer ninos,
			@RequestParam(name = "infantes", required = false) Integer infantes) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.debug("Recibiendo parametros obtenerDestinos " + this.getClass().getName());
			
			ConsultaViajeDto consultaViajeDto = new ConsultaViajeDto();
			consultaViajeDto.setAdultos(adultos);
			consultaViajeDto.setClaseVuelo(claseVuelo);
			consultaViajeDto.setCodigoIataDestino(codigoIataDestino);
			consultaViajeDto.setCodigoIataOrigen(codigoIataOrigen);
			consultaViajeDto.setFechaIda(UtilIvDto.parseStringADate(fechaIda, "dd/MM/yyyy", null));
			consultaViajeDto.setFechaVuelta(UtilIvDto.parseStringADate(fechaVuelta, "dd/MM/yyyy", null));
			consultaViajeDto.setInfantes(infantes);
			consultaViajeDto.setNinos(ninos);
			consultaViajeDto.setTipoViaje(tipoViaje);
			
			log.info(UtilIvDto.escribeObjetoEnLog(consultaViajeDto));
			
			VuelosEncontrados vuelosEncontrados = destinoCiudadCatalogoService.consultarVuelos(consultaViajeDto);
			
			if (UtilIvDto.listaNoVacia(vuelosEncontrados.getOfertasEncontradas())) {
				status = HttpStatus.OK;
			}

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Existo");
			mapeo.put(Constantes.VALOR_DATA_MAP, vuelosEncontrados);
			
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
