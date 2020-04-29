insert into ROL (ID_ROL, NOMBRE_ROL ) values (1, 'ROL_USUARIO');
insert into ROL (ID_ROL, NOMBRE_ROL ) values (2,'ROL_ADMIN');

INSERT INTO usuario (APELLIDOS, BANCO, CODIGO_SEGURIDAD, PASSWORD, DIRECCION_ENVIO, DIRECCION_FACTURACION, EMAIL, NOMBRE, NUMERO_TARJETA, TITULAR, USUARIO) VALUES ('Normal Ycorriente', 'Bankia', 564384165, '$2a$10$tGkjd7XEPHsmGlUagRvwaula8YQegTBMCDyilTozu/UhBhLlRr9ru', 'Avenida Eclipse', 'Calle Codigo', 'usuario@gmail.com', 'Usuario', 6843, 'Usuario Normal Ycorriente', 'usuario');
INSERT INTO usuario (APELLIDOS, BANCO, CODIGO_SEGURIDAD, PASSWORD, DIRECCION_ENVIO, DIRECCION_FACTURACION, EMAIL, NOMBRE, NUMERO_TARJETA, TITULAR, USUARIO) VALUES ('Bosco Romero', 'ING Direct', 846561325, '$2a$10$O1TSZ7OprOHddZE/9c3RQu30ipstHbEu85LjQvOgk3v6xSPcsyEye', 'Calle Java', 'Avenida Spring', 'laurabosrom@gmail.com', 'Laura', 6451, 'Laura Bosco Romero', 'lonlitay');

INSERT INTO usuario_rol (ID_USUARIO, ID_ROL) VALUES (1, 2);
INSERT INTO usuario_rol (ID_USUARIO, ID_ROL) VALUES (2, 1);

INSERT INTO categoria (NOMBRE) VALUES ('Camisetas');
INSERT INTO categoria (NOMBRE) VALUES ('Pantalones');
INSERT INTO categoria (NOMBRE) VALUES ('Zapatillas');

INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Top', 15.0, 5, 1);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Shorts', 30.0, 2, 2);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Chanclas', 40.0, 10, 3);

INSERT INTO pregunta (ID_USUARIO, ID_PRODUCTO, TEXTO) VALUES (2, 1, 'Es suave o rugosa?');
INSERT INTO pregunta (ID_USUARIO, ID_PRODUCTO, TEXTO) VALUES (1, 1, '�Qu� colores ten�is disponibles?');
INSERT INTO pregunta (ID_USUARIO, ID_PRODUCTO, TEXTO) VALUES (2, 1, '�Es de algod�n?');

INSERT INTO imagen (DATA, ID_PRODUCTO) VALUES (LOAD_FILE('/src/main/resources/top1.JPG'), 1);
INSERT INTO imagen (DATA, ID_PRODUCTO) VALUES (LOAD_FILE('../webapp/WEB-INF/images/top2.JPG'), 1);
INSERT INTO imagen (DATA, ID_PRODUCTO) VALUES (LOAD_FILE('../webapp/WEB-INF/images/top3.JPG'), 1);
INSERT INTO imagen (DATA, ID_PRODUCTO) VALUES (LOAD_FILE('../webapp/WEB-INF/images/top4.JPG'), 1);