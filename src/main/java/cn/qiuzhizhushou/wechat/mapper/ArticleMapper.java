package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.pojo.ArticleSimple;
import cn.qiuzhizhushou.wechat.model.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    ArrayList<Article> list();

    @Select("select article.*, author.name as 'author.name', author.dynasty as 'author.dynasty' from article, author where article.author_id = author.id and article.id=#{id} ")
    @Results(
            {
                    @Result(column = "id", property = "quotes",
                        many = @Many(
                                select = "cn.qiuzhizhushou.wechat.mapper.QuoteMapper.findByArticleId",
                                fetchType = FetchType.EAGER
                        )
                    )
            }
    )
    Article findOneById(int id);
}
