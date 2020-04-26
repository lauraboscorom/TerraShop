drop database if exists terrashop;
create database terrashop;
use terrashop;

DROP TABLE IF EXISTS rol;
CREATE TABLE ROL
(
   ID_ROL NOT NULL AUTO_INCREMENT,
   NOMBRE_ROL VARCHAR (40) NOT NULL,
   PRIMARY KEY (ID_ROL)
);

DROP TABLE IF EXISTS usuario;
CREATE TABLE USUARIO (
  ID_USUARIO bigint(20) NOT NULL AUTO_INCREMENT,
  APELLIDOS varchar(255) NOT NULL,
  BANCO varchar(255) DEFAULT NULL,
  CODIGO_SEGURIDAD int(20) DEFAULT NULL,
  PASSWORD varchar(255) NOT NULL,
  DIRECCION_ENVIO varchar(255) DEFAULT NULL,
  DIRECCION_FACTURACION varchar(255) DEFAULT NULL,
  EMAIL varchar(255) DEFAULT NULL,
  NOMBRE varchar(255) NOT NULL,
  NUMERO_TARJETA int(20) DEFAULT NULL,
  TITULAR varchar(255) DEFAULT NULL,
  USUARIO varchar(255) NOT NULL UNIQUE,
  ROL varchar(255) DEFAULT 'CLIENTE',
  PRIMARY KEY (ID_USUARIO)
);

DROP TABLE IF EXISTS usuario_rol;
CREATE TABLE USUARIO_ROL (
   ID_USUARIO INT NOT NULL,
   ID_ROL NOT NULL,
      PRIMARY KEY
   (
      ID_USUARIO,
      ID_ROL
   ),
   PRIMARY KEY (ID_ROL)
      CONSTRAINT FK_USUARIO_ROL_1 FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (ID_USUARIO) ON DELETE CASCADE,
	CONSTRAINT FK_USUARIO_ROL_2 FOREIGN KEY (ID_ROL) REFERENCES ROL (ID_ROL)
);

DROP TABLE IF EXISTS imagen;
CREATE TABLE imagen (
  ID_IMAGEN BIGINT(20) NOT NULL,
  DATA VARBINARY(100) NOT NULL,
  ID_PRODUCTO bigint(20) NOT NULL,
  PRIMARY KEY (ID_IMAGEN),
  KEY FK_ID_PRODUCTO (ID_PRODUCTO),
  CONSTRAINT FK_ID_PRODUCTO FOREIGN KEY (ID_PRODUCTO) REFERENCES producto (ID_PRODUCTO)
);

DROP TABLE IF EXISTS producto;
CREATE TABLE producto (
  ID_PRODUCTO bigint(20) NOT NULL AUTO_INCREMENT,
  NOMBRE varchar(255) NOT NULL,
  PRECIO float NOT NULL,
  STOCK int(11) NOT NULL,
  PRIMARY KEY (ID_PRODUCTO)
);

DROP TABLE IF EXISTS venta;
CREATE TABLE venta (
  ID_VENTA bigint(20) NOT NULL AUTO_INCREMENT,
  FECHA_VENTA date NOT NULL,
  DESCUENTO float NOT NULL,
  ID_USUARIO bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID_VENTA),
  KEY FK_ID_USUARIO (ID_USUARIO),
  CONSTRAINT FK_ID_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES usuario (ID_CLIENTE)
);

DROP TABLE IF EXISTS lineadc;
CREATE TABLE lineadc (
  ID_LINEADC bigint(20) NOT NULL AUTO_INCREMENT,
  ID_PRODUCTO bigint(20) NOT NULL,
  ID_VENTA bigint(20) NOT NULL,
  PRECIO_PRODUCTO float NOT NULL,
  PRIMARY KEY (ID_LINEADC),
  KEY FK_ID_PRODUCTO (ID_PRODUCTO),
  KEY FK_ID_VENTA (ID_VENTA),
  CONSTRAINT FK_ID_PRODUCTO FOREIGN KEY (ID_PRODUCTO) REFERENCES producto (ID_PRODUCTO),
  CONSTRAINT FK_ID_VENTA FOREIGN KEY (ID_VENTA) REFERENCES ventas (ID_VENTA) ON DELETE CASCADE
);

DROP TABLE IF EXISTS pregunta;
CREATE TABLE pregunta (
  ID_PREGUNTA BIGINT(20) NOT NULL AUTO_INCREMENT,
  TEXTO VARCHAR(255) NOT NULL,
  ID_PRODUCTO BIGINT(20) NOT NULL,
  ID_USUARIO BIGINT(20) NOT NULL,
  PRIMARY KEY (ID_PREGUNTA),
  KEY FK_ID_PRODUCTO (ID_PRODUCTO),
  KEY FK_ID_USUARIO (ID_USUARIO),
  CONSTRAINT FK_ID_PRODUCTO FOREIGN KEY (ID_PRODUCTO) REFERENCES producto (ID_PRODUCTO),
  CONSTRAINT FK_ID_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES usuario (ID_USUARIO)
);

DROP TABLE IF EXISTS respuesta;
CREATE TABLE respuesta (
  ID_RESPUESTA BIGINT(20) NOT NULL AUTO_INCREMENT,
  ID_PREGUNTA BIGINT(20) NULL DEFAULT NULL,
  TEXTO VARCHAR(255) NULL DEFAULT NULL,
  ID_USUARIO BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (ID_RESPUESTA),
  KEY FK_ID_PREGUNTA (ID_PREGUNTA),
  KEY FK_ID_USUARIO (ID_USUARIO),
  CONSTRAINT FK_ID_PREGUNTA FOREIGN KEY (ID_PREGUNTA) REFERENCES pregunta (ID_PREGUNTA),
  CONSTRAINT FK_ID_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES usuario (ID_USUARIO)
);

