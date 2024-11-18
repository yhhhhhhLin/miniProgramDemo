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

    /**
     * 标签id，如果是<=0 那么就是查询所有标签的
     */
    private Integer tagId = 0;
}
