# Gestión de Tiendas - API REST

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?logo=docker&logoColor=white)
![JWT](https://img.shields.io/badge/Security-JWT-red?logo=jsonwebtokens&logoColor=white)
![Flyway](https://img.shields.io/badge/Migrations-Flyway-CC0200?logo=flyway&logoColor=white)

Aplicación para la gestión de tiendas de una cadena de supermercados, incluyendo la gestión de trabajadores, secciones y asignaciones.

## ⚠️ Nota de Seguridad

Este proyecto es para fines de aprendizaje y portfolio. Las credenciales en `application.properties` son para desarrollo local.

**Si lo usas en producción:**
- Cambia `jwt.secret` por una clave aleatoria segura
- Usa variables de entorno para credenciales sensibles
- Configura usuarios de base de datos con permisos limitados

## Tecnologías

- Java 21
- Spring Boot 3
- PostgreSQL
- Docker / Docker Compose
- Spring Security + JWT (autenticación)
- Flyway (migraciones de base de datos)
- Internacionalización (i18n)
- Swagger/OpenAPI

## Ejecución con Docker (Recomendado)

### Requisitos
- Docker
- Docker Compose

### Levantar la aplicación

Desde la raíz del proyecto (carpeta `gestiontiendas`):

```bash
docker-compose up --build
```

Esto levantará:
- **PostgreSQL** en puerto 5432
- **Servicio externo de tiendas** en puerto 8081
- **Aplicación** en puerto 8080

### Parar la aplicación

```bash
docker-compose down
```

### Parar y eliminar datos

```bash
docker-compose down -v
```

## Ejecución local (Desarrollo)

### Requisitos
- Java 21
- Maven
- PostgreSQL

### Configuración

1. Crear base de datos `mydb` en PostgreSQL
2. Configurar credenciales en `application.properties` si es necesario
3. Levantar el servicio externo de tiendas:

```bash
docker run -d -p 8081:8080 jameral/stores:latest
```

### Ejecutar

```bash
mvn spring-boot:run
```

## Colección de Peticiones

La colección de Postman está disponible en: `postman/gestiontiendas.postman_collection.json`

**Importante:** Todos los endpoints (excepto login y register) requieren autenticación JWT.

## Autenticación

La aplicación utiliza **JWT (JSON Web Tokens)** para la autenticación.

### Usuarios por defecto

- **Admin:** username: `admin`, password: `admin123`
- **User:** username: `user`, password: `user123`

### Flujo de autenticación

**1. Login:**
```bash
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "admin",
  "role": "ADMIN"
}
```

**2. Usar el token en requests:**

Añade el header en todas las peticiones protegidas:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**3. Registrar nuevo usuario:**
```bash
POST /api/auth/register
Content-Type: application/json

{
  "username": "nuevouser",
  "password": "password123"
}
```

### Endpoints públicos (sin autenticación)

- `POST /api/auth/login`
- `POST /api/auth/register`
- `/swagger-ui.html`
- `/api-docs`

### Endpoints protegidos

Todos los demás endpoints requieren token JWT válido.

## Internacionalización

La aplicación soporta mensajes de error y validaciones en **español** e **inglés**. Los mensajes están configurados en:
- `src/main/resources/messages.properties` (español, por defecto)
- `src/main/resources/messages_en.properties` (inglés)

El idioma se determina mediante el header `Accept-Language`.

**Ejemplos:**
```
Accept-Language: es  (español)
Accept-Language: en  (inglés)
```

## Documentación API (Swagger)

La documentación interactiva de la API está disponible en:

**Swagger UI:** http://localhost:8080/swagger-ui.html

Desde aquí puedes:
- Ver todos los endpoints disponibles
- Probar las peticiones directamente desde el navegador
- Ver los esquemas de los DTOs
- Consultar los códigos de respuesta

**OpenAPI JSON:** http://localhost:8080/api-docs

## Gestión de Base de Datos

El proyecto utiliza **Flyway** para el control de versiones y migraciones de base de datos.

### Migraciones

Los scripts de migración se encuentran en `src/main/resources/db/migration/`:

- `V1__crear_tablas.sql` - Creación de tablas principales
- `V2__insertar_secciones.sql` - Datos iniciales de secciones
- `V3__insertar_tiendas.sql` - Datos iniciales de tiendas
- `V4__insertar_aptitudes.sql` - Datos iniciales de aptitudes
- `V5__insertar_seccion_aptitudes.sql` - Relaciones sección-aptitud

### Historial de migraciones

Flyway mantiene un registro de todas las migraciones ejecutadas en la tabla `flyway_schema_history`.

**Nota:** Los scripts de migración nunca deben modificarse una vez ejecutados. Para realizar cambios en el esquema, crear nuevos scripts con versiones incrementales (V6, V7, etc.).

## Endpoints API

### Trabajadores

| Método | Endpoint                                   | Descripción                       |
|--------|--------------------------------------------|-----------------------------------|
| GET    | `/api/tiendas/{codigoTienda}/trabajadores` | Listar trabajadores de una tienda |
| POST   | `/api/tiendas/{codigoTienda}/trabajadores` | Crear trabajador                  |
| PUT    | `/api/trabajadores/{id}`                   | Editar trabajador                 |
| DELETE | `/api/trabajadores/{id}`                   | Eliminar trabajador               |

**Ejemplo crear trabajador:**
```json
POST /api/tiendas/1/trabajadores
{
  "nombre": "Juan",
  "apellidos": "García López",
  "dni": "12345678A",
  "horasDisponibles": 8
}
```

### Asignaciones

| Método | Endpoint                                                    | Descripción                          |
|--------|-------------------------------------------------------------|--------------------------------------|
| GET    | `/api/trabajadores/{trabajadorId}/asignaciones`             | Listar asignaciones de un trabajador |
| POST   | `/api/trabajadores/{trabajadorId}/asignaciones`             | Asignar trabajador a sección         |
| DELETE | `/api/trabajadores/{trabajadorId}/asignaciones/{seccionId}` | Desasignar de sección                |

**Ejemplo asignar:**
```json
POST /api/trabajadores/1/asignaciones
{
  "seccionId": 1,
  "horasAsignadas": 4
}
```

### Informes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/tiendas/{codigoTienda}/informes/estado` | Estado de la tienda con secciones y trabajadores |
| GET | `/api/tiendas/{codigoTienda}/informes/horas-sin-cubrir` | Secciones con horas sin cubrir |

### Tiendas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/tiendas/{codigoTienda}/secciones-aptitudes` | Tienda con secciones y aptitudes necesarias |

## Datos de prueba

La aplicación carga datos iniciales automáticamente:

**Tiendas:**
- 1: SANTOMERA
- 2: CAMINO DE SUAREZ
- 3: CARRIL DEL CONDE

**Secciones:**
- Horno (8 horas)
- Cajas (16 horas)
- Pescadería (16 horas)
- Verduras (16 horas)
- Droguería (16 horas)

**Aptitudes:**
- Hornear Pan, Repostería (Horno)
- Simpatía, Matemáticas (Cajas)
- Manejo de armas blancas, Limpiar pescado (Pescadería)
- Fortaleza física (Verduras)
- Alquimia (Droguería)

## Licencia

Copyright © 2025 Husnain Chaudhry. Todos los derechos reservados.

Este proyecto es de código abierto para fines de visualización y evaluación únicamente. No está permitido el uso, copia, modificación o distribución sin autorización explícita del autor.

## Autor

Husnain Chaudhry