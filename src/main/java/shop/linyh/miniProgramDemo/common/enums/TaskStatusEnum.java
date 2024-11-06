package shop.linyh.miniProgramDemo.common.enums;

import lombok.Getter;

/**
 * @author linzz
 */

@Getter
public enum TaskStatusEnum {

    INCOMPLETE(0, "未完成"),
    COMPLETE(1, "完成"),
    EXPIRED(2, "超时");

    private final int status;
    private final String desc;

    TaskStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}
