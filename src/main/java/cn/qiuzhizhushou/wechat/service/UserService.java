package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.mapper.UserMapper;
import cn.qiuzhizhushou.wechat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/4
 * Time: 下午4:56
 */
@Service
public class UserService
{
    @Autowired
    UserMapper userMapper;

    public User find(int id)
    {
        return userMapper.find(id);
    }

    public int insert(User user)
    {
        return userMapper.insert(user);
    }


    public User findIdByOpenid(String openid)
    {
        return userMapper.findIdByOpenid(openid);
    }
}
