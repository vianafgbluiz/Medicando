/*
	Insere MEDICAMENTOS
*/ 
INSERT INTO med_medicamentos (medic_nome) VALUES 
('A CURITYBINA'), ('ABLOK'), ('ACEBROFILINA'), ('AGUA PARA INJETAVEIS'), ('AMOXICILINA'),
('BACINA'), ('BART H'), ('BENEGRIP'), ('BENZETACIL'), ('BOTOX'),
('CATIVA'), ('CEFALEXINA'), ('CETOCONAZOL'), ('CETOPROFENO'), ('CLORDILON'),
('DESOL'), ('DETAMAX'), ('DIABEMED'), ('DIAZEPAM'), ('DIPIRONA SODICA'),
('EQUITAM'), ('ERANZ'), ('ERBITUX'), ('ERITROMAX'), ('ESC');

----------------------------------------------------------------------------------------------------------------
/*
	Insere DESCRICOES
*/ 
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

----------------------------------------------------------------------------------------------------------------
/*
	Insere FARMACIAS (COM OS RESPECTIVOS ENDEREÇOS)
*/

INSERT INTO med_farmacias(farm_nome, farm_cnpj, farm_tel, farm_logos, farm_razao_social) 
	 VALUES
('Droga Assis', '12.562.790/0001-88', '34 3235-9354', NULL, 'Drogaria Assis Comercio Farmaceutico LTDA'),
('Droga City', '08.647.378/0001-93', '34 3231-3420', NULL ,'Frankel Comercio de Medicamentos LTDA'),
('Farmatem Drogaria e Farmácia', '08.647.378/0001-93', '34 3217-2890', NULL, 'J B da Silva Vieira Drogaria LTDA - ME'),
('Drogaria Morumbi', '11.578.720/0001-55', '34 3226-3874', NULL, 'Tarcisio da Silveira Cruz LTDA - ME');

INSERT INTO med_enderecos (end_tipo, end_idfk, end_cep, end_uf, 
			end_cidade, end_bairro, end_logradouro, end_complemento, end_numero)
	VALUES
('F', 1, '38411-410', 'MG', 'Uberlândia', 'Shopping Park', 'Avenida Boulanger Fonseca', 'Farmácia', '224'),
('F', 2, '38408-168', 'MG', 'Uberlândia', 'Santa Mônica', 'Avenida Belarmino Cotta Pacheco', 'Farmácia', '687'),
('F', 3, '38412-144', 'MG', 'Uberlândia', 'Cidade Jardim', 'Rua dos Eucalíptos', 'Farmácia', '417'),
('F', 4, '38407-477', 'MG', 'Uberlândia', 'Morumbi', 'Avenida Antônio Jorge Isaac', 'Farmácia', '1332');

----------------------------------------------------------------------------------------------------------------
/*
	Insere ESTADOS PEDIDOS
*/

INSERT INTO med_situacao VALUES 
(1, 'Pedido Efetuado'),
(2, 'Pedido Autorizado'),
(3, 'Pedido Em transporte'),
(4, 'Pedido Entregue'),
(5, 'Pedido Não Autorizado'),
(6, 'Pedido cancelado'),
(7, 'Pedido Não Entregue'),
(8, 'Pedido Aguardando Retirada');

----------------------------------------------------------------------------------------------------------------
/*
	Insere ESTADOS PEDIDOS
*/
INSERT INTO med_bandeiras VALUES 
(1, 'AMEX'),
(2, 'Diners Club'),
(3, 'Elo'),
(4, 'HiperCard'),
(5, 'MasterCard'),
(6, 'Visa');

----------------------------------------------------------------------------------------------------------------
/*
	Insere ESTADOS PEDIDOS
*/

----------------------------------------------------------------------------------------------------------------
/*
	Insere ESTADOS PEDIDOS
*/




