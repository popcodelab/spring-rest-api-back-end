# Châtop Back-end

## Table of contents

- Description
- Get the files from Github
- Database setup
- Run the back-end
- Swagger
- Technologies
- Authors

## Description

This project is a back-end for a portal for connecting future tenants and owners for seasonal rentals.
It relies on Spring Boot and a MySQL database

## Installation

Git clone:

> git clone https://github.com/popcodelab/spring-rest-api-back-end.git

Go inside folder:

> cd spring-rest-api-back-end
 
>mvn install

It generates :
- The jar (`chatop-backend-1.0.0-SNAPSHOT.jar`) file into the folder: `target`
- The javadoc into the folder: `doc\javadoc`

## Database setup

Assuming that you have MySQL server installed on your machine.
Enter the following commands :

Providing your MySQL username:

> mysql -u `username` -p < .\src\main\resources\sql\chatop-db.sql

Provide your MySQL password at the prompt.

The database has been created.

To check, type this command:
> mysql -u `username` -p

At the prompt, enter your password.
Once you ll be connected to the server, type this command:
> SHOW DATABASES; 

the Châtop database should appear in the result.

## Run the back-end



> java -jar .\target\chatop-backend-1.0.0-SNAPSHOT.jar

## Swagger

Make sure that the back-end is running, then you can browse this address :

http://localhost:8080/swagger-ui/index.html

## Technologies

<table style="border: none">
<tr style="border: none">
  <td style="border: none">Java 17</td><td style="border: none">
  <img style="height: 40px;width: 40px;" src="https://raw.github.com/popcodelab/svg-icons/main/java.svg?sanitize=true" alt="Java"></td>
</tr>
<tr style="border: none">
  <td style="border: none">Spring 6.1.5</td><td style="border: none">
  <img style="height: 100px;" src="https://raw.github.com/popcodelab/svg-icons/main/spring1.svg?sanitize=true" alt="Spring 6.1.5"></td>
</tr>
<tr style="border: none">
  <td style="border: none">Spring Boot 3.2.4</td><td style="border: none">
  <img style="height: 40px;width: 40px;" src="https://raw.github.com/popcodelab/svg-icons/main/spring.svg?sanitize=true" alt="Spring Boot 3.2.4"></td>
</tr>
<tr style="border: none"> 
  <td style="border: none" colspan="2">JPA</td>
</tr>
<tr style="border: none">
  <td style="border: none">JSON</td><td style="border: none">
  <img style="height: 40px;width: 40px;" src="https://raw.github.com/popcodelab/svg-icons/main/json5-smiley.svg?sanitize=true" alt="JSON"></td>
</tr>
<tr style="border: none">
  <td style="border: none">JWT</td>
  <td style="border: none"><img style="height: 40px;width: 40px;" src="https://raw.github.com/popcodelab/svg-icons/main/jwt.svg?sanitize=true" alt="JWT"></td>  
</tr>
<tr style="border: none">
  <td style="border: none">REST API</td>
  <td style="border: none"><img style="height: 40px;width: 40px;" src="https://raw.github.com/popcodelab/svg-icons/main/rest.svg?sanitize=true" alt="REST API"></td>
 </tr>
<tr style="border: none"> 
  <td style="border: none">SQL (MySQL 8.3)</td>
  <td style="border: none"><img style="height: 100px;" src="https://raw.github.com/popcodelab/svg-icons/main/mysql.svg?sanitize=true" alt="SQL (MySQL)"></td>
</tr>
<tr style="border: none"> 
  <td style="width:15em;word-wrap:break-word; border: none">Springdoc Openapi v2.5.0 & Swagger UI</td>
  <td style="border: none"><img style="height: 40px;" src="https://raw.github.com/popcodelab/svg-icons/main/swagger.svg?sanitize=true" alt="Springdoc Openapi v2.5.0 & Swagger UI"></td>
</tr>
</table>

## Authors

POP's Code Lab


<hr/>

[![forthebadge](https://forthebadge.com/images/badges/built-by-developers.svg)](https://forthebadge.com)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![Apache](https://img.shields.io/badge/apache-%23D42029.svg?style=for-the-badge&logo=apache&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
[![forthebadge](https://forthebadge.com/images/badges/uses-git.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/uses-markdown.svg)](https://forthebadge.com)
