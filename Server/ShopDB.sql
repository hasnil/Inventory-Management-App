-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: localhost    Database: ENSF608Project
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `CLIENTS`
--

DROP TABLE IF EXISTS `CLIENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CLIENTS` (
  `ClientID` int NOT NULL,
  `LName` varchar(20) NOT NULL,
  `FName` varchar(20) NOT NULL,
  `Type` char(1) DEFAULT NULL,
  `PhoneNum` char(15) DEFAULT NULL,
  `Address` varchar(40) NOT NULL,
  `PostalCode` char(7) DEFAULT NULL,
  PRIMARY KEY (`ClientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CLIENTS`
--

LOCK TABLES `CLIENTS` WRITE;
/*!40000 ALTER TABLE `CLIENTS` DISABLE KEYS */;
INSERT INTO `CLIENTS` VALUES (101,'Proudmoore','Jaina','R','403-101-5555','T2O 2S5',NULL),(102,'Frostwolf','Thrall','R','403-102-5555','T2O 2S5',NULL),(103,'Wrynn','Varian','R','403-103-5555','T2O 2S7',NULL),(104,'Menethil','Arthas','R','403-104-5555','T2O 2S8',NULL),(105,'Bronzebeard','Muradin','R','403-105-5555','T2O 2S9',NULL),(106,'Lightbringer','Uther','C','403-106-5555','T2O 2S1',NULL),(107,'Sanguinar','Valeera','C','403-107-5555','T2O 2S4',NULL),(108,'Windrunner','Sylvanas','C','403-108-5555','T2O 2S3',NULL),(109,'Whitemane','Sally','C','403-109-5555','T2O 2S2',NULL),(110,'Mograine','Alexandros','C','403-110-5555','T2O 2S6',NULL);
/*!40000 ALTER TABLE `CLIENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ELECTRICAL`
--

DROP TABLE IF EXISTS `ELECTRICAL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ELECTRICAL` (
  `ToolID` int NOT NULL,
  `PowerType` varchar(2) NOT NULL,
  PRIMARY KEY (`ToolID`),
  CONSTRAINT `electrical_ibfk_1` FOREIGN KEY (`ToolID`) REFERENCES `TOOL` (`ToolID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ELECTRICAL`
--

LOCK TABLES `ELECTRICAL` WRITE;
/*!40000 ALTER TABLE `ELECTRICAL` DISABLE KEYS */;
INSERT INTO `ELECTRICAL` VALUES (1000,'AC'),(1001,'DC'),(1002,'DC'),(1003,'AC'),(1004,'DC'),(1005,'AC'),(1006,'AC'),(1007,'AC'),(1008,'AC'),(1009,'DC'),(1010,'AC'),(1011,'DC'),(1012,'AC'),(1013,'DC'),(1014,'AC'),(1015,'DC'),(1016,'AC'),(1017,'DC'),(1018,'AC');
/*!40000 ALTER TABLE `ELECTRICAL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INTERNATIONAL`
--

DROP TABLE IF EXISTS `INTERNATIONAL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `INTERNATIONAL` (
  `SupplierID` int NOT NULL,
  `ImportTax` decimal(5,5) DEFAULT NULL,
  PRIMARY KEY (`SupplierID`),
  CONSTRAINT `international_ibfk_1` FOREIGN KEY (`SupplierID`) REFERENCES `SUPPLIER` (`SupplierID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INTERNATIONAL`
--

LOCK TABLES `INTERNATIONAL` WRITE;
/*!40000 ALTER TABLE `INTERNATIONAL` DISABLE KEYS */;
INSERT INTO `INTERNATIONAL` VALUES (8020,0.15000),(8021,0.15000),(8022,0.15000),(8023,0.15000),(8024,0.15000),(8025,0.15000);
/*!40000 ALTER TABLE `INTERNATIONAL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERLINE`
--

DROP TABLE IF EXISTS `ORDERLINE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ORDERLINE` (
  `OrderID` int NOT NULL,
  `ToolID` int NOT NULL,
  `SupplierID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`OrderID`,`ToolID`),
  KEY `ToolID` (`ToolID`),
  KEY `SupplierID` (`SupplierID`),
  CONSTRAINT `orderline_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `ORDERS` (`OrderID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderline_ibfk_2` FOREIGN KEY (`ToolID`) REFERENCES `TOOL` (`ToolID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderline_ibfk_3` FOREIGN KEY (`SupplierID`) REFERENCES `SUPPLIER` (`SupplierID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDERLINE`
--

LOCK TABLES `ORDERLINE` WRITE;
/*!40000 ALTER TABLE `ORDERLINE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ORDERLINE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERS`
--

DROP TABLE IF EXISTS `ORDERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ORDERS` (
  `OrderID` int NOT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDERS`
--

LOCK TABLES `ORDERS` WRITE;
/*!40000 ALTER TABLE `ORDERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `ORDERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PURCHASES`
--

DROP TABLE IF EXISTS `PURCHASES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PURCHASES` (
  `ClientID` int NOT NULL,
  `ToolID` int NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`ClientID`,`ToolID`,`Date`),
  KEY `ToolID` (`ToolID`),
  CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `CLIENTS` (`ClientID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `purchases_ibfk_2` FOREIGN KEY (`ToolID`) REFERENCES `TOOL` (`ToolID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PURCHASES`
--

LOCK TABLES `PURCHASES` WRITE;
/*!40000 ALTER TABLE `PURCHASES` DISABLE KEYS */;
/*!40000 ALTER TABLE `PURCHASES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUPPLIER`
--

DROP TABLE IF EXISTS `SUPPLIER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SUPPLIER` (
  `SupplierID` int NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Address` varchar(40) NOT NULL,
  `CName` varchar(20) NOT NULL,
  `Phone` char(15) DEFAULT NULL,
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUPPLIER`
--

LOCK TABLES `SUPPLIER` WRITE;
/*!40000 ALTER TABLE `SUPPLIER` DISABLE KEYS */;
INSERT INTO `SUPPLIER` VALUES (8001,'Local','Grommet Builders','788 30th St., SE, Calgary','Fred','403-555-8001'),(8002,'Local','Pong Works','749 Dufferin Blvd., SE, Calgary','Bart','403-555-8002'),(8003,'Local','Wiley Inc.','26 40th St., SE, Calgary','BillyBob','403-555-8003'),(8004,'Local','Winork Manufacturing Inc.','156 51st Ave., SE, Calgary','Marty','403-555-8004'),(8005,'Local','Grab Bag Inc.','138 42nd Ave., NE, Calgary','Monty','403-555-8005'),(8006,'Local','Paddy\'s Manufacturing','311 McCall Way, NE, Calgary','Barney','403-555-8006'),(8007,'Local','Smickles Industries','966 Smed Lane, SE, Calgary','Lisa','403-555-8007'),(8008,'Local','Thangs Inc.','879 37th Ave., NE, Calgary','Thelma','403-555-8008'),(8009,'Local','Flip Works Inc.','472 Ogden Dale Rd., SE, Calgary','Rory','403-555-8009'),(8010,'Local','Irkle Industries','754 Sunridge Way, NE, Calgary','Irma','403-555-8010'),(8011,'Local','Biff Builders','690 19th St., NE, Calgary','Borjn','403-555-8011'),(8012,'Local','Twinkles Inc.','318 29th St.,NE, Calgary','Barkley','403-555-8012'),(8013,'Local','Piddles Industries','238 112th Ave., NE, Calgary','Parnell','403-555-8013'),(8014,'Local','Tic Tac Manufacturing','598 Palmer Rd., NE, Calgary','Teisha','403-555-8014'),(8015,'Local','Knock Knock Inc.','363 42nd Ave., NE, Calgary','Ned','403-555-8015'),(8016,'Local','Corky Things Inc.','35 McCall Way, NE, Calgary','Corrine','403-555-8016'),(8017,'Local','E & O Industries','883 44th St., SE, Calgary','Stan','403-555-8017'),(8018,'Local','Fiddleys Makes Stuff Inc.','350 27th St., NE, Calgary','Fredda','403-555-8018'),(8019,'Local','Horks and Stuff Manufacturing','997 42nd Ave., NE, Calgary','Harold','403-555-8019'),(8020,'International','Fake Company','666 5th St., SE, New York, USA','Jim','403-555-8020'),(8021,'International','Fake LLC','667 5th St., SE, New York, USA','Jon','403-555-8021'),(8022,'International','FakeCorp','668 5th St., SE, New York, USA','Joe','403-555-8022'),(8023,'International','Lex Luthor Industries','669 5th St., SE, New York, USA','Clark','403-555-8023'),(8024,'International','Big Business','670 5th St., SE, New York, USA','Guy','403-555-8024'),(8025,'International','Corporate Sellouts Inc.','671 5th St., SE, New York, USA','Jean','403-555-8025');
/*!40000 ALTER TABLE `SUPPLIER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TOOL`
--

DROP TABLE IF EXISTS `TOOL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TOOL` (
  `ToolID` int NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Type` varchar(15) NOT NULL,
  `Quantity` int DEFAULT NULL,
  `Price` decimal(5,2) DEFAULT NULL,
  `SupplierId` int DEFAULT NULL,
  PRIMARY KEY (`ToolID`),
  KEY `SupplierId` (`SupplierId`),
  CONSTRAINT `tool_ibfk_1` FOREIGN KEY (`SupplierId`) REFERENCES `SUPPLIER` (`SupplierID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TOOL`
--

LOCK TABLES `TOOL` WRITE;
/*!40000 ALTER TABLE `TOOL` DISABLE KEYS */;
INSERT INTO `TOOL` VALUES (1000,'Knock Bits','Electrical',88,12.67,8015),(1001,'Widgets','Electrical',10,35.50,8004),(1002,'Grommets','Electrical',20,23.45,8001),(1003,'Wedges','Electrical',15,10.15,8020),(1004,'Wing Bats','Electrical',11,11.25,8003),(1005,'Twinkies','Electrical',75,15.75,8012),(1006,'Wiggles','Electrical',30,12.34,8020),(1007,'Bing Bobs','Electrical',25,2.39,8005),(1008,'Wog Wits','Electrical',300,19.99,8004),(1009,'Barney Bits','Electrical',21,23.59,8006),(1010,'Willie Widgits','Electrical',89,12.99,8003),(1011,'Barge Bogs','Electrical',35,2.99,8011),(1012,'Poggy Pogs','Electrical',99,1.10,8002),(1013,'Pardle Pins','Electrical',400,0.69,8013),(1014,'Piddley Wicks','Electrical',54,5.19,8013),(1015,'Iggy Orks','Electrical',22,49.95,8010),(1016,'Crank Cribs','Electrical',888,0.29,8005),(1017,'Thingies','Electrical',67,45.98,8008),(1018,'Orf Dappers','Electrical',32,19.98,8018),(1019,'Piff Knocks','Non-Electrical',82,4.95,8002),(1020,'Knit Piks','Non-Electrical',66,6.75,8015),(1021,'Piddley Pins','Non-Electrical',370,0.25,8020),(1022,'Tic Tocs','Non-Electrical',87,1.36,8014),(1023,'Tin Wits','Non-Electrical',23,5.67,8014),(1024,'Thinga-ma-bobs','Non-Electrical',40,10.98,8012),(1025,'Fling Flobs','Non-Electrical',254,2.33,8009),(1026,'Barn Bins','Non-Electrical',45,88.67,8006),(1027,'Flap Wrappers','Non-Electrical',89,44.88,8009),(1028,'Pong Pangs','Non-Electrical',2345,0.10,8002),(1029,'Oof Tongs','Non-Electrical',345,8.49,8011),(1030,'Nic Nacs','Non-Electrical',238,2.99,8015),(1031,'Tork Tins','Non-Electrical',376,0.95,8012),(1032,'Lilly Larks','Non-Electrical',56,12.99,8007),(1033,'Minnie Morks','Non-Electrical',800,1.95,8007),(1034,'Cork Handles','Non-Electrical',654,2.66,8016),(1035,'Ding Darns','Non-Electrical',1208,0.15,8019),(1036,'Erk Orks','Non-Electrical',498,3.99,8017),(1037,'Foo Figs','Non-Electrical',234,5.89,8018),(1038,'Googly Eyes','Non-Electrical',756,6.99,8001),(1039,'Handy Pandies','Non-Electrical',321,4.35,8017),(1041,'FakeTool1','Non-Electrical',50,5.00,8021),(1042,'FakeTool2','Non-Electrical',50,5.00,8022),(1043,'FakeTool3','Non-Electrical',50,5.00,8023),(1044,'FakeTool4','Non-Electrical',50,5.00,8024),(1045,'FakeTool5','Non-Electrical',50,5.00,8025);
/*!40000 ALTER TABLE `TOOL` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-24 18:41:47
