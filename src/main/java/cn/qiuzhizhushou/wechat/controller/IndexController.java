package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.model.User;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/index")
public class IndexController extends BaseController
{
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    private JsonResponse index()
    {
        User user = new User();
        user.setName("lin");
        user.setAge(100);
        return JsonResponse.success(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    private JsonResponse findById(@PathVariable int id)
    {
        return JsonResponse.success(userService.selectUser(1));
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    private JsonResponse add(@RequestParam String name, @RequestParam int age)
    {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        int result =  userService.insert(user);
        return JsonResponse.success(result);
    }
}
