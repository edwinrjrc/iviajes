/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.innovaviajes.cross.util.Constantes;
import pe.com.innovaviajes.cross.util.UtilIvDto;
import pe.com.innovaviajes.dto.ConsultaViajeDto;
import pe.com.innovaviajes.dto.ConsultaViajeWebDto;
import pe.com.innovaviajes.dto.RegistroVentaWebDto;
import pe.com.innovaviajes.dto.VuelosEncontrados;
import pe.com.innovaviajes.exception.UtilIvDtoException;
import pe.com.innovaviajes.web.ivserviceviajes.exception.IvServiceDestinoCiudadException;
import pe.com.innovaviajes.web.ivserviceviajes.service.DestinoCiudadCatalogoService;
import pe.com.innovaviajes.web.ivserviceviajes.util.UtilConversionS;
import pe.com.innovaviajes.web.ivserviceviajes.util.UtilSecurity;

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
	
	@Autowired
    private UtilSecurity utilSecurity;

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
	@GetMapping(value = "/vuelosCotizacion")
	public ResponseEntity<Map<String, Object>> consultarViaje(
			@RequestParam(name = "param1", required = false) String tipoViaje,
			@RequestParam(name = "param2", required = false) String codigoIataOrigen,
			@RequestParam(name = "param3", required = false) String codigoIataDestino,
			@RequestParam(name = "param4", required = false) String claseVuelo,
			@RequestParam(name = "param5", required = false) String fechaIda,
			@RequestParam(name = "param6", required = false) String fechaVuelta,
			@RequestParam(name = "param7", required = false) String adultos,
			@RequestParam(name = "param8", required = false) String ninos,
			@RequestParam(name = "param9", required = false) String infantes) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.debug("Recibiendo parametros obtenerDestinos " + this.getClass().getName());
			
			log.info("codigoIataOrigen ::"+codigoIataOrigen);
			log.info("codigoIataDestino ::"+codigoIataDestino);
			log.info("tipoViaje ::"+tipoViaje);
			log.info("claseVuelo ::"+claseVuelo);
			log.info("fechaIda ::"+fechaIda);
			log.info("fechaVuelta ::"+fechaVuelta);
			log.info("adultos ::"+adultos);
			log.info("ninos ::"+ninos);
			log.info("infantes ::"+infantes);
			
			UtilConversionS utilSeguridad = new UtilConversionS();
			
			ConsultaViajeDto consultaViajeDto = new ConsultaViajeDto();
			consultaViajeDto.setAdultos(Integer.valueOf(utilSeguridad.descencripta(adultos)));
			String numInfantes = utilSeguridad.descencripta(infantes);
			if (StringUtils.isNotBlank(numInfantes)) {
				consultaViajeDto.setInfantes(Integer.valueOf(numInfantes));
			}
			else {
				consultaViajeDto.setInfantes(0);
			}
			String numNinos = utilSeguridad.descencripta(ninos);
			if (StringUtils.isNotBlank(numNinos)) {
				consultaViajeDto.setNinos(Integer.valueOf(numNinos));
			}
			else {
				consultaViajeDto.setNinos(0);
			}
			consultaViajeDto.setClaseVuelo(utilSeguridad.descencripta(claseVuelo));
			consultaViajeDto.setCodigoIataDestino(utilSeguridad.descencripta(codigoIataDestino));
			consultaViajeDto.setCodigoIataOrigen(utilSeguridad.descencripta(codigoIataOrigen));
			consultaViajeDto.setFechaIda(UtilIvDto.parseStringADate(utilSeguridad.descencripta(fechaIda), Constantes.FORMAT_DATE_2, null));
			consultaViajeDto.setFechaVuelta(UtilIvDto.parseStringADate(utilSeguridad.descencripta(fechaVuelta), Constantes.FORMAT_DATE_2, null));
			log.info(consultaViajeDto.getFechaIda().toString());
			log.info(consultaViajeDto.getFechaVuelta().toString());
			consultaViajeDto.setTipoViaje(utilSeguridad.descencripta(tipoViaje));
			
			log.info(UtilIvDto.escribeObjetoEnLog(consultaViajeDto));
			
			VuelosEncontrados vuelosEncontrados = destinoCiudadCatalogoService.consultarVuelos(consultaViajeDto);
			
			if (UtilIvDto.listaNoVacia(vuelosEncontrados.getOfertasEncontradas())) {
				status = HttpStatus.OK;
			}

			mapeo = generaHashRegreso(false, Constantes.VALOR_DATA_MSJE_EXITO);
			mapeo.put(Constantes.VALOR_DATA_MAP, vuelosEncontrados);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			
			mapeo = generaHashRegreso(true, Constantes.VALOR_DATA_MSJE_ERROR);
		}

		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);
		return salida;
	}
	
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
	 * @throws IvServiceDestinoCiudadException 
	 * @throws UtilIvDtoException 
	 */
	@PostMapping(value = "/vuelosCotizacion")
	public ResponseEntity<Map<String, Object>> consultarViaje2(@RequestBody ConsultaViajeWebDto consulta) throws IvServiceDestinoCiudadException, UtilIvDtoException {
	    
	    log.info("Iniciando cotización - Origen: {} Destino: {}", consulta.getCodigoIataOrigen(), consulta.getCodigoIataDestino());

	    // Paso 1: Transformación y Desencriptación (Si falla, el GlobalHandler responde 400)
	    ConsultaViajeDto consultaViajeDto = prepararDto(consulta);
	    
	    // Paso 2: Lógica de Negocio
	    VuelosEncontrados vuelosEncontrados = destinoCiudadCatalogoService.consultarVuelos(consultaViajeDto);
	    
	    // Paso 3: Respuesta controlada de éxito
	    Map<String, Object> mapeo = generaHashRegreso(false, Constantes.VALOR_DATA_MSJE_EXITO);
		mapeo.put(Constantes.VALOR_DATA_MAP, vuelosEncontrados);

	    HttpStatus status = UtilIvDto.listaNoVacia(vuelosEncontrados.getOfertasEncontradas()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;

	    return new ResponseEntity<>(mapeo, status);
	}
	
	@PostMapping(value = "/reservaCotizacion")
	public ResponseEntity<Map<String, Object>> guardarDatosReserva(@RequestBody RegistroVentaWebDto reservaDto){
		HttpStatus status = HttpStatus.NO_CONTENT;
		
		Map<String, Object> mapeo = generaHashRegreso(false, Constantes.VALOR_DATA_MSJE_EXITO);
		mapeo.put(Constantes.VALOR_DATA_MAP, null);
		
		return new ResponseEntity<>(mapeo, status);
	}

	// Método auxiliar para limpiar el controlador de lógica de seguridad/mapeo
	private ConsultaViajeDto prepararDto(ConsultaViajeWebDto web) throws UtilIvDtoException {
	    ConsultaViajeDto dto = new ConsultaViajeDto();
	    dto.setAdultos(Integer.valueOf(utilSecurity.descencripta(web.getAdultos())));
	    dto.setNinos(StringUtils.isNotBlank(utilSecurity.descencripta(web.getNinos())) ? Integer.valueOf(utilSecurity.descencripta(web.getNinos())) : 0);
	    dto.setInfantes(StringUtils.isNotBlank(utilSecurity.descencripta(web.getInfantes())) ? Integer.valueOf(utilSecurity.descencripta(web.getInfantes())) : 0);
	    dto.setClaseVuelo(utilSecurity.descencripta(web.getClaseVuelo()));
	    dto.setCodigoIataDestino(utilSecurity.descencripta(web.getCodigoIataDestino()));
	    dto.setCodigoIataOrigen(utilSecurity.descencripta(web.getCodigoIataOrigen()));
	    dto.setFechaIda(UtilIvDto.parseStringADate(utilSecurity.descencripta(web.getFechaIdaStr()), Constantes.FORMAT_DATE_2, null));
	    dto.setFechaVuelta(UtilIvDto.parseStringADate(utilSecurity.descencripta(web.getFechaVueltaStr()), Constantes.FORMAT_DATE_2, null));
	    dto.setTipoViaje(utilSecurity.descencripta(web.getTipoViaje()));
	    return dto;
	}

	
	private Map<String, Object> generaHashRegreso(Boolean error, String mensaje){
		Map<String, Object> mapeo = null;
		mapeo = new HashMap<>();
		mapeo.put(Constantes.VALOR_FLG_ERROR, error);
		mapeo.put(Constantes.VALOR_DATA_MSJE, mensaje);
		
		return mapeo;
	}
}
