# Mi Agenda Web

Aplicación para gestionar tareas semanales. Este proyecto nació como una agenda de consola en Java puro y evolucionó hacia una arquitectura web Full Stack utilizando Spring Boot y JavaScript.

## Funcionalidades actuales

- Visualización de la semana completa en el navegador (Lunes a Domingo).
- Carga de tareas en tiempo real desde el backend usando Fetch API y JSON.
- Formulario web para agregar nuevas tareas sin recargar la página (POST).
- Botón para eliminar tareas de forma dinámica desde la interfaz (DELETE).
- Backend preparado con API REST completa (GET, POST, PUT, DELETE).

## Tecnologías utilizadas

**Backend:**

- Java 21
- Spring Boot (Web, REST)
- Gradle

**Frontend:**

- HTML5
- CSS (Estilos inline básicos)
- JavaScript (Fetch API, manejo del DOM)

**Herramientas:**

- Visual Studio Code
- Git y GitHub (Trabajo con ramas: `main` y `spring-boot`)

## Cómo ejecutarlo

1. Asegurate de tener Java 21 instalado en tu PC.
2. Cloná este repositorio.
3. Abrí una terminal en la carpeta raíz del proyecto.
4. Entrá a la carpeta del proyecto web:

   ```bash
   cd web
   ```

> Ejecutá el proyecto con Gradle:
bash

``
./gradlew bootRun
``

En VS Code y apretá el botón de "Play" en MiAgendaWebApplication.java

Abrí tu navegador y entrá a: [http://localhost:8081]
Estructura del proyecto

El repositorio mantiene la versión original de consola en la raíz, y la versión web en su propia carpeta.

text

mi_agenda
  ├── web/                              # Proyecto Spring Boot
  │   ├── src/main/java/.../miagendaweb/
  │   │   ├── Tarea.java                   # Modelo (con getters/setters para JSON)
  │   │   ├── Agenda.java                  # Lógica de negocio (@Service)
  │   │   ├── AgendaController.java        # Endpoints REST (@RestController)
  │   │   └── MiAgendaWebApplication.java  # Punto de entrada de Spring
  │   └── src/main/resources/
  │       ├── application.properties       # Configuración (puerto 8081)
  │       └── static/
  │           └── index.html               # Frontend (HTML + JS)
  ├── Tarea.java                        # Versión de consola (sin modificar)
  ├── Agenda.java                       # Versión de consola (sin modificar)
  ├── Main.java                         # Versión de consola (sin modificar)
  └── README.md

> Próximos pasos

- Conectar el botón de "Modificar" en el index.html (usar el método PUT del Controller mediante un prompt o formulario auxiliar).
  
- Reemplazar el ArrayList de la clase Agenda por una base de datos (H2 o MySQL) usando Spring Data JPA para que las tareas no se pierdan al apagar el servidor.
  
- Fusionar la rama spring-boot con main cuando la versión web esté estable.
