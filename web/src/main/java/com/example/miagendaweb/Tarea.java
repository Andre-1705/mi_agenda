package com.example.miagendaweb;

public class Tarea {
    String dia;
    String hora;
    String texto;

        public Tarea(String dia, String hora, String texto) {
            this.dia = dia;
            this.hora = hora;
            this.texto = texto;
        }

        public String toString() {
        return hora + " - " + texto;
    }
}