package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.pojo.ArticleSimple;
import cn.qiuzhizhushou.wechat.mapper.ArticleMapper;
import cn.qiuzhizhushou.wechat.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    public ArrayList<Article> list()
    {
        return articleMapper.list();
    }

    public Article find(int id)
    {
        return articleMapper.findOneById(id);
    }
}
