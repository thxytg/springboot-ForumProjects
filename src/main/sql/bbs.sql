`user`/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.39 : Database - af_bbs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`af_bbs` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `af_bbs`;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `creator` INT(11) DEFAULT NULL COMMENT '创建者',
  `title` VARCHAR(100) DEFAULT NULL COMMENT '标题',
  `content` TEXT COMMENT '内容',
  `cat1` INT(11) DEFAULT NULL COMMENT '1级板块',
  `cat2` INT(11) DEFAULT NULL COMMENT '2级板块',
  `cat3` INT(11) DEFAULT NULL,
  `ref1` bigint(20) DEFAULT NULL COMMENT '引文ID(楼主)',
  `ref2` bigint(20) DEFAULT NULL,
  `refstr` varchar(100) DEFAULT NULL,
  `timeCreate` datetime DEFAULT NULL,
  `timeUpdate` datetime DEFAULT NULL,
  `niceFlag` int(4) DEFAULT NULL COMMENT '精华标识',
  `topFlag` int(4) DEFAULT NULL COMMENT '置顶标题',
  `banFlag` tinyint(1) DEFAULT NULL COMMENT 'reserved',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标识',
  `closeFlag` tinyint(1) DEFAULT NULL COMMENT '完结标识',
  `numReply` int(5) DEFAULT NULL COMMENT '回复数',
  `numLike` int(5) DEFAULT NULL COMMENT '点赞数',
  `storePath` varchar(200) DEFAULT NULL COMMENT '图片存储路径',
  `imgCount` int(2) DEFAULT NULL,
  `img1` varchar(100) DEFAULT NULL COMMENT '图片1',
  `img2` varchar(100) DEFAULT NULL,
  `img3` varchar(100) DEFAULT NULL,
  `replyUser` int(11) DEFAULT NULL COMMENT '最近一次回复',
  `replyName` varchar(50) DEFAULT NULL COMMENT '回复者的名称',
  `replyTime` datetime DEFAULT NULL,
  `replyText` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `topFlag` (`topFlag`),
  KEY `replyTime` (`replyTime`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`creator`,`title`,`content`,`cat1`,`cat2`,`cat3`,`ref1`,`ref2`,`refstr`,`timeCreate`,`timeUpdate`,`niceFlag`,`topFlag`,`banFlag`,`delFlag`,`closeFlag`,`numReply`,`numLike`,`storePath`,`imgCount`,`img1`,`img2`,`img3`,`replyUser`,`replyName`,`replyTime`,`replyText`) values (1,9,'测试Admin对帖子置顶加精删除','哈哈哈',0,0,0,0,0,NULL,'2020-02-18 10:45:40',NULL,1,1,0,0,0,1,0,'202002/18/15820227400452/',0,'15820227390581.jpg','','',19,'b','2020-02-18 20:09:59','bbbbbbbbbbbbbbbb'),(2,9,'测试二','哈哈哈哈哈啊啊啊',0,0,0,0,0,NULL,'2020-02-18 10:51:28',NULL,1,0,0,0,0,0,0,'202002/18/15820230876571/',0,'','','',9,'admin','2020-02-18 10:51:28','测试二'),(38,9,'和H和H','VDSCDSCDS',0,0,0,0,0,NULL,'2020-02-18 12:08:13',NULL,0,0,0,0,0,0,0,'202002/18/15820276932201/',0,'','','',9,'admin','2020-02-18 12:08:13','和H和H'),(39,9,'VFDVVVVVVVVVVVV','VVV',0,0,0,0,0,NULL,'2020-02-18 12:08:21',NULL,0,0,0,0,0,0,0,'202002/18/15820277006232/',0,'','','',9,'admin','2020-02-18 12:08:21','VFDVVVVVVVVVVVV'),(40,19,'bbbbbbbbbbbbbb','bbbbbbbbbbbbbb',0,0,0,0,0,NULL,'2020-02-18 12:09:45',NULL,0,0,0,0,0,0,0,'202002/18/15820277851183/',0,'','','',19,'b','2020-02-18 12:09:45','bbbbbbbbbbbbbb'),(41,19,'bbbbbbbbbbbbbbbb','bbbbbbbbbbbbbbbb',0,0,0,1,0,NULL,'2020-02-18 12:09:59',NULL,0,0,0,0,0,0,0,'202002/18/15820277992554/',0,'','','',NULL,NULL,NULL,NULL),(42,18,'发帖权限测试','第一次发帖。第一次发帖。',0,0,0,0,0,NULL,'2020-02-18 12:44:15',NULL,0,0,0,0,0,0,0,'202002/18/15820298546301/',0,'','','',18,'a','2020-02-18 12:44:15','发帖权限测试'),(43,18,'发帖权限测试','第一次发帖。第一次发帖。',0,0,0,0,0,NULL,'2020-02-18 12:44:16',NULL,0,0,0,0,0,0,0,'202002/18/15820298561312/',0,'','','',18,'a','2020-02-18 12:44:16','发帖权限测试'),(44,18,'sssssss','sssssssss',0,0,0,0,0,NULL,'2020-02-18 12:46:56',NULL,0,0,0,0,0,0,0,'202002/18/15820300159471/',0,'','','',18,'a','2020-02-18 12:46:56','sssssss'),(45,18,'ssssssssssssssssssaaa','ssssssssssssssssssaaa',0,0,0,0,0,NULL,'2020-02-18 12:47:05',NULL,0,0,0,0,0,0,0,'202002/18/15820300253472/',0,'','','',18,'a','2020-02-18 12:47:05','ssssssssssssssssssaaa'),(46,18,'cccccccccc','cccccccccc',0,0,0,0,0,NULL,'2020-02-18 12:47:10',NULL,0,0,0,0,0,0,0,'202002/18/15820300296713/',0,'','','',18,'a','2020-02-18 12:47:10','cccccccccc'),(47,18,'ccccccccccc','ccccccccccc',0,0,0,0,0,NULL,'2020-02-18 12:47:13',NULL,0,0,0,0,0,0,0,'202002/18/15820300334554/',0,'','','',18,'a','2020-02-18 12:47:13','ccccccccccc'),(48,18,'cccccccccccc','cccccccccccc',0,0,0,0,0,NULL,'2020-02-18 12:47:17',NULL,0,0,0,0,0,0,0,'202002/18/15820300367265/',0,'','','',18,'a','2020-02-18 12:47:17','cccccccccccc'),(49,18,'ccccccccc','ccccccccc',0,0,0,0,0,NULL,'2020-02-18 12:47:21',NULL,0,0,0,0,0,0,0,'202002/18/15820300413996/',0,'','','',18,'a','2020-02-18 12:47:21','ccccccccc'),(50,18,'cccccccccc','cccccccccc',0,0,0,0,0,NULL,'2020-02-18 12:47:25',NULL,0,0,0,0,0,0,0,'202002/18/15820300451977/',0,'','','',18,'a','2020-02-18 12:47:25','cccccccccc'),(51,19,'xbbbbbbbbbbb','xbbbbbbbbbbb',0,0,0,0,0,NULL,'2020-02-18 12:54:11',NULL,0,0,0,0,0,0,0,'202002/18/15820304511401/',0,'','','',19,'b','2020-02-18 12:54:11','xbbbbbbbbbbb'),(52,19,'bbbbbbbbbbbbbbb','bbbbbbbbbbbbbbb',0,0,0,0,0,NULL,'2020-02-18 12:54:15',NULL,0,0,0,0,0,0,0,'202002/18/15820304550382/',0,'','','',19,'b','2020-02-18 12:54:15','bbbbbbbbbbbbbbb'),(53,19,'bbbbbbbbbbb','bbbbbbbbbbb',0,0,0,0,0,NULL,'2020-02-18 12:54:20',NULL,0,0,0,0,0,0,0,'202002/18/15820304597563/',0,'','','',19,'b','2020-02-18 12:54:20','bbbbbbbbbbb'),(54,19,'bbbbbbbbbbb','bbbbbbbbbbb',0,0,0,0,0,NULL,'2020-02-18 12:54:23',NULL,0,0,0,0,0,0,0,'202002/18/15820304629674/',0,'','','',19,'b','2020-02-18 12:54:23','bbbbbbbbbbb'),(55,19,'bbbbbbbbbbb','bbbbbbbbbbb',0,0,0,0,0,NULL,'2020-02-18 12:54:26',NULL,0,0,0,0,0,0,0,'202002/18/15820304662655/',0,'','','',19,'b','2020-02-18 12:54:26','bbbbbbbbbbb'),(56,19,'bbbbbbbbb','bbbbbbbbb',0,0,0,0,0,NULL,'2020-02-18 12:54:30',NULL,0,0,0,0,0,0,0,'202002/18/15820304699676/',0,'','','',19,'b','2020-02-18 12:54:30','bbbbbbbbb'),(57,18,'cccccccccccccc','cccccccccccccc',0,0,0,0,0,NULL,'2020-02-18 13:03:38',NULL,0,0,0,0,0,0,0,'202002/18/15820310182851/',0,'','','',18,'a','2020-02-18 13:03:38','cccccccccccccc'),(58,18,'cccccccccc','cccccccccc',0,0,0,0,0,NULL,'2020-02-18 13:05:29',NULL,0,0,0,0,0,0,0,'202002/18/15820311286011/',0,'','','',18,'a','2020-02-18 13:05:29','cccccccccc'),(59,18,'ccccccccccccc','ccccccccccccc',0,0,0,0,0,NULL,'2020-02-18 13:05:37',NULL,0,0,0,0,0,0,0,'202002/18/15820311368832/',0,'','','',18,'a','2020-02-18 13:05:37','ccccccccccccc');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `qqid` varchar(50) DEFAULT NULL COMMENT 'qq的openid',
  `qq` varchar(30) DEFAULT NULL,
  `qqFlag` tinyint(1) DEFAULT NULL,
  `qqName` varchar(100) DEFAULT NULL COMMENT '最初的QQ昵称',
  `email` varchar(100) DEFAULT NULL,
  `emailFlag` tinyint(1) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `phoneFlag` tinyint(1) DEFAULT NULL,
  `thumb` varchar(200) DEFAULT NULL COMMENT '头像',
  `level` int(11) DEFAULT NULL COMMENT '用户等级',
  `vip` tinyint(4) DEFAULT NULL COMMENT 'VIP等级',
  `vipName` varchar(30) DEFAULT NULL COMMENT '头衔',
  `isAdmin` tinyint(1) DEFAULT NULL COMMENT '是否超级管理',
  `timeCreate` datetime DEFAULT NULL COMMENT '注册时间',
  `timeUpdate` datetime DEFAULT NULL,
  `timeLogin` datetime DEFAULT NULL COMMENT '最后一次登录',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `qqid` (`qqid`),
  UNIQUE KEY `qq` (`qq`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`qqid`,`qq`,`qqFlag`,`qqName`,`email`,`emailFlag`,`phone`,`phoneFlag`,`thumb`,`level`,`vip`,`vipName`,`isAdmin`,`timeCreate`,`timeUpdate`,`timeLogin`) values (1,'祝雪震','admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\\img2\\userPhoto\\1\\15817537203602.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'admin','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,'',1,NULL,NULL,NULL),(18,'a','a',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,'',0,NULL,NULL,NULL),(19,'b','b',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,'',0,NULL,NULL,NULL);

/*Table structure for table `user_ability` */

DROP TABLE IF EXISTS `user_ability`;

CREATE TABLE `user_ability` (
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `banFlag` int(11) DEFAULT NULL COMMENT '临时禁言标识',
  `banDate` datetime DEFAULT NULL,
  `imageCount` int(11) DEFAULT NULL,
  `imageMax` int(11) DEFAULT NULL COMMENT '上传图片额度',
  `msgCount` int(11) DEFAULT NULL,
  `msgMax` int(11) DEFAULT NULL COMMENT '发帖的额度',
  `replyCount` int(11) DEFAULT NULL,
  `replyMax` int(11) DEFAULT NULL COMMENT '回复的额度',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_ability` */

insert  into `user_ability`(`userId`,`banFlag`,`banDate`,`imageCount`,`imageMax`,`msgCount`,`msgMax`,`replyCount`,`replyMax`) values (17,0,NULL,10,10,5,5,20,20),(18,0,NULL,10,10,5,5,20,20),(19,0,NULL,10,10,5,5,20,20);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
