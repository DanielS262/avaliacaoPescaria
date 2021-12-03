DROP IF EXISTS DATABASE pescaria;

CREATE DATABASE pescaria;

USE pescaria;

CREATE TABLE pescador (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	cidade VARCHAR(20) NOT NULL,
	quantidade INTEGER NOT NULL,
	status BOOLEAN NOT NULL,
	excedente INTEGER NOT NULL,
	multa DECIMAL(10,2) NOT NULL
);


INSERT INTO pescador (cidade, quantidade, status, excedente, multa) values
("Pedreira", 12, true, 2, 2000),
("Santos", 12, false, 0, 0),
("Pedreira", 14, true, 4, 4000);