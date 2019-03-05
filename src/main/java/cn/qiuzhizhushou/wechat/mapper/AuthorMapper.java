package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.wechat.model.Author;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午4:57
 */
@Repository
public interface AuthorMapper
{
    @Select("select * from author where id = #{id}")
    Author findById(int id);
}
