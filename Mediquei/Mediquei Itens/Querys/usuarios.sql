CREATE TABLE med_usuarios (
    user_id              INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_nome            VARCHAR(50) NOT NULL,
    user_email           VARCHAR(100) NOT NULL,
    user_senha           VARCHAR(30) NOT NULL,
    user_ativo           CHAR(1) NOT NULL,
    user_data_cadastro   DATETIME NOT NULL
);

ALTER TABLE med_usuarios ADD CONSTRAINT med_usuarios_pk PRIMARY KEY ( user_id );

DROP TABLE med_usuarios;
DROP TABLE med_bandeiras;
DROP TABLE med_compras;
DROP TABLE med_bancarios;
DROP TABLE med_enderecos;
DROP TABLE med_medicamentos;
DROP TABLE med_farmacias;
DROP TABLE med_forma_pagamento;
DROP TABLE med_pedidos;
DROP TABLE med_situacao;

INSERT INTO med_usuarios(user_nome, user_email, user_senha, user_ativo, user_data_cadastro)
	 VALUES ('Luiz Felipe', 'vianafgluiz@gmail.com', '123456', 'A', NOW());
     
SELECT * FROM med_usuarios;

DELETE FROM med_usuarios WHERE user_id in (4,7,8);

UPDATE med_usuarios SET user_senha = 'Stu@rtLiro' WHERE user_id = 1;


-- QUERY GERADA VIA DATAMODELER

CREATE TABLE med_bancarios (
    ban_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ban_id_bandeira    INTEGER NOT NULL,
    ban_numero         VARCHAR(16) NOT NULL,
    ban_codseguranca   VARCHAR(3) NOT NULL,
    ban_nome_cartao    VARCHAR(50) NOT NULL,
    ban_validade       DATE NOT NULL,
    ban_id_usuario     INTEGER NOT NULL,
	ban_cpf VARCHAR(15) NOT NULL
);


CREATE TABLE med_bandeiras (
    bandeira_id     INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bandeira_desc   VARCHAR(30)
);

CREATE TABLE med_compras (
    comp_id              INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    comp_id_usuario      INTEGER NOT NULL,
    comp_id_pedido       INTEGER NOT NULL,
    comp_id_farmacia     INTEGER NOT NULL,
    comp_num             VARCHAR(8) NOT NULL,
    comp_data_entregue   DATETIME NOT NULL
);

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
);

CREATE TABLE med_farmacias (
    farm_id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    farm_nome    VARCHAR(100) NOT NULL,
    farm_cnpj    VARCHAR(50) NOT NULL,
    farm_tel     VARCHAR(15) NOT NULL,
    farm_logos   VARCHAR(150)
);

ALTER TABLE med_farmacias 
	ADD COLUMN farm_razao_social VARCHAR(200) NOT NULL;

CREATE TABLE med_forma_pagamento (
    id_forma_pagamento   INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_desc              VARCHAR(10) NOT NULL
);


CREATE TABLE med_medicamentos (
    medic_id     INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    medic_nome   VARCHAR(50)
);


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
);

CREATE TABLE med_situacao (
    sit_id          INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sit_descricao   VARCHAR(30) NOT NULL
);

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

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE med_pedidos
    ADD CONSTRAINT med_pedidos_med_forma_pag_fk FOREIGN KEY ( ped_id_forma_pagamento )
        REFERENCES med_forma_pagamento ( id_forma_pagamento );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE med_pedidos
    ADD CONSTRAINT med_pedidos_med_medcts_fk FOREIGN KEY ( ped_id_medicamento )
        REFERENCES med_medicamentos ( medic_id );

ALTER TABLE med_pedidos
    ADD CONSTRAINT med_pedidos_med_situacao_fk FOREIGN KEY ( ped_id_situacao )
        REFERENCES med_situacao ( sit_id );

ALTER TABLE med_pedidos
    ADD CONSTRAINT med_pedidos_med_usuarios_fk FOREIGN KEY ( ped_id_usuario )
        REFERENCES med_usuarios ( user_id );
