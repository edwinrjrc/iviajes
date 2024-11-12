/**
 * 
 */
package pe.com.innovaviajes.dto;

import java.util.Date;
import java.util.List;

/**
 * @author Edwin
 *
 */
public class RutaTramoDto extends BaseDto {

	private static final long serialVersionUID = -994908192316823446L;

	private AeropuertoWebMostrarDto origen;

	private AeropuertoWebMostrarDto destino;
	
	private List<HorarioRutaDto> horariosRuta;
	
	private Integer numeroOrden;
	
	private Date fechaViaje;
	
	private Integer tipoViaje;

	/**
	 * @return the origen
	 */
	public AeropuertoWebMostrarDto getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(AeropuertoWebMostrarDto origen) {
		this.origen = origen;
	}

	/**
	 * @return the destino
	 */
	public AeropuertoWebMostrarDto getDestino() {
		return destino;
	}

	/**
	 * @param destino the destino to set
	 */
	public void setDestino(AeropuertoWebMostrarDto destino) {
		this.destino = destino;
	}

	/**
	 * @return the horariosRuta
	 */
	public List<HorarioRutaDto> getHorariosRuta() {
		return horariosRuta;
	}

	/**
	 * @param horariosRuta the horariosRuta to set
	 */
	public void setHorariosRuta(List<HorarioRutaDto> horariosRuta) {
		this.horariosRuta = horariosRuta;
	}

	/**
	 * @return the numeroOrden
	 */
	public Integer getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * @param numeroOrden the numeroOrden to set
	 */
	public void setNumeroOrden(Integer numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * @return the fechaViaje
	 */
	public Date getFechaViaje() {
		return fechaViaje;
	}

	/**
	 * @param fechaViaje the fechaViaje to set
	 */
	public void setFechaViaje(Date fechaViaje) {
		this.fechaViaje = fechaViaje;
	}

	/**
	 * @return the tipoViaje
	 */
	public Integer getTipoViaje() {
		return tipoViaje;
	}

	/**
	 * @param tipoViaje the tipoViaje to set
	 */
	public void setTipoViaje(Integer tipoViaje) {
		this.tipoViaje = tipoViaje;
	}
}
