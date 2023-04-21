SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user`;
CREATE TABLE `rbac_user`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT,
    `username`           varchar(32)  NOT NULL COMMENT '用户名',
    `password`           longtext     NOT NULL COMMENT '密码，加密存储',
    `realname`           varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '真实姓名',
    `job_num`            varchar(255) NOT NULL COMMENT '工号',
    `mobile`             varchar(16)                       DEFAULT NULL COMMENT '手机号',
    `email`              varchar(32)                       DEFAULT NULL COMMENT '邮箱',
    `enabled`            tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（0: 禁用1:启用）',
    `account_non_locked` tinyint(1) DEFAULT '1' COMMENT '是否还未锁定(0:否 1是)',
    `login_error_times`  tinyint(2) NOT NULL DEFAULT '0' COMMENT '密码输入错误次数',
    `verify_error_times` tinyint(2) NOT NULL DEFAULT '0' COMMENT '验证码输入错误次数',
    `secret`             varchar(255)                      DEFAULT NULL COMMENT '谷歌校验器密钥',
    `initial`            tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否初始用户',
    `bind`               tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否绑定谷歌校验',
    `creator`            varchar(255)                      DEFAULT NULL COMMENT '创建人',
    `create_time`        datetime     NOT NULL COMMENT '创建时间',
    `last_operator`      varchar(255)                      DEFAULT NULL COMMENT '最近操作人',
    `update_time`        datetime                          DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

SET
FOREIGN_KEY_CHECKS = 1;