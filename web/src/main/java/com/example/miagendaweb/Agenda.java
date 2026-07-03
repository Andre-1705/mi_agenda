package com.example.miagendaweb;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Agenda {
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};        
        private final TareaRepository tareaRepository;

    public Agenda(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }
    
    public ArrayList<ArrayList<Tarea>> obtenerSemana() {
        // 1. Pedimos TODAS las tareas a la base de datos 
        List<Tarea> todasLasTareas = tareaRepository.findAll();

        // 2. Recreamos la estructura de 7 días 
        ArrayList<ArrayList<Tarea>> semana = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            semana.add(new ArrayList<>());
        }

            // 3. Clasificamos cada tarea en su día correspondiente
            for (Tarea tarea : todasLasTareas) {
                String diaTarea = tarea.getDia();
                for (int i = 0; i < 7; i++) {
                    if (dias[i].equalsIgnoreCase(diaTarea)) {
                        semana.get(i).add(tarea);
                        break;
                    }
                }
            }
        return semana;
    }

    @Transactional
    public void agregarTarea(String dia, String hora, String texto) {
        Tarea nuevaTarea = new Tarea(dia, hora, texto);
        tareaRepository.save(nuevaTarea);
    }

    @Transactional
    public void eliminarTarea(int dia, int numTarea) {
        // 1. Pedimos la semana armada para encontrar la tarea por su posición
        ArrayList<ArrayList<Tarea>> semana = obtenerSemana();
        
        if (dia >= 0 && dia < 7) {
            ArrayList<Tarea> tareasDelDia = semana.get(dia);
            if (numTarea >= 0 && numTarea < tareasDelDia.size()) {
                // 2. Encontramos la tarea y sacamos su ID real de base de datos
                Tarea tareaAEliminar = tareasDelDia.get(numTarea);
                
                // 3. Le decimos a JPA que borre usando el ID
                tareaRepository.deleteById(tareaAEliminar.getId());
            }
        }
    }

    @Transactional
    public void modificarTarea(int dia, int numTarea, String nuevaHora, String nuevoTexto) {
        ArrayList<ArrayList<Tarea>> semana = obtenerSemana();
        
        if (dia >= 0 && dia < 7) {
            ArrayList<Tarea> tareasDelDia = semana.get(dia);
            if (numTarea >= 0 && numTarea < tareasDelDia.size()) {
                // 1. Encontramos la tarea existente
                Tarea tareaAModificar = tareasDelDia.get(numTarea);
                
                // 2. Le cambiamos los datos usando los setters
                tareaAModificar.setHora(nuevaHora);
                tareaAModificar.setTexto(nuevoTexto);
                
                // 3. Como el objeto ya tiene un ID (no es null)
                // JPA no hace un INSERT, hace un UPDATE automaticamente.
                tareaRepository.save(tareaAModificar);
            }
        }
    }
}

