# Gestión de Tiendas - API REST

Aplicación para la gestión de tiendas de una cadena de supermercados, incluyendo la gestión de trabajadores, secciones y asignaciones.

## Tecnologías

- Java 21
- Spring Boot 3.5.8
- PostgreSQL
- Docker / Docker Compose

## Ejecución con Docker

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

## Colección de Peticiones

La colección de Postman está disponible en: `postman/gestiontiendas.postman_collection.json`

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

**OpenAPI JSON:** http://localhost:8080/api-docs

## Endpoints API

### Trabajadores

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/tiendas/{codigoTienda}/trabajadores` | Listar trabajadores de una tienda |
| POST | `/api/tiendas/{codigoTienda}/trabajadores` | Crear trabajador |
| PUT | `/api/trabajadores/{id}` | Editar trabajador |
| DELETE | `/api/trabajadores/{id}` | Eliminar trabajador |

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

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/trabajadores/{trabajadorId}/asignaciones` | Listar asignaciones de un trabajador |
| POST | `/api/trabajadores/{trabajadorId}/asignaciones` | Asignar trabajador a sección |
| DELETE | `/api/trabajadores/{trabajadorId}/asignaciones/{seccionId}` | Desasignar de sección |

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

## Autor

Husnain Chaudhry
