package shop.linyh.miniProgramDemo.service;

import shop.linyh.miniProgramDemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author linzz
* @description 针对表【user】的数据库操作Service
* @createDate 2024-11-04 22:50:29
*/
public interface UserService extends IService<User> {

    /**
     * 根据临时code获取存储的loginId
     * @param code
     * @return
     */
    String getWxLoginId(String code);
}
