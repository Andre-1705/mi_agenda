# Mi Agenda Web

Aplicación para gestionar tareas semanales. Este proyecto nació como una agenda de consola en Java puro y evolucionó hacia una arquitectura web Full Stack utilizando Spring Boot y JavaScript.

## Funcionalidades actuales

- Visualización de la semana completa en el navegador (Lunes a Domingo).
- Carga de tareas en tiempo real desde el backend usando Fetch API y JSON.
- Formulario web para agregar nuevas tareas sin recargar la página (POST).
- Backend preparado con API REST completa (GET, POST, PUT, DELETE).

## Tecnologías utilizadas

**Backend:**

- Java 21
- - Spring Boot (Web, REST)
- Gradle

**Frontend:**

- HTML5
- CSS (Estilos inline básicos)
- JavaScript (Fetch API)

**Herramientas:**

- Visual Studio Code
- Git y GitHub (Trabajo con ramas: `main` y `spring-boot`)

## Cómo ejecutarlo

1. Usá Java 21 instalado en tu PC.
2. Cloná este repositorio.
3. Abrí una terminal en la carpeta raíz del proyecto.
4. Entrá a la carpeta del proyecto web:

   ```bash
   cd web
   ```

5. VS Code y apretá el botón de "Play" en MiAgendaWebApplication.java
6. Abrí tu navegador y entrá a: [http://localhost:8081]

### Estructura del proyecto

mi_agenda
  ├── web/                                 # Proyecto Spring Boot
  │   ├── src/main/java/.../miagendaweb/
  │   │   ├── Tarea.java                   # Modelo (con getters/setters para JSON)
  │   │   ├── Agenda.java                  # Lógica de negocio (@Service)
  │   │   ├── AgendaController.java        # Endpoints REST (@RestController)
  │   │   └── MiAgendaWebApplication.java  # Punto de entrada de Spring
  │   └── src/main/resources/
  │       ├application.properties          # Configuración (puerto 8081)
  │       └── static/
  │           └ index.html                 # Frontend (HTML + JS)
  ├── Tarea.java                           # Versión de consola
  ├── Agenda.java                          # Versión de consola
  ├── Main.java                            # Versión de consola
  └── README.md

#### Próximos pasos

- Agregar botones de "Modificar" y "Eliminar" en el index.html (conectar los métodos PUT y DELETE del Controller).
- Reemplazar el ArrayList de la clase Agenda por una base de datos (H2 o MySQL) usando Spring Data JPA para que las tareas no se pierdan al apagar el servidor.
- Fusionar la rama spring-boot con main cuando la versión web esté estable.
  