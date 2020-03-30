insert into ROL (ID_ROL, NOMBRE_ROL ) values (1, 'ROL_USUARIO');
insert into ROL (ID_ROL, NOMBRE_ROL ) values (2,'ROL_ADMIN');

INSERT INTO usuario (APELLIDOS, BANCO, CODIGO_SEGURIDAD, PASSWORD, DIRECCION_ENVIO, DIRECCION_FACTURACION, EMAIL, NOMBRE, NUMERO_TARJETA, TITULAR, USUARIO) VALUES ('Normal Ycorriente', 'Bankia', 564384165, '$2a$10$tGkjd7XEPHsmGlUagRvwaula8YQegTBMCDyilTozu/UhBhLlRr9ru', 'Avenida Eclipse', 'Calle Codigo', 'usuario@gmail.com', 'Usuario', 6843, 'Usuario Normal Ycorriente', 'usuario');
INSERT INTO usuario (APELLIDOS, BANCO, CODIGO_SEGURIDAD, PASSWORD, DIRECCION_ENVIO, DIRECCION_FACTURACION, EMAIL, NOMBRE, NUMERO_TARJETA, TITULAR, USUARIO) VALUES ('Bosco Romero', 'ING Direct', 846561325, '$2a$10$O1TSZ7OprOHddZE/9c3RQu30ipstHbEu85LjQvOgk3v6xSPcsyEye', 'Calle Java', 'Avenida Spring', 'laurabosrom@gmail.com', 'Laura', 6451, 'Laura Bosco Romero', 'lonlitay');

INSERT INTO usuario_rol (ID_USUARIO, ID_ROL) VALUES (1, 2);
INSERT INTO usuario_rol (ID_USUARIO, ID_ROL) VALUES (2, 1);

INSERT INTO producto (NOMBRE, PRECIO, STOCK) VALUES ('Camiseta', 15.0, 5);
INSERT INTO producto (NOMBRE, PRECIO, STOCK) VALUES ('Pantalon', 30.0, 2);
INSERT INTO producto (NOMBRE, PRECIO, STOCK) VALUES ('Zapatillas', 40.0, 10);