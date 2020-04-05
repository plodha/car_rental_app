-- Adminer 4.7.6 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `Address`;
CREATE TABLE `Address` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Street` text NOT NULL,
  `City` text NOT NULL,
  `State` text NOT NULL,
  `ZipCode` int NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `Address` WRITE;
INSERT INTO `Address` VALUES(1, '700 CAMPBELL RD','RICHARDSON','TEXAS',75080);
INSERT INTO `Address` VALUES(2, '800 RENNER RD','RICHARDSON','TEXAS',75080);
INSERT INTO `Address` VALUES(3, '6547 CANOGA AVE','CANOGA PARK','CALIFORNIA',91303);
INSERT INTO `Address` VALUES(4, '1445 ROSS AVE','DALLAS','TEXAS',75202);
INSERT INTO `Address` VALUES(5, '7825 MCCALLUM BLVD','DALLAS','TEXAS',75252);
INSERT INTO `Address` VALUES(6, '367 MEANDERING WAY','HOUSTON','TEXAS',76245);
INSERT INTO `Address` VALUES(7, '888 PRESTON ROAD','DULLES','VIRGINIA',92367);
INSERT INTO `Address` VALUES(8, '1007 DALLAS PARKWAY','DALLAS','TEXAS',72212);
INSERT INTO `Address` VALUES(9, '2256 WALL STREET','NEWARK','NEW JERSEY',65289);
INSERT INTO `Address` VALUES(10, '0099 ALMA ROAD','DULLES','VIRGINIA',97325);
UNLOCK TABLES;


DROP TABLE IF EXISTS `Customer`;
CREATE TABLE `Customer` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `UserId` int NOT NULL,
  `FirstName` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LastName` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Address` int NOT NULL,
  `Email` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LicenseNumber` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LicenseExpDate` date NOT NULL,
  `MembershipStartDate` date NOT NULL,
  `MembershipEndDate` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Address` (`Address`),
  KEY `UserId` (`UserId`),
  CONSTRAINT `Customer_ibfk_2` FOREIGN KEY (`Address`) REFERENCES `Address` (`Id`),
  CONSTRAINT `Customer_ibfk_3` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `Customer` WRITE;
INSERT INTO `Customer` VALUES(1, 1, 'KEERTHIKUMAR', 'RAVICHANDRAN', 1, 'keerthi@gmail.com', 'F1234554', '2017-06-15', '2017-06-15', '2018-06-15');
INSERT INTO `Customer` VALUES(2, 2, 'NIVEDITHA', 'VARADHA CHANDRASEKARAN', 2, 'nivi07@gmail.com', 'F9764521', '2017-06-15', '2017-06-15', '2018-06-15');
INSERT INTO `Customer` VALUES(3, 3, 'SURESH', 'GOPALAKRISHNAN', 3, 'suresh2234@gmail.com', 'F2345611', '2017-06-15', '2017-06-15', '2018-06-15');
INSERT INTO `Customer` VALUES(4, 4, 'MARK', 'HUFF', 4, 'markhuff@gmail.com', 'R8763578', '2017-06-15', '2017-06-15', '2018-06-15');
INSERT INTO `Customer` VALUES(5, 5, 'MARK', 'TOWNSEND', 5, 'markstown@gmail.com', 'I3478953', '2017-06-15', '2017-06-15', '2018-06-15');
INSERT INTO `Customer` VALUES(6, 6, 'MITA', 'RANA', 6, 'mitarana@gmail.com', 'E7521097', '2017-06-15', '2017-06-15', '2018-06-15');
INSERT INTO `Customer` VALUES(7, 7, 'DANISH', 'HASSAN', 7, 'danishhasan@gmail.com', 'T0981237', '2017-06-15', '2017-06-15', '2018-06-15');
INSERT INTO `Customer` VALUES(8, 8, 'MIKE', 'BOYEAR', 8, 'mikeboy@gmail.com', 'F0091266', '2017-06-15', '2017-06-15', '2018-06-15');
INSERT INTO `Customer` VALUES(9, 9, 'CHRIS', 'ALEXANDER', 9, 'chrisalex@gmail.com', 'P1234567', '2017-06-15', '2017-06-15', '2018-06-15');
INSERT INTO `Customer` VALUES(10, 10, 'VELA', 'REYNALDO', 10, 'reyvela@gmail.com', 'V5690245', '2017-06-15', '2017-06-15', '2018-06-15');
UNLOCK TABLES;


DROP TABLE IF EXISTS `Damage`;
CREATE TABLE `Damage` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `VehicleTypeId` int NOT NULL,
  `DamageType` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DamageFee` double NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `VehicleTypeId` (`VehicleTypeId`),
  CONSTRAINT `Damage_ibfk_1` FOREIGN KEY (`VehicleTypeId`) REFERENCES `VehicleType` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `Employee`;
CREATE TABLE `Employee` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `UserId` int NOT NULL,
  `FirstName` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LastName` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Role` text NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `UserId` (`UserId`),
  CONSTRAINT `Employee_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `Invoice`;
CREATE TABLE `Invoice` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `EstimatedPrice` double NOT NULL,
  `DamageFee` double DEFAULT NULL,
  `LateFee` double DEFAULT NULL,
  `TotalPrice` double DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `Location`;
CREATE TABLE `Location` (
  `Id` int NOT NULL,
  `Name` text NOT NULL,
  `VehicleCapacity` int NOT NULL,
  `Address` int NOT NULL,
  `ContactNumber` int NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Address` (`Address`),
  CONSTRAINT `Location_ibfk_1` FOREIGN KEY (`Address`) REFERENCES `Address` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `Price`;
CREATE TABLE `Price` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `LateFee` int NOT NULL,
  `HourlyFee` int NOT NULL,
  `VehicleTypeId` int NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`),
  KEY `VehicleTypeId` (`VehicleTypeId`),
  CONSTRAINT `Price_ibfk_1` FOREIGN KEY (`VehicleTypeId`) REFERENCES `VehicleType` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `Reservation`;
CREATE TABLE `Reservation` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Customer` int NOT NULL,
  `Vehicle` int NOT NULL,
  `Location` int NOT NULL,
  `PickUpTime` datetime NOT NULL,
  `EstimateDropOffTime` datetime NOT NULL,
  `ActualDropOffTime` datetime DEFAULT NULL,
  `EstimatePrice` double NOT NULL,
  `Invoice` int NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`),
  KEY `Customer` (`Customer`),
  KEY `Vehicle` (`Vehicle`),
  KEY `Location` (`Location`),
  KEY `Invoice` (`Invoice`),
  CONSTRAINT `Reservation_ibfk_1` FOREIGN KEY (`Customer`) REFERENCES `Customer` (`Id`),
  CONSTRAINT `Reservation_ibfk_2` FOREIGN KEY (`Vehicle`) REFERENCES `Vehicle` (`Id`),
  CONSTRAINT `Reservation_ibfk_3` FOREIGN KEY (`Location`) REFERENCES `Location` (`Id`),
  CONSTRAINT `Reservation_ibfk_4` FOREIGN KEY (`Invoice`) REFERENCES `Invoice` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `Username` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL,
  `Id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `Vehicle`;
CREATE TABLE `Vehicle` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `VIN` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `VehicleTypeId` int NOT NULL,
  `Location` int NOT NULL,
  `LicensePlate` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Make` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Model` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Year` int NOT NULL,
  `Status` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`),
  KEY `Location` (`Location`),
  KEY `VehicleTypeId` (`VehicleTypeId`),
  CONSTRAINT `Vehicle_ibfk_2` FOREIGN KEY (`Location`) REFERENCES `Location` (`Id`),
  CONSTRAINT `Vehicle_ibfk_3` FOREIGN KEY (`VehicleTypeId`) REFERENCES `VehicleType` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `VehicleType`;
CREATE TABLE `VehicleType` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `VehicleSize` int NOT NULL,
  `VehicleClass` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- 2020-04-04 20:26:10
