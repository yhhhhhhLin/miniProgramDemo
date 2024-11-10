package shop.linyh.miniProgramDemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Calendar;
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
     * 任务时间（2024-11-10）
     */
    private Date taskTimeDate;

    /**
     * 任务时间（17:20)
     */
    private Date taskTimeTime;

    /**
     * 完成时间
     */
    private Date finishTime;

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

    /**
     * 获取完整的时间
     * @return
     */
    public Date getFullTaskTime(){
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(taskTimeDate);

        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(taskTimeTime);

        // 设置时间
        calendarDate.set(Calendar.HOUR_OF_DAY, calendarTime.get(Calendar.HOUR_OF_DAY));
        calendarDate.set(Calendar.MINUTE, calendarTime.get(Calendar.MINUTE));
        calendarDate.set(Calendar.SECOND, calendarTime.get(Calendar.SECOND));

        return calendarDate.getTime();
    }
}