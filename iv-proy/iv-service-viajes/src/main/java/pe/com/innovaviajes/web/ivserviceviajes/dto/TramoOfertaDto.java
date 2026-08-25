/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.dto;

import java.util.ArrayList;
import java.util.List;

import pe.com.innovaviajes.web.ivserviceviajes.util.UtilIvDto;


/**
 * @author Edwin
 *
 */
public class TramoOfertaDto extends TramoBaseDto {

	private static final long serialVersionUID = -3543134250850248952L;
	
	private List<TramoBaseDto> listaEscalas;

	/**
	 * @return the listaEscalas
	 */
	public List<TramoBaseDto> getListaEscalas() {
		if (!UtilIvDto.listaNoVacia(listaEscalas)) {
			listaEscalas = new ArrayList<TramoBaseDto>();
		}
		return listaEscalas;
	}

	/**
	 * @param listaEscalas the listaEscalas to set
	 */
	public void setListaEscalas(List<TramoBaseDto> listaEscalas) {
		this.listaEscalas = listaEscalas;
	}
	
}
