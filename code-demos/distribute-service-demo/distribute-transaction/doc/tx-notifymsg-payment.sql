/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.66.100
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : 192.168.66.100:3306
 Source Schema         : tx-notifymsg-payment

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 25/05/2022 09:58:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pay_info
-- ----------------------------
DROP TABLE IF EXISTS `pay_info`;
CREATE TABLE `pay_info`  (
  `tx_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '充值记录流水号',
  `account_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账户',
  `pay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '充值金额',
  `pay_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '充值结果',
  `pay_time` datetime(0) NOT NULL COMMENT '充值时间',
  PRIMARY KEY (`tx_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pay_info
-- ----------------------------
INSERT INTO `pay_info` VALUES ('3e00727d61a742f2bb94ba15f8fff64c', '1001', 100.00, 'success', '2022-05-24 11:14:26');
INSERT INTO `pay_info` VALUES ('875131fd500845ef8701ccd3cddb8c3c', '1001', 100.00, 'success', '2022-05-24 11:21:23');

SET FOREIGN_KEY_CHECKS = 1;
