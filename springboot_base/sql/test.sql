/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.25-log : Database - mydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mydb`;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

---学生表
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`tel`) values (1,'张三','123121231231'),(2,'李四','23243242332'),(3,'王五','23243242332'),(4,'赵六','3243242134'),(5,'小明','7234673242'),(6,'小花','478327849792');

/*Table structure for table `t_customer` */

DROP TABLE IF EXISTS `t_customer`;

---顾客表
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_customer` */

insert  into `t_customer`(`id`,`name`,`gender`) values (1,'老王','男');

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

---订单表
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderno` varchar(255) DEFAULT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `cust_id` int(11) DEFAULT NULL,
  `DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgt3n29ngim2bryiw3eimwjs52` (`cust_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`orderno`,`productName`,`cust_id`,`DATE`) values (1,'201907173y28423','红米Note7',1,'2019-08-24 10:30:42'),(2,'201907173y32899498','小米9',1,NULL),(3,'1213232324','meizu16s',1,'2019-07-18 15:59:22'),(4,'1213232324','meizu16s',1,'2019-07-18 15:59:36');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
