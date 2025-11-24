CREATE TABLE tienda (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL
);

-- Crear tabla secciones
CREATE TABLE seccion (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    horas_trabajo_necesarias INTEGER NOT NULL
);

-- Crear tabla trabajadores
CREATE TABLE trabajador (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    dni VARCHAR(9) NOT NULL UNIQUE,
    horas_disponibles INTEGER NOT NULL,
    tienda_id BIGINT NOT NULL,
    CONSTRAINT fk_trabajador_tienda FOREIGN KEY (tienda_id) REFERENCES tienda(id)
);

-- Crear tabla asignaciones
CREATE TABLE asignacion_trabajador_seccion (
    id BIGSERIAL PRIMARY KEY,
    trabajador_id BIGINT NOT NULL,
    seccion_id BIGINT NOT NULL,
    horas_asignadas INTEGER NOT NULL,
    CONSTRAINT fk_asignacion_trabajador FOREIGN KEY (trabajador_id) REFERENCES trabajador(id) ON DELETE CASCADE,
    CONSTRAINT fk_asignacion_seccion FOREIGN KEY (seccion_id) REFERENCES seccion(id)
);

-- Crear tabla aptitudes
CREATE TABLE aptitud (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

-- Tabla intermedia seccion-aptitud
CREATE TABLE seccion_aptitud (
    seccion_id BIGINT NOT NULL,
    aptitud_id BIGINT NOT NULL,
    PRIMARY KEY (seccion_id, aptitud_id),
    CONSTRAINT fk_seccion_aptitud_seccion FOREIGN KEY (seccion_id) REFERENCES seccion(id),
    CONSTRAINT fk_seccion_aptitud_aptitud FOREIGN KEY (aptitud_id) REFERENCES aptitud(id)
);

-- Tabla intermedia trabajador-aptitud
CREATE TABLE trabajador_aptitud (
    trabajador_id BIGINT NOT NULL,
    aptitud_id BIGINT NOT NULL,
    PRIMARY KEY (trabajador_id, aptitud_id),
    CONSTRAINT fk_trabajador_aptitud_trabajador FOREIGN KEY (trabajador_id) REFERENCES trabajador(id) ON DELETE CASCADE,
    CONSTRAINT fk_trabajador_aptitud_aptitud FOREIGN KEY (aptitud_id) REFERENCES aptitud(id)
);