package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.pojo.User;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/index")
public class IndexController extends BaseController
{
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    private User index()
    {
        User user = new User();
        user.setUsername("lin");
        user.setAge(100);
        user.setPassword("123456");
        user.setBirthday(new Date());
        user.setDesc("这世上最帅的人");

        return user;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    private JsonResponse findById(@PathVariable int id)
    {
        return JsonResponse.success(userService.selectUser(1));
    }
}
