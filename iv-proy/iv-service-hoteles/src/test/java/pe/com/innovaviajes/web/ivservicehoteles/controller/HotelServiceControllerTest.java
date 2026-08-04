/**
 * 
 */
package pe.com.innovaviajes.web.ivservicehoteles.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import pe.com.innovaviajes.dto.HotelDto;
import pe.com.innovaviajes.web.ivservicehoteles.exception.IvGlobalExceptionHandler;
import pe.com.innovaviajes.web.ivservicehoteles.exception.IvServiceHotelesException;
import pe.com.innovaviajes.web.ivservicehoteles.service.HotelService;

/**
 * @author Edwin
 *
 */
@WebMvcTest(HotelServiceController.class)
@Import(IvGlobalExceptionHandler.class)
@TestPropertySource(properties = {"JWT_SECRET=clave-secreta-de-prueba-para-tests-unitarios-32ch"})
public class HotelServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HotelService hotelService;

	private HotelDto hotelEjemplo;

	@BeforeEach
	void setUp() {
		hotelEjemplo = new HotelDto();
		hotelEjemplo.setIdHotel(1);
		hotelEjemplo.setNombre("Hotel Lima Palace");
		hotelEjemplo.setCiudad("Lima");
		hotelEjemplo.setPais("Perú");
		hotelEjemplo.setEstrellas(5);
		hotelEjemplo.setPrecioNoche(250.0);
	}

	@Test
	@WithMockUser
	public void listarHoteles_cuandoHayHoteles_retorna200() throws Exception {
		when(hotelService.listarHoteles()).thenReturn(Arrays.asList(hotelEjemplo));

		mockMvc.perform(get("/hotelService/hoteles"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.error").value(false))
				.andExpect(jsonPath("$.dataRpta[0].nombre").value("Hotel Lima Palace"));
	}

	@Test
	@WithMockUser
	public void listarHoteles_cuandoNoHayHoteles_retorna204() throws Exception {
		when(hotelService.listarHoteles()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/hotelService/hoteles"))
				.andDo(print())
				.andExpect(status().isNoContent());
	}

	@Test
	@WithMockUser
	public void obtenerHotel_cuandoExiste_retorna200() throws Exception {
		when(hotelService.obtenerHotelPorId(1)).thenReturn(hotelEjemplo);

		mockMvc.perform(get("/hotelService/hoteles/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.dataRpta.idHotel").value(1))
				.andExpect(jsonPath("$.dataRpta.nombre").value("Hotel Lima Palace"));
	}

	@Test
	@WithMockUser
	public void obtenerHotel_cuandoNoExiste_retorna404() throws Exception {
		when(hotelService.obtenerHotelPorId(99))
				.thenThrow(new IvServiceHotelesException("Hotel no encontrado con id: 99"));

		mockMvc.perform(get("/hotelService/hoteles/99"))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.error").value(true));
	}

	@Test
	@WithMockUser
	public void crearHotel_cuandoDatosValidos_retorna201() throws Exception {
		when(hotelService.crearHotel(any(HotelDto.class))).thenReturn(hotelEjemplo);

		String jsonHotel = "{\"nombre\": \"Hotel Lima Palace\", \"ciudad\": \"Lima\", \"pais\": \"Perú\", \"estrellas\": 5, \"precioNoche\": 250.0}";

		mockMvc.perform(post("/hotelService/hoteles")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonHotel))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.error").value(false))
				.andExpect(jsonPath("$.dataRpta.idHotel").value(1));
	}

	@Test
	@WithMockUser
	public void crearHotel_cuandoNombreVacio_retorna400() throws Exception {
		when(hotelService.crearHotel(any(HotelDto.class)))
				.thenThrow(new IvServiceHotelesException("El nombre del hotel es requerido"));

		String jsonHotel = "{\"nombre\": \"\", \"ciudad\": \"Lima\"}";

		mockMvc.perform(post("/hotelService/hoteles")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonHotel))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value(true));
	}

	@Test
	@WithMockUser
	public void actualizarHotel_cuandoExiste_retorna200() throws Exception {
		when(hotelService.actualizarHotel(eq(1), any(HotelDto.class))).thenReturn(hotelEjemplo);

		String jsonHotel = "{\"nombre\": \"Hotel Lima Palace Actualizado\", \"ciudad\": \"Lima\"}";

		mockMvc.perform(put("/hotelService/hoteles/1")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonHotel))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.error").value(false));
	}

	@Test
	@WithMockUser
	public void actualizarHotel_cuandoNoExiste_retorna404() throws Exception {
		when(hotelService.actualizarHotel(eq(99), any(HotelDto.class)))
				.thenThrow(new IvServiceHotelesException("Hotel no encontrado con id: 99"));

		String jsonHotel = "{\"nombre\": \"Hotel Inexistente\"}";

		mockMvc.perform(put("/hotelService/hoteles/99")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonHotel))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser
	public void eliminarHotel_cuandoExiste_retorna204() throws Exception {
		doNothing().when(hotelService).eliminarHotel(1);

		mockMvc.perform(delete("/hotelService/hoteles/1")
				.with(csrf()))
				.andDo(print())
				.andExpect(status().isNoContent());
	}

	@Test
	@WithMockUser
	public void eliminarHotel_cuandoNoExiste_retorna404() throws Exception {
		doThrow(new IvServiceHotelesException("Hotel no encontrado con id: 99"))
				.when(hotelService).eliminarHotel(99);

		mockMvc.perform(delete("/hotelService/hoteles/99")
				.with(csrf()))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
}
