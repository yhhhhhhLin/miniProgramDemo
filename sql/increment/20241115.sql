alter table tasks
    add tag tinyint null comment '任务标签';

CREATE TABLE task_tag
(
    id           INT AUTO_INCREMENT PRIMARY KEY,                                  -- ID
    user_id      INT NOT NULL,                                                    -- 任务所属用户ID
    tag_name     VARCHAR(255),                                                    -- 标签名称
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                             -- 创建时间
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 更新时间
    is_delete    BOOLEAN   DEFAULT 0                                              -- 逻辑删除字段
);

drop table task_tag;