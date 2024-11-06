package shop.linyh.miniProgramDemo.service.impl;
import java.util.Date;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import shop.linyh.miniProgramDemo.common.enums.ErrorCodeEnum;
import shop.linyh.miniProgramDemo.common.exception.BusinessException;
import shop.linyh.miniProgramDemo.entity.User;
import shop.linyh.miniProgramDemo.mapper.UserMapper;
import shop.linyh.miniProgramDemo.service.UserService;
import shop.linyh.miniProgramDemo.utils.RedisUtil;

import java.util.HashMap;
import java.util.Map;

import static shop.linyh.miniProgramDemo.common.CommonConstant.OPEN_ID;

/**
 * @author linzz
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-11-04 22:50:29
 */
@Service
@Slf4j
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
        JSONObject jsonObject = JSON.parseObject(res);
        Object openId = jsonObject.get(OPEN_ID);
        User user = getUserInfo(openId);

        log.info("loginId:{},res:{}", loginId,res);
        redisUtil.set(loginId, res, timeout);
        return loginId;
    }

    @Override
    public User getUserByOpenId(String openId) {
        User user = lambdaQuery().eq(User::getOpenid, openId)
                .one();
        if(user == null){
            throw new BusinessException(ErrorCodeEnum.NOT_LOGIN_ERROR, "未登录或登陆过期");
        }
        return user;
    }

    private User getUserInfo(Object openId) {
        User user = lambdaQuery().eq(User::getOpenid, openId).one();
//        如果user为空，那么就是还没登陆过的
        if(user == null) {
            user = getInitUser(openId);
            this.save(user);
        }
        return user;
    }

    private User getInitUser(Object openId) {
        User user = new User();
        user.setIsBindOtherAccount(false);
        user.setOpenid(openId.toString());
        user.setSessionKey("");
        user.setUsername("微信用户");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setPhone("");

        return user;
    }

    private Map<String, Object> getWxLoginReqParam(String code) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("appid", appId);
        param.put("secret", secret);
        param.put("js_code", code);
        return param;
    }
}




