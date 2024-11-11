package shop.linyh.miniProgramDemo.common.enums;

import lombok.Getter;

/**
 * @author linzz
 */

@Getter
public enum NotificationMethodEnum {
    EMAIL(0, "邮箱"),
    WECHAT(1, "公众号"),
    SMS(2, "短信");

    private final int code;
    private final String description;

    NotificationMethodEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    // 根据code获取枚举实例
    public static NotificationMethodEnum fromCode(int code) {
        for (NotificationMethodEnum method : NotificationMethodEnum.values()) {
            if (method.getCode() == code) {
                return method;
            }
        }
        throw new IllegalArgumentException("Invalid code for NotificationMethod: " + code);
    }
}
