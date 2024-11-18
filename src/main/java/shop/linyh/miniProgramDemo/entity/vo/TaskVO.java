package shop.linyh.miniProgramDemo.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author linzz
 */
@Data
public class TaskVO {
    private Long taskId;

    private String taskContent;

    /**
     * fullTaskTime
     */
    private Date taskTime;

    private Date finishTime;

    private Integer taskStatus;

    private Boolean needNotify;

    private Long tagId;

    private String tagName;
}
