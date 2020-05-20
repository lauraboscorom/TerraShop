insert into ROL (ID_ROL, NOMBRE_ROL) values (1, 'ROL_USUARIO');
insert into ROL (ID_ROL, NOMBRE_ROL) values (2,'ROL_ADMIN');

INSERT INTO usuario (APELLIDOS, BANCO, CODIGO_SEGURIDAD, PASSWORD, DIRECCION_ENVIO, DIRECCION_FACTURACION, EMAIL, NOMBRE, NUMERO_TARJETA, TITULAR, USUARIO) VALUES ('Bosco Romero', 'Bankia', 564384165, '$2a$10$tGkjd7XEPHsmGlUagRvwaula8YQegTBMCDyilTozu/UhBhLlRr9ru', 'Avenida Eclipse', 'Calle Codigo', 'laurabosrom@gmail.com', 'Laura', 6843, 'Laura Bosco Romero', 'usuario');
INSERT INTO usuario (APELLIDOS, BANCO, CODIGO_SEGURIDAD, PASSWORD, DIRECCION_ENVIO, DIRECCION_FACTURACION, EMAIL, NOMBRE, NUMERO_TARJETA, TITULAR, USUARIO) VALUES ('Puente', 'ING Direct', 846561325, '$2a$10$O1TSZ7OprOHddZE/9c3RQu30ipstHbEu85LjQvOgk3v6xSPcsyEye', 'Calle Java', 'Avenida Spring', 'marcospuente@gmail.com', 'Marcos', 6451, 'Marcos Puente', 'admin');

INSERT INTO usuario_rol (ID_USUARIO, ID_ROL) VALUES (1, 1);
INSERT INTO usuario_rol (ID_USUARIO, ID_ROL) VALUES (2, 2);

INSERT INTO categoria (NOMBRE) VALUES ('Camisetas');
INSERT INTO categoria (NOMBRE) VALUES ('Pantalones');
INSERT INTO categoria (NOMBRE) VALUES ('Zapatillas');

INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Top', 15.0, 5, 1);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Shorts', 30.0, 2, 2);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Chanclas', 40.0, 10, 3);

INSERT INTO pregunta (ID_USUARIO, ID_PRODUCTO, TEXTO) VALUES (2, 1, 'Es suave o rugosa?');
INSERT INTO pregunta (ID_USUARIO, ID_PRODUCTO, TEXTO) VALUES (1, 1, '¿Qué colores tenéis disponibles?');
INSERT INTO pregunta (ID_USUARIO, ID_PRODUCTO, TEXTO) VALUES (2, 1, '¿Es de algodón?');