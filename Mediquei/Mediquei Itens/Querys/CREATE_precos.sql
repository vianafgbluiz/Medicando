CREATE TABLE med_precos (
	p_medicamento INTEGER,
    p_farmacia INTEGER,
    p_preco NUMERIC(8,2)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

ALTER TABLE med_precos
    ADD CONSTRAINT med_precos_med_mdescricao_fk FOREIGN KEY ( p_medicamento )
        REFERENCES med_mdescricao ( desc_id );
        
ALTER TABLE med_precos
    ADD CONSTRAINT med_precos_med_farmacias_fk FOREIGN KEY ( p_farmacia )
        REFERENCES med_farmacias ( farm_id );
        
        DROP TABLE med_precos;
        
SELECT * FROM med_precos;
