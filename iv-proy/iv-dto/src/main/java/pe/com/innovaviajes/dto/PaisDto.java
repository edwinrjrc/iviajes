/**
 * 
 */
package pe.com.innovaviajes.dto;

/**
 * @author Edwin
 *
 */
public class PaisDto extends BaseDto {

	private static final long serialVersionUID = 8750786268836225395L;
	
	
	private Integer id;
	
	private Integer idContinente;
	
	private String nombre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdContinente() {
		return idContinente;
	}

	public void setIdContinente(Integer idContinente) {
		this.idContinente = idContinente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
