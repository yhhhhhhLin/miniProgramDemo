package shop.linyh.miniProgramDemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName tasks
 */
@TableName(value = "tasks")
@Data
public class Tasks implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private String taskContent;

    /**
     *
     */
    private Date taskTime;

    /**
     *
     */
    private Long userId;

    /**
     * 任务状态 0未完成 1完成 2超时 -->enum
     */
    private Integer taskStatus;

    /**
     *
     */
    private Boolean needNotify;

    /**
     *
     */
    private Date createdTime;

    /**
     *
     */
    private Date updatedTime;

    /**
     *
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}