package shop.linyh.miniProgramDemo.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import shop.linyh.miniProgramDemo.common.UserOpenIdContext;
import shop.linyh.miniProgramDemo.utils.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static shop.linyh.miniProgramDemo.common.CommonConstant.LOGIN_ID;
import static shop.linyh.miniProgramDemo.common.CommonConstant.OPEN_ID;

/**
 * @author linzz
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final List<Pattern> WHITE_LIST = Arrays.asList(
//            微信登陆接口
            Pattern.compile("/api/user/getWxLoginId(/.*)?")
    );

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
//        如果是免登录接口直接返回
        if (isWhiteList(uri)) {
            return true;
        }

        String loginId = request.getHeader(LOGIN_ID);
        if (loginId == null) {
//            TODO 改为抛出异常提示没登陆
            return false;
        }

        Object openIdObj = redisUtil.get(loginId);
        if (openIdObj == null) {
//            TODO 改为抛出异常提示没登陆
            return false;
        }

        Object openId = JSON.parseObject(openIdObj.toString()).get(OPEN_ID);
        UserOpenIdContext.setOpenId(openId.toString());
        return true;
    }

    private boolean isWhiteList(String uri) {
        return WHITE_LIST.stream().anyMatch(pattern -> pattern.matcher(uri).matches());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
