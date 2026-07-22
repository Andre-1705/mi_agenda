# Mi Agenda Semanal

Proyecto de aprendizaje personal que recorre distintas etapas de desarrollo, desde una aplicación de consola en Java hasta una API REST en Kotlin con frontend en React, y finalmente una app móvil en React Native.

---

## Etapa 1 — Aplicación de Consola (Java)

La versión original. Tres archivos Java en la raíz del repositorio, sin frameworks, sin base de datos:

> **Main.java**
> Punto de entrada. Menú interactivo por consola con un loop que permite agregar, eliminar, modificar y listar tareas.
>
> **Agenda.java**
> Lógica de negocio. Maneja una lista de tareas en memoria organizadas por día de la semana.
>
> **Tarea.java**
> Modelo. Una tarea con día, hora y texto.

**Para ejecutar:**

```bash
javac *.java
java Main
```

Objetivo de esta etapa: reutilizar los fundamentos de Java — clases, colecciones, control de flujo, y entrada/salida por consola.

---

## Etapa 2 — Spring Boot + React + Base de Datos

La agenda se convierte en una aplicación web fullstack:

### Backend (`web/`)

API REST construida con **Spring Boot 4.1.0** y **Kotlin 2.1.0**. Usa Spring Data JPA con H2 (base de datos en memoria) para persistir las tareas.

**Estructura de archivos:**

- **MiAgendaWebApplication.kt** — Punto de entrada de Spring Boot.
- **Tarea.kt** — Entity de JPA. Cada tarea tiene id, día, hora y texto.
- **TareaRepository.kt** — Interface que extiende JpaRepository. Spring genera la implementación automáticamente.
- **Agenda.kt** — Servicio (@Service) con la lógica de negocio: obtener la semana, agregar, eliminar y modificar tareas.
- **AgendaController.kt** — Controlador REST (@RestController) con endpoints para CRUD completo. Incluye una función de extensión `sinAcentos()` que normaliza tildes usando `java.text.Normalizer` para que días como "miércoles" y "sábado" se guarden correctamente.

**Endpoints:**

| Método | Ruta                       | Descripción                        |
|--------|----------------------------|------------------------------------|
| GET    | `/tareas`                  | Obtiene tareas organizadas por día |
| POST   | `/tareas`                  | Agrega una nueva tarea             |
| PUT    | `/tareas/{dia}/{numTarea}` | Modifica una tarea existente       |
| DELETE | `/tareas/{dia}/{numTarea}` | Elimina una tarea                  |

### Frontend (`frontend/`)

Aplicación de React construida con Vite. Consume la API del backend y permite gestionar la agenda semanal desde el navegador con una interfaz visual.

### Sub-etapas de la migración

El backend nació escrito en Java y luego se migró a Kotlin:

- **2a — Java original:** Los archivos .java fueron reemplazados por sus equivalentes .kt.
- **2b — Migración a Kotlin:** Se reescribió toda la lógica aprovechando data class, constructores secundarios, y la sintaxis más concisa de Kotlin. Se agregaron los plugins `kotlin-spring` (para que Spring pueda crear proxies de clases Kotlin) y `kotlin-jpa` (para generar automáticamente el constructor no-arg que necesita JPA). El resultado fue pasar de 219 líneas Java a 151 líneas Kotlin.

---

## Etapa 3 — React Native: de la web al celular

La agenda da el salto al celular. El backend de Kotlin sigue siendo el mismo — lo que cambia es el "cabeza". En vez de React en el navegador, ahora hay una app nativa de Android construida con React Native y TypeScript.

### ¿Por qué React Native?

Permite reutilizar la lógica de React que ya se conocía de la web (estados, componentes, useEffect, fetch) pero generando una app real que se instala en un teléfono Android. El backend (Spring Boot + Kotlin) no tocó nada — la app móvil consume exactamente la misma API en el puerto 8082.

### Proyecto separado

MiAgendaMobile vive en su **propio repositorio Git** dentro de la carpeta `MiAgendaMobile/`. El repo principal lo ignora mediante `.gitignore` porque son proyectos independientes con su propio historial de commits.

### Qué se construyó

Un solo archivo `App.tsx` con toda la interfaz: lista de días de la semana, tareas por día, y CRUD completo (crear, leer, editar, eliminar). La app se conecta al backend usando la dirección especial `http://10.0.2.2:8082` que es la forma en que el emulador Android accede a la computadora host (el "localhost" del celu virtual).

**Funcionalidades:**

- Ver todas las tareas organizadas por día
- Tocar un día para agregar una nueva tarea con hora y texto
- Editar una tarea existente (hora y texto)
- Eliminar una tarea con un botón

**Desafíos encontrados durante el desarrollo:**

- **Acentos (miércoles, sábado):** Al igual que en la web, los días con tilde no se guardaban. La solución ya existía en el backend con `sinAcentos()`, así que la app mobile heredó la corrección automáticamente.
- **Puertos confusos:** El backend usa 8082, Metro bundler usa 8081, y ADB usa 5037. Son tres cosas distintas que conviven sin problema.
- **Emulador:** El emulador Android a veces arranca con pantalla negra. La solución es usar cold boot (`-no-snapshot-load`) para que arranque limpio desde cero.
- **Metro "No apps connected":** Cuando Metro no detecta la app, hay que ejecutar `adb reverse tcp:8081 tcp:8081` para conectar el emulador con el bundler.

### Stack tecnológico de la app móvil

| Componente        | Tecnología                           |
|-------------------|--------------------------------------|
| Framework         | React Native (CLI)                   |
| Lenguaje          | TypeScript (.tsx)                    |
| Emulador          | Android Studio AVD (Pixel 7, API 36) |
| Backend compartido| Spring Boot 4.1.0 + Kotlin 2.1.0     |

---

## Stack Tecnológico Completo

| Componente         | Tecnología                       |
|--------------------|----------------------------------|
| Backend            | Spring Boot 4.1.0 + Kotlin 2.1.0 |
| Base de datos      | H2 (en memoria)                  |
| ORM                | Spring Data JPA                  |
| Frontend web       | React + Vite                     |
| Frontend móvil     | React Native + TypeScript        |
| Build backend      | Gradle                           |
| Build móvil        | Gradle (Android)                 |

---

## Cómo ejecutar el proyecto

### Requisitos previos

- **Java JDK** (para el backend Spring Boot)
- **Node.js y npm** (para el frontend web y la app móvil)
- **Android Studio** con SDK instalado (solo para la app móvil)
- **Git**

### Opción A — Solo la app web (Etapa 2)

**1. Levantar el backend:**

```bash
cd web
./gradlew bootRun
```

La API queda disponible en [http://localhost:8082](http://localhost:8082). Dejás esta terminal abierta.

**2. Levantar el frontend:**

Abrís otra terminal:

```bash
cd frontend
npm install
npm run dev
```

La app web queda disponible en [http://localhost:5173](http://localhost:5173).

### Opción B — App móvil (Etapa 3)

Esta opción necesita **tres terminales** abiertas al mismo tiempo: una para el backend, una para el emulador, y una para la app móvil.

**Paso 1 — Levantar el backend** (terminal 1):

```bash
cd web
./gradlew bootRun
```

Dejás esta terminal abierta. El backend corre en el puerto **8082**.

**Paso 2 — Abrir el emulador Android** (terminal 2):

Primero agregá las herramientas de Android al PATH (hay que hacerlo cada vez que abrís una terminal nueva):

```bash
export PATH="$PATH:/c/Users/TU_USUARIO/AppData/Local/Android/Sdk/platform-tools:/c/Users/TU_USUARIO/AppData/Local/Android/Sdk/emulator"
```

> Reemplazá `TU_USUARIO` por tu nombre de usuario de Windows.

Luego arrancá el emulador con cold boot (para evitar pantalla negra):

```bash
emulator -avd Pixel_7 -no-snapshot-load
```

> El nombre `Pixel_7` depende de cómo se llame tu emulador. Si no estás seguro, ejecutá `emulator -list-avds` para ver el nombre correcto.

Esperá a que el emulador termine de arrancar (aparezcan los iconos del celu). Esto puede tardar uno o dos minutos.

**Paso 3 — Correr la app móvil** (terminal 3):

Entrá al proyecto móvil:

```bash
cd MiAgendaMobile
```

Levantá Metro bundler (el servidor de desarrollo de React Native):

```bash
npx react-native start
```

Dejás esta terminal abierta. Metro corre en el puerto **8081**.

Abrís **otra terminal más** (terminal 4) dentro de MiAgendaMobile y ejecutás:

```bash
npx react-native run-android
```

Esto compila la app, la instala en el emulador y la abre automáticamente.

> **Si la app no aparece en el emulador:** Probá deslizar hacia arriba en la pantalla del celu para ver todas las apps, o ejecutá `adb shell am start -n com.miagendamobile/.MainActivity` para abrirla manualmente.
>
> **Si Metro dice "No apps connected":** Ejecutá `adb reverse tcp:8081 tcp:8081` para conectar el emulador con Metro.

### Resumen de puertos

| Puerto | Qué es                    | Lo usa                       |
|--------|---------------------------|------------------------------|
| 5037   | ADB (Android Debug Bridge)| Comunicación con el emulador |
| 8081   | Metro bundler             | React Native                 |
| 8082   | Spring Boot backend       | API REST                     |
| 5173   | Vite dev server           | Frontend web                 |

---

## Estructura del repositorio

```
mi_agenda/
├── web/                    # Backend Spring Boot + Kotlin
│   └── src/main/java/.../  # Agenda.kt, Tarea.kt, AgendaController.kt, etc.
├── frontend/               # Frontend web React + Vite
│   └── src/App.tsx         # Interfaz web de la agenda
├── MiAgendaMobile/         # App móvil React Native (repo Git separado)
│   └── App.tsx             # Toda la interfaz móvil (CRUD completo)
├── .gitignore              # Ignora MiAgendaMobile/ y build/
└── README.md
```
