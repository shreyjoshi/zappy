-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.17-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema project
--

CREATE DATABASE IF NOT EXISTS project;
USE project;

--
-- Definition of table `admintable`
--

DROP TABLE IF EXISTS `admintable`;
CREATE TABLE `admintable` (
  `id` varchar(10) default NULL,
  `password` varchar(15) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admintable`
--

/*!40000 ALTER TABLE `admintable` DISABLE KEYS */;
INSERT INTO `admintable` (`id`,`password`) VALUES 
 ('101','101'),
 ('102','102');
/*!40000 ALTER TABLE `admintable` ENABLE KEYS */;


--
-- Definition of table `carttable`
--

DROP TABLE IF EXISTS `carttable`;
CREATE TABLE `carttable` (
  `id` int(10) NOT NULL,
  `name` varchar(45) default NULL,
  `price` double(9,2) default NULL,
  `weight` double(9,2) default NULL,
  `description` varchar(80) default NULL,
  `image` varchar(30) default NULL,
  `ipaddress` varchar(30) default NULL,
  `quantity` int(5) default NULL,
  `dateonly` varchar(20) default NULL,
  `status` int(11) default NULL,
  `customerid` int(11) default NULL,
  `customeremail` varchar(40) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `carttable`
--

/*!40000 ALTER TABLE `carttable` DISABLE KEYS */;
/*!40000 ALTER TABLE `carttable` ENABLE KEYS */;


--
-- Definition of table `customertable`
--

DROP TABLE IF EXISTS `customertable`;
CREATE TABLE `customertable` (
  `id` int(10) NOT NULL auto_increment,
  `email` varchar(25) NOT NULL,
  `firstname` varchar(15) default NULL,
  `lastname` varchar(15) default NULL,
  `address1` varchar(45) default NULL,
  `address2` varchar(45) default NULL,
  `postalcode` varchar(6) default NULL,
  `city` varchar(50) default NULL,
  `state` varchar(50) default NULL,
  `mobilenumber` varchar(15) default NULL,
  `password` varchar(20) default NULL,
  PRIMARY KEY  (`email`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customertable`
--

/*!40000 ALTER TABLE `customertable` DISABLE KEYS */;
INSERT INTO `customertable` (`id`,`email`,`firstname`,`lastname`,`address1`,`address2`,`postalcode`,`city`,`state`,`mobilenumber`,`password`) VALUES 
 (1,'adarshbatham@gmail.com','Krishna','Vasudeva Namah','Vridavan, Gujarat','Vrindavan, Gujarat','452020','Gujarat ','M.P.','8517913075','101'),
 (2,'adarshbatham@yahoo.com','Mohan','Sharma','Indore','Indore','451012','Indore','M.P.','9147852369','102'),
 (3,'firozpatel3210@gmail.com','Firoz','Patel','Indore','Indore','452020','Indore','MP','8514697631','101'),
 (4,'krishna@gmail.com','Krishna','Vasudeva','Indore','Indore','452020','Indore','MP','8517913075','101'),
 (6,'swaroop@gmail.com','Swaroop','Patidar','						\r\n		Tillore','Tillore						\r\n				','452010','Indore','Madhya Pradesh','9854712563','102');
/*!40000 ALTER TABLE `customertable` ENABLE KEYS */;


--
-- Definition of table `ordertable`
--

DROP TABLE IF EXISTS `ordertable`;
CREATE TABLE `ordertable` (
  `id` int(11) default NULL,
  `name` varchar(25) default NULL,
  `price` double(9,2) default NULL,
  `weight` double(9,2) default NULL,
  `description` varchar(60) default NULL,
  `image` varchar(45) default NULL,
  `dateonly` varchar(20) default NULL,
  `sumquantity` int(11) default NULL,
  `sumprice` double(9,2) default NULL,
  `status` int(10) default NULL,
  `ipaddress` varchar(40) default NULL,
  `customerid` int(11) default NULL,
  `customeremail` varchar(50) default NULL,
  `uniqueid` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`uniqueid`),
  UNIQUE KEY `uniqueid` (`uniqueid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ordertable`
--

/*!40000 ALTER TABLE `ordertable` DISABLE KEYS */;
INSERT INTO `ordertable` (`id`,`name`,`price`,`weight`,`description`,`image`,`dateonly`,`sumquantity`,`sumprice`,`status`,`ipaddress`,`customerid`,`customeremail`,`uniqueid`) VALUES 
 (1,'Bran and fiber cake',40.00,100.00,'Very Nice required','choclate_cake.jpg','13/10/2017',1,40.00,3,'127.0.0.1',NULL,'adarshbatham@gmail.com',21),
 (2,'Brownie cake',20.00,100.00,'Nice','brownie.jpg','13/10/2017',1,20.00,3,'127.0.0.1',NULL,'adarshbatham@gmail.com',22),
 (3,'chocolate cake',10.00,100.00,'Nice','choclate_cake.jpg','13/10/2017',1,10.00,3,'127.0.0.1',NULL,'adarshbatham@gmail.com',23),
 (4,'Dahi vada ',20.00,120.00,'Nice','dahi_vada.jpg','13/10/2017',1,20.00,3,'127.0.0.1',NULL,'adarshbatham@gmail.com',24),
 (1,'Bran and fiber cake',40.00,100.00,'Very Nice required','choclate_cake.jpg','13/10/2017',1,40.00,3,'127.0.0.1',NULL,'adarshbatham@gmail.com',25),
 (3,'chocolate cake',10.00,100.00,'Nice','choclate_cake.jpg','13/10/2017',1,10.00,3,'127.0.0.1',NULL,'adarshbatham@gmail.com',26),
 (2,'Brownie cake',20.00,100.00,'Nice','brownie.jpg','13/10/2017',1,20.00,3,'127.0.0.1',NULL,'adarshbatham@gmail.com',27),
 (4,'Dahi vada ',20.00,120.00,'Nice','dahi_vada.jpg','13/10/2017',1,20.00,3,'127.0.0.1',NULL,'adarshbatham@gmail.com',28);
/*!40000 ALTER TABLE `ordertable` ENABLE KEYS */;


--
-- Definition of table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(45) default NULL,
  `price` double(9,2) default NULL,
  `weight` double(9,2) default NULL,
  `description` varchar(45) default NULL,
  `image` varchar(45) default NULL,
  `date` varchar(40) default NULL,
  `dateonly` varchar(40) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`,`name`,`price`,`weight`,`description`,`image`,`date`,`dateonly`) VALUES 
 (1,'Bran and fiber cake',40.00,100.00,'Very Nice required','choclate_cake.jpg','Sat Oct 21 22:41:26 IST 2017','21/9/2017'),
 (2,'Brownie cake',20.00,100.00,'Nice','brownie.jpg','Sat Oct 21 22:49:45 IST 2017','21/9/2017'),
 (3,'chocolate cake',10.00,100.00,'Nice','choclate_cake.jpg','Sat Oct 21 22:50:10 IST 2017','21/9/2017'),
 (4,'Dahi vada ',20.00,120.00,'Nice','dahi_vada.jpg','Sat Oct 21 22:51:21 IST 2017','21/9/2017'),
 (5,'coconut drink',10.00,20.00,'Nice','Coconut.jpg','Sun Oct 22 14:45:00 IST 2017','22/9/2017'),
 (8,'Mango drink',10.00,40.00,'Nice','mango.jpg','Sun Oct 22 14:57:15 IST 2017','22/9/2017'),
 (9,'Khaman Dhokla',50.00,60.00,'Nice','Khaman dhokla.jpg','Sun Oct 22 15:04:25 IST 2017','22/9/2017'),
 (10,'Khatta Dhokla',60.00,50.00,'Nice','khatta dhokla.jpg','Sun Oct 22 15:04:46 IST 2017','22/9/2017'),
 (11,'Masala Idli',50.00,60.00,'NIce','masala idli.jpg','Sun Oct 22 15:05:20 IST 2017','22/9/2017');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
