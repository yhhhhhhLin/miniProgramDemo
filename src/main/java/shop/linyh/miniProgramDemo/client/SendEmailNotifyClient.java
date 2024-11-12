package shop.linyh.miniProgramDemo.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import shop.linyh.miniProgramDemo.common.enums.NotifyStatusEnum;
import shop.linyh.miniProgramDemo.entity.Notifications;
import shop.linyh.miniProgramDemo.entity.Tasks;
import shop.linyh.miniProgramDemo.entity.User;
import shop.linyh.miniProgramDemo.service.NotificationsService;
import shop.linyh.miniProgramDemo.service.UserService;

import javax.mail.MessagingException;
import java.util.Date;

/**
 * @author linzz
 */
@Slf4j
@Component
public class SendEmailNotifyClient implements SendNotifyClient {

    @Value("${spring.mail.username}")
    private String sendMailer;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationsService notificationsService;

    /**
     * 邮箱提醒
     *
     * @param task
     * @param notifications
     * @return
     */
    @Override
    public boolean sendMessage(Tasks task, Notifications notifications) {

        Long userId = task.getUserId();
        User user = userService.getById(userId);
        String email = user.getEmail();

        String emailTextTemplate = "您创建的任务：%，已经到达时间，请记得完成";
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            mimeMessageHelper.setFrom(sendMailer);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("任务提醒");
            mimeMessageHelper.setText(String.format(emailTextTemplate, task.getTaskContent()));
            mimeMessageHelper.setSentDate(new Date());

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            log.info("任务 {}, 通过email通知成功", task.getId());

            Boolean result = notificationsService.updateNotifyStatus(notifications.getId(), NotifyStatusEnum.NOTIFY_SUCCESS.getCode());
            return true;

        } catch (MessagingException e) {
            log.error("任务通知 {} 发送失败", task.getId(), e);
            return false;
        }
    }
}
