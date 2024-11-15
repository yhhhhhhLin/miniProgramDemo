package shop.linyh.miniProgramDemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.linyh.miniProgramDemo.common.UserOpenIdContext;
import shop.linyh.miniProgramDemo.common.enums.ErrorCodeEnum;
import shop.linyh.miniProgramDemo.common.exception.BusinessException;
import shop.linyh.miniProgramDemo.entity.TaskTag;
import shop.linyh.miniProgramDemo.entity.Tasks;
import shop.linyh.miniProgramDemo.entity.User;
import shop.linyh.miniProgramDemo.entity.dto.UpdateTaskTagDto;
import shop.linyh.miniProgramDemo.mapper.TaskTagMapper;
import shop.linyh.miniProgramDemo.service.TaskTagService;
import shop.linyh.miniProgramDemo.service.TasksService;
import shop.linyh.miniProgramDemo.service.UserService;

import java.util.List;

/**
 * @author linzz
 * @description 针对表【task_tag】的数据库操作Service实现
 * @createDate 2024-11-15 23:07:47
 */
@Service
public class TaskTagServiceImpl extends ServiceImpl<TaskTagMapper, TaskTag>
        implements TaskTagService {

    @Autowired
    private UserService userService;

    @Autowired
    private TasksService tasksService;

    @Override
    public Boolean addTag(String tagName) {
        User user = userService.getUserByOpenId(UserOpenIdContext.getOpenId());
        TaskTag taskTag = new TaskTag();
        taskTag.setTagName(tagName);
        taskTag.setUserId(user.getId());

        return this.save(taskTag);
    }

    @Override
    public Boolean updateTag(UpdateTaskTagDto dto) {
        TaskTag sameNameTag = getOne(Wrappers.<TaskTag>lambdaQuery().eq(TaskTag::getTagName, dto.getTagId()));
        if (sameNameTag != null) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "已经存在这个名字标签");
        }

        return lambdaUpdate()
                .eq(TaskTag::getId, dto.getTagId())
                .set(TaskTag::getTagName, dto.getNewTagName())
                .update();
    }

    @Override
    public Boolean delTag(Long tagId) {
        List<Tasks> tasksList = tasksService.getUnFinishTaskByTagId(tagId);
        if(!tasksList.isEmpty()){
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "这个标签绑定下存在未完成或超时任务");
        }

        return this.removeById(tagId);
    }
}




