package cn.qiuzhizhushou.pojo;

import cn.qiuzhizhushou.wechat.model.Quote;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/6
 * Time: 上午11:15
 */
public class QuoteAuthorAndArticle
{
    private Integer id;

    private String content;

    private String articleTitle;

    private String authorName;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getArticleTitle()
    {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle)
    {
        this.articleTitle = articleTitle;
    }

    public String getAuthorName()
    {
        return authorName;
    }

    public void setAuthorName(String authorName)
    {
        this.authorName = authorName;
    }

    public QuoteAuthorAndArticle(Quote quote)
    {
        id = quote.getId();
        content = quote.getContent();
        authorName = null == quote.getAuthor() ? "未知" : quote.getAuthor().getName();
        articleTitle = null == quote.getArticle() ? "未知": quote.getArticle().getTitle();
    }
}
