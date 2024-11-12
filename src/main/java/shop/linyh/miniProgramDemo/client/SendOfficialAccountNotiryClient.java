package shop.linyh.miniProgramDemo.client;

import org.springframework.stereotype.Component;
import shop.linyh.miniProgramDemo.entity.Notifications;
import shop.linyh.miniProgramDemo.entity.Tasks;

/**
 * @author linzz
 */
@Component
public class SendOfficialAccountNotiryClient implements SendNotifyClient {


    /**
     * 发送公众号提醒
     *
     * @param task
     * @param notifications
     * @return
     */
    @Override
    public boolean sendMessage(Tasks task, Notifications notifications) {
        return false;
    }

}
