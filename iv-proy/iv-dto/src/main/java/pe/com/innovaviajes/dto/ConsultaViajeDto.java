/**
 * 
 */
package pe.com.innovaviajes.dto;

import java.util.Date;

/**
 * @author Edwin
 *
 */
public class ConsultaViajeDto {

	private String tipoViaje;
	private String codigoIataOrigen;
	private String codigoIataDestino;
	private String claseVuelo;
	private Date fechaIda;
	private Date fechaVuelta;
	private Integer adultos;
	private Integer ninos;
	private Integer infantes;
	
	
	/**
	 * @return the tipoViaje
	 */
	public String getTipoViaje() {
		return tipoViaje;
	}
	/**
	 * @param tipoViaje the tipoViaje to set
	 */
	public void setTipoViaje(String tipoViaje) {
		this.tipoViaje = tipoViaje;
	}
	/**
	 * @return the codigoIataOrigen
	 */
	public String getCodigoIataOrigen() {
		return codigoIataOrigen;
	}
	/**
	 * @param codigoIataOrigen the codigoIataOrigen to set
	 */
	public void setCodigoIataOrigen(String codigoIataOrigen) {
		this.codigoIataOrigen = codigoIataOrigen;
	}
	/**
	 * @return the codigoIataDestino
	 */
	public String getCodigoIataDestino() {
		return codigoIataDestino;
	}
	/**
	 * @param codigoIataDestino the codigoIataDestino to set
	 */
	public void setCodigoIataDestino(String codigoIataDestino) {
		this.codigoIataDestino = codigoIataDestino;
	}
	/**
	 * @return the claseVuelo
	 */
	public String getClaseVuelo() {
		return claseVuelo;
	}
	/**
	 * @param claseVuelo the claseVuelo to set
	 */
	public void setClaseVuelo(String claseVuelo) {
		this.claseVuelo = claseVuelo;
	}
	/**
	 * @return the fechaIda
	 */
	public Date getFechaIda() {
		return fechaIda;
	}
	/**
	 * @param fechaIda the fechaIda to set
	 */
	public void setFechaIda(Date fechaIda) {
		this.fechaIda = fechaIda;
	}
	/**
	 * @return the fechaVuelta
	 */
	public Date getFechaVuelta() {
		return fechaVuelta;
	}
	/**
	 * @param fechaVuelta the fechaVuelta to set
	 */
	public void setFechaVuelta(Date fechaVuelta) {
		this.fechaVuelta = fechaVuelta;
	}
	/**
	 * @return the adultos
	 */
	public Integer getAdultos() {
		return adultos;
	}
	/**
	 * @param adultos the adultos to set
	 */
	public void setAdultos(Integer adultos) {
		this.adultos = adultos;
	}
	/**
	 * @return the ninos
	 */
	public Integer getNinos() {
		return ninos;
	}
	/**
	 * @param ninos the ninos to set
	 */
	public void setNinos(Integer ninos) {
		this.ninos = ninos;
	}
	/**
	 * @return the infantes
	 */
	public Integer getInfantes() {
		return infantes;
	}
	/**
	 * @param infantes the infantes to set
	 */
	public void setInfantes(Integer infantes) {
		this.infantes = infantes;
	}
	
	
}
