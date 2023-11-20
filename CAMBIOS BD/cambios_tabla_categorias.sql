--USE proaula_6;


--ALTER TABLE productos_global DROP CONSTRAINT fk_productos_global_categorias;
--EXEC sp_help productos_global;
--DROP TABLE categorias;


/*CREATE TABLE categorias(
	id INT IDENTITY PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL UNIQUE,
	detalles VARCHAR(200)
);*/

--ALTER TABLE productos_global ADD CONSTRAINT fk_productos_global_categorias FOREIGN KEY(categoria) REFERENCES categorias(id);
--EXEC sp_help categorias;