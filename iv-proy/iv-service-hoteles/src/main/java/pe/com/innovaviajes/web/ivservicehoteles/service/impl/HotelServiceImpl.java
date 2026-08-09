package pe.com.innovaviajes.web.ivservicehoteles.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pe.com.innovaviajes.dto.BusquedaHotelRequest;
import pe.com.innovaviajes.dto.HotelDisponibleResponse;
import pe.com.innovaviajes.dto.ReservaHotelRequest;
import pe.com.innovaviajes.dto.ReservaHotelResponse;
import pe.com.innovaviajes.web.ivservicehoteles.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    private static final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Override
    public List<HotelDisponibleResponse> buscarHoteles(BusquedaHotelRequest request) {
        validarBusqueda(request);

        final int totalPasajeros = Math.max(1, request.getAdultos())
                + Math.max(0, request.getNinos())
                + Math.max(0, request.getInfantes());

        final String destinoFiltro = normalizar(request.getDestino());
        final String categoriaFiltro = normalizar(request.getCategoria());
        final BigDecimal precioMaximo = request.getPrecioMaximo() == null
                ? new BigDecimal("999999")
                : request.getPrecioMaximo();

        List<HotelDisponibleResponse> hotelesBase =hotelesBase();

        if (hotelesBase == null || hotelesBase.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron hoteles disponibles");
        }

        log.info(" hotelesBase ::"+hotelesBase);

        return hotelesBase.stream()
        .filter(Objects::nonNull)
        //.filter(h -> h.getUbicacion() != null && normalizar(h.getUbicacion()).contains(destinoFiltro))
        .filter(h -> h.getCapacidad() >= totalPasajeros)
        //.filter(h -> categoriaFiltro.isEmpty() || (h.getCategoria() != null && normalizar(h.getCategoria()).equals(categoriaFiltro)))
        //.filter(h -> h.getPrecioPorNoche() != null && h.getPrecioPorNoche().compareTo(precioMaximo) <= 0)
        .sorted(Comparator
                // Cambiar la referencia a método por lambda elimina el aviso en rojo del analizador
                .comparing((HotelDisponibleResponse h) -> h.getPrecioPorNoche(), Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(h -> h.getRating(), Comparator.nullsLast(Comparator.reverseOrder())))
        .collect(Collectors.toList());
    }

    @Override
    public ReservaHotelResponse reservarHotel(ReservaHotelRequest request) {
        validarReserva(request);

        final Optional<HotelDisponibleResponse> hotelEncontrado = hotelesBase().stream()
                .filter(h -> Objects.equals(h.getId(), request.getHotelId()))
                .findFirst();

        if (hotelEncontrado.isEmpty()) {
            throw new IllegalArgumentException("No se encontro el hotel solicitado para reserva");
        }

        final HotelDisponibleResponse hotel = hotelEncontrado.get();
        final BigDecimal subtotal = hotel.getPrecioPorNoche().multiply(BigDecimal.valueOf(request.getNoches()));
        final BigDecimal factorTipoHabitacion = obtenerFactorTipoHabitacion(request.getTipoHabitacion());
        final BigDecimal precioTotal = subtotal.multiply(factorTipoHabitacion).setScale(2, RoundingMode.HALF_UP);

        return new ReservaHotelResponse(
                "HRES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(Locale.ROOT),
                "CONFIRMADA",
                hotel.getId(),
                hotel.getNombre(),
                precioTotal,
                "Reserva creada correctamente");
    }

    private BigDecimal obtenerFactorTipoHabitacion(String tipoHabitacion) {
        final String tipo = normalizar(tipoHabitacion);
        return switch (tipo) {
            case "simple" -> new BigDecimal("1.00");
            case "doble" -> new BigDecimal("1.20");
            case "suite" -> new BigDecimal("1.60");
            case "familiar" -> new BigDecimal("1.45");
            default -> new BigDecimal("1.00");
        };
    }

    private void validarBusqueda(BusquedaHotelRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de busqueda es obligatorio");
        }
        if (request.getDestino() == null || request.getDestino().isBlank()) {
            throw new IllegalArgumentException("El destino es obligatorio");
        }
        if (request.getFechaLlegada() == null) {
            throw new IllegalArgumentException("La fecha de llegada es obligatoria");
        }
        if (request.getNoches() <= 0) {
            throw new IllegalArgumentException("El numero de noches debe ser mayor que cero");
        }
        if (request.getAdultos() <= 0) {
            throw new IllegalArgumentException("Debe existir al menos un adulto");
        }
    }

    private void validarReserva(ReservaHotelRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request de reserva es obligatorio");
        }
        if (request.getHotelId() == null || request.getHotelId().isBlank()) {
            throw new IllegalArgumentException("El hotelId es obligatorio");
        }
        if (request.getTipoHabitacion() == null || request.getTipoHabitacion().isBlank()) {
            throw new IllegalArgumentException("El tipoHabitacion es obligatorio");
        }
        if (request.getFechaCheckIn() == null || request.getFechaCheckOut() == null) {
            throw new IllegalArgumentException("Las fechas de check-in y check-out son obligatorias");
        }
        if (request.getNoches() <= 0) {
            throw new IllegalArgumentException("El numero de noches debe ser mayor que cero");
        }
        if (request.getAdultos() <= 0) {
            throw new IllegalArgumentException("Debe existir al menos un adulto para la reserva");
        }
    }

    private List<HotelDisponibleResponse> hotelesBase() {
        final List<HotelDisponibleResponse> hoteles = new ArrayList<>();
        hoteles.add(new HotelDisponibleResponse(
                "HOT-001",
                "Hotel Costa Azul",
                "5★",
                "Miraflores, Lima",
                4,
                new BigDecimal("180.00"),
                new BigDecimal("4.9"),
                "Hotel frente al mar con desayuno incluido"));
        hoteles.add(new HotelDisponibleResponse(
                "HOT-002",
                "Hotel Sol y Mar",
                "4★",
                "San Isidro, Lima",
                3,
                new BigDecimal("140.00"),
                new BigDecimal("4.5"),
                "Ideal para viajeros de negocios y turismo"));
        hoteles.add(new HotelDisponibleResponse(
                "HOT-003",
                "Hotel Horizonte",
                "4★",
                "Cusco Centro",
                5,
                new BigDecimal("160.00"),
                new BigDecimal("4.3"),
                "Excelente conexion con zonas turisticas"));
        hoteles.add(new HotelDisponibleResponse(
                "HOT-004",
                "Hotel El Bosque",
                "3★",
                "Arequipa",
                2,
                new BigDecimal("110.00"),
                new BigDecimal("4.0"),
                "Opcion economica para estancias largas"));
        return hoteles;
    }

    private String normalizar(String texto) {
        return texto == null ? "" : texto.trim().toLowerCase(Locale.ROOT);
    }
}
