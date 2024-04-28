-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: chatop
-- ------------------------------------------------------
-- Server version	8.3.0

CREATE DATABASE IF NOT EXISTS chatop
CHARACTER SET utf8mb4
COLLATE utf8mb4_0900_ai_ci;

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

USE chatop;
--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rental_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ce1i9w1rtics9wjwj8y5y3md` (`rental_id`),
  KEY `FKpsmh6clh3csorw43eaodlqvkn` (`user_id`),
  CONSTRAINT `FK3ce1i9w1rtics9wjwj8y5y3md` FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`),
  CONSTRAINT `FKpsmh6clh3csorw43eaodlqvkn` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES ('2024-04-27 17:00:00','2024-04-27 17:00:00',1,NULL,1,'This is a first message from admin'),('2024-04-27 17:00:00','2024-04-27 17:00:00',2,NULL,1,'This is a second message from admin');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentals`
--

DROP TABLE IF EXISTS `rentals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rentals` (
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `price` decimal(38,2) NOT NULL,
  `surface` decimal(38,2) NOT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `owner_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf462yhxa9vd3m2qdmcoixg1fv` (`owner_id`),
  CONSTRAINT `FKf462yhxa9vd3m2qdmcoixg1fv` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentals`
--

LOCK TABLES `rentals` WRITE;
/*!40000 ALTER TABLE `rentals` DISABLE KEYS */;
INSERT INTO `rentals` VALUES ('2024-04-27 17:00:00',1000.00,50.00,'2024-04-27 17:00:00',1,3,'My First Rental ','rental_1',NULL),('2024-04-27 17:00:00',1030.00,59.00,'2024-04-27 17:00:00',2,3,'My second Rental ','rental_2',NULL);
/*!40000 ALTER TABLE `rentals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('USER','ADMIN','MANAGER') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('2024-04-27 17:00:00','2024-04-27 17:00:00',1,'admin@mail.com','Admin','$2a$10$dyHkv08ONBVaGmMEoWqFceLVdFCA3aFxN78h7i9SSNcq5ASXZp/Ji','ADMIN'),('2024-04-27 17:00:00','2024-04-27 17:00:00',2,'manager@mail.com','Manager','$2a$10$UIfelenqawpbZcPZpJ1lUegZnIarthL4xL23SsbE755LfO9cvQzhu','MANAGER'),('2024-04-27 17:00:00','2024-04-27 17:00:00',3,'user@mail.com','User','$2a$10$r7uCUKU4iLILZ.WSBePzd.70Z/rpmFT9RtXJKmZH64.3Sf92ANWL6','USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'chatop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-28 18:38:31
