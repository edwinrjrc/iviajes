/**
 * 
 */
package pe.com.innovaviajes.web.ivservicehoteles.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pe.com.innovaviajes.dto.HotelDto;
import pe.com.innovaviajes.web.ivservicehoteles.exception.IvServiceHotelesException;
import pe.com.innovaviajes.web.ivservicehoteles.service.HotelService;

/**
 * @author Edwin
 *
 */
@Service
public class HotelServiceImpl implements HotelService {

	private static final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);

	private final Map<Integer, HotelDto> almacen = new ConcurrentHashMap<>();
	private final AtomicInteger secuencia = new AtomicInteger(1);

	@Override
	public List<HotelDto> listarHoteles() throws IvServiceHotelesException {
		log.debug("Listando todos los hoteles");
		return new ArrayList<>(almacen.values());
	}

	@Override
	public HotelDto obtenerHotelPorId(Integer idHotel) throws IvServiceHotelesException {
		log.debug("Buscando hotel con id: {}", idHotel);
		HotelDto hotel = almacen.get(idHotel);
		if (hotel == null) {
			throw new IvServiceHotelesException("Hotel no encontrado con id: " + idHotel);
		}
		return hotel;
	}

	@Override
	public HotelDto crearHotel(HotelDto hotelDto) throws IvServiceHotelesException {
		if (hotelDto == null || hotelDto.getNombre() == null || hotelDto.getNombre().isBlank()) {
			throw new IvServiceHotelesException("El nombre del hotel es requerido");
		}
		int nuevoId = secuencia.getAndIncrement();
		hotelDto.setIdHotel(nuevoId);
		almacen.put(nuevoId, hotelDto);
		log.info("Hotel creado con id: {}", nuevoId);
		return hotelDto;
	}

	@Override
	public HotelDto actualizarHotel(Integer idHotel, HotelDto hotelDto) throws IvServiceHotelesException {
		if (!almacen.containsKey(idHotel)) {
			throw new IvServiceHotelesException("Hotel no encontrado con id: " + idHotel);
		}
		if (hotelDto == null || hotelDto.getNombre() == null || hotelDto.getNombre().isBlank()) {
			throw new IvServiceHotelesException("El nombre del hotel es requerido");
		}
		hotelDto.setIdHotel(idHotel);
		almacen.put(idHotel, hotelDto);
		log.info("Hotel actualizado con id: {}", idHotel);
		return hotelDto;
	}

	@Override
	public void eliminarHotel(Integer idHotel) throws IvServiceHotelesException {
		if (!almacen.containsKey(idHotel)) {
			throw new IvServiceHotelesException("Hotel no encontrado con id: " + idHotel);
		}
		almacen.remove(idHotel);
		log.info("Hotel eliminado con id: {}", idHotel);
	}
}
