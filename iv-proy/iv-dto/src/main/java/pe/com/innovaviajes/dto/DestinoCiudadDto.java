/**
 * 
 */
package pe.com.innovaviajes.dto;

import java.util.List;

/**
 * @author Edwin
 *
 */
public class DestinoCiudadDto extends BaseDto {

	private static final long serialVersionUID = -199543956320228559L;
	
	private Integer id;
	
	private String descripcion;
	
	private PaisDto paisDto;
	
	private List<AeropuertoWebDto> listaAeropuertosDto;

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
	 * @return the paisDto
	 */
	public PaisDto getPaisDto() {
		if (paisDto == null) {
			paisDto = new PaisDto();
		}
		return paisDto;
	}

	/**
	 * @param paisDto the paisDto to set
	 */
	public void setPaisDto(PaisDto paisDto) {
		this.paisDto = paisDto;
	}

	/**
	 * @return the listaAeropuertosDto
	 */
	public List<AeropuertoWebDto> getListaAeropuertosDto() {
		return listaAeropuertosDto;
	}

	/**
	 * @param listaAeropuertosDto the listaAeropuertosDto to set
	 */
	public void setListaAeropuertosDto(List<AeropuertoWebDto> listaAeropuertosDto) {
		this.listaAeropuertosDto = listaAeropuertosDto;
	}
	
}
