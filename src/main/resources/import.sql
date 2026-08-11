INSERT INTO usuarios (nombre, email, fecha_registro) VALUES ('Jose', 'jose@ual.com', NOW())
INSERT INTO usuarios (nombre, email, fecha_registro) VALUES ('Maria', 'mmm@ual.com', NOW())

INSERT INTO perfiles (direccion, fecha_nacimiento, telefono, usuario_id) VALUES ('Almeria', '2000-09-09','600000011', 1)

INSERT INTO prestamos (usuario_id, fecha_prestamo) VALUES (1, '2026-08-06 13:12:18.196190')

INSERT INTO categorias(nombre) VALUES ('Java')
INSERT INTO categorias(nombre) VALUES ('Python')
INSERT INTO categorias(nombre) VALUES ('Programacion')
INSERT INTO categorias(nombre) VALUES ('POO')

INSERT INTO libros(autor, titulo) VALUES ('Robert C. Martin', 'Clean Code')
INSERT INTO libros(autor, titulo) VALUES ('Joshua Bloch', 'Effective Java')

INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (1, 1)
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (1, 2)
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (1, 3)
INSERT INTO libros_categorias (libro_id, categoria_id) VALUES (2, 2)