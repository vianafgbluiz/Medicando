SELECT e.end_tipo, e.end_id, e.end_uf, e.end_cidade, e.end_bairro,e.end_logradouro, e.end_numero 
	 FROM med_enderecos e
	WHERE (e.end_idfk = 1 AND e.end_tipo = 'U')
       OR (e.end_idfk = 1 AND e.end_tipo = 'F')
 ORDER BY e.end_tipo;
