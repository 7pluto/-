/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : studentmanage

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2020-11-08 22:33:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `number` varchar(20) COLLATE utf8_bin NOT NULL,
  `name` varchar(10) COLLATE utf8_bin NOT NULL,
  `sex` varchar(2) COLLATE utf8_bin NOT NULL,
  `identity` varchar(18) COLLATE utf8_bin NOT NULL,
  `address` varchar(50) COLLATE utf8_bin NOT NULL,
  `phone` varchar(15) COLLATE utf8_bin NOT NULL,
  `classed` int(6) NOT NULL,
  `grade` int(6) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `geade_user` (`grade`),
  KEY `class_user` (`classed`),
  CONSTRAINT `class_user` FOREIGN KEY (`classed`) REFERENCES `classed` (`id`),
  CONSTRAINT `geade_user` FOREIGN KEY (`grade`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('3118000800', '张韶涵', '女', '440982300009281865', '深圳', '18910451987', '1', '1');
INSERT INTO `student` VALUES ('3118000801', '周杰伦', '男', '440982300203241654', '广州', '18417641234', '5', '1');
INSERT INTO `student` VALUES ('3118000802', '韩红', '女', '440982290009291902', '珠海', '18217681908', '8', '4');
INSERT INTO `student` VALUES ('3118000803', '李大钊', '男', '440982299903211875', '佛山', '19612341235', '4', '3');
INSERT INTO `student` VALUES ('3118000804', '于谦', '男', '440982299903211276', '汕头', '18712426789', '6', '2');
INSERT INTO `student` VALUES ('3118000805', '小丽', '女', 'null', 'null', 'null', '1', '1');
