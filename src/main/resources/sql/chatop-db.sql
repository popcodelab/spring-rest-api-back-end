-- Pignon Pierre-Olivier
-- Script version : 2
-- ------------------------------------------------------
-- Server version	8.3.0


CREATE DATABASE IF NOT EXISTS chatop
CHARACTER SET utf8mb4
COLLATE utf8mb4_0900_ai_ci;

USE chatop;

DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
                            `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
                            `rental_id` bigint DEFAULT NULL,
                            `user_id` bigint DEFAULT NULL,
                            `message` varchar(3000) DEFAULT NULL,
                            KEY `rentals_FK` (`rental_id`),
                            KEY `users_FK` (`user_id`),
                            CONSTRAINT `rentals_FK` FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`),
                            CONSTRAINT `users_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ;

DROP TABLE IF EXISTS `rentals`;
CREATE TABLE `rentals` (
                           `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `price` decimal(38,2) NOT NULL,
                           `surface` decimal(38,2) NOT NULL,
                           `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
                           `owner_id` bigint NOT NULL,
                           `description` varchar(2000) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `picture` varchar(255) DEFAULT NULL,
                           KEY `users_FK` (`owner_id`),
                           CONSTRAINT `users_FK` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
)


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         `email` varchar(255) DEFAULT NOT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NOT NULL
                     UNIQUE (`email`)
)

