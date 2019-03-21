package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.wechat.model.Collection;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/21
 * Time: 上午11:07
 */
public interface CollectionMapper
{
    ArrayList<Collection> selectByUid(int userId);

    Collection findByUidAndArticleId(@Param("userId") int userId, @Param("articleId") int articleId);

    boolean save(Collection collection);

    boolean update(Collection collection);
}
