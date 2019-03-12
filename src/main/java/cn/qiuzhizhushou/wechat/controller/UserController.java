package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.MiniProgramService;
import cn.qiuzhizhushou.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/11
 * Time: 上午10:57
 */
@RestController
@RequestMapping("wechat/user")
public class UserController extends BaseController
{
    @Autowired
    UserService userService;

    @Autowired
    MiniProgramService miniProgramService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResponse find(@PathVariable int id)
    {
        return JsonResponse.success(userService.find(id));
    }
}
