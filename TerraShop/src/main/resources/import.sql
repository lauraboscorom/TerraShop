insert into ROL (ID_ROL, NOMBRE_ROL) values (1, 'ROL_USUARIO');
insert into ROL (ID_ROL, NOMBRE_ROL) values (2,'ROL_ADMIN');

INSERT INTO usuario (APELLIDOS, BANCO, CODIGO_SEGURIDAD, PASSWORD, DIRECCION_ENVIO, DIRECCION_FACTURACION, EMAIL, NOMBRE, NUMERO_TARJETA, TITULAR, USUARIO) VALUES ('Bosco Romero', 'Bankia', 564384165, '$2a$10$tGkjd7XEPHsmGlUagRvwaula8YQegTBMCDyilTozu/UhBhLlRr9ru', 'Avenida Eclipse', 'Calle Codigo', 'laurabosrom@gmail.com', 'Laura', 6843, 'Laura Bosco Romero', 'usuario');
INSERT INTO usuario (APELLIDOS, BANCO, CODIGO_SEGURIDAD, PASSWORD, DIRECCION_ENVIO, DIRECCION_FACTURACION, EMAIL, NOMBRE, NUMERO_TARJETA, TITULAR, USUARIO) VALUES ('Puente', 'ING Direct', 846561325, '$2a$10$O1TSZ7OprOHddZE/9c3RQu30ipstHbEu85LjQvOgk3v6xSPcsyEye', 'Calle Java', 'Avenida Spring', 'marcospuente@gmail.com', 'Marcos', 6451, 'Marcos Puente', 'admin');

INSERT INTO usuario_rol (ID_USUARIO, ID_ROL) VALUES (1, 1);
INSERT INTO usuario_rol (ID_USUARIO, ID_ROL) VALUES (2, 2);

INSERT INTO categoria (NOMBRE) VALUES ('Camisetas');
INSERT INTO categoria (NOMBRE) VALUES ('Pantalones');
INSERT INTO categoria (NOMBRE) VALUES ('Zapatillas');

INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Camiseta corta de rayas', 6.0, 5, 1);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Chancletas de punta abierta con diseño de lazo', 6.0, 15, 3);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Camiseta de manga corta con estampado de corazon', 8.0, 10, 1);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Pantalones de doblez con cinturón de cintura con volante', 13.0, 10, 2);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Camiseta volante rígido liso burdeos', 11.0, 15, 1);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Pantalones anchos unicolor de cintura con cordon', 12.0, 20, 2);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Tacones gruesos de cuadros con tira tobillera', 16.0, 9, 3);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Pantalones anchos con estampado tribal', 14.0, 13, 2);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Zapatillas con diseño de lazo con estampado floral', 4.0, 21, 3);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Pantalones deportivos de Tie Dye de cintura elastica', 14.0, 2, 2);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Tacones gruesos gradiador con diseño claveteado', 12.0, 6, 3);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Zapatos con tira tobillera con diseño de perla ', 18.0, 8, 3);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Camiseta con cuello en V', 12.0, 23, 1);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Cuñas con diseño de diamante', 40.0, 11, 3);
INSERT INTO producto (NOMBRE, PRECIO, STOCK, ID_CATEGORIA) VALUES ('Camiseta de puño fruncido de color combinado', 9.0, 3, 1);

INSERT INTO pregunta (ID_USUARIO, ID_PRODUCTO, TEXTO) VALUES (2, 1, 'Es suave o rugosa?');
INSERT INTO pregunta (ID_USUARIO, ID_PRODUCTO, TEXTO) VALUES (1, 1, '¿Qué colores tenéis disponibles?');
INSERT INTO pregunta (ID_USUARIO, ID_PRODUCTO, TEXTO) VALUES (2, 1, '¿Es de algodón?');