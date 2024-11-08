package shop.linyh.miniProgramDemo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author linzz
 */
@Component
public class SendEmailNotifyClient implements SendNotifyClient {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送邮箱提醒
     *
     * @param to
     * @param subject
     * @param message
     * @return
     */
    @Override
    public boolean sendMessage(String to, String subject, String message) {

        return false;
    }
}
