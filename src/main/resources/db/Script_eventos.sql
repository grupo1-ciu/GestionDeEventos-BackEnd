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

CREATE TABLE eventos.salas(
	id UUID NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	capacidad INT(50) NOT NULL,
	direccion VARCHAR(320) NOT NULL,
	CONSTRAINT id_sala_pkey PRIMARY KEY (id)
);

CREATE TABLE eventos.eventos(
	id UUID NOT NULL,
	sala UUID NOT NULL,
	fecha_evento DATE NOT NULL,
	hora_inicio DATETIME NOT NULL,
	hora_fin DATETIME NOT NULL,
	estado INT NOT NULL,
	tipo INT NOT NULL,
	CONSTRAINT id_evento_pkey PRIMARY KEY (id),
	CONSTRAINT eventos_salas_fkey FOREIGN KEY (sala) REFERENCES eventos.salas(id),
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
	('disponible'),
	('en curso'),
	('finalizado'),
	('cancelado'),
	('reprogramado');
	
INSERT INTO eventos.estados_inscripciones (nombre) VALUES
	('activa'),
	('aceptada'),
	('rechazada'),
	('cancelada'),
	('concretada');
	
INSERT INTO eventos.usuarios (id, nombre, apellido, email, password) VALUES
	(uuid(),'root', 'root', 'root@root', 'root');
	
INSERT INTO eventos.usuarios_roles (usuario_id , rol_id) VALUES
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_USER')),
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_ADMIN')),
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_OPERATOR'));