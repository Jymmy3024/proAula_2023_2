
USE proaula_6;

ALTER TABLE productos DROP CONSTRAINT DF__productos__dispo__10566F31;
ALTER TABLE productos ALTER COLUMN disponibilidad BIT NOT NULL;
ALTER TABLE productos ADD estado VARCHAR(15) NULL DEFAULT 'Activo';
