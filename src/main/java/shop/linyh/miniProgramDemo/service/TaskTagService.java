package shop.linyh.miniProgramDemo.service;

import shop.linyh.miniProgramDemo.entity.TaskTag;
import com.baomidou.mybatisplus.extension.service.IService;
import shop.linyh.miniProgramDemo.entity.dto.UpdateTaskTagDto;

/**
* @author linzz
* @description 针对表【task_tag】的数据库操作Service
* @createDate 2024-11-15 23:07:47
*/
public interface TaskTagService extends IService<TaskTag> {

    Boolean addTag(String tagName);

    Boolean updateTag(UpdateTaskTagDto dto);

    Boolean delTag(Long tagId);
}
