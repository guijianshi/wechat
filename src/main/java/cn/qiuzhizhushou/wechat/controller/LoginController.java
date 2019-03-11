package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.model.User;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.MiniProgramService;
import cn.qiuzhizhushou.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/7
 * Time: 下午7:36
 */
@RestController
public class LoginController extends BaseController
{
    @Autowired
    MiniProgramService miniProgramService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/wxlogin", method = RequestMethod.GET)
    public JsonResponse wxlogin(@RequestParam String code) throws BusinessException
    {
        Map<String, String> res = miniProgramService.wxlogin(code);
        if (res.containsKey("openid") && null == userService.findIdByOpenid(res.get("openid"))) {
            User user = new User();
            user.setOpenid(res.get("openid"));
            userService.insert(user);
        }
        return JsonResponse.success(res);
    }
}
