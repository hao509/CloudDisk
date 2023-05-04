-- MySQL dump 10.13  Distrib 5.5.15, for Win32 (x86)
--
-- Host: localhost    Database: clouddisk111
-- ------------------------------------------------------
-- Server version	5.5.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `adminid` varchar(20) NOT NULL COMMENT '管理员id',
  `adminname` varchar(20) NOT NULL COMMENT '管理员姓名',
  `passwd` varchar(50) NOT NULL COMMENT '管理员账号密码',
  `adminphone` varchar(20) NOT NULL COMMENT '管理员电话',
  PRIMARY KEY (`adminid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc`
--

DROP TABLE IF EXISTS `doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc` (
  `docid` int(20) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `docfakename` varchar(20) NOT NULL COMMENT '文件虚假名',
  `docowner` varchar(20) NOT NULL COMMENT '拥有者id',
  `docsize` decimal(20,2) NOT NULL COMMENT '文件大小',
  PRIMARY KEY (`docid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc`
--

LOCK TABLES `doc` WRITE;
/*!40000 ALTER TABLE `doc` DISABLE KEYS */;
INSERT INTO `doc` VALUES (1,'教育部学籍在线验证报告_张仲浩.jpg','zzc',0.74),(14,'照片.png','yx',14962.00),(15,'d3比例尺.doc','yx',229376.00),(16,'first.txt','yx',177483.00),(17,'张仲浩.docx','yx',12298.00),(18,'1681116048945.xlsx','yx',13046.00),(19,'axios.png','yx',42909.00),(20,'axios(1).png','yx',42909.00);
/*!40000 ALTER TABLE `doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docfake`
--

DROP TABLE IF EXISTS `docfake`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docfake` (
  `docid` varchar(20) NOT NULL COMMENT '文件id',
  `docname` varchar(20) NOT NULL COMMENT '文件真实名',
  `docfakename` varchar(20) NOT NULL COMMENT '文件虚假名',
  PRIMARY KEY (`docid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docfake`
--

LOCK TABLES `docfake` WRITE;
/*!40000 ALTER TABLE `docfake` DISABLE KEYS */;
/*!40000 ALTER TABLE `docfake` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docshare`
--

DROP TABLE IF EXISTS `docshare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docshare` (
  `shareid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(20) NOT NULL,
  `docid` int(20) NOT NULL,
  `docplace` varchar(50) NOT NULL,
  PRIMARY KEY (`shareid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docshare`
--

LOCK TABLES `docshare` WRITE;
/*!40000 ALTER TABLE `docshare` DISABLE KEYS */;
INSERT INTO `docshare` VALUES (13,'yx',15,'\\d3比例尺.doc'),(14,'yx',14,'\\照片.png'),(15,'yx',15,'\\d3比例尺.doc'),(16,'yx',15,'\\d3比例尺.doc'),(17,'yx',15,'\\d3比例尺.doc'),(18,'yx',16,'\\first.txt'),(19,'yx',16,'\\first.txt');
/*!40000 ALTER TABLE `docshare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docshare_copy1`
--

DROP TABLE IF EXISTS `docshare_copy1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docshare_copy1` (
  `shareid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(20) NOT NULL,
  `docid` varchar(20) NOT NULL,
  `docplace` varchar(50) NOT NULL,
  PRIMARY KEY (`shareid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docshare_copy1`
--

LOCK TABLES `docshare_copy1` WRITE;
/*!40000 ALTER TABLE `docshare_copy1` DISABLE KEYS */;
/*!40000 ALTER TABLE `docshare_copy1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendship`
--

DROP TABLE IF EXISTS `friendship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendship` (
  `friendid` varchar(20) NOT NULL COMMENT '好友关系标签',
  `user1` varchar(20) NOT NULL COMMENT '好友1的id',
  `user2` varchar(20) NOT NULL COMMENT '好友2的id',
  PRIMARY KEY (`friendid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendship`
--

LOCK TABLES `friendship` WRITE;
/*!40000 ALTER TABLE `friendship` DISABLE KEYS */;
/*!40000 ALTER TABLE `friendship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `menu_pid` int(11) DEFAULT NULL,
  `menu_type` int(255) DEFAULT NULL,
  `menu_click` varchar(255) DEFAULT NULL,
  `menu_icon` varchar(255) DEFAULT NULL,
  `menu_state` varchar(255) DEFAULT '1',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (-2,'上帝权限','*',NULL,0,NULL,NULL,'1'),(-1,'系统父节点',NULL,-2,0,NULL,NULL,'1'),(1,'test1','test1',-1,0,NULL,'el-icon-setting','1'),(2,'test11','test11',1,0,'toTest11','el-icon-user-solid','1'),(3,'test22','test22',1,0,'toTest22','el-icon-s-check','1'),(6,'test2','test2',-1,0,NULL,'el-icon-reading','1'),(7,'test21','test21',6,0,'toTest21','el-icon-wallet','1'),(8,'test22','test22',6,0,'toTest22','el-icon-document-copy','1'),(9,'菜单列表','menu',6,0,'toMenuList','el-icon-wallet','1');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `op`
--

DROP TABLE IF EXISTS `op`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `op` (
  `opid` varchar(20) NOT NULL COMMENT '操作id',
  `oprole` varchar(20) NOT NULL COMMENT '操作角色',
  `opmethod` varchar(20) NOT NULL COMMENT '操作方式',
  `opdate` datetime NOT NULL COMMENT '操作时间',
  `opres` varchar(20) NOT NULL COMMENT '操作结果',
  PRIMARY KEY (`opid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `op`
--

LOCK TABLES `op` WRITE;
/*!40000 ALTER TABLE `op` DISABLE KEYS */;
/*!40000 ALTER TABLE `op` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` varchar(20) NOT NULL COMMENT '用户id',
  `limits` varchar(20) NOT NULL COMMENT '用户权限分级（senior，junior，primary）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` varchar(20) NOT NULL COMMENT 'id',
  `userole` varchar(20) NOT NULL COMMENT '用户角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('yx','user'),('zzc','user'),('zzh','admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `realName` varchar(20) NOT NULL,
  `phoneNum` varchar(11) NOT NULL,
  `idStatus` int(11) NOT NULL,
  `userspace` varchar(50) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('yx','yx','73fee192744f842a611a5ec336459f5c','yx','18188888888',1,'524,288,000'),('zzc','zzc','e10adc3949ba59abbe56e057f20f883e','zzc','12121212121',1,'524,288,000'),('zzh','zzh','e10adc3949ba59abbe56e057f20f883e','zzh','18188482067',1,'524,288,000');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-04 22:21:47
