/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.service;

import java.util.List;

import pe.com.innovaviajes.web.ivserviceviajes.dto.AeropuertoWebMostrarDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.ConsultaViajeDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.VuelosEncontrados;
import pe.com.innovaviajes.web.ivserviceviajes.exception.IvServiceDestinoCiudadException;
import pe.com.innovaviajes.web.ivserviceviajes.exception.UtilIvDtoException;

/**
 * @author Edwin
 *
 */
public interface DestinoCiudadCatalogoService {

	public List<AeropuertoWebMostrarDto> consultaDestinos(String nombreDestino) throws IvServiceDestinoCiudadException;
	
	public VuelosEncontrados consultarVuelos(ConsultaViajeDto consultaViajeDto) throws UtilIvDtoException;
}
