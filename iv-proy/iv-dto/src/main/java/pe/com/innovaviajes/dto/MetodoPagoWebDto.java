/**
 * 
 */
package pe.com.innovaviajes.dto;

/**
 * @author Edwin
 *
 */
public class MetodoPagoWebDto {

	private String numeroTarjeta;
	private String fechaVencimiento;
	private String codigoSeguridad;
	private String nombreTitular;
	
	
	/**
	 * @return the numeroTarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	/**
	 * @param numeroTarjeta the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	/**
	 * @return the codigoSeguridad
	 */
	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}
	/**
	 * @param codigoSeguridad the codigoSeguridad to set
	 */
	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}
	/**
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}
	/**
	 * @param nombreTitular the nombreTitular to set
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	
	

}
