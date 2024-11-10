CREATE TABLE user
(
    id                    INT AUTO_INCREMENT PRIMARY KEY,                                  -- 用户ID
    openid                VARCHAR(64)  NOT NULL UNIQUE,                                    -- 微信OpenID，唯一
    session_key           VARCHAR(128) NOT NULL,                                           -- 会话密钥
    username              VARCHAR(50),                                                     -- 用户名
    avatar_url            VARCHAR(255),                                                    -- 头像URL
    gender                TINYINT,                                                         -- 性别（0：未知，1：男性，2：女性）
    phone                 varchar(255),                                                    -- 手机号码
    create_time           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                             -- 创建时间
    update_time           TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新时间
    is_bind_other_account TINYINT   default 0,-- 是否绑定第三方账号密码
    is_delete             TINYINT   default 0                                              -- 是否删除 0为否 1为是
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE tasks
(
    id           INT AUTO_INCREMENT PRIMARY KEY,                                              -- 任务ID，自增主键
    task_content VARCHAR(255) NOT NULL,                                                       -- 任务内容
    task_time_date    DATE     NOT NULL,                                                       -- 任务的具体时间，精确到小时、分钟、秒
    task_time_time    TIME     NOT NULL,                                                       -- 任务的具体时间，精确到小时、分钟、秒
    user_id      INT          NOT NULL,                                                       -- 任务所属用户ID
    finish_time  TIMESTAMP,                                                                   -- 完成时间
    task_status  BOOLEAN      NOT NULL DEFAULT 0,                                             -- 任务状态 0未完成 1完成 2超时
    need_notify  BOOLEAN      NOT NULL DEFAULT 0,                                             -- 是否需要提醒
    created_time TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,                             -- 创建时间，记录任务添加时间
    updated_time TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新时间
    is_delete    BOOLEAN               DEFAULT 0                                              -- 逻辑删除字段
);


CREATE TABLE notifications
(
    id             INT AUTO_INCREMENT PRIMARY KEY,                                  -- 通知记录ID
    task_id        INT      NOT NULL,                                               -- 关联任务ID
    notify_method  ENUM('email', 'wechat', 'sms') NOT NULL,                         -- 通知方式（邮箱、公众号、短信等）
    notify_time    DATETIME NOT NULL,                                               -- 通知时间
    notify_status  ENUM('pending', 'sent', 'failed') NOT NULL,                      -- 通知状态（待发送、已发送、发送失败）
    failure_reason VARCHAR(255),                                                    -- 发送失败原因（如果有）
    created_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                             -- 记录创建时间
    updated_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新时间
    is_delete      BOOLEAN   DEFAULT 0                                              -- 逻辑删除字段
);
