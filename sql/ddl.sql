CREATE TABLE user
(
    id                    INT AUTO_INCREMENT PRIMARY KEY,                                  -- 用户ID
    openid                VARCHAR(64)  NOT NULL UNIQUE,                                    -- 微信OpenID，唯一
    session_key           VARCHAR(128) NOT NULL,                                           -- 会话密钥
    username              VARCHAR(50),                                                     -- 用户名
    avatar_url            VARCHAR(255),                                                    -- 头像URL
    gender                TINYINT,                                                         -- 性别（0：未知，1：男性，2：女性）
    phone                 varchar(255),                                                    -- 手机号码
    created_time            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                             -- 创建时间
    updated_time            TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新时间
    is_bind_other_account TINYINT   default 0,-- 是否绑定第三方账号密码
    is_delete             TINYINT   default 0                                           -- 是否删除 0为否 1为是
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
