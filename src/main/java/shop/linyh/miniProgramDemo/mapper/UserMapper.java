package shop.linyh.miniProgramDemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.linyh.miniProgramDemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author linzz
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-11-04 22:50:29
* @Entity shop.linyh.miniProgramDemo.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




