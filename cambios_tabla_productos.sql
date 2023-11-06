
--USE proaula_6;

/*ALTER TABLE productos DROP CONSTRAINT DF__productos__dispo__10566F31;
ALTER TABLE productos ALTER COLUMN disponibilidad BIT NOT NULL;
ALTER TABLE productos ADD estado VARCHAR(15) NULL DEFAULT 'Activo';*/


--USE proaula_6;

--ALTER TABLE locales DROP CONSTRAINT fk_locales_tiendas;
--ALTER TABLE productos DROP CONSTRAINT fk_productos_tiendas;
--ALTER TABLE tiendas DROP CONSTRAINT PK__tiendas__40F9A207701B2D7D;
--ALTER TABLE tiendas DROP COLUMN codigo;

--ALTER TABLE tiendas ADD CONSTRAINT pk_tiendas PRIMARY KEY(nit);
--ALTER TABLE productos ADD CONSTRAINT fk_productos_tienda FOREIGN KEY(tienda) REFERENCES tiendas(nit);
--ALTER TABLE locales ADD CONSTRAINT fk_locales_tienda FOREIGN KEY(tienda) REFERENCES tiendas(nit);
