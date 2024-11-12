package shop.linyh.miniProgramDemo.common.enums;

import lombok.Getter;

/**
 * @author linzz
 */

@Getter
public enum NotifyStatusEnum {

    NOT_NOTIFIED(0, "未通知"),
    NOTIFY_SUCCESS(1, "通知成功"),
    NOTIFY_FAILED(2, "通知失败");

    private final int code;
    private final String description;

    NotifyStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static NotifyStatusEnum fromCode(int code) {
        for (NotifyStatusEnum status : NotifyStatusEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的通知状态 code: " + code);
    }
}
