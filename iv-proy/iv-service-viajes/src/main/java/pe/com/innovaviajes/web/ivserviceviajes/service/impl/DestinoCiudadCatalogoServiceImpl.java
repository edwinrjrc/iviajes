/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.service.impl;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.innovaviajes.cross.util.Constantes;
import pe.com.innovaviajes.cross.util.UtilIvDto;
import pe.com.innovaviajes.dto.AerolineaDto;
import pe.com.innovaviajes.dto.AeropuertoWebDto;
import pe.com.innovaviajes.dto.AeropuertoWebMostrarDto;
import pe.com.innovaviajes.dto.ConsultaViajeDto;
import pe.com.innovaviajes.dto.DestinoCiudadDto;
import pe.com.innovaviajes.dto.HorarioRutaDto;
import pe.com.innovaviajes.dto.OfertaEncontrada;
import pe.com.innovaviajes.dto.PrecioOfertaDto;
import pe.com.innovaviajes.dto.RutaTramoDto;
import pe.com.innovaviajes.dto.VuelosEncontrados;
import pe.com.innovaviajes.exception.UtilIvDtoException;
import pe.com.innovaviajes.web.ivserviceviajes.exception.IvServiceDestinoCiudadException;
import pe.com.innovaviajes.web.ivserviceviajes.service.DestinoCiudadCatalogoService;
import pe.com.innovaviajes.web.ivserviceviajes.service.remote.RemoteServiceDestinoCiudad;
import pe.com.innovaviajes.web.ivserviceviajes.util.UtilServiceViajes;

/**
 * @author Edwin
 *
 */

@Service
public class DestinoCiudadCatalogoServiceImpl implements DestinoCiudadCatalogoService {
	
	private static final Logger log = LoggerFactory.getLogger(DestinoCiudadCatalogoServiceImpl.class);
	
	@Autowired
	private RemoteServiceDestinoCiudad remoteServiceDestinoCiudad;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() throws IvServiceDestinoCiudadException {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
			
			return restTemplate;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new IvServiceDestinoCiudadException(e);
		}
	}
	
	@Override
	public List<AeropuertoWebMostrarDto> consultaDestinos(String nombreDestino) throws IvServiceDestinoCiudadException {
		try {
			List<DestinoCiudadDto> listaDestinos = remoteServiceDestinoCiudad.consultarDestinos(nombreDestino);
			List<AeropuertoWebMostrarDto> listaAeropuertos = null;
			String aeropuerto = "";
			
			if (UtilIvDto.listaNoVacia(listaDestinos)) {
				listaAeropuertos = new ArrayList<AeropuertoWebMostrarDto>();
				
				for (DestinoCiudadDto destinoCiudadDto : listaDestinos) {
					if (destinoCiudadDto != null) {
						
						List<AeropuertoWebDto> listaAeropuertos2 = destinoCiudadDto.getListaAeropuertosDto();
						if (UtilIvDto.listaNoVacia(listaAeropuertos2)) {
							for (AeropuertoWebDto aeropuertoWebDto : listaAeropuertos2) {
								if (aeropuertoWebDto != null) {
									aeropuerto = UtilServiceViajes.parseDestino(aeropuertoWebDto, destinoCiudadDto).getNombreAeropuertoMostrar();
									if (StringUtils.isNotBlank(aeropuerto)) {
										listaAeropuertos.add(UtilServiceViajes.parseDestino(aeropuertoWebDto, destinoCiudadDto));
									}
								}
							}
						}
					}
				}
			}
			
			return listaAeropuertos;
		} catch (IvServiceDestinoCiudadException e) {
			log.error(e.getMessage(),e);
			throw new IvServiceDestinoCiudadException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new IvServiceDestinoCiudadException(e);
		}
	}

	@Override
	public VuelosEncontrados consultarVuelos(ConsultaViajeDto consultaViajeDto) throws IvServiceDestinoCiudadException {
		try {
			VuelosEncontrados vuelosEncontrados = new VuelosEncontrados();
			
			List<OfertaEncontrada> listaOfertasEncontradas = new ArrayList<OfertaEncontrada>();
			
			OfertaEncontrada oferta1 = new OfertaEncontrada();

			AerolineaDto aerolineaDto = new AerolineaDto();
			aerolineaDto.setCodigoIata("LIM");
			aerolineaDto.setId(1);
			aerolineaDto.setNombre("LATAM Airlines");
			
			AeropuertoWebMostrarDto aeropuertoDestino = new AeropuertoWebMostrarDto();
			aeropuertoDestino.setCodigoIata("LIM");
			aeropuertoDestino.setDescripcion("Aeropuerto Internacional Jorge Chavez");
			aeropuertoDestino.setId(1);
			aeropuertoDestino.setIdEstadoRegistro(1);
			aeropuertoDestino.setNombreAeropuertoMostrar("Aeropuerto Internacional Jorge Chavez");
			aeropuertoDestino.setNombreCiudad("Lima");
			aeropuertoDestino.setNombrePais("Peru");
			
			AeropuertoWebMostrarDto aeropuertoOrigen = new AeropuertoWebMostrarDto();
			aeropuertoOrigen.setCodigoIata("TYL");
			aeropuertoOrigen.setDescripcion("Aeropuerto Internacional Capitán FAP Víctor Montes Arias");
			aeropuertoOrigen.setId(1);
			aeropuertoOrigen.setNombreAeropuertoMostrar("Aeropuerto Internacional Capitán FAP Víctor Montes Arias");
			aeropuertoOrigen.setNombreCiudad("Talara");
			aeropuertoOrigen.setNombrePais("Peru");
			
			HorarioRutaDto horarioRuta = new HorarioRutaDto();
			horarioRuta.setAerolineaDto(aerolineaDto);
			horarioRuta.setEquipaBodega(true);
			horarioRuta.setEquipaBodegaEjecutivo(true);
			horarioRuta.setEquipaCarrion(true);
			horarioRuta.setEquipaMochila(true);
			horarioRuta.setFechaLlegadaVuelo(UtilIvDto.parseStringADate("05/10/2022 14:11:52", Constantes.FORMAT_DATE_1, null));
			horarioRuta.setFechaModificacion(UtilIvDto.hoy());
			horarioRuta.setFechaRegistro(UtilIvDto.hoy());
			horarioRuta.setFechaSalidaVuelo(UtilIvDto.parseStringADate("05/10/2022 04:05:11", Constantes.FORMAT_DATE_1, null));
			horarioRuta.setIdEstadoRegistro(1);
			horarioRuta.setTimeZoneLlegada(UtilIvDto.obtenerTimeZoneLocal());
			horarioRuta.setTimeZoneOrigen(UtilIvDto.obtenerTimeZoneLocal());
			horarioRuta.setIdUsuarioModificacion(1);
			horarioRuta.setIdUsuarioRegistro(1);
			
			List<HorarioRutaDto> horariosRuta = new ArrayList<HorarioRutaDto>();
			horariosRuta.add(horarioRuta);
			
			List<HorarioRutaDto> horariosRuta2 = new ArrayList<HorarioRutaDto>();
			horarioRuta = new HorarioRutaDto();
			horarioRuta.setAerolineaDto(aerolineaDto);
			horarioRuta.setEquipaBodega(true);
			horarioRuta.setEquipaBodegaEjecutivo(true);
			horarioRuta.setEquipaCarrion(true);
			horarioRuta.setEquipaMochila(true);
			horarioRuta.setFechaLlegadaVuelo(UtilIvDto.parseStringADate("05/11/2022 13:23:52", Constantes.FORMAT_DATE_1, null));
			horarioRuta.setFechaModificacion(UtilIvDto.hoy());
			horarioRuta.setFechaRegistro(UtilIvDto.hoy());
			horarioRuta.setFechaSalidaVuelo(UtilIvDto.parseStringADate("05/11/2022 04:05:11", Constantes.FORMAT_DATE_1, null));
			horarioRuta.setIdEstadoRegistro(1);
			horarioRuta.setTimeZoneLlegada(UtilIvDto.obtenerTimeZoneLocal());
			horarioRuta.setTimeZoneOrigen(UtilIvDto.obtenerTimeZoneLocal());
			horarioRuta.setIdUsuarioModificacion(1);
			horarioRuta.setIdUsuarioRegistro(1);
			horariosRuta2.add(horarioRuta);
			
			List<RutaTramoDto> listaRutaTramos = new ArrayList<RutaTramoDto>();
			
			RutaTramoDto rutaTramo = new RutaTramoDto();
			rutaTramo.setDestino(aeropuertoDestino);
			rutaTramo.setNumeroOrden(1);
			rutaTramo.setOrigen(aeropuertoOrigen);
			rutaTramo.setHorariosRuta(horariosRuta);
			rutaTramo.setFechaViaje(UtilIvDto.parseStringADate("05/11/2022 04:05:11", Constantes.FORMAT_DATE_1, null));
			rutaTramo.setTipoViaje(1);
			listaRutaTramos.add(rutaTramo);
			
			rutaTramo = new RutaTramoDto();
			rutaTramo.setDestino(aeropuertoOrigen);
			rutaTramo.setNumeroOrden(2);
			rutaTramo.setOrigen(aeropuertoDestino);
			rutaTramo.setHorariosRuta(horariosRuta2);
			rutaTramo.setFechaViaje(UtilIvDto.parseStringADate("05/11/2022 19:08:52", Constantes.FORMAT_DATE_1, null));
			rutaTramo.setTipoViaje(2);
			listaRutaTramos.add(rutaTramo);
			
			PrecioOfertaDto precioOferta = new PrecioOfertaDto();
			precioOferta.setCantidadAdultos((short) 1);
			precioOferta.setCantidadInfantes((short) 0);
			precioOferta.setCantidadNinos((short) 0);
			precioOferta.setFechaModificacion(UtilIvDto.hoy());
			precioOferta.setFechaRegistro(UtilIvDto.hoy());
			precioOferta.setIdEstadoRegistro(1);
			precioOferta.setIdUsuarioModificacion(1);
			precioOferta.setIdUsuarioRegistro(1);
			precioOferta.setPorcentajePrecioInfantes(BigDecimal.TEN);
			precioOferta.setPorcentajePrecioNinos(BigDecimal.TEN);
			precioOferta.setPrecioUnitarioClase(BigDecimal.valueOf(998.15));
			precioOferta.setTotalFee(BigDecimal.valueOf(89.15));
			precioOferta.setTotalImpuestos(BigDecimal.valueOf(489.15));
			
			oferta1.setListaRutaTramos(listaRutaTramos);
			oferta1.setPrecioOfertaDto(precioOferta);
			
			listaOfertasEncontradas.add(oferta1);
			
			vuelosEncontrados.setOfertasEncontradas(listaOfertasEncontradas);
			
			return vuelosEncontrados;
		} catch (UtilIvDtoException e) {
			log.error(e.getMessage(),e);
			throw new IvServiceDestinoCiudadException(e);
		}
	}

}
