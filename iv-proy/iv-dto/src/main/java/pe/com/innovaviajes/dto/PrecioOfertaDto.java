/**
 * 
 */
package pe.com.innovaviajes.dto;

import java.math.BigDecimal;

/**
 * @author Edwin
 *
 */
public class PrecioOfertaDto extends BaseDto {

	private static final long serialVersionUID = 5339547090035108662L;
	
	private BigDecimal precioUnitarioClase;
	
	private Short cantidadAdultos;
	
	private BigDecimal totalPrecioAdultos;
	
	private Short cantidadNinos;
	
	private BigDecimal porcentajePrecioNinos;
	
	private BigDecimal totalPrecioNinos;
	
	private Short cantidadInfantes;
	
	private BigDecimal porcentajePrecioInfantes;
	
	private BigDecimal totalPrecioInfantes;
	
	private BigDecimal totalImpuestos;
	
	private BigDecimal totalFee;
	
	private BigDecimal totalRuta;

	/**
	 * @return the precioUnitarioClase
	 */
	public BigDecimal getPrecioUnitarioClase() {
		return precioUnitarioClase;
	}

	/**
	 * @param precioUnitarioClase the precioUnitarioClase to set
	 */
	public void setPrecioUnitarioClase(BigDecimal precioUnitarioClase) {
		this.precioUnitarioClase = precioUnitarioClase;
	}

	/**
	 * @return the cantidadAdultos
	 */
	public Short getCantidadAdultos() {
		return cantidadAdultos;
	}

	/**
	 * @param cantidadAdultos the cantidadAdultos to set
	 */
	public void setCantidadAdultos(Short cantidadAdultos) {
		this.cantidadAdultos = cantidadAdultos;
	}

	/**
	 * @return the totalPrecioAdultos
	 */
	public BigDecimal getTotalPrecioAdultos() {
		return totalPrecioAdultos;
	}

	/**
	 * @param totalPrecioAdultos the totalPrecioAdultos to set
	 */
	public void setTotalPrecioAdultos(BigDecimal totalPrecioAdultos) {
		this.totalPrecioAdultos = totalPrecioAdultos;
	}

	/**
	 * @return the cantidadNinos
	 */
	public Short getCantidadNinos() {
		return cantidadNinos;
	}

	/**
	 * @param cantidadNinos the cantidadNinos to set
	 */
	public void setCantidadNinos(Short cantidadNinos) {
		this.cantidadNinos = cantidadNinos;
	}

	/**
	 * @return the porcentajePrecioNinos
	 */
	public BigDecimal getPorcentajePrecioNinos() {
		return porcentajePrecioNinos;
	}

	/**
	 * @param porcentajePrecioNinos the porcentajePrecioNinos to set
	 */
	public void setPorcentajePrecioNinos(BigDecimal porcentajePrecioNinos) {
		this.porcentajePrecioNinos = porcentajePrecioNinos;
	}

	/**
	 * @return the totalPrecioNinos
	 */
	public BigDecimal getTotalPrecioNinos() {
		return totalPrecioNinos;
	}

	/**
	 * @param totalPrecioNinos the totalPrecioNinos to set
	 */
	public void setTotalPrecioNinos(BigDecimal totalPrecioNinos) {
		this.totalPrecioNinos = totalPrecioNinos;
	}

	/**
	 * @return the cantidadInfantes
	 */
	public Short getCantidadInfantes() {
		return cantidadInfantes;
	}

	/**
	 * @param cantidadInfantes the cantidadInfantes to set
	 */
	public void setCantidadInfantes(Short cantidadInfantes) {
		this.cantidadInfantes = cantidadInfantes;
	}

	/**
	 * @return the porcentajePrecioInfantes
	 */
	public BigDecimal getPorcentajePrecioInfantes() {
		return porcentajePrecioInfantes;
	}

	/**
	 * @param porcentajePrecioInfantes the porcentajePrecioInfantes to set
	 */
	public void setPorcentajePrecioInfantes(BigDecimal porcentajePrecioInfantes) {
		this.porcentajePrecioInfantes = porcentajePrecioInfantes;
	}

	/**
	 * @return the totalPrecioInfantes
	 */
	public BigDecimal getTotalPrecioInfantes() {
		return totalPrecioInfantes;
	}

	/**
	 * @param totalPrecioInfantes the totalPrecioInfantes to set
	 */
	public void setTotalPrecioInfantes(BigDecimal totalPrecioInfantes) {
		this.totalPrecioInfantes = totalPrecioInfantes;
	}

	/**
	 * @return the totalImpuestos
	 */
	public BigDecimal getTotalImpuestos() {
		return totalImpuestos;
	}

	/**
	 * @param totalImpuestos the totalImpuestos to set
	 */
	public void setTotalImpuestos(BigDecimal totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	/**
	 * @return the totalFee
	 */
	public BigDecimal getTotalFee() {
		return totalFee;
	}

	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * @return the totalRuta
	 */
	public BigDecimal getTotalRuta() {
		return totalRuta;
	}

	/**
	 * @param totalRuta the totalRuta to set
	 */
	public void setTotalRuta(BigDecimal totalRuta) {
		this.totalRuta = totalRuta;
	}
	
	

}
