package shop.linyh.miniProgramDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.linyh.miniProgramDemo.common.BaseResponse;
import shop.linyh.miniProgramDemo.common.UserOpenIdContext;
import shop.linyh.miniProgramDemo.entity.dto.AddTaskDTO;
import shop.linyh.miniProgramDemo.entity.vo.DayAndCountVO;
import shop.linyh.miniProgramDemo.service.TasksService;
import shop.linyh.miniProgramDemo.utils.ResultUtils;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TasksService tasksService;

    @PostMapping("/add")
    public BaseResponse<Boolean> addTask(@RequestBody AddTaskDTO addTaskDTO){
        String openId = UserOpenIdContext.getOpenId();
        return null;
    }

    /**
     * 根据月份获取当月的所有日对应的数量
     * @return
     */
    @GetMapping("/calendars")
    public BaseResponse<List<DayAndCountVO>> calendars(Integer year, Integer month){
        List<DayAndCountVO> lists = tasksService.calendars(year, month);
        return ResultUtils.success(lists);
    }


}
