package shop.linyh.miniProgramDemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import shop.linyh.miniProgramDemo.entity.Tasks;
import shop.linyh.miniProgramDemo.service.TasksService;
import shop.linyh.miniProgramDemo.mapper.TasksMapper;
import org.springframework.stereotype.Service;

/**
* @author linzz
* @description 针对表【tasks】的数据库操作Service实现
* @createDate 2024-11-05 16:38:39
*/
@Service
public class TasksServiceImpl extends ServiceImpl<TasksMapper, Tasks>
    implements TasksService{

}




