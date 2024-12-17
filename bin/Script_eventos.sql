CREATE DATABASE IF NOT EXISTS eventos;

USE eventos;

CREATE TABLE eventos.tipos_eventos (
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100),
	CONSTRAINT tipo_evento_pkey PRIMARY KEY (id)
);

CREATE TABLE eventos.estados_eventos (
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100),
	CONSTRAINT estado_evento_pkey PRIMARY KEY (id)
);

CREATE TABLE eventos.estados_inscripciones (
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100),
	CONSTRAINT estado_inscripciones_pkey PRIMARY KEY (id)
);

CREATE TABLE eventos.roles (
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100),
	CONSTRAINT roles_pkey PRIMARY KEY(id)
);

CREATE TABLE eventos.usuarios (
	id UUID NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	CONSTRAINT id_usuario_pkey PRIMARY KEY (id)
);

CREATE TABLE eventos.usuarios_roles(
	id INT NOT NULL AUTO_INCREMENT,
	usuario_id UUID NOT NULL,
	rol_id INT NOT NULL,
	CONSTRAINT id_usuario_rol_pkey PRIMARY KEY (id),
	CONSTRAINT usuariosroles_usuarios_fkey FOREIGN KEY (usuario_id) REFERENCES eventos.usuarios(id),
	CONSTRAINT usuariosroles_roles_fkey FOREIGN KEY (rol_id) REFERENCES eventos.roles(id)
);

CREATE TABLE eventos.eventos(
	id UUID NOT NULL,
	sala VARCHAR (255),
	capacidad INT,
	descripcion VARCHAR(255),
	fecha_evento DATE NOT NULL,
	hora_inicio TIME NOT NULL,
	estado INT NOT NULL,
	tipo INT NOT NULL,
	CONSTRAINT id_evento_pkey PRIMARY KEY (id),
	CONSTRAINT eventos_estados_fkey FOREIGN KEY (estado) REFERENCES eventos.estados_eventos(id),
	CONSTRAINT eventos_tipos_fkey FOREIGN KEY (tipo) REFERENCES eventos.tipos_eventos(id)
);

CREATE TABLE eventos.inscripciones(
	id UUID NOT NULL,
	id_usuario UUID NOT NULL,
	id_evento UUID NOT NULL,
	id_estado_inscripcion INT NOT NULL,
	CONSTRAINT id_inscripcion_pkey PRIMARY KEY (id),
	CONSTRAINT inscripciones_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES eventos.usuarios(id),
	CONSTRAINT inscripciones_evento_fkey FOREIGN KEY (id_evento) REFERENCES eventos.eventos(id),
	CONSTRAINT inscripciones_estadoinscripcion_fkey FOREIGN KEY (id_estado_inscripcion) REFERENCES eventos.estados_inscripciones(id)
);

INSERT INTO eventos.roles (nombre) VALUES
	('ROLE_USER'),
	('ROLE_ADMIN'),
	('ROLE_OPERATOR');
	
INSERT INTO eventos.estados_eventos (nombre) VALUES
	('CREADO'),
	('DISPONIBLE'),
	('EN_CURSO'),
	('FINALIZADO'),
	('CANCELADO'),
	('REPROGRAMADO');

INSERT INTO eventos.tipos_eventos (nombre) VALUES
	('TALLER'),
	('CONCIERTO'),
	('CONFERENCIA'),
	('EXPOSICION'),
	('PROYECCION');
	
INSERT INTO eventos.estados_inscripciones (nombre) VALUES
	('PENDIENTE'),
	('ACEPTADA'),
	('RECHAZADA'),
	('CANCELADA'),
	('CONCRETADA');
	

select
        u1_0.id,
        u1_0.apellido,
        u1_0.email,
        u1_0.nombre,
        u1_0.password 
    from
        eventos.usuarios u1_0; 
    where
        u1_0.email='root@root';
       
INSERT INTO eventos.usuarios (id, nombre, apellido, email, password) VALUES
	(uuid(),'root', 'root', 'root@root', '$2a$10$eFl6ONxvmhLcZjUV8iQfJO0nrFpFE3Fmty1q8ke0/xJfRTLHYbIqe');

INSERT INTO eventos.usuarios (id, nombre, apellido, email, password) VALUES
	(uuid(),'root2', 'root2', 'root2@root2', '$2a$10$eFl6ONxvmhLcZjUV8iQfJO0nrFpFE3Fmty1q8ke0/xJfRTLHYbIqe');

	
INSERT INTO eventos.usuarios_roles (usuario_id , rol_id) VALUES
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_USER')),
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_ADMIN')),
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_OPERATOR'));

-- Insert de 6 eventos.
INSERT INTO eventos.eventos (id, sala, capacidad, descripcion, fecha_evento, hora_inicio, estado, tipo) VALUES
	(uuid(),
	('Cine y Teatro Español'),
	(2000),
	("Nahuel Pennisi en vivo"),
	('2024-12-05'),
	('21:00:00'),
	(SELECT id FROM eventos.estados_eventos WHERE nombre = 'DISPONIBLE'),
	(SELECT id FROM eventos.tipos_eventos WHERE nombre = 'CONCIERTO'));

INSERT INTO eventos.eventos (id, sala, capacidad, descripcion, fecha_evento, hora_inicio, estado, tipo) VALUES
	(uuid(),
	('Estadio Único'),
	(53000),
	("La Renga en vivo"),
	('2024-12-15'),
	('23:00:00'),
	(SELECT id FROM eventos.estados_eventos WHERE nombre = 'DISPONIBLE'),
	(SELECT id FROM eventos.tipos_eventos WHERE nombre = 'CONCIERTO'));

INSERT INTO eventos.eventos (id, sala, capacidad, descripcion, fecha_evento, hora_inicio, estado, tipo) VALUES
	(uuid(),
	('Casa de la cultura Miguel Briante'),
	(500),
	("Muestra de arte"),
	('2025-02-10'),
	('16:00:00'),
	(SELECT id FROM eventos.estados_eventos WHERE nombre = 'DISPONIBLE'),
	(SELECT id FROM eventos.tipos_eventos WHERE nombre = 'EXPOSICION'));

INSERT INTO eventos.eventos (id, sala, capacidad, descripcion, fecha_evento, hora_inicio, estado, tipo) VALUES
	(uuid(),
	('Cine y Teatro Español'),
	(2000),
	("Homenaje sinfónico a Nirvana"),
	('2024-11-30'),
	('21:00:00'),
	(SELECT id FROM eventos.estados_eventos WHERE nombre = 'DISPONIBLE'),
	(SELECT id FROM eventos.tipos_eventos WHERE nombre = 'CONCIERTO'));

INSERT INTO eventos.eventos (id, sala, capacidad, descripcion, fecha_evento, hora_inicio, estado, tipo) VALUES
	(uuid(),
	('Cine y Teatro Español'),
	(2000),
	("La venganza será terrible"),
	('2025-01-20'),
	('21:00:00'),
	(SELECT id FROM eventos.estados_eventos WHERE nombre = 'DISPONIBLE'),
	(SELECT id FROM eventos.tipos_eventos WHERE nombre = 'CONFERENCIA'));

INSERT INTO eventos.eventos (id, sala, capacidad, descripcion, fecha_evento, hora_inicio, estado, tipo) VALUES
	(uuid(),
	('Centro Costa Salguero'),
	(2000),
	("Argentina Comic Con"),
	('2024-12-08'),
	('13:00:00'),
	(SELECT id FROM eventos.estados_eventos WHERE nombre = 'DISPONIBLE'),
	(SELECT id FROM eventos.tipos_eventos WHERE nombre = 'CONFERENCIA'));

INSERT INTO eventos.inscripciones (id,id_usuario,id_evento,id_estado_inscripcion) VALUES
	(uuid(),
	(SELECT id FROM eventos.usuarios WHERE email='root@root'),
	(SELECT id FROM eventos.eventos WHERE eventos.eventos.descripcion = 'Nahuel Pennisi en vivo'),
	(SELECT id FROM eventos.estados_inscripciones WHERE nombre = 'PENDIENTE'));

INSERT INTO eventos.inscripciones (id, id_usuario, id_evento, id_estado_inscripcion) VALUES
	(uuid(),
	(SELECT id FROM eventos.usuarios WHERE email='root@root'),
	(SELECT id FROM eventos.eventos WHERE eventos.eventos.descripcion = 'La venganza será terrible'),
	(SELECT id FROM eventos.estados_inscripciones WHERE nombre = 'PENDIENTE'));

SELECT * FROM eventos.usuarios u;

SELECT * FROM eventos.inscripciones i;

SELECT * FROM eventos.usuarios u 
	LEFT JOIN 
		eventos.inscripciones i 
		ON i.id_usuario = u.id 
	WHERE
		u.email = 'root@root';
	
SELECT * FROM eventos.eventos e;

SELECT * FROM eventos.estados_eventos ee;

-- SELECT u.nombre, u.apellido, r.nombre, i.id_evento, ei.nombre FROM eventos.usuarios u
-- 	LEFT JOIN eventos.usuarios_roles ur
-- 		ON ur.usuario_id = u.id
-- 	LEFT JOIN eventos.roles r
-- 		ON r.id = ur.rol_id 
-- 	LEFT JOIN eventos.inscripciones i 
-- 		ON i.id_usuario = u.id 
-- 	LEFT JOIN eventos.estados_inscripciones ei 
-- 		ON ei.id = i.id_estado_inscripcion 
-- 	WHERE 
-- 		u.email = 'root@root';