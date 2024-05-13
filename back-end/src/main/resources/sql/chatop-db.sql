-- Pignon Pierre-Olivier
-- Script version : 2
-- ------------------------------------------------------
-- Server version	8.3.0


CREATE
    DATABASE IF NOT EXISTS chatop
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_ai_ci;

USE
    chatop;

DROP TABLE IF EXISTS `messages`;
DROP TABLE IF EXISTS `rentals`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    `id`         bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `email`      varchar(248)       NOT NULL,
    `name`       varchar(64)        NOT NULL,
    `password`   varchar(64)        NOT NULL,
    `created_at` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE (`email`)
);


CREATE TABLE `rentals`
(
    `id`          bigint         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name`        varchar(248)   NOT NULL,
    `price`       decimal(38, 2) NOT NULL,
    `surface`     decimal(38, 2) NOT NULL,
    `description` varchar(2000)  NOT NULL,
    `owner_id`    bigint         NOT NULL,
    `picture`     varchar(248)            DEFAULT NULL,
    `created_at`  timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_at`  timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT `rentals_users_FK` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
);


CREATE TABLE `messages`
(

    `id`         bigint        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `message`    varchar(3000) not NULL,
    `rental_id`  bigint        NOT NULL,
    `user_id`    bigint        NOT NULL,
    `created_at` timestamp     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` timestamp     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT `messages_rentals_FK` FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`),
    CONSTRAINT `messages_users_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
