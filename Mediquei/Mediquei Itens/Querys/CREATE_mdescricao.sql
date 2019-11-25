CREATE TABLE med_mdescricao (
	desc_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    desc_id_medic INTEGER NOT NULL,
    desc_dsc VARCHAR(100),
    desc_quantidade VARCHAR(25),
    desc_receita CHAR(1)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

ALTER TABLE med_mdescricao
    ADD CONSTRAINT med_mdescricao_med_medicamentos_fk FOREIGN KEY ( desc_id_medic )
        REFERENCES med_medicamentos ( medic_id );
        
ALTER TABLE med_mdescricao CHANGE COLUMN desc_quantidade desc_quantidade VARCHAR(25);
        
DROP TABLE med_mdescricao;
        