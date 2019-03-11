package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.wechat.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/4
 * Time: 下午4:53
 */
@Repository
public interface UserMapper
{

    int insert(User user);

    User find(int id);

    User findIdByOpenid(@Param(value = "openid") String openid);
}
