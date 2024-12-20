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
     * 通知方式（0邮箱、1公众号、2短信等等）
     */
    private Integer notifyMethod;

    /**
     * 
     */
    private Date notifyTime;

    /**
     * 0未通知 1通知成功 2通知失败
     */
    private Integer notifyStatus;

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