SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rbac_authority
-- ----------------------------
DROP TABLE IF EXISTS `rbac_authority`;
CREATE TABLE `rbac_authority`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `system`         varchar(255) NOT NULL COMMENT '所属系统',
    `group_`         varchar(255) NOT NULL COMMENT '分组',
    `method`         varchar(255) DEFAULT NULL COMMENT 'http提交方式',
    `authority_abbr` varchar(255) DEFAULT NULL COMMENT '资源简称',
    `authority_name` varchar(255) DEFAULT NULL COMMENT '资源名称',
    `authority_url`  varchar(255) DEFAULT NULL COMMENT '资源路径',
    `creator`        varchar(255) DEFAULT NULL COMMENT '创建人',
    `create_time`    datetime     NOT NULL,
    `last_operator`  varchar(255) DEFAULT NULL COMMENT '最近操作人',
    `update_time`    datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='资源表';

SET
FOREIGN_KEY_CHECKS = 1;
