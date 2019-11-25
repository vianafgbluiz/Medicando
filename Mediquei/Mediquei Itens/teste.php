<?php
	$servidor = "bd-teste.mysql.uhserver.com";
	$usuario = "teste_banco";
	$banco = "bd_teste";
	$senha = "Mudar@12";

$conn = mysqli_connect($hostname,$usuario,$senha,$banco); mysqli_select_db($banco) or die( "Não foi possível conectar ao banco MySQL");
if (!$conn) {echo "Não foi possível conectar ao banco MySQL.
"; exit;}
else {echo "Parabéns!! A conexão ao banco de dados ocorreu normalmente!.
";}
mysql_close(); 
?>