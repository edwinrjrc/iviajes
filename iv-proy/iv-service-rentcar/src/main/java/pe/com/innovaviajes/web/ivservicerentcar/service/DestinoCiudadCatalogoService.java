/**
 * 
 */
package pe.com.innovaviajes.web.ivservicerentcar.service;

import java.util.List;

import pe.com.innovaviajes.web.ivservicerentcar.dto.VuelosEncontrados;
import pe.com.innovaviajes.web.ivservicerentcar.exception.IvServiceRentCarException;
import pe.com.innovaviajes.web.ivservicerentcar.dto.ConsultaViajeDto;
import pe.com.innovaviajes.web.ivservicerentcar.dto.AeropuertoWebMostrarDto;

/**
 * @author Edwin
 *
 */
public interface DestinoCiudadCatalogoService {

	public List<AeropuertoWebMostrarDto> consultaDestinos(String nombreDestino) throws IvServiceRentCarException;
	
	public VuelosEncontrados consultarVuelos(ConsultaViajeDto consultaViajeDto) throws IvServiceRentCarException;
}
