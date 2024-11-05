package shop.linyh.miniProgramDemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.linyh.miniProgramDemo.entity.Notifications;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author linzz
* @description 针对表【notifications】的数据库操作Mapper
* @createDate 2024-11-05 16:38:20
* @Entity shop.linyh.miniProgramDemo.entity.Notifications
*/
@Mapper
public interface NotificationsMapper extends BaseMapper<Notifications> {

}




