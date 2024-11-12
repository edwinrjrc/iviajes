/**
 * 
 */
package pe.com.innovaviajes.dto;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author Edwin
 *
 */
public class TramoBaseDto extends BaseDto {

	private static final long serialVersionUID = -3018813436576509422L;

	private Date fechaSalidaVuelo;

	private TimeZone timeZoneOrigen;

	private Date fechaLlegadaVuelo;

	private TimeZone timeZoneLlegada;

	private AerolineaDto aerolineaDto;

	private boolean equipaMochila;

	private boolean equipaCarrion;

	private boolean equipaBodega;

	private boolean equipaBodegaEjecutivo;

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
	 * @return the equipaMochila
	 */
	public boolean isEquipaMochila() {
		return equipaMochila;
	}

	/**
	 * @param equipaMochila the equipaMochila to set
	 */
	public void setEquipaMochila(boolean equipaMochila) {
		this.equipaMochila = equipaMochila;
	}

	/**
	 * @return the equipaCarrion
	 */
	public boolean isEquipaCarrion() {
		return equipaCarrion;
	}

	/**
	 * @param equipaCarrion the equipaCarrion to set
	 */
	public void setEquipaCarrion(boolean equipaCarrion) {
		this.equipaCarrion = equipaCarrion;
	}

	/**
	 * @return the equipaBodega
	 */
	public boolean isEquipaBodega() {
		return equipaBodega;
	}

	/**
	 * @param equipaBodega the equipaBodega to set
	 */
	public void setEquipaBodega(boolean equipaBodega) {
		this.equipaBodega = equipaBodega;
	}

	/**
	 * @return the equipaBodegaEjecutivo
	 */
	public boolean isEquipaBodegaEjecutivo() {
		return equipaBodegaEjecutivo;
	}

	/**
	 * @param equipaBodegaEjecutivo the equipaBodegaEjecutivo to set
	 */
	public void setEquipaBodegaEjecutivo(boolean equipaBodegaEjecutivo) {
		this.equipaBodegaEjecutivo = equipaBodegaEjecutivo;
	}

}
