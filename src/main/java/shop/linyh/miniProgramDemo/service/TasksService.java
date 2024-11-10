package shop.linyh.miniProgramDemo.service;

import shop.linyh.miniProgramDemo.entity.Tasks;
import com.baomidou.mybatisplus.extension.service.IService;
import shop.linyh.miniProgramDemo.entity.dto.AddTaskDTO;
import shop.linyh.miniProgramDemo.entity.dto.QueryTaskDTO;
import shop.linyh.miniProgramDemo.entity.dto.UpdateStatusDTO;
import shop.linyh.miniProgramDemo.entity.vo.DayAndCountVO;
import shop.linyh.miniProgramDemo.entity.vo.TaskClassificationVO;

import java.util.List;

/**
* @author linzz
* @description 针对表【tasks】的数据库操作Service
* @createDate 2024-11-05 16:38:39
*/
public interface TasksService extends IService<Tasks> {

    /**
     * 根据年月查询当前月的所有日和对应任务数量
     * @param year
     * @param month
     * @return
     */
    List<DayAndCountVO> calendars(Integer year, Integer month);

    /**
     * 添加任务
     * @param addTaskDTO
     * @return
     */
    Boolean addTask(AddTaskDTO addTaskDTO);

    TaskClassificationVO listTaskAndClassify(QueryTaskDTO dto);

    Boolean updateTaskStatus(UpdateStatusDTO dto);

    /**
     * 根据时间获取当天未完成的任务
     * @param date
     * @return
     */
    List<Tasks> getUnFinishTask(String date);
}
