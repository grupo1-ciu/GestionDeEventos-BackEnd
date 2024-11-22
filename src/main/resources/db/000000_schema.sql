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
