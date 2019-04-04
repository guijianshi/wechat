package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.mapper.ArticleCollectionMapper;
import cn.qiuzhizhushou.wechat.model.ArticleCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/21
 * Time: 上午11:18
 */
@Service
public class ArticleCollectionService
{
	@Autowired
	ArticleCollectionMapper articleCollectionMapper;

	public ArticleCollection findByUidAndArticleId(int userId, int articleId)
	{
		return articleCollectionMapper.findByUidAndArticleId(userId, articleId);
	}

	public ArticleCollection findByUidAndTypeAndTargetId(int userId, int type, int targetId)
	{
		return articleCollectionMapper.findByUidAndTypeAndTargetId(userId, type, targetId);
	}

	public boolean isCollected(int userId, int articleId)
	{
		ArticleCollection articleCollection = findByUidAndArticleId(userId, articleId);

		return null != articleCollection && articleCollection.isCollected();
	}

	public boolean save(ArticleCollection collection)
	{
		return articleCollectionMapper.save(collection);
	}

	public boolean update(ArticleCollection collection)
	{
		return articleCollectionMapper.update(collection);
	}

	public List<Map<String, Object>> articleList(int userId, int offset)
	{
		return articleCollectionMapper.articleList(userId, offset);
	}
}
