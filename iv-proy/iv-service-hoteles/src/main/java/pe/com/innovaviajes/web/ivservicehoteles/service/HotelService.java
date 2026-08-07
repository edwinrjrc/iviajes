package pe.com.innovaviajes.web.ivservicehoteles.service;

import java.util.List;

import pe.com.innovaviajes.dto.BusquedaHotelRequest;
import pe.com.innovaviajes.dto.HotelDisponibleResponse;
import pe.com.innovaviajes.dto.ReservaHotelRequest;
import pe.com.innovaviajes.dto.ReservaHotelResponse;

public interface HotelService {

    List<HotelDisponibleResponse> buscarHoteles(BusquedaHotelRequest request);

    ReservaHotelResponse reservarHotel(ReservaHotelRequest request);
}
