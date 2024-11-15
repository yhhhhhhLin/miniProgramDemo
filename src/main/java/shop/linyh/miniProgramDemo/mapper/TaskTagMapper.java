package shop.linyh.miniProgramDemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.linyh.miniProgramDemo.entity.TaskTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author linzz
* @description 针对表【task_tag】的数据库操作Mapper
* @createDate 2024-11-15 23:07:47
* @Entity shop.linyh.miniProgramDemo.entity.TaskTag
*/
@Mapper
public interface TaskTagMapper extends BaseMapper<TaskTag> {

}




