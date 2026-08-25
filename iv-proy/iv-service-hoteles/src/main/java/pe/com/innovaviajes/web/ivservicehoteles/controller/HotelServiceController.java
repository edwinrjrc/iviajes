package pe.com.innovaviajes.web.ivservicehoteles.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.innovaviajes.web.ivservicehoteles.dto.BusquedaHotelRequest;
import pe.com.innovaviajes.web.ivservicehoteles.dto.HotelDisponibleResponse;
import pe.com.innovaviajes.web.ivservicehoteles.dto.ReservaHotelRequest;
import pe.com.innovaviajes.web.ivservicehoteles.dto.ReservaHotelResponse;
import pe.com.innovaviajes.web.ivservicehoteles.service.HotelService;

@RestController
@RequestMapping(value = "/hotelservice")
public class HotelServiceController {

    private static final Logger log = LoggerFactory.getLogger(HotelServiceController.class);

    private final HotelService hotelService;

    public HotelServiceController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(value = "/busqueda")
    public ResponseEntity<Map<String, Object>> buscarHoteles(@RequestBody BusquedaHotelRequest request) {
        try {
            final List<HotelDisponibleResponse> hoteles = hotelService.buscarHoteles(request);

            log.info("Hoteles 2 ::"+hoteles);

            final HttpStatus status = hoteles.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
            return new ResponseEntity<>(
                    mapearRespuesta(false, "Consulta de hoteles realizada", hoteles),
                    status);
        } catch (IllegalArgumentException ex) {
            log.warn("Validacion de busqueda de hoteles: {}", ex.getMessage());
            return new ResponseEntity<>(
                    mapearRespuesta(true, ex.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("Error en busqueda de hoteles", ex);
            return new ResponseEntity<>(
                    mapearRespuesta(true, "No se pudo completar la busqueda de hoteles", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/reserva")
    public ResponseEntity<Map<String, Object>> reservarHotel(@RequestBody ReservaHotelRequest request) {
        try {
            final ReservaHotelResponse reserva = hotelService.reservarHotel(request);
            return new ResponseEntity<>(
                    mapearRespuesta(false, "Reserva registrada correctamente", reserva),
                    HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            log.warn("Validacion de reserva de hotel: {}", ex.getMessage());
            return new ResponseEntity<>(
                    mapearRespuesta(true, ex.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("Error al registrar reserva de hotel", ex);
            return new ResponseEntity<>(
                    mapearRespuesta(true, "No se pudo completar la reserva de hotel", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> mapearRespuesta(boolean error, String mensaje, Object data) {
        final Map<String, Object> salida = new HashMap<>();
        salida.put("error", error);
        salida.put("mensaje", mensaje);
        salida.put("data", data);
        return salida;
    }
}
