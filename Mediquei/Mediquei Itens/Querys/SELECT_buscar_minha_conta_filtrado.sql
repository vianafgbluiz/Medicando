SELECT  DISTINCT 
        usr.user_nome, usr.user_email,
        endr.end_cep
   FROM med_usuarios usr, med_enderecos endr
 WHERE usr.user_id = 1
   AND endr.end_tipo = 'U'
   AND endr.end_idfk = usr.user_id;
   
   SELECT banc.ban_nome_cartao AS nome_cartao, banc.ban_numero AS numero_cartao, 
            banc.ban_validade AS validade_cartao, bnd.bandeira_desc AS bandeira_cartao
  FROM med_usuarios usr, med_bancarios banc, med_bandeiras bnd
 WHERE usr.user_id = 1 
   AND banc.ban_id_bandeira = bnd.bandeira_id;
   
SELECT * FROM med_usuarios;
