package shop.linyh.miniProgramDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.linyh.miniProgramDemo.common.BaseResponse;
import shop.linyh.miniProgramDemo.utils.ResultUtils;
import shop.linyh.miniProgramDemo.service.UserService;

/**
 * @author linzz
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getWxLoginId/{code}")
    public BaseResponse<String> getWxLoginId(@PathVariable String code) {
        String loginId = userService.getWxLoginId(code);
        return ResultUtils.success(loginId);
    }
}
