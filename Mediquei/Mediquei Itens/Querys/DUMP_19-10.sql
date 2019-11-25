-- QUERY COMPLETA

-- QUERY GERADA VIA DATAMODELER

CREATE TABLE med_bancarios (
    ban_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ban_id_bandeira    INTEGER NOT NULL,
    ban_numero         VARCHAR(16) NOT NULL,
    ban_codseguranca   VARCHAR(3) NOT NULL,
    ban_nome_cartao    VARCHAR(50) NOT NULL,
    ban_validade       DATETIME NOT NULL,
    ban_id_usuario     INTEGER NOT NULL,
	ban_cpf 		   VARCHAR(15) NOT NULL
)  CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE med_bandeiras (
    bandeira_id     INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bandeira_desc   VARCHAR(30)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE med_compras (
    comp_id              INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    comp_id_usuario      INTEGER NOT NULL,
    comp_id_pedido       INTEGER NOT NULL,
    comp_id_farmacia     INTEGER NOT NULL,
    comp_num             VARCHAR(8) NOT NULL,
    comp_data_entregue   DATETIME NOT NULL
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE med_enderecos (
    end_id            INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    end_tipo          CHAR(1) NOT NULL,
    end_idfk          INTEGER NOT NULL,
    end_cep           VARCHAR(10) NOT NULL,
    end_uf            CHAR(2),
    end_cidade        VARCHAR(30) NOT NULL,
    end_bairro        VARCHAR(30),
    end_logradouro    VARCHAR(100) NOT NULL,
    end_complemento   VARCHAR(50),
    end_numero        VARCHAR(10) NOT NULL
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE med_farmacias (
    farm_id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    farm_nome    VARCHAR(100) NOT NULL,
	farm_razao_social VARCHAR(200) NOT NULL,
    farm_cnpj    VARCHAR(50) NOT NULL,
    farm_tel     VARCHAR(15) NOT NULL,
    farm_logos   VARCHAR(150)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE med_forma_pagamento (
    id_forma_pagamento   INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_desc              VARCHAR(10) NOT NULL
) CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE med_medicamentos (
    medic_id     INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    medic_nome   VARCHAR(50)
) CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE med_pedidos (
    ped_id                    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ped_id_situacao           INTEGER NOT NULL,
    ped_id_usuario            INTEGER NOT NULL,
    ped_id_farmacia           INTEGER NOT NULL,
    ped_id_medicamento        INTEGER NOT NULL,
    ped_qtdade_medicamentos   INTEGER NOT NULL,
    ped_data_solicitacao      DATETIME NOT NULL,
    ped_data_entregue         DATETIME NOT NULL,
    ped_valor_total           NUMERIC(8,2) NOT NULL,
    ped_id_forma_pagamento    INTEGER NOT NULL
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE med_situacao (
    sit_id          INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sit_descricao   VARCHAR(30) NOT NULL
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE med_usuarios (
    user_id              INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_nome            VARCHAR(50) NOT NULL,
    user_email           VARCHAR(100) NOT NULL,
    user_senha           VARCHAR(50) NOT NULL,
    user_ativo           CHAR(1) NOT NULL,
    user_data_cadastro   DATETIME NOT NULL
) CHARACTER SET utf8 COLLATE utf8_general_ci;

ALTER TABLE med_bancarios
    ADD CONSTRAINT med_bancarios_med_bandeiras_fk FOREIGN KEY ( ban_id_bandeira )
        REFERENCES med_bandeiras ( bandeira_id );

ALTER TABLE med_bancarios
    ADD CONSTRAINT med_bancarios_med_usuarios_fk FOREIGN KEY ( ban_id_usuario )
        REFERENCES med_usuarios ( user_id );

ALTER TABLE med_compras
    ADD CONSTRAINT med_compras_med_farmacias_fk FOREIGN KEY ( comp_id_farmacia )
        REFERENCES med_farmacias ( farm_id );

ALTER TABLE med_compras
    ADD CONSTRAINT med_compras_med_pedidos_fk FOREIGN KEY ( comp_id_pedido )
        REFERENCES med_pedidos ( ped_id );

ALTER TABLE med_compras
    ADD CONSTRAINT med_compras_med_usuarios_fk FOREIGN KEY ( comp_id_usuario )
        REFERENCES med_usuarios ( user_id );

ALTER TABLE med_pedidos
    ADD CONSTRAINT med_pedidos_med_farmacias_fk FOREIGN KEY ( ped_id_farmacia )
        REFERENCES med_farmacias ( farm_id );

ALTER TABLE med_pedidos
    ADD CONSTRAINT med_pedidos_med_forma_pag_fk FOREIGN KEY ( ped_id_forma_pagamento )
        REFERENCES med_forma_pagamento ( id_forma_pagamento );

ALTER TABLE med_pedidos
    ADD CONSTRAINT med_pedidos_med_medcts_fk FOREIGN KEY ( ped_id_medicamento )
        REFERENCES med_medicamentos ( medic_id );

ALTER TABLE med_pedidos
    ADD CONSTRAINT med_pedidos_med_situacao_fk FOREIGN KEY ( ped_id_situacao )
        REFERENCES med_situacao ( sit_id );

ALTER TABLE med_pedidos
    ADD CONSTRAINT med_pedidos_med_usuarios_fk FOREIGN KEY ( ped_id_usuario )
        REFERENCES med_usuarios ( user_id );

-- Query para inserir na tabela (med_farmacias) as Farmacias
INSERT INTO med_farmacias(farm_nome, farm_cnpj, farm_tel, farm_logos, farm_razao_social) 
	 VALUES
('Droga Assis', '12.562.790/0001-88', '34 3235-9354', NULL, 'Drogaria Assis Comercio Farmaceutico LTDA'),
('Droga City', '08.647.378/0001-93', '34 3231-3420', NULL ,'Frankel Comercio de Medicamentos LTDA'),
('Farmatem Drogaria e Farmácia', '08.647.378/0001-93', '34 3217-2890', NULL, 'J B da Silva Vieira Drogaria LTDA - ME'),
('Drogaria Morumbi', '11.578.720/0001-55', '34 3226-3874', NULL, 'Tarcisio da Silveira Cruz LTDA - ME');

-- Query para inserir na tabela (med_situacao) as Situações de Pagamento
INSERT INTO med_situacao VALUES 
(1, 'Pedido Efetuado'),
(2, 'Pedido Autorizado'),
(3, 'Pedido Em transporte'),
(4, 'Pedido Entregue'),
(5, 'Pedido Não Autorizado'),
(6, 'Pedido cancelado'),
(7, 'Pedido Não Entregue'),
(8, 'Pedido Aguardando Retirada');

-- Query para inserir na tabela (med_bandeiras) as Bandeiras Aceitas
INSERT INTO med_bandeiras VALUES 
(1, 'AMEX'),
(2, 'Diners Club'),
(3, 'Elo'),
(4, 'HiperCard'),
(5, 'MasterCard'),
(6, 'Visa');
