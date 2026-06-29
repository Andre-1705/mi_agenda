import java.util.ArrayList;

public class Agenda {
    ArrayList<ArrayList<Tarea>> semana;
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        

    public Agenda() {
        semana = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            semana.add(new ArrayList<>());
        }
    }

    public void mostrar() {
        for (int i = 0; i < 7; i++) {
            System.out.println(dias[i] + ":");
            for (Tarea tarea : semana.get(i)) {
                System.out.println("  " + tarea);
            }
        }
    }

    public void agregarTarea(String dia, String hora, String texto){
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equalsIgnoreCase(dia)){
                semana.get(i).add(new Tarea(dia, hora, texto));
            } 
        }
    }

    public void modificarTarea(int dia, int numTarea, String nuevaHora, String nuevoTexto) {
        semana.get(dia).set(numTarea, new Tarea(dias[dia], nuevaHora, nuevoTexto)); 
    }

    public void eliminarTarea(int dia, int numTarea) {
        semana.get(dia).remove(numTarea);
    }
}

