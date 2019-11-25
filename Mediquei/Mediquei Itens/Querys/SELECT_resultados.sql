SELECT * FROM ( 
				SELECT m.medic_id, m.medic_nome, 
				       d.desc_id, d.desc_dsc, d.desc_quantidade, d.desc_receita,
					   p.p_preco, 
					   f.farm_id, f.farm_nome
				FROM med_medicamentos m , med_precos p, med_farmacias f, med_mdescricao d
			   WHERE m.medic_id = d.desc_id_medic
				 AND p.p_medicamento = d.desc_id
				 AND p.p_farmacia = f.farm_id
				 AND m.medic_nome LIKE 'AMOXICILINA%'
			ORDER BY d.desc_id, p.p_preco ASC) sq
GROUP BY sq.desc_id;

-- QUERY QUE FUNCIONOU
SELECT * FROM ( 
				SELECT m.medic_id, m.medic_nome, 
				       d.desc_id, d.desc_dsc, d.desc_quantidade, d.desc_receita,
					   p.p_preco, 
					   f.farm_id, f.farm_nome
				FROM med_medicamentos m , med_precos p, med_farmacias f, med_mdescricao d
			   WHERE m.medic_id = d.desc_id_medic
				 AND p.p_medicamento = d.desc_id
				 AND p.p_farmacia = f.farm_id
				 AND m.medic_nome LIKE 'AMOXICILINA%'
			GROUP BY p.p_preco ASC) sq
GROUP BY sq.desc_id ORDER BY sq.p_preco;

SELECT m.medic_id, m.medic_nome, 
				       d.desc_id, d.desc_dsc, d.desc_quantidade, d.desc_receita,
					   MIN(p.p_preco) p_preco, 
					   f.farm_id, f.farm_nome
				FROM med_medicamentos m , med_precos p, med_farmacias f, med_mdescricao d
			   WHERE m.medic_id = d.desc_id_medic
				 AND p.p_medicamento = d.desc_id
				 AND p.p_farmacia = f.farm_id
				 AND m.medic_nome LIKE 'AMOXICILINA%'
			GROUP BY d.desc_id ORDER BY p_preco;

SELECT * FROM med_medicamentos m;
SELECT * FROM med_precos p;
SELECT * FROM med_farmacias f;
SELECT * FROM med_mdescricao d;


CREATE TABLE tst_buscas (
	id INTEGER,
	descricao VARCHAR (10),
    padrao INTEGER,
    favorito INTEGER
);

DROP TABLE tst_buscas;

INSERT INTO tst_buscas VALUES (1, 'Busca 1', 0, 0) ;
INSERT INTO tst_buscas VALUES (2, 'Busca 2', 0, 0) ;
INSERT INTO tst_buscas VALUES (3, 'Busca 3', 0, 0) ;
INSERT INTO tst_buscas VALUES (4, 'Busca 4', 0, 0) ;
INSERT INTO tst_buscas VALUES (5, 'Busca 5', 0, 0) ;
INSERT INTO tst_buscas VALUES (6, 'Busca 6', 1, 0) ;
INSERT INTO tst_buscas VALUES (7, 'Busca 7', 1, 0) ;
INSERT INTO tst_buscas VALUES (8, 'Busca 8', 1, 0) ;
INSERT INTO tst_buscas VALUES (9, 'Busca 9', 1, 1) ;

SELECT MAX(id) FROM tst_buscas;

SELECT * FROM tst_buscas;

SELECT * FROM tst_buscas 
	WHERE padrao = 0 
       OR favorito = 1 
ORDER BY favorito DESC, padrao

