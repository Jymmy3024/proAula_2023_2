--USE proaula_6;
EXEC sp_help productos_global;

/*QUITAR IDENTITY DE CODIGO
ALTER TABLE productos DROP CONSTRAINT fk_productos_productos_global;
ALTER TABLE productos_global DROP CONSTRAINT PK__producto__40F9A207DA250D82;
TRUNCATE TABLE productos_global;
ALTER TABLE productos_global DROP COLUMN codigo;
ALTER TABLE productos_global ADD codigo INT NOT NULL;
ALTER TABLE productos_global ADD CONSTRAINT pk_productos_global PRIMARY KEY(codigo);
DELETE  FROM productos;
ALTER TABLE productos ADD CONSTRAINT fk_productos_productos_global FOREIGN KEY(producto_global) REFERENCES productos_global(codigo);
*/

--COLOCAR NOMBRE COMO UNICO
--ALTER TABLE productos_global ADD CONSTRAINT nombre_unico UNIQUE(nombre);


--EXEC sp_help productos;


