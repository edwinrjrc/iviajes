/**
 * 
 */
package pe.com.innovaviajes.dto;

import java.util.List;

/**
 * @author Edwin
 *
 */
public class OfertaEncontrada extends BaseDto {

	private static final long serialVersionUID = 3036440112108850020L;
	
	private List<RutaTramoDto> listaRutaTramos;
	
	private PrecioOfertaDto precioOfertaDto;


	/**
	 * @return the precioOfertaDto
	 */
	public PrecioOfertaDto getPrecioOfertaDto() {
		return precioOfertaDto;
	}

	/**
	 * @param precioOfertaDto the precioOfertaDto to set
	 */
	public void setPrecioOfertaDto(PrecioOfertaDto precioOfertaDto) {
		this.precioOfertaDto = precioOfertaDto;
	}

	/**
	 * @return the listaRutaTramos
	 */
	public List<RutaTramoDto> getListaRutaTramos() {
		return listaRutaTramos;
	}

	/**
	 * @param listaRutaTramos the listaRutaTramos to set
	 */
	public void setListaRutaTramos(List<RutaTramoDto> listaRutaTramos) {
		this.listaRutaTramos = listaRutaTramos;
	}
	
}
