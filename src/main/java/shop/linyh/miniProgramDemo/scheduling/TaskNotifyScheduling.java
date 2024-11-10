package shop.linyh.miniProgramDemo.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import shop.linyh.miniProgramDemo.entity.Tasks;
import shop.linyh.miniProgramDemo.service.TasksService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author linzz
 * <p>
 * 检查当天事件是否到达时间
 */
@Component
@EnableScheduling
@Slf4j
public class TaskNotifyScheduling {

    @Autowired
    private TasksService tasksService;


    /**
     * 检查是否到达时间
     */
    @Scheduled(cron = "0 * * * * ?")
    public void checkAndSendNotifications() {
//        1. 获取当天所有没完成的任务
        String nowDate = "2024-10-9";
        List<Tasks> tasksList = tasksService.getUnFinishTask(nowDate);
//        2. 获取当前的时间

//        3. 判断任务时间是否到期

//        4. 按照消息类型获取对应client发送消息
        log.info("执行一次查看...{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
    }


    /**
     * 发送通知
     *
     * @param task
     */
    @Async("taskSendNotifyExecutor")
    public void sendNotification(Tasks task) {
//        TODO 根根据任务通知类型发送对应的通知
    }
}
