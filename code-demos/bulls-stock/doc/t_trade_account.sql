CREATE TABLE `t_trade_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '������ʶ',
  `userId` bigint(20) NOT NULL,
  `accountNo` varchar(16) NOT NULL COMMENT '�����˺�',
  `balance` bigint(21) DEFAULT NULL COMMENT '���',
  `tradeGroupId` bigint(20) NOT NULL COMMENT '�˻���ID',
  `activeTime` datetime DEFAULT NULL COMMENT '����ʱ��',
  `status` tinyint(3) NOT NULL COMMENT '״̬(0:��Ч�� 1�������� 2�����ã�',
  `institutionTypeId` varchar(32) DEFAULT NULL COMMENT '��������id',
  `institutionId` bigint(19) DEFAULT NULL COMMENT '��Ӧ���������µĻ���id',
  `companyId` bigint(20) DEFAULT NULL COMMENT '��˾ID',
  `userName` varchar(32) DEFAULT NULL COMMENT '�û�����',
  `tradeGroupName` varchar(32) DEFAULT NULL COMMENT '�˻�������',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_accountNo` (`accountNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='�û��˺ű�';

