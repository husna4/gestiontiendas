INSERT INTO seccion (id, nombre, horas_trabajo_diarias) VALUES (1, 'Horno', 8) ON CONFLICT (id) DO NOTHING;
INSERT INTO seccion (id, nombre, horas_trabajo_diarias) VALUES (2, 'Cajas', 16) ON CONFLICT (id) DO NOTHING;
INSERT INTO seccion (id, nombre, horas_trabajo_diarias) VALUES (3, 'Pescadería', 16) ON CONFLICT (id) DO NOTHING;
INSERT INTO seccion (id, nombre, horas_trabajo_diarias) VALUES (4, 'Verduras', 16) ON CONFLICT (id) DO NOTHING;
INSERT INTO seccion (id, nombre, horas_trabajo_diarias) VALUES (5, 'Droguería', 16) ON CONFLICT (id) DO NOTHING;

INSERT INTO tienda (id, codigo, nombre) VALUES (1, '1', 'SANTOMERA') ON CONFLICT (id) DO NOTHING;
INSERT INTO tienda (id, codigo, nombre) VALUES (2, '2', 'CAMINO DE SUAREZ') ON CONFLICT (id) DO NOTHING;
INSERT INTO tienda (id, codigo, nombre) VALUES (3, '3', 'CARRIL DEL CONDE') ON CONFLICT (id) DO NOTHING;

INSERT INTO aptitud (id, nombre, descripcion) VALUES (1, 'Hornear Pan', 'Capacidad para hornear pan artesanal') ON CONFLICT (id) DO NOTHING;
INSERT INTO aptitud (id, nombre, descripcion) VALUES (2, 'Repostería', 'Conocimientos en repostería y pastelería') ON CONFLICT (id) DO NOTHING;
INSERT INTO aptitud (id, nombre, descripcion) VALUES (3, 'Simpatía', 'Trato amable con los clientes') ON CONFLICT (id) DO NOTHING;
INSERT INTO aptitud (id, nombre, descripcion) VALUES (4, 'Matemáticas', 'Habilidad para el cálculo rápido') ON CONFLICT (id) DO NOTHING;
INSERT INTO aptitud (id, nombre, descripcion) VALUES (5, 'Manejo de armas blancas', 'Destreza con cuchillos y herramientas de corte') ON CONFLICT (id) DO NOTHING;
INSERT INTO aptitud (id, nombre, descripcion) VALUES (6, 'Limpiar pescado', 'Capacidad para limpiar y preparar pescado') ON CONFLICT (id) DO NOTHING;
INSERT INTO aptitud (id, nombre, descripcion) VALUES (7, 'Fortaleza física', 'Resistencia para cargar peso') ON CONFLICT (id) DO NOTHING;
INSERT INTO aptitud (id, nombre, descripcion) VALUES (8, 'Alquimia', 'Conocimientos en productos químicos y droguería') ON CONFLICT (id) DO NOTHING;

-- Seccion-Aptitud (Horno: 1, Cajas: 2, Pescadería: 3, Verduras: 4, Droguería: 5)
-- Horno -> Hornear Pan, Repostería
INSERT INTO seccion_aptitud (seccion_id, aptitud_id)
SELECT 1, 1 WHERE NOT EXISTS (SELECT 1 FROM seccion_aptitud WHERE seccion_id = 1 AND aptitud_id = 1);
INSERT INTO seccion_aptitud (seccion_id, aptitud_id)
SELECT 1, 2 WHERE NOT EXISTS (SELECT 1 FROM seccion_aptitud WHERE seccion_id = 1 AND aptitud_id = 2);

-- Cajas -> Simpatía, Matemáticas
INSERT INTO seccion_aptitud (seccion_id, aptitud_id)
SELECT 2, 3 WHERE NOT EXISTS (SELECT 1 FROM seccion_aptitud WHERE seccion_id = 2 AND aptitud_id = 3);
INSERT INTO seccion_aptitud (seccion_id, aptitud_id)
SELECT 2, 4 WHERE NOT EXISTS (SELECT 1 FROM seccion_aptitud WHERE seccion_id = 2 AND aptitud_id = 4);

-- Pescadería -> Manejo de armas blancas, Limpiar pescado
INSERT INTO seccion_aptitud (seccion_id, aptitud_id)
SELECT 3, 5 WHERE NOT EXISTS (SELECT 1 FROM seccion_aptitud WHERE seccion_id = 3 AND aptitud_id = 5);
INSERT INTO seccion_aptitud (seccion_id, aptitud_id)
SELECT 3, 6 WHERE NOT EXISTS (SELECT 1 FROM seccion_aptitud WHERE seccion_id = 3 AND aptitud_id = 6);

-- Verduras -> Fortaleza física
INSERT INTO seccion_aptitud (seccion_id, aptitud_id)
SELECT 4, 7 WHERE NOT EXISTS (SELECT 1 FROM seccion_aptitud WHERE seccion_id = 4 AND aptitud_id = 7);

-- Droguería -> Alquimia
INSERT INTO seccion_aptitud (seccion_id, aptitud_id)
SELECT 5, 8 WHERE NOT EXISTS (SELECT 1 FROM seccion_aptitud WHERE seccion_id = 5 AND aptitud_id = 8);