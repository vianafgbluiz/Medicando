-- Insere MEDICAMENTOS
INSERT INTO med_mdescricao(desc_id_medic, desc_dsc, desc_quantidade, desc_receita)
	VALUES
--  A CURITYBINA
(1,'Ajuda na descamação da pele, removendo verrugas comuns e calos.', '0,1g/mL x 5mL', 'N'),
(1,'Ajuda na descamação da pele, removendo verrugas comuns e calos.', '12 pastilhas x 13g', 'N'),
-- ABLOK
(2,'Controle da hipertensão arterial (pressão alta)', '30 comprimidos x 100mg', 'S'),
(2,'Controle da hipertensão arterial (pressão alta)', '30 comprimidos x 25mg', 'S'),
(2,'Controle da hipertensão arterial (pressão alta)', '30 comprimidos x 50mg', 'S'),
-- ACEBROFILINA
(3,'Tratamento da obstrução dos brônquios', '5mg x Xarope 120 mL', 'N'),
(3,'Tratamento da obstrução dos brônquios', '10mg x Xarope 120 mL', 'N'),
(3,'Tratamento da obstrução dos brônquios', '25mg x Xarope 120 mL', 'N'),
(3,'Tratamento da obstrução dos brônquios', '50mg x Xarope 120 mL', 'N'),
-- AGUA PARA INJETAVEIS
(4,'Tratamento de candidíase invasiva grave', '100 amp x 5mL', 'N'),
(4,'Tratamento de candidíase invasiva grave', '100 amp x 10mL', 'N'),
(4,'Tratamento de candidíase invasiva grave', '100 amp x 20mL', 'N'),
-- AMOXICILINA
(5,'Tratamento de infecções bacterianas', '15 comprimidos', 'N'),
(5,'Tratamento de infecções bacterianas', '21 comprimidos', 'N'),
(5,'Tratamento de infecções bacterianas', '30 comprimidos', 'N'),
-- BACINA
(6,'Tratamento de infecções da pele e/ou de mucosas', '10g pomada', 'N'),
(6,'Tratamento de infecções da pele e/ou de mucosas', '15g pomada', 'N'),
-- BART H 
(7,'Tratamento da hipertensão arterial (pressão alta)', '30 comprimidos x 150mg', 'S'),
(7,'Tratamento da hipertensão arterial (pressão alta)', '30 comprimidos x 300mg', 'S'),
-- BENEGRIP
(8,'Tratamento da gripe', '20 comprimidos', 'N'),
-- BENZETACIL
(9,'Tratamento de infecções oriundas da sensibilidade à penicilina G', '50fa x 4mL injeção', 'S'),
(9,'Tratamento de infecções oriundas da sensibilidade à penicilina G', '10fa x 4mL injeção', 'S'),
-- BOTOX
(10,'Melhora da espasticidade (rigidez muscular)', '100ui frasco-ampola', 'S'),
(10,'Melhora da espasticidade (rigidez muscular)', '200ui frasco-ampola', 'S'),
(10,'Melhora da espasticidade (rigidez muscular)', '50ui frasco-ampola', 'S');

SELECT * FROM med_mdescricao GROUP BY desc_id_medic;

