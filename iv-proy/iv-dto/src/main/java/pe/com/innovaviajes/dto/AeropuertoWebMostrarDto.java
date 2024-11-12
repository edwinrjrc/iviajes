/**
 * 
 */
package pe.com.innovaviajes.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Edwin
 *
 */
public class AeropuertoWebMostrarDto extends BaseDto {

	private static final long serialVersionUID = 3678553750786160411L;

	/**
	 * Id de Aeropuerto Web
	 */
	private Integer id;
	
	/**
	 * Nombre del aeropuerto internacional que se muestra en el combo
	 */
	private String descripcion;
	
	/**
	 * Nombre de la ciudad a la cual sirve el aeropuerto
	 */
	private String nombreCiudad;
	
	/**
	 * Nombre del Pais donde se encuentra la ciudad y el aeropuerto
	 */
	private String nombrePais;
	
	/**
	 * Codigo IATA del aeropuerto
	 */
	private String codigoIata;
	
	/**
	 * Concatenacion del nombre del aeropuerto que va mostrar
	 */
	private String nombreAeropuertoMostrar;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the nombreCiudad
	 */
	public String getNombreCiudad() {
		return nombreCiudad;
	}

	/**
	 * @param nombreCiudad the nombreCiudad to set
	 */
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	/**
	 * @return the nombrePais
	 */
	public String getNombrePais() {
		return nombrePais;
	}

	/**
	 * @param nombrePais the nombrePais to set
	 */
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	/**
	 * @return the codigoIata
	 */
	public String getCodigoIata() {
		return codigoIata;
	}

	/**
	 * @param codigoIata the codigoIata to set
	 */
	public void setCodigoIata(String codigoIata) {
		this.codigoIata = codigoIata;
	}

	/**
	 * @return the nombreAeropuertoMostrar
	 */
	public String getNombreAeropuertoMostrar() {
		boolean completo = false;
		if (StringUtils.isBlank(nombreAeropuertoMostrar)) {
			this.nombreAeropuertoMostrar = "";
			
			if (StringUtils.isNotBlank(this.descripcion) && StringUtils.isNotBlank(this.codigoIata)) {
				this.nombreAeropuertoMostrar += StringUtils.replaceIgnoreCase(this.descripcion, "Aeropuerto", "Arpto.");
				this.nombreAeropuertoMostrar = StringUtils.replaceIgnoreCase(this.nombreAeropuertoMostrar, "Internacional", "Int.");
				
				this.nombreAeropuertoMostrar += " ("+this.codigoIata+")";
				this.nombreAeropuertoMostrar += ", ";
				
				if (StringUtils.isNotBlank(this.nombreCiudad)) {
					this.nombreAeropuertoMostrar += this.nombreCiudad;
					this.nombreAeropuertoMostrar += ", ";
					
					if (StringUtils.isNotBlank(this.nombrePais)) {
						this.nombreAeropuertoMostrar += this.nombrePais;
						completo = true;
					}
				}
			}
		}
		
		if (!completo) {
			this.nombreAeropuertoMostrar = "";
		}
		
		return nombreAeropuertoMostrar;
	}

	/**
	 * @param nombreAeropuertoMostrar the nombreAeropuertoMostrar to set
	 */
	public void setNombreAeropuertoMostrar(String nombreAeropuertoMostrar) {
		this.nombreAeropuertoMostrar = nombreAeropuertoMostrar;
	}
	

}
