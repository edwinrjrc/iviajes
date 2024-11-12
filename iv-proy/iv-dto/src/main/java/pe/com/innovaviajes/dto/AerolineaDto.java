/**
 * 
 */
package pe.com.innovaviajes.dto;

/**
 * @author Edwin
 *
 */
public class AerolineaDto extends BaseDto {

	private static final long serialVersionUID = -5191447534524187542L;
	
	private Integer id;
	
	private String codigoIata;
	
	private String nombre;

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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
