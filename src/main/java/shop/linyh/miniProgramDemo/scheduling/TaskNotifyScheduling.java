package shop.linyh.miniProgramDemo.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import shop.linyh.miniProgramDemo.client.SendNotifyClient;
import shop.linyh.miniProgramDemo.entity.Notifications;
import shop.linyh.miniProgramDemo.entity.Tasks;
import shop.linyh.miniProgramDemo.factory.SendNotifyClientFactory;
import shop.linyh.miniProgramDemo.service.NotificationsService;
import shop.linyh.miniProgramDemo.service.TasksService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author linzz
 */
@Component
@EnableScheduling
@Slf4j
public class TaskNotifyScheduling {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final ReentrantLock lock = new ReentrantLock();

    @Autowired
    private TasksService tasksService;

    @Autowired
    private NotificationsService notificationsService;

    /**
     * 定时任务：每分钟检查任务是否过期并发送通知
     */
//    @Scheduled(cron = "0 * * * * ?")
    public void checkAndSendNotifications() {
        if (lock.tryLock()) {
            try {
                LocalDateTime currentTime = LocalDateTime.now();
                String todayDate = DATE_FORMAT.format(currentTime);

                List<Tasks> tasksList = tasksService.getUnFinishTask(todayDate);
                tasksList.sort(Comparator.comparing(Tasks::getFullTaskTime));

                log.info("执行任务检查，当前时间：{}", DATE_TIME_FORMAT.format(currentTime));

                List<Tasks> expireTasks = getExpireTasks(tasksList, currentTime);

                if (!expireTasks.isEmpty()) {
                    if (tasksService.batchFinishTask(expireTasks)) {
                        log.info("批量更新完成，共更新 {} 个过期任务", expireTasks.size());
                        judgingAndSendTasks(expireTasks);
                    } else {
                        log.warn("批量更新任务失败");
                    }
                } else {
                    log.info("无过期任务需要处理");
                }
            } finally {
                lock.unlock();
            }
        } else {
            log.info("任务检查被跳过，上次检查尚未完成");
        }
    }

    /**
     * 获取已过期的任务列表
     */
    private List<Tasks> getExpireTasks(List<Tasks> tasksList, LocalDateTime currentTime) {
        List<Tasks> expireTasks = new ArrayList<>();
        tasksList.forEach(task -> {
            if (task.getFullTaskTime().toInstant().isBefore(currentTime.atZone(java.time.ZoneId.systemDefault()).toInstant())) {
                expireTasks.add(task);
                log.info("任务 {} 已过期，执行通知", task.getId());
            } else {
                log.info("任务 {} 尚未到期", task.getId());
            }
        });
        return expireTasks;
    }

    /**
     * 筛选需要通知的任务并发送通知
     */
    private void judgingAndSendTasks(List<Tasks> expireTasks) {
        expireTasks.stream()
                .filter(Tasks::getNeedNotify)
                .forEach(this::sendNotification);
    }

    /**
     * 异步发送通知，根据通知方式选择相应的通知客户端
     */
    @Async("taskSendNotifyExecutor")
    public void sendNotification(Tasks task) {
        try {
            Notifications notifications = notificationsService.getByTaskId(task.getId());
            SendNotifyClient client = SendNotifyClientFactory.getClient(notifications.getNotifyMethod());
            boolean sendResult = client.sendMessage(task, notifications);
        } catch (Exception e) {
            log.error("任务 {} 的通知发送失败，异常信息：{}", task.getId(), e.getMessage());
        }
    }
}
