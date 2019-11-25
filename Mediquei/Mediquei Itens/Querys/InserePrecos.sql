/*SELECT m.medic_nome, d.* 
	 FROM med_mdescricao d, med_medicamentos m
	WHERE d.desc_id_medic = m.medic_id
      AND d.desc_id_medic IN (7,8,9,10); 

DELETE FROM med_precos WHERE(1);*/

INSERT INTO med_precos(p_medicamento, p_farmacia, p_preco) 
	VALUES 
-- A CURITYBINA
-- Farmacia 2
(1, 2, 85.00),
(2, 2, 95.50),
-- Farmacia 3
(1, 3, 90.00),
(2, 3, 92.50),
-- Farmacia 4
(2, 4, 87.50),
-- ABLOK
-- Farmacia 1
(3, 1, 31.20), 
(4, 1, 9.60),
(5, 1, 15.90),
-- Farmacia 2
(3, 2, 38.20), 
(5, 2, 15.60),
-- Farmacia 4
(4, 4, 8.20),
-- ACEBROFILINA
-- Farmacia 1
(6, 1, 5.34),
(7, 1, 10.34),
(8, 1, 20.34),
(9, 1, 40.34),
-- Farmacia 2
(6, 2, 7.50),
(7, 2, 12.50),
(8, 2, 19.50),
(9, 2, 39.50),
-- Farmacia 3
(6, 3, 6.00),
(7, 3, 12.00),
(8, 3, 22.00),
(9, 3, 42.50),
-- Farmacia 4
(6, 4, 8.00),
(7, 4, 16.50),
(8, 4, 25.10),
(9, 4, 52.18),
-- AMOXILINA
-- Farmacia 1
(13, 1, 10.00), 
(14, 1, 15.00),
(15, 1, 18.00),
-- Farmacia 2
(13, 2, 11.00), 
(14, 2, 15.50),
(15, 2, 18.50),
-- Farmacia 3
(13, 3, 7.49),
(14, 3, 18.00),
(15, 3, 20.00),
-- BACINA
-- Farmacia 1
(16, 1, 16.50),
(17, 1, 20.50),
-- Farmacia 2
(16, 2, 17.50),
(17, 2, 21.50),
-- Farmacia 3
(16, 3, 18.50),
(17, 3, 22.50),
-- Farmacia 4
(16, 4, 14.50),
(17, 4, 26.50),
-- BART H
-- Farmacia 1
(18, 1, 45.00),
(19, 1, 70.00),
-- Farmacia 2
(18, 2, 40.00),
(19, 2, 80.00),
-- Farmacia 3
(18, 3, 33.00),
(19, 3, 68.00),
-- Farmacia 4
(18, 4, 50.00),
(19, 4, 79.00),
-- BENEGRIP
-- Farmacia 1
(20, 1, 21.90),
-- Farmacia 2
(20, 2, 22.90),
-- Farmacia 3
(20, 3, 19.90),
-- Farmacia 4
(20, 4, 17.90),
-- BOTOX
-- Farmacia 2
(23, 2, 1200.00),
(24, 2, 1100.00),
(25, 2, 450.00),
-- Farmacia 3
(23, 3, 600.00),
(24, 3, 1100.00),
(25, 3, 400.00);



