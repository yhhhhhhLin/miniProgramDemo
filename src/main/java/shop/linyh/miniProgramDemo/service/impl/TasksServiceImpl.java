package shop.linyh.miniProgramDemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.linyh.miniProgramDemo.entity.Tasks;
import shop.linyh.miniProgramDemo.entity.vo.DayAndCountVO;
import shop.linyh.miniProgramDemo.mapper.TasksMapper;
import shop.linyh.miniProgramDemo.service.TasksService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author linzz
 * @description 针对表【tasks】的数据库操作Service实现
 * @createDate 2024-11-05 16:38:39
 */
@Service
@Slf4j
public class TasksServiceImpl extends ServiceImpl<TasksMapper, Tasks>
        implements TasksService {

    @Autowired
    private TasksMapper tasksMapper;

    @Override
    public List<DayAndCountVO> calendars(Integer year, Integer month) {
        String[] dates = getStartAndEndOfMonth(year, month);
        List<DayAndCountVO> result = tasksMapper.calendars(dates[0], dates[1]);
        return result;
    }

    private String[] getStartAndEndOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0);
        Date startDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        Date endDate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return new String[]{format.format(startDate), format.format(endDate)};
    }
}




