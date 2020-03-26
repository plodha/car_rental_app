-- MySQL dump 10.13  Distrib 8.0.19
--
-- Host: localhost    Database: car_rental
-- ------------------------------------------------------
-- Server version	8.0.19

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

DROP TABLE IF EXISTS `Address`;
CREATE TABLE `Address` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`Street` TEXT NOT NULL,
	`City` TEXT NOT NULL,
	`State` TEXT NOT NULL,
	`ZipCode` INT(5) NOT NULL,
	PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `VehicleType`;
CREATE TABLE `VehicleType` (
	`VehicleSize` INT NOT NULL,
	`VehicleClass` INT NOT NULL,
	PRIMARY KEY (`VehicleClass`)
);

DROP TABLE IF EXISTS `Vehicle`;
CREATE TABLE `Vehicle` (
	`Location` INT NOT NULL,
	`Status` INT NOT NULL,
	`VehicleType` INT NOT NULL,
	`Make` INT NOT NULL,
	`Model` INT NOT NULL,
	`Year` INT NOT NULL,
	`VIN` INT NOT NULL,
	`LIcensePlate` INT NOT NULL,
	`Id` INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `Price`;
CREATE TABLE `Price` (
	`LateFee` INT NOT NULL,
	`HourlyFee` INT NOT NULL,
	`VehicleTypeId` INT NOT NULL,
	PRIMARY KEY (`VehicleTypeId`)
);

DROP TABLE IF EXISTS `Location`;
CREATE TABLE `Location` (
	`Name` INT NOT NULL,
	`VehicleCapacity` INT NOT NULL,
	`Address` TEXT NOT NULL,
	`ContactNumber` INT(10) NOT NULL,
	PRIMARY KEY (`Name`)
);

DROP TABLE IF EXISTS `Customer`;
CREATE TABLE `Customer` (
	`Name` TEXT NOT NULL,
	`LicenseNumber` INT NOT NULL,
	`RegistrationDate` DATE NOT NULL,
	`RegistrationEndDate` DATE NOT NULL,
	`MembershipStatus` BOOLEAN NOT NULL,
	`Verified` BOOLEAN NOT NULL,
	`LicenseExpDate` DATE NOT NULL,
	`Address` TEXT NOT NULL,
	`Email` TEXT NOT NULL,
	`Id` INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `Employee`;
CREATE TABLE `Employee` (
	`Name` TEXT NOT NULL,
	`Role` TEXT NOT NULL,
	`Id` INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `Reservation`;
CREATE TABLE `Reservation` (
	`Location` INT NOT NULL,
	`Vehicle` INT NOT NULL,
	`Customer` INT NOT NULL,
	`EstimatePrice` DOUBLE NOT NULL,
	`PickUpTime` DATETIME NOT NULL,
	`EstimateDropOffTime` DATETIME NOT NULL,
	`ActualDropOffTime` DATETIME,
	`Invoice` INT NOT NULL,
	PRIMARY KEY (`Invoice`)
);

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
	`Username` VARCHAR(10) NOT NULL,
	`Password` VARCHAR(10) NOT NULL,
	`Id` INT NOT NULL,
	`Role` TEXT NOT NULL,
	PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `Invoice`;
CREATE TABLE `Invoice` (
	`EstimatedPrice` DOUBLE NOT NULL,
	`VehicleStatus` INT,
	`DamageFee` DOUBLE,
	`LateFee` DOUBLE,
	`TotalPrice` DOUBLE,
	`Id` INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `Damage`;
CREATE TABLE `Damage` (
	`DamageType` TEXT NOT NULL,
	`DamageFee` DOUBLE NOT NULL,
	`VehicleTypeId` INT NOT NULL,
	`DamageId` INT NOT NULL,
	PRIMARY KEY (`DamageId`)
);
