drop database if exists tiendaOnline;
create database tiendaOnline;
use tiendaOnline;

drop table if exists Cliente;
create table Cliente (
	idCliente bigint not null auto_increment,
	nombre varchar(225) not null,
	apellido varchar(225) not null,
	fnacimiento varchar(225) not null,
	direccion varchar(225) not null,
	email varchar(225) not null unique,
	password varchar(225) not null, 
	tipoCliente int not null Default 1,
	PRIMARY KEY(idCliente)
);

drop table if exists Banco;
create table Banco (

	idBanco bigint not null auto_increment,
	nombre varchar(225) not null,
	numTarjeta bigint not null,
	titular varchar(225) not null,
	codSeguridad int(3) not null,
	dirFactura varchar(225) not null,
	idCliente bigint null,
	PRIMARY KEY(idBanco),
	FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) ON DELETE NO ACTION
);

drop table if exists Productos;
create table Productos ( 
	idProducto bigint not null auto_increment,
	codProducto bigint not null unique, 
	titulo varchar(255) not null, 
	descripcion varchar(255) not null,
	precio float not null, 
	descuento float not null, 
	primary key (idProducto)
);

INSERT INTO Cliente (nombre,apellido,fnacimiento,direccion,email,password,tipoCliente) VALUES('admin','admin','08-07-1999','C/Jose Antonio 2', 'root@root.com','root',2);
CREATE TABLE CLIENTE_PRODUCTO
(
   idCliente BIGINT NOT NULL,
   idProducto BIGINT NOT NULL,
   PRIMARY KEY
   (
      idCliente,
      idProducto
   ),
   CONSTRAINT FK_CLIENTE_PRODUCTO_1 FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente) ON DELETE CASCADE,
   CONSTRAINT FK_CLIENTE_PRODUCTO_2 FOREIGN KEY (idProducto) REFERENCES Productos (idProducto)
);

