-- Crear tabla usuarios
CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Insertar usuario admin por defecto (password: admin123)
-- BCrypt hash de "password123"
INSERT INTO usuario (username, password, role)
VALUES ('admin', '$2a$10$DE2ACRIFEhZNr6pWcr/xF.ofBsiFOIzqwfxtcreYit9FCSQb6.xWS', 'ADMIN');

-- Insertar usuario normal por defecto (password: user123)
-- BCrypt hash de "password123"
INSERT INTO usuario (username, password, role)
VALUES ('user', '$2a$10$DE2ACRIFEhZNr6pWcr/xF.ofBsiFOIzqwfxtcreYit9FCSQb6.xWS', 'USER');