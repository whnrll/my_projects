/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.66.100
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : 192.168.66.100:3306
 Source Schema         : tx-tcc-bank02

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 25/05/2022 09:57:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cancel_log
-- ----------------------------
DROP TABLE IF EXISTS `cancel_log`;
CREATE TABLE `cancel_log`  (
  `tx_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '全局事务编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`tx_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Cancel阶段执行的日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cancel_log
-- ----------------------------

-- ----------------------------
-- Table structure for confirm_log
-- ----------------------------
DROP TABLE IF EXISTS `confirm_log`;
CREATE TABLE `confirm_log`  (
  `tx_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '全局事务编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`tx_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Confirm阶段执行的日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of confirm_log
-- ----------------------------

-- ----------------------------
-- Table structure for try_log
-- ----------------------------
DROP TABLE IF EXISTS `try_log`;
CREATE TABLE `try_log`  (
  `tx_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '全局事务编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`tx_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Try阶段执行的日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of try_log
-- ----------------------------

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account`  (
  `account_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户编号',
  `account_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户名称',
  `account_balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户余额',
  `transfer_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '转账金额',
  PRIMARY KEY (`account_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账户信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES ('1002', '李四', 10000.00, 0.00);

SET FOREIGN_KEY_CHECKS = 1;
