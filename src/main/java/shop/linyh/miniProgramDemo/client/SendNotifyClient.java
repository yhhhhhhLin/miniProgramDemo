package shop.linyh.miniProgramDemo.client;

public interface SendNotifyClient {

    boolean sendMessage(String to, String subject, String message);
}
