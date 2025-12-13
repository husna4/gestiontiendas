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
VALUES ('admin', '$2a$10$deNCSKnH5e4AysOEwT8B2eFvBjNOd827I/xrjU.yBZluYEEBoJhZK', 'ADMIN');

-- Insertar usuario normal por defecto (password: user123)
-- BCrypt hash de "user123"
INSERT INTO usuario (username, password, role)
VALUES ('user', '$2a$10$5LOH3uPN.G5k0ZzgEzVCcelM.7WWq1FIn/qwHTKwQ9jrQkluFqiP2', 'USER');