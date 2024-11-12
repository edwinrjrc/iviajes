/**
 * 
 */
package pe.com.innovaviajes.data.ivjpadestinos.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.innovaviajes.cross.util.UtilIvDto;
import pe.com.innovaviajes.data.ivjpadestinos.entity.AeropuertoWeb;
import pe.com.innovaviajes.data.ivjpadestinos.entity.DestinoCiudad;
import pe.com.innovaviajes.data.ivjpadestinos.exception.JpaDestinosException;
import pe.com.innovaviajes.dto.AeropuertoWebDto;
import pe.com.innovaviajes.dto.DestinoCiudadDto;

/**
 * @author Edwin
 *
 */
public class Utilivjpa {
	
	private static final Logger log = LoggerFactory.getLogger(Utilivjpa.class);
	
	public static DestinoCiudadDto parseDestino(DestinoCiudad entity) throws JpaDestinosException {
		try {
			DestinoCiudadDto dto = new DestinoCiudadDto();
			
			dto.setDescripcion(capitalizaDestino(entity.getDescripcion()));
			dto.setFechaModificacion(entity.getFechamodificacion());
			dto.setFechaRegistro(entity.getFechacreacion());
			dto.setId(entity.getId());
			
			dto.getPaisDto().setId(entity.getPaisWeb().getId());
			dto.getPaisDto().setNombre( capitalizaDestino(entity.getPaisWeb().getDescripcion()) );
			
			List<AeropuertoWeb> listaAeropuertos = entity.getAeropuertoWebs();
			if (UtilIvDto.listaNoVacia(listaAeropuertos)) {
				List<AeropuertoWebDto> listaAeropuertosDto = new ArrayList<AeropuertoWebDto>();
				
				for (AeropuertoWeb aeropuertoWeb : listaAeropuertos) {
					if (aeropuertoWeb != null) {
						listaAeropuertosDto.add(parseAeropuertoWeb(aeropuertoWeb));
					}
				}
				
				dto.setListaAeropuertosDto(listaAeropuertosDto);
			}
			
			dto.setIdUsuarioModificacion(entity.getIdusuariomodificacion());
			dto.setFechaRegistro(entity.getFechacreacion());
			dto.setIdUsuarioRegistro(entity.getIdusuariocreacion());
			dto.setFechaModificacion(entity.getFechamodificacion());
			
			return dto;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new JpaDestinosException(e);
		}
	}
	
	private static String capitalizaDestino(String nombre) throws JpaDestinosException {
		try {
			String nombreFinal = "";
			
			nombre = StringUtils.lowerCase(nombre);
			nombre = StringUtils.normalizeSpace(nombre);
			
			StringTokenizer stk = new StringTokenizer(nombre, " ");
			
			while(stk.hasMoreTokens()) {
				nombreFinal = nombreFinal + " " + StringUtils.capitalize(stk.nextToken());
			}
			
			return nombreFinal;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new JpaDestinosException(e);
		}
	}
	
	public static AeropuertoWebDto parseAeropuertoWeb(AeropuertoWeb entity) throws JpaDestinosException {
		try {
			AeropuertoWebDto dto = new AeropuertoWebDto();
			
			dto.setId(entity.getId());
			dto.setDescripcion(entity.getDescripcion());
			dto.setCodigoIata(entity.getCodigoiata());
			dto.setFechaModificacion(entity.getFechamodificacion());
			dto.setFechaRegistro(entity.getFechacreacion());
			dto.setIdUsuarioModificacion(entity.getIdusuariomodificacion());
			dto.setIdUsuarioRegistro(entity.getIdusuariocreacion());
			dto.setIdEstadoRegistro(entity.getIdestadoregistro());
			
			
			
			return dto;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new JpaDestinosException(e);
		}
	}
}
