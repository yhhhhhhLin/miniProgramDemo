package shop.linyh.miniProgramDemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.linyh.miniProgramDemo.entity.Tasks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author linzz
* @description 针对表【tasks】的数据库操作Mapper
* @createDate 2024-11-05 16:38:39
* @Entity shop.linyh.miniProgramDemo.entity.Tasks
*/
@Mapper
public interface TasksMapper extends BaseMapper<Tasks> {

}




