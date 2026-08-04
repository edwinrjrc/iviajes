/**
 * 
 */
package pe.com.innovaviajes.web.ivservicehoteles.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.innovaviajes.cross.util.Constantes;
import pe.com.innovaviajes.dto.HotelDto;
import pe.com.innovaviajes.web.ivservicehoteles.exception.IvServiceHotelesException;
import pe.com.innovaviajes.web.ivservicehoteles.service.HotelService;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/hotelService")
public class HotelServiceController {

	private static final Logger log = LoggerFactory.getLogger(HotelServiceController.class);

	@Autowired
	private HotelService hotelService;

	/**
	 * Lista todos los hoteles disponibles.
	 *
	 * @return lista de hoteles con HTTP 200, o HTTP 204 si no hay hoteles
	 */
	@GetMapping(value = "/hoteles")
	public ResponseEntity<Map<String, Object>> listarHoteles() {
		Map<String, Object> mapeo = generaHashRegreso(false, Constantes.VALOR_DATA_MSJE_EXITO);
		try {
			log.debug("Listando hoteles");
			List<HotelDto> hoteles = hotelService.listarHoteles();
			mapeo.put(Constantes.VALOR_DATA_MAP, hoteles);
			HttpStatus status = (hoteles != null && !hoteles.isEmpty()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
			return new ResponseEntity<>(mapeo, status);
		} catch (IvServiceHotelesException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(generaHashRegreso(true, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene un hotel por su identificador.
	 *
	 * @param idHotel identificador del hotel
	 * @return hotel encontrado con HTTP 200, o HTTP 404 si no existe
	 */
	@GetMapping(value = "/hoteles/{idHotel}")
	public ResponseEntity<Map<String, Object>> obtenerHotel(@PathVariable Integer idHotel) {
		Map<String, Object> mapeo = generaHashRegreso(false, Constantes.VALOR_DATA_MSJE_EXITO);
		try {
			log.debug("Buscando hotel con id: {}", idHotel);
			HotelDto hotel = hotelService.obtenerHotelPorId(idHotel);
			mapeo.put(Constantes.VALOR_DATA_MAP, hotel);
			return new ResponseEntity<>(mapeo, HttpStatus.OK);
		} catch (IvServiceHotelesException e) {
			log.warn("Hotel no encontrado: {}", e.getMessage());
			return new ResponseEntity<>(generaHashRegreso(true, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Crea un nuevo hotel.
	 *
	 * @param hotelDto datos del hotel a crear
	 * @return hotel creado con HTTP 201
	 */
	@PostMapping(value = "/hoteles")
	public ResponseEntity<Map<String, Object>> crearHotel(@RequestBody HotelDto hotelDto) {
		Map<String, Object> mapeo = generaHashRegreso(false, Constantes.VALOR_DATA_MSJE_EXITO);
		try {
			log.info("Creando nuevo hotel: {}", hotelDto != null ? hotelDto.getNombre() : "null");
			if (hotelDto == null) {
				return new ResponseEntity<>(generaHashRegreso(true, "Los datos del hotel son requeridos"),
						HttpStatus.BAD_REQUEST);
			}
			HotelDto creado = hotelService.crearHotel(hotelDto);
			mapeo.put(Constantes.VALOR_DATA_MAP, creado);
			return new ResponseEntity<>(mapeo, HttpStatus.CREATED);
		} catch (IvServiceHotelesException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(generaHashRegreso(true, e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Actualiza un hotel existente.
	 *
	 * @param idHotel  identificador del hotel
	 * @param hotelDto datos actualizados del hotel
	 * @return hotel actualizado con HTTP 200, o HTTP 404 si no existe
	 */
	@PutMapping(value = "/hoteles/{idHotel}")
	public ResponseEntity<Map<String, Object>> actualizarHotel(
			@PathVariable Integer idHotel,
			@RequestBody HotelDto hotelDto) {
		Map<String, Object> mapeo = generaHashRegreso(false, Constantes.VALOR_DATA_MSJE_EXITO);
		try {
			log.info("Actualizando hotel con id: {}", idHotel);
			if (hotelDto == null) {
				return new ResponseEntity<>(generaHashRegreso(true, "Los datos del hotel son requeridos"),
						HttpStatus.BAD_REQUEST);
			}
			HotelDto actualizado = hotelService.actualizarHotel(idHotel, hotelDto);
			mapeo.put(Constantes.VALOR_DATA_MAP, actualizado);
			return new ResponseEntity<>(mapeo, HttpStatus.OK);
		} catch (IvServiceHotelesException e) {
			log.warn("Hotel no encontrado para actualizar: {}", e.getMessage());
			return new ResponseEntity<>(generaHashRegreso(true, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Elimina un hotel por su identificador.
	 *
	 * @param idHotel identificador del hotel
	 * @return HTTP 204 si se eliminó, o HTTP 404 si no existe
	 */
	@DeleteMapping(value = "/hoteles/{idHotel}")
	public ResponseEntity<Map<String, Object>> eliminarHotel(@PathVariable Integer idHotel) {
		try {
			log.info("Eliminando hotel con id: {}", idHotel);
			hotelService.eliminarHotel(idHotel);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (IvServiceHotelesException e) {
			log.warn("Hotel no encontrado para eliminar: {}", e.getMessage());
			return new ResponseEntity<>(generaHashRegreso(true, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	private Map<String, Object> generaHashRegreso(Boolean error, String mensaje) {
		Map<String, Object> mapeo = new HashMap<>();
		mapeo.put(Constantes.VALOR_FLG_ERROR, error);
		mapeo.put(Constantes.VALOR_DATA_MSJE, mensaje);
		return mapeo;
	}
}
