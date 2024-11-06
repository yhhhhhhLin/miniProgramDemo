package shop.linyh.miniProgramDemo.entity.dto;

import lombok.Data;

/**
 * @author linzz
 */
@Data
public class QueryTaskDTO {
    private Integer year;

    /**
     * 月 1-12
     */
    private Integer month;

    private Integer day;
}
