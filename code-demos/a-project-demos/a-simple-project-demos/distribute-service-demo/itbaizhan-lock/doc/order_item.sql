

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单ID',
  `produce_id` int(11) NULL DEFAULT NULL COMMENT '商品ID',
  `purchase_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '购买价格',
  `purchase_num` int(11) NULL DEFAULT NULL COMMENT '购买数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('1529685823796764675', '1529685823796764674', 1001, 100.00, 1);
INSERT INTO `order_item` VALUES ('1529686091737296898', '1529686091737296897', 1001, 100.00, 1);
INSERT INTO `order_item` VALUES ('1529686091808600067', '1529686091808600066', 1001, 100.00, 1);
INSERT INTO `order_item` VALUES ('1529686091875708931', '1529686091875708930', 1001, 100.00, 1);
INSERT INTO `order_item` VALUES ('1529686092181880835', '1529686092181880834', 1001, 100.00, 1);
INSERT INTO `order_item` VALUES ('1529686092202852354', '1529686092202852353', 1001, 100.00, 1);

SET FOREIGN_KEY_CHECKS = 1;
