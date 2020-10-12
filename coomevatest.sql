CREATE TABLE test_clientes(
    id_cliente INT(18) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    identificacion INT(18) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(300) NOT NULL,
    telefono VARCHAR(50),
    email VARCHAR(100)
);
CREATE TABLE test_productos(
    id_producto INT(18) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    codigo VARCHAR(30) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    valor_unidad FLOAT(18, 3) NOT NULL,
    stock INT NOT NULL
);
CREATE TABLE test_facturas(
    id_factura INT(18) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_cliente INT(18) NOT NULL,
    fecha_venta datetime NOT NULL,
    valor_total FLOAT(18, 3) NOT NULL,
    FOREIGN KEY(id_cliente) REFERENCES test_clientes(id_cliente)
);
CREATE TABLE test_factura_detalles(
    id_factura_detalle INT(18) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_factura INT(18) NOT NULL,
    id_producto INT(18) NOT NULL,
    cantidad int NOT NULL,
    valor_unidad FLOAT(18, 3) NOT NULL,
    valor_total FLOAT(18, 3) NOT NULL,
    FOREIGN KEY(id_factura) REFERENCES test_facturas(id_factura),
    FOREIGN KEY(id_producto) REFERENCES test_productos(id_producto)
);
INSERT INTO test_clientes(identifiacion,nombres,apellidos,direccion,telefono,email)
VALUES(
        '11223344',
        'CLIENTE',
        'BAJO',
        'CALLE 1 # 2-1',
        '3210099',
        'CL01@YAHOO.ES'
    );
INSERT INTO test_clientes(identifiacion,nombres,apellidos,direccion,telefono,email)
VALUES(
        '789456',
        'PETER',
        'ROJO',
        'CALLE 11 # 21-11',
        NULL,
        NULL
    );
INSERT INTO test_clientes(identifiacion,nombres,apellidos,direccion,telefono,email)
VALUES(
        '66995522',
        'JAIR',
        'RUIZ',
        'CALLE 63 # 55-1',
        '3669955',
        'JAIR@YAHOO.ES'
    );
INSERT INTO test_clientes(identifiacion,nombres,apellidos,direccion,telefono,email)
VALUES(
        '2255448',
        'VICTOR',
        'BARCO',
        'CALLE 19 # 21',
        '7410022',
        'VICTOR@YAHOO.ES'
    );
INSERT INTO test_clientes(identifiacion,nombres,apellidos,direccion,telefono,email)
VALUES(
        '123456789',
        'MARIA',
        'CALLE',
        'CALLE 61 # 77D-96',
        '6660005',
        'CALLE@YAHOO.ES'
    );
INSERT INTO test_clientes(identifiacion,nombres,apellidos,direccion,telefono,email)
VALUES(
        '41000333',
        'LUIS',
        'CORREO',
        'CALLE 45 # 32-11',
        '4444444',
        'LCORREO@YAHOO.ES'
    );
INSERT INTO test_productos(codigo,nombre,valor_unidad,stock)
VALUES('0001', 'TOSTACOS', 950, 15);
INSERT INTO test_productos(codigo,nombre,valor_unidad,stock)
VALUES('0002', 'LECHE', 2350, 40);
INSERT INTO test_productos(codigo,nombre,valor_unidad,stock)
VALUES('0003', 'PLATANO', 1500, 20);
INSERT INTO test_productos(codigo,nombre,valor_unidad,stock)
VALUES('0004', 'PAÑALES', 36500, 4);
INSERT INTO test_productos(codigo,nombre,valor_unidad,stock)
VALUES('0005', 'BULTO DE MANDARINAS', 156800, 9);
INSERT INTO test_productos(codigo,nombre,valor_unidad,stock)
VALUES('0006', 'ARROZ', 6450, 60);
INSERT INTO test_productos(codigo,nombre,valor_unidad,stock)
VALUES('0007', 'PESCADO', 99000, 45);
INSERT INTO test_productos(codigo,nombre,valor_unidad,stock)
VALUES('0008', 'HUEVOS', 11500, 20);
INSERT INTO test_productos(codigo,nombre,valor_unidad,stock)
VALUES('0009', 'PAPAS JAJAJAJA', 6600, 1);
INSERT INTO test_productos(codigo,nombre,valor_unidad,stock)
VALUES('0010', 'DETERGENTE', 5001000, 3);