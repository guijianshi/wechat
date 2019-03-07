package cn.qiuzhizhushou.wechat.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午1:26
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Quote extends Base
{
    private Integer id;

    private String content;

    private Integer articleId;

    private Integer authorId;

    private Author author;

    private Article article;

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

    public Integer getArticleId()
    {
        return articleId;
    }

    public void setArticleId(Integer articleId)
    {
        this.articleId = articleId;
    }

    public Integer getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(Integer authorId)
    {
        this.authorId = authorId;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public Article getArticle()
    {
        return article;
    }

    public void setArticle(Article article)
    {
        this.article = article;
    }

    /**
     * 基础格式
     */
    public Quote formatBase()
    {
        author = null;
        article = null;
        return this;
    }

    /**
     * 完整格式
     */
    public Quote formatFull()
    {
        author = this.getAuthor();
        article = this.getArticle();
        return this;
    }
}
