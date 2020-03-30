-- MySQL dump 10.13  Distrib 8.0.19
--
-- Host: localhost    Database: car_rental
-- ------------------------------------------------------
-- Server versio8.0.19

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
-- Address
--

DROP TABLE IF EXISTS `Address`;
CREATE TABLE `Address` (`Id` INT NOT NULL AUTO_INCREMENT,`Street` TEXT NOT NULL,`City` TEXT NOT NULL,`State` TEXT NOT NULL,`ZipCode` INT(5) NOT NULL,PRIMARY KEY (`Id`)
);

LOCK TABLES `Address` WRITE;
INSERT INTO `Address` VALUES(1, 'Crazy', 'Really Crazy', 'CA', 94539);
UNLOCK TABLES;

--
-- VehicleType
--

DROP TABLE IF EXISTS `VehicleType`;
CREATE TABLE `VehicleType` (`VehicleSize` INT NOT NULL,`VehicleClass` VARCHAR(15) NOT NULL,PRIMARY KEY (`VehicleClass`)
);

LOCK TABLES `VehicleType` WRITE;
INSERT INTO `VehicleType` VALUES(4 , 'COMPACT');
INSERT INTO `VehicleType` VALUES(5 , 'ECONOMY');
INSERT INTO `VehicleType` VALUES(5 , 'MID SIZE');
INSERT INTO `VehicleType` VALUES(5 , 'STANDARD');
INSERT INTO `VehicleType` VALUES(5 , 'FULL SIZE');
INSERT INTO `VehicleType` VALUES(2 , 'LUXURY CAR');
INSERT INTO `VehicleType` VALUES(6 , 'MID SIZE SUV');
INSERT INTO `VehicleType` VALUES(6 , 'STANDARD SUV');
INSERT INTO `VehicleType` VALUES(7 , 'FULL SIZE SUV');
INSERT INTO `VehicleType` VALUES(10 , 'MINI VAN');
UNLOCK TABLES;


--
-- Vehicle
--

DROP TABLE IF EXISTS `Vehicle`;
CREATE TABLE `Vehicle` (`LicensePlate` VARCHAR(7) NOT NULL,`Model` TEXT NOT NULL,`Make` TEXT NOT NULL,`Year` INT NOT NULL,`VehicleType` VARCHAR(15) NOT NULL,`Location` INT NOT NULL,`Status` BOOLEAN NOT NULL,`VIN` TEXT NOT NULL,PRIMARY KEY (`LicensePlate`)
);

LOCK TABLES `Vehicle` WRITE;
INSERT INTO `Vehicle` VALUES('ABX1234','CIVIC','HONDA',2014,'ECONOMY',101,'T', 'WP0AA2A79BL017100');
INSERT INTO `Vehicle` VALUES('SDF4567','FIESTA','FORD',2015,'ECONOMY',102,'T', 'WP0AA2A79BL017101');
INSERT INTO `Vehicle` VALUES('WER3245','ACCENT','HYUNDAI',2014,'ECONOMY',103,'T', 'WP0AA2A79BL017102');
INSERT INTO `Vehicle` VALUES('GLZ2376','COROLLA','TOYOTA',2016,'ECONOMY',104,'T', 'WP0AA2A79BL017103');
INSERT INTO `Vehicle` VALUES('HJK1234','CIVIC','HONDA',2015,'ECONOMY',102,'T', 'WP0AA2A79BL017104');
INSERT INTO `Vehicle` VALUES('GLS7625','FOCUS','FORD',2014,'COMPACT',107,'T', 'WP0AA2A79BL017105');
INSERT INTO `Vehicle` VALUES('FKD8202','GOLF','VOLKSWAGAN',2016,'COMPACT',106,'T', 'WP0AA2A79BL017106');
INSERT INTO `Vehicle` VALUES('HNX1890','PRIUS','TOYOTA',2015,'COMPACT',105,'T', 'WP0AA2A79BL017107');
INSERT INTO `Vehicle` VALUES('KJS1983','PRIUS','TOYOTA',2014,'COMPACT',104,'T', 'WP0AA2A79BL017108');
INSERT INTO `Vehicle` VALUES('SDL9356','FOCUS','FORD',2016,'COMPACT',103,'T', 'WP0AA2A79BL017109');
INSERT INTO `Vehicle` VALUES('OTY7293','CRUZE','CHEVROLET',2016,'MID SIZE',102,'T', 'WP0AA2A79BL017110');
INSERT INTO `Vehicle` VALUES('QWE4562','LEGACY','SUBARU',2012,'MID SIZE',101,'T', 'WP0AA2A79BL017111');
INSERT INTO `Vehicle` VALUES('CXZ2356','AVENGER','DODGE',2015,'MID SIZE',102,'T', 'WP0AA2A79BL017112');
INSERT INTO `Vehicle` VALUES('ASD9090','ACCORD','HONDA',2016,'MID SIZE',103,'T', 'WP0AA2A79BL017113');
INSERT INTO `Vehicle` VALUES('UYT3981','LEGACY','SUBARU',2013,'MID SIZE',104,'T', 'WP0AA2A79BL017114');
INSERT INTO `Vehicle` VALUES('TRE9726','200','CHRYSTLER',2012,'STANDARD',105,'T', 'WP0AA2A79BL017115');
INSERT INTO `Vehicle` VALUES('HGF5628','TAURUS','FORD',2013,'STANDARD',106,'T', 'WP0AA2A79BL017116');
INSERT INTO `Vehicle` VALUES('LKJ7253','200','CHRYSTLER',2014,'STANDARD',107,'T', 'WP0AA2A79BL017117');
INSERT INTO `Vehicle` VALUES('VBN6283','TAURUS','FORD',2015,'STANDARD',101,'T', 'WP0AA2A79BL017118');
INSERT INTO `Vehicle` VALUES('POI7281','200','CHRYSTLER',2016,'STANDARD',102,'T', 'WP0AA2A79BL017119');
INSERT INTO `Vehicle` VALUES('MNB8654','FALCON','FORD',2012,'FULL SIZE',103,'T', 'WP0AA2A79BL017120');
INSERT INTO `Vehicle` VALUES('UHV9786','IMPALA','CHEVROLET',2013,'FULL SIZE',104,'T', 'WP0AA2A79BL017121');
INSERT INTO `Vehicle` VALUES('EFB5427','WAYFARER','FORD',2014,'FULL SIZE',105,'T', 'WP0AA2A79BL017122');
INSERT INTO `Vehicle` VALUES('PLM9873','IMPALA','CHEVROLET',2015,'FULL SIZE',106,'T', 'WP0AA2A79BL017123');
INSERT INTO `Vehicle` VALUES('WDV2458','FALCON','FORD',2016,'FULL SIZE',107,'T', 'WP0AA2A79BL017124');
INSERT INTO `Vehicle` VALUES('QSC8709','MKZ','LINCOLN',2012,'LUXURY CAR',101,'T', 'WP0AA2A79BL017125');
INSERT INTO `Vehicle` VALUES('TGB8961','GENESIS','HYUNDAI',2013,'LUXURY CAR',102,'T', 'WP0AA2A79BL017126');
INSERT INTO `Vehicle` VALUES('MKU0172','TLX','ACURA',2014,'LUXURY CAR',103,'T', 'WP0AA2A79BL017127');
INSERT INTO `Vehicle` VALUES('CFT1908','328I','BMW',2015,'LUXURY CAR',104,'T', 'WP0AA2A79BL017128');
INSERT INTO `Vehicle` VALUES('WHM7619','AVALON','TOYOTA',2016,'LUXURY CAR',105,'T', 'WP0AA2A79BL017129');
INSERT INTO `Vehicle` VALUES('WLZ8955','ESCAPE','FORD',2012,'MID SIZE SUV',106,'T', 'WP0AA2A79BL017130');
INSERT INTO `Vehicle` VALUES('QIO7621','EQUINOX','CHEVROLET',2013,'MID SIZE SUV',107,'T', 'WP0AA2A79BL017131');
INSERT INTO `Vehicle` VALUES('YSN1927','PATHFINDER','NISSAN',2014,'MID SIZE SUV',101,'T', 'WP0AA2A79BL017132');
INSERT INTO `Vehicle` VALUES('EDM8610','GLA','MERCEDEZ BENZ',2015,'MID SIZE SUV',102,'T', 'WP0AA2A79BL017133');
INSERT INTO `Vehicle` VALUES('AHK7325','RAV4','TOYOTA',2016,'MID SIZE SUV',103,'T', 'WP0AA2A79BL017134');
INSERT INTO `Vehicle` VALUES('OHZ0976','EDGE','FORD',2012,'STANDARD SUV',104,'T', 'WP0AA2A79BL017135');
INSERT INTO `Vehicle` VALUES('RKS9862','TAHOE','CHEVROLET',2013,'STANDARD SUV',105,'T', 'WP0AA2A79BL017136');
INSERT INTO `Vehicle` VALUES('WIJ6190','EDGE','FORD',2014,'STANDARD SUV',106,'T', 'WP0AA2A79BL017137');
INSERT INTO `Vehicle` VALUES('ZDT8612','TAHOE','CHEVROLET',2015,'STANDARD SUV',107,'T', 'WP0AA2A79BL017138');
INSERT INTO `Vehicle` VALUES('LDJ7719','EDGE','FORD',2016,'STANDARD SUV',101,'T', 'WP0AA2A79BL017139');
INSERT INTO `Vehicle` VALUES('UIA8709','EXPEDITION','FORD',2012,'FULL SIZE SUV',102,'T', 'WP0AA2A79BL017140');
INSERT INTO `Vehicle` VALUES('WKJ7972','SEQUOIA','TOYOTA',2013,'FULL SIZE SUV',103,'T', 'WP0AA2A79BL017141');
INSERT INTO `Vehicle` VALUES('JLS1097','SUBURBAN','CHEVROLET',2014,'FULL SIZE SUV',104,'T', 'WP0AA2A79BL017142');
INSERT INTO `Vehicle` VALUES('UHJ6782','EXPEDITION','FORD',2015,'FULL SIZE SUV',105,'T', 'WP0AA2A79BL017143');
INSERT INTO `Vehicle` VALUES('XBM6822','SUBURBAN','CHEVROLET',2016,'FULL SIZE SUV',106,'T', 'WP0AA2A79BL017144');
INSERT INTO `Vehicle` VALUES('SHK7767','QUEST','NISSAN',2012,'MINI VAN',107,'T', 'WP0AA2A79BL017145');
INSERT INTO `Vehicle` VALUES('JSL7920','ODYSSEY','HONDA',2013,'MINI VAN',106,'T', 'WP0AA2A79BL017146');
INSERT INTO `Vehicle` VALUES('PAJ5289','GRAND CARAVAN','DODGE',2014,'MINI VAN',105,'T', 'WP0AA2A79BL017147');
INSERT INTO `Vehicle` VALUES('TSJ6290','QUEST','NISSAN',2015,'MINI VAN',104,'T', 'WP0AA2A79BL017148');
INSERT INTO `Vehicle` VALUES('MWO9296','ODYSSEY','HONDA',2016,'MINI VAN',103,'T', 'WP0AA2A79BL017149');
UNLOCK TABLES;


--
-- Price
--

DROP TABLE IF EXISTS `Price`;
CREATE TABLE `Price` (`LateFee` INT NOT NULL,`HourlyFee` INT NOT NULL,`VehicleTypeId` INT NOT NULL,PRIMARY KEY (`VehicleTypeId`)
);


--
-- Location
--

DROP TABLE IF EXISTS `Location`;
CREATE TABLE `Location` (`ID` INT NOT NULL,`Name` TEXT NOT NULL,`VehicleCapacity` INT NOT NULL,`Address` TEXT NOT NULL,`ContactNumber` INT(10) NOT NULL,PRIMARY KEY (`ID`)
);

LOCK TABLES `Location` WRITE;
INSERT INTO `Location` VALUES(101,'DALLAS LOVE FIELD AIRPORT',50,'Herb Kelleher Way, Dallas Texas, 75235', 1234567890);
INSERT INTO `Location` VALUES(102,'LOS ANGELES INTL AIRPORT',50,'World Way, Los Angeles California 90045', 1234567891);
INSERT INTO `Location` VALUES(103,'DALLAS/ FORT WORTH INTL AIRPORT',50,'International Pkwy, DFW Airport Texas, 75261', 1234567892);
INSERT INTO `Location` VALUES(104,'WEST HOUSTON AIRPORT',50,'Groschke Rd, Houston Texas, 77094', 1234567893);
INSERT INTO `Location` VALUES(105,'WASHINGTON DULLES INTL AIRPORT',50,'Saarinen Cir, Dulles Virginia, 20166', 1234567894);
INSERT INTO `Location` VALUES(106,'NEWARK LIBERTY INTL AIRPORT',50,'Brewster Rd, Newark New Jersey, 07114', 1234567895);
INSERT INTO `Location` VALUES(107,'SALT LAKE CITY INTL AIRPORT',50,'N Terminal Dr, Salt Lake City Utah, 84122', 1234567896);
UNLOCK TABLES;

--
-- Customer
--

DROP TABLE IF EXISTS `Customer`;
CREATE TABLE `Customer` (`Name` TEXT NOT NULL,`LicenseNumber` INT NOT NULL,`RegistrationDate` DATE NOT NULL,`RegistrationEndDate` DATE NOT NULL,`MembershipStatus` BOOLEAN NOT NULL,`Verified` BOOLEAN NOT NULL,`LicenseExpDate` DATE NOT NULL,`Address` TEXT NOT NULL,`Email` TEXT NOT NULL,`Id` INT NOT NULL AUTO_INCREMENT,PRIMARY KEY (`Id`)
);

--
-- Employee
--

DROP TABLE IF EXISTS `Employee`;
CREATE TABLE `Employee` (`Id` INT NOT NULL AUTO_INCREMENT,`Name` TEXT NOT NULL,`Role` TEXT NOT NULL,PRIMARY KEY (`Id`)
);

LOCK TABLES `Employee` WRITE;
INSERT INTO `Employee` VALUES(101,'Pranav','KING');
INSERT INTO `Employee` VALUES(102,'Wasae','Manager');
INSERT INTO `Employee` VALUES(103,'Jeyasri','Manager');
INSERT INTO `Employee` VALUES(104,'Subarna','Manager');
INSERT INTO `Employee` VALUES(105,'Ronak','Manager');
INSERT INTO `Employee` VALUES(106,'Manmeet','Manager');
INSERT INTO `Employee` VALUES(107,'Vignesh','Manager');
UNLOCK TABLES;


--
-- Reservation
--

DROP TABLE IF EXISTS `Reservation`;
CREATE TABLE `Reservation` (`Location` INT NOT NULL,`Vehicle` INT NOT NULL,`Customer` INT NOT NULL,`EstimatePrice` DOUBLE NOT NULL,`PickUpTime` DATETIME NOT NULL,`EstimateDropOffTime` DATETIME NOT NULL,`ActualDropOffTime` DATETIME,`Invoice` INT NOT NULL,PRIMARY KEY (`Invoice`)
);

--
-- User
--

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (`Username` VARCHAR(10) NOT NULL,`Password` VARCHAR(10) NOT NULL,`Id` INT NOT NULL,`Role` TEXT NOT NULL,PRIMARY KEY (`Id`)
);

--
-- Invoice
--

DROP TABLE IF EXISTS `Invoice`;
CREATE TABLE `Invoice` (`EstimatedPrice` DOUBLE NOT NULL,`VehicleStatus` INT,`DamageFee` DOUBLE,`LateFee` DOUBLE,`TotalPrice` DOUBLE,`Id` INT NOT NULL AUTO_INCREMENT,PRIMARY KEY (`Id`)
);

--
-- Damage
--

DROP TABLE IF EXISTS `Damage`;
CREATE TABLE `Damage` (`DamageType` TEXT NOT NULL,`DamageFee` DOUBLE NOT NULL,`VehicleTypeId` INT NOT NULL,`DamageId` INT NOT NULL,PRIMARY KEY (`DamageId`)
);
