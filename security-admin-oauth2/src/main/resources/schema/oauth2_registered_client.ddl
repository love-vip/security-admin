SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_registered_client`;
CREATE TABLE `oauth2_registered_client`
(
    `id`                            varchar(100)  NOT NULL,
    `client_id`                     varchar(100)  NOT NULL,
    `client_id_issued_at`           timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `client_secret`                 varchar(200)           DEFAULT NULL,
    `client_secret_expires_at`      timestamp NULL DEFAULT NULL,
    `client_name`                   varchar(200)  NOT NULL,
    `client_authentication_methods` varchar(1000) NOT NULL,
    `authorization_grant_types`     varchar(1000) NOT NULL,
    `redirect_uris`                 varchar(1000)          DEFAULT NULL,
    `scopes`                        varchar(1000) NOT NULL,
    `client_settings`               varchar(2000) NOT NULL,
    `token_settings`                varchar(2000) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET
FOREIGN_KEY_CHECKS = 1;
