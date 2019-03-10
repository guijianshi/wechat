package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.wechat.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午7:14
 */
public interface ArticleMapper
{
    Article findById(int id);

    ArrayList<Article> list();

    List<Article> search(String value);
}
