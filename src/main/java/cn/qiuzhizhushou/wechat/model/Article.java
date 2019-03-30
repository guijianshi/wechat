package cn.qiuzhizhushou.wechat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午7:14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article extends Base
{
    private Integer id;

    private String title;

    private Integer authorId;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;

    private Integer type = 0;

    private Author author;

    private List<Quote> quotes;

    public void setQuotes(List<Quote> quotes)
    {
        this.quotes = quotes;
    }

    public List<Quote> getQuotes()
    {
        return quotes;
    }

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

    public Integer getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(Integer authorId)
    {
        this.authorId = authorId;
    }

    public String getContent()
    {
        if (null == content) {
            return null;
        }
        String articleContent = content.replaceAll( "(?i)\\<br\\s*\\/\\s*\\>", "\n");
//        articleContent = articleContent.replaceAll("<([^>]+)>[\\d\\D]*<\\/\\1>", "");
        articleContent = articleContent.replaceAll("<([^>]+)>", "\n");
        articleContent = articleContent.replaceAll("\\(([^\\(]+\\))", "\n");
        articleContent = articleContent.replaceAll("（[^）]+）", "\n");
        articleContent = articleContent.replaceAll("\n(\\s)*\n", "\n");
        return articleContent;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    /**
     * 基础格式
     */
    public Article formatBase()
    {
        author = null;
        quotes = null;
        return this;
    }

    /**
     * 完整格式
     */
    public Article formatFull()
    {
        author = this.getAuthor();
        quotes = this.getQuotes();
        return this;
    }
}
