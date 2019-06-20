DROP SCHEMA IF EXISTS `lemory`;
CREATE SCHEMA IF NOT EXISTS `lemory`;
USE `lemory`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: lemory
-- ------------------------------------------------------
-- Server version	8.0.15



--
-- Table structure for table `stats`
--

DROP TABLE IF EXISTS `stats`;
CREATE TABLE `stats` (
  `usernr` int(11) NOT NULL,
  `won` boolean DEFAULT FALSE,
  `time` int(11) DEFAULT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
);

--
-- Dumping data for table `stats`
--

LOCK TABLES `stats` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(48) NOT NULL,
  `passwd` varchar(65) NOT NULL,
  `usernr` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(150) NOT NULL,
  `first_name` varchar(55) NOT NULL,
  `last_name` varchar(55) NOT NULL,
  `geburtstag` date NOT NULL,
-- New Created 
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`usernr`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `usernr_UNIQUE` (`usernr`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
UNLOCK TABLES;


-- Dump completed on 2019-05-10 16:51:15
