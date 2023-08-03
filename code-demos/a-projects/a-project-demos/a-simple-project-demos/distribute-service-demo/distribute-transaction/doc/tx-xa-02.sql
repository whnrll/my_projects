/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.66.100
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : 192.168.66.100:3306
 Source Schema         : tx-xa-02

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 25/05/2022 09:57:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account`  (
  `account_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账户编号',
  `account_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账户名称',
  `account_balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户余额',
  PRIMARY KEY (`account_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES ('1002', '李四', 1000.00);

SET FOREIGN_KEY_CHECKS = 1;
