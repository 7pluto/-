/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : studentmanage

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2020-11-08 22:34:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classed
-- ----------------------------
DROP TABLE IF EXISTS `classed`;
CREATE TABLE `classed` (
  `id` int(6) NOT NULL,
  `name` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of classed
-- ----------------------------
INSERT INTO `classed` VALUES ('1', '180800');
INSERT INTO `classed` VALUES ('2', '180801');
INSERT INTO `classed` VALUES ('3', '180802');
INSERT INTO `classed` VALUES ('4', '180803');
INSERT INTO `classed` VALUES ('5', '180804');
INSERT INTO `classed` VALUES ('6', '180805');
INSERT INTO `classed` VALUES ('7', '180806');
INSERT INTO `classed` VALUES ('8', '180807');
INSERT INTO `classed` VALUES ('9', '180808');
INSERT INTO `classed` VALUES ('10', '180809');
INSERT INTO `classed` VALUES ('11', '180810');
INSERT INTO `classed` VALUES ('12', '180811');
INSERT INTO `classed` VALUES ('13', '180812');
INSERT INTO `classed` VALUES ('14', '180813');
INSERT INTO `classed` VALUES ('15', '180814');
