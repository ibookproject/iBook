CREATE DATABASE  IF NOT EXISTS `ibook` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ibook`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ibook
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `bookID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `language` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `summary` varchar(45) NOT NULL,
  `bookEnable` tinyint(1) NOT NULL DEFAULT '1',
  `keyword` varchar(10000) DEFAULT '" "',
  `content` varchar(10000) DEFAULT '" "',
  PRIMARY KEY (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--
-- ORDER BY:  `bookID`

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`bookID`, `title`, `language`, `author`, `summary`, `bookEnable`, `keyword`, `content`) VALUES (1,'kofiko','HEB','j.k. roling','g',1,NULL,NULL),(2,'kofiko','HEB','j.k. roling','f',1,NULL,NULL),(3,'kofiko','HEB','j.k. roling','e',1,NULL,NULL),(4,'kofiko','HEB','j.k. roling','d',1,NULL,NULL),(5,'kofiko','HEB','j.k. roling','c',1,NULL,NULL),(6,'kofiko','HEB','j.k. roling','b',1,NULL,NULL),(7,'kofiko','HEB','j.k. roling','a',1,NULL,NULL),(8,'kofiko','HEB','j.k. roling','k',1,NULL,NULL),(9,'kofiko','HEB','j.k. roling','t',1,NULL,NULL),(17,'narnita','ENG','j.k. roling','best to know',1,NULL,NULL),(18,'narnita','ENG','j.k. roling','best to know',1,NULL,NULL),(19,'narnita','ENG','j.k. roling','best to know',1,NULL,NULL),(20,'narnita','ENG','j.k. roling','best to know',1,NULL,NULL),(21,'narnita','ENG','j.k. roling','best to know',1,NULL,NULL),(22,'kofiko','HEB','j.k. roling','best to know',1,NULL,NULL),(23,'kofiko','HEB','j.k. roling','best to know',1,NULL,NULL),(24,'2','2','2','2',1,'\" \"','\" \"'),(25,'go','heb','sagi','ds',1,'\" \"','\" \"'),(26,'','','','',1,'\" \"','\" \"');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `userID` varchar(45) NOT NULL,
  `bookID` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `price` float NOT NULL,
  `buyDate` date DEFAULT NULL,
  PRIMARY KEY (`userID`,`bookID`),
  KEY `bookID_idx` (`bookID`),
  CONSTRAINT `bookID` FOREIGN KEY (`bookID`) REFERENCES `book` (`bookID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--
-- ORDER BY:  `userID`,`bookID`

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domain`
--

DROP TABLE IF EXISTS `domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domain` (
  `domainID` int(10) NOT NULL AUTO_INCREMENT,
  `domainName` varchar(255) NOT NULL,
  PRIMARY KEY (`domainID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domain`
--
-- ORDER BY:  `domainID`

LOCK TABLES `domain` WRITE;
/*!40000 ALTER TABLE `domain` DISABLE KEYS */;
INSERT INTO `domain` (`domainID`, `domainName`) VALUES (1,'23'),(2,'543');
/*!40000 ALTER TABLE `domain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libraryworker`
--

DROP TABLE IF EXISTS `libraryworker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libraryworker` (
  `workerID` bigint(19) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL DEFAULT '1',
  `department` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`workerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libraryworker`
--
-- ORDER BY:  `workerID`

LOCK TABLES `libraryworker` WRITE;
/*!40000 ALTER TABLE `libraryworker` DISABLE KEYS */;
/*!40000 ALTER TABLE `libraryworker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `reviewID` int(11) NOT NULL AUTO_INCREMENT,
  `reviewDate` date NOT NULL,
  `reviewContent` varchar(20000) NOT NULL,
  `reviewStatus` tinyint(1) NOT NULL DEFAULT '0',
  `bookID` int(11) NOT NULL,
  PRIMARY KEY (`reviewID`,`bookID`),
  KEY `_bookID_idx` (`bookID`),
  CONSTRAINT `reviewToBookID` FOREIGN KEY (`bookID`) REFERENCES `book` (`bookID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--
-- ORDER BY:  `reviewID`,`bookID`

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` (`reviewID`, `reviewDate`, `reviewContent`, `reviewStatus`, `bookID`) VALUES (1,'2016-09-01','best book ',0,1),(2,'2016-09-01','nice book',0,2),(3,'2016-09-01','bad writer',0,3),(4,'2016-09-01','good summery',0,4),(5,'2016-09-01','good autor',0,5),(6,'2016-09-01','nuce book',0,6),(7,'2016-09-01','shit a book',0,7),(8,'2016-09-01','good book',0,8);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `domainID` int(11) NOT NULL,
  `nameSubject` varchar(255) NOT NULL,
  PRIMARY KEY (`nameSubject`,`domainID`),
  KEY `domainTosubject_idx` (`domainID`),
  CONSTRAINT `domainTosubject` FOREIGN KEY (`domainID`) REFERENCES `domain` (`domainID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--
-- ORDER BY:  `nameSubject`,`domainID`

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` (`domainID`, `nameSubject`) VALUES (1,'kjhgfd'),(2,'tre');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjecttobook`
--

DROP TABLE IF EXISTS `subjecttobook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjecttobook` (
  `nameSubject` varchar(255) NOT NULL,
  `domainID` int(11) NOT NULL,
  `bookID` int(11) NOT NULL,
  PRIMARY KEY (`nameSubject`,`domainID`,`bookID`),
  KEY `bookID_idx` (`bookID`),
  KEY `DomainID_idx` (`domainID`),
  CONSTRAINT `_DomainID` FOREIGN KEY (`domainID`) REFERENCES `domain` (`domainID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `_bookID` FOREIGN KEY (`bookID`) REFERENCES `book` (`bookID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `_nameSubject` FOREIGN KEY (`nameSubject`) REFERENCES `subject` (`nameSubject`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjecttobook`
--
-- ORDER BY:  `nameSubject`,`domainID`,`bookID`

LOCK TABLES `subjecttobook` WRITE;
/*!40000 ALTER TABLE `subjecttobook` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjecttobook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `userStatus` int(10) NOT NULL DEFAULT '0',
  `subscriptionMethod` int(10) DEFAULT NULL,
  `privilege` int(11) NOT NULL DEFAULT '1',
  `suscriptionRequset` int(11) DEFAULT '0',
  `finishDateOfSubscription` date DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--
-- ORDER BY:  `userID`

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userID`, `password`, `firstName`, `lastName`, `userStatus`, `subscriptionMethod`, `privilege`, `suscriptionRequset`, `finishDateOfSubscription`) VALUES ('1','1','sagi','entenberg',0,0,1,0,NULL),('2','2','coral','carmeli',0,0,2,0,NULL),('3','3','hen','saada',0,0,3,0,NULL),('4','4','almog','yamin',0,0,4,0,NULL),('5','5','kfir','girshtain',0,0,5,0,NULL),('6','6','avi','sofer',0,0,6,0,NULL),('8','8','8','8',0,NULL,1,0,NULL),('9','86','6','6',0,NULL,1,0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-10  2:38:38
