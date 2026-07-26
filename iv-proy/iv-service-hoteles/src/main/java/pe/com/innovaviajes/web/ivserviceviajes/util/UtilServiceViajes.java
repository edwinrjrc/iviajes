/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.innovaviajes.dto.AeropuertoWebDto;
import pe.com.innovaviajes.dto.AeropuertoWebMostrarDto;
import pe.com.innovaviajes.dto.DestinoCiudadDto;
import pe.com.innovaviajes.web.ivserviceviajes.exception.IvServiceDestinoCiudadException;

/**
 * @author Edwin
 *
 */
public class UtilServiceViajes {

	private static final Logger log = LoggerFactory.getLogger(UtilServiceViajes.class);

	public static AeropuertoWebMostrarDto parseDestino(AeropuertoWebDto aeropuertoDto, DestinoCiudadDto destinoDto)
			throws IvServiceDestinoCiudadException {
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
			throw new IvServiceDestinoCiudadException(e);
		}
	}

}
