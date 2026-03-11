# Conflict Tracker API

Buenas, aquí dejo la entrega de la práctica del Backend. Es la API para gestionar conflictos y países que se pedía en el enunciado.

## Qué he usado
Básicamente lo que pedían los requisitos:
* Java 17 y Spring Boot 3.
* Base de datos H2 (en memoria) para que sea fácil de probar sin instalar nada.
* Maven para las dependencias.

## Cómo arrancarlo
Lo más fácil es abrir el proyecto en IntelliJ y darle al **Play** en la clase `ConflicttrackerApplication`.

Se abrirá en el puerto **8080**.

## Cómo probarlo 
Para no tener que meter datos a mano uno a uno, he dejado un archivo llamado **`datos.http`** en la carpeta principal.
* Ábrelo en IntelliJ.
* Verás unos triángulos verdes ("Play") al lado de cada petición.
* Dales en orden (del 1 al 8) y se crearán solos los países, conflictos y facciones de prueba.

## Endpoints principales
La API está en `/api/v1`:
* **Ver todo:** `GET /api/v1/conflicts`
* **Filtrar activos:** `GET /api/v1/conflicts?status=ACTIVE` (El requisito avanzado).
* **Ver por país:** `GET /api/v1/countries/{codigo}/conflicts`
* **Crear:** `POST /api/v1/conflicts` (Tenéis el formato JSON en el archivo `datos.http`).

## Extras
* **Base de datos:** Si queréis cotillear la H2, entrad en `http://localhost:8080/h2-console`.
    * JDBC URL: `jdbc:h2:mem:conflictdb`
    * User: `sa`
    * Password: (dejar vacío)
* **Web de prueba:** He hecho el `index.html` básico con JS nativo que pedía el enunciado. Está en `http://localhost:8080/index.html`. Si habéis cargado datos, saldrán ahí en una tabla.


El código está separado por capas (Controller, Service, Repository) y uso DTOs para no exponer las entidades directamente, tal cual pedía la rúbrica.


Video demostrando:https://www.youtube.com/watch?v=4UzYgdfMC2E
