/*
Navicat MySQL Data Transfer

Source Server         : local MySQL server
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : gttss

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-04-09 23:58:05
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
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------

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
