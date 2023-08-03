/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.19.150
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 192.168.19.150:3306
 Source Schema         : sc_offline_v1

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 27/12/2019 15:42:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_authority_role
-- ----------------------------
DROP TABLE IF EXISTS `s_authority_role`;
CREATE TABLE `s_authority_role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自定义角色ID',
  `MODEL_ID` bigint(20) NOT NULL COMMENT '模块类型id',
  `ROLE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `ROLE_DEC` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `IS_ADMIN` tinyint(3) NULL DEFAULT 0 COMMENT '1：机构下的管理员角色，0普通角色',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `IDX_ROLE_NAME`(`ROLE_NAME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_authority_role
-- ----------------------------
INSERT INTO `s_authority_role` VALUES (1, 1, '前台角色', '无', 1);
INSERT INTO `s_authority_role` VALUES (2, 2, '后台角色', '无', 1);
INSERT INTO `s_authority_role` VALUES (3, 2, '信息化小组后台', '信息化小组后台', 0);
INSERT INTO `s_authority_role` VALUES (4, 1, '信息化小组前台', '信息化小组前台', 0);
INSERT INTO `s_authority_role` VALUES (5, 2, '市场部后台', '市场部后台', 0);
INSERT INTO `s_authority_role` VALUES (6, 2, '游客部', '', 0);
INSERT INTO `s_authority_role` VALUES (7, 1, '票房售票前台', '', 0);
INSERT INTO `s_authority_role` VALUES (8, 1, '票房领班前台', '', 0);
INSERT INTO `s_authority_role` VALUES (9, 2, '票房后台', '', 0);
INSERT INTO `s_authority_role` VALUES (10, 2, '测试_后台全权限', '', 0);
INSERT INTO `s_authority_role` VALUES (12, 1, '测试_前台全权限', '', 0);

-- ----------------------------
-- Table structure for s_authority_user
-- ----------------------------
DROP TABLE IF EXISTS `s_authority_user`;
CREATE TABLE `s_authority_user`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自定义用户ID',
  `MODEL_ID` bigint(20) NOT NULL COMMENT '模块类型ID',
  `USER_ACCOUNT` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户帐号（登录用）',
  `USER_PASSWORD` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `USER_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `repository_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '仓库类型1总仓2个人仓',
  `PHONE` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `EMAIL` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `CREATE_TIME` timestamp(0) NOT NULL COMMENT '用户创建时间',
  `CREATOR_USER_ACCOUNT` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `STATUS` tinyint(3) NOT NULL COMMENT '状态（0无效1有效）',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `USER_ACCOUNT`(`USER_ACCOUNT`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_authority_user
-- ----------------------------
INSERT INTO `s_authority_user` VALUES (1, 2, 'admin', '96e79218965eb72c92a549dd5a330112', '系统管理员', 0, '13800138000', '123156456@qq.com', '2014-12-03 19:53:09', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (2, 2, '02961', '96e79218965eb72c92a549dd5a330112', '张俊影', 0, '13800138000', 'zhangjunying@test.com', '2017-09-07 18:05:40', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (3, 2, '03147', 'e10adc3949ba59abbe56e057f20f883e', '张敏敏', 0, '13800138000', 'zhangminmin@test.com', '2017-09-07 18:06:43', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (4, 2, '03119', 'e10adc3949ba59abbe56e057f20f883e', '贺云芳', 0, '13800138000', 'heyunfang@test.com', '2017-09-12 10:27:55', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (5, 2, '00076', 'e10adc3949ba59abbe56e057f20f883e', '黄玲珍', 0, '13800138000', 'huanglingzhen@test.com', '2017-09-12 10:30:36', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (6, 2, '00324', 'e10adc3949ba59abbe56e057f20f883e', '何岚', 0, '13800138000', '', '2017-09-12 10:31:26', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (7, 2, '00321', 'e10adc3949ba59abbe56e057f20f883e', '尹莉', 0, '13800138000', '', '2017-09-12 10:32:03', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (8, 2, '01548', 'e10adc3949ba59abbe56e057f20f883e', '黄映珊', 0, '13800138000', '', '2017-09-12 10:32:48', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (9, 2, '02588', 'e10adc3949ba59abbe56e057f20f883e', '宋小鸽', 0, '13800138000', '', '2017-09-12 10:33:55', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (10, 2, '04011', 'e10adc3949ba59abbe56e057f20f883e', '郭安燕', 0, '13800138000', '', '2017-09-12 10:35:01', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (11, 2, '03801', 'e10adc3949ba59abbe56e057f20f883e', '林康丽', 0, '13800138000', '', '2017-09-12 10:36:00', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (12, 2, '00948', 'e10adc3949ba59abbe56e057f20f883e', '罗小红', 0, '13800138000', '', '2017-09-12 10:44:56', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (13, 2, '04328', 'e10adc3949ba59abbe56e057f20f883e', '雷婷', 0, '13800138000', '', '2017-09-12 10:45:35', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (14, 2, '04616', 'e10adc3949ba59abbe56e057f20f883e', '黄虹', 0, '13800138000', '', '2017-09-12 10:46:20', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (15, 2, '04716', 'e10adc3949ba59abbe56e057f20f883e', '白文静', 0, '13800138000', '', '2017-09-12 10:47:01', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (16, 2, '04906', 'e10adc3949ba59abbe56e057f20f883e', '黄庆玲', 0, '13800138000', '', '2017-09-12 10:47:39', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (17, 2, '04905', 'e10adc3949ba59abbe56e057f20f883e', '龚亚萍', 0, '13800138000', '', '2017-09-12 10:48:19', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (18, 2, '00581', 'e10adc3949ba59abbe56e057f20f883e', '杨玲', 0, '13800138000', '', '2017-09-12 10:48:56', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (19, 2, '03923', 'e10adc3949ba59abbe56e057f20f883e', '吴巧媚', 0, '13800138000', 'wuqiaomei@test.com', '2017-10-18 16:25:49', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (20, 2, '00031', 'e10adc3949ba59abbe56e057f20f883e', '温静兰', 0, '13800138000', 'wenjinglan@test.com', '2017-10-18 16:28:09', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (21, 2, '00763', 'e10adc3949ba59abbe56e057f20f883e', '闸口查询', 0, '13800138000', 'zhoujiajia@test.com', '2017-11-09 14:34:21', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (22, 2, '00242', '96e79218965eb72c92a549dd5a330112', '翦俊', 0, '13800138000', '', '2018-01-19 15:53:59', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (23, 2, '02697', 'e10adc3949ba59abbe56e057f20f883e', '彭钧平', 0, '13800138000', '', '2018-02-01 17:06:51', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (24, 2, '00077', 'e10adc3949ba59abbe56e057f20f883e', '谭晓文', 0, '13800138000', '', '2018-02-01 17:07:46', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (25, 2, '02623', 'e10adc3949ba59abbe56e057f20f883e', '叶晓瑜', 0, '13800138000', '', '2018-02-01 17:09:05', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (30, 2, 'test01', '96e79218965eb72c92a549dd5a330112', '测试_操作员', 0, '13800138000', '', '2018-03-15 16:58:26', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (31, 2, 'mxq', '96e79218965eb72c92a549dd5a330112', 'mxq', 0, '13800138000', '', '2018-03-17 17:21:58', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (32, 2, '001', '96e79218965eb72c92a549dd5a330112', '001', 0, '13800138000', '', '2018-03-19 16:55:01', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (33, 2, 'test1', '96e79218965eb72c92a549dd5a330112', 'test1', 0, '13800138000', '', '2018-03-20 16:32:07', 'admin', 1);
INSERT INTO `s_authority_user` VALUES (34, 2, 'fuqiang', '96e79218965eb72c92a549dd5a330112', '测试傅强', 0, '13800138000', '', '2018-03-21 13:34:13', 'admin', 1);

-- ----------------------------
-- Table structure for s_authority_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_authority_user_role`;
CREATE TABLE `s_authority_user_role`  (
  `USER_ID` bigint(20) NOT NULL COMMENT '自定义用户ID',
  `ROLE_ID` bigint(32) NOT NULL COMMENT '自定义角色ID',
  PRIMARY KEY (`USER_ID`, `ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_authority_user_role
-- ----------------------------
INSERT INTO `s_authority_user_role` VALUES (1, 1);
INSERT INTO `s_authority_user_role` VALUES (1, 2);
INSERT INTO `s_authority_user_role` VALUES (2, 7);
INSERT INTO `s_authority_user_role` VALUES (2, 10);
INSERT INTO `s_authority_user_role` VALUES (3, 3);
INSERT INTO `s_authority_user_role` VALUES (3, 4);
INSERT INTO `s_authority_user_role` VALUES (5, 3);
INSERT INTO `s_authority_user_role` VALUES (5, 8);
INSERT INTO `s_authority_user_role` VALUES (6, 8);
INSERT INTO `s_authority_user_role` VALUES (6, 9);
INSERT INTO `s_authority_user_role` VALUES (7, 8);
INSERT INTO `s_authority_user_role` VALUES (7, 9);
INSERT INTO `s_authority_user_role` VALUES (8, 7);
INSERT INTO `s_authority_user_role` VALUES (9, 7);
INSERT INTO `s_authority_user_role` VALUES (10, 7);
INSERT INTO `s_authority_user_role` VALUES (11, 7);
INSERT INTO `s_authority_user_role` VALUES (12, 7);
INSERT INTO `s_authority_user_role` VALUES (13, 7);
INSERT INTO `s_authority_user_role` VALUES (14, 7);
INSERT INTO `s_authority_user_role` VALUES (15, 7);
INSERT INTO `s_authority_user_role` VALUES (16, 7);
INSERT INTO `s_authority_user_role` VALUES (17, 7);
INSERT INTO `s_authority_user_role` VALUES (18, 8);
INSERT INTO `s_authority_user_role` VALUES (18, 9);
INSERT INTO `s_authority_user_role` VALUES (19, 5);
INSERT INTO `s_authority_user_role` VALUES (20, 5);
INSERT INTO `s_authority_user_role` VALUES (21, 6);
INSERT INTO `s_authority_user_role` VALUES (22, 5);
INSERT INTO `s_authority_user_role` VALUES (23, 8);
INSERT INTO `s_authority_user_role` VALUES (23, 9);
INSERT INTO `s_authority_user_role` VALUES (24, 8);
INSERT INTO `s_authority_user_role` VALUES (24, 9);
INSERT INTO `s_authority_user_role` VALUES (25, 8);
INSERT INTO `s_authority_user_role` VALUES (25, 9);
INSERT INTO `s_authority_user_role` VALUES (30, 10);
INSERT INTO `s_authority_user_role` VALUES (30, 12);
INSERT INTO `s_authority_user_role` VALUES (31, 10);
INSERT INTO `s_authority_user_role` VALUES (31, 12);
INSERT INTO `s_authority_user_role` VALUES (32, 10);
INSERT INTO `s_authority_user_role` VALUES (32, 12);
INSERT INTO `s_authority_user_role` VALUES (33, 12);
INSERT INTO `s_authority_user_role` VALUES (34, 10);
INSERT INTO `s_authority_user_role` VALUES (34, 12);

SET FOREIGN_KEY_CHECKS = 1;
