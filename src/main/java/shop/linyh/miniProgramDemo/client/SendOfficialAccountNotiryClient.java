package shop.linyh.miniProgramDemo.client;

import org.springframework.stereotype.Component;

/**
 * @author linzz
 */
@Component
public class SendOfficialAccountNotiryClient implements SendNotifyClient{

    /**
     * 发送公众号提醒
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
