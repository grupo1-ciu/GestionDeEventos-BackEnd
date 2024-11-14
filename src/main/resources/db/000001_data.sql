USE eventos;

INSERT INTO roles (nombre) VALUES
	('ROLE_USER'),
	('ROLE_ADMIN'),
	('ROLE_OPERATOR');

INSERT INTO estados_eventos (nombre) VALUES
	('disponible'),
	('en curso'),
	('finalizado'),
	('cancelado'),
	('reprogramado');

INSERT INTO estados_inscripciones (nombre) VALUES
	('activa'),
	('aceptada'),
	('rechazada'),
	('cancelada'),
	('concretada');

INSERT INTO usuarios (id, nombre, apellido, email, password) VALUES
	(uuid(),'root', 'root', 'root@root', '$2a$10$eFl6ONxvmhLcZjUV8iQfJO0nrFpFE3Fmty1q8ke0/xJfRTLHYbIqe');

INSERT INTO usuarios_roles (usuario_id , rol_id) VALUES
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_USER')),
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_ADMIN')),
	((SELECT id FROM eventos.usuarios WHERE email = 'root@root'),
	(SELECT id FROM eventos.roles WHERE nombre = 'ROLE_OPERATOR'));
