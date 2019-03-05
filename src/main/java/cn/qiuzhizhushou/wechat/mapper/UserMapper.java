package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.wechat.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
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
    @Select("select * from User where id = #{id}")
    User selectUser(int id);

    @Insert("insert into user (name, age) values (#{name}, #{age}) ")
    int insert(User user);
}
