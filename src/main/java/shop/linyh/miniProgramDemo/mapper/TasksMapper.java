package shop.linyh.miniProgramDemo.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.linyh.miniProgramDemo.entity.Tasks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import shop.linyh.miniProgramDemo.entity.vo.DayAndCountVO;

import java.util.List;
import java.util.Map;

/**
 * @author linzz
 * @description 针对表【tasks】的数据库操作Mapper
 * @createDate 2024-11-05 16:38:39
 * @Entity shop.linyh.miniProgramDemo.entity.Tasks
 */
@Mapper
public interface TasksMapper extends BaseMapper<Tasks> {

    List<DayAndCountVO> calendars(@Param("startDate") String startDate,
                                  @Param("endDate") String date1);
}




