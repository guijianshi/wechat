package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.mapper.CollectionMapper;
import cn.qiuzhizhushou.wechat.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/21
 * Time: 上午11:18
 */
@Service
public class CollectionService
{
    @Autowired
    CollectionMapper collectionMapper;

    public Collection findByUidAndArticleId(int userId, int articleId)
    {
        return collectionMapper.findByUidAndArticleId(userId, articleId);
    }

    public boolean isCollected(int userId, int articleId)
    {
        return null != collectionMapper.findByUidAndArticleId(userId, articleId);
    }

    public boolean save(Collection collection)
    {
        return collectionMapper.save(collection);
    }

    public boolean update(Collection collection)
    {
        return collectionMapper.update(collection);
    }
}
