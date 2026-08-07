package pe.com.innovaviajes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BusquedaHotelRequest {

    private String destino;
    private LocalDate fechaLlegada;
    private int noches;
    private int adultos;
    private int ninos;
    private int infantes;
    private String categoria;
    private BigDecimal precioMaximo;

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public int getNoches() {
        return noches;
    }

    public void setNoches(int noches) {
        this.noches = noches;
    }

    public int getAdultos() {
        return adultos;
    }

    public void setAdultos(int adultos) {
        this.adultos = adultos;
    }

    public int getNinos() {
        return ninos;
    }

    public void setNinos(int ninos) {
        this.ninos = ninos;
    }

    public int getInfantes() {
        return infantes;
    }

    public void setInfantes(int infantes) {
        this.infantes = infantes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecioMaximo() {
        return precioMaximo;
    }

    public void setPrecioMaximo(BigDecimal precioMaximo) {
        this.precioMaximo = precioMaximo;
    }
}
