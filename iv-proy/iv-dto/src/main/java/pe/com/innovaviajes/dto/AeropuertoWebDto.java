/**
 * 
 */
package pe.com.innovaviajes.dto;

/**
 * @author Edwin
 *
 */
public class AeropuertoWebDto extends BaseDto {

	private static final long serialVersionUID = 6971092461533610087L;
	
	private Integer id;
	
	private String descripcion;
	
	private String codigoIata;

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
	
	

}
