/**
 * 
 */
package pe.com.innovaviajes.web.ivservicehoteles.service;

import java.util.List;

import pe.com.innovaviajes.dto.HotelDto;
import pe.com.innovaviajes.web.ivservicehoteles.exception.IvServiceHotelesException;

/**
 * @author Edwin
 *
 */
public interface HotelService {

	List<HotelDto> listarHoteles() throws IvServiceHotelesException;

	HotelDto obtenerHotelPorId(Integer idHotel) throws IvServiceHotelesException;

	HotelDto crearHotel(HotelDto hotelDto) throws IvServiceHotelesException;

	HotelDto actualizarHotel(Integer idHotel, HotelDto hotelDto) throws IvServiceHotelesException;

	void eliminarHotel(Integer idHotel) throws IvServiceHotelesException;
}
