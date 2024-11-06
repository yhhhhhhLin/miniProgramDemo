package shop.linyh.miniProgramDemo.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AddTaskDTO {
    private String taskContent;

    private Date taskTime;

    private Boolean needNotify = false;
}
