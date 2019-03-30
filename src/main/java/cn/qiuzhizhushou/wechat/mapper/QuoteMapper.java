package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.wechat.model.Quote;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午4:33
 */
@Repository
public interface QuoteMapper
{
    Quote findById(int id);

    @Select("select * from quote where article_id=#{articleId}")
    List<Quote> findByArticleId(int articleId);

	List<Quote> selectByIds(@Param("ids") int[] ids);
}
