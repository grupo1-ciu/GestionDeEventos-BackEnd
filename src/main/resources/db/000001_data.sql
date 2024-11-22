USE eventos;

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

INSERT INTO usuarios (id, nombre, apellido, email, password) VALUES
	(uuid(),'root', 'root', 'root@root', '$2a$10$eFl6ONxvmhLcZjUV8iQfJO0nrFpFE3Fmty1q8ke0/xJfRTLHYbIqe');

INSERT INTO usuarios_roles (usuario_id , rol_id) VALUES
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_USER')),
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_ADMIN')),
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_OPERATOR'));
	
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

INSERT INTO eventos.eventos (id, sala, capacidad, descripcion, fecha_evento, hora_inicio, estado, tipo) VALUES
	(uuid(),
	('Asamblea San Telmo'),
	(2000),
	("2 Minutos"),
	('2024-11-19'),
	('13:00:00'),
	(SELECT id FROM eventos.estados_eventos WHERE nombre = 'FINALIZADO'),
	(SELECT id FROM eventos.tipos_eventos WHERE nombre = 'CONCIERTO'));

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