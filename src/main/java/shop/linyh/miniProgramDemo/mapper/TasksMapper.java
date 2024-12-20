package shop.linyh.miniProgramDemo.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.linyh.miniProgramDemo.entity.Tasks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import shop.linyh.miniProgramDemo.entity.vo.DayAndCountVO;
import shop.linyh.miniProgramDemo.entity.vo.TaskVO;

import java.util.Date;
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

    /**
     * dateStr不一定一定有 格式：2024-11-6
     * @param dateStr
     * @return
     */
    List<Tasks> listTask(@Param("dateStr") String dateStr, @Param("userId") Long userId, @Param("tagId") Integer tagId);
}




