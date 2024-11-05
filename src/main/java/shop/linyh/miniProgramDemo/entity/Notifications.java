package shop.linyh.miniProgramDemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName notifications
 */
@TableName(value ="notifications")
@Data
public class Notifications implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer taskId;

    /**
     * 
     */
    private Object notifyMethod;

    /**
     * 
     */
    private Date notifyTime;

    /**
     * 
     */
    private Object notifyStatus;

    /**
     * 
     */
    private String failureReason;

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