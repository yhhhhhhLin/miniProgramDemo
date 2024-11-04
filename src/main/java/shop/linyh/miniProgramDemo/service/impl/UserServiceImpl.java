package shop.linyh.miniProgramDemo.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import shop.linyh.miniProgramDemo.entity.User;
import shop.linyh.miniProgramDemo.mapper.UserMapper;
import shop.linyh.miniProgramDemo.service.UserService;
import shop.linyh.miniProgramDemo.utils.RedisUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linzz
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-11-04 22:50:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Value("${weixin.appid}")
    private String appId;

    @Value("${weixin.secret}")
    private String secret;

    private final int timeout = 86400;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String getWxLoginId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, Object> requestParam = getWxLoginReqParam(code);
        String res = HttpUtil.get(url, requestParam);
        String loginId = IdUtil.simpleUUID();
        redisUtil.set(loginId, res, timeout);
        return loginId;
    }

    private Map<String, Object> getWxLoginReqParam(String code) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("appid", appId);
        param.put("secret", secret);
        param.put("js_code", code);
        return param;
    }
}




