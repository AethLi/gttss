/*
Navicat MySQL Data Transfer

Source Server         : local MySql server
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : gttss

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-04-18 23:10:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for batch
-- ----------------------------
DROP TABLE IF EXISTS `batch`;
CREATE TABLE `batch` (
  `batchId` varchar(36) NOT NULL,
  `batchName` varchar(30) DEFAULT NULL,
  `batchDate` datetime DEFAULT NULL,
  PRIMARY KEY (`batchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of batch
-- ----------------------------

-- ----------------------------
-- Table structure for classname
-- ----------------------------
DROP TABLE IF EXISTS `classname`;
CREATE TABLE `classname` (
  `code` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `repeatIndex` int(2) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classname
-- ----------------------------
INSERT INTO `classname` VALUES ('2abf84db-58e8-11e9-bfc6-80fa5b585ee4', '网络工程', '1');

-- ----------------------------
-- Table structure for origin
-- ----------------------------
DROP TABLE IF EXISTS `origin`;
CREATE TABLE `origin` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of origin
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `userId` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `classCode` varchar(36) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  `batchCode` varchar(36) DEFAULT NULL,
  `gradeName` varchar(20) DEFAULT NULL,
  `studentNum` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('8dc6cc3e-5c66-11e9-b206-80fa5b585ee4', '杨仁杰', null, '0', null, '2015级', '201510');

-- ----------------------------
-- Table structure for studentbatchgroup
-- ----------------------------
DROP TABLE IF EXISTS `studentbatchgroup`;
CREATE TABLE `studentbatchgroup` (
  `studentId` varchar(36) NOT NULL,
  `batchCode` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentbatchgroup
-- ----------------------------

-- ----------------------------
-- Table structure for sysuser
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `userId` varchar(36) NOT NULL,
  `account` varchar(20) NOT NULL,
  `permission` int(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('8dc6cc3e-5c66-11e9-b206-80fa5b585ee4', '201510', '0', '96e79218965eb72c92a549dd5a330112', '0');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `userId` varchar(36) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `unitId` varchar(36) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` varchar(36) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `teacherId` varchar(36) DEFAULT NULL,
  `selectStatus` int(1) DEFAULT NULL,
  `needStudent` int(2) DEFAULT NULL,
  `sourceId` varchar(36) DEFAULT NULL,
  `typeId` varchar(36) DEFAULT NULL,
  `teacher2Id` varchar(36) DEFAULT NULL,
  `propertyId` varchar(36) DEFAULT NULL,
  `compare` int(1) DEFAULT NULL,
  `content` text,
  `result` text,
  `reference` text,
  `planLinkKey` varchar(36) DEFAULT NULL,
  `teacherVerifyId` varchar(36) DEFAULT NULL,
  `adminVerifyId` varchar(36) DEFAULT NULL,
  `createDt` datetime DEFAULT NULL,
  `createBy` varchar(36) DEFAULT NULL,
  `validityBatch` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('dfas', 'fsdfas', 'ohioh', '1', '1', '1dfasdf', 'fdsfsfas', 'dfgsdgsdf', '22', '23424', '4234', '4', '34', '4', '324', '2424', '2019-04-02 22:47:40', '42423', '23');

-- ----------------------------
-- Table structure for topicproperty
-- ----------------------------
DROP TABLE IF EXISTS `topicproperty`;
CREATE TABLE `topicproperty` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topicproperty
-- ----------------------------

-- ----------------------------
-- Table structure for topictype
-- ----------------------------
DROP TABLE IF EXISTS `topictype`;
CREATE TABLE `topictype` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topictype
-- ----------------------------

-- ----------------------------
-- Table structure for verify
-- ----------------------------
DROP TABLE IF EXISTS `verify`;
CREATE TABLE `verify` (
  `id` varchar(36) NOT NULL,
  `type` int(1) DEFAULT NULL,
  `explanation` text,
  `createDt` datetime DEFAULT NULL,
  `createBy` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of verify
-- ----------------------------
