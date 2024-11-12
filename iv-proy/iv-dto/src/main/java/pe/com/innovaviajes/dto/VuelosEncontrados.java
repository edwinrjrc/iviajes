/**
 * 
 */
package pe.com.innovaviajes.dto;

import java.util.List;

/**
 * @author Edwin
 *
 */
public class VuelosEncontrados extends BaseDto {

	private static final long serialVersionUID = 947524806383185L;
	
	private List<OfertaEncontrada> ofertasEncontradas;

	/**
	 * @return the ofertasEncontradas
	 */
	public List<OfertaEncontrada> getOfertasEncontradas() {
		return ofertasEncontradas;
	}

	/**
	 * @param ofertasEncontradas the ofertasEncontradas to set
	 */
	public void setOfertasEncontradas(List<OfertaEncontrada> ofertasEncontradas) {
		this.ofertasEncontradas = ofertasEncontradas;
	}

	
}
