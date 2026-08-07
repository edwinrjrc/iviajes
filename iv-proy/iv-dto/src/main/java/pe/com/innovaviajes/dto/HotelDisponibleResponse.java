package pe.com.innovaviajes.dto;

import java.math.BigDecimal;

public class HotelDisponibleResponse {

    private String id;
    private String nombre;
    private String categoria;
    private String ubicacion;
    private int capacidad;
    private BigDecimal precioPorNoche;
    private BigDecimal rating;
    private String descripcion;

    public HotelDisponibleResponse() {
    }

    public HotelDisponibleResponse(
            String id,
            String nombre,
            String categoria,
            String ubicacion,
            int capacidad,
            BigDecimal precioPorNoche,
            BigDecimal rating,
            String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.precioPorNoche = precioPorNoche;
        this.rating = rating;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
