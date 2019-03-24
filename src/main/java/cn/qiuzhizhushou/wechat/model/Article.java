package cn.qiuzhizhushou.wechat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Result;

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

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
    private Date updatedAt;

    private Boolean poem = false;

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
        return content.replaceAll( "(?i)\\<br\\s*\\/\\s*\\>", "\n");
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

    public Boolean getPoem()
    {
        return poem;
    }

    public void setPoem(Boolean poem)
    {
        poem = poem;
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
