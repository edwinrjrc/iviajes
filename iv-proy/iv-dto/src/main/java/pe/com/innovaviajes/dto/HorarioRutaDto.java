/**
 * 
 */
package pe.com.innovaviajes.dto;

/**
 * @author Edwin
 *
 */
public class HorarioRutaDto extends HorarioVueloDto {

	private static final long serialVersionUID = -204214283301368793L;

	private boolean equipaMochila;

	private boolean equipaCarrion;

	private boolean equipaBodega;

	private boolean equipaBodegaEjecutivo;


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
