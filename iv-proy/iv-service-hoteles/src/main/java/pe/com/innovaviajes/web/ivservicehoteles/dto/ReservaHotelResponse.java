package pe.com.innovaviajes.web.ivservicehoteles.dto;

import java.math.BigDecimal;

public class ReservaHotelResponse {

    private String codigoReserva;
    private String estado;
    private String hotelId;
    private String hotelNombre;
    private BigDecimal precioTotal;
    private String mensaje;

    public ReservaHotelResponse() {
    }

    public ReservaHotelResponse(
            String codigoReserva,
            String estado,
            String hotelId,
            String hotelNombre,
            BigDecimal precioTotal,
            String mensaje) {
        this.codigoReserva = codigoReserva;
        this.estado = estado;
        this.hotelId = hotelId;
        this.hotelNombre = hotelNombre;
        this.precioTotal = precioTotal;
        this.mensaje = mensaje;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelNombre() {
        return hotelNombre;
    }

    public void setHotelNombre(String hotelNombre) {
        this.hotelNombre = hotelNombre;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
