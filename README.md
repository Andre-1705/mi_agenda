# Mi Agenda Web

Aplicación Full Stack para gestionar tareas semanales. Este proyecto demuestra la evolución desde una simple agenda de consola en Java puro hasta una arquitectura web moderna, desacoplada y persistente.

## Funcionalidades

- Arquitectura desacoplada: Backend (Spring Boot) y Frontend (React) completamente separados.
- Comunicación en tiempo real mediante API REST y formato JSON.
- CRUD completo funcionando (Crear, Leer, Modificar, Eliminar).
- Persistencia de datos: Las tareas se guardan en un archivo de base de datos H2 y sobreviven al reiniciar el servidor.
- Interfaz moderna y responsiva construida con React y Tailwind CSS v4.
- Manejo de estado en React sin recargar la página (Single Page Application).

## Tecnologías utilizadas

**Backend:**

- Java 21
- Spring Boot (Web, REST, Data JPA)
- Hibernate (Mapeo Objeto-Relacional)
- H2 Database (Base de datos en archivo)
- Gradle

**Frontend:**

- React 19
- Vite
- Tailwind CSS v4 (con PostCSS)
- Fetch API (JavaScript nativo)

**Herramientas y Flujo de trabajo:**

- VS Code
- Git y GitHub (Ramas: main, spring-boot, feature/frontend-vite)
- Arquitectura de 4 capas (Controller, Service, Repository, Entity)

## Arquitectura del proyecto

El sistema funciona con dos servidores corriendo al mismo tiempo en puertos diferentes:

1. El Backend (Spring Boot) corre en el puerto 8082. Expone endpoints REST y devuelve JSON. Tiene configurado CORS para permitir que el frontend se comunique con él.
2. El Frontend (Vite) corre en el puerto 5173. Muestra la interfaz gráfica, maneja el estado de la aplicación y hace peticiones Fetch al backend.
3. La base de datos H2 genera un archivo local en la carpeta web/data/ para guardar la información de forma permanente.

## Como ejecutarlo

Requerimientos: Tener Java 21 y Node.js instalados.

1. Clonar el repositorio.
2. Abrir una terminal en la raíz del proyecto.

> Paso 1: Levantar el Backend

Entrar a la carpeta del backend y ejecutarlo con Gradle:
``
bash
cd web
./gradlew bootRun
``

(Esperar a que la consola diga "Started MiAgendaWebApplication...")

> Paso 2: Levantar el Frontend

Abrir UNA NUEVA terminal (dejar la del backend abierta), entrar a la carpeta del frontend e instalar las dependencias:

``
bash
cd frontend
npm install
npm run dev
``

> Paso 3: Usar la aplicación

Abrir el navegador y entrar a la dirección que indica Vite (usualmente [http://localhost:5173]).

> Estructura del proyecto

mi_agenda/
web/ # Proyecto Spring Boot (Backend)
data/ # Se genera aca el archivo de base de datos H2
src/main/java/.../miagendaweb/
Tarea.java # Modelo (@Entity con ID auto-generado y getters/setters)
TareaRepository.java # Interfaz JPA (metodos magicos de base de datos)
Agenda.java # Lógica de negocio (usa Repository en vez de ArrayList)
AgendaController.java # Endpoints REST (@RestController, @CrossOrigin)
MiAgendaWebApplication.java # Punto de entrada de Spring
src/main/resources/
application.properties # Configuracion (puerto 8082, conexion H2)
static/
index.html # Frontend antiguo de prueba (ya no se usa)
frontend/ # Proyecto React + Vite (Frontend)
src/
App.jsx # Componente principal (Maneja el CRUD y el estado)
index.css # Importación de Tailwind CSS v4
tailwind.config.js # Configuración de Tailwind
postcss.config.js # Configuración de PostCSS
package.json # Dependencias de Node
Tarea.java # Versión de consola (sin modificar)
Agenda.java # Versión de consola (sin modificar)
Main.java # Versión de consola (sin modificar)
README.md

> Próximos pasos

- Mejorar la UX del botón "Modificar" (reemplazar el prompt por un formulario inline en la tarjeta de React).
- Migrar la base de datos de H2 a MySQL o PostgreSQL para entorno de producción.
- Implementar autenticación (JWT) en el Backend.
