package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.MiniProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/7
 * Time: 下午7:36
 */
public class LoginController extends BaseController
{
    @Autowired
    MiniProgramService miniProgramService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public JsonResponse login(@RequestParam String code)
    {
        //todo 微信登入
        return JsonResponse.success(miniProgramService.login(code));
    }
}
