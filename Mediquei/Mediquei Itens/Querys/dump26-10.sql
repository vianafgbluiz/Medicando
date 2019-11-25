-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_mediquei
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.34-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `med_bancarios`
--

DROP TABLE IF EXISTS `med_bancarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_bancarios` (
  `ban_id` int(11) NOT NULL AUTO_INCREMENT,
  `ban_id_bandeira` int(11) NOT NULL,
  `ban_numero` varchar(16) NOT NULL,
  `ban_codseguranca` varchar(3) NOT NULL,
  `ban_nome_cartao` varchar(50) NOT NULL,
  `ban_id_usuario` int(11) NOT NULL,
  `ban_cpf` varchar(15) NOT NULL,
  `ban_validade` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`ban_id`),
  KEY `med_bancarios_med_bandeiras_fk` (`ban_id_bandeira`),
  KEY `med_bancarios_med_usuarios_fk` (`ban_id_usuario`),
  CONSTRAINT `med_bancarios_med_bandeiras_fk` FOREIGN KEY (`ban_id_bandeira`) REFERENCES `med_bandeiras` (`bandeira_id`),
  CONSTRAINT `med_bancarios_med_usuarios_fk` FOREIGN KEY (`ban_id_usuario`) REFERENCES `med_usuarios` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_bancarios`
--

--
-- Table structure for table `med_bandeiras`
--

DROP TABLE IF EXISTS `med_bandeiras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_bandeiras` (
  `bandeira_id` int(11) NOT NULL AUTO_INCREMENT,
  `bandeira_desc` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`bandeira_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_bandeiras`
--

LOCK TABLES `med_bandeiras` WRITE;
/*!40000 ALTER TABLE `med_bandeiras` DISABLE KEYS */;
INSERT INTO `med_bandeiras` VALUES (1,'AMEX'),(2,'Diners Club'),(3,'Elo'),(4,'HiperCard'),(5,'MasterCard'),(6,'Visa');
/*!40000 ALTER TABLE `med_bandeiras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_enderecos`
--

DROP TABLE IF EXISTS `med_enderecos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_enderecos` (
  `end_id` int(11) NOT NULL AUTO_INCREMENT,
  `end_tipo` char(1) NOT NULL,
  `end_idfk` int(11) NOT NULL,
  `end_cep` varchar(10) NOT NULL,
  `end_uf` char(2) DEFAULT NULL,
  `end_cidade` varchar(30) NOT NULL,
  `end_bairro` varchar(30) DEFAULT NULL,
  `end_logradouro` varchar(100) NOT NULL,
  `end_complemento` varchar(50) DEFAULT NULL,
  `end_numero` varchar(10) NOT NULL,
  PRIMARY KEY (`end_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_enderecos`
--

LOCK TABLES `med_enderecos` WRITE;
/*!40000 ALTER TABLE `med_enderecos` DISABLE KEYS */;
INSERT INTO `med_enderecos` VALUES (1,'U',1,'38412112','MG','Uberlandia','Cidade Jardim','Rua das Petunias','Casa','499'),(2,'F', 1, '38411-410', 'MG', 'Uberlândia', 'Shopping Park', 'Avenida Boulanger Fonseca', 'Farmácia', '224'),(3,'F', 2, '38408-168', 'MG', 'Uberlândia', 'Santa Mônica', 'Avenida Belarmino Cotta Pacheco', 'Farmácia', '687'),(4,'F', 3, '38412-144', 'MG', 'Uberlândia', 'Cidade Jardim', 'Rua dos Eucalíptos', 'Farmácia', '417'),(5,'F', 4, '38407-477', 'MG', 'Uberlândia', 'Morumbi', 'Avenida Antônio Jorge Isaac', 'Farmácia', '1332');
/*!40000 ALTER TABLE `med_enderecos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_farmacias`
--

DROP TABLE IF EXISTS `med_farmacias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_farmacias` (
  `farm_id` int(11) NOT NULL AUTO_INCREMENT,
  `farm_nome` varchar(100) NOT NULL,
  `farm_cnpj` varchar(50) NOT NULL,
  `farm_tel` varchar(15) NOT NULL,
  `farm_logos` varchar(150) DEFAULT NULL,
  `farm_razao_social` varchar(200) NOT NULL,
  PRIMARY KEY (`farm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_farmacias`
--

LOCK TABLES `med_farmacias` WRITE;
/*!40000 ALTER TABLE `med_farmacias` DISABLE KEYS */;
INSERT INTO `med_farmacias` VALUES (1,'Droga Assis','12.562.790/0001-88','34 3235-9354',NULL,'Drogaria Assis Comercio Farmaceutico LTDA'),(2,'Droga City','08.647.378/0001-93','34 3231-3420',NULL,'Frankel Comercio de Medicamentos LTDA'),(3,'Farmatem Drogaria e Farmácia','08.647.378/0001-93','34 3217-2890',NULL,'J B da Silva Vieira Drogaria LTDA - ME'),(4,'Drogaria Morumbi','11.578.720/0001-55','34 3226-3874',NULL,'Tarcisio da Silveira Cruz LTDA - ME');
/*!40000 ALTER TABLE `med_farmacias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_forma_pagamento`
--

DROP TABLE IF EXISTS `med_forma_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_forma_pagamento` (
  `id_forma_pagamento` int(11) NOT NULL AUTO_INCREMENT,
  `fp_descricao` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_forma_pagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_forma_pagamento`
--

LOCK TABLES `med_forma_pagamento` WRITE;
/*!40000 ALTER TABLE `med_forma_pagamento` DISABLE KEYS */;
INSERT INTO `med_forma_pagamento` VALUES (1,'Cartão de Crédito'),(2,'Boleto Bancário');
/*!40000 ALTER TABLE `med_forma_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_mdescricao`
--

DROP TABLE IF EXISTS `med_mdescricao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_mdescricao` (
  `desc_id` int(11) NOT NULL AUTO_INCREMENT,
  `desc_id_medic` int(11) NOT NULL,
  `desc_dsc` varchar(100) DEFAULT NULL,
  `desc_quantidade` varchar(25) DEFAULT NULL,
  `desc_receita` char(1) DEFAULT NULL,
  PRIMARY KEY (`desc_id`),
  KEY `med_mdescricao_med_medicamentos_fk` (`desc_id_medic`),
  CONSTRAINT `med_mdescricao_med_medicamentos_fk` FOREIGN KEY (`desc_id_medic`) REFERENCES `med_medicamentos` (`medic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_mdescricao`
--

LOCK TABLES `med_mdescricao` WRITE;
/*!40000 ALTER TABLE `med_mdescricao` DISABLE KEYS */;
INSERT INTO `med_mdescricao` VALUES (1,1,'Ajuda na descamação da pele, removendo verrugas comuns e calos.','0,1g/mL x 5mL','N'),(2,1,'Ajuda na descamação da pele, removendo verrugas comuns e calos.','12 pastilhas x 13g','N'),(3,2,'Controle da hipertensão arterial (pressão alta)','30 comprimidos x 100mg','S'),(4,2,'Controle da hipertensão arterial (pressão alta)','30 comprimidos x 25mg','S'),(5,2,'Controle da hipertensão arterial (pressão alta)','30 comprimidos x 50mg','S'),(6,3,'Tratamento da obstrução dos brônquios','5mg x Xarope 120 mL','N'),(7,3,'Tratamento da obstrução dos brônquios','10mg x Xarope 120 mL','N'),(8,3,'Tratamento da obstrução dos brônquios','25mg x Xarope 120 mL','N'),(9,3,'Tratamento da obstrução dos brônquios','50mg x Xarope 120 mL','N'),(10,4,'Tratamento de candidíase invasiva grave','100 amp x 5mL','N'),(11,4,'Tratamento de candidíase invasiva grave','100 amp x 10mL','N'),(12,4,'Tratamento de candidíase invasiva grave','100 amp x 20mL','N'),(13,5,'Tratamento de infecções bacterianas','15 comprimidos','N'),(14,5,'Tratamento de infecções bacterianas','21 comprimidos','N'),(15,5,'Tratamento de infecções bacterianas','30 comprimidos','N'),(16,6,'Tratamento de infecções da pele e/ou de mucosas','10g pomada','N'),(17,6,'Tratamento de infecções da pele e/ou de mucosas','15g pomada','N'),(18,7,'Tratamento da hipertensão arterial (pressão alta)','30 comprimidos x 150mg','S'),(19,7,'Tratamento da hipertensão arterial (pressão alta)','30 comprimidos x 300mg','S'),(20,8,'Tratamento da gripe','20 comprimidos','N'),(21,9,'Tratamento de infecções oriundas da sensibilidade à penicilina G','50fa x 4mL injeção','S'),(22,9,'Tratamento de infecções oriundas da sensibilidade à penicilina G','10fa x 4mL injeção','S'),(23,10,'Melhora da espasticidade (rigidez muscular)','100ui frasco-ampola','S'),(24,10,'Melhora da espasticidade (rigidez muscular)','200ui frasco-ampola','S'),(25,10,'Melhora da espasticidade (rigidez muscular)','50ui frasco-ampola','S');
/*!40000 ALTER TABLE `med_mdescricao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_medicamentos`
--

DROP TABLE IF EXISTS `med_medicamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_medicamentos` (
  `medic_id` int(11) NOT NULL AUTO_INCREMENT,
  `medic_nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`medic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_medicamentos`
--

LOCK TABLES `med_medicamentos` WRITE;
/*!40000 ALTER TABLE `med_medicamentos` DISABLE KEYS */;
INSERT INTO `med_medicamentos` VALUES (1,'A CURITYBINA'),(2,'ABLOK'),(3,'ACEBROFILINA'),(4,'AGUA PARA INJETAVEIS'),(5,'AMOXICILINA'),(6,'BACINA'),(7,'BART H'),(8,'BENEGRIP'),(9,'BENZETACIL'),(10,'BOTOX'),(11,'CATIVA'),(12,'CEFALEXINA'),(13,'CETOCONAZOL'),(14,'CETOPROFENO'),(15,'CLORDILON'),(16,'DESOL'),(17,'DETAMAX'),(18,'DIABEMED'),(19,'DIAZEPAM'),(20,'DIPIRONA SODICA'),(21,'EQUITAM'),(22,'ERANZ'),(23,'ERBITUX'),(24,'ERITROMAX'),(25,'ESC');
/*!40000 ALTER TABLE `med_medicamentos` ENABLE KEYS */;
UNLOCK TABLES;



DROP TABLE IF EXISTS `med_precos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_precos` (
  `p_medicamento` int(11) DEFAULT NULL,
  `p_farmacia` int(11) DEFAULT NULL,
  `p_preco` decimal(8,2) DEFAULT NULL,
  KEY `med_precos_med_mdescricao_fk` (`p_medicamento`),
  KEY `med_precos_med_farmacias_fk` (`p_farmacia`),
  CONSTRAINT `med_precos_med_farmacias_fk` FOREIGN KEY (`p_farmacia`) REFERENCES `med_farmacias` (`farm_id`),
  CONSTRAINT `med_precos_med_mdescricao_fk` FOREIGN KEY (`p_medicamento`) REFERENCES `med_mdescricao` (`desc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_precos`
--

LOCK TABLES `med_precos` WRITE;
/*!40000 ALTER TABLE `med_precos` DISABLE KEYS */;
INSERT INTO `med_precos` VALUES (1,2,85.00),(2,2,95.50),(1,3,90.00),(2,3,92.50),(2,4,87.50),(3,1,31.20),(4,1,9.60),(5,1,15.90),(3,2,38.20),(5,2,15.60),(4,4,8.20),(6,1,5.34),(7,1,10.34),(8,1,20.34),(9,1,40.34),(6,2,7.50),(7,2,12.50),(8,2,19.50),(9,2,39.50),(6,3,6.00),(7,3,12.00),(8,3,22.00),(9,3,42.50),(6,4,8.00),(7,4,16.50),(8,4,25.10),(9,4,52.18),(13,1,10.00),(14,1,15.00),(15,1,18.00),(13,2,11.00),(14,2,15.50),(15,2,18.50),(13,3,7.49),(14,3,18.00),(15,3,20.00),(16,1,16.50),(17,1,20.50),(16,2,17.50),(17,2,21.50),(16,3,18.50),(17,3,22.50),(16,4,14.50),(17,4,26.50),(18,1,45.00),(19,1,70.00),(18,2,40.00),(19,2,80.00),(18,3,33.00),(19,3,68.00),(18,4,50.00),(19,4,79.00),(20,1,21.90),(20,2,22.90),(20,3,19.90),(20,4,17.90),(23,2,1200.00),(24,2,1100.00),(25,2,450.00),(23,3,600.00),(24,3,1100.00),(25,3,400.00);
/*!40000 ALTER TABLE `med_precos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_situacao`
--

DROP TABLE IF EXISTS `med_situacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_situacao` (
  `sit_id` int(11) NOT NULL AUTO_INCREMENT,
  `sit_descricao` varchar(30) NOT NULL,
  PRIMARY KEY (`sit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_situacao`
--

LOCK TABLES `med_situacao` WRITE;
/*!40000 ALTER TABLE `med_situacao` DISABLE KEYS */;
INSERT INTO `med_situacao` VALUES (1,'Pedido Efetuado'),(2,'Pedido Autorizado'),(3,'Pedido Em transporte'),(4,'Pedido Entregue'),(5,'Pedido Não Autorizado'),(6,'Pedido cancelado'),(7,'Pedido Não Entregue'),(8,'Pedido Aguardando Retirada');
/*!40000 ALTER TABLE `med_situacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_usuarios`
--

DROP TABLE IF EXISTS `med_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_usuarios` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_nome` varchar(50) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_senha` varchar(50) NOT NULL,
  `user_ativo` char(1) NOT NULL,
  `user_data_cadastro` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_usuarios`
--

LOCK TABLES `med_usuarios` WRITE;
/*!40000 ALTER TABLE `med_usuarios` DISABLE KEYS */;
INSERT INTO `med_usuarios` VALUES (1,'Luiz Felipe G B Viana','vianafgluiz@gmail.com','MTIzNDU2','A','2018-10-05 20:26:07');
/*!40000 ALTER TABLE `med_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_version`
--

DROP TABLE IF EXISTS `med_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_version` (
  `v_id` int(11) NOT NULL AUTO_INCREMENT,
  `v_version` decimal(6,1) DEFAULT NULL,
  PRIMARY KEY (`v_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_version`
--

LOCK TABLES `med_version` WRITE;
/*!40000 ALTER TABLE `med_version` DISABLE KEYS */;
/*!40000 ALTER TABLE `med_version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bd_mediquei'
--

--
-- Table structure for table `med_pedidos`
--

DROP TABLE IF EXISTS `med_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_pedidos` (
  `ped_id` varchar(8) NOT NULL,
  `ped_id_situacao` int(11) NOT NULL,
  `ped_id_user` int(11) NOT NULL,
  `ped_id_user_endereco` int(11) NOT NULL,
  `ped_id_user_bancarios` int(11) DEFAULT NULL,
  `ped_id_farmacia` int(11) NOT NULL,
  `ped_id_medicamento` int(11) NOT NULL,
  `ped_id_descricao` int(11) NOT NULL,
  `ped_qtdade_medicamentos` int(11) NOT NULL,
  `ped_valor_total` decimal(8,2) NOT NULL,
  `ped_id_forma_pagamento` int(11) NOT NULL,
  `ped_qtdade_parcelas` int(11) DEFAULT NULL,
  `ped_data_solicitacao` datetime NOT NULL,
  `ped_data_entregue` datetime DEFAULT NULL,
  `ped_link_receita` varchar(200) DEFAULT NULL,
  `ped_taxa_entrega` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`ped_id`),
  KEY `med_pedidos_med_farmacias_fk` (`ped_id_farmacia`),
  KEY `med_pedidos_med_forma_pag_fk` (`ped_id_forma_pagamento`),
  KEY `med_pedidos_med_medcts_fk` (`ped_id_medicamento`),
  KEY `med_pedidos_med_situacao_fk` (`ped_id_situacao`),
  KEY `med_pedidos_med_usuarios_fk` (`ped_id_user`),
  KEY `med_pedidos_med_endereco_fk` (`ped_id_user_endereco`),
  KEY `med_pedidos_med_bancarios_fk` (`ped_id_user_bancarios`),
  CONSTRAINT `med_pedidos_med_bancarios_fk` FOREIGN KEY (`ped_id_user_bancarios`) REFERENCES `med_bancarios` (`ban_id`),
  CONSTRAINT `med_pedidos_med_endereco_fk` FOREIGN KEY (`ped_id_user_endereco`) REFERENCES `med_enderecos` (`end_id`),
  CONSTRAINT `med_pedidos_med_farmacias_fk` FOREIGN KEY (`ped_id_farmacia`) REFERENCES `med_farmacias` (`farm_id`),
  CONSTRAINT `med_pedidos_med_forma_pag_fk` FOREIGN KEY (`ped_id_forma_pagamento`) REFERENCES `med_forma_pagamento` (`id_forma_pagamento`),
  CONSTRAINT `med_pedidos_med_medcts_fk` FOREIGN KEY (`ped_id_medicamento`) REFERENCES `med_medicamentos` (`medic_id`),
  CONSTRAINT `med_pedidos_med_situacao_fk` FOREIGN KEY (`ped_id_situacao`) REFERENCES `med_situacao` (`sit_id`),
  CONSTRAINT `med_pedidos_med_usuarios_fk` FOREIGN KEY (`ped_id_user`) REFERENCES `med_usuarios` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_pedidos`
--

LOCK TABLES `med_pedidos` WRITE;
/*!40000 ALTER TABLE `med_pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `med_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_precos`
--

--
-- Dumping routines for database 'bd_mediquei'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-26  9:03:12
