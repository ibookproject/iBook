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
  `summary` varchar(500) NOT NULL,
  `bookEnable` tinyint(2) NOT NULL DEFAULT '1',
  `keyword` varchar(10000) DEFAULT '" "',
  `content` varchar(10000) DEFAULT '" "',
  `numberOfOrder` bigint(45) NOT NULL DEFAULT '0',
  `price` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'kofiko','HEB','j.k. roling','Newly updated to include information for the UK, The Carrot Principle illustrates how ordinary organizations have made themselves extraordinary through the use of strategic employee recognition.',1,'information','information',13,23),(2,'twilight','HEB','Elena maris','The authors show how great organizations and great managers succeed through living the Carrot Principle. Featuring case studies of effective recognition in some of the world\'s most successful organizations, such as DHL, Avis, Pepsi, etc.',1,'managers heb twilight elena','managers heb twilight elena',15,20),(3,'hasod','HEB','Jordan tery',' The book provides examples of leaders - from around the globe - who lead through the Carrot Principle: providing plentiful how-to\'s for managers wishing to get started or hoping to enhance their recognition abilities.',1,'globe tery hasod','hasod jordan books',50,60),(4,'hacoach','HEB','San diego','The book is divided into four chapters, and each chapter serves a different purpose; deals with a different pain; heals a different heartache. ',1,'hacoach heb san diego','hacoach diego chapters',45,70),(5,'Born','ENG','Bruce Springsteen','THE NUMBER ONE BESTSELLER \'Writing about yourself is a funny business...But in a project like this, the writer has made one promise, to show the reader his mind. In these pages, I\'ve tried to do this.\' -Bruce Springsteen, from the pages of Born to Run In 2009, Bruce Springsteen and the E Street Band performed at the Super Bowl\'s halftime show. ',1,'born bruce heb','chepter one bruce',23,80),(6,'Just Kids','ENG','Patti Smith','Just Kids takes readers through a journey of the most bitter moments in life and finds sweetness in them because there is sweetness everywhere ifyou are just willing to look.',1,'ENG Just Kids Patti Smith','Patti  kids Smith',45,96),(7,'Deary','ENG','Toby Little','When Toby Little was five years old, he decided to write to someone in every country in the world. With the help of his mum, Toby started handwriting and posting letters to everyone from research scientists in Antarctica to game-keepers in Chad and even the Pope. Not only did Toby achieve his goal but the world wrote back. Dear World, How Are You? is a collection of the most fascinating and heart-warming letters he sent and received.  ',1,'ENG toby deary','tobe little deary',54,70),(8,'The','ENG','Anne Frank','The definitive 70th anniversary',1,'ENG Anne Frank','run holand anna',50,82),(9,'Being','ENG','Atul Gawande','For most of human history, death was a common.  ',1,'being eng atul','being most',76,50),(10,'Grillstock','SPN','Winston Graham','Part of a small anti-IS activist group, the diaries were written, encrypted and sent to a third country before being translated. ',1,'spn Grillstock Graham','country before being encrypted and sent spn winston',67,0),(11,'Sourdough','ARB','Winston Graham','The diarist\'s father is killed and mother badly injured during an air strike, he is sentenced to 40 lashes for speaking out against a beheading, he sees a woman stoned to death. ',1,'Winston Graham Sourdough woman ',' speaking out against winston graham',67,0),(12,'Cakes','ARB','Paul Wallis','In early 2014, after many years living abroad, Sam Miller returned to his childhood home in London. His father was dying. When the editor, writer, critic and academic Karl Miller died later that year, the obituaries spoke of his brilliance and influence, of how he founded the London Review of Books, and how he had shaped the careers of some of the finest writers and poets of the second half of the twentieth century.',1,'Paul Wallis arb Cakes',' careers of some of the finest ',56,0),(13,'Snakehead','ARB','Daniel Brayshaw','He had been told, long ago, a family secret involving his parents and a close friend. Now, by reading his father\'s papers and with the help of his mother, he was able to piece together a remarkable story.',1,'arb Daniel Brayshaw Snakehead','Snakehead remarkable stor',45,0),(14,'Devonshire','CHN','Sam Lister','Devonshire Fathers is the result: a tender, thoughtful exploration of childhood and parenthood, of friendship, love and loyalty.',1,'Sam Lister CHN Devonshire','exploration of childhood Devonshire',56,0),(15,'Precious','CHN','Daniel Brayshaw',' In Age of Anger, Pankaj Mishra answers our bewilderment by casting his gaze back to the eighteenth century, before leading us to the present. ',1,'Daniel Brayshaw chn Precious',' before leading us eighteenth century',45,0),(16,'Grace','CHN','Paul Wallis',' He shows that as the world became modern those who were unable to fulfil its promises - freedom, stability and prosperity - were increasingly susceptible to demagogues.',1,'chn Paul Wallis Grace','Grace susceptible to demagogues',50,0),(17,'narnita','ENG','Doreen Virtue','for SATB and organ or orchestra This gentle yet uplifting carol was commissioned for the Choir of King\'s College, Cambridge, and was first performed during the 2012 Festival of Nine Lessons and Carols. Rutter\'s gift for melody is in evidence throughout the work, with a sense of hushed awe leading to moments of glorious optimism.',1,'best to know',' fascinating',76,76),(18,'Things','ENG','Ilona Andrews','It is September 1995. Selin, a Turkish-American college freshman from New Jersey, is about to embark on her first year at Harvard University, where she is deter-mined to decipher the mysteries of language and to become a writer.',1,'ENG ilona andrews','glorious',12,96),(19,'Wildfire','ENG','Jodi Picoult','In between studying psycho-linguistics and the philosophy of language, teaching ESL to a Costa Rican plumber, and befriending her classmate Svetlana (a Serbian refugee from Connecticut), Selin falls in love with a Hungarian maths student in her Russian class.',1,'wildfire eng piclott','language jodi eng wildfire',24,45),(20,'Surrender','ENG','Andrzej Sapkowski','Surrender is the summer in the Hungarian countryside teaching English to village children, where sad and comic misunderstandings ensue.',1,'surrender eng sapkowski','village children eng surrender',25,45),(21,'Shattered','RUS','Sui Ishida','Full of the razor-sharp evocations of character and place that have long delighted readers of Batuman\'s non-fiction, The Idiot tackles literary ambition, female friend-ship, the American dream, Chomskian linguistics, the Russian novel and romantic love.',1,'ENG ishida rus shattered','Chomskian linguistics sui shattered',56,67),(22,'Caraval','RUS','Stephanie Garber','Discover the unforgettable novel picked among 2017\'s most anticipated books by the Guardian, Daily Telegraph, Buzzfeed, New York Times and many more.',1,'rus stephani caraval','caraval New York Times and many sui',36,69),(23,'Burn','RUS','Helen Hardt','An extraordinary story of love and hope, travelling from the Middle East to London and beyond, from the bestselling, Man Booker-shortlisted author of The Reluctant Fundamentalist Nadia and Saeed are two ordinary young .',1,'rus Helen Hardt burn','two ordinary young hardt',64,69),(24,'Heartless','SPN','Marissa Meyer','Heartless Theirs will be a love story but also a story about how we live now and how we might live tomorrow, of a world in crisis and two human beings travelling through it.',1,'spn Heartless marissa meyer','travelling marissa meyer heartless',35,69),(25,'Wild','SPN','Anne Bishop','They will join the great outpouring of people fleeing a collapsing city, hoping against hope, looking for their place in the world .',1,'spn Anne Bishop Wild old cool amazing',' looking for their place spn wild',75,69),(26,'Hexed','SPN','Anne Bishop',' The Raqqa Diaries began as a series of short broadcasts on Radio 4\'s \'Today\' Programme. Now one of the most isolated and fear ridden cities on earth, no-one is allowed to speak to western journalists or leave Raqqa, without IS\'s permission. ',1,'spn Anne Bishop Hexed','search  leave Raqqa',37,45),(27,'Magics','SPN','Anne Bishop',' Those caught breaking the rules face death by beheading. Despite this, Mike Thomson, with the help of BBC\'s Arabic Service, found a young man who is willing to risk his life to tell the world what is happening in his city. ',1,'spn Magics world willing','happening in his city anna spn magic',47,50),(28,'Demelza','CHN','Daniel Brayshaw',' The many who came late to this new world or were left, or pushed, behind, reacted in horrifyingly similar ways: intense hatred ',1,'Daniel Brayshaw chn Demelza','horrifyingly similar ways Daniel Brayshaw',96,0),(29,'Heat','HEB','Sam Lister','It was from among the ranks of the disaffected that the militants of the 19th century arose - angry young men who became cultural nationalists in Germany, messianic revolutionaries in Russia, bellicose chauvinists in Italy, and anarchist terrorists internationally. ',1,'Heat heb Sam Lister old fun coll sweet','bellicose chauvinists in Italy anarchist terrorists internationally',24,0),(30,'Principles','HEB','Martin Evans',' Today, just as then, the wider embrace of mass politics, technology, and the pursuit of wealth and individualism has cast many more millions adrift in a literally demoralized world.',1,'Martin Evans heb Principles','pursuit of wealth and individualism millions adrift in a literally',34,0);
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
  `status` int(11) NOT NULL DEFAULT '0',
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

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('11',3,1,'2016-02-02'),('11',4,1,'2016-12-12'),('12',1,1,'2016-08-09'),('12',2,1,'2016-08-09'),('12',5,1,'2015-11-11'),('12',6,1,'2016-07-04'),('12',7,1,'2016-08-09'),('13',1,1,'2016-08-09'),('13',2,1,'2016-08-09'),('13',3,1,'2016-08-09'),('14',1,1,'2015-11-11'),('15',5,0,'2017-01-21'),('15',8,0,'2017-01-16'),('15',23,0,'2017-01-15'),('16',1,0,'2017-01-22'),('16',5,0,'2017-01-10'),('16',6,0,'2017-01-09'),('16',9,0,'2017-01-08'),('16',24,0,'2017-01-22'),('17',23,1,'2015-03-12'),('17',24,1,'2017-01-13'),('17',25,1,'2016-02-07'),('18',1,1,'2017-01-14'),('18',2,1,'2016-02-03'),('18',3,1,'2014-05-06'),('18',27,1,'2014-09-08'),('18',28,1,'2015-03-12'),('19',4,1,'2015-03-12'),('19',14,1,'2016-02-07'),('19',15,1,'2016-06-16'),('19',18,1,'2016-06-16');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domain`
--

LOCK TABLES `domain` WRITE;
/*!40000 ALTER TABLE `domain` DISABLE KEYS */;
INSERT INTO `domain` VALUES (1,'nature'),(2,'science'),(3,'madab'),(4,'Biography'),(5,'guide'),(6,'children'),(7,'health'),(8,'lifestyle'),(9,'fiction'),(10,'crime');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libraryworker`
--

LOCK TABLES `libraryworker` WRITE;
/*!40000 ALTER TABLE `libraryworker` DISABLE KEYS */;
INSERT INTO `libraryworker` VALUES (3,'3','library worker','3@gmail.com'),(4,'4','library worker','4@gmail.com'),(5,'5','library worker','5@gmail.com'),(6,'6','manager','6@gmail.com');
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
  `reviewStatus` int(11) NOT NULL DEFAULT '0',
  `bookID` int(11) NOT NULL,
  `userSign` varchar(9) NOT NULL,
  PRIMARY KEY (`reviewID`,`bookID`),
  KEY `_bookID_idx` (`bookID`),
  CONSTRAINT `reviewToBookID` FOREIGN KEY (`bookID`) REFERENCES `book` (`bookID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'2016-05-09','Good summary!',0,1,'1'),(2,'2016-05-04','excellent author',0,1,'2'),(3,'2015-06-07','One of the finest books made in recent years.',0,1,'4'),(4,'2016-05-06','He shines when it is his time to go back into the world.',0,2,'5'),(5,'2015-06-08','This is simply one of the best books ever made and I know.',1,3,'6'),(6,'2016-05-09','very boring book!',0,4,'7'),(7,'2015-06-07','awsome!!',0,4,'8'),(8,'2016-07-06','I have never seen such an amazing book since I saw The Shawshank Redemption.',0,12,'1'),(9,'2017-01-21','What makes this book one of the best ever made is the message it conveys.',0,1,'2'),(10,'2016-04-03','It is a simple book, yet it has an everlasting message.',0,14,'3'),(11,'2016-04-03','Set aside a little over two hours tonight and buy this book.',0,15,'4'),(12,'2015-04-23','Can Hollywood, usually creating things for entertainment purposes only, create art?',0,17,'5'),(13,'2016-04-03','While maintaining some of the poetic and moving dialogue of the novella',0,19,'6'),(14,'2015-11-15','The story begins with the trial of a young banker, Andy Dufrense, victimized by circumstantial evidence',0,18,'1'),(15,'2015-11-15','With work from vast array of talented scene designers, costume designers, composers, cinematographers.',0,12,'4'),(16,'2015-12-16','This book manages to redeem Hollywood in the eyes of people who feared it long lost in a dark sea of clichés and predictability.',0,12,'5'),(17,'2015-12-22','Firstly, its setting. The opening aerial shots of the prison are a total eye-opener. This is an amazing piece of architecture, strong and Gothic in design. Immediately, the prison becomes a character.',0,12,'6'),(18,'2015-11-28','With themes of faith and hope, there is a definite religious subtext to be found here.',0,12,'4'),(19,'2016-04-03','The reason I became a member of this database is because I finally found a book ranking that recognized the true greatness of this book.',0,1,'3'),(20,'2015-11-15','I was so impressed with how every single subplot was given a great deal of respect and attention from the author.',0,1,'2'),(21,'2015-01-09','One of my all time favorites.',0,1,'6'),(22,'2014-10-12','If you haven\'t read this book,GO AND buy IT NOW. ',0,3,'5'),(23,'2014-05-04','It is the story of Andy Dufresne, played by Tim Robbins and his time at Shawshank Prison. It is a perspective of what it is to be human and to have hope for the future even in the face of insurmountable adversity. ',0,23,'2'),(24,'2014-09-08','If there is a book by which other books should be judged.',0,25,'3'),(25,'2016-04-03','I don\'t know how to write a good book review so I guess telling you what I feel about it may do better.',0,27,'1'),(26,'2015-11-15','What I don`t like about it is the amount of cliches.',0,26,'1'),(27,'2014-10-12','There`s also some outstanding touches from author.',0,26,'5');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searchtobook`
--

DROP TABLE IF EXISTS `searchtobook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchtobook` (
  `bookID` int(11) NOT NULL,
  `SearchDate` date NOT NULL,
  `numberOfSearches` bigint(45) NOT NULL DEFAULT '0',
  PRIMARY KEY (`bookID`,`SearchDate`),
  CONSTRAINT `SbookID` FOREIGN KEY (`bookID`) REFERENCES `book` (`bookID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchtobook`
--

LOCK TABLES `searchtobook` WRITE;
/*!40000 ALTER TABLE `searchtobook` DISABLE KEYS */;
INSERT INTO `searchtobook` VALUES (1,'2016-01-09',3),(1,'2016-07-07',1),(2,'2016-07-09',2),(3,'2015-07-08',1),(4,'2015-04-05',7),(5,'2014-04-04',9),(6,'2015-07-08',23),(7,'2016-07-12',10),(8,'2016-07-14',32),(8,'2016-08-09',32),(8,'2017-01-03',33),(9,'2014-04-04',45),(9,'2015-07-08',43),(9,'2016-08-11',44),(10,'2016-02-23',11),(17,'2014-04-04',5),(17,'2015-07-08',4),(20,'2017-01-01',5),(21,'2014-04-04',3),(22,'2015-07-08',4),(23,'2017-01-01',5),(25,'2017-01-02',5),(29,'2015-06-29',6);
/*!40000 ALTER TABLE `searchtobook` ENABLE KEYS */;
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

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'animals'),(1,'dogs'),(1,'flowers'),(2,'brain'),(2,'umanBuddy'),(3,'space'),(4,'people'),(4,'world'),(5,'Challenge Guide'),(5,'Dining Guide'),(5,'Fitness Guide'),(5,'Nature Guide'),(6,'songs'),(6,'tree'),(7,'personal advice'),(8,'Conformal'),(8,'Innovative'),(8,'recessive'),(9,'world'),(10,'culprits'),(10,'thiefs');
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

LOCK TABLES `subjecttobook` WRITE;
/*!40000 ALTER TABLE `subjecttobook` DISABLE KEYS */;
INSERT INTO `subjecttobook` VALUES ('animals',1,1),('Innovative',8,2),('personal advice',7,2),('umanBuddy',2,2),('world',9,3),('animals',1,4),('umanBuddy',2,4),('culprits',10,5),('people',4,5),('personal advice',7,5),('songs',6,5),('Innovative',8,6),('people',4,6),('songs',6,6),('world',4,7),('flowers',1,8),('world',4,8),('flowers',1,9),('personal advice',7,9),('flowers',1,17),('personal advice',7,17),('dogs',1,18),('Innovative',8,18),('dogs',1,19),('recessive',8,19),('recessive',8,20),('recessive',8,25),('culprits',10,29),('Conformal',8,30);
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
  `identityNumber` varchar(9) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `userStatus` int(10) NOT NULL DEFAULT '0',
  `subscriptionMethod` int(10) DEFAULT NULL,
  `privilege` int(11) NOT NULL DEFAULT '1',
  `subscriptionRequest` int(11) DEFAULT '0',
  `finishDateOfSubscription` date DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','1','123456789','a','a',0,0,1,0,'2017-05-05'),('10','kate111','123456789','Katerina','Kornblat',0,0,6,0,'2017-01-12'),('11','sagi111','111111111','Sagi','Entenberg',0,0,1,0,'2015-05-06'),('12','coral222','222222222','Coral','Carmeli',0,1,2,0,'2017-01-14'),('13','hen333','333333333','Hen','Saada',0,0,3,0,'2017-01-11'),('14','almog444','444444444','Almog','Yamin',0,0,4,0,'2016-12-12'),('15','kfir555','555555555','Kfir','Girshtain',0,2,5,0,'2016-11-12'),('16','avi6666','666666666','Avi','Sofer',0,0,6,0,'2017-05-12'),('17','mal777','777777777','Malci','Grosman',0,3,6,0,'2016-04-13'),('18','sha888','888888888','Sharon','Tidhar',0,0,1,0,'2016-09-06'),('19','sergei999','999999999','Sergei','Mazin',0,1,1,0,'2016-07-08');
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

-- Dump completed on 2017-01-24 21:57:37
