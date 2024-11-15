package shop.linyh.miniProgramDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.linyh.miniProgramDemo.common.BaseResponse;
import shop.linyh.miniProgramDemo.entity.TaskTag;
import shop.linyh.miniProgramDemo.entity.dto.UpdateTaskTagDto;
import shop.linyh.miniProgramDemo.service.TaskTagService;
import shop.linyh.miniProgramDemo.utils.ResultUtils;

import java.util.List;

/**
 * @author linzz
 */
@RestController("/taskTag")
public class TaskTagController {

    @Autowired
    private TaskTagService taskTagService;

    @PostMapping("/add")
    public BaseResponse<Boolean> addTag(@RequestBody String tagName){
//        todo 参数校验
        Boolean result = taskTagService.addTag(tagName);
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateTag(@RequestBody UpdateTaskTagDto dto){
        Boolean result = taskTagService.updateTag(dto);
        return ResultUtils.success(result);
    }

    @PostMapping("/del")
    public BaseResponse<Boolean> deleteTag(Long tagId){
        Boolean result = taskTagService.delTag(tagId);
        return ResultUtils.success(result);
    }

    @PostMapping("/getOne")
    public BaseResponse<TaskTag> getOne(Long tagId){
        return ResultUtils.success(taskTagService.getById(tagId));
    }

    @PostMapping("/list")
    public BaseResponse<List<TaskTag>> list(){
        return ResultUtils.success(taskTagService.list());
    }

}
