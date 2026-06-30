package com.example.miagendaweb;

public class Tarea {
    String dia;
    String hora;
    String texto;

    public Tarea() {
    }

    public Tarea(String dia, String hora, String texto) {
        this.dia = dia;
        this.hora = hora;
        this.texto = texto;
    }

    public void setDia(String dia) {
    this.dia = dia;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public String getTexto() {
        return texto;
    }

    public String toString() {
        return hora + " - " + texto;
    }
}