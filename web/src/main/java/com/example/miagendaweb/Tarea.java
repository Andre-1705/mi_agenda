
package com.example.miagendaweb;
import jakarta.persistence.*;
@Entity
public class Tarea {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dia;
    private String hora;
    private String texto;

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

        public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}