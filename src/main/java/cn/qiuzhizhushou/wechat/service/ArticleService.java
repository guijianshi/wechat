package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.mapper.ArticleMapper;
import cn.qiuzhizhushou.wechat.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午7:57
 */
@Service
public class ArticleService
{
    @Autowired
    ArticleMapper articleMapper;

//    public ArrayList<Article> list()
//    {
//        return articleMapper.list();
//    }

    public Article findById(int id)
    {
        return articleMapper.findById(id);
    }

    public List<Article> search(String column, String value)
    {
        return articleMapper.search(value);
    }
}
