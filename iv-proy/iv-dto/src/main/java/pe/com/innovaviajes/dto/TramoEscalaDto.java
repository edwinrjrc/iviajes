/**
 * 
 */
package pe.com.innovaviajes.dto;

/**
 * @author Edwin
 *
 */
public class TramoEscalaDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6552023101750950923L;
	
	private AeropuertoWebMostrarDto origen;

	private AeropuertoWebMostrarDto destino;
	
	private HorarioVueloDto horarioVuelo;
	
	private AvionDto avionTramo;
	
	private String numeroVuelo;

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
	 * @return the horarioVuelo
	 */
	public HorarioVueloDto getHorarioVuelo() {
		return horarioVuelo;
	}

	/**
	 * @param horarioVuelo the horarioVuelo to set
	 */
	public void setHorarioVuelo(HorarioVueloDto horarioVuelo) {
		this.horarioVuelo = horarioVuelo;
	}

	/**
	 * @return the avionTramo
	 */
	public AvionDto getAvionTramo() {
		return avionTramo;
	}

	/**
	 * @param avionTramo the avionTramo to set
	 */
	public void setAvionTramo(AvionDto avionTramo) {
		this.avionTramo = avionTramo;
	}

	/**
	 * @return the numeroVuelo
	 */
	public String getNumeroVuelo() {
		return numeroVuelo;
	}

	/**
	 * @param numeroVuelo the numeroVuelo to set
	 */
	public void setNumeroVuelo(String numeroVuelo) {
		this.numeroVuelo = numeroVuelo;
	}

}
