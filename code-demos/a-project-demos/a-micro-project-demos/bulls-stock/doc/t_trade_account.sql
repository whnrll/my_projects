CREATE TABLE `t_trade_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `userId` bigint(20) NOT NULL,
  `accountNo` varchar(16) NOT NULL COMMENT '交易账号',
  `balance` bigint(21) DEFAULT NULL COMMENT '余额',
  `tradeGroupId` bigint(20) NOT NULL COMMENT '账户组ID',
  `activeTime` datetime DEFAULT NULL COMMENT '开户时间',
  `status` tinyint(3) NOT NULL COMMENT '状态(0:有效， 1：锁定， 2：禁用）',
  `institutionTypeId` varchar(32) DEFAULT NULL COMMENT '机构类型id',
  `institutionId` bigint(19) DEFAULT NULL COMMENT '对应机构类型下的机构id',
  `companyId` bigint(20) DEFAULT NULL COMMENT '公司ID',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名称',
  `tradeGroupName` varchar(32) DEFAULT NULL COMMENT '账户组名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_accountNo` (`accountNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户账号表';

