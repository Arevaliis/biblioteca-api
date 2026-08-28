-- =========================================================
-- USUARIOS
-- =========================================================

INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Jose', 'jose@ual.com', NOW(), 'ADMIN', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Maria', 'maria@ual.com', NOW(), 'ADMIN', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Carlos', 'carlos@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Ana', 'ana@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Luis', 'luis@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Laura', 'laura@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Pedro', 'pedro@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Lucia', 'lucia@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Miguel', 'miguel@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Sofia', 'sofia@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Daniel', 'daniel@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Elena', 'elena@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Javier', 'javier@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Marta', 'marta@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Pablo', 'pablo@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Raquel', 'raquel@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Alberto', 'alberto@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Nerea', 'nerea@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO 123456789 (nombre, email, fecha_registro, rol, password) VALUES ('Sergio', 'sergio@ual.com', NOW(), 'USUARIO', '123456789');
INSERT INTO usuarios (nombre, email, fecha_registro, rol, password) VALUES ('Cristina', 'cristina@ual.com', NOW(), 'USUARIO', '123456789');

-- =========================================================
-- PERFILES
-- =========================================================

INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Almeria', '2000-09-09', '600000011', 1);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Roquetas de Mar', '1999-04-15', '600000012', 2);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('El Ejido', '1998-11-23', '600000013', 3);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Aguadulce', '2001-02-10', '600000014', 4);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Viator', '1997-07-18', '600000015', 5);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Huercal de Almeria', '2002-05-30', '600000016', 6);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Nijar', '1996-12-05', '600000017', 7);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Carboneras', '2000-08-21', '600000018', 8);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Vera', '1995-03-14', '600000019', 9);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Mojacar', '2001-10-27', '600000020', 10);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Garrucha', '1999-06-08', '600000021', 11);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Adra', '1997-01-19', '600000022', 12);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Berja', '2000-12-11', '600000023', 13);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Dalias', '1998-09-03', '600000024', 14);
INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Pechina', '2002-04-22', '600000025', 15);

-- =========================================================
-- PRESTAMOS
-- =========================================================

INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (1, '2026-08-06 13:12:18');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (1, '2026-08-10 10:30:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (1, '2026-08-15 16:45:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (2, '2026-08-07 09:15:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (2, '2026-08-18 11:20:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (3, '2026-08-08 12:00:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (3, '2026-08-19 17:30:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (4, '2026-08-09 14:10:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (5, '2026-08-11 18:00:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (5, '2026-08-20 09:45:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (6, '2026-08-12 10:00:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (7, '2026-08-13 13:25:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (7, '2026-08-21 15:40:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (8, '2026-08-14 16:00:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (9, '2026-08-16 11:30:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (10, '2026-08-17 12:45:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (10, '2026-08-22 18:15:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (11, '2026-08-18 09:00:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (12, '2026-08-19 14:30:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (13, '2026-08-20 16:20:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (14, '2026-08-21 10:10:00');
INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (15, '2026-08-22 13:50:00');

-- =========================================================
-- CATEGORIAS
-- =========================================================

INSERT INTO categorias(nombre) VALUES ('Java');
INSERT INTO categorias(nombre) VALUES ('Python');
INSERT INTO categorias(nombre) VALUES ('Programacion');
INSERT INTO categorias(nombre) VALUES ('POO');
INSERT INTO categorias(nombre) VALUES ('Spring');
INSERT INTO categorias(nombre) VALUES ('Spring Boot');
INSERT INTO categorias(nombre) VALUES ('Bases de Datos');
INSERT INTO categorias(nombre) VALUES ('SQL');
INSERT INTO categorias(nombre) VALUES ('Algoritmos');
INSERT INTO categorias(nombre) VALUES ('Clean Code');

-- =========================================================
-- LIBROS
-- =========================================================

INSERT INTO libros(autor, titulo) VALUES ('Robert C. Martin', 'Clean Code');
INSERT INTO libros(autor, titulo) VALUES ('Joshua Bloch', 'Effective Java');
INSERT INTO libros(autor, titulo) VALUES ('Bruce Eckel', 'Thinking in Java');
INSERT INTO libros(autor, titulo) VALUES ('Cay S. Horstmann', 'Core Java');
INSERT INTO libros(autor, titulo) VALUES ('Herbert Schildt', 'Java: The Complete Reference');
INSERT INTO libros(autor, titulo) VALUES ('Craig Walls', 'Spring in Action');
INSERT INTO libros(autor, titulo) VALUES ('Mark Heckler', 'Spring Boot: Up and Running');
INSERT INTO libros(autor, titulo) VALUES ('Antonio Cangiano', 'Python Programming');
INSERT INTO libros(autor, titulo) VALUES ('Eric Matthes', 'Python Crash Course');
INSERT INTO libros(autor, titulo) VALUES ('Luciano Ramalho', 'Fluent Python');
INSERT INTO libros(autor, titulo) VALUES ('Martin Fowler', 'Refactoring');
INSERT INTO libros(autor, titulo) VALUES ('Robert C. Martin', 'The Clean Coder');
INSERT INTO libros(autor, titulo) VALUES ('Thomas H. Cormen', 'Introduction to Algorithms');
INSERT INTO libros(autor, titulo) VALUES ('Martin Kleppmann', 'Designing Data-Intensive Applications');
INSERT INTO libros(autor, titulo) VALUES ('Ramez Elmasri', 'Fundamentals of Database Systems');

-- =========================================================
-- LIBROS_CATEGORIAS
-- =========================================================

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (1, 3);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (1, 4);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (1, 10);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (2, 1);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (2, 3);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (2, 4);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (2, 10);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (3, 1);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (3, 3);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (3, 4);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (4, 1);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (4, 3);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (5, 1);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (5, 3);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (6, 1);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (6, 5);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (6, 3);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (7, 1);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (7, 5);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (7, 6);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (8, 2);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (8, 3);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (9, 2);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (9, 3);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (10, 2);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (10, 4);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (11, 3);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (11, 4);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (11, 10);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (12, 3);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (12, 10);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (13, 3);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (13, 9);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (14, 7);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (14, 8);

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (15, 7);
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (15, 8);