import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner teclado = new Scanner(System.in);

        while (true) {
           System.out.println("Seleccione una opción: ");
           System.out.println("1) Mostrar agenda");
           System.out.println("2) Agregar tarea");
           System.out.println("3) Modificar tarea");
           System.out.println("4) Eliminar tarea");
           System.out.println("5) Salir");

           int opcion = teclado.nextInt();


            if (opcion == 1) {
                agenda.mostrar();
            }
            if (opcion == 2) {
                System.out.println("Ingrese el día de la tarea (Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo): ");
                String dia = teclado.next();
                System.out.println("Ingrese la hora de la tarea (Ej: 10:00): ");
                String hora = teclado.next();
                System.out.println("Ingrese el texto de la tarea: ");
                teclado.nextLine(); // limpia el buffer del teclado
                String texto = teclado.nextLine(); // ahora sí lee bien
                agenda.agregarTarea(dia, hora, texto);
            }
            if (opcion == 3) {
                System.out.println("Ingrese el número del día a modificar (0=Lunes, 1=Martes, 2=Miércoles, 3=Jueves, 4=Viernes, 5=Sábado, 6=Domingo): ");
                int dia = teclado.nextInt();
                System.out.println("Ingrese el número de la tarea a modificar (0=Primera, 1=Segunda, 2=Tercera, 3=Cuarta, 4=Quinta, etc. ): ");
                int numTarea = teclado.nextInt();
                System.out.println("Ingrese la nueva hora de la tarea a modificar (Ej: 10:00): ");
                String nuevaHora = teclado.next();
                System.out.println("Ingrese el nuevo texto de la tarea a modificar: ");
                teclado.nextLine(); // limpia el buffer del teclado
                String nuevoTexto = teclado.nextLine(); //ahora sí lee bien
                agenda.modificarTarea(dia, numTarea, nuevaHora, nuevoTexto);
            }
            if (opcion == 4) {
                System.out.println("Ingrese el número del día a eliminar (0=Lunes, 1=Martes, 2=Miércoles, 3=Jueves, 4=Viernes, 5=Sábado, 6=Domingo): ");
                int dia = teclado.nextInt();
                System.out.println("Ingrese el número de la tarea a eliminar (0=Primera, 1=Segunda, 2=Tercera, 3=Cuarta, 4=Quinta, etc. ): ");
                int numTarea  = teclado.nextInt();
                agenda.eliminarTarea(dia, numTarea);
            }
            if (opcion == 5) {
            break;  
            }
        }    
    }
}