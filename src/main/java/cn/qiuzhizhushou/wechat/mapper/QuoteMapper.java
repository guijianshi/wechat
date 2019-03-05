package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.wechat.model.Quote;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午4:33
 */
@Repository
public interface QuoteMapper
{
    @Select("select * from quote where id=#{id}")
    @Results(
            {
                    @Result(id=true,column="id",property="id"),
                    @Result(column="name",property="name"),
                    @Result(column="age",property="age"),
                    @Result(column="author_id",property="authorId"),
                    @Result(column="article_id",property="articleId"),
                    @Result(column="author_id",property="author",
                        one = @One(
                                select = "cn.qiuzhizhushou.wechat.mapper.AuthorMapper.findById",
                                fetchType = FetchType.EAGER
                        )
                    ),
                    @Result(column = "article_id", property = "article",
                        one = @One(
                                select = "cn.qiuzhizhushou.wechat.mapper.ArticleMapper.findById",
                                fetchType = FetchType.EAGER
                        )
                    )
            })
    Quote findById(int id);
}
