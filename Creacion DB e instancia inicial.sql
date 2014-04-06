CREATE TABLE Articulos(
	NombreArticulo	VARCHAR(30) NOT NULL PRIMARY KEY,
	PrecioArticulo INT NOT NULL
);

CREATE TABLE VentasArticulos(
	NombreVendedor	VARCHAR(30) NOT NULL,
	NombreComprador VARCHAR(30) NOT NULL,
	ApellidoComprador VARCHAR(20) NOT NULL,
	DocumentoComprador VARCHAR(10) NOT NULL,
	AnioVenta	INT	NOT NULL,
	MesVenta	INT	NOT NULL,
	DiaVenta	INT	NOT NULL,
	IdentificadorVenta	INT	NOT NULL PRIMARY KEY,
	UNIQUE (NombreVendedor, DocumentoComprador, AnioVenta, MesVenta, DiaVenta)
);

CREATE TABLE ArticuloDeVenta(
	VA_IdentificadorVenta	INT	NOT NULL,
	A_NombreArticulo	VARCHAR(30) NOT NULL,
	Cantidad	INT NOT NULL,
	PRIMARY KEY(VA_IdentificadorVenta, A_NombreArticulo),
	FOREIGN KEY (VA_IdentificadorVenta) REFERENCES VentasArticulos(IdentificadorVenta),
	FOREIGN KEY (A_NombreArticulo) REFERENCES Articulos(NombreArticulo)
);

INSERT INTO Articulos VALUES ('auricular sony',200);
INSERT INTO Articulos VALUES ('play station 3',6000);
INSERT INTO Articulos VALUES ('videocamara jvc',7500);
INSERT INTO Articulos VALUES ('minicomponente philips', 5500);
INSERT INTO Articulos VALUES ('camara digital olympus',3600);
INSERT INTO Articulos VALUES ('taladro percutor',1440);
INSERT INTO Articulos VALUES ('set toalla y toallon',300);
INSERT INTO Articulos VALUES ('tv led 50 pulgadas',10000);
INSERT INTO Articulos VALUES ('reproductor blueray',2100);
INSERT INTO Articulos VALUES ('celular nokia lumia 720',4749);
INSERT INTO Articulos VALUES ('notebook lenovo',9000);
INSERT INTO Articulos VALUES ('impresora multifuncion',1660);
INSERT INTO Articulos VALUES ('gps',2600);
INSERT INTO Articulos VALUES ('autoestereo',1100);
INSERT INTO Articulos VALUES ('amoladora',720);