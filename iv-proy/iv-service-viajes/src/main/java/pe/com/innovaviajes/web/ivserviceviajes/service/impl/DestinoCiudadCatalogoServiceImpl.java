/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import pe.com.innovaviajes.web.ivserviceviajes.exception.UtilIvDtoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.innovaviajes.web.ivserviceviajes.dto.AerolineaDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.AeropuertoWebMostrarDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.AeropuertoWebDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.DestinoCiudadDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.AvionDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.ConsultaViajeDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.HorarioRutaDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.HorarioVueloDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.OfertaEncontrada;
import pe.com.innovaviajes.web.ivserviceviajes.dto.PrecioOfertaDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.RutaTramoDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.TramoEscalaDto;
import pe.com.innovaviajes.web.ivserviceviajes.dto.VuelosEncontrados;
import pe.com.innovaviajes.web.ivserviceviajes.exception.IvServiceDestinoCiudadException;
import pe.com.innovaviajes.web.ivserviceviajes.service.DestinoCiudadCatalogoService;
import pe.com.innovaviajes.web.ivserviceviajes.service.remote.RemoteServiceDestinoCiudad;
import pe.com.innovaviajes.web.ivserviceviajes.util.Constantes;
import pe.com.innovaviajes.web.ivserviceviajes.util.UtilIvDto;
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
									aeropuerto = UtilServiceViajes.parseDestino(aeropuertoWebDto, destinoCiudadDto)
											.getNombreAeropuertoMostrar();
									if (StringUtils.isNotBlank(aeropuerto)) {
										listaAeropuertos.add(
												UtilServiceViajes.parseDestino(aeropuertoWebDto, destinoCiudadDto));
									}
								}
							}
						}
					}
				}
			}

			return listaAeropuertos;
		} catch (IvServiceDestinoCiudadException e) {
			log.error(e.getMessage(), e);
			throw new IvServiceDestinoCiudadException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new IvServiceDestinoCiudadException(e);
		}
	}

	@Override
	public VuelosEncontrados consultarVuelos(ConsultaViajeDto consultaViajeDto) throws UtilIvDtoException {
		VuelosEncontrados vuelosEncontrados = new VuelosEncontrados();

		vuelosEncontrados.setOfertasEncontradas(agregaOfertas(consultaViajeDto));

		return vuelosEncontrados;
	}

	private List<OfertaEncontrada> agregaOfertas(ConsultaViajeDto consultaViajeDto) throws UtilIvDtoException {
		List<OfertaEncontrada> listaOfertasEncontradas = new ArrayList<>();

		listaOfertasEncontradas.add(this.generaOferta(1, consultaViajeDto));
		listaOfertasEncontradas.add(this.generaOferta(2, consultaViajeDto));
		listaOfertasEncontradas.add(this.generaOferta(3, consultaViajeDto));

		return listaOfertasEncontradas;
	}

	private OfertaEncontrada generaOferta(int idOferta, ConsultaViajeDto consultaViajeDto) throws UtilIvDtoException {
		OfertaEncontrada ofertaEncontrada = new OfertaEncontrada();

		ofertaEncontrada.setId(idOferta);
		ofertaEncontrada.setListaRutaTramos(this.generaListaTramos(consultaViajeDto));
		ofertaEncontrada.setPrecioOfertaDto(this.generarPrecioOferta(consultaViajeDto));

		return ofertaEncontrada;
	}

	private List<RutaTramoDto> generaListaTramos(ConsultaViajeDto consultaViajeDto) throws UtilIvDtoException {
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
		horarioRuta.setAerolineaDto(generaAerolinea());
		horarioRuta.setEquipaBodega(true);
		horarioRuta.setEquipaBodegaEjecutivo(true);
		horarioRuta.setEquipaCarrion(true);
		horarioRuta.setEquipaMochila(true);
		horarioRuta.setFechaLlegadaVuelo(
				UtilIvDto.parseStringADate("05/10/2022 14:11:00", Constantes.FORMAT_DATE_1, null));
		horarioRuta.setFechaModificacion(UtilIvDto.hoy());
		horarioRuta.setFechaRegistro(UtilIvDto.hoy());
		horarioRuta
				.setFechaSalidaVuelo(UtilIvDto.parseStringADate("05/10/2022 04:05:00", Constantes.FORMAT_DATE_1, null));
		horarioRuta.setIdEstadoRegistro(1);
		horarioRuta.setTimeZoneLlegada(UtilIvDto.obtenerTimeZoneLocal());
		horarioRuta.setTimeZoneOrigen(UtilIvDto.obtenerTimeZoneLocal());
		horarioRuta.setIdUsuarioModificacion(1);
		horarioRuta.setIdUsuarioRegistro(1);
		horarioRuta.setId(100);

		List<RutaTramoDto> listaRutaTramos = new ArrayList<RutaTramoDto>();

		listaRutaTramos.add(generaRutaTramo(1, 1));

		listaRutaTramos.add(generaRutaTramo(2, 2));

		return listaRutaTramos;
	}

	private AerolineaDto generaAerolinea() {
		AerolineaDto aerolineaDto = new AerolineaDto();
		aerolineaDto.setCodigoIata("LIM");
		aerolineaDto.setId(1);
		aerolineaDto.setNombre("Azerbaijan Airlines");
		aerolineaDto.setNombreCorto("Azerbaijan A.");

		return aerolineaDto;
	}

	private AvionDto generaAvion() {
		AvionDto avionTramo = new AvionDto();
		avionTramo.setId(1);
		avionTramo.setMarcaAvion("AirBus");
		avionTramo.setModeloAvion("A320 NEO");

		return avionTramo;
	}

	private AeropuertoWebMostrarDto generaAeropuerto(int idAeropuerto) {
		AeropuertoWebMostrarDto aeropuertoUsado = null;
		if (idAeropuerto == 1) {
			aeropuertoUsado = new AeropuertoWebMostrarDto();
			aeropuertoUsado.setCodigoIata("YOW");
			aeropuertoUsado.setDescripcion("Aeropuerto Internacional de Ottawa");
			aeropuertoUsado.setId(251);
			aeropuertoUsado.setNombreAeropuertoMostrar("Aeropuerto Internacional de Ottawa");
			aeropuertoUsado.setNombreCiudad("Otawa");
			aeropuertoUsado.setNombrePais("Canada");
		} else if (idAeropuerto == 2) {
			aeropuertoUsado = new AeropuertoWebMostrarDto();
			aeropuertoUsado.setCodigoIata("ATY");
			aeropuertoUsado.setDescripcion("Aeropuerto Internacional Jorge Chavez");
			aeropuertoUsado.setId(1);
			aeropuertoUsado.setIdEstadoRegistro(1);
			aeropuertoUsado.setNombreAeropuertoMostrar("Aeropuerto Internacional Jorge Chavez");
			aeropuertoUsado.setNombreCiudad("Lima");
			aeropuertoUsado.setNombrePais("Peru");
		} else if (idAeropuerto == 3) {
			aeropuertoUsado = new AeropuertoWebMostrarDto();
			aeropuertoUsado.setCodigoIata("HYT");
			aeropuertoUsado.setDescripcion("Aeropuerto Internacional Capitán FAP Víctor Montes Arias");
			aeropuertoUsado.setId(1);
			aeropuertoUsado.setNombreAeropuertoMostrar("Aeropuerto Internacional Capitán FAP Víctor Montes Arias");
			aeropuertoUsado.setNombreCiudad("Talara");
			aeropuertoUsado.setNombrePais("Peru");
		}

		return aeropuertoUsado;
	}

	private TramoEscalaDto generaTramoEscala() throws UtilIvDtoException {
		HorarioVueloDto horarioVueloEscala = new HorarioVueloDto();
		horarioVueloEscala.setAerolineaDto(generaAerolinea());
		horarioVueloEscala.setFechaLlegadaVuelo(
				UtilIvDto.parseStringADate("05/10/2022 04:05:00", Constantes.FORMAT_DATE_1, null));
		horarioVueloEscala
				.setFechaSalidaVuelo(UtilIvDto.parseStringADate("05/10/2022 04:05:00", Constantes.FORMAT_DATE_1, null));
		horarioVueloEscala.setIdEstadoRegistro(1);

		TramoEscalaDto tramoEscala = new TramoEscalaDto();
		tramoEscala.setOrigen(generaAeropuerto(1));
		tramoEscala.setAvionTramo(generaAvion());
		tramoEscala.setDestino(generaAeropuerto(2));
		tramoEscala.setHorarioVuelo(horarioVueloEscala);
		tramoEscala.setIdEstadoRegistro(1);
		tramoEscala.setNumeroEscala(1);
		tramoEscala.setNumeroVuelo("123");

		return tramoEscala;
	}

	private RutaTramoDto generaRutaTramo(int idRutaTramo, int idTipoViaje) throws UtilIvDtoException {
		List<HorarioRutaDto> horariosRuta = new ArrayList<HorarioRutaDto>();
		horariosRuta.add(this.generaHorarioRuta(100));
		horariosRuta.add(this.generaHorarioRuta(102));
		horariosRuta.add(this.generaHorarioRuta(103));

		RutaTramoDto rutaTramo = new RutaTramoDto();
		rutaTramo.setDestino(this.generaAeropuerto(1));
		rutaTramo.setNumeroOrden(1);
		rutaTramo.setOrigen(this.generaAeropuerto(2));
		rutaTramo.setHorariosRuta(horariosRuta);
		rutaTramo.setFechaViaje(UtilIvDto.parseStringADate("05/10/2022 14:11:00", Constantes.FORMAT_DATE_1, null));
		rutaTramo.setTipoViaje(idTipoViaje);
		rutaTramo.setId(idRutaTramo);

		return rutaTramo;
	}

	private HorarioRutaDto generaHorarioRuta(int idHorarioRuta) throws UtilIvDtoException {
		HorarioRutaDto horarioRuta = new HorarioRutaDto();
		horarioRuta.setAerolineaDto(this.generaAerolinea());
		horarioRuta.setEquipaBodega(true);
		horarioRuta.setEquipaBodegaEjecutivo(true);
		horarioRuta.setEquipaCarrion(true);
		horarioRuta.setEquipaMochila(true);
		horarioRuta.setFechaSalidaVuelo(generaFechaHoraRuta());
		horarioRuta.setFechaLlegadaVuelo(generaFechaHoraLlegada(horarioRuta.getFechaSalidaVuelo()));
		horarioRuta.setFechaModificacion(UtilIvDto.hoy());
		horarioRuta.setFechaRegistro(UtilIvDto.hoy());
		horarioRuta.setIdEstadoRegistro(1);
		horarioRuta.setTimeZoneLlegada(UtilIvDto.obtenerTimeZoneLocal());
		horarioRuta.setTimeZoneOrigen(UtilIvDto.obtenerTimeZoneLocal());
		horarioRuta.setIdUsuarioModificacion(1);
		horarioRuta.setIdUsuarioRegistro(1);
		horarioRuta.setId(idHorarioRuta);

		return horarioRuta;
	}

	private PrecioOfertaDto generarPrecioOferta(ConsultaViajeDto consultaViajeDto) throws UtilIvDtoException {
		BigDecimal porcentajeFee = BigDecimal.valueOf(0.05);

		BigDecimal totalBoletos = BigDecimal.ZERO;

		BigDecimal totalFee = BigDecimal.ZERO;// BigDecimal.valueOf(89.15);

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

		BigDecimal totalPrecioAdultos = precioOferta.getPrecioUnitarioClase()
				.multiply(UtilIvDto.parseBigDecimal(precioOferta.getCantidadAdultos()));
		precioOferta.setTotalPrecioAdultos(totalPrecioAdultos);

		BigDecimal totalPrecioInfantes = precioOferta.getPrecioUnitarioClase()
				.multiply(UtilIvDto.parseBigDecimal(precioOferta.getCantidadInfantes()));
		totalPrecioInfantes = totalPrecioInfantes.multiply(precioOferta.getPorcentajePrecioInfantes());
		totalPrecioInfantes = totalPrecioInfantes.divide(BigDecimal.valueOf(100));
		precioOferta.setTotalPrecioInfantes(totalPrecioInfantes);

		BigDecimal totalPrecioNinos = precioOferta.getPrecioUnitarioClase()
				.multiply(UtilIvDto.parseBigDecimal(precioOferta.getCantidadNinos()));
		totalPrecioNinos = totalPrecioNinos.multiply(precioOferta.getPorcentajePrecioNinos());
		totalPrecioNinos = totalPrecioNinos.divide(BigDecimal.valueOf(100));
		precioOferta.setTotalPrecioNinos(totalPrecioNinos);

		totalBoletos = totalBoletos.add(totalPrecioAdultos).add(totalPrecioNinos).add(totalPrecioInfantes);

		totalFee = totalBoletos.multiply(porcentajeFee);

		totalImptosCargos = totalImptosCargos.add(totalImptos).add(totalFee);

		precioOferta.setTotalImptosCargos(totalImptosCargos);

		BigDecimal totalRuta = UtilIvDto.redondeA2(totalBoletos.add(totalImptosCargos));
		precioOferta.setTotalRuta(totalRuta);

		return precioOferta;
	}

	private Date generaFechaHoraRuta() throws UtilIvDtoException {
		
		String fecha = "20/05/2027 14:16:05";
		
		Date fechaHora = UtilIvDto.parseStringADate(fecha, Constantes.FORMAT_DATE_1, null);
		return fechaHora;
	}

	private Date generaFechaHoraLlegada(Date fechaHoraSalida) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(fechaHoraSalida);
		
		cal.add(Calendar.DATE, 123);
		cal.add(Calendar.MINUTE, 1600);

		return cal.getTime();
	}
}
