CREATE TABLE `t_trade_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `userNo` varchar(16) NOT NULL COMMENT '用户编号',
  `name` varchar(16) DEFAULT NULL COMMENT '用户名称',
  `userPwd` varchar(32) NOT NULL COMMENT '用户密码',
  `phone` varchar(16) DEFAULT NULL COMMENT '电话号码',
  `companyId` bigint(20) DEFAULT NULL COMMENT '公司ID',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `lastLoginIp` varchar(32) DEFAULT NULL COMMENT '最近一次用户登陆IP',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最近一次登陆时间',
  `status` tinyint(3) NOT NULL COMMENT '状态(0:有效， 1：锁定， 2：禁用）',
  `craeteTime` datetime DEFAULT NULL COMMENT '创建时间',
  `idcard` varchar(32) DEFAULT NULL COMMENT '身份证',
  `institutionTypeId` varchar(32) DEFAULT NULL COMMENT '机构类型id',
  `institutionId` bigint(19) DEFAULT NULL COMMENT '对应机构类型下的机构id',
  `companyName` varchar(64) DEFAULT NULL COMMENT '公司名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_userNo` (`userNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';


INSERT INTO `trade_stock`.`t_trade_user`(`id`, `userNo`, `name`, `userPwd`, `phone`, `companyId`, `email`, `address`, `lastLoginIp`, `lastLoginTime`, `status`, `craeteTime`) VALUES (1, 'admin', 'admin', 'ISMvKXpXpadDiUoOSoAfww==', '123', 1, NULL, NULL, NULL, NULL, 0, NULL);
