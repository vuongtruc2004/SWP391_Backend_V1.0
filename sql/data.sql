-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: online-learning-uat
-- ------------------------------------------------------
-- Server version	8.0.40
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
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK3erw1a3t0r78st8ty27x6v3g1` (`question_id`),
  CONSTRAINT `FK3erw1a3t0r78st8ty27x6v3g1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,'Câu trả lời D',_binary '',1,NULL,NULL),(2,'Câu trả lời F',_binary '',1,NULL,NULL),(3,'Câu trả lời E',_binary '',1,NULL,NULL),(4,'Câu trả lời C',_binary '',1,NULL,NULL),(5,'Câu trả lời A',_binary '',1,NULL,NULL),(6,'Câu trả lời B',_binary '',1,NULL,NULL),(7,'Câu trả lời A',_binary '\0',2,NULL,NULL),(8,'Câu trả lời B',_binary '\0',2,NULL,NULL),(9,'Câu trả lời D',_binary '\0',2,NULL,NULL),(10,'Câu trả lời E',_binary '\0',2,NULL,NULL),(11,'Câu trả lời F',_binary '\0',2,NULL,NULL),(12,'Câu trả lời C',_binary '\0',2,NULL,NULL),(13,'Câu trả lời A',_binary '\0',3,NULL,NULL),(14,'Câu trả lời F',_binary '\0',3,NULL,NULL),(15,'Câu trả lời B',_binary '\0',3,NULL,NULL),(16,'Câu trả lời D',_binary '\0',3,NULL,NULL),(17,'Câu trả lời E',_binary '\0',3,NULL,NULL),(18,'Câu trả lời C',_binary '\0',3,NULL,NULL),(19,'Câu trả lời C',_binary '',4,NULL,NULL),(20,'Câu trả lời A',_binary '',4,NULL,NULL),(21,'Câu trả lời D',_binary '',4,NULL,NULL),(22,'Câu trả lời F',_binary '',4,NULL,NULL),(23,'Câu trả lời B',_binary '',4,NULL,NULL),(24,'Câu trả lời E',_binary '',4,NULL,NULL),(25,'Câu trả lời B',_binary '',5,NULL,NULL),(26,'Câu trả lời F',_binary '',5,NULL,NULL),(27,'Câu trả lời C',_binary '',5,NULL,NULL),(28,'Câu trả lời D',_binary '',5,NULL,NULL),(29,'Câu trả lời E',_binary '',5,NULL,NULL),(30,'Câu trả lời A',_binary '',5,NULL,NULL),(31,'Câu trả lời C',_binary '',6,NULL,NULL),(32,'Câu trả lời A',_binary '',6,NULL,NULL),(33,'Câu trả lời B',_binary '',6,NULL,NULL),(34,'Câu trả lời F',_binary '',6,NULL,NULL),(35,'Câu trả lời E',_binary '',6,NULL,NULL),(36,'Câu trả lời D',_binary '',6,NULL,NULL),(37,'Câu trả lời B',_binary '\0',7,NULL,NULL),(38,'Câu trả lời E',_binary '\0',7,NULL,NULL),(39,'Câu trả lời A',_binary '\0',7,NULL,NULL),(40,'Câu trả lời F',_binary '\0',7,NULL,NULL),(41,'Câu trả lời C',_binary '\0',7,NULL,NULL),(42,'Câu trả lời D',_binary '\0',7,NULL,NULL),(43,'Câu trả lời A',_binary '\0',8,NULL,NULL),(44,'Câu trả lời B',_binary '\0',8,NULL,NULL),(45,'Câu trả lời E',_binary '\0',8,NULL,NULL),(46,'Câu trả lời D',_binary '\0',8,NULL,NULL),(47,'Câu trả lời C',_binary '\0',8,NULL,NULL),(48,'Câu trả lời F',_binary '\0',8,NULL,NULL),(49,'Câu trả lời D',_binary '',9,NULL,NULL),(50,'Câu trả lời E',_binary '',9,NULL,NULL),(51,'Câu trả lời B',_binary '',9,NULL,NULL),(52,'Câu trả lời C',_binary '',9,NULL,NULL),(53,'Câu trả lời F',_binary '',9,NULL,NULL),(54,'Câu trả lời A',_binary '',9,NULL,NULL),(55,'Câu trả lời E',_binary '\0',10,NULL,NULL),(56,'Câu trả lời B',_binary '\0',10,NULL,NULL),(57,'Câu trả lời A',_binary '\0',10,NULL,NULL),(58,'Câu trả lời C',_binary '\0',10,NULL,NULL),(59,'Câu trả lời F',_binary '\0',10,NULL,NULL),(60,'Câu trả lời D',_binary '\0',10,NULL,NULL),(61,'Câu trả lời D',_binary '',11,NULL,NULL),(62,'Câu trả lời A',_binary '',11,NULL,NULL),(63,'Câu trả lời E',_binary '',11,NULL,NULL),(64,'Câu trả lời C',_binary '',11,NULL,NULL),(65,'Câu trả lời F',_binary '',11,NULL,NULL),(66,'Câu trả lời B',_binary '',11,NULL,NULL),(67,'Câu trả lời C',_binary '',12,NULL,NULL),(68,'Câu trả lời A',_binary '',12,NULL,NULL),(69,'Câu trả lời E',_binary '',12,NULL,NULL),(70,'Câu trả lời D',_binary '',12,NULL,NULL),(71,'Câu trả lời B',_binary '',12,NULL,NULL),(72,'Câu trả lời F',_binary '',12,NULL,NULL),(73,'Câu trả lời E',_binary '\0',13,NULL,NULL),(74,'Câu trả lời D',_binary '\0',13,NULL,NULL),(75,'Câu trả lời C',_binary '\0',13,NULL,NULL),(76,'Câu trả lời B',_binary '\0',13,NULL,NULL),(77,'Câu trả lời F',_binary '\0',13,NULL,NULL),(78,'Câu trả lời A',_binary '\0',13,NULL,NULL),(79,'Câu trả lời B',_binary '',14,NULL,NULL),(80,'Câu trả lời E',_binary '',14,NULL,NULL),(81,'Câu trả lời F',_binary '',14,NULL,NULL),(82,'Câu trả lời D',_binary '',14,NULL,NULL),(83,'Câu trả lời C',_binary '',14,NULL,NULL),(84,'Câu trả lời A',_binary '',14,NULL,NULL),(85,'Câu trả lời C',_binary '',15,NULL,NULL),(86,'Câu trả lời F',_binary '',15,NULL,NULL),(87,'Câu trả lời B',_binary '',15,NULL,NULL),(88,'Câu trả lời A',_binary '',15,NULL,NULL),(89,'Câu trả lời D',_binary '',15,NULL,NULL),(90,'Câu trả lời E',_binary '',15,NULL,NULL),(91,'Câu trả lời A',_binary '\0',16,NULL,NULL),(92,'Câu trả lời F',_binary '\0',16,NULL,NULL),(93,'Câu trả lời B',_binary '\0',16,NULL,NULL),(94,'Câu trả lời E',_binary '\0',16,NULL,NULL),(95,'Câu trả lời C',_binary '\0',16,NULL,NULL),(96,'Câu trả lời D',_binary '\0',16,NULL,NULL),(97,'Câu trả lời F',_binary '\0',17,NULL,NULL),(98,'Câu trả lời D',_binary '\0',17,NULL,NULL),(99,'Câu trả lời C',_binary '\0',17,NULL,NULL),(100,'Câu trả lời E',_binary '\0',17,NULL,NULL),(101,'Câu trả lời B',_binary '\0',17,NULL,NULL),(102,'Câu trả lời A',_binary '\0',17,NULL,NULL),(103,'Câu trả lời F',_binary '\0',18,NULL,NULL),(104,'Câu trả lời E',_binary '\0',18,NULL,NULL),(105,'Câu trả lời B',_binary '\0',18,NULL,NULL),(106,'Câu trả lời C',_binary '\0',18,NULL,NULL),(107,'Câu trả lời A',_binary '\0',18,NULL,NULL),(108,'Câu trả lời D',_binary '\0',18,NULL,NULL),(109,'Câu trả lời E',_binary '\0',19,NULL,NULL),(110,'Câu trả lời C',_binary '\0',19,NULL,NULL),(111,'Câu trả lời A',_binary '\0',19,NULL,NULL),(112,'Câu trả lời F',_binary '\0',19,NULL,NULL),(113,'Câu trả lời B',_binary '\0',19,NULL,NULL),(114,'Câu trả lời D',_binary '\0',19,NULL,NULL),(115,'Câu trả lời F',_binary '\0',20,NULL,NULL),(116,'Câu trả lời E',_binary '\0',20,NULL,NULL),(117,'Câu trả lời A',_binary '\0',20,NULL,NULL),(118,'Câu trả lời D',_binary '\0',20,NULL,NULL),(119,'Câu trả lời B',_binary '\0',20,NULL,NULL),(120,'Câu trả lời C',_binary '\0',20,NULL,NULL);
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
INSERT INTO `blog_tag` VALUES (6,1),(9,1),(1,2),(7,2),(8,2),(13,2),(4,3),(7,3),(10,3),(11,4),(15,4),(3,5),(4,5),(10,5),(4,6),(6,6),(9,6),(13,6),(13,7),(15,7),(7,8),(8,8),(11,8),(14,8),(15,8),(1,9),(9,9),(12,9),(14,9),(2,10),(5,10),(14,11),(6,12),(7,12),(9,12),(5,13),(6,13),(11,13),(13,13),(2,14),(4,14),(5,14),(10,14),(13,14),(15,14),(3,15),(4,15),(9,15),(15,15),(5,16),(9,16),(10,16),(14,16),(3,17),(11,17),(13,17),(14,17),(15,17),(6,18),(14,18),(7,19),(11,19),(14,19),(2,20),(5,21),(5,23),(1,24),(2,24),(5,25),(8,25),(9,25),(2,26),(15,26),(4,27),(9,27),(3,28),(6,28),(8,28),(10,28),(12,28),(8,29),(10,29),(12,29),(15,29),(9,30),(12,30);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogs`
--

LOCK TABLES `blogs` WRITE;
/*!40000 ALTER TABLE `blogs` DISABLE KEYS */;
INSERT INTO `blogs` VALUES (1,'<p>Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? <strong>Bài viết này</strong> sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend.</p><p>Bạn sẽ học <em>HTML, CSS, JavaScript, React</em> và <strong>Spring Boot</strong> để xây dựng website chuyên nghiệp.</p>','2025-02-14 10:56:50.910793',_binary '','Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? Bài viết này sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend. Bạn sẽ học HTML, CSS, JavaScript, React và Spring Boot để xây dựng website chuyên nghiệp.',_binary '','1.jpg','Lập Trình Web Từ A Đến Z: Hướng Dẫn Chi Tiết',NULL,1),(2,'<p>Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. <strong>Bài viết này</strong> sẽ chỉ ra những lỗi đó và cách khắc phục.</p><p>Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.</p>','2025-02-14 10:56:50.911793',_binary '\0','Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. Bài viết này sẽ chỉ ra những lỗi đó và cách khắc phục. Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.',_binary '\0','2.jpg','Những Sai Lầm Cần Tránh Khi Học Lập Trình',NULL,1),(3,'<p>Công cụ phù hợp giúp lập trình viên làm việc nhanh chóng và hiệu quả hơn. <strong>Dưới đây</strong> là danh sách 10 công cụ không thể thiếu.</p><ul><li>VS Code</li><li>Postman</li><li>Docker</li></ul>','2025-02-14 10:56:50.912792',_binary '\0','Công cụ phù hợp giúp lập trình viên làm việc nhanh chóng và hiệu quả hơn. Dưới đây là danh sách 10 công cụ không thể thiếu. VS Code Postman Docker',_binary '','3.jpg','Top 10 Công Cụ Hữu Ích Cho Lập Trình Viên',NULL,1),(4,'<p><strong>Git và GitHub</strong> giúp lập trình viên quản lý mã nguồn và làm việc nhóm hiệu quả. Bài viết này sẽ hướng dẫn bạn từ cơ bản đến nâng cao.</p>','2025-02-14 10:56:50.912792',_binary '\0','Git và GitHub giúp lập trình viên quản lý mã nguồn và làm việc nhóm hiệu quả. Bài viết này sẽ hướng dẫn bạn từ cơ bản đến nâng cao.',_binary '','4.jpg','Làm Chủ Git Và GitHub: Hướng Dẫn Dành Cho Người Mới',NULL,1),(5,'<p><strong>Full-stack developer</strong> là một trong những vị trí hot nhất. Bài viết này giúp bạn nắm vững kỹ năng cần thiết và lộ trình học tập hiệu quả.</p>','2025-02-14 10:56:50.913792',_binary '\0','Full-stack developer là một trong những vị trí hot nhất. Bài viết này giúp bạn nắm vững kỹ năng cần thiết và lộ trình học tập hiệu quả.',_binary '\0','5.jpg','Làm Sao Để Trở Thành Lập Trình Viên Full-Stack?',NULL,1),(6,'<p>Viết code không chỉ để máy tính hiểu, mà còn giúp đồng đội dễ đọc. Hãy áp dụng các nguyên tắc như <strong>DRY</strong> và <em>KISS</em> để tối ưu mã nguồn.</p>','2025-02-14 10:56:50.913792',_binary '\0','Viết code không chỉ để máy tính hiểu, mà còn giúp đồng đội dễ đọc. Hãy áp dụng các nguyên tắc như DRY và KISS để tối ưu mã nguồn.',_binary '','6.jpg','Cách Viết Code Sạch Và Dễ Bảo Trì',NULL,1),(7,'<p>JavaScript là ngôn ngữ lập trình phổ biến nhất hiện nay. <strong>Bài viết này</strong> sẽ cung cấp cho bạn lộ trình học từ cơ bản đến nâng cao.</p>','2025-02-14 10:56:50.914792',_binary '\0','JavaScript là ngôn ngữ lập trình phổ biến nhất hiện nay. Bài viết này sẽ cung cấp cho bạn lộ trình học từ cơ bản đến nâng cao.',_binary '','7.jpg','Học JavaScript Trong 30 Ngày: Lộ Trình Chi Tiết',NULL,1),(8,'<p>Cấu trúc dữ liệu và thuật toán là một phần quan trọng trong lập trình. <strong>Bài viết này</strong> sẽ giúp bạn hiểu cách áp dụng chúng vào thực tế.</p>','2025-02-14 10:56:50.915792',_binary '\0','Cấu trúc dữ liệu và thuật toán là một phần quan trọng trong lập trình. Bài viết này sẽ giúp bạn hiểu cách áp dụng chúng vào thực tế.',_binary '','8.jpg','Lập Trình Viên Có Cần Học Data Structures & Algorithms?',NULL,1),(9,'<p><strong>Python, JavaScript, Go</strong> và <em>Rust</em> là những ngôn ngữ có xu hướng phát triển mạnh trong năm tới.</p>','2025-02-14 10:56:50.915792',_binary '\0','Python, JavaScript, Go và Rust là những ngôn ngữ có xu hướng phát triển mạnh trong năm tới.',_binary '','9.jpg','Những Ngôn Ngữ Lập Trình Phổ Biến Năm 2025',NULL,1),(10,'<p>Chọn <strong>React Native</strong> nếu bạn thích JavaScript, hoặc <strong>Flutter</strong> nếu bạn muốn hiệu suất cao với Dart.</p>','2025-02-14 10:56:50.916792',_binary '\0','Chọn React Native nếu bạn thích JavaScript, hoặc Flutter nếu bạn muốn hiệu suất cao với Dart.',_binary '','10.jpg','Phát Triển Ứng Dụng Mobile: Nên Chọn React Native Hay Flutter?',NULL,1),(11,'<p>Học lập trình cần kiên nhẫn và thực hành liên tục. <strong>Bạn nên</strong> đặt mục tiêu rõ ràng, làm bài tập thực tế và tham gia dự án nhóm.</p>','2025-02-14 10:56:50.917793',_binary '\0','Học lập trình cần kiên nhẫn và thực hành liên tục. Bạn nên đặt mục tiêu rõ ràng, làm bài tập thực tế và tham gia dự án nhóm.',_binary '','11.jpg','Bí Quyết Học Lập Trình Hiệu Quả Cho Người Mới Bắt Đầu',NULL,1),(12,'<p><strong>DevOps</strong> giúp cải thiện quy trình phát triển phần mềm, kết hợp <strong>CI/CD</strong> để tự động hóa triển khai.</p>','2025-02-14 10:56:50.917793',_binary '\0','DevOps giúp cải thiện quy trình phát triển phần mềm, kết hợp CI/CD để tự động hóa triển khai.',_binary '','12.jpg','Tìm Hiểu Về DevOps Và CI/CD',NULL,1),(13,'<p>Để thành công trong lĩnh vực lập trình, bạn cần kỹ năng giải quyết vấn đề, giao tiếp và học hỏi liên tục.</p>','2025-02-14 10:56:50.918792',_binary '\0','Để thành công trong lĩnh vực lập trình, bạn cần kỹ năng giải quyết vấn đề, giao tiếp và học hỏi liên tục.',_binary '\0','13.jpg','Các Kỹ Năng Cần Có Để Trở Thành Lập Trình Viên Thành Công',NULL,1),(14,'<p>Hiểu về <strong>RESTful API</strong> và <em>WebSocket</em> sẽ giúp bạn thiết kế các dịch vụ web hiệu quả hơn.</p>','2025-02-14 10:56:50.918792',_binary '\0','Hiểu về RESTful API và WebSocket sẽ giúp bạn thiết kế các dịch vụ web hiệu quả hơn.',_binary '','14.jpg','Điều Quan Trọng Cần Biết Khi Làm Việc Với API',NULL,1),(15,'<p><strong>Scrum</strong> là phương pháp Agile phổ biến để phát triển phần mềm, giúp cải thiện năng suất và đảm bảo chất lượng.</p>','2025-02-14 10:56:50.919792',_binary '\0','Scrum là phương pháp Agile phổ biến để phát triển phần mềm, giúp cải thiện năng suất và đảm bảo chất lượng.',_binary '','15.jpg','Phát Triển Phần Mềm Với Scrum: Lợi Ích Và Thực Hành',NULL,1);
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
  `updated_at` datetime(6) DEFAULT NULL,
  `discount_percentage` double DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `discount_range` enum('ALL','COURSES') DEFAULT NULL,
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
-- Table structure for table `cart_courses`
--

DROP TABLE IF EXISTS `cart_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_courses` (
  `cart_course_id` bigint NOT NULL AUTO_INCREMENT,
  `status` enum('LATER','NOW') DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  PRIMARY KEY (`cart_course_id`),
  KEY `FK5ungrehmiuancpo0t49d1w0ln` (`cart_id`),
  KEY `FKsqi9btt9o3wa75exnrxgergyf` (`course_id`),
  CONSTRAINT `FK5ungrehmiuancpo0t49d1w0ln` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`),
  CONSTRAINT `FKsqi9btt9o3wa75exnrxgergyf` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_courses`
--

LOCK TABLES `cart_courses` WRITE;
/*!40000 ALTER TABLE `cart_courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `cart_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `UK64t7ox312pqal3p7fg9o503c2` (`user_id`),
  CONSTRAINT `FKb5o626f86h46m4s7ms6ginnop` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1,10);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapters`
--

DROP TABLE IF EXISTS `chapters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapters` (
  `chapter_id` bigint NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `title` varchar(255) DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  PRIMARY KEY (`chapter_id`),
  KEY `FK6h1m0nrtdwj37570c0sp2tdcs` (`course_id`),
  CONSTRAINT `FK6h1m0nrtdwj37570c0sp2tdcs` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapters`
--

LOCK TABLES `chapters` WRITE;
/*!40000 ALTER TABLE `chapters` DISABLE KEYS */;
INSERT INTO `chapters` VALUES (1,'Tìm hiểu tổng quan về Java và ứng dụng thực tế.','Giới thiệu về Java',1),(2,'Các khái niệm OOP trong Java: class, object, inheritance.','Lập trình hướng đối tượng',1),(3,'Các thuật toán sắp xếp, tìm kiếm, danh sách liên kết.','Cấu trúc dữ liệu và giải thuật',1),(4,'Cách sử dụng MySQL, thiết kế database.','Làm việc với MySQL',2),(5,'Tìm hiểu về Spring Boot và cách tạo ứng dụng.','Spring Boot cơ bản',2),(6,'Học cách tạo API trong Spring Boot.','RESTful API với Spring Boot',2),(7,'JWT, OAuth2 và bảo mật trong ứng dụng.','Bảo mật Spring Boot',3),(8,'ORM trong Java với Hibernate.','Làm việc với Hibernate',3),(9,'Giới thiệu về React và cách xây dựng ứng dụng web.','Lập trình Web với React',3),(10,'Cách tích hợp React với API từ Spring Boot.','Kết nối Frontend và Backend',4),(11,'Quản lý trạng thái ứng dụng với Redux.','Redux trong React',4),(12,'Tìm hiểu về Next.js và Server-side Rendering.','Next.js và SSR',4),(13,'Tự động hóa quy trình triển khai ứng dụng.','CI/CD với GitHub Actions',5),(14,'Triển khai ứng dụng với Docker và Kubernetes.','Docker và Kubernetes',5),(15,'Xây dựng hệ thống Microservices với Spring Cloud.','Microservices với Spring Cloud',5),(16,'Tìm hiểu về GraphQL và cách sử dụng với Spring Boot.','GraphQL trong Java',6),(17,'Xây dựng ứng dụng Android hiện đại với Kotlin.','Lập trình Android với Kotlin',6),(18,'Cơ bản về Machine Learning, AI, Data Science.','Machine Learning với Python',6),(19,'Nâng cao kỹ năng Java với multithreading, stream API.','Advanced Java Programming',7),(20,'Lập trình hướng kiểm thử với JUnit và Mockito.','TDD với JUnit',7),(21,'Tăng tốc hiệu suất ứng dụng với Redis Cache.','Bộ nhớ đệm với Redis',7),(22,'Xây dựng hệ thống phân tán với Apache Kafka.','Kafka và Event-Driven Architecture',8),(23,'Tạo GraphQL API với Spring Boot.','Xây dựng API GraphQL',8),(24,'Tích hợp OAuth2 và OpenID Connect trong Spring Security.','OAuth2 và OpenID Connect',8),(25,'Xây dựng ứng dụng real-time với WebSockets.','WebSockets với Spring Boot',9),(26,'Tìm kiếm nhanh với Elasticsearch và Kibana.','Elasticsearch cho Big Data',9),(27,'Học cách sử dụng Golang để xây dựng API mạnh mẽ.','Golang cho Backend Developers',9),(28,'Xây dựng mô hình Machine Learning với TensorFlow và Java.','TensorFlow với Java',10),(29,'CI/CD chuyên sâu với Kubernetes và ArgoCD.','Xây dựng hệ thống CI/CD nâng cao',10),(30,'Sử dụng AWS Lambda để phát triển ứng dụng không máy chủ.','Xây dựng ứng dụng Serverless',10),(31,'Tìm hiểu về biến, kiểu dữ liệu cơ bản và cách sử dụng chúng.','Biến và kiểu dữ liệu trong Java',11),(32,'Hướng dẫn sử dụng if-else, switch-case và vòng lặp trong Java.','Cấu trúc điều kiện và vòng lặp',11),(33,'Giới thiệu về lập trình OOP, các khái niệm class, object, inheritance, polymorphism.','Lập trình hướng đối tượng với Java',11),(34,'Học cách sử dụng mảng, ArrayList, HashMap trong Java.','Làm việc với Array và Collection',12),(35,'Hướng dẫn cách sử dụng try-catch-finally để xử lý lỗi chương trình.','Xử lý ngoại lệ trong Java',12),(36,'Đọc ghi file bằng FileReader, FileWriter, BufferedReader và BufferedWriter.','Làm việc với File trong Java',12),(37,'Kết nối Java với MySQL sử dụng JDBC API.','Giới thiệu về JDBC',13),(38,'Học cách tạo và quản lý thread trong Java.','Lập trình đa luồng trong Java',13),(39,'Hướng dẫn tạo giao diện đồ họa với Java Swing.','Xây dựng ứng dụng Java Swing',13),(40,'Gọi API RESTful và xử lý dữ liệu JSON bằng Java.','Làm việc với API trong Java',14),(41,'Cài đặt và cấu hình dự án Spring Boot đầu tiên.','Spring Boot: Giới thiệu và Cấu hình',14),(42,'Học cách xây dựng API RESTful với Spring Boot.','Spring Boot: Xây dựng API RESTful',14),(43,'Hướng dẫn tích hợp JPA với MySQL trong Spring Boot.','Spring Boot: Làm việc với JPA và MySQL',15),(44,'Hướng dẫn triển khai xác thực và phân quyền trong Spring Security.','Spring Security: Xác thực và phân quyền',15),(45,'Ứng dụng kiến thức đã học vào dự án thực tế.','Dự án thực tế: Xây dựng website bán hàng với Spring Boot',15),(46,'Tổng quan về Python, cài đặt và thiết lập môi trường.','Giới thiệu về Python',16),(47,'Học về biến, kiểu dữ liệu và cách sử dụng trong Python.','Biến và kiểu dữ liệu trong Python',16),(48,'Sử dụng if-else, for, while trong Python.','Cấu trúc điều kiện và vòng lặp',16),(49,'Class, object, inheritance, polymorphism trong Python.','Lập trình hướng đối tượng trong Python',17),(50,'Học cách thao tác với các cấu trúc dữ liệu trong Python.','Làm việc với List, Tuple, Dictionary',17),(51,'Đọc, ghi file với Python sử dụng open, read, write.','Xử lý file trong Python',17),(52,'Async, await trong Python để tối ưu hiệu suất.','Lập trình bất đồng bộ trong Python',18),(53,'Ứng dụng thực tế giúp bạn hiểu sâu hơn về lập trình Python.','Dự án Python: Xây dựng ứng dụng quản lý công việc',18),(54,'Học React từ cơ bản, hiểu về JSX và component.','Giới thiệu về React.js',18),(55,'Sử dụng useState, useEffect, Context API trong React.','React Hooks và State Management',19),(56,'Học cách tối ưu SEO với Next.js.','Next.js: Xây dựng ứng dụng SSR và SSG',19),(57,'Dùng Express.js để xây dựng API RESTful.','Xây dựng API với Node.js',19),(58,'Học cách sử dụng TypeScript để viết code dễ bảo trì hơn.','Tìm hiểu về TypeScript',20),(59,'Ứng dụng các kiến thức về React, Redux vào một dự án thực tế.','Dự án thực tế: Xây dựng website bán hàng với React',20),(60,'Học cách tối ưu và cải thiện hiệu suất cho ứng dụng React.','Tối ưu hiệu suất với React và Next.js',20);
/*!40000 ALTER TABLE `chapters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chats`
--

DROP TABLE IF EXISTS `chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chats` (
  `chat_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`chat_id`),
  KEY `FKmolqi1xj49bg3jjr33674limy` (`user_id`),
  CONSTRAINT `FKmolqi1xj49bg3jjr33674limy` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chats`
--

LOCK TABLES `chats` WRITE;
/*!40000 ALTER TABLE `chats` DISABLE KEYS */;
/*!40000 ALTER TABLE `chats` ENABLE KEYS */;
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
  `parent_comment_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK9aakob3a7aghrm94k9kmbrjqd` (`blog_id`),
  KEY `FK7h839m3lkvhbyv3bcdv7sm4fj` (`parent_comment_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
  CONSTRAINT `FK7h839m3lkvhbyv3bcdv7sm4fj` FOREIGN KEY (`parent_comment_id`) REFERENCES `comments` (`comment_id`),
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
  `discount_type` enum('FIXED','PERCENTAGE') DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `max_discount_amount` double DEFAULT NULL,
  `max_uses` bigint DEFAULT NULL,
  `min_order_value` double DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `used_count` bigint DEFAULT NULL,
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
INSERT INTO `coupons` VALUES (1,'A50','Giảm 50.000 cho đơn từ 100.000','Giảm 50K','2025-03-18 07:30:14.000000',50000,NULL,'FIXED','2025-12-31 23:59:59.000000',NULL,100,100000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',5),(2,'B70','Giảm 70.000 cho đơn từ 150.000','Giảm 70K','2025-03-18 07:30:14.000000',70000,NULL,'FIXED','2025-06-30 23:59:59.000000',NULL,50,150000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',40),(3,'C90','Giảm 90.000 cho đơn từ 200.000','Giảm 90K','2025-03-18 07:30:14.000000',90000,NULL,'FIXED','2025-09-30 23:59:59.000000',NULL,200,200000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',7),(4,'D30','Giảm 100.000 cho đơn từ 250.000','Giảm 100K','2025-03-18 07:30:14.000000',100000,NULL,'FIXED','2025-05-31 23:59:59.000000',NULL,50,250000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',3),(5,'E99','Giảm 150.000 cho đơn từ 300.000','Giảm 150K','2025-03-18 07:30:14.000000',150000,NULL,'FIXED','2025-08-31 23:59:59.000000',NULL,500,300000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',8),(6,'F10','Giảm 200.000 cho đơn từ 400.000','Giảm 200K','2025-03-18 07:30:14.000000',200000,NULL,'FIXED','2025-07-31 23:59:59.000000',NULL,300,400000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',6),(7,'G15','Giảm 300.000 cho đơn từ 500.000','Giảm 300K','2025-03-18 07:30:14.000000',300000,NULL,'FIXED','2025-06-30 23:59:59.000000',NULL,150,500000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',4),(8,'H20','Giảm 400.000 cho đơn từ 600.000','Giảm 400K','2025-03-18 07:30:14.000000',400000,NULL,'FIXED','2025-10-31 23:59:59.000000',NULL,100,600000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',2),(9,'I70','Giảm 700.000 cho đơn từ 900.000','Giảm 700K','2025-03-18 07:30:14.000000',700000,NULL,'FIXED','2025-09-30 23:59:59.000000',NULL,250,900000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',9),(10,'J99','Giảm 999.000 cho đơn từ 1.000.000','Giảm 999K','2025-03-18 07:30:14.000000',999000,NULL,'FIXED','2025-12-31 23:59:59.000000',NULL,50,1000000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',3),(11,'T24','Giảm 15% cho hóa đơn từ 500.000','Giảm 15%','2025-03-18 07:30:14.000000',NULL,15,'PERCENTAGE','2025-12-31 23:59:59.000000',1000000,50,500000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',2),(12,'J97','Giảm 35% cho hóa đơn từ 3.500.000','Giảm 35%','2025-03-18 07:30:14.000000',NULL,35,'PERCENTAGE','2025-12-31 23:59:59.000000',5000000,97,3500000,'2025-03-18 07:30:14.000000','2025-03-18 07:30:14.000000',2),(13,'HD24','Giảm 10% cho hóa đơn từ 100.000','Giảm 10%','2025-03-18 07:30:14.000000',NULL,10,'PERCENTAGE','2025-04-01 23:59:59.999999',150000,120,100000,'2025-03-25 00:00:00.000000','2025-03-18 07:30:14.000000',0),(14,'HP20','Giảm 20% cho hóa đơn từ 200.000','Giảm 20%','2025-03-18 07:30:14.000000',NULL,20,'PERCENTAGE','2025-04-01 23:59:59.999999',200000,20,200000,'2025-03-21 00:00:00.000000','2025-03-18 07:30:14.000000',0),(15,'CR7','Giảm 7k cho mọi hóa đơn','Giảm 7k','2025-03-18 07:30:14.000000',7000,NULL,'FIXED','2025-04-01 23:59:59.999999',NULL,10,NULL,'2025-03-21 00:00:00.000000','2025-03-18 07:30:14.000000',0);
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
INSERT INTO `course_subject` VALUES (1,1),(3,1),(5,1),(13,1),(15,1),(17,1),(19,1),(2,2),(9,2),(10,2),(17,2),(18,2),(19,2),(4,3),(7,3),(8,3),(11,3),(13,3),(16,3),(17,3),(6,4),(4,6),(7,6),(8,6),(13,6),(15,6),(7,7),(8,7),(13,7),(15,7),(20,7),(3,8),(13,8),(15,8),(13,9),(17,10),(1,11),(3,11),(4,11),(7,11),(8,11),(11,11),(13,11),(15,11),(16,11),(4,12),(7,12),(8,12),(13,12),(15,12),(20,12),(1,13),(3,13),(4,13),(7,13),(8,13),(11,13),(13,13),(15,13),(16,13),(1,14),(3,14),(4,14),(7,14),(8,14),(13,14),(15,14),(16,14),(4,15),(5,15),(17,16),(7,17),(13,17),(20,17),(3,18),(5,18),(12,18),(13,18),(14,18),(15,18),(19,18),(7,19),(8,19),(12,19),(13,19),(19,19),(20,19),(7,20),(13,20),(16,20),(3,21),(4,21),(7,21),(8,21),(13,21),(15,21),(16,21),(4,22),(7,22),(8,22),(13,22),(16,22),(4,23),(7,23),(8,23),(13,23),(15,23),(16,23),(4,24),(7,24),(13,24),(15,24),(16,24),(6,25),(8,26),(13,26),(15,26),(20,26),(14,27),(15,28),(20,28);
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
INSERT INTO `course_user` VALUES (13,1),(17,1),(18,1),(19,1),(20,1),(1,6),(2,6),(3,6),(4,6),(14,6),(15,6),(5,9);
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
  `description` longtext,
  `introduction` varchar(255) DEFAULT NULL,
  `objectives` text,
  `price` double DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `campaign_id` bigint DEFAULT NULL,
  `expert_id` bigint DEFAULT NULL,
  `course_status` enum('DRAFT','PROCESSING','REJECT','SUCCESS') DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `FK7l13d4e1nvy06049rlobu4wq8` (`campaign_id`),
  KEY `FKaog0x4gdi738saifl8upy44sb` (`expert_id`),
  KEY `FKb60up8k0778lggpd5ykw21kfi` (`cart_id`),
  CONSTRAINT `FK7l13d4e1nvy06049rlobu4wq8` FOREIGN KEY (`campaign_id`) REFERENCES `campaigns` (`campaign_id`),
  CONSTRAINT `FKaog0x4gdi738saifl8upy44sb` FOREIGN KEY (`expert_id`) REFERENCES `experts` (`expert_id`),
  CONSTRAINT `FKb60up8k0778lggpd5ykw21kfi` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,_binary '','Java Cơ Bản','2025-02-14 10:56:52.317864','Học Java từ cơ bản đến nâng cao.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Java Cơ Bản\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',300000,'1.jpg','2025-02-25 02:15:15.505993',NULL,1,NULL,NULL),(2,_binary '','Lập trình Python','2025-02-14 10:56:52.318863','Khóa học giúp bạn làm chủ Python.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Lập trình Python\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',250000,'2.jpg','2025-02-25 02:15:43.897610',NULL,1,NULL,NULL),(3,_binary '','Spring Boot Web','2025-02-14 10:56:52.318863','Xây dựng ứng dụng web với Spring Boot.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Spring Boot Web\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',400000,'3.jpg','2025-02-25 02:16:00.729570',NULL,1,NULL,NULL),(4,_binary '','JavaScript và React','2025-02-14 10:56:52.318863','Lập trình front-end với React.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về JavaScript và React\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',350000,'4.jpg','2025-02-25 02:16:35.317286',NULL,2,NULL,NULL),(5,_binary '','Lập trình Android','2025-02-14 10:56:52.319864','Học Kotlin và xây dựng ứng dụng Android.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Lập trình Android\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',320000,'5.jpg','2025-02-25 02:21:52.037595',NULL,2,NULL,NULL),(6,_binary '','C++ từ cơ bản đến nâng cao','2025-02-14 10:56:52.319864','Lập trình C++ cho người mới bắt đầu.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về C++ từ cơ bản đến nâng cao\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',280000,'6.jpg','2025-02-25 02:17:11.002936',NULL,1,NULL,NULL),(7,_binary '','Lập trình Node.js','2025-02-14 10:56:52.319864','Phát triển backend với Node.js và Express.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Lập trình Node.js\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',360000,'7.jpg','2025-02-25 02:17:30.118941',NULL,2,NULL,NULL),(8,_binary '','Fullstack với Next.js','2025-02-14 10:56:52.320863','Tạo website fullstack với Next.js.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Fullstack với Next.js\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',400000,'8.jpg','2025-02-25 02:17:49.397203',NULL,3,NULL,NULL),(9,_binary '','Data Science với Python','2025-02-14 10:56:52.320863','Phân tích dữ liệu với Python, Pandas.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Data Science với Python\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',420000,'9.jpg','2025-02-25 02:18:00.897062',NULL,1,NULL,NULL),(10,_binary '','Machine Learning','2025-02-14 10:56:52.320863','Giới thiệu về AI và Machine Learning.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Machine Learning\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',450000,'10.jpg','2025-02-25 02:18:09.401771',NULL,1,NULL,NULL),(11,_binary '','HTML & CSS Cơ Bản','2025-02-14 10:56:52.321864','Học cách xây dựng giao diện web với HTML và CSS.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về HTML & CSS Cơ Bản\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',180000,'11.jpg','2025-02-25 02:18:23.314908',NULL,3,NULL,NULL),(12,_binary '','SQL cho người mới bắt đầu','2025-02-14 10:56:52.321864','Học cách truy vấn và quản lý cơ sở dữ liệu với SQL.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về SQL cho người mới bắt đầu\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',150000,'12.jpg','2025-02-25 02:18:30.651516',NULL,1,NULL,NULL),(13,_binary '','Kiến trúc Microservices','2025-02-14 10:56:52.321864','Triển khai hệ thống Microservices với Spring Cloud.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Kiến trúc Microservices\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',850000,'13.jpg','2025-02-25 02:18:51.843736',NULL,3,NULL,NULL),(14,_binary '','Lập trình Golang','2025-02-14 10:56:52.321864','Học cách lập trình với ngôn ngữ Golang.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Lập trình Golang\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',900000,'14.jpg','2025-02-25 02:21:58.883494',NULL,1,NULL,NULL),(15,_binary '','Kubernetes & Docker','2025-02-14 10:56:52.322863','Triển khai ứng dụng với Docker và Kubernetes.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Kubernetes & Docker\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',950000,'15.jpg','2025-02-25 02:24:21.590114',NULL,2,NULL,NULL),(16,_binary '','Thiết kế UI/UX','2025-02-14 10:56:52.322863','Học cách thiết kế giao diện đẹp và trải nghiệm tốt.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Thiết kế UI/UX\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',180000,'16.jpg','2025-02-25 02:24:43.651671',NULL,2,NULL,NULL),(17,_binary '','Cấu trúc dữ liệu & Giải thuật','2025-02-14 10:56:52.322863','Học thuật toán và cấu trúc dữ liệu để tối ưu code.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Cấu trúc dữ liệu & Giải thuật\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',190000,'17.jpg','2025-02-25 02:25:04.112248',NULL,1,NULL,NULL),(18,_binary '','Lập trình AI với Python','2025-02-14 10:56:52.323863','Khóa học AI từ cơ bản đến nâng cao với Python.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Lập trình AI với Python\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',850000,'18.jpg','2025-02-25 02:25:18.276525',NULL,1,NULL,NULL),(19,_binary '','Blockchain và Smart Contracts','2025-02-14 10:56:52.323863','Xây dựng ứng dụng phi tập trung với Blockchain.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về Blockchain và Smart Contracts\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',900000,'19.jpg','2025-02-25 02:25:35.407738',NULL,1,NULL,NULL),(20,_binary '','DevOps với AWS và Docker','2025-02-14 10:56:52.323863','Học DevOps từ cơ bản, triển khai CI/CD với AWS.','https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv','[\"Hiểu cơ bản về DevOps với AWS và Docker\",\"Nắm vững kỹ thuật lập trình liên quan\",\"Xây dựng dự án thực tế\",\"Tự tin ứng dụng vào công việc\"]',1500000,'20.jpg','2025-02-25 02:25:56.281960',NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expert_user`
--

DROP TABLE IF EXISTS `expert_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expert_user` (
  `expert_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`expert_id`,`user_id`),
  KEY `FK27s6rcj7h83qrn59yipsmi23k` (`user_id`),
  CONSTRAINT `FK27s6rcj7h83qrn59yipsmi23k` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKn4dtwbtnefruftss8p21v96aj` FOREIGN KEY (`expert_id`) REFERENCES `experts` (`expert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expert_user`
--

LOCK TABLES `expert_user` WRITE;
/*!40000 ALTER TABLE `expert_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `expert_user` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experts`
--

LOCK TABLES `experts` WRITE;
/*!40000 ALTER TABLE `experts` DISABLE KEYS */;
INSERT INTO `experts` VALUES (1,'20 năm thiết kế giao diện cho website','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum','Web Designer',20,6),(2,'10 năm lập trình backend cho các tập đoàn lớn','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum','Backend Developer',10,7),(3,'15 năm viết tài liệu cho các dự án lớn nhỏ','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum','Business Analyst',15,8);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtags`
--

LOCK TABLES `hashtags` WRITE;
/*!40000 ALTER TABLE `hashtags` DISABLE KEYS */;
INSERT INTO `hashtags` VALUES (1,'java'),(2,'javascript'),(3,'typescript'),(4,'html'),(5,'css'),(6,'machine learning'),(7,'reactjs'),(8,'nextjs'),(9,'c++'),(10,'python'),(11,'spring boot'),(12,'docker'),(13,'kubernetes'),(14,'ai'),(15,'deep learning'),(16,'flutter'),(17,'nodejs'),(18,'golang'),(19,'rust'),(20,'database'),(21,'mysql'),(22,'mongodb'),(23,'postgresql'),(24,'graphql'),(25,'restapi'),(26,'android'),(27,'ios'),(28,'aws'),(29,'cloud computing'),(30,'cybersecurity');
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
  `description` longtext,
  PRIMARY KEY (`lesson_id`),
  KEY `FKmb78vk1f2oljr16oj1hpo45ma` (`chapter_id`),
  CONSTRAINT `FKmb78vk1f2oljr16oj1hpo45ma` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`chapter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lessons`
--

LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` VALUES (111,'2025-02-14 10:56:50.994315','<h1>Java là gì?</h1><p>Java là một ngôn ngữ lập trình hướng đối tượng, mạnh mẽ.</p>',60,'DOCUMENT','Giới thiệu về Java','2025-02-14 10:56:51.063330',NULL,1,NULL),(112,'2025-02-14 10:56:50.994315','<h1>Python</h1><p>Python là một ngôn ngữ lập trình bậc cao, dễ học.</p>',60,'DOCUMENT','Tìm hiểu về Python','2025-02-14 10:56:51.079332',NULL,2,NULL),(113,'2025-02-14 10:56:50.995317','<h1>HTML & CSS</h1><p>HTML giúp tạo cấu trúc, còn CSS giúp tạo kiểu cho trang web.</p>',60,'DOCUMENT','HTML và CSS là gì?','2025-02-14 10:56:51.096323',NULL,3,NULL),(114,'2025-02-14 10:56:50.995317','<h1>JavaScript</h1><p>Ngôn ngữ lập trình phổ biến trên web.</p>',60,'DOCUMENT','Giới thiệu về JavaScript','2025-02-14 10:56:51.115218',NULL,4,NULL),(115,'2025-02-14 10:56:50.996315','<h1>C++</h1><p>Một ngôn ngữ lập trình mạnh mẽ, dùng cho game, hệ thống nhúng.</p>',60,'DOCUMENT','C++ là gì?','2025-02-14 10:56:51.129688',NULL,5,NULL),(116,'2025-02-14 10:56:50.996315','<h1>PHP</h1><p>PHP là một ngôn ngữ phổ biến để phát triển web.</p>',60,'DOCUMENT','Lập trình với PHP','2025-02-14 10:56:51.146533',NULL,6,NULL),(117,'2025-02-14 10:56:50.996315','<h1>Kotlin</h1><p>Kotlin là ngôn ngữ chính thức để phát triển Android.</p>',60,'DOCUMENT','Tìm hiểu về Kotlin','2025-02-14 10:56:51.161754',NULL,7,NULL),(118,'2025-02-14 10:56:50.997315','<h1>Dart & Flutter</h1><p>Dart là ngôn ngữ, Flutter là framework phát triển ứng dụng mobile.</p>',60,'DOCUMENT','Dart và Flutter','2025-02-14 10:56:51.177758',NULL,8,NULL),(119,'2025-02-14 10:56:50.997315','<h1>Golang</h1><p>Ngôn ngữ lập trình mạnh mẽ, hiệu suất cao do Google phát triển.</p>',60,'DOCUMENT','Go (Golang) là gì?','2025-02-14 10:56:51.193264',NULL,9,NULL),(120,'2025-02-14 10:56:50.998315','<h1>Rust</h1><p>Rust là một ngôn ngữ lập trình hệ thống với độ an toàn bộ nhớ cao.</p>',60,'DOCUMENT','Rust và sự an toàn bộ nhớ','2025-02-14 10:56:51.208302',NULL,10,NULL),(121,'2025-02-14 10:56:50.998315','<h1>Swift</h1><p>Swift là ngôn ngữ chính thức để phát triển ứng dụng iOS.</p>',60,'DOCUMENT','Swift và iOS Development','2025-02-14 10:56:51.225303',NULL,11,NULL),(122,'2025-02-14 10:56:50.999314','<h1>Scala</h1><p>Scala kết hợp lập trình hướng đối tượng và lập trình hàm.</p>',60,'DOCUMENT','Scala và lập trình hàm','2025-02-14 10:56:51.240314',NULL,12,NULL),(123,'2025-02-14 10:56:50.999314','<h1>R</h1><p>R là ngôn ngữ mạnh mẽ để phân tích dữ liệu và thống kê.</p>',60,'DOCUMENT','R và phân tích dữ liệu','2025-02-14 10:56:51.260227',NULL,13,NULL),(124,'2025-02-14 10:56:51.000316','<h1>TypeScript</h1><p>TypeScript là JavaScript có kiểu tĩnh, giúp phát triển ứng dụng lớn.</p>',60,'DOCUMENT','TypeScript là gì?','2025-02-14 10:56:51.277228',NULL,14,NULL),(125,'2025-02-14 10:56:51.000316','<h1>Perl</h1><p>Perl được sử dụng nhiều trong quản trị hệ thống và xử lý văn bản.</p>',60,'DOCUMENT','Perl và lập trình hệ thống','2025-02-14 10:56:51.297796',NULL,15,NULL),(126,'2025-02-14 10:56:51.001315','<h1>Shell Script</h1><p>Shell Script giúp tự động hóa các tác vụ trên hệ điều hành Unix/Linux.</p>',60,'DOCUMENT','Shell Script và Automation','2025-02-14 10:56:51.312383',NULL,16,NULL),(127,'2025-02-14 10:56:51.001315','<h1>Haskell</h1><p>Haskell là ngôn ngữ lập trình thuần hàm mạnh mẽ.</p>',60,'DOCUMENT','Haskell và lập trình thuần hàm','2025-02-14 10:56:51.327208',NULL,17,NULL),(128,'2025-02-14 10:56:51.001315','<h1>Elixir</h1><p>Elixir là ngôn ngữ lập trình mạnh mẽ trong lập trình phân tán.</p>',60,'DOCUMENT','Elixir và lập trình phân tán','2025-02-14 10:56:51.341290',NULL,18,NULL),(129,'2025-02-14 10:56:51.002314','<h1>Lua</h1><p>Lua là ngôn ngữ nhẹ, mạnh mẽ cho lập trình nhúng.</p>',60,'DOCUMENT','Lua và lập trình nhúng','2025-02-14 10:56:51.357433',NULL,19,NULL),(130,'2025-02-14 10:56:51.002314','<h1>OOP là gì?</h1><p>OOP (Object-Oriented Programming) là một mô hình lập trình dựa trên đối tượng.</p>',60,'DOCUMENT','Lập trình hướng đối tượng','2025-02-14 10:56:51.371441',NULL,20,NULL),(131,'2025-02-14 10:56:51.003315','<h1>Functional Programming</h1><p>Lập trình hàm là một mô hình lập trình dựa trên các hàm toán học.</p>',60,'DOCUMENT','Lập trình Functional','2025-02-14 10:56:51.386222',NULL,21,NULL),(132,'2025-02-14 10:56:51.003315','<h1>JavaScript là gì?</h1><p>JavaScript là một ngôn ngữ lập trình phổ biến trên web.</p>',60,'DOCUMENT','JavaScript cơ bản','2025-02-14 10:56:51.402731',NULL,22,NULL),(133,'2025-02-14 10:56:51.003315','<h1>Python là gì?</h1><p>Python là một ngôn ngữ lập trình bậc cao, dễ học và mạnh mẽ.</p>',60,'DOCUMENT','Python và ứng dụng','2025-02-14 10:56:51.419730',NULL,23,NULL),(134,'2025-02-14 10:56:51.004315','<h1>C++ là gì?</h1><p>C++ là một ngôn ngữ lập trình mạnh mẽ, thường được sử dụng trong lập trình hệ thống.</p>',60,'DOCUMENT','C++ và lập trình hệ thống','2025-02-14 10:56:51.434735',NULL,24,NULL),(135,'2025-02-14 10:56:51.004315','<h1>Spring Boot là gì?</h1><p>Spring Boot là một framework giúp phát triển ứng dụng Java dễ dàng hơn.</p>',60,'DOCUMENT','Spring Boot cơ bản','2025-02-14 10:56:51.450563',NULL,25,NULL),(136,'2025-02-14 10:56:51.004315','<h1>REST API</h1><p>REST API là một tiêu chuẩn phổ biến để xây dựng các dịch vụ web.</p>',60,'DOCUMENT','REST API là gì?','2025-02-14 10:56:51.466505',NULL,26,NULL),(137,'2025-02-14 10:56:51.005316','<h1>SQL là gì?</h1><p>SQL là ngôn ngữ truy vấn dữ liệu phổ biến cho hệ quản trị cơ sở dữ liệu.</p>',60,'DOCUMENT','SQL và cơ sở dữ liệu','2025-02-14 10:56:51.482972',NULL,27,NULL),(138,'2025-02-14 10:56:51.006316','<h1>Docker là gì?</h1><p>Docker là một nền tảng giúp đóng gói và triển khai ứng dụng dễ dàng.</p>',60,'DOCUMENT','Docker và DevOps','2025-02-14 10:56:51.499597',NULL,28,NULL),(139,'2025-02-14 10:56:51.006316','<h1>Machine Learning</h1><p>Machine Learning là một lĩnh vực của trí tuệ nhân tạo giúp máy tính học từ dữ liệu.</p>',60,'DOCUMENT','Machine Learning với Python','2025-02-14 10:56:51.513818',NULL,29,NULL),(140,'2025-02-14 10:56:51.007316','<h1>ReactJS là gì?</h1><p>ReactJS là một thư viện JavaScript phổ biến để xây dựng giao diện người dùng.</p>',60,'DOCUMENT','Lập trình ReactJS','2025-02-14 10:56:51.527830',NULL,30,NULL),(141,'2025-02-14 10:56:51.012315','<h1>Agile là gì?</h1><p>Agile là một phương pháp phát triển phần mềm linh hoạt.</p>',60,'DOCUMENT','Tìm hiểu về Agile','2025-02-14 10:56:51.709123',NULL,41,NULL),(142,'2025-02-14 10:56:51.012315','<h1>Scrum trong Agile</h1><p>Scrum là một framework phổ biến trong Agile giúp quản lý dự án hiệu quả.</p>',60,'DOCUMENT','Scrum và Agile','2025-02-14 10:56:51.725456',NULL,42,NULL),(143,'2025-02-14 10:56:51.013315','<h1>Big Data là gì?</h1><p>Big Data là thuật ngữ dùng để chỉ tập dữ liệu lớn và phức tạp.</p>',60,'DOCUMENT','Big Data cơ bản','2025-02-14 10:56:51.741839',NULL,43,NULL),(144,'2025-02-14 10:56:51.013315','<h1>Hadoop</h1><p>Hadoop là một framework giúp xử lý dữ liệu lớn một cách hiệu quả.</p>',60,'DOCUMENT','Xử lý Big Data với Hadoop','2025-02-14 10:56:51.758241',NULL,44,NULL),(145,'2025-02-14 10:56:51.014315','<h1>Blockchain là gì?</h1><p>Blockchain là công nghệ giúp lưu trữ dữ liệu một cách an toàn và minh bạch.</p>',60,'DOCUMENT','Blockchain và ứng dụng','2025-02-14 10:56:51.774512',NULL,45,NULL),(146,'2025-02-14 10:56:51.014315','<h1>Ethereum</h1><p>Ethereum là một nền tảng blockchain phổ biến, hỗ trợ hợp đồng thông minh.</p>',60,'DOCUMENT','Ethereum và Smart Contract','2025-02-14 10:56:51.790923',NULL,46,NULL),(147,'2025-02-14 10:56:51.015315','<h1>Cybersecurity</h1><p>Cybersecurity là các biện pháp bảo vệ hệ thống và dữ liệu khỏi tấn công mạng.</p>',60,'DOCUMENT','An toàn thông tin','2025-02-14 10:56:51.807234',NULL,47,NULL),(148,'2025-02-14 10:56:51.015315','<h1>Penetration Testing</h1><p>Penetration Testing là quá trình kiểm tra hệ thống để phát hiện lỗ hổng bảo mật.</p>',60,'DOCUMENT','Kiểm thử bảo mật','2025-02-14 10:56:51.823678',NULL,48,NULL),(149,'2025-02-14 10:56:51.016315','<h1>AI Ethics</h1><p>AI Ethics là những nguyên tắc đạo đức trong phát triển và sử dụng AI.</p>',60,'DOCUMENT','Đạo đức trong AI','2025-02-14 10:56:51.840012',NULL,49,NULL),(150,'2025-02-14 10:56:51.016315','<h1>Cloud Computing</h1><p>Cloud Computing là mô hình cung cấp tài nguyên máy tính qua internet.</p>',60,'DOCUMENT','Điện toán đám mây','2025-02-14 10:56:51.856345',NULL,50,NULL),(151,'2025-02-14 10:56:51.016315','<h1>OpenCV</h1><p>OpenCV là một thư viện giúp xử lý ảnh và video.</p>',60,'DOCUMENT','Computer Vision với OpenCV','2025-02-14 10:56:51.851470',NULL,51,NULL),(152,'2025-02-14 10:56:51.017315','<h1>Blockchain</h1><p>Blockchain là công nghệ giúp lưu trữ dữ liệu phân tán an toàn.</p>',60,'DOCUMENT','Blockchain và Smart Contracts','2025-02-14 10:56:51.873469',NULL,52,NULL),(153,'2025-02-14 10:56:51.018315','<h1>Web3</h1><p>Web3 giúp xây dựng các ứng dụng phi tập trung trên blockchain.</p>',60,'DOCUMENT','Tìm hiểu về Web3','2025-02-14 10:56:51.888978',NULL,53,NULL),(154,'2025-02-14 10:56:51.019317','<h1>Apache Spark</h1><p>Apache Spark là framework xử lý dữ liệu lớn mạnh mẽ.</p>',60,'DOCUMENT','Big Data với Apache Spark','2025-02-14 10:56:51.904007',NULL,54,NULL),(155,'2025-02-14 10:56:51.020315','<h1>AI và lập trình</h1><p>AI giúp lập trình viên tạo ra các hệ thống thông minh hơn.</p>',60,'DOCUMENT','Ứng dụng AI trong lập trình','2025-02-14 10:56:51.917008',NULL,55,NULL),(156,'2025-02-14 10:56:51.020315','<h1>JUnit</h1><p>JUnit là một framework hỗ trợ kiểm thử đơn vị trong Java.</p>',60,'DOCUMENT','Unit Testing với JUnit','2025-02-14 10:56:51.932906',NULL,56,NULL),(157,'2025-02-14 10:56:51.020315','<h1>TDD là gì?</h1><p>TDD là phương pháp phát triển phần mềm dựa trên kiểm thử.</p>',60,'DOCUMENT','Tìm hiểu về Test-Driven Development','2025-02-14 10:56:51.947778',NULL,57,NULL),(158,'2025-02-14 10:56:51.021317','<h1>Refactoring</h1><p>Refactoring giúp cải thiện chất lượng mã nguồn mà không thay đổi chức năng.</p>',60,'DOCUMENT','Refactoring code là gì?','2025-02-14 10:56:51.962777',NULL,58,NULL),(159,'2025-02-14 10:56:51.024513','<h1>Làm sao để học lập trình tốt?</h1><p>Học lập trình cần kết hợp thực hành và lý thuyết.</p>',60,'DOCUMENT','Học lập trình hiệu quả','2025-02-14 10:56:51.975751',NULL,59,NULL),(160,'2025-02-14 10:56:51.025315','<h1>Docker là gì?</h1><p>Docker giúp đóng gói và triển khai ứng dụng dễ dàng với container.</p>',60,'DOCUMENT','Tìm hiểu về Docker','2025-02-14 10:56:51.991255',NULL,60,NULL),(161,'2025-02-14 10:56:50.938200',NULL,603,'VIDEO','When & Where to Add “use client” in React / Next.js (Client Components vs Server Components)','2025-02-14 10:56:51.053314','https://youtu.be/Qdkg_mrniLk?si=OI-cJQU5nWWqzTls',1,NULL),(162,'2025-02-14 10:56:50.939200',NULL,1236,'VIDEO','10 common mistakes with the Next.js App Router','2025-02-14 10:56:51.074332','https://youtu.be/RBM03RihZVs?si=iwBzGi3UH-SnBBw-',2,NULL),(163,'2025-02-14 10:56:50.939200',NULL,268,'VIDEO','What is Spring Framework?','2025-02-14 10:56:51.090847','https://youtu.be/Zxwq3aW9ctU?si=_Q5PIIMS00zQKHWQ',3,NULL),(164,'2025-02-14 10:56:50.940200',NULL,813,'VIDEO','Dependency Injection using Spring Boot','2025-02-14 10:56:51.109323','https://youtu.be/9EoAXpjnsxM?si=YVGJNYXfQDF8_PB9',4,NULL),(165,'2025-02-14 10:56:50.940200',NULL,915,'VIDEO','Autowire using Spring Boot','2025-02-14 10:56:51.124687','https://youtu.be/ET39IFffr24?si=zsQixgt2XigHMWUP',5,NULL),(166,'2025-02-14 10:56:50.941200',NULL,393,'VIDEO','TypeScript 5.8 Has 2 AWESOME Features','2025-02-14 10:56:51.140533','https://youtu.be/vcVoyLQMCxU?si=Ved2PkGSMEEkpJ6I',6,NULL),(167,'2025-02-14 10:56:50.941200',NULL,420,'VIDEO','Build anything with DeepSeek + Cline (CHEAP & EASY)','2025-02-14 10:56:51.155755','https://youtu.be/ksr-_IXsVvs?si=gLpr3GDaAPYzZ6Ly',7,NULL),(168,'2025-02-14 10:56:50.942201',NULL,567,'VIDEO','Tailwind v4 Is FINALLY Out – Here’s What’s New (and how to migrate!)','2025-02-14 10:56:51.170754','https://youtu.be/ud913ekwAOQ?si=25uinR32Zx84cODw',8,NULL),(169,'2025-02-14 10:56:50.942201',NULL,1014,'VIDEO','My best CSS tips from 2024','2025-02-14 10:56:51.186757','https://youtu.be/lUU2OAAg4Uw?si=X2HhkieAKwfVFFQy',9,NULL),(170,'2025-02-14 10:56:50.943200',NULL,1686,'VIDEO','Next.js Server Actions (revalidatePath, useFormStatus & useOptimistic)','2025-02-14 10:56:51.202303','https://youtu.be/RadgkoJrhu0?si=KBRCSnMIjj_V8-xO',10,NULL),(171,'2025-02-14 10:56:50.943200',NULL,111,'VIDEO','Difference Between Properties and Methods • C# Programming • C# Tutorial • Learn C#','2025-02-14 10:56:51.219303','https://www.youtube.com/watch?v=eHGhtzu5p5s',11,NULL),(172,'2025-02-14 10:56:50.944200',NULL,249,'VIDEO','Learn C# Sharp in Four Minutes','2025-02-14 10:56:51.234313','https://www.youtube.com/watch?v=FqCHwSH56PA',12,NULL),(173,'2025-02-14 10:56:50.945200',NULL,1133,'VIDEO','What is .NET? What\'s C# and F#? What\'s the .NET Ecosystem? .NET Core Explained, what can .NET build?','2025-02-14 10:56:51.252312','https://www.youtube.com/watch?v=bEfBfBQq7EE',13,NULL),(174,'2025-02-14 10:56:50.945200',NULL,3552,'VIDEO','Learn C# Basics 3 of 3 with Scott and Cherita','2025-02-14 10:56:51.272226','https://www.youtube.com/watch?v=FqCHwSH56PA',14,NULL),(175,'2025-02-14 10:56:50.946200',NULL,589,'VIDEO','Every single feature of C# in 10 minutes','2025-02-14 10:56:51.290389','https://www.youtube.com/watch?v=J0FhV3dM80o',15,NULL),(176,'2025-02-14 10:56:50.946200',NULL,373,'VIDEO','How C# and Java Are Actually Twins','2025-02-14 10:56:51.306795','https://www.youtube.com/watch?v=gW6m36xR-2U',16,NULL),(177,'2025-02-14 10:56:50.947202',NULL,244,'VIDEO','Upgrading Your Xamarin Project to MAUI','2025-02-14 10:56:51.321209','https://www.youtube.com/watch?v=c7ValognY4I',17,NULL),(178,'2025-02-14 10:56:50.947202',NULL,518,'VIDEO','Converting Java to C# using GPT-4','2025-02-14 10:56:51.336216','https://www.youtube.com/watch?v=ivFv0PzCRPw',18,NULL),(179,'2025-02-14 10:56:50.948199',NULL,590,'VIDEO','Why C# Has Multiple Ways to Write the Same Code','2025-02-14 10:56:51.351283','https://www.youtube.com/watch?v=rjVAz6jLvUQ',19,NULL),(180,'2025-02-14 10:56:50.948199',NULL,211,'VIDEO','Primary Constructors! New Feature Coming to C#','2025-02-14 10:56:51.366434','https://www.youtube.com/watch?v=VarE4d2BqMo',20,NULL),(181,'2025-02-14 10:56:50.949200',NULL,194,'VIDEO','Tổng quan về khóa học HTML CSS tại F8 | Học lập trình web cơ bản | Học được gì trong khóa học?','2025-02-14 10:56:51.381223','https://youtu.be/R6plN3FvzFY?si=GAioUucKIlup-h56',21,NULL),(182,'2025-02-14 10:56:50.949200',NULL,148,'VIDEO','HTML CSS là gì? | Ví dụ trực quan về HTML & CSS','2025-02-14 10:56:51.396731','https://youtu.be/zwsPND378OQ?si=lgry-wwVS8FV2Z7x',22,NULL),(183,'2025-02-14 10:56:50.950201',NULL,234,'VIDEO','Làm quen với Dev tools | Giới thiệu bộ công cụ Dev tools trên trình duyệt','2025-02-14 10:56:51.414731','https://youtu.be/7BJiPyN4zZ0?si=CbmHOInhnl4ABntt',23,NULL),(184,'2025-02-14 10:56:50.950201',NULL,131,'VIDEO','Comments trong HTML | Cú pháp mở và đóng Comments','2025-02-14 10:56:51.428733','https://youtu.be/JG0pdfdKjgQ?si=gwiv7dTx5ZShYMAe',24,NULL),(185,'2025-02-14 10:56:50.950201',NULL,114,'VIDEO','Attributes trong HTML | Thêm thuộc tính (Attributes) vào thẻ','2025-02-14 10:56:51.444736','https://youtu.be/UYpIh5pIkSA?si=c5VD8zUy-j9RdPQj',25,NULL),(186,'2025-02-14 10:56:50.951200',NULL,228,'VIDEO','CSS Variable là gì? | Cách đặt biến trong CSS','2025-02-14 10:56:51.460563','https://youtu.be/x9fnxVTkpP4?si=S90IsOYxRbcfAl_M',26,NULL),(187,'2025-02-14 10:56:50.951200',NULL,254,'VIDEO','CSS Background-clip | Thuộc tính Background-clip','2025-02-14 10:56:51.476973','https://youtu.be/hMWhvbCJIq8?si=qqD72d89KfUNSOpE',27,NULL),(188,'2025-02-14 10:56:50.951200',NULL,196,'VIDEO','Thuộc tính Background-size | CSS Background-size','2025-02-14 10:56:51.494597','https://youtu.be/8zsmGFNpqb4?si=XiO2JV9deVMaXVh4',28,NULL),(189,'2025-02-14 10:56:50.952208',NULL,177,'VIDEO','Thuộc tính Background-origin | CSS Background-origin','2025-02-14 10:56:51.508597','https://youtu.be/32a_fYd5zIo?si=PeEiGuBJrhPdLrNb',29,NULL),(190,'2025-02-14 10:56:50.952208',NULL,149,'VIDEO','Cú pháp shorthand | CSS Background shorthand','2025-02-14 10:56:51.522817','https://youtu.be/4hf8kMSRUJI?si=A2JZHoSjWENWxwf5',30,NULL),(191,'2025-02-14 10:56:50.953200',NULL,609,'VIDEO','Master TypeScript in an easy way','2025-02-14 10:56:51.537830','https://www.youtube.com/watch?v=nFwmB1_iQ7A',31,NULL),(192,'2025-02-14 10:56:50.953200',NULL,737,'VIDEO','Master React JS in easy way','2025-02-14 10:56:51.551909','https://www.youtube.com/watch?v=E8lXC2mR6-k',32,NULL),(193,'2025-02-14 10:56:50.954201',NULL,725,'VIDEO','Flutter Tutorial for Beginners #1 - Intro & Setup','2025-02-14 10:56:51.566157','https://www.youtube.com/watch?v=1ukSR1GRtMU&list=PL4cUxeGkcC9jLYyp2Aoh6hcWuxFDX6PBJ',33,NULL),(194,'2025-02-14 10:56:50.954201',NULL,375,'VIDEO','Angular Tutorial - 1 - Introduction','2025-02-14 10:56:51.584170','https://www.youtube.com/watch?v=0eWrpsCLMJQ&list=PLC3y8-rFHvwhBRAgFinJR8KHIrCdTkZcZ',34,NULL),(195,'2025-02-14 10:56:50.955200',NULL,612,'VIDEO','[100daysOfAngular] Day 1 - Introduction','2025-02-14 10:56:51.598676','https://www.youtube.com/watch?v=NS6P1fpU77o&list=PLMTyi4Bfd5pW73uXw-6jgRxDwdPYqwk0r',35,NULL),(196,'2025-02-14 10:56:50.955200',NULL,631,'VIDEO','Introduction to C++ Programming','2025-02-14 10:56:51.617676','https://www.youtube.com/watch?v=s0g4ty29Xgg&list=PLBlnK6fEyqRh6isJ01MBnbNpV3ZsktSyS',36,NULL),(197,'2025-02-14 10:56:50.956200',NULL,865,'VIDEO','Numbers, Integers, and Math [Pt 7] | C# for Beginners','2025-02-14 10:56:51.632939','https://www.youtube.com/watch?v=ZXCMBOxry8A&list=PLdo4fOcmZ0oULFjxrOagaERVAMbmG20Xe&index=7',37,NULL),(198,'2025-02-14 10:56:50.956200',NULL,638,'VIDEO','The Options Pattern in C# in 10 Minutes or Less','2025-02-14 10:56:51.650940','https://www.youtube.com/watch?v=ko1Ie9gDydY',38,NULL),(199,'2025-02-14 10:56:50.956200',NULL,619,'VIDEO','The Dictionary Data Structure in C# in 10 Minutes or Less','2025-02-14 10:56:51.666940','https://www.youtube.com/watch?v=R94JHIXdTk0',39,NULL),(200,'2025-02-14 10:56:50.957200',NULL,708,'VIDEO','JavaScript Basics in 10 Minutes','2025-02-14 10:56:51.686943','https://www.youtube.com/watch?v=xwKbtUP87Dk',40,NULL),(201,'2025-02-14 10:56:50.957200',NULL,395,'VIDEO','This CSS Property Replaces Hundreds of Lines of Code','2025-02-14 10:56:51.703082','https://youtu.be/ElELqkwzcYM?si=km2dsDrSgfV7-02J',41,NULL),(202,'2025-02-14 10:56:50.957200',NULL,511,'VIDEO','Learn CSS Positioning Quickly With A Real World Example','2025-02-14 10:56:51.716574','https://youtu.be/MxEtxo_AaZ4?si=L0gTIT8ArVinwNf6',42,NULL),(203,'2025-02-14 10:56:50.958200',NULL,310,'VIDEO','One Line Of Code Clip-Path By Master CSS','2025-02-14 10:56:51.730353','https://youtu.be/g-R_YlDg2kQ?si=LKrzzvZWWUMqcmiG',43,NULL),(204,'2025-02-14 10:56:50.958200',NULL,737,'VIDEO','Master React JS in easy way','2025-02-14 10:56:51.746996','https://youtu.be/E8lXC2mR6-k?si=nO2BEjW4cF_iUSy7',44,NULL),(205,'2025-02-14 10:56:50.959200',NULL,505,'VIDEO','Master React Hooks in easy way | useEffect','2025-02-14 10:56:51.760915','https://youtu.be/g-R_YlDg2kQ?si=WD5uRfc851t9gJAY',45,NULL),(206,'2025-02-14 10:56:50.959200',NULL,140,'VIDEO','NestJS in 100 Seconds','2025-02-14 10:56:51.773922','https://youtu.be/0M8AYU_hPas?si=0_e2eFBhwQITGFer',46,NULL),(207,'2025-02-14 10:56:50.960201',NULL,539,'VIDEO','Next.js 13 - The Basics','2025-02-14 10:56:51.788017','https://youtu.be/mSgDEOyv8?si=T3VmDTg3PLsupT55',47,NULL),(208,'2025-02-14 10:56:50.960201',NULL,597,'VIDEO','Express.js 5 is here (since a month already, actually)','2025-02-14 10:56:51.802072','https://youtu.be/-MMjFX5UfN4?si=R851MIi8cRasOBkR',48,NULL),(209,'2025-02-14 10:56:50.961200',NULL,146,'VIDEO','MongoDB in 100 Seconds','2025-02-14 10:56:51.817130','https://youtu.be/-bt_y4Loofg?si=749xUTz-Yc5wc-Sv',49,NULL),(210,'2025-02-14 10:56:50.961200',NULL,329,'VIDEO','MySQL vs MongoDB','2025-02-14 10:56:51.832215','https://youtu.be/OdgZ0jr4jpM?si=POAUqP2o-dQFYLTD',50,NULL),(211,'2025-02-14 10:56:50.962201',NULL,84,'VIDEO','Fetch vs. Axios in 1 minute','2025-02-14 10:56:51.846215','https://youtu.be/OFWATycG_Wc?si=C7xIzojN9YoeY1RA',51,NULL),(212,'2025-02-14 10:56:50.962201',NULL,590,'VIDEO','10 Advanced Tailwind Tricks Inspired by Shadcn','2025-02-14 10:56:51.867469','https://youtu.be/9z2Ifq-OPEI?si=vJTfnI1VVYAmVsxJ',52,NULL),(213,'2025-02-14 10:56:50.963200',NULL,805,'VIDEO','#8 Spring without Boot','2025-02-14 10:56:51.882472','https://youtu.be/42X_fDrP-Vg?si=SUxHA2R6PXpPlE5a',53,NULL),(214,'2025-02-14 10:56:50.963200',NULL,946,'VIDEO','#10 Constructor and Setter Injection in Spring','2025-02-14 10:56:51.898775','https://youtu.be/02Mv2lc-h-8?si=9h-izl-x8qPD12G7',54,NULL),(215,'2025-02-14 10:56:50.964200',NULL,763,'VIDEO','Mastering TypeScript Generics | Advanced TypeScript Concepts','2025-02-14 10:56:51.913009','https://youtu.be/Ba3rJEOqbNA?si=A78LnUpbIsppRhJ7',55,NULL),(216,'2025-02-14 10:56:50.964200',NULL,771,'VIDEO','Learn TypeScript Generics In 13 Minutes','2025-02-14 10:56:51.927013','https://youtu.be/EcCTIExsqmI?si=Vjt-xdB4twm8hCx0',56,NULL),(217,'2025-02-14 10:56:50.965200',NULL,503,'VIDEO','#2. Setup dự án thực hành | Tự Học Fullstack Next.js/Nest.js với TypeScript','2025-02-14 10:56:51.942905','https://youtu.be/-aYoZhvn8-4?si=PYXk3TxFq1UjHqqA',57,NULL),(218,'2025-02-14 10:56:50.966200',NULL,574,'VIDEO','NestJS MongoDB Connection: Quick Guide with 3 Approaches','2025-02-14 10:56:51.957776','https://youtu.be/XXjfTQ7d-eo?si=-AY5N672c1Sm65RU',58,NULL),(219,'2025-02-14 10:56:50.966200',NULL,458,'VIDEO','Framer Motion Tutorial: How to create Awesome text animation with framer-motion','2025-02-14 10:56:51.970748','https://youtu.be/J8SFL3Z7zw4?si=YBuZkyjBx6rWZE-C',59,NULL),(220,'2025-02-14 10:56:50.966200',NULL,312,'VIDEO','Framer Motion Tutorial: React Scroll Animations with Framer Motion','2025-02-14 10:56:51.984751','https://youtu.be/bxzk0LEF5OE?si=1_VHKwuk9-K1aCC_',60,NULL);
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
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `message_id` bigint NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `created_at` datetime(6) DEFAULT NULL,
  `role` enum('FUNCTION','MODEL','SYSTEM','USER') DEFAULT NULL,
  `chat_id` bigint DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FK64w44ngcpqp99ptcb9werdfmb` (`chat_id`),
  CONSTRAINT `FK64w44ngcpqp99ptcb9werdfmb` FOREIGN KEY (`chat_id`) REFERENCES `chats` (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
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
  `status` enum('PENDING','SENT') DEFAULT NULL,
  `set_date` datetime(6) DEFAULT NULL,
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
  `price_at_time_purchase` double DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_details_id`),
  KEY `FKtc2uxybe6r9ak6sd66whjd27` (`course_id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FKtc2uxybe6r9ak6sd66whjd27` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
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
  `expired_at` datetime(6) DEFAULT NULL,
  `order_code` varchar(255) DEFAULT NULL,
  `paid_at` datetime(6) DEFAULT NULL,
  `payment_url` mediumtext,
  `total_price` double DEFAULT NULL,
  `coupon_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKn1d1gkxckw648m2n2d5gx0yx5` (`coupon_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKn1d1gkxckw648m2n2d5gx0yx5` FOREIGN KEY (`coupon_id`) REFERENCES `coupons` (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
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
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'Ngôn ngữ lập trình nào được sử dụng để phát triển ứng dụng Android?',NULL,NULL),(2,'HTML là viết tắt của cụm từ nào?',NULL,NULL),(3,'Trong Python, hàm nào được sử dụng để in ra màn hình?',NULL,NULL),(4,'CSS được sử dụng để làm gì trong phát triển web?',NULL,NULL),(5,'JavaScript là ngôn ngữ lập trình loại gì?',NULL,NULL),(6,'Git là gì trong phát triển phần mềm?',NULL,NULL),(7,'Trong Java, từ khóa \'final\' có ý nghĩa gì?',NULL,NULL),(8,'API là viết tắt của cụm từ nào?',NULL,NULL),(9,'Trong cơ sở dữ liệu, SQL là viết tắt của cụm từ nào?',NULL,NULL),(10,'Framework nào được sử dụng để phát triển ứng dụng web bằng Python?',NULL,NULL),(11,'Trong lập trình hướng đối tượng, tính đa hình (polymorphism) là gì?',NULL,NULL),(12,'Trong JavaScript, \'NaN\' có nghĩa là gì?',NULL,NULL),(13,'Trong C++, từ khóa \'virtual\' được sử dụng để làm gì?',NULL,NULL),(14,'Trong phát triển web, REST là gì?',NULL,NULL),(15,'Trong Python, thư viện nào được sử dụng để phân tích dữ liệu?',NULL,NULL),(16,'Trong Java, \'JVM\' là viết tắt của cụm từ nào?',NULL,NULL),(17,'Trong lập trình, \'debugging\' là gì?',NULL,NULL),(18,'Trong CSS, \'flexbox\' được sử dụng để làm gì?',NULL,NULL),(19,'Trong JavaScript, \'closure\' là gì?',NULL,NULL),(20,'Trong phát triển ứng dụng di động, \'Flutter\' là gì?',NULL,NULL);
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
  `number_of_corrects` int DEFAULT NULL,
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
  `allow_see_answers` bit(1) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` mediumtext,
  `duration` int DEFAULT NULL,
  `published` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `chapter_id` bigint DEFAULT NULL,
  `expert_id` bigint DEFAULT NULL,
  PRIMARY KEY (`quiz_id`),
  UNIQUE KEY `UK8t7yvf83h97wti5y48pouog65` (`chapter_id`),
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
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rates`
--

LOCK TABLES `rates` WRITE;
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
INSERT INTO `rates` VALUES (21,'Khóa học không hữu ích như mong đợi.','2025-02-14 10:56:52.684130',1,'2025-02-14 10:56:52.684130',1,1),(22,'Nội dung khá cơ bản, chưa thực sự chuyên sâu.','2025-02-14 10:56:52.684130',2,'2025-02-14 10:56:52.684130',1,2),(23,'Khóa học tạm ổn, có thể cải thiện thêm.','2025-02-14 10:56:52.684130',3,'2025-02-14 10:56:52.684130',1,3),(24,'Giảng viên dạy dễ hiểu, kiến thức hữu ích.','2025-02-14 10:56:52.684130',4,'2025-02-14 10:56:52.684130',1,4),(25,'Khóa học rất tuyệt vời, đáng để học!','2025-02-14 10:56:52.684130',5,'2025-02-14 10:56:52.684130',1,5),(26,'Khóa học rất hữu ích, giảng viên dạy dễ hiểu.','2025-02-14 10:56:52.688130',5,'2025-02-14 10:56:52.688130',2,15),(27,'Nội dung khá ổn nhưng có thể cải thiện thêm.','2025-02-14 10:56:52.688130',3,'2025-02-14 10:56:52.688130',2,8),(28,'Bài giảng rõ ràng nhưng phần thực hành hơi ít.','2025-02-14 10:56:52.688130',3,'2025-02-14 10:56:52.688130',2,12),(29,'Khóa học tốt, đáng để học thử!','2025-02-14 10:56:52.688130',4,'2025-02-14 10:56:52.688130',2,3),(30,'Chất lượng giảng dạy tuyệt vời!','2025-02-14 10:56:52.688130',5,'2025-02-14 10:56:52.688130',2,19),(31,'Khóa học chưa thực sự như mong đợi.','2025-02-14 10:56:52.691671',2,'2025-02-14 10:56:52.691671',3,7),(32,'Nội dung hơi sơ sài, cần bổ sung thêm.','2025-02-14 10:56:52.691671',2,'2025-02-14 10:56:52.691671',3,14),(33,'Bài giảng khá nhàm chán, chưa hấp dẫn.','2025-02-14 10:56:52.691671',2,'2025-02-14 10:56:52.691671',3,9),(34,'Hơi thất vọng vì thiếu phần thực hành.','2025-02-14 10:56:52.691671',2,'2025-02-14 10:56:52.691671',3,18),(35,'Giảng viên giảng hơi nhanh, khó hiểu.','2025-02-14 10:56:52.691671',2,'2025-02-14 10:56:52.691671',3,5),(36,'Khóa học ở mức trung bình, không quá xuất sắc.','2025-02-14 10:56:52.695635',3,'2025-02-14 10:56:52.695635',4,11),(37,'Bài giảng chưa đủ sâu, nhưng tạm chấp nhận.','2025-02-14 10:56:52.695635',2,'2025-02-14 10:56:52.695635',4,7),(38,'Chất lượng giảng dạy tốt, nhưng giá hơi cao.','2025-02-14 10:56:52.695635',4,'2025-02-14 10:56:52.695635',4,19),(39,'Nội dung cần cải thiện thêm, nhưng ổn.','2025-02-14 10:56:52.695635',3,'2025-02-14 10:56:52.695635',4,5),(40,'Khóa học rất hay, đáng giá từng đồng!','2025-02-14 10:56:52.695635',5,'2025-02-14 10:56:52.695635',4,2),(41,'Khóa học rất hữu ích, kiến thức thực tế.','2025-02-14 10:56:52.699635',5,'2025-02-14 10:56:52.699635',5,12),(42,'Giảng viên dạy dễ hiểu, nhưng tốc độ hơi nhanh.','2025-02-14 10:56:52.699635',4,'2025-02-14 10:56:52.699635',5,8),(43,'Nội dung cần cập nhật thêm, hơi lỗi thời.','2025-02-14 10:56:52.699635',3,'2025-02-14 10:56:52.699635',5,17),(44,'Bài giảng chưa đủ sâu, chưa đi vào thực tế.','2025-02-14 10:56:52.699635',2,'2025-02-14 10:56:52.699635',5,6),(45,'Hoàn toàn không đáng tiền, thất vọng.','2025-02-14 10:56:52.699635',1,'2025-02-14 10:56:52.699635',5,14),(46,'Chất lượng khóa học không như mong đợi.','2025-02-14 10:56:52.703635',2,'2025-02-14 10:56:52.703635',6,10),(47,'Bài giảng cần chi tiết hơn, còn hơi sơ sài.','2025-02-14 10:56:52.703635',3,'2025-02-14 10:56:52.703635',6,4),(48,'Khóa học ổn, nhưng có thể cải thiện hơn.','2025-02-14 10:56:52.703635',4,'2025-02-14 10:56:52.703635',6,15),(49,'Giảng viên giảng rất dễ hiểu, rất hài lòng!','2025-02-14 10:56:52.703635',5,'2025-02-14 10:56:52.703635',6,7),(50,'Nội dung bài học rất hữu ích, đáng để học.','2025-02-14 10:56:52.703635',5,'2025-02-14 10:56:52.703635',6,20),(51,'Khóa học chất lượng, đáng giá tiền!','2025-02-14 10:56:52.706635',5,'2025-02-14 10:56:52.706635',7,12),(52,'Học phí rẻ nhưng nội dung rất tốt.','2025-02-14 10:56:52.706635',4,'2025-02-14 10:56:52.706635',7,6),(53,'Nội dung khá cơ bản, chưa chuyên sâu.','2025-02-14 10:56:52.706635',3,'2025-02-14 10:56:52.706635',7,18),(54,'Cần cập nhật thêm bài học mới.','2025-02-14 10:56:52.706635',2,'2025-02-14 10:56:52.706635',7,9),(55,'Không đáng tiền lắm, hơi sơ sài.','2025-02-14 10:56:52.706635',1,'2025-02-14 10:56:52.706635',7,3),(56,'Khóa học này không như mong đợi.','2025-02-14 10:56:52.711214',2,'2025-02-14 10:56:52.711214',8,5),(57,'Giá hơi cao so với nội dung.','2025-02-14 10:56:52.711214',3,'2025-02-14 10:56:52.711214',8,14),(58,'Không có nhiều kiến thức mới.','2025-02-14 10:56:52.711214',2,'2025-02-14 10:56:52.711214',8,7),(59,'Khá ổn nhưng cần cải thiện.','2025-02-14 10:56:52.711214',3,'2025-02-14 10:56:52.711214',8,19),(60,'Chưa thực sự hữu ích lắm.','2025-02-14 10:56:52.711214',1,'2025-02-14 10:56:52.711214',8,2),(61,'Khóa học rất đáng tiền!','2025-02-14 10:56:52.715218',5,'2025-02-14 10:56:52.715218',9,3),(62,'Không ngờ lại hay đến vậy.','2025-02-14 10:56:52.715218',4,'2025-02-14 10:56:52.715218',9,17),(63,'Nội dung súc tích, dễ hiểu.','2025-02-14 10:56:52.715218',5,'2025-02-14 10:56:52.715218',9,12),(64,'Mình học xong có thể áp dụng ngay.','2025-02-14 10:56:52.715218',5,'2025-02-14 10:56:52.715218',9,8),(65,'Giảng viên rất có tâm.','2025-02-14 10:56:52.715218',4,'2025-02-14 10:56:52.715218',9,5),(66,'Khóa học rất bổ ích!','2025-02-14 10:56:52.719217',5,'2025-02-14 10:56:52.719217',10,2),(67,'Chất lượng nội dung rất tốt.','2025-02-14 10:56:52.719217',4,'2025-02-14 10:56:52.719217',10,15),(68,'Bài giảng hơi khó hiểu.','2025-02-14 10:56:52.719217',3,'2025-02-14 10:56:52.719217',10,8),(69,'Giảng viên nhiệt tình nhưng hơi nhanh.','2025-02-14 10:56:52.719217',4,'2025-02-14 10:56:52.719217',10,12),(70,'Nội dung quá hay, cảm ơn giảng viên.','2025-02-14 10:56:52.719217',5,'2025-02-14 10:56:52.719217',10,6),(71,'Khóa học rất bổ ích!','2025-02-14 10:56:52.723183',5,'2025-02-14 10:56:52.723183',11,3),(72,'Chất lượng nội dung rất tốt.','2025-02-14 10:56:52.723183',4,'2025-02-14 10:56:52.723183',11,17),(73,'Bài giảng hơi khó hiểu.','2025-02-14 10:56:52.723183',3,'2025-02-14 10:56:52.723183',11,10),(74,'Giảng viên nhiệt tình nhưng hơi nhanh.','2025-02-14 10:56:52.723183',4,'2025-02-14 10:56:52.723183',11,6),(75,'Nội dung quá hay, cảm ơn giảng viên.','2025-02-14 10:56:52.723183',5,'2025-02-14 10:56:52.723183',11,12),(76,'Dạy chán quá!','2025-02-14 10:56:52.727183',1,'2025-02-14 10:56:52.727183',12,12),(77,'Bài giảng khó hiểu.','2025-02-14 10:56:52.727183',2,'2025-02-14 10:56:52.727183',12,5),(78,'Không đáng tiền.','2025-02-14 10:56:52.727183',1,'2025-02-14 10:56:52.727183',12,8),(79,'Không hài lòng lắm.','2025-02-14 10:56:52.727183',2,'2025-02-14 10:56:52.727183',12,15),(80,'Cần cải thiện nội dung.','2025-02-14 10:56:52.727183',3,'2025-02-14 10:56:52.727183',12,18),(81,'Khóa học này rất hay!','2025-02-14 10:56:52.730183',5,'2025-02-14 10:56:52.730183',13,13),(82,'Nội dung hữu ích, nhưng cần cải thiện.','2025-02-14 10:57:00.123456',4,'2025-02-14 10:57:00.123456',13,14),(83,'Giảng viên giảng dễ hiểu.','2025-02-14 10:57:05.654321',5,'2025-02-14 10:57:05.654321',13,15),(84,'Bài tập thực hành còn ít.','2025-02-14 10:57:10.789012',3,'2025-02-14 10:57:10.789012',13,16),(85,'Chưa thực sự chuyên sâu.','2025-02-14 10:57:15.111213',3,'2025-02-14 10:57:15.111213',13,17),(86,'Khóa học rất bổ ích','2025-02-14 10:56:52.734183',5,'2025-02-14 10:56:52.734183',14,7),(87,'Bài giảng dễ hiểu','2025-02-14 10:56:52.734183',4,'2025-02-14 10:56:52.734183',14,12),(88,'Nội dung cần cập nhật thêm','2025-02-14 10:56:52.734183',3,'2025-02-14 10:56:52.734183',14,5),(89,'Hơi khó hiểu với người mới','2025-02-14 10:56:52.734183',2,'2025-02-14 10:56:52.734183',14,18),(90,'Không đáng tiền','2025-02-14 10:56:52.734183',1,'2025-02-14 10:56:52.734183',14,9),(91,'Khóa học rất đáng giá','2025-02-14 10:56:52.737661',5,'2025-02-14 10:56:52.737661',15,3),(92,'Giảng viên giảng khá dễ hiểu','2025-02-14 10:56:52.737661',4,'2025-02-14 10:56:52.737661',15,7),(93,'Chất lượng bài học trung bình','2025-02-14 10:56:52.737661',3,'2025-02-14 10:56:52.737661',15,14),(94,'Nội dung chưa thực sự hấp dẫn','2025-02-14 10:56:52.737661',2,'2025-02-14 10:56:52.737661',15,19),(95,'Không đáng tiền','2025-02-14 10:56:52.737661',1,'2025-02-14 10:56:52.737661',15,10),(96,'Khóa học rất hữu ích','2025-02-14 10:56:52.740692',5,'2025-02-14 10:56:52.740692',16,4),(97,'Nội dung cần bổ sung thêm','2025-02-14 10:56:52.740692',3,'2025-02-14 10:56:52.740692',16,11),(98,'Giảng viên giảng dễ hiểu','2025-02-14 10:56:52.740692',4,'2025-02-14 10:56:52.740692',16,7),(99,'Không phù hợp với người mới','2025-02-14 10:56:52.740692',2,'2025-02-14 10:56:52.740692',16,19),(100,'Không đáng tiền','2025-02-14 10:56:52.740692',1,'2025-02-14 10:56:52.740692',16,15),(101,'Khóa học rất thú vị','2025-02-14 10:56:52.744701',5,'2025-02-14 10:56:52.744701',17,6),(102,'Nội dung chưa thực sự hấp dẫn','2025-02-14 10:56:52.744701',3,'2025-02-14 10:56:52.744701',17,14),(103,'Hơi khó hiểu với người mới','2025-02-14 10:56:52.744701',2,'2025-02-14 10:56:52.744701',17,9),(104,'Giảng viên dạy dễ hiểu','2025-02-14 10:56:52.744701',4,'2025-02-14 10:56:52.744701',17,12),(105,'Không đáng tiền','2025-02-14 10:56:52.744701',1,'2025-02-14 10:56:52.744701',17,20),(106,'Nội dung rất bổ ích','2025-02-14 10:56:52.748692',5,'2025-02-14 10:56:52.748692',18,3),(107,'Bài giảng hơi nhanh, khó theo kịp','2025-02-14 10:56:52.748692',2,'2025-02-14 10:56:52.748692',18,17),(108,'Khóa học này giúp tôi hiểu rõ hơn','2025-02-14 10:56:52.748692',4,'2025-02-14 10:56:52.748692',18,8),(109,'Không phù hợp với mong đợi của tôi','2025-02-14 10:56:52.748692',1,'2025-02-14 10:56:52.748692',18,12),(110,'Giảng viên giảng rất dễ hiểu','2025-02-14 10:56:52.748692',5,'2025-02-14 10:56:52.748692',18,6),(111,'Khóa học thực sự rất hữu ích!','2025-02-14 10:56:52.758692',5,'2025-02-14 10:56:52.758692',19,7),(112,'Nội dung ổn nhưng thiếu bài tập','2025-02-14 10:56:52.758692',3,'2025-02-14 10:56:52.758692',19,14),(113,'Chất lượng giảng dạy tốt, rất đáng tiền','2025-02-14 10:56:52.758692',4,'2025-02-14 10:56:52.758692',19,2),(114,'Khóa học khá hay, nhưng hơi khó hiểu','2025-02-14 10:56:52.758692',2,'2025-02-14 10:56:52.758692',19,11),(115,'Thầy dạy rất nhiệt tình và dễ hiểu','2025-02-14 10:56:52.758692',5,'2025-02-14 10:56:52.758692',19,5),(116,'Không phù hợp với người mới bắt đầu','2025-02-14 10:56:52.758692',1,'2025-02-14 10:56:52.758692',20,18),(117,'Bài giảng rõ ràng, dễ hiểu nhưng cần thêm ví dụ thực tế','2025-02-14 10:56:52.758692',4,'2025-02-14 10:56:52.758692',20,9),(118,'Chất lượng kém, không đáng tiền','2025-02-14 10:56:52.758692',1,'2025-02-14 10:56:52.758692',20,16),(119,'Nội dung khá cơ bản, phù hợp cho người mới','2025-02-14 10:56:52.760692',3,'2025-02-14 10:56:52.760692',20,12),(120,'Khóa học tuyệt vời! Mình rất hài lòng','2025-02-14 10:56:52.760692',5,'2025-02-14 10:56:52.760692',20,8);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER'),(3,'EXPERT'),(4,'MARKETING');
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
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (1,'Ngôn ngữ lập trình phổ biến, chạy trên JVM.','Java','java.png',NULL,NULL),(2,'Ngôn ngữ lập trình dễ học, mạnh mẽ cho AI, Data Science.','Python','python.png',NULL,NULL),(3,'Ngôn ngữ lập trình chính cho web frontend.','JavaScript','javascript.png',NULL,NULL),(4,'Ngôn ngữ lập trình mạnh mẽ cho hệ thống và game.','C++','cplus.png',NULL,NULL),(5,'Ngôn ngữ phát triển ứng dụng trên nền tảng Microsoft.','C#','csharp.png',NULL,NULL),(6,'Thư viện JavaScript phát triển UI động.','React JS','reactjs.png',NULL,NULL),(7,'Framework React hỗ trợ SSR và SEO tốt.','Next JS','nextjs.png',NULL,NULL),(8,'Framework Java để phát triển ứng dụng web nhanh chóng.','Spring Boot','springboot.png',NULL,NULL),(9,'Ngôn ngữ lập trình phổ biến cho backend web.','PHP','php.png',NULL,NULL),(10,'Ngôn ngữ lập trình linh hoạt, thường dùng với Rails.','Ruby','ruby.png',NULL,NULL),(11,'Ngôn ngữ đánh dấu để xây dựng trang web.','HTML','html.png',NULL,NULL),(12,'Phiên bản nâng cao của JavaScript với kiểu tĩnh.','TypeScript','typescript.png',NULL,NULL),(13,'Ngôn ngữ tạo kiểu cho trang web.','CSS','css.png',NULL,NULL),(14,'Tiền xử lý CSS giúp viết CSS dễ dàng hơn.','SASS','sass.png',NULL,NULL),(15,'Ngôn ngữ lập trình chính thức cho Android.','Kotlin','kotlin.png',NULL,NULL),(16,'Ngôn ngữ lập trình chính thức cho iOS.','Swift','swift.png',NULL,NULL),(17,'Framework Node.js để xây dựng backend hiệu quả.','Nest JS','nestjs.png',NULL,NULL),(18,'Hệ quản trị cơ sở dữ liệu quan hệ phổ biến.','My SQL','mysql.png',NULL,NULL),(19,'Cơ sở dữ liệu NoSQL dạng document.','MongoDB','mongodb.png',NULL,NULL),(20,'Framework JavaScript để xây dựng UI nhanh chóng.','Vue JS','vuejs.png',NULL,NULL),(21,'Framework CSS tiện lợi, hỗ trợ thiết kế nhanh chóng.','Tailwind CSS','tailwindcss.png',NULL,NULL),(22,'Thư viện UI cho React, dựa trên Material Design.','MUI','mui.png',NULL,NULL),(23,'Thư viện UI mạnh mẽ dành cho React.','Ant Design','antd.png',NULL,NULL),(24,'Framework CSS phổ biến giúp phát triển web nhanh.','Bootstrap','bootstrap.png',NULL,NULL),(25,'Ngôn ngữ lập trình mạnh mẽ, nền tảng cho nhiều ngôn ngữ khác.','C','c.png',NULL,NULL),(26,'Nền tảng container hóa giúp triển khai và quản lý ứng dụng dễ dàng hơn.','Docker','docker.png',NULL,NULL),(27,'Ngôn ngữ lập trình mã nguồn mở do Google phát triển, nổi bật với hiệu suất cao, cú pháp đơn giản và hỗ trợ xử lý đồng thời (concurrency) mạnh mẽ','Go','golang.png',NULL,NULL),(28,'Nền tảng mã nguồn mở giúp tự động triển khai, mở rộng và quản lý các ứng dụng container.','Kubernates','kubernates.png',NULL,NULL);
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
  `question_id` bigint DEFAULT NULL,
  `quiz_attempt_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_answer_id`),
  KEY `FK6b46l4bb7a6wfxvmn6l7ig8vo` (`question_id`),
  KEY `FKqy4lhxwoi677jc3u95au6qmxw` (`quiz_attempt_id`),
  CONSTRAINT `FK6b46l4bb7a6wfxvmn6l7ig8vo` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`),
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
-- Table structure for table `user_answers_answer`
--

DROP TABLE IF EXISTS `user_answers_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_answers_answer` (
  `user_answer_id` bigint NOT NULL,
  `answer_id` bigint NOT NULL,
  PRIMARY KEY (`user_answer_id`,`answer_id`),
  KEY `FKtn33w5vhip5arxeo92e2uag8p` (`answer_id`),
  CONSTRAINT `FK1udyf788y6qi8qy7yj4ht8p1i` FOREIGN KEY (`user_answer_id`) REFERENCES `user_answers` (`user_answer_id`) ON DELETE CASCADE,
  CONSTRAINT `FKtn33w5vhip5arxeo92e2uag8p` FOREIGN KEY (`answer_id`) REFERENCES `answers` (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_answers_answer`
--

LOCK TABLES `user_answers_answer` WRITE;
/*!40000 ALTER TABLE `user_answers_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_answers_answer` ENABLE KEYS */;
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
  `lesson_id` bigint DEFAULT NULL,
  `quiz_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `chapter_id` bigint DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  PRIMARY KEY (`progress_id`),
  KEY `FKrt37sneeps21829cuqetjm5ye` (`user_id`),
  CONSTRAINT `FKrt37sneeps21829cuqetjm5ye` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'CREDENTIALS',_binary '','truc.jpg','2025-02-14 10:56:50.769286',NULL,'trucnvhe180248@fpt.edu.vn','Nguyen Vuong Truc Admin','MALE',_binary '\0','$2a$10$tC4IhQM1AjJdsnCS4Lgu5uRY6.ZNNuPi.9dUrLBgrhetOag3/X7o6','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cnVjbnZoZTE4MDI0OEBmcHQuZWR1LnZuIiwiZXhwIjoxNzUxMDAyMTA5LCJpYXQiOjE3NDIzNjIxMDksImFjY291bnRUeXBlIjoiQ1JFREVOVElBTFMifQ.apqvNifzPDCQ6AHzuPglY1urmoqkQk44UCBU_YGUg74kEI1XodDXh3V7YtyzISOrrAWu9g6axvdmYc1b7lQr1w','2025-03-19 05:28:29.234044',1),(2,'CREDENTIALS',_binary '','cuong.jpg','2025-02-14 10:56:50.772279',NULL,'cuongdo13042004@gmail.com','Do Xuan Cuong Admin','MALE',_binary '\0','$2a$10$vZJfI4a59Wah2IqC/CTxlOLCZnRRa9d2rjgmhkyFJT08es44SE8dK','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdW9uZ2RvMTMwNDIwMDRAZ21haWwuY29tIiwiZXhwIjoxNzQ5MDkwOTA5LCJpYXQiOjE3NDA0NTA5MDksImFjY291bnRUeXBlIjoiQ1JFREVOVElBTFMifQ.f0fJuntCGOebRvHiR3KhJLbt5Fo05d2EhCwzPwrQT0PwIcLt-Xccoq1HjY5y_-JlST3lgMdklBhEQh9zTBIREA','2025-02-25 02:35:09.579696',1),(3,'CREDENTIALS',_binary '','dung.jpg','2025-02-14 10:56:50.773282',NULL,'dung06032004@gmail.com','Tran Nam Dung Admin','MALE',_binary '\0','$2a$10$O0AjuLpnr0fAgWcR3jYrJeYgpvU8p5FBNA5Z2usBYuXrl/XOof/wC',NULL,NULL,1),(4,'CREDENTIALS',_binary '','duc.jpg','2025-02-14 10:56:50.775282',NULL,'ducnhhe186325@fpt.edu.vn','Nong Hoang Duc Admin','MALE',_binary '\0','$2a$10$D7tO60G8ZchfhgkNHpebpOBWiG/znZzUcp7ozUc5jYkn7Z/VG1XFi',NULL,NULL,1),(5,'CREDENTIALS',_binary '','truong.jpg','2025-02-14 10:56:50.775282',NULL,'luongtruong15122004@gmail.com','Luong Hoang Truong Admin','MALE',_binary '\0','$2a$10$/PEz3lZpTCTEP54fe1dBVeC6bRUyAbumTTT3JW81VMq3NrYP68h1G',NULL,NULL,1),(6,'CREDENTIALS',_binary '','truc.jpg','2025-02-14 10:56:50.776283',NULL,'vuongtruc2004@gmail.com','Nguyen Vuong Truc Expert','MALE',_binary '\0','$2a$10$7bK28BYj16lVHFznZf9hX.AKqcnjv4xwaw2tlVhQyu/1rnVUaD7BS','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dW9uZ3RydWMyMDA0QGdtYWlsLmNvbSIsImV4cCI6MTc1MDQxMDI5NiwiaWF0IjoxNzQxNzcwMjk2LCJhY2NvdW50VHlwZSI6IkNSRURFTlRJQUxTIn0.tq9V_RUFWa86QNMjkXjOu_cDWZM2k-vb0RUYsnhCEprUKJxeUJp_Nt9Cap4Dn3_QuS5x2CY_HaIy56s15Lr0BQ','2025-03-12 09:04:56.781595',3),(7,'CREDENTIALS',_binary '','truc.jpg','2025-02-14 10:56:50.777283',NULL,'vuongtruc20042@gmail.com','Vuong Truc Expert','MALE',_binary '\0','$2a$10$iGVslfwYEIBb1mhzwY9InujO.eJNWkBvZGps4aopLQeimga3yLyJ6',NULL,NULL,3),(8,'CREDENTIALS',_binary '','truc.jpg','2025-02-14 10:56:50.778283',NULL,'vuongtruc20043@gmail.com','Anh Truc Expert','MALE',_binary '\0','$2a$10$W6/.UBd5AxOfx7Ld.n0N1empgiphyOapAQfNQ5Ra1Shd5lGVLvw.m',NULL,NULL,3),(9,'CREDENTIALS',_binary '','truc.jpg','2025-02-14 10:56:50.779282',NULL,'vuongtruc2008@gmail.com','Nguyen Vuong Truc Marketing','MALE',_binary '\0','$2a$10$juJQTIE2Pz9KCqqq6MdJWeKrH8Q.FXnS0cd2sLm1ZmqATGQQdnyU6','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2dW9uZ3RydWMyMDA4QGdtYWlsLmNvbSIsImV4cCI6MTc0OTU3MjQwNywiaWF0IjoxNzQwOTMyNDA3LCJhY2NvdW50VHlwZSI6IkNSRURFTlRJQUxTIn0.mGnWfudDc-Fbd7jsXqX-2j8LAD9rBxYctIV81YxoFV6BMZPnnM9-gr9k8PhW9Eg6N7kpR5-YvNeamo32lPcUfA','2025-03-02 16:20:07.525988',4),(10,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.780282',NULL,'1@gmail.com','Nguyen Vuong Truc 1','FEMALE',_binary '\0','$2a$10$ZlDuG4L9DbhAD1AtLqS5vO8/I2ZTO8s8jb/8rXgB6shlMm7d.N3b2',NULL,'2025-02-25 02:31:58.338138',2),(11,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.781283','2016-02-18 00:00:00.000000','2@gmail.com','Nguyen Vuong Truc 2','FEMALE',_binary '\0','$2a$10$ZJwsYFeXLvWTfAlItg/tRuQGBYb0cFICVYWvj8Ovr0ngXXPYqjQW6',NULL,'2025-02-25 02:35:02.343307',2),(12,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.784283',NULL,'3@gmail.com','Nguyen Vuong Truc 3','FEMALE',_binary '\0','$2a$10$ddgj0a/Ku6W2lIrswaYVJegGwh2NRitrTle.OVSX.BwEt.9OWvhRO',NULL,NULL,2),(13,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.784283',NULL,'4@gmail.com','Nguyen Vuong Truc 4','MALE',_binary '\0','$2a$10$TuasB4q/WxQAhhg6xsMobuDGyVB3Abfzk7lTzG9icBQQLQUoY4OrG',NULL,NULL,2),(14,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.785282',NULL,'5@gmail.com','Nguyen Vuong Truc 5','FEMALE',_binary '\0','$2a$10$OBhosIl/a7g9yFqf86g0I.Wp6HgVc/qicPPksdBk26LfrUhKF2VWG',NULL,NULL,2),(15,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.786282',NULL,'6@gmail.com','Nguyen Vuong Truc 6','MALE',_binary '\0','$2a$10$Iw.fn.7bEWck29yTAxD9ieTHLJeIWRNEaqsBv0l4906uedUcMTtwK',NULL,NULL,2),(16,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.787283',NULL,'7@gmail.com','Nguyen Vuong Truc 7','FEMALE',_binary '\0','$2a$10$IeyD85BhXGFP8ewqgE0vvOhByHBRzkgNBCYfvvbkRBaB/SvkCIJrm',NULL,NULL,2),(17,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.788282',NULL,'8@gmail.com','Nguyen Vuong Truc 8','MALE',_binary '\0','$2a$10$uJo2XAARVs3zB/7o5Z3xA.deaMtByis.v46uZ1Z.aY/4BhCskKlNy',NULL,NULL,2),(18,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.788282',NULL,'9@gmail.com','Nguyen Vuong Truc 9','FEMALE',_binary '\0','$2a$10$7OLvM.fezw/n.UnR9dfyRek0NWCyphhPstuls0S6GKRUkoEMwnYQe',NULL,NULL,2),(19,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.790362',NULL,'10@gmail.com','Nguyen Vuong Truc 10','MALE',_binary '\0','$2a$10$qXua6F2XD/AjEaNgtCim8.8he0iXsjYqelwbMcHerLI6pAcCF7W8e',NULL,NULL,2),(20,'CREDENTIALS',_binary '',NULL,'2025-02-14 10:56:50.791363',NULL,'11@gmail.com','Nguyen Vuong Truc 11','FEMALE',_binary '\0','$2a$10$.jNRupXxR.8U73lToXuKTu4/s1tnwTNi55zWq2FZL7i0U9nRMqMwC',NULL,NULL,2),(21,'GOOGLE',_binary '','https://lh3.googleusercontent.com/a/ACg8ocINA6x0fKz0WIb9x7RyQST-hxYZjvUicoCpLVKlUE_w-zfg1zw=s96-c','2025-02-25 02:27:51.580424','2004-02-20 00:00:00.000000','trucnvhe180248@fpt.edu.vn','Nguyen Vuong Truc (K18 HL)','MALE',_binary '\0',NULL,NULL,'2025-02-25 02:28:53.652993',2),(22,'GITHUB',_binary '','https://avatars.githubusercontent.com/u/153248550?v=4','2025-02-25 02:28:58.692734',NULL,'trucnvhe180248@fpt.edu.vn','Vuong Truc (mail truong)',NULL,_binary '\0',NULL,NULL,'2025-02-25 02:30:34.535081',2);
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

-- Dump completed on 2025-03-23  0:23:02


