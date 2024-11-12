/**
 * 
 */
package pe.com.innovaviajes.dto;

/**
 * @author Edwin
 *
 */
public class DestinoDto extends BaseDto{

	private static final long serialVersionUID = 5853517475885513239L;
	
	private Integer id;
	
	private Integer idContinente;
	
	private PaisDto pais;
	
	private String codigoIata;
	
	private Integer idTipoDestino;
	
	private String descripcion;

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
	 * @return the idContinente
	 */
	public Integer getIdContinente() {
		return idContinente;
	}

	/**
	 * @param idContinente the idContinente to set
	 */
	public void setIdContinente(Integer idContinente) {
		this.idContinente = idContinente;
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
	 * @return the idTipoDestino
	 */
	public Integer getIdTipoDestino() {
		return idTipoDestino;
	}

	/**
	 * @param idTipoDestino the idTipoDestino to set
	 */
	public void setIdTipoDestino(Integer idTipoDestino) {
		this.idTipoDestino = idTipoDestino;
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
	 * @return the pais
	 */
	public PaisDto getPais() {
		if (pais == null) {
			pais = new PaisDto();
		}
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(PaisDto pais) {
		this.pais = pais;
	}

}
