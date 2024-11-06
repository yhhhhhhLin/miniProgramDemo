package shop.linyh.miniProgramDemo.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author linzz
 */
@Data
public class TaskVO {
    private Long id;

    private String taskContent;

    private Date taskTime;

    private Integer taskStatus;

    private Boolean needNotify;
}
