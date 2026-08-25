/**
 * 
 */
package pe.com.innovaviajes.web.ivservicerentcar.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.innovaviajes.web.ivservicerentcar.dto.AeropuertoWebDto;
import pe.com.innovaviajes.web.ivservicerentcar.dto.AeropuertoWebMostrarDto;
import pe.com.innovaviajes.web.ivservicerentcar.dto.DestinoCiudadDto;
import pe.com.innovaviajes.web.ivservicerentcar.exception.IvServiceRentCarException;


/**
 * @author Edwin
 *
 */
public class UtilServiceViajes {

	private static final Logger log = LoggerFactory.getLogger(UtilServiceViajes.class);

	public static AeropuertoWebMostrarDto parseDestino(AeropuertoWebDto aeropuertoDto, DestinoCiudadDto destinoDto)
			throws IvServiceRentCarException {
		try {
			AeropuertoWebMostrarDto dto = new AeropuertoWebMostrarDto();
			
			dto.setCodigoIata(aeropuertoDto.getCodigoIata());
			dto.setDescripcion(aeropuertoDto.getDescripcion());
			dto.setFechaModificacion(aeropuertoDto.getFechaModificacion());
			dto.setFechaRegistro(aeropuertoDto.getFechaRegistro());
			dto.setId(aeropuertoDto.getId());
			dto.setIdEstadoRegistro(aeropuertoDto.getIdEstadoRegistro());
			dto.setIdUsuarioModificacion(aeropuertoDto.getIdUsuarioModificacion());
			dto.setIdUsuarioRegistro(aeropuertoDto.getIdUsuarioRegistro());
			dto.setNombreCiudad(destinoDto.getDescripcion());
			dto.setNombrePais(destinoDto.getPaisDto().getNombre());

			return dto;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new IvServiceRentCarException(e);
		}
	}

}
