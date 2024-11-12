/**
 * 
 */
package pe.com.innovaviajes.dto;

/**
 * @author Edwin
 *
 */
public class AvionDto extends BaseDto {

	private static final long serialVersionUID = 269431273022270199L;

	private Integer id;
	private String marcaAvion;
	private String modeloAvion;
	
	
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
	 * @return the marcaAvion
	 */
	public String getMarcaAvion() {
		return marcaAvion;
	}
	/**
	 * @param marcaAvion the marcaAvion to set
	 */
	public void setMarcaAvion(String marcaAvion) {
		this.marcaAvion = marcaAvion;
	}
	/**
	 * @return the modeloAvion
	 */
	public String getModeloAvion() {
		return modeloAvion;
	}
	/**
	 * @param modeloAvion the modeloAvion to set
	 */
	public void setModeloAvion(String modeloAvion) {
		this.modeloAvion = modeloAvion;
	}
	
	
}
