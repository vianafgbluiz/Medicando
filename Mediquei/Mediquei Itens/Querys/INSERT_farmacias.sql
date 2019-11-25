SELECT * FROM med_farmacias;
SELECT * FROM med_enderecos;

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