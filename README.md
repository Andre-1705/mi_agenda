
# Mi Agenda

Aplicación por consola desarrollada en Java para gestionar tareas semanales. Permite agregar, ver, modificar y eliminar tareas organizadas por día y hora.

## Funcionalidades

- Visualización de la semana completa (Lunes a Domingo).
- Alta de tareas (día, hora y descripción).
- Modificación de tareas existentes.
- Baja de tareas.
- Menú interactivo en consola.

## Tecnologías utilizadas

- Java (OpenJDK 21)
- Visual Studio Code
- Git y GitHub

## Cómo ejecutarlo

1. Asegurate de tener Java 21 instalado en tu PC.
2. Cloná este repositorio o descargá los archivos.
3. Abrí una terminal en la carpeta donde estén los archivos.
4. Compilá los archivos con el siguiente comando:
   javac *.java
5. Ejecutá el programa con el siguiente comando:
   java Main

## Estructura del proyecto

El proyecto se divide en 3 clases para mantener el código ordenado:

- Tarea.java: Representa una tarea. Guarda el día, la hora y el texto. Tiene un toString() para imprimirse bonita en consola.
- Agenda.java: Maneja la lógica. Utiliza un ArrayList de ArrayList (una lista de 7 días, donde cada día es una lista de Tareas). Contiene los métodos para mostrar, agregar, modificar y eliminar.
- Main.java: Es el punto de entrada. Se encarga de mostrar el menú, pedir los datos al usuario por teclado (usando Scanner) y llamar a los métodos de Agenda.

## Próximos pasos (Futuro)

- Migrar la aplicación de consola a una interfaz gráfica web.
- Crear un backend con Spring Boot exponiendo endpoints.
- Crear un frontend con HTML, CSS y JavaScript.
- Conectar ambos lados enviando los datos en formato JSON usando fetch.# Clase ¿Para qué?
