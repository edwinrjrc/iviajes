/**
 * 
 */
package pe.com.innovaviajes.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Edwin
 *
 */
public class HorarioVueloDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3051537809679165287L;

	private Date fechaSalidaVuelo;

	private TimeZone timeZoneOrigen;

	private Date fechaLlegadaVuelo;

	private TimeZone timeZoneLlegada;

	private AerolineaDto aerolineaDto;
	
	private List<TramoEscalaDto> escalas;
	
	private Integer inEscalas;
	
	private Integer numeroEscalas;

	/**
	 * @return the fechaSalidaVuelo
	 */
	public Date getFechaSalidaVuelo() {
		return fechaSalidaVuelo;
	}

	/**
	 * @param fechaSalidaVuelo the fechaSalidaVuelo to set
	 */
	public void setFechaSalidaVuelo(Date fechaSalidaVuelo) {
		this.fechaSalidaVuelo = fechaSalidaVuelo;
	}

	/**
	 * @return the timeZoneOrigen
	 */
	public TimeZone getTimeZoneOrigen() {
		return timeZoneOrigen;
	}

	/**
	 * @param timeZoneOrigen the timeZoneOrigen to set
	 */
	public void setTimeZoneOrigen(TimeZone timeZoneOrigen) {
		this.timeZoneOrigen = timeZoneOrigen;
	}

	/**
	 * @return the fechaLlegadaVuelo
	 */
	public Date getFechaLlegadaVuelo() {
		return fechaLlegadaVuelo;
	}

	/**
	 * @param fechaLlegadaVuelo the fechaLlegadaVuelo to set
	 */
	public void setFechaLlegadaVuelo(Date fechaLlegadaVuelo) {
		this.fechaLlegadaVuelo = fechaLlegadaVuelo;
	}

	/**
	 * @return the timeZoneLlegada
	 */
	public TimeZone getTimeZoneLlegada() {
		return timeZoneLlegada;
	}

	/**
	 * @param timeZoneLlegada the timeZoneLlegada to set
	 */
	public void setTimeZoneLlegada(TimeZone timeZoneLlegada) {
		this.timeZoneLlegada = timeZoneLlegada;
	}

	/**
	 * @return the aerolineaDto
	 */
	public AerolineaDto getAerolineaDto() {
		return aerolineaDto;
	}

	/**
	 * @param aerolineaDto the aerolineaDto to set
	 */
	public void setAerolineaDto(AerolineaDto aerolineaDto) {
		this.aerolineaDto = aerolineaDto;
	}

	/**
	 * @return the escalas
	 */
	public List<TramoEscalaDto> getEscalas() {
		if (escalas == null) {
			escalas = new ArrayList<TramoEscalaDto>();
		}
		return escalas;
	}

	/**
	 * @param escalas the escalas to set
	 */
	public void setEscalas(List<TramoEscalaDto> escalas) {
		this.escalas = escalas;
	}

	/**
	 * @return the inEscalas
	 */
	public Integer getInEscalas() {
		boolean vacio = getEscalas().isEmpty();
		inEscalas = 0;
		if (!vacio) {
			inEscalas = 1;
		}
		return inEscalas;
	}

	/**
	 * @param inEscalas the inEscalas to set
	 */
	public void setInEscalas(Integer inEscalas) {
		this.inEscalas = inEscalas;
	}

	/**
	 * @return the numeroEscalas
	 */
	public Integer getNumeroEscalas() {
		numeroEscalas = 0;
		if (getInEscalas().intValue() == 1) {
			numeroEscalas = getEscalas().size();
		}
		
		return numeroEscalas;
	}

	/**
	 * @param numeroEscalas the numeroEscalas to set
	 */
	public void setNumeroEscalas(Integer numeroEscalas) {
		this.numeroEscalas = numeroEscalas;
	}
}
