package shop.linyh.miniProgramDemo.client;

import shop.linyh.miniProgramDemo.entity.Notifications;
import shop.linyh.miniProgramDemo.entity.Tasks;

public interface SendNotifyClient {

    boolean sendMessage(Tasks task, Notifications notifications);
}
