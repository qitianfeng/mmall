/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.28 : Database - mall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mall`;

/*Table structure for table `cart_item` */

DROP TABLE IF EXISTS `cart_item`;

CREATE TABLE `cart_item` (
  `cart_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
  `good_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `quantity` bigint(20) DEFAULT NULL COMMENT '购买数量',
  `good_pic` varchar(200) DEFAULT NULL COMMENT '商品主图',
  `good_name` varchar(200) DEFAULT NULL COMMENT '商品名字',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_status` int(1) DEFAULT NULL COMMENT '删除状态',
  `check_status` int(1) DEFAULT NULL COMMENT '是否被勾选',
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cart_item` */

LOCK TABLES `cart_item` WRITE;

UNLOCK TABLES;

/*Table structure for table `content` */

DROP TABLE IF EXISTS `content`;

CREATE TABLE `content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `good_id` bigint(20) NOT NULL COMMENT '内容类目ID',
  `title` varchar(200) DEFAULT NULL COMMENT '内容标题',
  `url` varchar(500) DEFAULT NULL COMMENT '链接',
  `pic` varchar(300) DEFAULT NULL COMMENT '图片绝对路径',
  `status` varchar(1) DEFAULT NULL COMMENT '状态,0无效，1有效',
  `sort_order` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `good_id` (`good_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Data for the table `content` */

LOCK TABLES `content` WRITE;

insert  into `content`(`id`,`good_id`,`title`,`url`,`pic`,`status`,`sort_order`) values (49,1,'《我们相爱了》情人节告白PPT模板',NULL,'http://101.132.142.155:8080/group/M00/00/00/rBEkfF6-FyOAbgf_AABUYzmNVKE946.png','0',NULL);

UNLOCK TABLES;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名字',
  `goods_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `goods_desc` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `goods_add_time` datetime DEFAULT NULL COMMENT '商品添加时间',
  `goods_status` int(1) DEFAULT NULL COMMENT '1 表示商品上架，0 表示商品下架',
  `goods_author` varchar(25) DEFAULT NULL COMMENT '商品作者',
  `goods_image` varchar(200) DEFAULT NULL COMMENT '商品图片',
  `goods_download` varchar(200) DEFAULT NULL COMMENT '商品下载链接',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

LOCK TABLES `goods` WRITE;

insert  into `goods`(`goods_id`,`goods_name`,`goods_price`,`goods_desc`,`goods_add_time`,`goods_status`,`goods_author`,`goods_image`,`goods_download`) values (1,'《我们相爱了》情人节告白PPT模板','10.00','这是一套清新唯美风格的，《我们相爱了》情人节告白PPT模板。共24张；  PPT模板使用了白色窗帘与玫瑰花PPT背景图。首页左侧放置情侣相片，右侧填写《我们相爱了》PPT标题文字。界面清新唯美。  PowerPoint模板内容页，使用了多张甜蜜情侣照片，搭配小清新的爱情文案排版。幻灯片图表为22张紫色动态PPT图表。  本模板适合用于制作情人节告白PPT，情侣相册PPT，甜蜜爱情相册PPT等。.PPTX格式；','2020-05-15 12:14:38',1,'祁天锋','http://101.132.142.155:8080/group/M00/00/00/rBEkfF6-FyOAbgf_AABUYzmNVKE946.png','http://101.132.142.155:8080/group/M00/00/00/rBEkfF6-FymAEHKcABwypgVovC0227.zip'),(2,'111','2.00','123',NULL,1,NULL,'FyOAbgf_AABUYzmNVKE946.png',NULL);

UNLOCK TABLES;

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；',
  `money_amount` decimal(10,2) DEFAULT NULL COMMENT '用户余额',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `goods_pic` varbinary(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

LOCK TABLES `order` WRITE;

UNLOCK TABLES;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nickname` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(30) DEFAULT NULL COMMENT '用户密码',
  `mobile` varchar(30) DEFAULT NULL COMMENT '用户手机',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

LOCK TABLES `user` WRITE;

insert  into `user`(`id`,`nickname`,`password`,`mobile`,`gmt_create`,`gmt_modified`) values (1,'nick',NULL,NULL,NULL,NULL);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
