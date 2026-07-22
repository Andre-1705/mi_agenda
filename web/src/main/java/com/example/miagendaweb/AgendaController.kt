package com.example.miagendaweb

import org.springframework.web.bind.annotation.*
import java.text.Normalizer

fun String.sinAcentos(): String {
    val normalizado = Normalizer.normalize(this, Normalizer.Form.NFD)
    return normalizado.replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
}

@CrossOrigin(origins = ["*"], methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
@RestController
class AgendaController(private val agenda: Agenda) {

    @GetMapping("/tareas")
    fun verTareas(): ArrayList<ArrayList<Tarea>> = agenda.obtenerSemana()

    @PostMapping("/tareas")
    fun agregarTarea(@RequestBody tarea: Tarea) {
        agenda.agregarTarea(tarea.dia.sinAcentos(), tarea.hora, tarea.texto)
    }

    @PutMapping("/tareas/{dia}/{numTarea}")
    fun modificarTarea(
        @PathVariable("dia") dia: Int,
        @PathVariable("numTarea") numTarea: Int,
        @RequestBody tarea: Tarea
    ) {
        agenda.modificarTarea(dia, numTarea, tarea.hora, tarea.texto)
    }

    @DeleteMapping("/tareas/{dia}/{numTarea}")
    fun eliminarTarea(
        @PathVariable("dia") dia: Int,
        @PathVariable("numTarea") numTarea: Int
    ) {
        agenda.eliminarTarea(dia, numTarea)
    }
}