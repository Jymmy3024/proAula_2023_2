--EXEC sp_help productos;
USE proaula_6;
ALTER TABLE productos ALTER COLUMN precio_unitario DECIMAL(14,4) NOT NULL;
ALTER TABLE productos ALTER COLUMN pum DECIMAL(14,4);
