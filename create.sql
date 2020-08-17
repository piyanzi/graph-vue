-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attribute_table`
--

DROP TABLE IF EXISTS `attribute_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute_table` (
  `attribute_id` int NOT NULL,
  `element_id` int NOT NULL,
  `attribute_name` varchar(32) DEFAULT NULL,
  `attribute_type` varchar(255) DEFAULT NULL,
  `attribute_default_value` varchar(45) DEFAULT NULL,
  `attribute_unit` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute_table`
--

LOCK TABLES `attribute_table` WRITE;
/*!40000 ALTER TABLE `attribute_table` DISABLE KEYS */;
INSERT INTO `attribute_table` VALUES (1,1,'属性1',NULL,'qwe','m3/(s·Pa.5)'),(2,1,'属性2',NULL,'asd','s'),(3,1,'属性3',NULL,'zxc','MPa'),(4,1,'属性4',NULL,'qwe','℃'),(5,1,'属性5',NULL,'zxc','Nm3/h'),(6,2,'属性1',NULL,'hgj','m/(3/h·KPa.5)'),(7,2,'属性2',NULL,'123','Fraction'),(8,2,'属性3',NULL,'g35g2','Wm3/d'),(9,2,'属性4',NULL,'67hjtn','K'),(10,2,'属性5',NULL,'45g4h','KJ/h'),(11,3,'属性1',NULL,'gtreg','MPa'),(12,3,'属性2',NULL,'grge','kg/s'),(13,3,'属性3',NULL,'789','KJ/h'),(14,3,'属性4',NULL,'ryth','r/min'),(15,3,'属性5',NULL,'regv','m3/(s·Pa.5)'),(16,4,'属性1',NULL,'trhrt','KJ/kg'),(17,4,'属性2',NULL,'erg','m3/(s·Pa.5)'),(18,4,'属性3',NULL,'erger','MPa'),(19,4,'属性4',NULL,'etb','m3/(s·Pa.5)'),(20,4,'属性5',NULL,'nrg','s'),(21,5,'属性1',NULL,'bebe','MPa'),(22,5,'属性2',NULL,'132','℃'),(23,5,'属性3',NULL,'233','Nm3/h'),(24,5,'属性4',NULL,'erg','m/(3/h·KPa.5)'),(25,5,'属性5',NULL,'fdg','Fraction'),(26,6,'属性1',NULL,'3g3h','Wm3/d'),(27,6,'属性2',NULL,'34gh','K'),(28,6,'属性3',NULL,'gerg','KJ/h'),(29,6,'属性4',NULL,'srg','MPa'),(30,6,'属性5',NULL,'erg','kg/s'),(31,7,'属性1',NULL,'afv','KJ/h'),(32,7,'属性2',NULL,'getg','r/min'),(33,7,'属性3',NULL,'trb','m3/(s·Pa.5)'),(34,7,'属性4',NULL,'ebr','KJ/kg'),(35,7,'属性5',NULL,'evb','m3/(s·Pa.5)'),(36,8,'属性1',NULL,'enbern','MPa'),(37,8,'属性2',NULL,'rge','m3/(s·Pa.5)'),(38,8,'属性3',NULL,'reg','s'),(39,8,'属性4',NULL,'ehh','MPa'),(40,8,'属性5',NULL,'reb','℃'),(41,9,'属性1',NULL,'grebv','Nm3/h'),(42,9,'属性2',NULL,'ebeb','m/(3/h·KPa.5)'),(43,9,'属性3',NULL,'rbeb','Fraction'),(44,9,'属性4',NULL,'erb','Wm3/d'),(45,9,'属性5',NULL,'vrfv','K'),(46,10,'属性1',NULL,'rbe','KJ/h'),(47,10,'属性2',NULL,'dsv','MPa'),(48,10,'属性3',NULL,'dfb','kg/s'),(49,10,'属性4',NULL,'nee','KJ/h'),(50,10,'属性5',NULL,'erbr','r/min'),(51,11,'属性1',NULL,'tnrt','m3/(s·Pa.5)'),(52,11,'属性2',NULL,'etn','KJ/kg'),(53,11,'属性3',NULL,'35y','m3/(s·Pa.5)'),(54,11,'属性4',NULL,'etnet','MPa'),(55,11,'属性5',NULL,'rb','m3/(s·Pa.5)'),(56,12,'属性1',NULL,'df','s'),(57,12,'属性2',NULL,'ssdv','MPa'),(58,12,'属性3',NULL,'3g43g','℃'),(59,12,'属性4',NULL,'rberb','Nm3/h'),(60,12,'属性5',NULL,'reb','m/(3/h·KPa.5)'),(61,13,'属性1',NULL,'regr','Fraction'),(62,13,'属性2',NULL,'dfb','Wm3/d'),(63,13,'属性3',NULL,'fdb','K'),(64,13,'属性4',NULL,'wev','KJ/h'),(65,13,'属性5',NULL,'web','MPa');
/*!40000 ALTER TABLE `attribute_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connection_table`
--

DROP TABLE IF EXISTS `connection_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `connection_table` (
  `connection_id` int NOT NULL,
  `element_id` int NOT NULL,
  `connection_x` float NOT NULL,
  `connection_y` float NOT NULL,
  PRIMARY KEY (`connection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connection_table`
--

LOCK TABLES `connection_table` WRITE;
/*!40000 ALTER TABLE `connection_table` DISABLE KEYS */;
INSERT INTO `connection_table` VALUES (1,1,0,0.5),(2,1,1,0.5),(3,2,0,0.5),(4,2,1,0.5),(5,3,0,0.5),(6,3,0.5,0),(7,4,1,0),(8,4,0,1),(9,5,0.75,0),(10,5,0,0.75),(11,6,1,0.5),(12,6,0.5,1),(13,7,0.75,1),(14,7,1,0.5),(15,8,1,0),(16,8,0,1),(17,9,1,0),(18,9,0,1),(19,10,0.5,1),(20,10,0.5,0),(21,11,0,0),(22,11,1,1),(23,12,0,0.5),(24,12,1,0.5),(25,13,0,0.5),(26,13,0.5,0);
/*!40000 ALTER TABLE `connection_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `element_table`
--

DROP TABLE IF EXISTS `element_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `element_table` (
  `element_id` int NOT NULL,
  `element_type` int DEFAULT NULL,
  `element_name` varchar(32) DEFAULT NULL,
  `element_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`element_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `element_table`
--

LOCK TABLES `element_table` WRITE;
/*!40000 ALTER TABLE `element_table` DISABLE KEYS */;
INSERT INTO `element_table` VALUES (1,NULL,'分输站','/elements/分输站.svg'),(2,NULL,'单向阀','/elements/单向阀.svg'),(3,NULL,'截断阀','/elements/截断阀.svg'),(4,NULL,'油库','/elements/油库.svg'),(5,NULL,'注入站','/elements/注入站.svg'),(6,NULL,'理想调节阀','/elements/理想调节阀.svg'),(7,NULL,'离心压缩机','/elements/离心压缩机.svg'),(8,NULL,'空冷器','/elements/空冷器.svg'),(9,NULL,'立管','/elements/立管.svg'),(10,NULL,'站场','/elements/站场.svg'),(11,NULL,'水平管道','/elements/水平管道.svg'),(12,NULL,'竖直管道','/elements/竖直管道.svg'),(13,NULL,'阀室','/elements/阀室.svg');
/*!40000 ALTER TABLE `element_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_table`
--

DROP TABLE IF EXISTS `project_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_table` (
  `project_id` int NOT NULL,
  `user_id` int NOT NULL,
  `project_name` varchar(32) DEFAULT NULL,
  `project_file` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_table`
--

LOCK TABLES `project_table` WRITE;
/*!40000 ALTER TABLE `project_table` DISABLE KEYS */;
INSERT INTO `project_table` VALUES (1,111,'项目1','/models/111/1.xml'),(2,111,'项目2','/models/111/2.xml'),(3,333,'test1','/models/333/1.xml');
/*!40000 ALTER TABLE `project_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_table`
--

DROP TABLE IF EXISTS `user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_table` (
  `user_id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_table`
--

LOCK TABLES `user_table` WRITE;
/*!40000 ALTER TABLE `user_table` DISABLE KEYS */;
INSERT INTO `user_table` VALUES ('111','a','a',1),('222','bbb','b',1),('333','c','c',0);
/*!40000 ALTER TABLE `user_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 13:51:03




