package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/index")
public class IndexController
{
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
}
