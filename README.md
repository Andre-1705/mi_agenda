# Mi Agenda Semanal

Proyecto de aprendizaje personal que recorre distintas etapas de desarrollo, desde una aplicación de consola en Java hasta una API REST en Kotlin con frontend en React.

## Etapa 1 — Aplicación de Consola (Java)

La versión original. Tres archivos Java en la raíz del repositorio, sin frameworks, sin base de datos:

> Main.java
  Punto de entrada. Menú interactivo por consola con un loop que permite agregar, eliminar, modificar y listar tareas.
> Agenda.java
  Lógica de negocio. Maneja una lista de tareas en memoria organizadas por día de la semana.
> Tarea.java
  Modelo. Una tarea con día, hora y texto.

**Para ejecutar:**

javac *.javajava Main
Objetivo de esta etapa: reutilizar los fundamentos de Java — clases, colecciones, control de flujo, y entrada/salida por consola.

## Etapa 2 — Spring Boot + React + Base de Datos

La agenda se convierte en una aplicación web fullstack:

> Backend (web/)

API REST construida con Spring Boot 4.1.0 y Kotlin 2.1.0. Usa Spring Data JPA con H2 (base de datos en memoria) para persistir las tareas.

> Estructura de archivos:

- MiAgendaWebApplication.kt — Punto de entrada de Spring Boot.
- Tarea.kt — Entity de JPA. Cada tarea tiene id, día, hora y texto.
- TareaRepository.kt — Interface que extiende JpaRepository. Spring genera la implementación automáticamente.
- Agenda.kt — Servicio (@Service) con la lógica de negocio: obtener la semana, agregar, eliminar y modificar tareas.
- AgendaController.kt — Controlador REST (@RestController) con endpoints para CRUD completo.

> Endpoints:

| Método |           Ruta         |         Descripción            |
|--------|------------------------|--------------------------------|
| GET    |/tareas                 |Obtiene tareas organizadas x día|
| POST   |/tareas                 | Agrega una nueva tarea         |
| PUT    |/tareas/{dia}/{numTarea}| Modifica una tarea existente   |
| DELETE |/tareas/{dia}/{numTarea}| Elimina una tarea              |

> Frontend (frontend/)

Aplicación de React construida con Vite. Consume la API del backend y permite gestionar la agenda semanal desde el navegador con una interfaz visual.

*Sub-etapas de la migración
El backend nació escrito en Java y luego se migró a Kotlin:

- 2a — Java original: Los archivos .java fueron reemplazados por sus equivalentes .kt.
- 2b — Migración a Kotlin: Se reescribió toda la lógica aprovechando data class, constructor secundarios, y la sintaxis más concisa de Kotlin. Se agregaron los plugins kotlin-spring (para que Spring pueda crear proxies de clases Kotlin) y kotlin-jpa (para generar automáticamente el constructor no-arg que necesita JPA). El resultado fue pasar de 219 líneas Java a 151 líneas Kotlin.

> Stack Tecnológico

|     Componente     |          Tecnología           |
|--------------------|-------------------------------|
|Backend             | Spring Boot 4.1.0 Kotlin 2.1.0|
|Base de datos       | H2 (en memoria)               |
|ORM(mapeo obj-relac)| Spring Data JPA               |
|Frontend            | React + Vite                  |
|Build               | Gradle                        |

*Cómo ejecutar

> Backend

```bash
cd web
./gradlew bootRun
```

La API queda disponible en [http://localhost:8082].

> Frontend

```bash
cd frontend
npm install
npm run dev
```

La app queda disponible en [http://localhost:5173].
