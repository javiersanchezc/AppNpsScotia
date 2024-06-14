package com.nps.AppNps.loadProces;



import java.time.LocalDateTime;

public class ConsultaResultado {
    private LocalDateTime fechaConsulta;
    private String nombreTabla;
    private long cantidadDatos;

    public ConsultaResultado(LocalDateTime fechaConsulta, String nombreTabla, long cantidadDatos) {
        this.fechaConsulta = fechaConsulta;
        this.nombreTabla = nombreTabla;
        this.cantidadDatos = cantidadDatos;
    }

    // Getters y Setters
    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public long getCantidadDatos() {
        return cantidadDatos;
    }

    public void setCantidadDatos(long cantidadDatos) {
        this.cantidadDatos = cantidadDatos;
    }
}
