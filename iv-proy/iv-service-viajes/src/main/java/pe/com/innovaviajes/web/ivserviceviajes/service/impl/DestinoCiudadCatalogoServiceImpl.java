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
import pe.com.innovaviajes.dto.AvionDto;
import pe.com.innovaviajes.dto.ConsultaViajeDto;
import pe.com.innovaviajes.dto.DestinoCiudadDto;
import pe.com.innovaviajes.dto.HorarioRutaDto;
import pe.com.innovaviajes.dto.HorarioVueloDto;
import pe.com.innovaviajes.dto.OfertaEncontrada;
import pe.com.innovaviajes.dto.PrecioOfertaDto;
import pe.com.innovaviajes.dto.RutaTramoDto;
import pe.com.innovaviajes.dto.TramoEscalaDto;
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
			aerolineaDto.setNombre("Azerbaijan Airlines");
			aerolineaDto.setNombreCorto("Azerbaijan A.");
			
			AvionDto avionTramo = new AvionDto();
			avionTramo.setId(1);
			avionTramo.setMarcaAvion("AirBus");
			avionTramo.setModeloAvion("A320 NEO");
			
			AeropuertoWebMostrarDto aeropuertoDestinoEscala = new AeropuertoWebMostrarDto();
			aeropuertoDestinoEscala.setCodigoIata("YOW");
			aeropuertoDestinoEscala.setDescripcion("Aeropuerto Internacional de Ottawa");
			aeropuertoDestinoEscala.setId(251);
			aeropuertoDestinoEscala.setNombreAeropuertoMostrar("Aeropuerto Internacional de Ottawa");
			aeropuertoDestinoEscala.setNombreCiudad("Otawa");
			aeropuertoDestinoEscala.setNombrePais("Canada");
			
			AeropuertoWebMostrarDto aeropuertoDestino = new AeropuertoWebMostrarDto();
			aeropuertoDestino.setCodigoIata(consultaViajeDto.getCodigoIataDestino());
			aeropuertoDestino.setDescripcion("Aeropuerto Internacional Jorge Chavez");
			aeropuertoDestino.setId(1);
			aeropuertoDestino.setIdEstadoRegistro(1);
			aeropuertoDestino.setNombreAeropuertoMostrar("Aeropuerto Internacional Jorge Chavez");
			aeropuertoDestino.setNombreCiudad("Lima");
			aeropuertoDestino.setNombrePais("Peru");
			
			AeropuertoWebMostrarDto aeropuertoOrigen = new AeropuertoWebMostrarDto();
			aeropuertoOrigen.setCodigoIata(consultaViajeDto.getCodigoIataOrigen());
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
			horarioRuta.setFechaLlegadaVuelo(UtilIvDto.parseStringADate("05/10/2022 14:11:00", Constantes.FORMAT_DATE_1, null));
			horarioRuta.setFechaModificacion(UtilIvDto.hoy());
			horarioRuta.setFechaRegistro(UtilIvDto.hoy());
			horarioRuta.setFechaSalidaVuelo(UtilIvDto.parseStringADate("05/10/2022 04:05:00", Constantes.FORMAT_DATE_1, null));
			horarioRuta.setIdEstadoRegistro(1);
			horarioRuta.setTimeZoneLlegada(UtilIvDto.obtenerTimeZoneLocal());
			horarioRuta.setTimeZoneOrigen(UtilIvDto.obtenerTimeZoneLocal());
			horarioRuta.setIdUsuarioModificacion(1);
			horarioRuta.setIdUsuarioRegistro(1);
			
			List<TramoEscalaDto> listaEscalas = new ArrayList<TramoEscalaDto>();
			
			HorarioVueloDto horarioVueloEscala = new HorarioVueloDto();
			horarioVueloEscala.setAerolineaDto(aerolineaDto);
			horarioVueloEscala.setFechaLlegadaVuelo(UtilIvDto.parseStringADate("05/10/2022 04:05:00", Constantes.FORMAT_DATE_1, null));
			horarioVueloEscala.setFechaSalidaVuelo(UtilIvDto.parseStringADate("05/10/2022 04:05:00", Constantes.FORMAT_DATE_1, null));
			horarioVueloEscala.setIdEstadoRegistro(1);
			
			TramoEscalaDto tramoEscala = new TramoEscalaDto();
			tramoEscala.setOrigen(aeropuertoOrigen);
			tramoEscala.setAvionTramo(avionTramo);
			tramoEscala.setDestino(aeropuertoDestinoEscala);
			tramoEscala.setHorarioVuelo(horarioVueloEscala);
			tramoEscala.setIdEstadoRegistro(1);
			tramoEscala.setNumeroEscala(1);
			tramoEscala.setNumeroVuelo("123");
			
			listaEscalas.add(tramoEscala);
			horarioRuta.setEscalas(listaEscalas);
			horarioRuta.setInEscalas(horarioRuta.getEscalas().isEmpty()?0:1);
			horarioRuta.setNumeroEscalas(horarioRuta.getInEscalas()==1?listaEscalas.size():0);
			
			List<HorarioRutaDto> horariosRuta = new ArrayList<HorarioRutaDto>();
			horariosRuta.add(horarioRuta);
			horariosRuta.add(horarioRuta);
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
			horariosRuta2.add(horarioRuta);
			
			List<RutaTramoDto> listaRutaTramos = new ArrayList<RutaTramoDto>();
			
			RutaTramoDto rutaTramo = new RutaTramoDto();
			rutaTramo.setDestino(aeropuertoDestino);
			rutaTramo.setNumeroOrden(1);
			rutaTramo.setOrigen(aeropuertoOrigen);
			rutaTramo.setHorariosRuta(horariosRuta);
			//rutaTramo.setFechaViaje(UtilIvDto.parseStringADate("05/11/2022 04:05:11", Constantes.FORMAT_DATE_1, null));
			rutaTramo.setFechaViaje(consultaViajeDto.getFechaIda());
			rutaTramo.setTipoViaje(1);
			listaRutaTramos.add(rutaTramo);
			
			rutaTramo = new RutaTramoDto();
			rutaTramo.setDestino(aeropuertoOrigen);
			rutaTramo.setNumeroOrden(2);
			rutaTramo.setOrigen(aeropuertoDestino);
			rutaTramo.setHorariosRuta(horariosRuta2);
			//rutaTramo.setFechaViaje(UtilIvDto.parseStringADate("05/11/2022 19:08:52", Constantes.FORMAT_DATE_1, null));
			rutaTramo.setFechaViaje(consultaViajeDto.getFechaVuelta());
			rutaTramo.setTipoViaje(2);
			listaRutaTramos.add(rutaTramo);
			
			BigDecimal porcentajeFee = BigDecimal.valueOf(0.05);
			
			BigDecimal totalBoletos = BigDecimal.ZERO;
			
			BigDecimal totalFee = BigDecimal.ZERO;//BigDecimal.valueOf(89.15);
			
			BigDecimal totalImptos = BigDecimal.valueOf(489.15);
			
			BigDecimal totalImptosCargos = BigDecimal.ZERO;
			
			PrecioOfertaDto precioOferta = new PrecioOfertaDto();
			precioOferta.setCantidadAdultos(consultaViajeDto.getAdultos().shortValue());
			precioOferta.setCantidadInfantes(consultaViajeDto.getInfantes().shortValue());
			precioOferta.setCantidadNinos(consultaViajeDto.getNinos().shortValue());
			precioOferta.setFechaModificacion(UtilIvDto.hoy());
			precioOferta.setFechaRegistro(UtilIvDto.hoy());
			precioOferta.setIdEstadoRegistro(1);
			precioOferta.setIdUsuarioModificacion(1);
			precioOferta.setIdUsuarioRegistro(1);
			precioOferta.setPorcentajePrecioInfantes(BigDecimal.TEN);
			precioOferta.setPorcentajePrecioNinos(BigDecimal.TEN);
			precioOferta.setPrecioUnitarioClase(BigDecimal.valueOf(998.15));
			precioOferta.setTotalFee(totalFee);
			precioOferta.setTotalImpuestos(totalImptos);
			
			BigDecimal totalPrecioAdultos = precioOferta.getPrecioUnitarioClase().multiply(UtilIvDto.parseBigDecimal(precioOferta.getCantidadAdultos()));
			precioOferta.setTotalPrecioAdultos(totalPrecioAdultos);
			
			BigDecimal totalPrecioInfantes = precioOferta.getPrecioUnitarioClase().multiply(UtilIvDto.parseBigDecimal(precioOferta.getCantidadInfantes()));
			totalPrecioInfantes = totalPrecioInfantes.multiply(precioOferta.getPorcentajePrecioInfantes());
			totalPrecioInfantes = totalPrecioInfantes.divide(BigDecimal.valueOf(100));
			precioOferta.setTotalPrecioInfantes(totalPrecioInfantes);
			
			BigDecimal totalPrecioNinos = precioOferta.getPrecioUnitarioClase().multiply(UtilIvDto.parseBigDecimal(precioOferta.getCantidadNinos()));
			totalPrecioNinos = totalPrecioNinos.multiply(precioOferta.getPorcentajePrecioNinos());
			totalPrecioNinos = totalPrecioNinos.divide(BigDecimal.valueOf(100));
			precioOferta.setTotalPrecioNinos(totalPrecioNinos);
			
			totalBoletos = totalBoletos.add(totalPrecioAdultos).add(totalPrecioNinos).add(totalPrecioInfantes);
			
			totalFee = totalBoletos.multiply(porcentajeFee);
			
			totalImptosCargos = totalImptosCargos.add(totalImptos).add(totalFee);
			
			precioOferta.setTotalImptosCargos(totalImptosCargos);
			
			BigDecimal totalRuta = UtilIvDto.redondeA2(totalBoletos.add(totalImptosCargos));
			precioOferta.setTotalRuta(totalRuta);
			
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
