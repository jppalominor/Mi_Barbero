package com.jpsapps.mibarberojp.data;

public class Cita {

    private String barbero;
    private String fecha;
    private String hora;
    private String cliente;

    public Cita(){}

    public Cita(String barbero, String fecha, String hora, String cliente){
        this.barbero = barbero;
        this.fecha = fecha;
        this.hora = hora;
        this.cliente = cliente;
    }

    public String getBarbero() {
        return barbero;
    }

    public void setBarbero(String barbero) {
        this.barbero = barbero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
