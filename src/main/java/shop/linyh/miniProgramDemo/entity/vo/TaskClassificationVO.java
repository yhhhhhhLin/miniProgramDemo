package shop.linyh.miniProgramDemo.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author linzz
 */
@Data
public class TaskClassificationVO {

    /**
     * 超时的任务
     */
    private List<TaskVO> expiredTasks;

    /**
     * 完成的任务
     */
    private List<TaskVO> completedTasks;

    /**
     * 未完成的任务
     */
    private List<TaskVO> incompleteTasks;
}
