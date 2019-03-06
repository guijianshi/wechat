package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.pojo.ArticleSimple;
import cn.qiuzhizhushou.wechat.model.Article;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午7:14
 */
@Repository
public interface ArticleMapper
{
    @Select("select * from article where id = #{id}")
    @Results(
            {
                    @Result(column="author_id",property="authorId"),
                    @Result(column = "author_id", property = "author",
                        one = @One(
                            select = "cn.qiuzhizhushou.wechat.mapper.AuthorMapper.findById",
                            fetchType = FetchType.EAGER
                        )
                    )
            }
    )
    Article findById(int id);

    @Select("select article.*, author.name as 'author.name', author.dynasty as 'author.dynasty' from article, author where article.author_id = author.id limit 10")
    ArrayList<ArticleSimple> list();
}
