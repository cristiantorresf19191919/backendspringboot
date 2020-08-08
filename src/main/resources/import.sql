
insert into regiones (id,nombre) values (1,"Sudamerica");
insert into regiones (id,nombre) values (2,"CentroAmerica");
insert into regiones (id,nombre) values (3,"NorteAmerica");
insert into regiones (id,nombre) values (4,"Europa");
insert into regiones (id,nombre) values (5,"Asia");
insert into regiones (id,nombre) values (6,"Africa");
insert into regiones (id,nombre) values (7,"Oceania");
insert into regiones (id,nombre) values (8,"Antartida");
insert into clientes (region_id,nombre, apellido, email, create_at) values(1,'Andrés', 'Guzmán', 'profesdsdsdddor@bolsadeideas.com','2018-01-01');
insert into clientes (region_id,nombre, apellido, email, create_at) values(2,'Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
insert into clientes (region_id,nombre, apellido, email, create_at) values(3,'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
insert into clientes (region_id,nombre, apellido, email, create_at) values(4,'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2018-01-04');
insert into clientes (region_id,nombre, apellido, email, create_at) values(5,'Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
insert into clientes (region_id,nombre, apellido, email, create_at) values(6,'Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
insert into clientes (region_id,nombre, apellido, email, create_at) values(7,'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');


insert into usuarios (username, password, enabled, nombre, apellido, email) values   ('admin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',1,'cristian','torres','cristian.torres19@hotmail.com');
insert into usuarios (username, password, enabled,nombre, apellido, email) values ('cristian','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq',1,'andres','guzman','andres@mail.com');



insert into roles (nombre) values ('ROLE_USER');
insert into roles (nombre) values ('ROLE_ADMIN');

insert into usuarios_roles (usuario_id, role_id) values (1, 2);
insert into usuarios_roles (usuario_id, role_id) values (2, 1);

INSERT INTO productos (nombre, precio, created_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, created_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, created_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, created_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, created_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, created_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, created_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());


INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());

INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);





