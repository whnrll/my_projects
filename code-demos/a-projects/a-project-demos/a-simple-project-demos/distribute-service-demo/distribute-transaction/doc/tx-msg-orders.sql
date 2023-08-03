/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.66.100
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : 192.168.66.100:3306
 Source Schema         : tx-msg-orders

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 25/05/2022 09:58:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单编号',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `pay_count` int(11) NULL DEFAULT NULL COMMENT '购买数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1527944482251288578, '2022-05-21 17:28:47', '3f3884df818b4fb19deeb17a12f48cd4', 1001, 10);
INSERT INTO `orders` VALUES (1527945065800609793, '2022-05-21 17:31:06', '365175ca1aae43a2bab4897d120241bd', 1001, 10);

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
INSERT INTO `tx_log` VALUES ('557b5ab0f1914315bf25e779faa34f8f', '2022-05-21 17:28:48');
INSERT INTO `tx_log` VALUES ('fa5537897fda42469799d898831b9170', '2022-05-21 17:31:06');

SET FOREIGN_KEY_CHECKS = 1;
