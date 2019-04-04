package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.wechat.model.ArticleCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/21
 * Time: 上午11:07
 */
public interface ArticleCollectionMapper
{
	ArticleCollection findByUidAndArticleId(@Param(value = "userId") int userId, @Param(value = "articleId") int articleId);

	boolean save(ArticleCollection collection);

	boolean update(ArticleCollection collection);

	List<Map<String, Object>> articleList(@Param(value = "userId") int userId, @Param(value = "offset") int offset);

	ArticleCollection findByUidAndTypeAndTargetId(@Param(value = "userId") int userId,
												  @Param(value = "type") int type,
												  @Param(value = "targetId") int targetId);
}
