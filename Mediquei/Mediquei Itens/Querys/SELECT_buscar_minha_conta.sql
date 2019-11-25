SELECT  DISTINCT 
		usr.user_nome AS nome, usr.user_email AS email, DATE_FORMAT(usr.user_data_cadastro, '%m/%d/%Y') as data_cadastro,
		endr.end_logradouro AS logradouro, endr.end_numero AS numero, endr.end_bairro AS bairro,
			endr.end_cep AS cep, endr.end_cidade AS cidade, endr.end_uf AS uf,
		banc.ban_nome_cartao AS nome_cartao, banc.ban_numero AS numero_cartao, 
			banc.ban_validade AS validade_cartao, bnd.bandeira_desc AS bandeira_cartao
  FROM med_usuarios usr, med_enderecos endr, med_bancarios banc, med_bandeiras bnd
 WHERE usr.user_id = 1
   AND endr.end_tipo = 'U'
   AND endr.end_idfk = usr.user_id
   AND banc.ban_id_bandeira = bnd.bandeira_id
ORDER BY endr.end_cep, banc.ban_numero ;
   
   SELECT  DISTINCT 
		usr.user_nome, usr.user_email, DATE_FORMAT(usr.user_data_cadastro, '%m/%d/%Y') as data_cadastro,
		endr.end_logradouro , endr.end_numero , endr.end_bairro ,
			endr.end_cep , endr.end_cidade , endr.end_uf ,
		banc.ban_nome_cartao , banc.ban_numero , 
			banc.ban_validade, bnd.bandeira_desc 
  FROM med_usuarios usr, med_enderecos endr, med_bancarios banc, med_bandeiras bnd
 WHERE usr.user_id = 1
   AND endr.end_tipo = 'U'
   AND endr.end_idfk = usr.user_id
   AND banc.ban_id_bandeira = bnd.bandeira_id;
   
-- SELECT 1
SELECT  DISTINCT 
		usr.user_nome AS nome, usr.user_email AS email, DATE_FORMAT(usr.user_data_cadastro, '%m/%d/%Y') as data_cadastro,
        endr.end_logradouro AS logradouro, endr.end_numero AS numero, endr.end_bairro AS bairro,
			endr.end_cep AS cep, endr.end_cidade AS cidade, endr.end_uf AS uf 
   FROM med_usuarios usr, med_enderecos endr
 WHERE usr.user_id = 1
   AND endr.end_tipo = 'U'
   AND endr.end_idfk = usr.user_id;
   
-- SELECT 2
SELECT banc.ban_nome_cartao AS nome_cartao, banc.ban_numero AS numero_cartao, 
			banc.ban_validade AS validade_cartao, bnd.bandeira_desc AS bandeira_cartao
  FROM med_usuarios usr, med_bancarios banc, med_bandeiras bnd
 WHERE usr.user_id = 1 
   AND banc.ban_id_bandeira = bnd.bandeira_id; 
   
--
SELECT * FROM med_enderecos;

INSERT INTO med_enderecos VALUES (1, 'U', 1, '38412112', 'MG', 'Uberlandia', 'Cidade Jardim',
 'Rua das Petunias', 'Casa', 499);
 INSERT INTO med_enderecos VALUES (2, 'U', 1, '38412114', 'MG', 'Uberlandia', 'Cidade Jardim',
 'Rua dos Bambuzais', 'Casa', 55);
 
SELECT * FROM med_bancarios banc;

ALTER TABLE med_bancarios
	DROP ban_validade, 
	 ADD ban_validade VARCHAR(7);
     

INSERT INTO med_bancarios VALUES (1, 5, '1010202030304040', '391', 'LUIZ F G B VIANA', 1, 
	STR_TO_DATE( "01/06/2021", "%m/%d/%Y")
);

SELECT u.user_id, u.user_email, u.user_senha, u.user_ativo
	FROM med_usuarios u WHERE u.user_email = 'vianafgluiz@gmail.com';
    
SELECT usr.user_nome, usr.user_email, 
        DATE_FORMAT(usr.user_data_cadastro, '%m/%d/%Y') as user_data_cadastro
	FROM med_usuarios usr 
   WHERE usr.user_id = '1';
   
   SELECT 1 FROM DUAL;
 
 SELECT * FROM med_usuarios;
 
 INSERT INTO med_bancarios VALUES (3, 5, '1010202030304040', '391', 'LUIZ F G B VIANA', 1, 
	STR_TO_DATE( "06/2021", "%m/%Y"), '104.159.159-85'
);

DELETE FROM med_bancarios WHERE ban_id in (1,2,3);

INSERT INTO med_bancarios(ban_id_bandeira, ban_numero, ban_codseguranca, ban_nome_cartao, 'ban_validade', ban_id_usuario, ban_cpf)	        VALUES ('6', '1234566666666666' ,'254', 'FHHH','04/2021', '3', '123.333.322-22')