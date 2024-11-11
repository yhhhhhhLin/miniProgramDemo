package shop.linyh.miniProgramDemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import shop.linyh.miniProgramDemo.entity.Notifications;
import shop.linyh.miniProgramDemo.mapper.NotificationsMapper;
import shop.linyh.miniProgramDemo.service.NotificationsService;

/**
 * @author linzz
 * @description 针对表【notifications】的数据库操作Service实现
 * @createDate 2024-11-05 16:38:20
 */
@Service
public class NotificationsServiceImpl extends ServiceImpl<NotificationsMapper, Notifications>
        implements NotificationsService {

    @Override
    public Notifications getByTaskId(Long taskId) {
        return lambdaQuery().eq(Notifications::getTaskId, taskId)
                .one();
    }
}




