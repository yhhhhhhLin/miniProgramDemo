package shop.linyh.miniProgramDemo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.linyh.miniProgramDemo.common.UserOpenIdContext;
import shop.linyh.miniProgramDemo.common.enums.ErrorCodeEnum;
import shop.linyh.miniProgramDemo.common.enums.NotificationMethodEnum;
import shop.linyh.miniProgramDemo.common.enums.TaskStatusEnum;
import shop.linyh.miniProgramDemo.common.exception.BusinessException;
import shop.linyh.miniProgramDemo.entity.Tasks;
import shop.linyh.miniProgramDemo.entity.User;
import shop.linyh.miniProgramDemo.entity.dto.AddTaskDTO;
import shop.linyh.miniProgramDemo.entity.dto.QueryTaskDTO;
import shop.linyh.miniProgramDemo.entity.dto.UpdateStatusDTO;
import shop.linyh.miniProgramDemo.entity.vo.DayAndCountVO;
import shop.linyh.miniProgramDemo.entity.vo.TaskClassificationVO;
import shop.linyh.miniProgramDemo.entity.vo.TaskVO;
import shop.linyh.miniProgramDemo.mapper.TasksMapper;
import shop.linyh.miniProgramDemo.service.TasksService;
import shop.linyh.miniProgramDemo.service.UserService;
import shop.linyh.miniProgramDemo.utils.ConversionUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private UserService userService;

    @Override
    public List<DayAndCountVO> calendars(Integer year, Integer month) {
        String[] dates = getStartAndEndOfMonth(year, month);
        List<DayAndCountVO> result = tasksMapper.calendars(dates[0], dates[1]);
        return result;
    }

    @Override
    public Boolean addTask(AddTaskDTO addTaskDTO) {
//        获取当前用户的小程序绑定openId
        String openId = UserOpenIdContext.getOpenId();
        User user = userService.getUserByOpenId(openId);

        Boolean needNotify = addTaskDTO.getNeedNotify();
        if (needNotify) {
            verifyNotifyParam(addTaskDTO);
        }

        Tasks tasks = new Tasks();
        Date taskTime = addTaskDTO.getTaskTime();
        String taskContent = addTaskDTO.getTaskContent();
//        判断如果已经超时了，那么直接修改状态为超时，否则就是未完成
        if (taskTimeOut(taskTime)) {
            tasks.setTaskStatus(TaskStatusEnum.EXPIRED.getStatus());
        } else {
            tasks.setTaskStatus(TaskStatusEnum.INCOMPLETE.getStatus());
        }
        tasks.setTaskContent(taskContent);
        tasks.setTaskTimeDate(taskTime);
        tasks.setTaskTimeTime(taskTime);
        tasks.setNeedNotify(addTaskDTO.getNeedNotify());
        tasks.setTagId(addTaskDTO.getTagId());
        tasks.setUserId(user.getId());
        boolean saveResult = save(tasks);
        log.info("添加新的任务成功:{}", tasks);
        return saveResult;
    }

    private void verifyNotifyParam(AddTaskDTO addTaskDTO) {
        Integer notifyMethod = addTaskDTO.getNotifyMethod();
        NotificationMethodEnum method = NotificationMethodEnum.fromCode(notifyMethod);
        switch (method) {
            case WECHAT:
                break;
            case EMAIL:
                verifyEmailNotifyParam();
                break;
            case SMS:
            default:
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "错误的通知方式");
        }


    }

    private void verifyEmailNotifyParam() {
        String userOpenId = UserOpenIdContext.getOpenId();
        User user = userService.getUserByOpenId(userOpenId);
        String email = user.getEmail();
        if (StrUtil.isBlank(email)) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "配置邮箱提醒必须先绑定邮箱!");
        }

    }

    @Override
    public TaskClassificationVO listTaskAndClassify(QueryTaskDTO dto) {
        String openId = UserOpenIdContext.getOpenId();
        Integer tagId = dto.getTagId();
        User user = userService.getUserByOpenId(openId);

        Calendar date = Calendar.getInstance();
        date.set(dto.getYear(), dto.getMonth() - 1, dto.getDay());

        List<Tasks> tasks = tasksMapper.listTask(new SimpleDateFormat("yyyy-MM-dd").format(date.getTime()), user.getId(), tagId);

        return getTaskClassificationVO(tasks);
    }

    private TaskClassificationVO getTaskClassificationVO(List<Tasks> tasks) {
        TaskClassificationVO taskClassificationVO = new TaskClassificationVO();

        List<Tasks> expiredTasks = tasks.stream()
                .filter(t -> t.getTaskStatus() == TaskStatusEnum.EXPIRED.getStatus())
                .collect(Collectors.toList());

        List<Tasks> inCompleteTasks = tasks.stream()
                .filter(t -> t.getTaskStatus() == TaskStatusEnum.INCOMPLETE.getStatus())
                .collect(Collectors.toList());

        List<Tasks> completeTasks = tasks.stream()
                .filter(t -> t.getTaskStatus() == TaskStatusEnum.COMPLETE.getStatus())
                .collect(Collectors.toList());

        taskClassificationVO.setExpiredTasks(convertToListTaskVO(expiredTasks));
        taskClassificationVO.setIncompleteTasks(convertToListTaskVO(inCompleteTasks));
        taskClassificationVO.setCompletedTasks(convertToListTaskVO(completeTasks));
        return taskClassificationVO;
    }

    @Override
    public Boolean updateTaskStatus(UpdateStatusDTO dto) {
        String openId = UserOpenIdContext.getOpenId();
        User user = userService.getUserByOpenId(openId);

        Long taskId = dto.getId();
        Integer status = dto.getStatus();

        return lambdaUpdate().eq(Tasks::getUserId, user.getId())
                .eq(Tasks::getId, taskId)
                .set(Tasks::getTaskStatus, status)
                .set(TaskStatusEnum.COMPLETE.getStatus() == dto.getStatus(), Tasks::getFinishTime, new Date())
                .update();
    }

    @Override
    public List<Tasks> getUnFinishTask(String date) {
        Calendar dateTime = Calendar.getInstance();
        return this.lambdaQuery()
                .eq(Tasks::getTaskTimeDate, dateTime.getTime())
                .eq(Tasks::getTaskStatus, TaskStatusEnum.INCOMPLETE.getStatus())
                .list();
    }

    /**
     * @param expireTasks
     */
    @Override
    public Boolean batchFinishTask(List<Tasks> expireTasks) {
        expireTasks.forEach(task -> {
            task.setTaskStatus(TaskStatusEnum.INCOMPLETE.getStatus());
            task.setFinishTime(new Date());
        });

        return this.updateBatchById(expireTasks);


    }

    @Override
    public List<Tasks> getUnFinishTaskByTagId(Long tagId) {
        return lambdaQuery().ne(Tasks::getTaskStatus, TaskStatusEnum.COMPLETE.getStatus())
                .eq(Tasks::getTagId, tagId)
                .list();
    }

    @Override
    public TaskClassificationVO listByTagId(Integer tagId) {
        if (tagId == null || tagId <= 0) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "标签数据错误");
        }
        List<Tasks> tasks = lambdaQuery()
                .eq(Tasks::getTagId, tagId)
                .list();
        return getTaskClassificationVO(tasks);
    }

    private List<TaskVO> convertToListTaskVO(List<Tasks> tasks) {
        return ConversionUtil.mapperCollection(tasks, t -> {
            TaskVO taskVO = new TaskVO();
            BeanUtils.copyProperties(t, taskVO);
//            拼接出完整时间
            taskVO.setTaskTime(t.getFullTaskTime());
            return taskVO;
        }, ArrayList::new);
    }

    private boolean taskTimeOut(Date time) {
//        和当前时间比较，判断是否超时
        Calendar currentTime = Calendar.getInstance();
        Calendar taskTime = Calendar.getInstance();
        taskTime.setTime(time);
        return taskTime.before(currentTime);
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




