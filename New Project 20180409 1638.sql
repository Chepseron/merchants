-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.25-MariaDB


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sgr
--

CREATE DATABASE IF NOT EXISTS sgr;
USE sgr;

--
-- Definition of table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
CREATE TABLE `bookings` (
  `idbookings` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `scheduleID` int(10) unsigned NOT NULL,
  `positiontaken` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idbookings`),
  KEY `FK_bookings_1` (`scheduleID`),
  CONSTRAINT `FK_bookings_1` FOREIGN KEY (`scheduleID`) REFERENCES `schedule` (`idschedule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookings`
--

/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;


--
-- Definition of table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
CREATE TABLE `passenger` (
  `idpassenger` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `sheduleID` int(10) unsigned NOT NULL,
  `amountPaid` varchar(45) NOT NULL,
  `seatNumber` int(10) unsigned NOT NULL,
  `idNumber` int(10) unsigned NOT NULL,
  `coach` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `dateOfTravel` datetime NOT NULL,
  `ticketNumber` varchar(45) NOT NULL,
  `confirmed` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`idpassenger`),
  KEY `FK_passenger_1` (`sheduleID`),
  CONSTRAINT `FK_passenger_1` FOREIGN KEY (`sheduleID`) REFERENCES `schedule` (`idschedule`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `passenger`
--

/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` (`idpassenger`,`name`,`sheduleID`,`amountPaid`,`seatNumber`,`idNumber`,`coach`,`email`,`dateOfTravel`,`ticketNumber`,`confirmed`) VALUES 
 (1,'Test Test',1,'1200',12,12345678,'','','0000-00-00 00:00:00','',0),
 (48,'Amon',1,'2000',0,12345567,'A','','0000-00-00 00:00:00','',0),
 (49,'Griffin',1,'5000',0,1234567,'A','chepseron@gmail.com','0000-00-00 00:00:00','',0),
 (50,'dffsdf',1,'1234',0,0,'A','chepseron@gmail.com','2018-04-12 00:00:00','3f2c0933-8c2d-49f8-bdcd-d44441c90625',0),
 (51,'dsadsad',1,'123213',0,0,'A','chepseron@gmail.com','2018-04-19 00:00:00','08fb85f6-9630-4bee-afaf-4e9150c094c5',0),
 (52,'chepseron',1,'1200',0,0,'A','chepseron@gmail.com','2018-04-20 00:00:00','14c22472-ce2a-4b30-a92c-91484b5134ef',0),
 (53,'aMON',1,'1200',0,12344,'A','chepseron@gmail.com','2018-04-19 00:00:00','fbfa3b04-41e6-4142-b422-a9293394bb21',0);
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;


--
-- Definition of table `payments`
--

DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments` (
  `idpayments` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `scheduleID` int(10) unsigned NOT NULL,
  `paymentType` varchar(45) NOT NULL,
  `passengerID` int(10) unsigned NOT NULL,
  `transactionID` varchar(100) NOT NULL,
  `confirmed` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`idpayments`),
  KEY `FK_payments_1` (`scheduleID`),
  KEY `FK_payments_2` (`passengerID`),
  CONSTRAINT `FK_payments_1` FOREIGN KEY (`scheduleID`) REFERENCES `schedule` (`idschedule`),
  CONSTRAINT `FK_payments_2` FOREIGN KEY (`passengerID`) REFERENCES `passenger` (`idpassenger`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payments`
--

/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` (`idpayments`,`scheduleID`,`paymentType`,`passengerID`,`transactionID`,`confirmed`) VALUES 
 (1,1,'MPESA',48,'Wed Apr 04 11:58:27 EAT 2018',0),
 (2,1,'MPESA',49,'Wed Apr 04 14:05:53 EAT 2018',0),
 (3,1,'MPESA',50,'Fri Apr 06 11:04:12 EAT 2018',0),
 (4,1,'MPESA',51,'Fri Apr 06 11:12:01 EAT 2018',0),
 (5,1,'MPESA',52,'Fri Apr 06 11:17:29 EAT 2018',0),
 (6,1,'MPESA',53,'Mon Apr 09 14:41:45 EAT 2018',0);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;


--
-- Definition of table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `idschedule` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `trainID` int(10) unsigned NOT NULL,
  `destination` varchar(100) NOT NULL,
  `source` varchar(100) NOT NULL,
  PRIMARY KEY (`idschedule`),
  KEY `FK_schedule_1` (`trainID`),
  CONSTRAINT `FK_schedule_1` FOREIGN KEY (`trainID`) REFERENCES `train` (`idtrain`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `schedule`
--

/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` (`idschedule`,`trainID`,`destination`,`source`) VALUES 
 (1,1,'Mombasa','');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;


--
-- Definition of table `train`
--

DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `idtrain` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `passengerNumber` int(10) unsigned NOT NULL,
  `identificationName` varchar(100) NOT NULL,
  PRIMARY KEY (`idtrain`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `train`
--

/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` (`idtrain`,`passengerNumber`,`identificationName`) VALUES 
 (1,23,'standard gauge railway');
/*!40000 ALTER TABLE `train` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `idusers` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `pword` varchar(45) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `groupID` int(10) unsigned DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` int(10) unsigned DEFAULT NULL,
  `staffID` int(10) unsigned DEFAULT NULL,
  `statusID` int(10) unsigned DEFAULT NULL,
  `createdBy` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idusers`),
  KEY `FK_user_1` (`groupID`),
  CONSTRAINT `FK_user_1` FOREIGN KEY (`groupID`) REFERENCES `usergroup` (`idgroups`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`idusers`,`username`,`pword`,`createdAt`,`lastLogin`,`groupID`,`name`,`email`,`phone`,`staffID`,`statusID`,`createdBy`) VALUES 
 (1,'test','test',NULL,NULL,1,'test','test@sgr.com',728140544,1,1,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
CREATE TABLE `usergroup` (
  `idgroups` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `createdBy` int(10) unsigned DEFAULT NULL,
  `responsibilities` varchar(100) NOT NULL,
  PRIMARY KEY (`idgroups`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usergroup`
--

/*!40000 ALTER TABLE `usergroup` DISABLE KEYS */;
INSERT INTO `usergroup` (`idgroups`,`name`,`createdAt`,`createdBy`,`responsibilities`) VALUES 
 (1,'Admin',NULL,NULL,'train'),
 (2,'Admin',NULL,NULL,'passenger'),
 (3,'Admin',NULL,NULL,'schedule'),
 (4,'Admin',NULL,NULL,'group'),
 (5,'Admin',NULL,NULL,'user'),
 (6,'Admin',NULL,NULL,'reports');
/*!40000 ALTER TABLE `usergroup` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
