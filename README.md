
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
- Conectar ambos lados enviando los datos en formato JSON usando fetch.
  
## Proyección por etapas

> ETAPA 1: Arrancar Spring Boot
Objetivo: Conectar Java con el navegador.

Instalar las herramientas necesarias (Spring Initializr).
Crear un proyecto nuevo vacío.
Hacer que al entrar a [http://localhost:8080] diga "Agenda funcionando".
Regla: Sin lógica, sin tareas. Solo ver que el servidor levanta.

> ETAPA 2: Migrar el cerebro (Servicios)
Objetivo: Que tu código actual funcione dentro de Spring.

Copiar tus clases Tarea y Agenda al nuevo proyecto.
Aprender qué es un @Service (para que Spring cuide tu Agenda).
Regla: Seguimos sin interfaz gráfica. Probamos que funciona usando la consola o herramientas de prueba, no con el navegador todavía.

> ETAPA 3: Abrir las puertas (Endpoints y JSON)
Objetivo: Que el navegador pueda pedir las tareas y mandar tareas nuevas.

Crear un @RestController.
Hacer la ruta GET /tareas que devuelva tu ArrayList en formato JSON.
Hacer la ruta POST /tareas que reciba un JSON y llame a tu agregarTarea().
Regla: Acá probamos con Postman o con el navegador viendo texto puro JSON. Nada de HTML todavía.

> ETAPA 4: La cara del programa (Frontend básico)
Objetivo: Unir el JavaScript con el Spring Boot.

Crear un archivo index.html en la carpeta de recursos.
Hacer una lista simple ul y li que muestre los días.
Usar JavaScript (fetch) para llamar al GET /tareas de la Etapa 3 y llenar esa lista.
Hacer un formulario básico para agregar tareas.
Regla: CSS feo está bien. El foco es que el botón "Agregar" le hable al fetch y this.actualice la lista.

> ETAPA 5: Detalles
Objetivo: Que no se pierdan los datos al cerrar y que se vea bien.

Agregar una base de datos (H2 o MySQL) con Spring Data JPA para reemplazar el ArrayList.
Agregar CSS para que se vea como una agenda real (tarjetas, colores).
Botones de "Modificar" y "Eliminar" en el HTML.
