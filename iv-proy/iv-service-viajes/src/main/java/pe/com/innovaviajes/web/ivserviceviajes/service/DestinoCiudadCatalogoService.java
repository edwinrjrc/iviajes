/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.service;

import java.util.List;

import pe.com.innovaviajes.dto.AeropuertoWebMostrarDto;
import pe.com.innovaviajes.dto.ConsultaViajeDto;
import pe.com.innovaviajes.dto.VuelosEncontrados;
import pe.com.innovaviajes.web.ivserviceviajes.exception.IvServiceDestinoCiudadException;

/**
 * @author Edwin
 *
 */
public interface DestinoCiudadCatalogoService {

	public List<AeropuertoWebMostrarDto> consultaDestinos(String nombreDestino) throws IvServiceDestinoCiudadException;
	
	public VuelosEncontrados consultarVuelos(ConsultaViajeDto consultaViajeDto) throws IvServiceDestinoCiudadException;
}
