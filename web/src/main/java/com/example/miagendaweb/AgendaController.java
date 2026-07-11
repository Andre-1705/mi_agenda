package com.example.miagendaweb;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
public class AgendaController {
    private final Agenda agenda;

    public AgendaController(Agenda agenda) {
        this.agenda = agenda;
    }

    @GetMapping("/tareas")
    public ArrayList<ArrayList<Tarea>> verTareas() {
        return agenda.obtenerSemana();
    }

    @PostMapping("/tareas")
    public void agregarTarea(@RequestBody Tarea tarea) {
        agenda.agregarTarea(tarea.getDia(), tarea.getHora(), tarea.getTexto());
    }

    @PutMapping("/tareas/{dia}/{numTarea}")
    public void modificarTarea(@PathVariable("dia") int dia, @PathVariable("numTarea") int numTarea, @RequestBody Tarea tarea) {
        agenda.modificarTarea(dia, numTarea, tarea.getHora(), tarea.getTexto());
    }

    @DeleteMapping("/tareas/{dia}/{numTarea}")
    public void eliminarTarea(@PathVariable("dia") int dia, @PathVariable("numTarea") int numTarea) {
        agenda.eliminarTarea(dia, numTarea);
    }
}
