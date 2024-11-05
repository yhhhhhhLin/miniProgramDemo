package shop.linyh.miniProgramDemo.common;

/**
 * @author linzz
 */
public class UserOpenIdContext {

    private static ThreadLocal<String> userOpenIdThreadLocal = new ThreadLocal<>();

    public static void setOpenId(String openId) {
        userOpenIdThreadLocal.set(openId);
    }

    public static String getOpenId() {
        return userOpenIdThreadLocal.get();
    }

    public static void clear() {
        userOpenIdThreadLocal.remove();
    }
}
