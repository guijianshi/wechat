package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.model.User;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wechat/index")
public class IndexController extends BaseController
{
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    private JsonResponse index()
    {
        User user = new User();
        return JsonResponse.success(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    private JsonResponse findById(@PathVariable int id)
    {
        return JsonResponse.success(userService.find(1));
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    private JsonResponse add(@RequestParam String name, @RequestParam int age)
    {
        User user = new User();
        return JsonResponse.success(user);
    }
}
