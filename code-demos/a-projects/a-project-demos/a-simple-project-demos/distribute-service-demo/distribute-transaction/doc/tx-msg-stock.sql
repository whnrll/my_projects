/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.66.100
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : 192.168.66.100:3306
 Source Schema         : tx-msg-stock

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 25/05/2022 09:58:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `total_count` int(11) NULL DEFAULT NULL COMMENT '商品总库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES (1, 1001, 80);

-- ----------------------------
-- Table structure for tx_log
-- ----------------------------
DROP TABLE IF EXISTS `tx_log`;
CREATE TABLE `tx_log`  (
  `tx_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分布式事务全局序列号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`tx_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tx_log
-- ----------------------------
INSERT INTO `tx_log` VALUES ('557b5ab0f1914315bf25e779faa34f8f', '2022-05-21 17:30:16');
INSERT INTO `tx_log` VALUES ('fa5537897fda42469799d898831b9170', '2022-05-21 17:31:06');

SET FOREIGN_KEY_CHECKS = 1;
