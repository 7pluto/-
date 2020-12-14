/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : studentmanage

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2020-11-08 22:34:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` int(6) NOT NULL,
  `teacher` varchar(10) COLLATE utf8_bin NOT NULL,
  `credit` double(10,0) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `courses_course` (`name`),
  CONSTRAINT `courses_course` FOREIGN KEY (`name`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '1', '王深感', '5');
INSERT INTO `course` VALUES ('2', '2', '蔡壮保', '3');
INSERT INTO `course` VALUES ('3', '3', '易江维', '3');
INSERT INTO `course` VALUES ('4', '4', '邱栾树', '1');
INSERT INTO `course` VALUES ('5', '5', '严席华', '1');
INSERT INTO `course` VALUES ('6', '6', '陆示笙', '2');
INSERT INTO `course` VALUES ('7', '7', '王成文', '4');
INSERT INTO `course` VALUES ('8', '8', '王昇侨', '3');
INSERT INTO `course` VALUES ('9', '9', '刘慕骏', '4');
INSERT INTO `course` VALUES ('10', '10', '刘德耿', '3');
INSERT INTO `course` VALUES ('11', '11', '郭炜俏', '3');
INSERT INTO `course` VALUES ('12', '12', '王吉蓉', '4');
INSERT INTO `course` VALUES ('13', '13', '齐量珍\r\n', '3');
INSERT INTO `course` VALUES ('14', '14', '华璐玉', '3');
INSERT INTO `course` VALUES ('15', '15', '潘忻妍', '3');
INSERT INTO `course` VALUES ('16', '16', '倪暮琴', '2');
