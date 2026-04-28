# Conflict Tracker Fullstack

Buenas, aquí dejo la entrega de la práctica de despliegue Fullstack. He pasado la aplicación de local a una infraestructura real en la nube, separando el proyecto en tres capas conectadas entre sí para que sea funcional al 100%.

##  Enlaces del proyecto
* **Frontend (Vercel):** https://conflicttraker-frontend-adrian-luqu.vercel.app
* **Backend (Render):** https://conflict-tracker-backend-oiwy.onrender.com/

##  La Arquitectura
He fragmentado la aplicación siguiendo el esquema que pedía el enunciado para asegurar que cada capa sea independiente:
* **Persistence Layer:** Base de datos PostgreSQL alojada en **Supabase**. He usado el Connection Pooler para que la API no pierda la conexión.
* **Backend Layer:** API REST con Spring Boot desplegada en **Render**.
* **Frontend Layer:** SPA con Vue 3 optimizada para producción y desplegada en **Vercel**.

##  Variables de Entorno
Para que el proyecto funcione desde cero, he configurado estas variables en los paneles de control:

**En Render (Backend):**
* `SPRING_PROFILES_ACTIVE`: `prod` (para usar el `application-prod.yml`).
* `DB_URL`: La URL JDBC de mi instancia en Supabase.
* `DB_USERNAME`: El usuario de la base de datos.
* `DB_PASSWORD`: La contraseña alfanumérica.

**En Vercel (Frontend):**
* `VITE_API_URL`: La URL de la API en Render (sin la barra final).

##  Modificaciones y errores solucionados
El despliegue ha tenido sus retos, y estos son los cambios clave que he tenido que hacer:

1. **El Dialecto de Hibernate:** Al conectar con Supabase, Spring no reconocía el dialecto de la base de datos. Lo solucioné forzando `PostgreSQLDialect` en el `application-prod.yml`.
2. **Símbolos en la contraseña:** La contraseña original de Supabase me daba errores de conexión por los símbolos. Tuve que cambiarla por una **estrictamente alfanumérica** para que Render la aceptara bien.
3. **SPA Routing (Error 404):** Al refrescar la página en Vercel me daba error 404. He creado el archivo **`vercel.json`** con reglas de `rewrites` para que todas las rutas apunten al `index.html`.
4. **Seguridad CORS:** He quitado el permiso genérico (`*`) y he configurado el `@CrossOrigin` con mi URL de Vercel para cumplir con los requisitos de seguridad en producción.
5. **Constraints de datos:** La base de datos fallaba al insertar por los Enums del estado. He ajustado el flujo para que los datos se manden en mayúsculas (ej: `ACTIVO`) para que coincidan con los `CHECK constraints` de PostgreSQL.
