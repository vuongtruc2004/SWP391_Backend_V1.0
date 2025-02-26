-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: online-learning-uat
-- ------------------------------------------------------
-- Server version	8.4.2
create database `online-learning-uat`;
use `online-learning-uat`;

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `answer_id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `correct` bit(1) DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK3erw1a3t0r78st8ty27x6v3g1` (`question_id`),
  CONSTRAINT `FK3erw1a3t0r78st8ty27x6v3g1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (1,'Câu trả lời D',_binary '',1);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (2,'Câu trả lời F',_binary '',1);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (3,'Câu trả lời E',_binary '',1);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (4,'Câu trả lời C',_binary '',1);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (5,'Câu trả lời A',_binary '',1);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (6,'Câu trả lời B',_binary '',1);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (7,'Câu trả lời A',_binary '\0',2);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (8,'Câu trả lời B',_binary '\0',2);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (9,'Câu trả lời D',_binary '\0',2);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (10,'Câu trả lời E',_binary '\0',2);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (11,'Câu trả lời F',_binary '\0',2);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (12,'Câu trả lời C',_binary '\0',2);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (13,'Câu trả lời A',_binary '\0',3);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (14,'Câu trả lời F',_binary '\0',3);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (15,'Câu trả lời B',_binary '\0',3);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (16,'Câu trả lời D',_binary '\0',3);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (17,'Câu trả lời E',_binary '\0',3);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (18,'Câu trả lời C',_binary '\0',3);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (19,'Câu trả lời C',_binary '',4);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (20,'Câu trả lời A',_binary '',4);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (21,'Câu trả lời D',_binary '',4);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (22,'Câu trả lời F',_binary '',4);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (23,'Câu trả lời B',_binary '',4);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (24,'Câu trả lời E',_binary '',4);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (25,'Câu trả lời B',_binary '',5);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (26,'Câu trả lời F',_binary '',5);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (27,'Câu trả lời C',_binary '',5);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (28,'Câu trả lời D',_binary '',5);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (29,'Câu trả lời E',_binary '',5);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (30,'Câu trả lời A',_binary '',5);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (31,'Câu trả lời C',_binary '',6);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (32,'Câu trả lời A',_binary '',6);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (33,'Câu trả lời B',_binary '',6);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (34,'Câu trả lời F',_binary '',6);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (35,'Câu trả lời E',_binary '',6);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (36,'Câu trả lời D',_binary '',6);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (37,'Câu trả lời B',_binary '\0',7);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (38,'Câu trả lời E',_binary '\0',7);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (39,'Câu trả lời A',_binary '\0',7);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (40,'Câu trả lời F',_binary '\0',7);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (41,'Câu trả lời C',_binary '\0',7);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (42,'Câu trả lời D',_binary '\0',7);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (43,'Câu trả lời A',_binary '\0',8);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (44,'Câu trả lời B',_binary '\0',8);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (45,'Câu trả lời E',_binary '\0',8);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (46,'Câu trả lời D',_binary '\0',8);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (47,'Câu trả lời C',_binary '\0',8);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (48,'Câu trả lời F',_binary '\0',8);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (49,'Câu trả lời D',_binary '',9);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (50,'Câu trả lời E',_binary '',9);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (51,'Câu trả lời B',_binary '',9);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (52,'Câu trả lời C',_binary '',9);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (53,'Câu trả lời F',_binary '',9);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (54,'Câu trả lời A',_binary '',9);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (55,'Câu trả lời E',_binary '\0',10);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (56,'Câu trả lời B',_binary '\0',10);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (57,'Câu trả lời A',_binary '\0',10);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (58,'Câu trả lời C',_binary '\0',10);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (59,'Câu trả lời F',_binary '\0',10);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (60,'Câu trả lời D',_binary '\0',10);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (61,'Câu trả lời D',_binary '',11);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (62,'Câu trả lời A',_binary '',11);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (63,'Câu trả lời E',_binary '',11);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (64,'Câu trả lời C',_binary '',11);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (65,'Câu trả lời F',_binary '',11);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (66,'Câu trả lời B',_binary '',11);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (67,'Câu trả lời C',_binary '',12);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (68,'Câu trả lời A',_binary '',12);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (69,'Câu trả lời E',_binary '',12);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (70,'Câu trả lời D',_binary '',12);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (71,'Câu trả lời B',_binary '',12);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (72,'Câu trả lời F',_binary '',12);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (73,'Câu trả lời E',_binary '\0',13);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (74,'Câu trả lời D',_binary '\0',13);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (75,'Câu trả lời C',_binary '\0',13);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (76,'Câu trả lời B',_binary '\0',13);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (77,'Câu trả lời F',_binary '\0',13);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (78,'Câu trả lời A',_binary '\0',13);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (79,'Câu trả lời B',_binary '',14);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (80,'Câu trả lời E',_binary '',14);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (81,'Câu trả lời F',_binary '',14);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (82,'Câu trả lời D',_binary '',14);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (83,'Câu trả lời C',_binary '',14);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (84,'Câu trả lời A',_binary '',14);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (85,'Câu trả lời C',_binary '',15);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (86,'Câu trả lời F',_binary '',15);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (87,'Câu trả lời B',_binary '',15);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (88,'Câu trả lời A',_binary '',15);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (89,'Câu trả lời D',_binary '',15);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (90,'Câu trả lời E',_binary '',15);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (91,'Câu trả lời A',_binary '\0',16);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (92,'Câu trả lời F',_binary '\0',16);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (93,'Câu trả lời B',_binary '\0',16);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (94,'Câu trả lời E',_binary '\0',16);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (95,'Câu trả lời C',_binary '\0',16);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (96,'Câu trả lời D',_binary '\0',16);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (97,'Câu trả lời F',_binary '\0',17);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (98,'Câu trả lời D',_binary '\0',17);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (99,'Câu trả lời C',_binary '\0',17);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (100,'Câu trả lời E',_binary '\0',17);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (101,'Câu trả lời B',_binary '\0',17);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (102,'Câu trả lời A',_binary '\0',17);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (103,'Câu trả lời F',_binary '\0',18);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (104,'Câu trả lời E',_binary '\0',18);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (105,'Câu trả lời B',_binary '\0',18);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (106,'Câu trả lời C',_binary '\0',18);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (107,'Câu trả lời A',_binary '\0',18);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (108,'Câu trả lời D',_binary '\0',18);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (109,'Câu trả lời E',_binary '\0',19);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (110,'Câu trả lời C',_binary '\0',19);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (111,'Câu trả lời A',_binary '\0',19);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (112,'Câu trả lời F',_binary '\0',19);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (113,'Câu trả lời B',_binary '\0',19);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (114,'Câu trả lời D',_binary '\0',19);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (115,'Câu trả lời F',_binary '\0',20);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (116,'Câu trả lời E',_binary '\0',20);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (117,'Câu trả lời A',_binary '\0',20);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (118,'Câu trả lời D',_binary '\0',20);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (119,'Câu trả lời B',_binary '\0',20);
INSERT INTO `answers` (`answer_id`, `content`, `correct`, `question_id`) VALUES (120,'Câu trả lời C',_binary '\0',20);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_tag`
--

DROP TABLE IF EXISTS `blog_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_tag` (
  `blog_id` bigint NOT NULL,
  `tag_id` bigint NOT NULL,
  PRIMARY KEY (`blog_id`,`tag_id`),
  KEY `FK3c6t9f1bjwx4qic9vwi0kbqos` (`tag_id`),
  CONSTRAINT `FK3c6t9f1bjwx4qic9vwi0kbqos` FOREIGN KEY (`tag_id`) REFERENCES `hashtags` (`tag_id`),
  CONSTRAINT `FKdl2hedc2u6i0kw0q5lg31ipmw` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_tag`
--

LOCK TABLES `blog_tag` WRITE;
/*!40000 ALTER TABLE `blog_tag` DISABLE KEYS */;
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (6,1);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (9,1);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (1,2);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (7,2);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (8,2);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (13,2);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (4,3);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (7,3);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (10,3);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (11,4);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (15,4);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (3,5);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (4,5);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (10,5);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (4,6);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (6,6);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (9,6);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (13,6);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (13,7);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (15,7);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (7,8);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (8,8);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (11,8);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (14,8);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (15,8);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (1,9);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (9,9);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (12,9);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (14,9);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (2,10);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (5,10);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (14,11);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (6,12);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (7,12);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (9,12);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (5,13);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (6,13);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (11,13);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (13,13);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (2,14);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (4,14);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (5,14);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (10,14);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (13,14);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (15,14);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (3,15);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (4,15);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (9,15);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (15,15);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (5,16);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (9,16);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (10,16);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (14,16);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (3,17);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (11,17);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (13,17);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (14,17);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (15,17);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (6,18);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (14,18);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (7,19);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (11,19);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (14,19);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (2,20);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (5,21);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (5,23);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (1,24);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (2,24);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (5,25);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (8,25);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (9,25);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (2,26);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (15,26);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (4,27);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (9,27);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (3,28);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (6,28);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (8,28);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (10,28);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (12,28);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (8,29);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (10,29);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (12,29);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (15,29);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (9,30);
INSERT INTO `blog_tag` (`blog_id`, `tag_id`) VALUES (12,30);
/*!40000 ALTER TABLE `blog_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogs`
--

DROP TABLE IF EXISTS `blogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogs` (
  `blog_id` bigint NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `created_at` datetime(6) DEFAULT NULL,
  `pinned` bit(1) DEFAULT NULL,
  `plain_content` longtext,
  `published` bit(1) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`blog_id`),
  KEY `FKpg4damav6db6a6fh5peylcni5` (`user_id`),
  CONSTRAINT `FKpg4damav6db6a6fh5peylcni5` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogs`
--

LOCK TABLES `blogs` WRITE;
/*!40000 ALTER TABLE `blogs` DISABLE KEYS */;
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (1,'<p>Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? <strong>Bài viết này</strong> sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend.</p><p>Bạn sẽ học <em>HTML, CSS, JavaScript, React</em> và <strong>Spring Boot</strong> để xây dựng website chuyên nghiệp.</p>','2025-02-14 10:56:50.910793',_binary '','Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? Bài viết này sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend. Bạn sẽ học HTML, CSS, JavaScript, React và Spring Boot để xây dựng website chuyên nghiệp.',_binary '','1.jpg','Lập Trình Web Từ A Đến Z: Hướng Dẫn Chi Tiết',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (2,'<p>Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. <strong>Bài viết này</strong> sẽ chỉ ra những lỗi đó và cách khắc phục.</p><p>Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.</p>','2025-02-14 10:56:50.911793',_binary '\0','Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. Bài viết này sẽ chỉ ra những lỗi đó và cách khắc phục. Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.',_binary '\0','2.jpg','Những Sai Lầm Cần Tránh Khi Học Lập Trình',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (3,'<p>Công cụ phù hợp giúp lập trình viên làm việc nhanh chóng và hiệu quả hơn. <strong>Dưới đây</strong> là danh sách 10 công cụ không thể thiếu.</p><ul><li>VS Code</li><li>Postman</li><li>Docker</li></ul>','2025-02-14 10:56:50.912792',_binary '\0','Công cụ phù hợp giúp lập trình viên làm việc nhanh chóng và hiệu quả hơn. Dưới đây là danh sách 10 công cụ không thể thiếu. VS Code Postman Docker',_binary '','3.jpg','Top 10 Công Cụ Hữu Ích Cho Lập Trình Viên',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (4,'<p><strong>Git và GitHub</strong> giúp lập trình viên quản lý mã nguồn và làm việc nhóm hiệu quả. Bài viết này sẽ hướng dẫn bạn từ cơ bản đến nâng cao.</p>','2025-02-14 10:56:50.912792',_binary '\0','Git và GitHub giúp lập trình viên quản lý mã nguồn và làm việc nhóm hiệu quả. Bài viết này sẽ hướng dẫn bạn từ cơ bản đến nâng cao.',_binary '','4.jpg','Làm Chủ Git Và GitHub: Hướng Dẫn Dành Cho Người Mới',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (5,'<p><strong>Full-stack developer</strong> là một trong những vị trí hot nhất. Bài viết này giúp bạn nắm vững kỹ năng cần thiết và lộ trình học tập hiệu quả.</p>','2025-02-14 10:56:50.913792',_binary '\0','Full-stack developer là một trong những vị trí hot nhất. Bài viết này giúp bạn nắm vững kỹ năng cần thiết và lộ trình học tập hiệu quả.',_binary '\0','5.jpg','Làm Sao Để Trở Thành Lập Trình Viên Full-Stack?',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (6,'<p>Viết code không chỉ để máy tính hiểu, mà còn giúp đồng đội dễ đọc. Hãy áp dụng các nguyên tắc như <strong>DRY</strong> và <em>KISS</em> để tối ưu mã nguồn.</p>','2025-02-14 10:56:50.913792',_binary '\0','Viết code không chỉ để máy tính hiểu, mà còn giúp đồng đội dễ đọc. Hãy áp dụng các nguyên tắc như DRY và KISS để tối ưu mã nguồn.',_binary '','6.jpg','Cách Viết Code Sạch Và Dễ Bảo Trì',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (7,'<p>JavaScript là ngôn ngữ lập trình phổ biến nhất hiện nay. <strong>Bài viết này</strong> sẽ cung cấp cho bạn lộ trình học từ cơ bản đến nâng cao.</p>','2025-02-14 10:56:50.914792',_binary '\0','JavaScript là ngôn ngữ lập trình phổ biến nhất hiện nay. Bài viết này sẽ cung cấp cho bạn lộ trình học từ cơ bản đến nâng cao.',_binary '','7.jpg','Học JavaScript Trong 30 Ngày: Lộ Trình Chi Tiết',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (8,'<p>Cấu trúc dữ liệu và thuật toán là một phần quan trọng trong lập trình. <strong>Bài viết này</strong> sẽ giúp bạn hiểu cách áp dụng chúng vào thực tế.</p>','2025-02-14 10:56:50.915792',_binary '\0','Cấu trúc dữ liệu và thuật toán là một phần quan trọng trong lập trình. Bài viết này sẽ giúp bạn hiểu cách áp dụng chúng vào thực tế.',_binary '','8.jpg','Lập Trình Viên Có Cần Học Data Structures & Algorithms?',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (9,'<p><strong>Python, JavaScript, Go</strong> và <em>Rust</em> là những ngôn ngữ có xu hướng phát triển mạnh trong năm tới.</p>','2025-02-14 10:56:50.915792',_binary '\0','Python, JavaScript, Go và Rust là những ngôn ngữ có xu hướng phát triển mạnh trong năm tới.',_binary '','9.jpg','Những Ngôn Ngữ Lập Trình Phổ Biến Năm 2025',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (10,'<p>Chọn <strong>React Native</strong> nếu bạn thích JavaScript, hoặc <strong>Flutter</strong> nếu bạn muốn hiệu suất cao với Dart.</p>','2025-02-14 10:56:50.916792',_binary '\0','Chọn React Native nếu bạn thích JavaScript, hoặc Flutter nếu bạn muốn hiệu suất cao với Dart.',_binary '','10.jpg','Phát Triển Ứng Dụng Mobile: Nên Chọn React Native Hay Flutter?',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (11,'<p>Học lập trình cần kiên nhẫn và thực hành liên tục. <strong>Bạn nên</strong> đặt mục tiêu rõ ràng, làm bài tập thực tế và tham gia dự án nhóm.</p>','2025-02-14 10:56:50.917793',_binary '\0','Học lập trình cần kiên nhẫn và thực hành liên tục. Bạn nên đặt mục tiêu rõ ràng, làm bài tập thực tế và tham gia dự án nhóm.',_binary '','11.jpg','Bí Quyết Học Lập Trình Hiệu Quả Cho Người Mới Bắt Đầu',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (12,'<p><strong>DevOps</strong> giúp cải thiện quy trình phát triển phần mềm, kết hợp <strong>CI/CD</strong> để tự động hóa triển khai.</p>','2025-02-14 10:56:50.917793',_binary '\0','DevOps giúp cải thiện quy trình phát triển phần mềm, kết hợp CI/CD để tự động hóa triển khai.',_binary '','12.jpg','Tìm Hiểu Về DevOps Và CI/CD',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (13,'<p>Để thành công trong lĩnh vực lập trình, bạn cần kỹ năng giải quyết vấn đề, giao tiếp và học hỏi liên tục.</p>','2025-02-14 10:56:50.918792',_binary '\0','Để thành công trong lĩnh vực lập trình, bạn cần kỹ năng giải quyết vấn đề, giao tiếp và học hỏi liên tục.',_binary '\0','13.jpg','Các Kỹ Năng Cần Có Để Trở Thành Lập Trình Viên Thành Công',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (14,'<p>Hiểu về <strong>RESTful API</strong> và <em>WebSocket</em> sẽ giúp bạn thiết kế các dịch vụ web hiệu quả hơn.</p>','2025-02-14 10:56:50.918792',_binary '\0','Hiểu về RESTful API và WebSocket sẽ giúp bạn thiết kế các dịch vụ web hiệu quả hơn.',_binary '','14.jpg','Điều Quan Trọng Cần Biết Khi Làm Việc Với API',NULL,1);
INSERT INTO `blogs` (`blog_id`, `content`, `created_at`, `pinned`, `plain_content`, `published`, `thumbnail`, `title`, `updated_at`, `user_id`) VALUES (15,'<p><strong>Scrum</strong> là phương pháp Agile phổ biến để phát triển phần mềm, giúp cải thiện năng suất và đảm bảo chất lượng.</p>','2025-02-14 10:56:50.919792',_binary '\0','Scrum là phương pháp Agile phổ biến để phát triển phần mềm, giúp cải thiện năng suất và đảm bảo chất lượng.',_binary '','15.jpg','Phát Triển Phần Mềm Với Scrum: Lợi Ích Và Thực Hành',NULL,1);
/*!40000 ALTER TABLE `blogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campaigns`
--

DROP TABLE IF EXISTS `campaigns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaigns` (
  `campaign_id` bigint NOT NULL AUTO_INCREMENT,
  `campaign_description` varchar(255) DEFAULT NULL,
  `campaign_name` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `discount_percentage` double DEFAULT NULL,
  `discount_range` tinyint DEFAULT NULL,
  `discount_type` tinyint DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`campaign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaigns`
--

LOCK TABLES `campaigns` WRITE;
/*!40000 ALTER TABLE `campaigns` DISABLE KEYS */;
/*!40000 ALTER TABLE `campaigns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapters`
--

DROP TABLE IF EXISTS `chapters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapters` (
  `chapter_id` bigint NOT NULL AUTO_INCREMENT,
  `description` mediumtext,
  `title` varchar(255) DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  PRIMARY KEY (`chapter_id`),
  KEY `FK6h1m0nrtdwj37570c0sp2tdcs` (`course_id`),
  CONSTRAINT `FK6h1m0nrtdwj37570c0sp2tdcs` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapters`
--

LOCK TABLES `chapters` WRITE;
/*!40000 ALTER TABLE `chapters` DISABLE KEYS */;
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (1,'Tìm hiểu tổng quan về Java và ứng dụng thực tế.','Giới thiệu về Java',1);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (2,'Các khái niệm OOP trong Java: class, object, inheritance.','Lập trình hướng đối tượng',1);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (3,'Các thuật toán sắp xếp, tìm kiếm, danh sách liên kết.','Cấu trúc dữ liệu và giải thuật',1);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (4,'Cách sử dụng MySQL, thiết kế database.','Làm việc với MySQL',2);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (5,'Tìm hiểu về Spring Boot và cách tạo ứng dụng.','Spring Boot cơ bản',2);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (6,'Học cách tạo API trong Spring Boot.','RESTful API với Spring Boot',2);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (7,'JWT, OAuth2 và bảo mật trong ứng dụng.','Bảo mật Spring Boot',3);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (8,'ORM trong Java với Hibernate.','Làm việc với Hibernate',3);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (9,'Giới thiệu về React và cách xây dựng ứng dụng web.','Lập trình Web với React',3);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (10,'Cách tích hợp React với API từ Spring Boot.','Kết nối Frontend và Backend',4);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (11,'Quản lý trạng thái ứng dụng với Redux.','Redux trong React',4);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (12,'Tìm hiểu về Next.js và Server-side Rendering.','Next.js và SSR',4);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (13,'Tự động hóa quy trình triển khai ứng dụng.','CI/CD với GitHub Actions',5);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (14,'Triển khai ứng dụng với Docker và Kubernetes.','Docker và Kubernetes',5);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (15,'Xây dựng hệ thống Microservices với Spring Cloud.','Microservices với Spring Cloud',5);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (16,'Tìm hiểu về GraphQL và cách sử dụng với Spring Boot.','GraphQL trong Java',6);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (17,'Xây dựng ứng dụng Android hiện đại với Kotlin.','Lập trình Android với Kotlin',6);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (18,'Cơ bản về Machine Learning, AI, Data Science.','Machine Learning với Python',6);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (19,'Nâng cao kỹ năng Java với multithreading, stream API.','Advanced Java Programming',7);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (20,'Lập trình hướng kiểm thử với JUnit và Mockito.','TDD với JUnit',7);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (21,'Tăng tốc hiệu suất ứng dụng với Redis Cache.','Bộ nhớ đệm với Redis',7);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (22,'Xây dựng hệ thống phân tán với Apache Kafka.','Kafka và Event-Driven Architecture',8);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (23,'Tạo GraphQL API với Spring Boot.','Xây dựng API GraphQL',8);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (24,'Tích hợp OAuth2 và OpenID Connect trong Spring Security.','OAuth2 và OpenID Connect',8);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (25,'Xây dựng ứng dụng real-time với WebSockets.','WebSockets với Spring Boot',9);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (26,'Tìm kiếm nhanh với Elasticsearch và Kibana.','Elasticsearch cho Big Data',9);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (27,'Học cách sử dụng Golang để xây dựng API mạnh mẽ.','Golang cho Backend Developers',9);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (28,'Xây dựng mô hình Machine Learning với TensorFlow và Java.','TensorFlow với Java',10);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (29,'CI/CD chuyên sâu với Kubernetes và ArgoCD.','Xây dựng hệ thống CI/CD nâng cao',10);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (30,'Sử dụng AWS Lambda để phát triển ứng dụng không máy chủ.','Xây dựng ứng dụng Serverless',10);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (31,'Tìm hiểu về biến, kiểu dữ liệu cơ bản và cách sử dụng chúng.','Biến và kiểu dữ liệu trong Java',11);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (32,'Hướng dẫn sử dụng if-else, switch-case và vòng lặp trong Java.','Cấu trúc điều kiện và vòng lặp',11);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (33,'Giới thiệu về lập trình OOP, các khái niệm class, object, inheritance, polymorphism.','Lập trình hướng đối tượng với Java',11);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (34,'Học cách sử dụng mảng, ArrayList, HashMap trong Java.','Làm việc với Array và Collection',12);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (35,'Hướng dẫn cách sử dụng try-catch-finally để xử lý lỗi chương trình.','Xử lý ngoại lệ trong Java',12);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (36,'Đọc ghi file bằng FileReader, FileWriter, BufferedReader và BufferedWriter.','Làm việc với File trong Java',12);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (37,'Kết nối Java với MySQL sử dụng JDBC API.','Giới thiệu về JDBC',13);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (38,'Học cách tạo và quản lý thread trong Java.','Lập trình đa luồng trong Java',13);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (39,'Hướng dẫn tạo giao diện đồ họa với Java Swing.','Xây dựng ứng dụng Java Swing',13);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (40,'Gọi API RESTful và xử lý dữ liệu JSON bằng Java.','Làm việc với API trong Java',14);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (41,'Cài đặt và cấu hình dự án Spring Boot đầu tiên.','Spring Boot: Giới thiệu và Cấu hình',14);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (42,'Học cách xây dựng API RESTful với Spring Boot.','Spring Boot: Xây dựng API RESTful',14);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (43,'Hướng dẫn tích hợp JPA với MySQL trong Spring Boot.','Spring Boot: Làm việc với JPA và MySQL',15);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (44,'Hướng dẫn triển khai xác thực và phân quyền trong Spring Security.','Spring Security: Xác thực và phân quyền',15);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (45,'Ứng dụng kiến thức đã học vào dự án thực tế.','Dự án thực tế: Xây dựng website bán hàng với Spring Boot',15);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (46,'Tổng quan về Python, cài đặt và thiết lập môi trường.','Giới thiệu về Python',16);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (47,'Học về biến, kiểu dữ liệu và cách sử dụng trong Python.','Biến và kiểu dữ liệu trong Python',16);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (48,'Sử dụng if-else, for, while trong Python.','Cấu trúc điều kiện và vòng lặp',16);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (49,'Class, object, inheritance, polymorphism trong Python.','Lập trình hướng đối tượng trong Python',17);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (50,'Học cách thao tác với các cấu trúc dữ liệu trong Python.','Làm việc với List, Tuple, Dictionary',17);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (51,'Đọc, ghi file với Python sử dụng open, read, write.','Xử lý file trong Python',17);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (52,'Async, await trong Python để tối ưu hiệu suất.','Lập trình bất đồng bộ trong Python',18);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (53,'Ứng dụng thực tế giúp bạn hiểu sâu hơn về lập trình Python.','Dự án Python: Xây dựng ứng dụng quản lý công việc',18);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (54,'Học React từ cơ bản, hiểu về JSX và component.','Giới thiệu về React.js',18);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (55,'Sử dụng useState, useEffect, Context API trong React.','React Hooks và State Management',19);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (56,'Học cách tối ưu SEO với Next.js.','Next.js: Xây dựng ứng dụng SSR và SSG',19);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (57,'Dùng Express.js để xây dựng API RESTful.','Xây dựng API với Node.js',19);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (58,'Học cách sử dụng TypeScript để viết code dễ bảo trì hơn.','Tìm hiểu về TypeScript',20);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (59,'Ứng dụng các kiến thức về React, Redux vào một dự án thực tế.','Dự án thực tế: Xây dựng website bán hàng với React',20);
INSERT INTO `chapters` (`chapter_id`, `description`, `title`, `course_id`) VALUES (60,'Học cách tối ưu và cải thiện hiệu suất cho ứng dụng React.','Tối ưu hiệu suất với React và Next.js',20);
/*!40000 ALTER TABLE `chapters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `blog_id` bigint DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `parent_comment_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK9aakob3a7aghrm94k9kmbrjqd` (`blog_id`),
  KEY `FK7ktrfqv6fgfuw6fvwludvibu4` (`course_id`),
  KEY `FK7h839m3lkvhbyv3bcdv7sm4fj` (`parent_comment_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
  CONSTRAINT `FK7h839m3lkvhbyv3bcdv7sm4fj` FOREIGN KEY (`parent_comment_id`) REFERENCES `comments` (`comment_id`),
  CONSTRAINT `FK7ktrfqv6fgfuw6fvwludvibu4` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FK9aakob3a7aghrm94k9kmbrjqd` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_course`
--

DROP TABLE IF EXISTS `coupon_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_course` (
  `coupon_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  PRIMARY KEY (`coupon_id`,`course_id`),
  KEY `FKosww4taai8gbc8s8celm19au3` (`course_id`),
  CONSTRAINT `FK84yjg4cd7hhiir7mrhbstxsp0` FOREIGN KEY (`coupon_id`) REFERENCES `coupons` (`coupon_id`),
  CONSTRAINT `FKosww4taai8gbc8s8celm19au3` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_course`
--

LOCK TABLES `coupon_course` WRITE;
/*!40000 ALTER TABLE `coupon_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupon_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupons`
--

DROP TABLE IF EXISTS `coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupons` (
  `coupon_id` bigint NOT NULL AUTO_INCREMENT,
  `coupon_code` varchar(255) DEFAULT NULL,
  `coupon_description` varchar(255) DEFAULT NULL,
  `coupon_name` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `discount_amount` double DEFAULT NULL,
  `discount_percent` double DEFAULT NULL,
  `discount_range` tinyint DEFAULT NULL,
  `discount_type` tinyint DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `max_discount_amount` double DEFAULT NULL,
  `max_uses` bigint DEFAULT NULL,
  `min_order_value` double DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `used_count` bigint DEFAULT NULL,
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_subject`
--

DROP TABLE IF EXISTS `course_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_subject` (
  `course_id` bigint NOT NULL,
  `subject_id` bigint NOT NULL,
  PRIMARY KEY (`course_id`,`subject_id`),
  KEY `FKdgqvcdk05rc11txucpjrqai5n` (`subject_id`),
  CONSTRAINT `FKdgqvcdk05rc11txucpjrqai5n` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`),
  CONSTRAINT `FKq0h1llihdiqg9ak6xhntlgyk1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_subject`
--

LOCK TABLES `course_subject` WRITE;
/*!40000 ALTER TABLE `course_subject` DISABLE KEYS */;
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (1,1);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (3,1);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (5,1);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,1);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,1);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (17,1);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (19,1);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (2,2);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (9,2);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (10,2);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (17,2);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (18,2);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (19,2);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,3);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,3);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,3);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (11,3);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,3);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (16,3);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (17,3);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (6,4);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,6);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,6);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,6);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,6);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,6);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,7);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,7);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,7);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,7);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (20,7);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (3,8);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,8);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,8);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,9);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (17,10);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (1,11);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (3,11);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,11);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,11);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,11);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (11,11);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,11);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,11);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (16,11);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,12);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,12);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,12);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,12);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,12);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (20,12);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (1,13);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (3,13);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,13);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,13);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,13);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (11,13);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,13);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,13);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (16,13);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (1,14);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (3,14);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,14);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,14);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,14);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,14);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,14);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (16,14);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,15);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (5,15);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (17,16);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,17);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,17);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (20,17);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (3,18);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (5,18);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (12,18);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,18);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (14,18);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,18);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (19,18);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,19);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,19);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (12,19);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,19);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (19,19);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (20,19);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,20);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,20);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (16,20);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (3,21);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,21);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,21);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,21);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,21);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,21);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (16,21);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,22);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,22);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,22);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,22);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (16,22);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,23);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,23);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,23);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,23);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,23);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (16,23);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (4,24);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (7,24);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,24);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,24);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (16,24);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (6,25);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (8,26);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (13,26);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,26);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (20,26);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (14,27);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (15,28);
INSERT INTO `course_subject` (`course_id`, `subject_id`) VALUES (20,28);
/*!40000 ALTER TABLE `course_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_user`
--

DROP TABLE IF EXISTS `course_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_user` (
  `course_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`course_id`,`user_id`),
  KEY `FKf2f9pdami9tgornv4vld7pfea` (`user_id`),
  CONSTRAINT `FK8lwf41pgqkmlkfvklvf22pmcb` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `FKf2f9pdami9tgornv4vld7pfea` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_user`
--

LOCK TABLES `course_user` WRITE;
/*!40000 ALTER TABLE `course_user` DISABLE KEYS */;
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (8,2);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (1,6);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (2,6);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (19,9);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (20,9);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (3,10);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (4,10);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (9,10);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (13,11);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (14,11);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (15,11);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (7,21);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (9,22);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (10,22);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (11,22);
INSERT INTO `course_user` (`course_id`, `user_id`) VALUES (12,22);
/*!40000 ALTER TABLE `course_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` bigint NOT NULL AUTO_INCREMENT,
  `accepted` bit(1) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `objectives` text,
  `price` double DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `expert_id` bigint DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `FKaog0x4gdi738saifl8upy44sb` (`expert_id`),
  CONSTRAINT `FKaog0x4gdi738saifl8upy44sb` FOREIGN KEY (`expert_id`) REFERENCES `experts` (`expert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (1,_binary '','Java Cơ Bản','2025-02-14 10:56:52.317864','Học Java từ cơ bản đến nâng cao.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Java Cơ Bản\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',300000,'1.jpg','2025-02-25 02:15:15.505993',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (2,_binary '','Lập trình Python','2025-02-14 10:56:52.318863','Khóa học giúp bạn làm chủ Python.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Lập trình Python\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',250000,'2.jpg','2025-02-25 02:15:43.897610',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (3,_binary '','Spring Boot Web','2025-02-14 10:56:52.318863','Xây dựng ứng dụng web với Spring Boot.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Spring Boot Web\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',400000,'3.jpg','2025-02-25 02:16:00.729570',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (4,_binary '','JavaScript và React','2025-02-14 10:56:52.318863','Lập trình front-end với React.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về JavaScript và React\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',350000,'4.jpg','2025-02-25 02:16:35.317286',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (5,_binary '','Lập trình Android','2025-02-14 10:56:52.319864','Học Kotlin và xây dựng ứng dụng Android.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Lập trình Android\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',320000,'5.jpg','2025-02-25 02:21:52.037595',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (6,_binary '','C++ từ cơ bản đến nâng cao','2025-02-14 10:56:52.319864','Lập trình C++ cho người mới bắt đầu.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về C++ từ cơ bản đến nâng cao\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',280000,'6.jpg','2025-02-25 02:17:11.002936',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (7,_binary '','Lập trình Node.js','2025-02-14 10:56:52.319864','Phát triển backend với Node.js và Express.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Lập trình Node.js\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',360000,'7.jpg','2025-02-25 02:17:30.118941',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (8,_binary '','Fullstack với Next.js','2025-02-14 10:56:52.320863','Tạo website fullstack với Next.js.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Fullstack với Next.js\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',400000,'8.jpg','2025-02-25 02:17:49.397203',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (9,_binary '','Data Science với Python','2025-02-14 10:56:52.320863','Phân tích dữ liệu với Python, Pandas.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Data Science với Python\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',420000,'9.jpg','2025-02-25 02:18:00.897062',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (10,_binary '','Machine Learning','2025-02-14 10:56:52.320863','Giới thiệu về AI và Machine Learning.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Machine Learning\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',450000,'10.jpg','2025-02-25 02:18:09.401771',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (11,_binary '','HTML & CSS Cơ Bản','2025-02-14 10:56:52.321864','Học cách xây dựng giao diện web với HTML và CSS.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về HTML & CSS Cơ Bản\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',180000,'11.jpg','2025-02-25 02:18:23.314908',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (12,_binary '','SQL cho người mới bắt đầu','2025-02-14 10:56:52.321864','Học cách truy vấn và quản lý cơ sở dữ liệu với SQL.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về SQL cho người mới bắt đầu\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',150000,'12.jpg','2025-02-25 02:18:30.651516',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (13,_binary '','Kiến trúc Microservices','2025-02-14 10:56:52.321864','Triển khai hệ thống Microservices với Spring Cloud.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Kiến trúc Microservices\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',850000,'13.jpg','2025-02-25 02:18:51.843736',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (14,_binary '','Lập trình Golang','2025-02-14 10:56:52.321864','Học cách lập trình với ngôn ngữ Golang.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Lập trình Golang\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',900000,'14.jpg','2025-02-25 02:21:58.883494',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (15,_binary '','Kubernetes & Docker','2025-02-14 10:56:52.322863','Triển khai ứng dụng với Docker và Kubernetes.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Kubernetes & Docker\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',950000,'15.jpg','2025-02-25 02:24:21.590114',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (16,_binary '','Thiết kế UI/UX','2025-02-14 10:56:52.322863','Học cách thiết kế giao diện đẹp và trải nghiệm tốt.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Thiết kế UI/UX\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',180000,'16.jpg','2025-02-25 02:24:43.651671',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (17,_binary '','Cấu trúc dữ liệu & Giải thuật','2025-02-14 10:56:52.322863','Học thuật toán và cấu trúc dữ liệu để tối ưu code.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Cấu trúc dữ liệu & Giải thuật\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',190000,'17.jpg','2025-02-25 02:25:04.112248',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (18,_binary '','Lập trình AI với Python','2025-02-14 10:56:52.323863','Khóa học AI từ cơ bản đến nâng cao với Python.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Lập trình AI với Python\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',850000,'18.jpg','2025-02-25 02:25:18.276525',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (19,_binary '','Blockchain và Smart Contracts','2025-02-14 10:56:52.323863','Xây dựng ứng dụng phi tập trung với Blockchain.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Blockchain và Smart Contracts\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',900000,'19.jpg','2025-02-25 02:25:35.407738',1);
INSERT INTO `courses` (`course_id`, `accepted`, `course_name`, `created_at`, `description`, `introduction`, `objectives`, `price`, `thumbnail`, `updated_at`, `expert_id`) VALUES (20,_binary '','DevOps với AWS và Docker','2025-02-14 10:56:52.323863','Học DevOps từ cơ bản, triển khai CI/CD với AWS.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về DevOps với AWS và Docker\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',1500000,'20.jpg','2025-02-25 02:25:56.281960',1);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experts`
--

DROP TABLE IF EXISTS `experts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experts` (
  `expert_id` bigint NOT NULL AUTO_INCREMENT,
  `achievement` mediumtext,
  `description` mediumtext,
  `job` varchar(255) DEFAULT NULL,
  `year_of_experience` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`expert_id`),
  UNIQUE KEY `UK66kp2njuac7qdaov6pocl0896` (`user_id`),
  CONSTRAINT `FK5os9nnfapw3vwad4yac038kvk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experts`
--

LOCK TABLES `experts` WRITE;
/*!40000 ALTER TABLE `experts` DISABLE KEYS */;
INSERT INTO `experts` (`expert_id`, `achievement`, `description`, `job`, `year_of_experience`, `user_id`) VALUES (1,'20 năm thiết kế giao diện cho website','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum','Web Designer',20,6);
INSERT INTO `experts` (`expert_id`, `achievement`, `description`, `job`, `year_of_experience`, `user_id`) VALUES (2,'10 năm lập trình backend cho các tập đoàn lớn','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum','Backend Developer',10,7);
INSERT INTO `experts` (`expert_id`, `achievement`, `description`, `job`, `year_of_experience`, `user_id`) VALUES (3,'15 năm viết tài liệu cho các dự án lớn nhỏ','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum','Business Analyst',15,8);
/*!40000 ALTER TABLE `experts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtags`
--

DROP TABLE IF EXISTS `hashtags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtags` (
  `tag_id` bigint NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtags`
--

LOCK TABLES `hashtags` WRITE;
/*!40000 ALTER TABLE `hashtags` DISABLE KEYS */;
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (1,'java');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (2,'javascript');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (3,'typescript');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (4,'html');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (5,'css');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (6,'machine learning');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (7,'reactjs');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (8,'nextjs');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (9,'c++');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (10,'python');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (11,'spring boot');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (12,'docker');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (13,'kubernetes');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (14,'ai');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (15,'deep learning');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (16,'flutter');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (17,'nodejs');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (18,'golang');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (19,'rust');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (20,'database');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (21,'mysql');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (22,'mongodb');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (23,'postgresql');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (24,'graphql');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (25,'restapi');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (26,'android');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (27,'ios');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (28,'aws');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (29,'cloud computing');
INSERT INTO `hashtags` (`tag_id`, `tag_name`) VALUES (30,'cybersecurity');
/*!40000 ALTER TABLE `hashtags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lessons`
--

DROP TABLE IF EXISTS `lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessons` (
  `lesson_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `document_content` longtext,
  `duration` bigint DEFAULT NULL,
  `lesson_type` enum('DOCUMENT','VIDEO') DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `chapter_id` bigint DEFAULT NULL,
  PRIMARY KEY (`lesson_id`),
  KEY `FKmb78vk1f2oljr16oj1hpo45ma` (`chapter_id`),
  CONSTRAINT `FKmb78vk1f2oljr16oj1hpo45ma` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`chapter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lessons`
--

LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` (`created_at`, `document_content`, `duration`, `lesson_type`, `title`, `updated_at`, `chapter_id`) VALUES 
('2025-02-14 10:56:50.994315', '<h1>Java là gì?</h1><p>Java là một ngôn ngữ lập trình hướng đối tượng, mạnh mẽ.</p>', 60, 'DOCUMENT', 'Giới thiệu về Java', '2025-02-14 10:56:51.063330', 1),
('2025-02-14 10:56:50.994315', '<h1>Python</h1><p>Python là một ngôn ngữ lập trình bậc cao, dễ học.</p>', 60, 'DOCUMENT', 'Tìm hiểu về Python', '2025-02-14 10:56:51.079332', 2),
('2025-02-14 10:56:50.995317', '<h1>HTML & CSS</h1><p>HTML giúp tạo cấu trúc, còn CSS giúp tạo kiểu cho trang web.</p>', 60, 'DOCUMENT', 'HTML và CSS là gì?', '2025-02-14 10:56:51.096323', 3),
('2025-02-14 10:56:50.995317', '<h1>JavaScript</h1><p>Ngôn ngữ lập trình phổ biến trên web.</p>', 60, 'DOCUMENT', 'Giới thiệu về JavaScript', '2025-02-14 10:56:51.115218', 4),
('2025-02-14 10:56:50.996315', '<h1>C++</h1><p>Một ngôn ngữ lập trình mạnh mẽ, dùng cho game, hệ thống nhúng.</p>', 60, 'DOCUMENT', 'C++ là gì?', '2025-02-14 10:56:51.129688', 5),
('2025-02-14 10:56:50.996315', '<h1>PHP</h1><p>PHP là một ngôn ngữ phổ biến để phát triển web.</p>', 60, 'DOCUMENT', 'Lập trình với PHP', '2025-02-14 10:56:51.146533', 6),
('2025-02-14 10:56:50.996315', '<h1>Kotlin</h1><p>Kotlin là ngôn ngữ chính thức để phát triển Android.</p>', 60, 'DOCUMENT', 'Tìm hiểu về Kotlin', '2025-02-14 10:56:51.161754', 7),
('2025-02-14 10:56:50.997315', '<h1>Dart & Flutter</h1><p>Dart là ngôn ngữ, Flutter là framework phát triển ứng dụng mobile.</p>', 60, 'DOCUMENT', 'Dart và Flutter', '2025-02-14 10:56:51.177758', 8),
('2025-02-14 10:56:50.997315', '<h1>Golang</h1><p>Ngôn ngữ lập trình mạnh mẽ, hiệu suất cao do Google phát triển.</p>', 60, 'DOCUMENT', 'Go (Golang) là gì?', '2025-02-14 10:56:51.193264', 9),
('2025-02-14 10:56:50.998315', '<h1>Rust</h1><p>Rust là một ngôn ngữ lập trình hệ thống với độ an toàn bộ nhớ cao.</p>', 60, 'DOCUMENT', 'Rust và sự an toàn bộ nhớ', '2025-02-14 10:56:51.208302', 10);
INSERT INTO `lessons` (`created_at`, `document_content`, `duration`, `lesson_type`, `title`, `updated_at`, `chapter_id`) VALUES
('2025-02-14 10:56:50.998315', '<h1>Swift</h1><p>Swift là ngôn ngữ chính thức để phát triển ứng dụng iOS.</p>', 60, 'DOCUMENT', 'Swift và iOS Development', '2025-02-14 10:56:51.225303', 11),
('2025-02-14 10:56:50.999314', '<h1>Scala</h1><p>Scala kết hợp lập trình hướng đối tượng và lập trình hàm.</p>', 60, 'DOCUMENT', 'Scala và lập trình hàm', '2025-02-14 10:56:51.240314', 12),
('2025-02-14 10:56:50.999314', '<h1>R</h1><p>R là ngôn ngữ mạnh mẽ để phân tích dữ liệu và thống kê.</p>', 60, 'DOCUMENT', 'R và phân tích dữ liệu', '2025-02-14 10:56:51.260227', 13),
('2025-02-14 10:56:51.000316', '<h1>TypeScript</h1><p>TypeScript là JavaScript có kiểu tĩnh, giúp phát triển ứng dụng lớn.</p>', 60, 'DOCUMENT', 'TypeScript là gì?', '2025-02-14 10:56:51.277228', 14),
('2025-02-14 10:56:51.000316', '<h1>Perl</h1><p>Perl được sử dụng nhiều trong quản trị hệ thống và xử lý văn bản.</p>', 60, 'DOCUMENT', 'Perl và lập trình hệ thống', '2025-02-14 10:56:51.297796', 15),
('2025-02-14 10:56:51.001315', '<h1>Shell Script</h1><p>Shell Script giúp tự động hóa các tác vụ trên hệ điều hành Unix/Linux.</p>', 60, 'DOCUMENT', 'Shell Script và Automation', '2025-02-14 10:56:51.312383', 16),
('2025-02-14 10:56:51.001315', '<h1>Haskell</h1><p>Haskell là ngôn ngữ lập trình thuần hàm mạnh mẽ.</p>', 60, 'DOCUMENT', 'Haskell và lập trình thuần hàm', '2025-02-14 10:56:51.327208', 17),
('2025-02-14 10:56:51.001315', '<h1>Elixir</h1><p>Elixir là ngôn ngữ lập trình mạnh mẽ trong lập trình phân tán.</p>', 60, 'DOCUMENT', 'Elixir và lập trình phân tán', '2025-02-14 10:56:51.341290', 18),
('2025-02-14 10:56:51.002314', '<h1>Lua</h1><p>Lua là ngôn ngữ nhẹ, mạnh mẽ cho lập trình nhúng.</p>', 60, 'DOCUMENT', 'Lua và lập trình nhúng', '2025-02-14 10:56:51.357433', 19),
('2025-02-14 10:56:51.002314', '<h1>OOP là gì?</h1><p>OOP (Object-Oriented Programming) là một mô hình lập trình dựa trên đối tượng.</p>', 60, 'DOCUMENT', 'Lập trình hướng đối tượng', '2025-02-14 10:56:51.371441', 20);
INSERT INTO `lessons` (`created_at`, `document_content`, `duration`, `lesson_type`, `title`, `updated_at`, `chapter_id`) VALUES
('2025-02-14 10:56:51.003315', '<h1>Functional Programming</h1><p>Lập trình hàm là một mô hình lập trình dựa trên các hàm toán học.</p>', 60, 'DOCUMENT', 'Lập trình Functional', '2025-02-14 10:56:51.386222', 21),
('2025-02-14 10:56:51.003315', '<h1>JavaScript là gì?</h1><p>JavaScript là một ngôn ngữ lập trình phổ biến trên web.</p>', 60, 'DOCUMENT', 'JavaScript cơ bản', '2025-02-14 10:56:51.402731', 22),
('2025-02-14 10:56:51.003315', '<h1>Python là gì?</h1><p>Python là một ngôn ngữ lập trình bậc cao, dễ học và mạnh mẽ.</p>', 60, 'DOCUMENT', 'Python và ứng dụng', '2025-02-14 10:56:51.419730', 23),
('2025-02-14 10:56:51.004315', '<h1>C++ là gì?</h1><p>C++ là một ngôn ngữ lập trình mạnh mẽ, thường được sử dụng trong lập trình hệ thống.</p>', 60, 'DOCUMENT', 'C++ và lập trình hệ thống', '2025-02-14 10:56:51.434735', 24),
('2025-02-14 10:56:51.004315', '<h1>Spring Boot là gì?</h1><p>Spring Boot là một framework giúp phát triển ứng dụng Java dễ dàng hơn.</p>', 60, 'DOCUMENT', 'Spring Boot cơ bản', '2025-02-14 10:56:51.450563', 25),
('2025-02-14 10:56:51.004315', '<h1>REST API</h1><p>REST API là một tiêu chuẩn phổ biến để xây dựng các dịch vụ web.</p>', 60, 'DOCUMENT', 'REST API là gì?', '2025-02-14 10:56:51.466505', 26),
('2025-02-14 10:56:51.005316', '<h1>SQL là gì?</h1><p>SQL là ngôn ngữ truy vấn dữ liệu phổ biến cho hệ quản trị cơ sở dữ liệu.</p>', 60, 'DOCUMENT', 'SQL và cơ sở dữ liệu', '2025-02-14 10:56:51.482972', 27),
('2025-02-14 10:56:51.006316', '<h1>Docker là gì?</h1><p>Docker là một nền tảng giúp đóng gói và triển khai ứng dụng dễ dàng.</p>', 60, 'DOCUMENT', 'Docker và DevOps', '2025-02-14 10:56:51.499597', 28),
('2025-02-14 10:56:51.006316', '<h1>Machine Learning</h1><p>Machine Learning là một lĩnh vực của trí tuệ nhân tạo giúp máy tính học từ dữ liệu.</p>', 60, 'DOCUMENT', 'Machine Learning với Python', '2025-02-14 10:56:51.513818', 29),
('2025-02-14 10:56:51.007316', '<h1>ReactJS là gì?</h1><p>ReactJS là một thư viện JavaScript phổ biến để xây dựng giao diện người dùng.</p>', 60, 'DOCUMENT', 'Lập trình ReactJS', '2025-02-14 10:56:51.527830', 30);
INSERT INTO lessons (created_at, document_content, duration, lesson_type, title, updated_at, chapter_id) VALUES
('2025-02-14 10:56:51.012315', '<h1>Agile là gì?</h1><p>Agile là một phương pháp phát triển phần mềm linh hoạt.</p>', 60, 'DOCUMENT', 'Tìm hiểu về Agile', '2025-02-14 10:56:51.709123', 41),
('2025-02-14 10:56:51.012315', '<h1>Scrum trong Agile</h1><p>Scrum là một framework phổ biến trong Agile giúp quản lý dự án hiệu quả.</p>', 60, 'DOCUMENT', 'Scrum và Agile', '2025-02-14 10:56:51.725456', 42),
('2025-02-14 10:56:51.013315', '<h1>Big Data là gì?</h1><p>Big Data là thuật ngữ dùng để chỉ tập dữ liệu lớn và phức tạp.</p>', 60, 'DOCUMENT', 'Big Data cơ bản', '2025-02-14 10:56:51.741839', 43),
('2025-02-14 10:56:51.013315', '<h1>Hadoop</h1><p>Hadoop là một framework giúp xử lý dữ liệu lớn một cách hiệu quả.</p>', 60, 'DOCUMENT', 'Xử lý Big Data với Hadoop', '2025-02-14 10:56:51.758241', 44),
('2025-02-14 10:56:51.014315', '<h1>Blockchain là gì?</h1><p>Blockchain là công nghệ giúp lưu trữ dữ liệu một cách an toàn và minh bạch.</p>', 60, 'DOCUMENT', 'Blockchain và ứng dụng', '2025-02-14 10:56:51.774512', 45),
('2025-02-14 10:56:51.014315', '<h1>Ethereum</h1><p>Ethereum là một nền tảng blockchain phổ biến, hỗ trợ hợp đồng thông minh.</p>', 60, 'DOCUMENT', 'Ethereum và Smart Contract', '2025-02-14 10:56:51.790923', 46),
('2025-02-14 10:56:51.015315', '<h1>Cybersecurity</h1><p>Cybersecurity là các biện pháp bảo vệ hệ thống và dữ liệu khỏi tấn công mạng.</p>', 60, 'DOCUMENT', 'An toàn thông tin', '2025-02-14 10:56:51.807234', 47),
('2025-02-14 10:56:51.015315', '<h1>Penetration Testing</h1><p>Penetration Testing là quá trình kiểm tra hệ thống để phát hiện lỗ hổng bảo mật.</p>', 60, 'DOCUMENT', 'Kiểm thử bảo mật', '2025-02-14 10:56:51.823678', 48),
('2025-02-14 10:56:51.016315', '<h1>AI Ethics</h1><p>AI Ethics là những nguyên tắc đạo đức trong phát triển và sử dụng AI.</p>', 60, 'DOCUMENT', 'Đạo đức trong AI', '2025-02-14 10:56:51.840012', 49),
('2025-02-14 10:56:51.016315', '<h1>Cloud Computing</h1><p>Cloud Computing là mô hình cung cấp tài nguyên máy tính qua internet.</p>', 60, 'DOCUMENT', 'Điện toán đám mây', '2025-02-14 10:56:51.856345', 50);
INSERT INTO lessons (created_at, document_content, duration, lesson_type, title, updated_at, chapter_id) VALUES
('2025-02-14 10:56:51.016315', '<h1>OpenCV</h1><p>OpenCV là một thư viện giúp xử lý ảnh và video.</p>', 60, 'DOCUMENT', 'Computer Vision với OpenCV', '2025-02-14 10:56:51.851470', 51),
('2025-02-14 10:56:51.017315', '<h1>Blockchain</h1><p>Blockchain là công nghệ giúp lưu trữ dữ liệu phân tán an toàn.</p>', 60, 'DOCUMENT', 'Blockchain và Smart Contracts', '2025-02-14 10:56:51.873469', 52),
('2025-02-14 10:56:51.018315', '<h1>Web3</h1><p>Web3 giúp xây dựng các ứng dụng phi tập trung trên blockchain.</p>', 60, 'DOCUMENT', 'Tìm hiểu về Web3', '2025-02-14 10:56:51.888978', 53),
('2025-02-14 10:56:51.019317', '<h1>Apache Spark</h1><p>Apache Spark là framework xử lý dữ liệu lớn mạnh mẽ.</p>', 60, 'DOCUMENT', 'Big Data với Apache Spark', '2025-02-14 10:56:51.904007', 54),
('2025-02-14 10:56:51.020315', '<h1>AI và lập trình</h1><p>AI giúp lập trình viên tạo ra các hệ thống thông minh hơn.</p>', 60, 'DOCUMENT', 'Ứng dụng AI trong lập trình', '2025-02-14 10:56:51.917008', 55),
('2025-02-14 10:56:51.020315', '<h1>JUnit</h1><p>JUnit là một framework hỗ trợ kiểm thử đơn vị trong Java.</p>', 60, 'DOCUMENT', 'Unit Testing với JUnit', '2025-02-14 10:56:51.932906', 56),
('2025-02-14 10:56:51.020315', '<h1>TDD là gì?</h1><p>TDD là phương pháp phát triển phần mềm dựa trên kiểm thử.</p>', 60, 'DOCUMENT', 'Tìm hiểu về Test-Driven Development', '2025-02-14 10:56:51.947778', 57),
('2025-02-14 10:56:51.021317', '<h1>Refactoring</h1><p>Refactoring giúp cải thiện chất lượng mã nguồn mà không thay đổi chức năng.</p>', 60, 'DOCUMENT', 'Refactoring code là gì?', '2025-02-14 10:56:51.962777', 58),
('2025-02-14 10:56:51.024513', '<h1>Làm sao để học lập trình tốt?</h1><p>Học lập trình cần kết hợp thực hành và lý thuyết.</p>', 60, 'DOCUMENT', 'Học lập trình hiệu quả', '2025-02-14 10:56:51.975751', 59),
('2025-02-14 10:56:51.025315', '<h1>Docker là gì?</h1><p>Docker giúp đóng gói và triển khai ứng dụng dễ dàng với container.</p>', 60, 'DOCUMENT', 'Tìm hiểu về Docker', '2025-02-14 10:56:51.991255', 60);
INSERT INTO lessons (created_at, duration, lesson_type, title, updated_at, video_url, chapter_id) VALUES
('2025-02-14 10:56:50.938200', 603, 'VIDEO', 'When & Where to Add “use client” in React / Next.js (Client Components vs Server Components)', '2025-02-14 10:56:51.053314', 'https://youtu.be/Qdkg_mrniLk?si=OI-cJQU5nWWqzTls', 1),
('2025-02-14 10:56:50.939200', 1236, 'VIDEO', '10 common mistakes with the Next.js App Router', '2025-02-14 10:56:51.074332', 'https://youtu.be/RBM03RihZVs?si=iwBzGi3UH-SnBBw-', 2),
('2025-02-14 10:56:50.939200', 268, 'VIDEO', 'What is Spring Framework?', '2025-02-14 10:56:51.090847', 'https://youtu.be/Zxwq3aW9ctU?si=_Q5PIIMS00zQKHWQ', 3),
('2025-02-14 10:56:50.940200', 813, 'VIDEO', 'Dependency Injection using Spring Boot', '2025-02-14 10:56:51.109323', 'https://youtu.be/9EoAXpjnsxM?si=YVGJNYXfQDF8_PB9', 4),
('2025-02-14 10:56:50.940200', 915, 'VIDEO', 'Autowire using Spring Boot', '2025-02-14 10:56:51.124687', 'https://youtu.be/ET39IFffr24?si=zsQixgt2XigHMWUP', 5),
('2025-02-14 10:56:50.941200', 393, 'VIDEO', 'TypeScript 5.8 Has 2 AWESOME Features', '2025-02-14 10:56:51.140533', 'https://youtu.be/vcVoyLQMCxU?si=Ved2PkGSMEEkpJ6I', 6),
('2025-02-14 10:56:50.941200', 420, 'VIDEO', 'Build anything with DeepSeek + Cline (CHEAP & EASY)', '2025-02-14 10:56:51.155755', 'https://youtu.be/ksr-_IXsVvs?si=gLpr3GDaAPYzZ6Ly', 7),
('2025-02-14 10:56:50.942201', 567, 'VIDEO', 'Tailwind v4 Is FINALLY Out – Here’s What’s New (and how to migrate!)', '2025-02-14 10:56:51.170754', 'https://youtu.be/ud913ekwAOQ?si=25uinR32Zx84cODw', 8),
('2025-02-14 10:56:50.942201', 1014, 'VIDEO', 'My best CSS tips from 2024', '2025-02-14 10:56:51.186757', 'https://youtu.be/lUU2OAAg4Uw?si=X2HhkieAKwfVFFQy', 9),
('2025-02-14 10:56:50.943200', 1686, 'VIDEO', 'Next.js Server Actions (revalidatePath, useFormStatus & useOptimistic)', '2025-02-14 10:56:51.202303', 'https://youtu.be/RadgkoJrhu0?si=KBRCSnMIjj_V8-xO', 10),
('2025-02-14 10:56:50.943200', 111, 'VIDEO', 'Difference Between Properties and Methods • C# Programming • C# Tutorial • Learn C#', '2025-02-14 10:56:51.219303', 'https://www.youtube.com/watch?v=eHGhtzu5p5s', 11),
('2025-02-14 10:56:50.944200', 249, 'VIDEO', 'Learn C# Sharp in Four Minutes', '2025-02-14 10:56:51.234313', 'https://www.youtube.com/watch?v=FqCHwSH56PA', 12),
('2025-02-14 10:56:50.945200', 1133, 'VIDEO', 'What is .NET? What\'s C# and F#? What\'s the .NET Ecosystem? .NET Core Explained, what can .NET build?', '2025-02-14 10:56:51.252312', 'https://www.youtube.com/watch?v=bEfBfBQq7EE', 13),
('2025-02-14 10:56:50.945200', 3552, 'VIDEO', 'Learn C# Basics 3 of 3 with Scott and Cherita', '2025-02-14 10:56:51.272226', 'https://www.youtube.com/watch?v=FqCHwSH56PA', 14),
('2025-02-14 10:56:50.946200', 589, 'VIDEO', 'Every single feature of C# in 10 minutes', '2025-02-14 10:56:51.290389', 'https://www.youtube.com/watch?v=J0FhV3dM80o', 15);
INSERT INTO lessons (created_at, duration, lesson_type, title, updated_at, video_url, chapter_id) VALUES
('2025-02-14 10:56:50.946200', 373, 'VIDEO', 'How C# and Java Are Actually Twins', '2025-02-14 10:56:51.306795', 'https://www.youtube.com/watch?v=gW6m36xR-2U', 16),
('2025-02-14 10:56:50.947202', 244, 'VIDEO', 'Upgrading Your Xamarin Project to MAUI', '2025-02-14 10:56:51.321209', 'https://www.youtube.com/watch?v=c7ValognY4I', 17),
('2025-02-14 10:56:50.947202', 518, 'VIDEO', 'Converting Java to C# using GPT-4', '2025-02-14 10:56:51.336216', 'https://www.youtube.com/watch?v=ivFv0PzCRPw', 18),
('2025-02-14 10:56:50.948199', 590, 'VIDEO', 'Why C# Has Multiple Ways to Write the Same Code', '2025-02-14 10:56:51.351283', 'https://www.youtube.com/watch?v=rjVAz6jLvUQ', 19),
('2025-02-14 10:56:50.948199', 211, 'VIDEO', 'Primary Constructors! New Feature Coming to C#', '2025-02-14 10:56:51.366434', 'https://www.youtube.com/watch?v=VarE4d2BqMo', 20),
('2025-02-14 10:56:50.949200', 194, 'VIDEO', 'Tổng quan về khóa học HTML CSS tại F8 | Học lập trình web cơ bản | Học được gì trong khóa học?', '2025-02-14 10:56:51.381223', 'https://youtu.be/R6plN3FvzFY?si=GAioUucKIlup-h56', 21),
('2025-02-14 10:56:50.949200', 148, 'VIDEO', 'HTML CSS là gì? | Ví dụ trực quan về HTML & CSS', '2025-02-14 10:56:51.396731', 'https://youtu.be/zwsPND378OQ?si=lgry-wwVS8FV2Z7x', 22),
('2025-02-14 10:56:50.950201', 234, 'VIDEO', 'Làm quen với Dev tools | Giới thiệu bộ công cụ Dev tools trên trình duyệt', '2025-02-14 10:56:51.414731', 'https://youtu.be/7BJiPyN4zZ0?si=CbmHOInhnl4ABntt', 23),
('2025-02-14 10:56:50.950201', 131, 'VIDEO', 'Comments trong HTML | Cú pháp mở và đóng Comments', '2025-02-14 10:56:51.428733', 'https://youtu.be/JG0pdfdKjgQ?si=gwiv7dTx5ZShYMAe', 24),
('2025-02-14 10:56:50.950201', 114, 'VIDEO', 'Attributes trong HTML | Thêm thuộc tính (Attributes) vào thẻ', '2025-02-14 10:56:51.444736', 'https://youtu.be/UYpIh5pIkSA?si=c5VD8zUy-j9RdPQj', 25),
('2025-02-14 10:56:50.951200', 228, 'VIDEO', 'CSS Variable là gì? | Cách đặt biến trong CSS', '2025-02-14 10:56:51.460563', 'https://youtu.be/x9fnxVTkpP4?si=S90IsOYxRbcfAl_M', 26),
('2025-02-14 10:56:50.951200', 254, 'VIDEO', 'CSS Background-clip | Thuộc tính Background-clip', '2025-02-14 10:56:51.476973', 'https://youtu.be/hMWhvbCJIq8?si=qqD72d89KfUNSOpE', 27),
('2025-02-14 10:56:50.951200', 196, 'VIDEO', 'Thuộc tính Background-size | CSS Background-size', '2025-02-14 10:56:51.494597', 'https://youtu.be/8zsmGFNpqb4?si=XiO2JV9deVMaXVh4', 28),
('2025-02-14 10:56:50.952208', 177, 'VIDEO', 'Thuộc tính Background-origin | CSS Background-origin', '2025-02-14 10:56:51.508597', 'https://youtu.be/32a_fYd5zIo?si=PeEiGuBJrhPdLrNb', 29),
('2025-02-14 10:56:50.952208', 149, 'VIDEO', 'Cú pháp shorthand | CSS Background shorthand', '2025-02-14 10:56:51.522817', 'https://youtu.be/4hf8kMSRUJI?si=A2JZHoSjWENWxwf5', 30);
INSERT INTO lessons (created_at, duration, lesson_type, title, updated_at, video_url, chapter_id) VALUES
('2025-02-14 10:56:50.953200', 609, 'VIDEO', 'Master TypeScript in an easy way', '2025-02-14 10:56:51.537830', 'https://www.youtube.com/watch?v=nFwmB1_iQ7A', 31),
('2025-02-14 10:56:50.953200', 737, 'VIDEO', 'Master React JS in easy way', '2025-02-14 10:56:51.551909', 'https://www.youtube.com/watch?v=E8lXC2mR6-k', 32),
('2025-02-14 10:56:50.954201', 725, 'VIDEO', 'Flutter Tutorial for Beginners #1 - Intro & Setup', '2025-02-14 10:56:51.566157', 'https://www.youtube.com/watch?v=1ukSR1GRtMU&list=PL4cUxeGkcC9jLYyp2Aoh6hcWuxFDX6PBJ', 33),
('2025-02-14 10:56:50.954201', 375, 'VIDEO', 'Angular Tutorial - 1 - Introduction', '2025-02-14 10:56:51.584170', 'https://www.youtube.com/watch?v=0eWrpsCLMJQ&list=PLC3y8-rFHvwhBRAgFinJR8KHIrCdTkZcZ', 34),
('2025-02-14 10:56:50.955200', 612, 'VIDEO', '[100daysOfAngular] Day 1 - Introduction', '2025-02-14 10:56:51.598676', 'https://www.youtube.com/watch?v=NS6P1fpU77o&list=PLMTyi4Bfd5pW73uXw-6jgRxDwdPYqwk0r', 35),
('2025-02-14 10:56:50.955200', 631, 'VIDEO', 'Introduction to C++ Programming', '2025-02-14 10:56:51.617676', 'https://www.youtube.com/watch?v=s0g4ty29Xgg&list=PLBlnK6fEyqRh6isJ01MBnbNpV3ZsktSyS', 36),
('2025-02-14 10:56:50.956200', 865, 'VIDEO', 'Numbers, Integers, and Math [Pt 7] | C# for Beginners', '2025-02-14 10:56:51.632939', 'https://www.youtube.com/watch?v=ZXCMBOxry8A&list=PLdo4fOcmZ0oULFjxrOagaERVAMbmG20Xe&index=7', 37),
('2025-02-14 10:56:50.956200', 638, 'VIDEO', 'The Options Pattern in C# in 10 Minutes or Less', '2025-02-14 10:56:51.650940', 'https://www.youtube.com/watch?v=ko1Ie9gDydY', 38),
('2025-02-14 10:56:50.956200', 619, 'VIDEO', 'The Dictionary Data Structure in C# in 10 Minutes or Less', '2025-02-14 10:56:51.666940', 'https://www.youtube.com/watch?v=R94JHIXdTk0', 39),
('2025-02-14 10:56:50.957200', 708, 'VIDEO', 'JavaScript Basics in 10 Minutes', '2025-02-14 10:56:51.686943', 'https://www.youtube.com/watch?v=xwKbtUP87Dk', 40),
('2025-02-14 10:56:50.957200', 395, 'VIDEO', 'This CSS Property Replaces Hundreds of Lines of Code', '2025-02-14 10:56:51.703082', 'https://youtu.be/ElELqkwzcYM?si=km2dsDrSgfV7-02J', 41),
('2025-02-14 10:56:50.957200', 511, 'VIDEO', 'Learn CSS Positioning Quickly With A Real World Example', '2025-02-14 10:56:51.716574', 'https://youtu.be/MxEtxo_AaZ4?si=L0gTIT8ArVinwNf6', 42),
('2025-02-14 10:56:50.958200', 310, 'VIDEO', 'One Line Of Code Clip-Path By Master CSS', '2025-02-14 10:56:51.730353', 'https://youtu.be/g-R_YlDg2kQ?si=LKrzzvZWWUMqcmiG', 43),
('2025-02-14 10:56:50.958200', 737, 'VIDEO', 'Master React JS in easy way', '2025-02-14 10:56:51.746996', 'https://youtu.be/E8lXC2mR6-k?si=nO2BEjW4cF_iUSy7', 44);
INSERT INTO lessons (created_at, duration, lesson_type, title, updated_at, video_url, chapter_id) VALUES
('2025-02-14 10:56:50.959200', 505, 'VIDEO', 'Master React Hooks in easy way | useEffect', '2025-02-14 10:56:51.760915', 'https://youtu.be/g-R_YlDg2kQ?si=WD5uRfc851t9gJAY', 45),
('2025-02-14 10:56:50.959200', 140, 'VIDEO', 'NestJS in 100 Seconds', '2025-02-14 10:56:51.773922', 'https://youtu.be/0M8AYU_hPas?si=0_e2eFBhwQITGFer', 46),
('2025-02-14 10:56:50.960201', 539, 'VIDEO', 'Next.js 13 - The Basics', '2025-02-14 10:56:51.788017', 'https://youtu.be/mSgDEOyv8?si=T3VmDTg3PLsupT55', 47),
('2025-02-14 10:56:50.960201', 597, 'VIDEO', 'Express.js 5 is here (since a month already, actually)', '2025-02-14 10:56:51.802072', 'https://youtu.be/-MMjFX5UfN4?si=R851MIi8cRasOBkR', 48),
('2025-02-14 10:56:50.961200', 146, 'VIDEO', 'MongoDB in 100 Seconds', '2025-02-14 10:56:51.817130', 'https://youtu.be/-bt_y4Loofg?si=749xUTz-Yc5wc-Sv', 49),
('2025-02-14 10:56:50.961200', 329, 'VIDEO', 'MySQL vs MongoDB', '2025-02-14 10:56:51.832215', 'https://youtu.be/OdgZ0jr4jpM?si=POAUqP2o-dQFYLTD', 50),
('2025-02-14 10:56:50.962201', 84, 'VIDEO', 'Fetch vs. Axios in 1 minute', '2025-02-14 10:56:51.846215', 'https://youtu.be/OFWATycG_Wc?si=C7xIzojN9YoeY1RA', 51),
('2025-02-14 10:56:50.962201', 590, 'VIDEO', '10 Advanced Tailwind Tricks Inspired by Shadcn', '2025-02-14 10:56:51.867469', 'https://youtu.be/9z2Ifq-OPEI?si=vJTfnI1VVYAmVsxJ', 52),
('2025-02-14 10:56:50.963200', 805, 'VIDEO', '#8 Spring without Boot', '2025-02-14 10:56:51.882472', 'https://youtu.be/42X_fDrP-Vg?si=SUxHA2R6PXpPlE5a', 53),
('2025-02-14 10:56:50.963200', 946, 'VIDEO', '#10 Constructor and Setter Injection in Spring', '2025-02-14 10:56:51.898775', 'https://youtu.be/02Mv2lc-h-8?si=9h-izl-x8qPD12G7', 54),
('2025-02-14 10:56:50.964200', 763, 'VIDEO', 'Mastering TypeScript Generics | Advanced TypeScript Concepts', '2025-02-14 10:56:51.913009', 'https://youtu.be/Ba3rJEOqbNA?si=A78LnUpbIsppRhJ7', 55),
('2025-02-14 10:56:50.964200', 771, 'VIDEO', 'Learn TypeScript Generics In 13 Minutes', '2025-02-14 10:56:51.927013', 'https://youtu.be/EcCTIExsqmI?si=Vjt-xdB4twm8hCx0', 56),
('2025-02-14 10:56:50.965200', 503, 'VIDEO', '#2. Setup dự án thực hành | Tự Học Fullstack Next.js/Nest.js với TypeScript', '2025-02-14 10:56:51.942905', 'https://youtu.be/-aYoZhvn8-4?si=PYXk3TxFq1UjHqqA', 57),
('2025-02-14 10:56:50.966200', 574, 'VIDEO', 'NestJS MongoDB Connection: Quick Guide with 3 Approaches', '2025-02-14 10:56:51.957776', 'https://youtu.be/XXjfTQ7d-eo?si=-AY5N672c1Sm65RU', 58),
('2025-02-14 10:56:50.966200', 458, 'VIDEO', 'Framer Motion Tutorial: How to create Awesome text animation with framer-motion', '2025-02-14 10:56:51.970748', 'https://youtu.be/J8SFL3Z7zw4?si=YBuZkyjBx6rWZE-C', 59),
('2025-02-14 10:56:50.966200', 312, 'VIDEO', 'Framer Motion Tutorial: React Scroll Animations with Framer Motion', '2025-02-14 10:56:51.984751', 'https://youtu.be/bxzk0LEF5OE?si=1_VHKwuk9-K1aCC_', 60);
/*!40000 ALTER TABLE `lessons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `blog_id` bigint DEFAULT NULL,
  `comment_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`like_id`),
  KEY `FKl0jvfq4a7glp0xeuhd8hm05yt` (`blog_id`),
  KEY `FKe4guax66lb963pf27kvm7ikik` (`comment_id`),
  KEY `FKnvx9seeqqyy71bij291pwiwrg` (`user_id`),
  CONSTRAINT `FKe4guax66lb963pf27kvm7ikik` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`comment_id`),
  CONSTRAINT `FKl0jvfq4a7glp0xeuhd8hm05yt` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`),
  CONSTRAINT `FKnvx9seeqqyy71bij291pwiwrg` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `notification_id` bigint NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `created_at` datetime(6) DEFAULT NULL,
  `global` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `udpated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `order_details_id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` bigint DEFAULT NULL,
  `price` double DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_details_id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (1,255000,1,1);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (2,225000,2,1);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (3,1050000,20,2);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (4,675000,19,2);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (5,270000,7,3);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (6,378000,9,4);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (7,165600,11,4);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (8,142500,12,4);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (9,351000,10,4);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (10,320000,3,5);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (11,332500,4,5);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (12,378000,9,5);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (13,792000,14,6);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (14,760000,15,6);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (15,612000,13,6);
INSERT INTO `order_details` (`order_details_id`, `price`, `course_id`, `order_id`) VALUES (16,280000,8,7);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `order_status` enum('COMPLETED','PENDING') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`order_id`, `created_at`, `order_status`, `updated_at`, `user_id`) VALUES (1,'2025-02-25 02:26:42.881292','COMPLETED','2025-02-25 02:26:44.517111',6);
INSERT INTO `orders` (`order_id`, `created_at`, `order_status`, `updated_at`, `user_id`) VALUES (2,'2025-02-25 02:27:31.678505','COMPLETED','2025-02-25 02:27:32.640592',9);
INSERT INTO `orders` (`order_id`, `created_at`, `order_status`, `updated_at`, `user_id`) VALUES (3,'2025-02-25 02:28:24.702544','COMPLETED','2025-02-25 02:28:27.840493',21);
INSERT INTO `orders` (`order_id`, `created_at`, `order_status`, `updated_at`, `user_id`) VALUES (4,'2025-02-25 02:29:21.527474','COMPLETED','2025-02-25 02:29:23.216122',22);
INSERT INTO `orders` (`order_id`, `created_at`, `order_status`, `updated_at`, `user_id`) VALUES (5,'2025-02-24 02:31:03.417066','COMPLETED','2025-02-24 02:31:39.424105',10);
INSERT INTO `orders` (`order_id`, `created_at`, `order_status`, `updated_at`, `user_id`) VALUES (6,'2025-02-24 02:32:36.311306','COMPLETED','2025-02-24 02:32:39.690530',11);
INSERT INTO `orders` (`order_id`, `created_at`, `order_status`, `updated_at`, `user_id`) VALUES (7,'2025-02-25 02:36:00.252045','COMPLETED','2025-02-25 02:36:03.445583',2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `otp_id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `expired_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`otp_id`),
  UNIQUE KEY `UK4mkxc1wpojj1vymcvurokktwm` (`user_id`),
  CONSTRAINT `FKs0hlsjury48cekfbfusk11lyr` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `permission_id` bigint NOT NULL AUTO_INCREMENT,
  `api_method` enum('DELETE','GET','PATCH','POST','PUT') DEFAULT NULL,
  `api_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `question_id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` (`question_id`, `title`) VALUES (1,'Ngôn ngữ lập trình nào được sử dụng để phát triển ứng dụng Android?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (2,'HTML là viết tắt của cụm từ nào?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (3,'Trong Python, hàm nào được sử dụng để in ra màn hình?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (4,'CSS được sử dụng để làm gì trong phát triển web?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (5,'JavaScript là ngôn ngữ lập trình loại gì?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (6,'Git là gì trong phát triển phần mềm?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (7,'Trong Java, từ khóa \'final\' có ý nghĩa gì?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (8,'API là viết tắt của cụm từ nào?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (9,'Trong cơ sở dữ liệu, SQL là viết tắt của cụm từ nào?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (10,'Framework nào được sử dụng để phát triển ứng dụng web bằng Python?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (11,'Trong lập trình hướng đối tượng, tính đa hình (polymorphism) là gì?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (12,'Trong JavaScript, \'NaN\' có nghĩa là gì?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (13,'Trong C++, từ khóa \'virtual\' được sử dụng để làm gì?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (14,'Trong phát triển web, REST là gì?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (15,'Trong Python, thư viện nào được sử dụng để phân tích dữ liệu?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (16,'Trong Java, \'JVM\' là viết tắt của cụm từ nào?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (17,'Trong lập trình, \'debugging\' là gì?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (18,'Trong CSS, \'flexbox\' được sử dụng để làm gì?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (19,'Trong JavaScript, \'closure\' là gì?');
INSERT INTO `questions` (`question_id`, `title`) VALUES (20,'Trong phát triển ứng dụng di động, \'Flutter\' là gì?');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_attempts`
--

DROP TABLE IF EXISTS `quiz_attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_attempts` (
  `quiz_attempt_id` bigint NOT NULL AUTO_INCREMENT,
  `attempt_number` int DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `quiz_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`quiz_attempt_id`),
  KEY `FKfwipvfipnnwsoacoyv5k7fbxc` (`quiz_id`),
  KEY `FKpj4a9hw0iv1mo1ut6rppg594u` (`user_id`),
  CONSTRAINT `FKfwipvfipnnwsoacoyv5k7fbxc` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`quiz_id`),
  CONSTRAINT `FKpj4a9hw0iv1mo1ut6rppg594u` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_attempts`
--

LOCK TABLES `quiz_attempts` WRITE;
/*!40000 ALTER TABLE `quiz_attempts` DISABLE KEYS */;
/*!40000 ALTER TABLE `quiz_attempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_question`
--

DROP TABLE IF EXISTS `quiz_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_question` (
  `quiz_id` bigint NOT NULL,
  `question_id` bigint NOT NULL,
  PRIMARY KEY (`quiz_id`,`question_id`),
  KEY `FKqeltu3y1r2onimmphk1s8eirs` (`question_id`),
  CONSTRAINT `FKkf4iskp1r4oogyx3cikwdj0i0` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`quiz_id`),
  CONSTRAINT `FKqeltu3y1r2onimmphk1s8eirs` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_question`
--

LOCK TABLES `quiz_question` WRITE;
/*!40000 ALTER TABLE `quiz_question` DISABLE KEYS */;
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,1);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,2);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,3);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,4);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,5);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,6);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,7);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,8);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,9);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,10);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,11);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,12);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,13);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,14);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,15);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,16);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,17);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,18);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,19);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (1,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (2,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (3,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (4,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (5,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (6,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (7,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (8,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (9,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (10,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (11,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (12,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (13,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (14,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (15,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (16,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (17,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (18,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (19,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (20,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (21,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (22,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (23,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (24,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (25,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (26,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (27,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (28,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (29,20);
INSERT INTO `quiz_question` (`quiz_id`, `question_id`) VALUES (30,20);
/*!40000 ALTER TABLE `quiz_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizzes`
--

DROP TABLE IF EXISTS `quizzes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quizzes` (
  `quiz_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `ended_at` datetime(6) DEFAULT NULL,
  `max_attempts` int DEFAULT NULL,
  `published` bit(1) DEFAULT NULL,
  `started_at` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `udpated_at` datetime(6) DEFAULT NULL,
  `chapter_id` bigint DEFAULT NULL,
  `expert_id` bigint DEFAULT NULL,
  PRIMARY KEY (`quiz_id`),
  KEY `FKbfcxv33pl1gl32wie5nobns7r` (`chapter_id`),
  KEY `FKjn5yj81ek8yx2s07hkje2wd98` (`expert_id`),
  CONSTRAINT `FKbfcxv33pl1gl32wie5nobns7r` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`chapter_id`),
  CONSTRAINT `FKjn5yj81ek8yx2s07hkje2wd98` FOREIGN KEY (`expert_id`) REFERENCES `experts` (`expert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizzes`
--

LOCK TABLES `quizzes` WRITE;
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (1,'2025-02-14 10:56:52.042266',NULL,3,_binary '\0',NULL,'Bài kiểm tra số 1',NULL,1,1);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (2,'2025-02-14 10:56:52.042266',NULL,3,_binary '\0',NULL,'Bài kiểm tra số 2',NULL,1,2);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (3,'2025-02-14 10:56:52.043266',NULL,3,_binary '\0',NULL,'Bài kiểm tra số 3',NULL,1,3);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (4,'2025-02-14 10:56:52.043266',NULL,2,_binary '\0',NULL,'Bài kiểm tra số 4',NULL,1,4);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (5,'2025-02-14 10:56:52.043266',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 5',NULL,1,5);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (6,'2025-02-14 10:56:52.044267',NULL,3,_binary '\0',NULL,'Bài kiểm tra số 6',NULL,1,6);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (7,'2025-02-14 10:56:52.044267',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 7',NULL,1,7);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (8,'2025-02-14 10:56:52.044267',NULL,2,_binary '\0',NULL,'Bài kiểm tra số 8',NULL,1,8);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (9,'2025-02-14 10:56:52.044267',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 9',NULL,1,9);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (10,'2025-02-14 10:56:52.045267',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 10',NULL,1,10);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (11,'2025-02-14 10:56:52.045267',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 11',NULL,1,11);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (12,'2025-02-14 10:56:52.045267',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 12',NULL,1,12);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (13,'2025-02-14 10:56:52.046267',NULL,2,_binary '\0',NULL,'Bài kiểm tra số 13',NULL,1,13);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (14,'2025-02-14 10:56:52.046267',NULL,2,_binary '\0',NULL,'Bài kiểm tra số 14',NULL,1,14);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (15,'2025-02-14 10:56:52.046267',NULL,2,_binary '\0',NULL,'Bài kiểm tra số 15',NULL,1,15);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (16,'2025-02-14 10:56:52.046267',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 16',NULL,1,16);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (17,'2025-02-14 10:56:52.047265',NULL,2,_binary '\0',NULL,'Bài kiểm tra số 17',NULL,1,17);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (18,'2025-02-14 10:56:52.047265',NULL,3,_binary '\0',NULL,'Bài kiểm tra số 18',NULL,1,18);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (19,'2025-02-14 10:56:52.047265',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 19',NULL,1,19);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (20,'2025-02-14 10:56:52.047265',NULL,3,_binary '\0',NULL,'Bài kiểm tra số 20',NULL,1,20);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (21,'2025-02-14 10:56:52.048265',NULL,2,_binary '\0',NULL,'Bài kiểm tra số 21',NULL,1,21);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (22,'2025-02-14 10:56:52.048265',NULL,2,_binary '\0',NULL,'Bài kiểm tra số 22',NULL,1,22);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (23,'2025-02-14 10:56:52.048265',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 23',NULL,1,23);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (24,'2025-02-14 10:56:52.048265',NULL,3,_binary '\0',NULL,'Bài kiểm tra số 24',NULL,1,24);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (25,'2025-02-14 10:56:52.049268',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 25',NULL,1,25);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (26,'2025-02-14 10:56:52.049268',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 26',NULL,1,26);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (27,'2025-02-14 10:56:52.049268',NULL,2,_binary '\0',NULL,'Bài kiểm tra số 27',NULL,1,27);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (28,'2025-02-14 10:56:52.049268',NULL,2,_binary '\0',NULL,'Bài kiểm tra số 28',NULL,1,28);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (29,'2025-02-14 10:56:52.050266',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 29',NULL,1,29);
INSERT INTO `quizzes` (`quiz_id`, `created_at`, `ended_at`, `max_attempts`, `published`, `started_at`, `title`, `udpated_at`, `expert_id`, `chapter_id`) VALUES (30,'2025-02-14 10:56:52.050266',NULL,1,_binary '\0',NULL,'Bài kiểm tra số 30',NULL,1,30);
/*!40000 ALTER TABLE `quizzes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rates`
--

DROP TABLE IF EXISTS `rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rates` (
  `rate_id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `stars` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`rate_id`),
  KEY `FKt5wuc6askynohbdaqeaj5wjeq` (`course_id`),
  KEY `FKanlgavwqngljux10mtly8qr6f` (`user_id`),
  CONSTRAINT `FKanlgavwqngljux10mtly8qr6f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKt5wuc6askynohbdaqeaj5wjeq` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rates`
--

LOCK TABLES `rates` WRITE;
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (21,'Khóa học không hữu ích như mong đợi.','2025-02-14 10:56:52.684130',1,'2025-02-14 10:56:52.684130',1,1);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (22,'Nội dung khá cơ bản, chưa thực sự chuyên sâu.','2025-02-14 10:56:52.684130',2,'2025-02-14 10:56:52.684130',1,2);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (23,'Khóa học tạm ổn, có thể cải thiện thêm.','2025-02-14 10:56:52.684130',3,'2025-02-14 10:56:52.684130',1,3);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (24,'Giảng viên dạy dễ hiểu, kiến thức hữu ích.','2025-02-14 10:56:52.684130',4,'2025-02-14 10:56:52.684130',1,4);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (25,'Khóa học rất tuyệt vời, đáng để học!','2025-02-14 10:56:52.684130',5,'2025-02-14 10:56:52.684130',1,5);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (26,'Khóa học rất hữu ích, giảng viên dạy dễ hiểu.','2025-02-14 10:56:52.688130',5,'2025-02-14 10:56:52.688130',2,15);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (27,'Nội dung khá ổn nhưng có thể cải thiện thêm.','2025-02-14 10:56:52.688130',3,'2025-02-14 10:56:52.688130',2,8);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (28,'Bài giảng rõ ràng nhưng phần thực hành hơi ít.','2025-02-14 10:56:52.688130',3,'2025-02-14 10:56:52.688130',2,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (29,'Khóa học tốt, đáng để học thử!','2025-02-14 10:56:52.688130',4,'2025-02-14 10:56:52.688130',2,3);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (30,'Chất lượng giảng dạy tuyệt vời!','2025-02-14 10:56:52.688130',5,'2025-02-14 10:56:52.688130',2,19);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (31,'Khóa học chưa thực sự như mong đợi.','2025-02-14 10:56:52.691671',2,'2025-02-14 10:56:52.691671',3,7);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (32,'Nội dung hơi sơ sài, cần bổ sung thêm.','2025-02-14 10:56:52.691671',2,'2025-02-14 10:56:52.691671',3,14);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (33,'Bài giảng khá nhàm chán, chưa hấp dẫn.','2025-02-14 10:56:52.691671',2,'2025-02-14 10:56:52.691671',3,9);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (34,'Hơi thất vọng vì thiếu phần thực hành.','2025-02-14 10:56:52.691671',2,'2025-02-14 10:56:52.691671',3,18);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (35,'Giảng viên giảng hơi nhanh, khó hiểu.','2025-02-14 10:56:52.691671',2,'2025-02-14 10:56:52.691671',3,5);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (36,'Khóa học ở mức trung bình, không quá xuất sắc.','2025-02-14 10:56:52.695635',3,'2025-02-14 10:56:52.695635',4,11);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (37,'Bài giảng chưa đủ sâu, nhưng tạm chấp nhận.','2025-02-14 10:56:52.695635',2,'2025-02-14 10:56:52.695635',4,7);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (38,'Chất lượng giảng dạy tốt, nhưng giá hơi cao.','2025-02-14 10:56:52.695635',4,'2025-02-14 10:56:52.695635',4,19);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (39,'Nội dung cần cải thiện thêm, nhưng ổn.','2025-02-14 10:56:52.695635',3,'2025-02-14 10:56:52.695635',4,5);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (40,'Khóa học rất hay, đáng giá từng đồng!','2025-02-14 10:56:52.695635',5,'2025-02-14 10:56:52.695635',4,2);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (41,'Khóa học rất hữu ích, kiến thức thực tế.','2025-02-14 10:56:52.699635',5,'2025-02-14 10:56:52.699635',5,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (42,'Giảng viên dạy dễ hiểu, nhưng tốc độ hơi nhanh.','2025-02-14 10:56:52.699635',4,'2025-02-14 10:56:52.699635',5,8);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (43,'Nội dung cần cập nhật thêm, hơi lỗi thời.','2025-02-14 10:56:52.699635',3,'2025-02-14 10:56:52.699635',5,17);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (44,'Bài giảng chưa đủ sâu, chưa đi vào thực tế.','2025-02-14 10:56:52.699635',2,'2025-02-14 10:56:52.699635',5,6);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (45,'Hoàn toàn không đáng tiền, thất vọng.','2025-02-14 10:56:52.699635',1,'2025-02-14 10:56:52.699635',5,14);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (46,'Chất lượng khóa học không như mong đợi.','2025-02-14 10:56:52.703635',2,'2025-02-14 10:56:52.703635',6,10);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (47,'Bài giảng cần chi tiết hơn, còn hơi sơ sài.','2025-02-14 10:56:52.703635',3,'2025-02-14 10:56:52.703635',6,4);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (48,'Khóa học ổn, nhưng có thể cải thiện hơn.','2025-02-14 10:56:52.703635',4,'2025-02-14 10:56:52.703635',6,15);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (49,'Giảng viên giảng rất dễ hiểu, rất hài lòng!','2025-02-14 10:56:52.703635',5,'2025-02-14 10:56:52.703635',6,7);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (50,'Nội dung bài học rất hữu ích, đáng để học.','2025-02-14 10:56:52.703635',5,'2025-02-14 10:56:52.703635',6,20);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (51,'Khóa học chất lượng, đáng giá tiền!','2025-02-14 10:56:52.706635',5,'2025-02-14 10:56:52.706635',7,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (52,'Học phí rẻ nhưng nội dung rất tốt.','2025-02-14 10:56:52.706635',4,'2025-02-14 10:56:52.706635',7,6);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (53,'Nội dung khá cơ bản, chưa chuyên sâu.','2025-02-14 10:56:52.706635',3,'2025-02-14 10:56:52.706635',7,18);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (54,'Cần cập nhật thêm bài học mới.','2025-02-14 10:56:52.706635',2,'2025-02-14 10:56:52.706635',7,9);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (55,'Không đáng tiền lắm, hơi sơ sài.','2025-02-14 10:56:52.706635',1,'2025-02-14 10:56:52.706635',7,3);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (56,'Khóa học này không như mong đợi.','2025-02-14 10:56:52.711214',2,'2025-02-14 10:56:52.711214',8,5);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (57,'Giá hơi cao so với nội dung.','2025-02-14 10:56:52.711214',3,'2025-02-14 10:56:52.711214',8,14);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (58,'Không có nhiều kiến thức mới.','2025-02-14 10:56:52.711214',2,'2025-02-14 10:56:52.711214',8,7);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (59,'Khá ổn nhưng cần cải thiện.','2025-02-14 10:56:52.711214',3,'2025-02-14 10:56:52.711214',8,19);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (60,'Chưa thực sự hữu ích lắm.','2025-02-14 10:56:52.711214',1,'2025-02-14 10:56:52.711214',8,2);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (61,'Khóa học rất đáng tiền!','2025-02-14 10:56:52.715218',5,'2025-02-14 10:56:52.715218',9,3);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (62,'Không ngờ lại hay đến vậy.','2025-02-14 10:56:52.715218',4,'2025-02-14 10:56:52.715218',9,17);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (63,'Nội dung súc tích, dễ hiểu.','2025-02-14 10:56:52.715218',5,'2025-02-14 10:56:52.715218',9,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (64,'Mình học xong có thể áp dụng ngay.','2025-02-14 10:56:52.715218',5,'2025-02-14 10:56:52.715218',9,8);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (65,'Giảng viên rất có tâm.','2025-02-14 10:56:52.715218',4,'2025-02-14 10:56:52.715218',9,5);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (66,'Khóa học rất bổ ích!','2025-02-14 10:56:52.719217',5,'2025-02-14 10:56:52.719217',10,2);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (67,'Chất lượng nội dung rất tốt.','2025-02-14 10:56:52.719217',4,'2025-02-14 10:56:52.719217',10,15);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (68,'Bài giảng hơi khó hiểu.','2025-02-14 10:56:52.719217',3,'2025-02-14 10:56:52.719217',10,8);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (69,'Giảng viên nhiệt tình nhưng hơi nhanh.','2025-02-14 10:56:52.719217',4,'2025-02-14 10:56:52.719217',10,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (70,'Nội dung quá hay, cảm ơn giảng viên.','2025-02-14 10:56:52.719217',5,'2025-02-14 10:56:52.719217',10,6);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (71,'Khóa học rất bổ ích!','2025-02-14 10:56:52.723183',5,'2025-02-14 10:56:52.723183',11,3);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (72,'Chất lượng nội dung rất tốt.','2025-02-14 10:56:52.723183',4,'2025-02-14 10:56:52.723183',11,17);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (73,'Bài giảng hơi khó hiểu.','2025-02-14 10:56:52.723183',3,'2025-02-14 10:56:52.723183',11,10);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (74,'Giảng viên nhiệt tình nhưng hơi nhanh.','2025-02-14 10:56:52.723183',4,'2025-02-14 10:56:52.723183',11,6);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (75,'Nội dung quá hay, cảm ơn giảng viên.','2025-02-14 10:56:52.723183',5,'2025-02-14 10:56:52.723183',11,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (76,'Dạy chán quá!','2025-02-14 10:56:52.727183',1,'2025-02-14 10:56:52.727183',12,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (77,'Bài giảng khó hiểu.','2025-02-14 10:56:52.727183',2,'2025-02-14 10:56:52.727183',12,5);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (78,'Không đáng tiền.','2025-02-14 10:56:52.727183',1,'2025-02-14 10:56:52.727183',12,8);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (79,'Không hài lòng lắm.','2025-02-14 10:56:52.727183',2,'2025-02-14 10:56:52.727183',12,15);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (80,'Cần cải thiện nội dung.','2025-02-14 10:56:52.727183',3,'2025-02-14 10:56:52.727183',12,18);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (81,'Khóa học này rất hay!','2025-02-14 10:56:52.730183',5,'2025-02-14 10:56:52.730183',13,13);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (82,'Nội dung hữu ích, nhưng cần cải thiện.','2025-02-14 10:57:00.123456',4,'2025-02-14 10:57:00.123456',13,14);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (83,'Giảng viên giảng dễ hiểu.','2025-02-14 10:57:05.654321',5,'2025-02-14 10:57:05.654321',13,15);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (84,'Bài tập thực hành còn ít.','2025-02-14 10:57:10.789012',3,'2025-02-14 10:57:10.789012',13,16);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (85,'Chưa thực sự chuyên sâu.','2025-02-14 10:57:15.111213',3,'2025-02-14 10:57:15.111213',13,17);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (86,'Khóa học rất bổ ích','2025-02-14 10:56:52.734183',5,'2025-02-14 10:56:52.734183',14,7);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (87,'Bài giảng dễ hiểu','2025-02-14 10:56:52.734183',4,'2025-02-14 10:56:52.734183',14,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (88,'Nội dung cần cập nhật thêm','2025-02-14 10:56:52.734183',3,'2025-02-14 10:56:52.734183',14,5);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (89,'Hơi khó hiểu với người mới','2025-02-14 10:56:52.734183',2,'2025-02-14 10:56:52.734183',14,18);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (90,'Không đáng tiền','2025-02-14 10:56:52.734183',1,'2025-02-14 10:56:52.734183',14,9);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (91,'Khóa học rất đáng giá','2025-02-14 10:56:52.737661',5,'2025-02-14 10:56:52.737661',15,3);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (92,'Giảng viên giảng khá dễ hiểu','2025-02-14 10:56:52.737661',4,'2025-02-14 10:56:52.737661',15,7);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (93,'Chất lượng bài học trung bình','2025-02-14 10:56:52.737661',3,'2025-02-14 10:56:52.737661',15,14);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (94,'Nội dung chưa thực sự hấp dẫn','2025-02-14 10:56:52.737661',2,'2025-02-14 10:56:52.737661',15,19);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (95,'Không đáng tiền','2025-02-14 10:56:52.737661',1,'2025-02-14 10:56:52.737661',15,10);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (96,'Khóa học rất hữu ích','2025-02-14 10:56:52.740692',5,'2025-02-14 10:56:52.740692',16,4);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (97,'Nội dung cần bổ sung thêm','2025-02-14 10:56:52.740692',3,'2025-02-14 10:56:52.740692',16,11);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (98,'Giảng viên giảng dễ hiểu','2025-02-14 10:56:52.740692',4,'2025-02-14 10:56:52.740692',16,7);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (99,'Không phù hợp với người mới','2025-02-14 10:56:52.740692',2,'2025-02-14 10:56:52.740692',16,19);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (100,'Không đáng tiền','2025-02-14 10:56:52.740692',1,'2025-02-14 10:56:52.740692',16,15);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (101,'Khóa học rất thú vị','2025-02-14 10:56:52.744701',5,'2025-02-14 10:56:52.744701',17,6);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (102,'Nội dung chưa thực sự hấp dẫn','2025-02-14 10:56:52.744701',3,'2025-02-14 10:56:52.744701',17,14);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (103,'Hơi khó hiểu với người mới','2025-02-14 10:56:52.744701',2,'2025-02-14 10:56:52.744701',17,9);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (104,'Giảng viên dạy dễ hiểu','2025-02-14 10:56:52.744701',4,'2025-02-14 10:56:52.744701',17,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (105,'Không đáng tiền','2025-02-14 10:56:52.744701',1,'2025-02-14 10:56:52.744701',17,20);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (106,'Nội dung rất bổ ích','2025-02-14 10:56:52.748692',5,'2025-02-14 10:56:52.748692',18,3);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (107,'Bài giảng hơi nhanh, khó theo kịp','2025-02-14 10:56:52.748692',2,'2025-02-14 10:56:52.748692',18,17);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (108,'Khóa học này giúp tôi hiểu rõ hơn','2025-02-14 10:56:52.748692',4,'2025-02-14 10:56:52.748692',18,8);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (109,'Không phù hợp với mong đợi của tôi','2025-02-14 10:56:52.748692',1,'2025-02-14 10:56:52.748692',18,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (110,'Giảng viên giảng rất dễ hiểu','2025-02-14 10:56:52.748692',5,'2025-02-14 10:56:52.748692',18,6);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (111,'Khóa học thực sự rất hữu ích!','2025-02-14 10:56:52.758692',5,'2025-02-14 10:56:52.758692',19,7);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (112,'Nội dung ổn nhưng thiếu bài tập','2025-02-14 10:56:52.758692',3,'2025-02-14 10:56:52.758692',19,14);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (113,'Chất lượng giảng dạy tốt, rất đáng tiền','2025-02-14 10:56:52.758692',4,'2025-02-14 10:56:52.758692',19,2);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (114,'Khóa học khá hay, nhưng hơi khó hiểu','2025-02-14 10:56:52.758692',2,'2025-02-14 10:56:52.758692',19,11);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (115,'Thầy dạy rất nhiệt tình và dễ hiểu','2025-02-14 10:56:52.758692',5,'2025-02-14 10:56:52.758692',19,5);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (116,'Không phù hợp với người mới bắt đầu','2025-02-14 10:56:52.758692',1,'2025-02-14 10:56:52.758692',20,18);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (117,'Bài giảng rõ ràng, dễ hiểu nhưng cần thêm ví dụ thực tế','2025-02-14 10:56:52.758692',4,'2025-02-14 10:56:52.758692',20,9);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (118,'Chất lượng kém, không đáng tiền','2025-02-14 10:56:52.758692',1,'2025-02-14 10:56:52.758692',20,16);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (119,'Nội dung khá cơ bản, phù hợp cho người mới','2025-02-14 10:56:52.760692',3,'2025-02-14 10:56:52.760692',20,12);
INSERT INTO `rates` (`rate_id`, `content`, `created_at`, `stars`, `updated_at`, `course_id`, `user_id`) VALUES (120,'Khóa học tuyệt vời! Mình rất hài lòng','2025-02-14 10:56:52.760692',5,'2025-02-14 10:56:52.760692',20,8);
/*!40000 ALTER TABLE `rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  KEY `FK2xn8qv4vw30i04xdxrpvn3bdi` (`permission_id`),
  KEY `FKtfgq8q9blrp0pt1pvggyli3v9` (`role_id`),
  CONSTRAINT `FK2xn8qv4vw30i04xdxrpvn3bdi` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`permission_id`),
  CONSTRAINT `FKtfgq8q9blrp0pt1pvggyli3v9` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` enum('ADMIN','EXPERT','MARKETING','USER') DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`role_id`, `role_name`) VALUES (1,'ADMIN');
INSERT INTO `roles` (`role_id`, `role_name`) VALUES (2,'USER');
INSERT INTO `roles` (`role_id`, `role_name`) VALUES (3,'EXPERT');
INSERT INTO `roles` (`role_id`, `role_name`) VALUES (4,'MARKETING');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjects` (
  `subject_id` bigint NOT NULL AUTO_INCREMENT,
  `description` mediumtext,
  `subject_name` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (1,'Ngôn ngữ lập trình phổ biến, chạy trên JVM.','Java','java.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (2,'Ngôn ngữ lập trình dễ học, mạnh mẽ cho AI, Data Science.','Python','python.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (3,'Ngôn ngữ lập trình chính cho web frontend.','JavaScript','javascript.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (4,'Ngôn ngữ lập trình mạnh mẽ cho hệ thống và game.','C++','cplus.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (5,'Ngôn ngữ phát triển ứng dụng trên nền tảng Microsoft.','C#','csharp.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (6,'Thư viện JavaScript phát triển UI động.','React JS','reactjs.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (7,'Framework React hỗ trợ SSR và SEO tốt.','Next JS','nextjs.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (8,'Framework Java để phát triển ứng dụng web nhanh chóng.','Spring Boot','springboot.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (9,'Ngôn ngữ lập trình phổ biến cho backend web.','PHP','php.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (10,'Ngôn ngữ lập trình linh hoạt, thường dùng với Rails.','Ruby','ruby.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (11,'Ngôn ngữ đánh dấu để xây dựng trang web.','HTML','html.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (12,'Phiên bản nâng cao của JavaScript với kiểu tĩnh.','TypeScript','typescript.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (13,'Ngôn ngữ tạo kiểu cho trang web.','CSS','css.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (14,'Tiền xử lý CSS giúp viết CSS dễ dàng hơn.','SASS','sass.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (15,'Ngôn ngữ lập trình chính thức cho Android.','Kotlin','kotlin.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (16,'Ngôn ngữ lập trình chính thức cho iOS.','Swift','swift.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (17,'Framework Node.js để xây dựng backend hiệu quả.','Nest JS','nestjs.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (18,'Hệ quản trị cơ sở dữ liệu quan hệ phổ biến.','My SQL','mysql.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (19,'Cơ sở dữ liệu NoSQL dạng document.','MongoDB','mongodb.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (20,'Framework JavaScript để xây dựng UI nhanh chóng.','Vue JS','vuejs.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (21,'Framework CSS tiện lợi, hỗ trợ thiết kế nhanh chóng.','Tailwind CSS','tailwindcss.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (22,'Thư viện UI cho React, dựa trên Material Design.','MUI','mui.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (23,'Thư viện UI mạnh mẽ dành cho React.','Ant Design','antd.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (24,'Framework CSS phổ biến giúp phát triển web nhanh.','Bootstrap','bootstrap.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (25,'Ngôn ngữ lập trình mạnh mẽ, nền tảng cho nhiều ngôn ngữ khác.','C','c.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (26,'Nền tảng container hóa giúp triển khai và quản lý ứng dụng dễ dàng hơn.','Docker','docker.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (27,'Ngôn ngữ lập trình mã nguồn mở do Google phát triển, nổi bật với hiệu suất cao, cú pháp đơn giản và hỗ trợ xử lý đồng thời (concurrency) mạnh mẽ','Go','golang.png');
INSERT INTO `subjects` (`subject_id`, `description`, `subject_name`, `thumbnail`) VALUES (28,'Nền tảng mã nguồn mở giúp tự động triển khai, mở rộng và quản lý các ứng dụng container.','Kubernates','kubernates.png');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_answers`
--

DROP TABLE IF EXISTS `user_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_answers` (
  `user_answer_id` bigint NOT NULL AUTO_INCREMENT,
  `answer_id` bigint DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  `quiz_attempt_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_answer_id`),
  KEY `FKq9ubv2ar56hkwxokdbp72b5by` (`answer_id`),
  KEY `FK6b46l4bb7a6wfxvmn6l7ig8vo` (`question_id`),
  KEY `FKqy4lhxwoi677jc3u95au6qmxw` (`quiz_attempt_id`),
  CONSTRAINT `FK6b46l4bb7a6wfxvmn6l7ig8vo` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`),
  CONSTRAINT `FKq9ubv2ar56hkwxokdbp72b5by` FOREIGN KEY (`answer_id`) REFERENCES `answers` (`answer_id`),
  CONSTRAINT `FKqy4lhxwoi677jc3u95au6qmxw` FOREIGN KEY (`quiz_attempt_id`) REFERENCES `quiz_attempts` (`quiz_attempt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_answers`
--

LOCK TABLES `user_answers` WRITE;
/*!40000 ALTER TABLE `user_answers` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notifications`
--

DROP TABLE IF EXISTS `user_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_notifications` (
  `user_notification_id` bigint NOT NULL AUTO_INCREMENT,
  `is_read` bit(1) DEFAULT NULL,
  `notification_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_notification_id`),
  KEY `FKovvx0ab3h8s9lrm6fppuadn7d` (`notification_id`),
  KEY `FK9f86wonnl11hos1cuf5fibutl` (`user_id`),
  CONSTRAINT `FK9f86wonnl11hos1cuf5fibutl` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKovvx0ab3h8s9lrm6fppuadn7d` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notifications`
--

LOCK TABLES `user_notifications` WRITE;
/*!40000 ALTER TABLE `user_notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_progress`
--

DROP TABLE IF EXISTS `user_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_progress` (
  `progress_id` bigint NOT NULL AUTO_INCREMENT,
  `chapter_id` bigint DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `lesson_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`progress_id`),
  KEY `FKrt37sneeps21829cuqetjm5ye` (`user_id`),
  CONSTRAINT `FKrt37sneeps21829cuqetjm5ye` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_progress`
--

LOCK TABLES `user_progress` WRITE;
/*!40000 ALTER TABLE `user_progress` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `account_type` enum('CREDENTIALS','GITHUB','GOOGLE') DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `gender` enum('FEMALE','MALE') DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `refresh_token` mediumtext,
  `updated_at` datetime(6) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (1,'CREDENTIALS',_binary '','truc.jpg','2025-02-14 10:56:50.769286',NULL,'trucnvhe180248@fpt.edu.vn','Nguyen Vuong Truc Admin','MALE',_binary '\0','$2a$10$tC4IhQM1AjJdsnCS4Lgu5uRY6.ZNNuPi.9dUrLBgrhetOag3/X7o6',NULL,NULL,1);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (2,'CREDENTIALS',_binary '','cuong.jpg','2025-02-14 10:56:50.772279',NULL,'cuongdo13042004@gmail.com','Do Xuan Cuong Admin','MALE',_binary '\0','$2a$10$vZJfI4a59Wah2IqC/CTxlOLCZnRRa9d2rjgmhkyFJT08es44SE8dK','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdW9uZ2RvMTMwNDIwMDRAZ21haWwuY29tIiwiZXhwIjoxNzQ5MDkwOTA5LCJpYXQiOjE3NDA0NTA5MDksImFjY291bnRUeXBlIjoiQ1JFREVOVElBTFMifQ.f0fJuntCGOebRvHiR3KhJLbt5Fo05d2EhCwzPwrQT0PwIcLt-Xccoq1HjY5y_-JlST3lgMdklBhEQh9zTBIREA','2025-02-25 02:35:09.579696',1);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (3,'CREDENTIALS',_binary '','dung.jpg','2025-02-14 10:56:50.773282',NULL,'dung06032004@gmail.com','Tran Nam Dung Admin','MALE',_binary '\0','$2a$10$O0AjuLpnr0fAgWcR3jYrJeYgpvU8p5FBNA5Z2usBYuXrl/XOof/wC',NULL,NULL,1);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (4,'CREDENTIALS',_binary '','duc.jpg','2025-02-14 10:56:50.775282',NULL,'ducnhhe186325@fpt.edu.vn','Nong Hoang Duc Admin','MALE',_binary '\0','$2a$10$D7tO60G8ZchfhgkNHpebpOBWiG/znZzUcp7ozUc5jYkn7Z/VG1XFi',NULL,NULL,1);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (5,'CREDENTIALS',_binary '','truong.jpg','2025-02-14 10:56:50.775282',NULL,'luongtruong15122004@gmail.com','Luong Hoang Truong Admin','MALE',_binary '\0','$2a$10$/PEz3lZpTCTEP54fe1dBVeC6bRUyAbumTTT3JW81VMq3NrYP68h1G',NULL,NULL,1);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (6,'CREDENTIALS',_binary '','truc.jpg','2025-02-14 10:56:50.776283',NULL,'vuongtruc2004@gmail.com','Nguyen Vuong Truc Expert','MALE',_binary '\0','$2a$10$7bK28BYj16lVHFznZf9hX.AKqcnjv4xwaw2tlVhQyu/1rnVUaD7BS',NULL,'2025-02-25 02:27:00.643947',3);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (7,'CREDENTIALS',_binary '','truc.jpg','2025-02-14 10:56:50.777283',NULL,'vuongtruc20042@gmail.com','Vuong Truc Expert','MALE',_binary '\0','$2a$10$iGVslfwYEIBb1mhzwY9InujO.eJNWkBvZGps4aopLQeimga3yLyJ6',NULL,NULL,3);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (8,'CREDENTIALS',_binary '','truc.jpg','2025-02-14 10:56:50.778283',NULL,'vuongtruc20043@gmail.com','Anh Truc Expert','MALE',_binary '\0','$2a$10$W6/.UBd5AxOfx7Ld.n0N1empgiphyOapAQfNQ5Ra1Shd5lGVLvw.m',NULL,NULL,3);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (9,'CREDENTIALS',_binary '','truc.jpg','2025-02-14 10:56:50.779282',NULL,'vuongtruc2008@gmail.com','Nguyen Vuong Truc Marketing','MALE',_binary '\0','$2a$10$juJQTIE2Pz9KCqqq6MdJWeKrH8Q.FXnS0cd2sLm1ZmqATGQQdnyU6',NULL,'2025-02-25 02:27:43.701949',4);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (10,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.780282',NULL,'1@gmail.com','Nguyen Vuong Truc 1','FEMALE',_binary '\0','$2a$10$ZlDuG4L9DbhAD1AtLqS5vO8/I2ZTO8s8jb/8rXgB6shlMm7d.N3b2',NULL,'2025-02-25 02:31:58.338138',2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (11,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.781283','2016-02-18 00:00:00.000000','2@gmail.com','Nguyen Vuong Truc 2','FEMALE',_binary '\0','$2a$10$ZJwsYFeXLvWTfAlItg/tRuQGBYb0cFICVYWvj8Ovr0ngXXPYqjQW6',NULL,'2025-02-25 02:35:02.343307',2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (12,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.784283',NULL,'3@gmail.com','Nguyen Vuong Truc 3','FEMALE',_binary '\0','$2a$10$ddgj0a/Ku6W2lIrswaYVJegGwh2NRitrTle.OVSX.BwEt.9OWvhRO',NULL,NULL,2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (13,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.784283',NULL,'4@gmail.com','Nguyen Vuong Truc 4','MALE',_binary '\0','$2a$10$TuasB4q/WxQAhhg6xsMobuDGyVB3Abfzk7lTzG9icBQQLQUoY4OrG',NULL,NULL,2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (14,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.785282',NULL,'5@gmail.com','Nguyen Vuong Truc 5','FEMALE',_binary '\0','$2a$10$OBhosIl/a7g9yFqf86g0I.Wp6HgVc/qicPPksdBk26LfrUhKF2VWG',NULL,NULL,2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (15,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.786282',NULL,'6@gmail.com','Nguyen Vuong Truc 6','MALE',_binary '\0','$2a$10$Iw.fn.7bEWck29yTAxD9ieTHLJeIWRNEaqsBv0l4906uedUcMTtwK',NULL,NULL,2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (16,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.787283',NULL,'7@gmail.com','Nguyen Vuong Truc 7','FEMALE',_binary '\0','$2a$10$IeyD85BhXGFP8ewqgE0vvOhByHBRzkgNBCYfvvbkRBaB/SvkCIJrm',NULL,NULL,2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (17,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.788282',NULL,'8@gmail.com','Nguyen Vuong Truc 8','MALE',_binary '\0','$2a$10$uJo2XAARVs3zB/7o5Z3xA.deaMtByis.v46uZ1Z.aY/4BhCskKlNy',NULL,NULL,2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (18,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.788282',NULL,'9@gmail.com','Nguyen Vuong Truc 9','FEMALE',_binary '\0','$2a$10$7OLvM.fezw/n.UnR9dfyRek0NWCyphhPstuls0S6GKRUkoEMwnYQe',NULL,NULL,2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (19,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.790362',NULL,'10@gmail.com','Nguyen Vuong Truc 10','MALE',_binary '\0','$2a$10$qXua6F2XD/AjEaNgtCim8.8he0iXsjYqelwbMcHerLI6pAcCF7W8e',NULL,NULL,2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (20,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.791363',NULL,'11@gmail.com','Nguyen Vuong Truc 11','FEMALE',_binary '\0','$2a$10$.jNRupXxR.8U73lToXuKTu4/s1tnwTNi55zWq2FZL7i0U9nRMqMwC',NULL,NULL,2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (21,'GOOGLE',_binary '','https://lh3.googleusercontent.com/a/ACg8ocINA6x0fKz0WIb9x7RyQST-hxYZjvUicoCpLVKlUE_w-zfg1zw=s96-c','2025-02-25 02:27:51.580424','2004-02-20 00:00:00.000000','trucnvhe180248@fpt.edu.vn','Nguyen Vuong Truc (K18 HL)','MALE',_binary '\0',NULL,NULL,'2025-02-25 02:28:53.652993',2);
INSERT INTO `users` (`user_id`, `account_type`, `active`, `avatar`, `created_at`, `dob`, `email`, `fullname`, `gender`, `locked`, `password`, `refresh_token`, `updated_at`, `role_id`) VALUES (22,'GITHUB',_binary '','https://avatars.githubusercontent.com/u/153248550?v=4','2025-02-25 02:28:58.692734',NULL,'trucnvhe180248@fpt.edu.vn','Vuong Truc (mail truong)',NULL,_binary '\0',NULL,NULL,'2025-02-25 02:30:34.535081',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-26  9:33:36
