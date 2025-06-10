-- PROYECTO

-- TABLAS 

CREATE SCHEMA Proyecto;
USE Proyecto;

CREATE TABLE Padrino (
	Dni INT PRIMARY KEY,
    Nombre VARCHAR(40),
    Apellido VARCHAR(40),
    Direccion VARCHAR(50),
    Celular INT,
    Telefono_Fijo INT,
    Email VARCHAR(254),  -- SEGUN STANDARD RFC 5231
    Facebook VARCHAR(50),
    Codigo_Postal INT,
    Fecha_Nacimiento DATE
);

	INSERT INTO Padrino VALUES
		(30876543, 'Mariana', 'Ferreyra', 'San Martín 321', '1165437890', '01142223333', 'mariana.ferreyra@example.com', 'mariana.f', '1407', '1988-06-15'),
		(30111222, 'Tomás', 'Herrera', 'Mitre 987', '1177118822', '01143211234', 'tomas.herrera@example.com', 'tomi.h', '1427', '1994-03-20'),
		(32233445, 'Valentina', 'Sánchez', 'Dorrego 1500', '1133445566', '01140998877', 'valen.sanchez@example.com', 'valens', '1414', '2001-12-03'),
		(29998887, 'Agustín', 'Moreno', 'Av. Rivadavia 6700', '1166778899', '01140774455', 'agustin.moreno@example.com', 'agus.m', '1406', '1990-01-01'),
		(31227888, 'Carla', 'Ruiz', 'Catamarca 456', '1122334455', '01143337711', 'carla.ruiz@example.com', 'carla.r', '1225', '1983-04-22'),
		(28765432, 'Joaquín', 'Luna', 'José Ingenieros 678', '1177889900', '01148889911', 'joaquin.luna@example.com', 'joaquin.l', '1602', '1996-08-10'),
		(29887766, 'Florencia', 'Ibarra', 'Alsina 1090', '1144556677', '01146667788', 'flor.ibarra@example.com', 'flor.i', '1420', '1987-10-29'),
		(31444555, 'Diego', 'Campos', 'Perón 876', '1177445599', '01149997766', 'diego.campos@example.com', 'diego.cam', '1221', '2003-02-17'),
		(30555111, 'Julieta', 'Paredes', 'Azcuénaga 321', '1199887766', '01145556644', 'julieta.paredes@example.com', 'julipar', '1280', '1999-07-08'),
		(31112233, 'Ramiro', 'Acosta', 'Vélez Sarsfield 4321', '1133778899', '01140001234', 'ramiro.acosta@example.com', 'rami.acosta', '1440', '1982-05-26'),
		(29444555, 'Ana', 'Silva', 'Rosales 222', '1155223344', '01146665555', 'ana.silva@example.com', 'ana.s', '1203', '1997-11-11'),
		(32233466, 'Luciano', 'Barrios', 'Oroño 1700', '1144332211', '01143335577', 'luciano.barrios@example.com', 'lucianob', '1500', '1989-09-13'),
		(30998877, 'Daniela', 'Cáceres', 'Yrigoyen 2400', '1155667788', '01142223344', 'daniela.caceres@example.com', 'dani.cac', '1342', '1993-06-06'),
		(30001122, 'Franco', 'Bermúdez', 'Av. La Plata 550', '1177112233', '01143336677', 'franco.bermudez@example.com', 'franco.b', '1260', '2002-01-14'),
		(29773366, 'Brenda', 'Quiroga', 'Echeverría 110', '1133445566', '01141112233', 'brenda.quiroga@example.com', 'bren.q', '1451', '1980-12-30');

CREATE TABLE Donante (
	Dni INT PRIMARY KEY,
    CONSTRAINT FKDonante FOREIGN KEY (Dni) REFERENCES Padrino(Dni) ON DELETE CASCADE ON UPDATE CASCADE,
    Ocupacion VARCHAR(30),
    Cuit BIGINT -- ES CLAVE CANDIDATA
);

-- Insertar datos en la tabla Donante
INSERT INTO Donante VALUES
		(30876543, 'Ingeniero', 20123456789),
		(30111222, 'Doctora', 27234567890),
		(32233466, 'Contador', 20345678901),
		(32233445, 'Abogada', 27456789012),
		(31227888, 'Arquitecto', 20567890123),
		(30555111, 'Profesora', 27678901234);

CREATE TABLE Contacto (
	Dni INT PRIMARY KEY,
    CONSTRAINT FKContacto FOREIGN KEY (Dni) REFERENCES Padrino(Dni),
	Fecha_Rechazo_Admision DATE,
    Fecha_Primer_Contacto DATE,
    Estado VARCHAR(60),
    CONSTRAINT Tipo_Estado CHECK ( Estado IN ( 'SIN LLAMAR', 'ERROR', 'EN GESTION', 'ADHERIDO', 'AMIGO', 'NO ACEPTA', 'BAJA', 'VOLUNTARIO' )),
    Fecha_Baja DATE,
    Fecha_Alta DATE
);
-- Insertar datos en la tabla Contacto
INSERT INTO Contacto VALUES
	(30876543, NULL, '2024-01-15', 'ERROR', NULL, '2024-01-20'),
	(30111222, NULL, '2024-02-10', 'SIN LLAMAR', NULL, '2024-02-15'),
	(32233445, '2024-03-01', '2024-02-25', 'VOLUNTARIO', NULL, NULL);

CREATE TABLE Programa (
	Id_Programa INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(30),
    Nombre VARCHAR(30)
);

-- Insertar datos en la tabla Programa
	INSERT INTO Programa VALUES
		(NULL, 'Ayuda alimentaria diaria', 'Comedor Solidario'),
		(NULL, 'Apoyo educativo integral', 'Becas Estudiantiles'),
		(NULL, 'Asistencia médica gratuita', 'Salud Para Todos'),
		(NULL, 'Capacitación laboral', 'Trabajo Digno'),
		(NULL, 'Refugio temporal', 'Casa Abierta');
        
CREATE TABLE Medio_De_Pago (
	Id_Pago INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Titular VARCHAR(50)
);

-- Insertar datos en la tabla Medio_De_Pago
	INSERT INTO Medio_De_Pago VALUES
		(NULL, 'Juan Pérez'),
		(NULL, 'María González'),
		(NULL, 'Carlos López'),
		(NULL, 'Ana Martínez'),
		(NULL, 'Pedro Rodríguez'),
		(NULL, 'Laura Fernández'),
		(NULL, 'Miguel Torres'),
		(NULL, 'Carmen Ruiz');

CREATE TABLE Aportan (
	Id_Programa INT,
    Dni INT,
	Frecuencia_Aporte ENUM ('MENSUAL', 'SEMESTRAL'),
    Monto INT,
    Id_Pago INT,
    CONSTRAINT FKDonanteAportan FOREIGN KEY (Dni) REFERENCES Donante (Dni) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FKPrograma FOREIGN KEY (Id_Programa) REFERENCES Programa(Id_Programa),
    CONSTRAINT FKPago FOREIGN KEY (Id_Pago) REFERENCES Medio_De_Pago(Id_Pago),
    CONSTRAINT PKAportan PRIMARY KEY (Id_Programa, Dni)
);

-- Inserta datos en la tabla Aportan
INSERT INTO Aportan VALUES
    -- Donante 30111222 (Tomás Herrera) - 3 aportes
    (1, 30111222, 'MENSUAL', 8000, 2),
    (3, 30111222, 'SEMESTRAL', 20000, 4),
    (4, 30111222, 'MENSUAL', 6000, 5),
    
    -- Donante 32233466 (Luciano Barrios) - 2 aportes
    (2, 32233466, 'MENSUAL', 4500, 1),
    (5, 32233466, 'SEMESTRAL', 12000, 3),
    
    -- Donante 32233445 (Valentina Sánchez) - 4 aportes
    (1, 32233445, 'MENSUAL', 7000, 6),
    (2, 32233445, 'MENSUAL', 3500, 7),
    (3, 32233445, 'SEMESTRAL', 18000, 8),
    (4, 32233445, 'MENSUAL', 5500, 1),
    
    -- Donante 31227888 (Carla Ruiz) - 1 aporte
    (5, 31227888, 'SEMESTRAL', 25000, 2),
    
    -- Donante 30555111 (Julieta Paredes) - 3 aportes
    (1, 30555111, 'MENSUAL', 4000, 3),
    (3, 30555111, 'MENSUAL', 6500, 4),
    (5, 30555111, 'SEMESTRAL', 15000, 5);    

CREATE TABLE DebitoTransferencia (
	Id_Pago INT PRIMARY KEY,
    Cbu VARCHAR(100),
    Nombre VARCHAR(20),
    Sucursal VARCHAR(20),
    Tipo_Cuenta VARCHAR(20),
    Nro_Cuenta INT,
    CONSTRAINT FKDebitoTranferencia FOREIGN KEY (Id_Pago) REFERENCES Medio_De_Pago(Id_Pago)
);

-- Insertar datos en la tabla DebitoTransferencia
INSERT INTO DebitoTransferencia VALUES
	(1, '1234567890123456789012', 'Juan Pérez', 'Centro', 'Caja Ahorro', 123456789),
	(2, '2345678901234567890123', 'María González', 'Norte', 'Cuenta Corriente', 234567890),
	(4, '4567890123456789012345', 'Ana Martínez', 'Sur', 'Caja Ahorro', 456789012),
	(5, '5678901234567890123456', 'Pedro Rodríguez', 'Oeste', 'Cuenta Corriente', 567890123),
	(7, '7890123456789012345678', 'Miguel Torres', 'Este', 'Caja Ahorro', 789012345);

CREATE TABLE Credito (
	Id_Pago INT PRIMARY KEY,
    Fecha_Vencimiento DATE,
    Nro_Tarjeta BIGINT,
    Nombre_Tarjeta VARCHAR(20),
    CONSTRAINT FKCredito FOREIGN KEY (Id_Pago) REFERENCES Medio_De_Pago(Id_Pago)
);

-- Insertar datos en la tabla Credito
INSERT INTO Credito VALUES
(3, '2026-12-31', 1234567890123456, 'VISA'),
(6, '2027-06-30', 2345678901234567, 'MASTERCARD'),
(8, '2025-11-30', 3456789012345678, 'AMERICAN EXPRESS');


CREATE TABLE Auditoria_Eliminacion_Donantes (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Dni_Donante INT,
    Fecha_Eliminacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Usuario VARCHAR(100)
);

DELIMITER //

CREATE TRIGGER Info_Donante 
AFTER DELETE ON Donante
FOR EACH ROW
BEGIN
	INSERT INTO Auditoria_Eliminacion_Donantes (
		Dni_Donante,
        Usuario
	)
    VALUES (
		OLD.Dni,
        CURRENT_USER()
	);
END;
//

-- a)
SELECT Id_Programa, COUNT(Id_Pago)
FROM Aportan
GROUP BY Id_Programa;

-- b) 
SELECT Dni, Nombre, Apellido, COUNT(Id_Pago) AS CantAportes
FROM Donante NATURAL JOIN Aportan NATURAL JOIN Padrino
GROUP BY Dni
HAVING CantAportes > 2;

-- c)
SELECT Dni, Nombre, Apellido, Id_Pago
FROM Aportan NATURAL JOIN Donante NATURAL JOIN Padrino;