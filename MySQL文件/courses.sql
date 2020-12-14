/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : studentmanage

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2020-11-08 22:33:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`,`name`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES ('1', '高等数学（1）');
INSERT INTO `courses` VALUES ('2', '大学英语（1）');
INSERT INTO `courses` VALUES ('3', '思想道德修养与法律基础');
INSERT INTO `courses` VALUES ('4', '体育（1）');
INSERT INTO `courses` VALUES ('5', 'C语言程序设计课程设计');
INSERT INTO `courses` VALUES ('6', '计算机科学概论');
INSERT INTO `courses` VALUES ('7', '形势与政策');
INSERT INTO `courses` VALUES ('8', '电子技术基础');
INSERT INTO `courses` VALUES ('9', '大学物理');
INSERT INTO `courses` VALUES ('10', '计算机组成原理');
INSERT INTO `courses` VALUES ('11', '离散数学');
INSERT INTO `courses` VALUES ('12', 'Java程序设计');
INSERT INTO `courses` VALUES ('13', '数据结构');
INSERT INTO `courses` VALUES ('14', '信号与系统');
INSERT INTO `courses` VALUES ('15', '移动终端软件开发技术');
INSERT INTO `courses` VALUES ('16', '中国近代史纲要');
