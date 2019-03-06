package cn.qiuzhizhushou.pojo;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/6
 * Time: 下午12:00
 */

import cn.qiuzhizhushou.wechat.model.Author;


/**
 * 首页展示格式
 */
public class ArticleSimple
{
    private Integer id;

    private String title;

    private String content;

    private Author author;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }
}
