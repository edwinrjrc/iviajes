/**
 * 
 */
package pe.com.innovaviajes.dto;

/**
 * @author Edwin
 *
 */
public class RegistroVentaWebDto {

	private PasajeroWebDto[] datosPasajeros;
	private MetodoPagoWebDto datosMetodoPago;
	
	
	/**
	 * @return the datosPasajeros
	 */
	public PasajeroWebDto[] getDatosPasajeros() {
		return datosPasajeros;
	}
	/**
	 * @param datosPasajeros the datosPasajeros to set
	 */
	public void setDatosPasajeros(PasajeroWebDto[] datosPasajeros) {
		this.datosPasajeros = datosPasajeros;
	}
	/**
	 * @return the datosMetodoPago
	 */
	public MetodoPagoWebDto getDatosMetodoPago() {
		return datosMetodoPago;
	}
	/**
	 * @param datosMetodoPago the datosMetodoPago to set
	 */
	public void setDatosMetodoPago(MetodoPagoWebDto datosMetodoPago) {
		this.datosMetodoPago = datosMetodoPago;
	}
	
	
}
