/**
 * 
 */
package pe.com.innovaviajes.web.ivserviceviajes.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import pe.com.innovaviajes.web.ivserviceviajes.util.UtilSecurity;
import pe.com.innovaviajes.web.ivserviceviajes.service.DestinoCiudadCatalogoService;

/**
 * @author Edwin
 *
 */
@WebMvcTest(ViajeServiceController.class)
public class ViajeServiceControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private DestinoCiudadCatalogoService destinoCiudadService;
    
 // AGREGA ESTA LÍNEA AQUÍ
    @MockBean
    private UtilSecurity utilSecurity;

    @BeforeEach
    void setUp() {
        // Configuramos para que cualquier cadena que se intente desencriptar
        // devuelva algo válido, evitando NullPointerExceptions inesperados.
        when(utilSecurity.descencripta(anyString())).thenAnswer(invocation -> {
            String arg = invocation.getArgument(0);
            return arg != null ? arg : "0"; 
        });
    }
    
    @Test
    @WithMockUser
    public void cuandoFechaInvalida_entoncesRetorna400() throws Exception {
        // Agregamos campos mínimos para evitar fallos en la desencriptación
        String jsonPeticion = "{\"fechaIdaStr\": \"2026/13/40\", \"adultos\": \"1\", \"ninos\": \"0\", \"infantes\": \"0\"}";

        mockMvc.perform(post("/viajeService/vuelosCotizacion")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPeticion))
                .andDo(print()) 
                .andExpect(status().isBadRequest()) 
                .andExpect(jsonPath("$.mensaje").value("El formato de las fechas o datos de viaje es incorrecto."));
    }

}
