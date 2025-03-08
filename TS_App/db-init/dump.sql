-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: timesheet_app
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_report`
--

DROP TABLE IF EXISTS `admin_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_report` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `average_working_hours` double DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `generated_by` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `total_working_hours` double DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `FKi34yf3rni1yk5i3uhujfv4arw` (`user_id`),
  CONSTRAINT `FKi34yf3rni1yk5i3uhujfv4arw` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_report`
--

LOCK TABLES `admin_report` WRITE;
/*!40000 ALTER TABLE `admin_report` DISABLE KEYS */;
INSERT INTO `admin_report` VALUES (4,2.8,'2025-02-26 17:07:50.422800',NULL,'admin1@gmail.com',NULL,14,37),(8,7.5,'2025-02-26 17:30:49.133090','2025-03-01 23:59:59.000000','admin1@gmail.com','2025-01-01 00:00:00.000000',15,38),(9,6,'2025-02-26 17:38:31.906809','2025-01-25 00:00:00.000000','admin1@gmail.com','2025-01-25 00:00:00.000000',6,38),(10,8,'2025-03-06 13:35:21.628662',NULL,'admin1@gmail.com',NULL,32,42),(11,8,'2025-03-06 13:49:01.982892','2025-05-03 23:59:59.000000','admin10@gmail.com','2025-03-06 00:00:00.000000',16,42),(12,0,'2025-03-06 13:51:49.917740','2025-01-25 00:00:00.000000','admin10@gmail.com','2025-01-25 00:00:00.000000',0,42),(13,0,'2025-03-06 14:00:23.361695','2025-01-25 00:00:00.000000','admin10@gmail.com','2025-01-25 00:00:00.000000',0,42),(14,6.833333333333333,'2025-03-06 18:04:00.337158',NULL,'admin1@gmail.com',NULL,41,42),(15,6.25,'2025-03-06 18:05:55.427660','2025-05-03 23:59:59.000000','admin10@gmail.com','2025-03-06 00:00:00.000000',25,42),(16,5.666666666666667,'2025-03-06 18:08:03.129622','2025-03-06 00:00:00.000000','admin10@gmail.com','2025-03-06 00:00:00.000000',17,42);
/*!40000 ALTER TABLE `admin_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_detail`
--

DROP TABLE IF EXISTS `report_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_detail` (
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `total_hours` double DEFAULT NULL,
  `work_detail` longtext,
  `report_id` int DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `FK2rlvh1nol24mcgaul4qsf66mb` (`report_id`),
  CONSTRAINT `FK2rlvh1nol24mcgaul4qsf66mb` FOREIGN KEY (`report_id`) REFERENCES `admin_report` (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_detail`
--

LOCK TABLES `report_detail` WRITE;
/*!40000 ALTER TABLE `report_detail` DISABLE KEYS */;
INSERT INTO `report_detail` VALUES (11,'2025-02-25 10:00:00.000000','Time Sheet Mgmt Sys',2,'Learned A',4),(12,'2025-02-25 10:00:00.000000','Time Sheet Mgmt Sys',2,'Learned B',4),(13,'2025-02-25 10:00:00.000000','Time Sheet Mgmt Sys',6,'Leanred Java Collections Framework',4),(14,'2025-02-25 10:00:00.000000','Time Sheet Mgmt Sys',2,'Learned E',4),(15,'2025-02-25 10:00:00.000000','Time Sheet Mgmt Sys',2,'Learned F',4),(24,'2025-01-25 16:40:27.382270','GIRA APP',6,'Leanred Synchronization',8),(25,'2025-02-25 16:40:49.179765','GIRA APP',9,'Leanred Multi threading and Concurrency',8),(26,'2025-01-25 16:40:27.382270','GIRA APP',6,'Leanred Synchronization',9),(27,'2025-03-06 08:00:00.000000','HRMS APP',8,'Solved Docker issues.',10),(28,'2025-04-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the login module and fixing API issues.',10),(29,'2025-05-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the login module and fixing API issues.',10),(30,'2025-06-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the login module and fixing API issues.',10),(31,'2025-03-06 08:00:00.000000','HRMS APP',8,'Solved Docker issues.',11),(32,'2025-04-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the login module and fixing API issues.',11),(33,'2025-03-06 08:00:00.000000','HRMS APP',8,'Solved Docker issues.',14),(34,'2025-04-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the login module and fixing API issues.',14),(35,'2025-05-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the login module and fixing API issues.',14),(36,'2025-06-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the login module and fixing API issues.',14),(37,'2025-03-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the GIRA DASHBOARD module and LOGOUT API issues.',14),(38,'2025-03-06 08:00:00.000000','HRMS APP',1,'Worked on implementing the login module and fixing API issues.',14),(39,'2025-03-06 08:00:00.000000','HRMS APP',8,'Solved Docker issues.',15),(40,'2025-04-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the login module and fixing API issues.',15),(41,'2025-03-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the GIRA DASHBOARD module and LOGOUT API issues.',15),(42,'2025-03-06 08:00:00.000000','HRMS APP',1,'Worked on implementing the login module and fixing API issues.',15),(43,'2025-03-06 08:00:00.000000','HRMS APP',8,'Solved Docker issues.',16),(44,'2025-03-06 08:00:00.000000','HRMS APP',8,'Worked on implementing the GIRA DASHBOARD module and LOGOUT API issues.',16),(45,'2025-03-06 08:00:00.000000','HRMS APP',1,'Worked on implementing the login module and fixing API issues.',16);
/*!40000 ALTER TABLE `report_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `app_role` enum('ROLE_ADMIN','ROLE_EMPLOYEE') DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_EMPLOYEE');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_sheet`
--

DROP TABLE IF EXISTS `time_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_sheet` (
  `time_sheet_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `total_hours` double DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `work_detail` text NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`time_sheet_id`),
  KEY `FKqsei8kt62fimjdo7a714pk5k1` (`user_id`),
  CONSTRAINT `FKqsei8kt62fimjdo7a714pk5k1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_sheet`
--

LOCK TABLES `time_sheet` WRITE;
/*!40000 ALTER TABLE `time_sheet` DISABLE KEYS */;
INSERT INTO `time_sheet` VALUES (4,'2025-01-25 16:40:27.382270','2025-01-25 16:40:27.382270','2025-01-25 18:00:00.000000','GIRA APP','2025-01-25 09:00:00.000000',6,'2025-02-25 17:18:20.007921','emp115@gmail.com','Leanred Synchronization',38),(5,'2025-02-25 16:40:49.179765','2025-02-25 16:40:49.179765','2025-02-25 18:00:00.000000','GIRA APP','2025-02-25 09:00:00.000000',9,'2025-02-25 17:17:43.472116','emp115@gmail.com','Leanred Multi threading and Concurrency',38),(6,'2025-03-25 16:42:38.178378','2025-03-25 16:42:38.178378','2025-03-25 18:00:00.000000','GIRA APP','2025-03-25 09:00:00.000000',2,'2025-02-25 16:42:38.178378','emp115@gmail.com','Learned Spring Security 6',38),(7,'2025-02-25 16:48:27.608945','2025-02-25 10:00:00.000000','2025-02-25 18:00:00.000000','Time Sheet Mgmt Sys','2025-02-25 09:00:00.000000',2,'2025-02-25 16:48:27.608945','emp114@gmail.com','Learned A',37),(8,'2025-02-25 16:48:33.044035','2025-02-25 10:00:00.000000','2025-02-25 18:00:00.000000','Time Sheet Mgmt Sys','2025-02-25 09:00:00.000000',2,'2025-02-25 16:48:33.044035','emp114@gmail.com','Learned B',37),(9,'2025-02-25 16:48:38.311926','2025-02-25 10:00:00.000000','2025-02-25 18:00:00.000000','Time Sheet Mgmt Sys','2025-02-25 09:00:00.000000',6,'2025-02-25 17:20:44.750490','emp114@gmail.com','Leanred Java Collections Framework',37),(11,'2025-02-25 16:48:47.337495','2025-02-25 10:00:00.000000','2025-02-25 18:00:00.000000','Time Sheet Mgmt Sys','2025-02-25 09:00:00.000000',2,'2025-02-25 16:48:47.337495','emp114@gmail.com','Learned E',37),(12,'2025-02-25 16:48:51.106174','2025-02-25 10:00:00.000000','2025-02-25 18:00:00.000000','Time Sheet Mgmt Sys','2025-02-25 09:00:00.000000',2,'2025-02-25 16:48:51.106174','emp114@gmail.com','Learned F',37),(13,'2025-02-25 16:49:23.348431','2025-02-25 10:00:00.000000','2025-02-25 18:00:00.000000','Time Sheet Mgmt Sys','2025-02-25 09:00:00.000000',2,'2025-02-25 16:49:23.348431','emp113@gmail.com','Learned Rest api',36),(14,'2025-02-25 16:49:43.132654','2025-02-25 10:00:00.000000','2025-02-25 18:00:00.000000','Time Sheet Mgmt Sys','2025-02-25 09:00:00.000000',2,'2025-02-25 16:49:43.132654','emp113@gmail.com','leaned Git',36),(16,'2025-03-06 12:50:50.931573','2025-03-06 08:00:00.000000','2025-06-06 17:00:00.000000','HRMS APP','2025-06-06 09:00:00.000000',8,'2025-03-06 13:25:16.432593','emp122@gmail.com','Solved Docker issues.',42),(17,'2025-04-06 12:52:35.091973','2025-04-06 08:00:00.000000','2025-04-06 17:00:00.000000','HRMS APP','2025-04-06 09:00:00.000000',8,'2025-04-06 12:52:35.091973','emp122@gmail.com','Worked on implementing the login module and fixing API issues.',42),(18,'2025-05-06 12:52:46.770634','2025-05-06 08:00:00.000000','2025-05-06 17:00:00.000000','HRMS APP','2025-05-06 09:00:00.000000',8,'2025-05-06 12:52:46.770634','emp122@gmail.com','Worked on implementing the login module and fixing API issues.',42),(19,'2025-06-06 12:52:56.717336','2025-06-06 08:00:00.000000','2025-06-06 17:00:00.000000','HRMS APP','2025-06-06 09:00:00.000000',8,'2025-06-06 12:52:56.717336','emp122@gmail.com','Worked on implementing the login module and fixing API issues.',42),(20,'2025-03-06 17:54:15.113499','2025-03-06 08:00:00.000000','2025-06-06 17:00:00.000000','HRMS APP','2025-06-06 09:00:00.000000',8,'2025-03-06 18:01:48.998113','emp122@gmail.com','Worked on implementing the GIRA DASHBOARD module and LOGOUT API issues.',42),(21,'2025-03-06 17:55:44.117420','2025-03-06 08:00:00.000000','2025-06-06 17:00:00.000000','HRMS APP','2025-06-06 09:00:00.000000',1,'2025-03-06 17:55:44.117420','emp122@gmail.com','Worked on implementing the login module and fixing API issues.',42);
/*!40000 ALTER TABLE `time_sheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `project` varchar(100) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(25) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `last_name` varchar(25) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK4qu1gr772nnf6ve5af002rwya` (`role_id`),
  CONSTRAINT `FK4qu1gr772nnf6ve5af002rwya` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Management','2025-02-25 10:47:51.000000','admin1@gmail.com','EMP001','Admin1',_binary '','Admin1','$2a$12$fkLu7j/usMLyo7fJfRJbCOgysX7XMKP2hIFoSczw/H0Sw5hg07eve','2025-02-25 10:47:51.000000',1),(2,'Management System','2025-02-25 14:33:07.267884','admin2@gmail.com','EMP002','Admin2',_binary '','Admin2','$2a$12$QHfbDtpevIsux.vDXmGpp.ZW5bW7Uk8fFaZin65HSnvG.UW6WMkuq','2025-02-25 14:33:07.267884',1),(3,'Management System','2025-02-25 14:38:59.473141','admin3@gmail.com','EMP003','Admin3',_binary '','Admin3','$2a$12$T8zy3SevBD1th0lPaVgM5umHY.rNqwOQHGbQaMid.LJmKylUxX2ie','2025-02-25 14:38:59.473141',1),(4,'Management System','2025-02-25 14:55:56.938449','admin4@gmail.com','EMP004','Admin4',_binary '','Admin4','$2a$12$nLTAcr0/FIJ/aHo4e1KiQODampI1gTZ2RJHH3EK5fXRZZNo6Iy2SG','2025-02-25 14:55:56.938449',1),(8,'Time Sheet Mgmt Sys','2025-02-25 15:08:38.974998','emp1@gmail.com','FSI001','Emp1',_binary '','Emp1','$2a$10$5BLbpPqUwccx.YRs6KjU7.ffVzYLJKRSVRwqM60QxiRaMKArtv.7e','2025-02-25 15:08:38.974998',2),(18,'Time Sheet Mgmt Sys','2025-02-25 15:19:00.409358','emp2@gmail.com','FSI002','Emp2',_binary '','Emp2','$2a$10$zqzh2my7tEcGMkISY1RFmOVZMnxliB0XiaWwyyDV3m/TS3PWXu2Ea','2025-02-25 15:19:00.409358',2),(19,'Time Sheet Mgmt Sys','2025-02-25 15:19:27.549454','emp3@gmail.com','FSI003','Emp3',_binary '','Emp3','$2a$10$qdQQ82qFRI3W5q5T4T9lsOTRWOVipUUWYkn9.Z6wvDmt3aqrUdmxe','2025-02-25 15:19:27.549454',2),(20,'Time Sheet Mgmt Sys','2025-02-25 15:19:56.311677','emp4@gmail.com','FSI004','Emp4',_binary '','Emp4','$2a$10$XT9/ZDJemSig/TOAQ22EwO4XmDLugB6XaLhw6VoE.vU2OcsUmUWkW','2025-02-25 15:19:56.311677',2),(22,'GIRA APP','2025-02-25 15:20:33.783404','emp6@gmail.com','FSI006','Emp6',_binary '','Emp6','$2a$10$FUBly6Z6.bkI4IzsoCXEGO27Qak7WnepF1hwkSeJ1BupjXQzGdyv6','2025-02-25 15:20:33.783404',2),(23,'GIRA APP','2025-02-25 15:20:52.077123','emp7@gmail.com','FSI007','Emp7',_binary '','Emp7','$2a$10$oGK9yoQj5.n56/vIngFqp.ekdJO.WYgjptfkn0LH.xtIC37TuMJ5a','2025-02-25 15:20:52.077123',2),(24,'GIRA APP','2025-02-25 15:21:05.662968','emp8@gmail.com','FSI008','Emp8',_binary '','Emp8','$2a$10$JFAMLgryQpPSQrym29UI7uyefSIACK06IKSW3yXbgP9/4AK0rUk3a','2025-02-25 15:21:05.662968',2),(25,'GIRA APP',NULL,'emp101@gmail.com','FSI101','Emp101',_binary '','Emp101','$2a$10$14j0OjUyEzvJ..s2oLb4Ge2gF6CLd8ZHIMK/VQDQXTH74nM9b6JWu','2025-02-25 15:27:14.230834',2),(27,'Free Stone Mgmt','2025-02-25 15:35:58.766960','admin5@gmail.com','EMP005','Admin5',_binary '','Admin5','$2a$10$1PqGcR7whkFqixPjkprVieDR6CrdPMVzp.0ws3nt3Zv57S4Z35pxu','2025-02-25 15:35:58.766960',1),(28,'Free Stone Mgmt','2025-02-25 15:36:32.276791','admin6@gmail.com','EMP006','Admin6',_binary '','Admin6','$2a$10$16C5ZuM.BysufjVyEUUG1.yLU2oErLxDgQjBwtix7ntPwJIHSjfwK','2025-02-25 15:36:32.276791',1),(29,'Free Stone Mgmt','2025-02-25 15:36:47.837737','admin7@gmail.com','EMP007','Admin7',_binary '','Admin7','$2a$10$/KNQkM0ZVdNxz/GPEbiyEeeFthziWHb3AdePGgdrksELGD9ug3K1y','2025-02-25 15:36:47.837737',1),(30,'Free Stone Mgmt','2025-02-25 15:37:07.542762','admin8@gmail.com','EMP008','Admin8',_binary '','Admin8','$2a$10$JPyVHb0pKxc3aLZWgFrDC..NiLiiRD.L8dI3kA1YOdSBpX965C/HK','2025-02-25 15:37:07.542762',1),(31,'Free Stone Mgmt','2025-02-25 15:37:24.831349','admin9@gmail.com','EMP009','Admin9',_binary '','Admin9','$2a$10$hvkuvuYYNqWXC3qYvuYN4eA80KDSL/zYqIm3ldUiMygnz46Ha7tS2','2025-02-25 15:37:24.831349',1),(32,'Free Stone Mgmt','2025-02-25 15:37:49.550243','admin10@gmail.com','EMP010','Admin10',_binary '','Admin10','$2a$10$005nP8vPGHFATJDogH2rVOJcmNw/jQ2b63NFkF.8eR55CiG0bCnLy','2025-02-25 15:37:49.550243',1),(33,'Time Sheet Mgmt Sys','2025-02-25 15:47:45.879966','emp110@gmail.com','FSI110','Emp110',_binary '','Emp110','$2a$10$sTEa6MThbYJOZ79o9jpX8OnStd4xkCPB1/bFbrFE3z58JIEbzWu/y','2025-02-25 15:47:45.879966',2),(34,'Time Sheet Mgmt Sys','2025-02-25 15:47:46.017728','emp111@gmail.com','FSI111','Emp111',_binary '','Emp111','$2a$10$aZg3AdqncOM1pDVY2sZgsO7dxfEfMLpF0TkU8KEZ0w0VE0Lai6bSK','2025-02-25 15:47:46.017728',2),(35,'GIRA APP','2025-02-25 15:47:46.096021','emp112@gmail.com','FSI112','Emp112',_binary '','Emp112','$2a$10$xFPiouY9EvOYpIbuOqw.xuZPjBqG4ysTfuufEqNN9C22EjeVIRazG','2025-02-25 15:47:46.096021',2),(36,'Time Sheet Mgmt Sys','2025-02-25 15:48:50.303996','emp113@gmail.com','FSI113','Emp113',_binary '','Emp113','$2a$10$wcVUe2Tc/Y0AQnChns69a.0XZilejaV9WJml7wltGg/K.yVWqMQZS','2025-02-25 15:48:50.303996',2),(37,'Time Sheet Mgmt Sys','2025-02-25 15:48:50.393725','emp114@gmail.com','FSI114','Emp114',_binary '','Emp114','$2a$10$Xyd2VIq3tSfDdiiQcmmty.TmGQY.lDHz2XfVv/MOPT4BqreD49Gee','2025-02-25 15:48:50.393725',2),(38,'GIRA APP','2025-02-25 15:48:50.471871','emp115@gmail.com','FSI115','Emp115',_binary '','Emp115','$2a$10$ydbAaBgbfZcVx.3ABfaGuudyUCN9bHEhV8wSdZet3zMZz7Ctb.K2e','2025-02-25 15:48:50.471871',2),(39,'Time Tracking System','2025-03-06 12:02:38.124581','emp120@example.com','FSI120','EMP120',_binary '','EMP120','$2a$10$a8/UaqmmD6ksCo4ZnhyM/eVp66eYcufuUJaC7Yh/Sd7TO0xpbPk8i','2025-03-06 12:02:38.124581',2),(40,'Game App','2025-03-06 12:06:18.628452','emp121@example.com','FSI121','EMP121',_binary '','EMP121','$2a$10$fZ25R90jllwmEjMI.K8ha.OB/D/Hvt21Qc2LH8BFpqEBTpJLmSptW','2025-03-06 12:06:18.629452',2),(42,'HRMS APP',NULL,'emp122@gmail.com','FSI122','EMP122',_binary '','EMP122','$2a$10$p5gt1xGjrJ2KQIgNE6HNueeyeEiORWRUY3OJ3U0LYRrXovyW4PbHW','2025-03-06 12:32:06.952380',2),(44,'HR MGMT SYSTEM','2025-03-06 17:51:00.405348','emp123@gmail.com','FSI123','EMP123',_binary '','EMP123','$2a$10$30nMaEpXU2gHdsbpBFA.B.lRzZiIiUTDD7D28kPbIekZnCyrHOYCe','2025-03-06 17:51:00.405348',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_time_sheet`
--

DROP TABLE IF EXISTS `users_time_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_time_sheet` (
  `users_user_id` int NOT NULL,
  `time_sheet_time_sheet_id` int NOT NULL,
  UNIQUE KEY `UKtocxk54w0u2c3xpj30mojkwtm` (`time_sheet_time_sheet_id`),
  KEY `FKo5kbfxw50s1a6e7u01lv01rof` (`users_user_id`),
  CONSTRAINT `FK44gb99ti0wg9ufxxc4nqt0op8` FOREIGN KEY (`time_sheet_time_sheet_id`) REFERENCES `time_sheet` (`time_sheet_id`),
  CONSTRAINT `FKo5kbfxw50s1a6e7u01lv01rof` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_time_sheet`
--

LOCK TABLES `users_time_sheet` WRITE;
/*!40000 ALTER TABLE `users_time_sheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_time_sheet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-09  3:33:17
