package com.example.miagendaweb

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Agenda(private val tareaRepository: TareaRepository) {

    private val dias = listOf(
        "Lunes", "Martes", "Miercoles", "Jueves",
        "Viernes", "Sabado", "Domingo"
    )

    fun obtenerSemana(): ArrayList<ArrayList<Tarea>> {
        val todasLasTareas = tareaRepository.findAll()
        val semana = ArrayList<ArrayList<Tarea>>()
        for (i in 0..6) semana.add(ArrayList())
        for (tarea in todasLasTareas) {
            val indice = dias.indexOfFirst { it.equals(tarea.dia, ignoreCase = true) }
            if (indice != -1) {
                semana[indice].add(tarea)
            }
        }
        return semana
    }

    @Transactional
    fun agregarTarea(dia: String, hora: String, texto: String) {
        tareaRepository.save(Tarea(dia, hora, texto))
    }

    @Transactional
    fun eliminarTarea(dia: Int, numTarea: Int) {
        val semana = obtenerSemana()
        if (dia in semana.indices) {
            val tareasDelDia = semana[dia]
            if (numTarea in tareasDelDia.indices) {
                tareaRepository.deleteById(tareasDelDia[numTarea].id!!)
            }
        }
    }

    @Transactional
    fun modificarTarea(dia: Int, numTarea: Int, nuevaHora: String, nuevoTexto: String) {
        val semana = obtenerSemana()
        if (dia in semana.indices) {
            val tareasDelDia = semana[dia]
            if (numTarea in tareasDelDia.indices) {
                val tarea = tareasDelDia[numTarea]
                tarea.hora = nuevaHora
                tarea.texto = nuevoTexto
                tareaRepository.save(tarea)
            }
        }
    }
}
