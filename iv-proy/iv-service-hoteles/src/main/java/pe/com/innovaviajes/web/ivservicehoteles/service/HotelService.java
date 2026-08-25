package pe.com.innovaviajes.web.ivservicehoteles.service;

import java.util.List;

import pe.com.innovaviajes.web.ivservicehoteles.dto.BusquedaHotelRequest;
import pe.com.innovaviajes.web.ivservicehoteles.dto.HotelDisponibleResponse;
import pe.com.innovaviajes.web.ivservicehoteles.dto.ReservaHotelRequest;
import pe.com.innovaviajes.web.ivservicehoteles.dto.ReservaHotelResponse;

public interface HotelService {

    List<HotelDisponibleResponse> buscarHoteles(BusquedaHotelRequest request);

    ReservaHotelResponse reservarHotel(ReservaHotelRequest request);
}
