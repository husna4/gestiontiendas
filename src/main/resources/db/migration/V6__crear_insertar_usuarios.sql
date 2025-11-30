-- Crear tabla usuarios
CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Insertar usuario admin por defecto (password: admin123)
-- BCrypt hash de "admin123"
INSERT INTO usuario (username, password, role)
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN');

-- Insertar usuario normal por defecto (password: user123)
-- BCrypt hash de "user123"
INSERT INTO usuario (username, password, role)
VALUES ('user', '$2a$10$DowJonesXjPk0vZ0K6Y0H.2gGxYuoMXWw/6VZvW3Rg2v0r.y0w4tK', 'USER');