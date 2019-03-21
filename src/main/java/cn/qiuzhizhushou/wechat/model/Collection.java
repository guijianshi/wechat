package cn.qiuzhizhushou.wechat.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/21
 * Time: 上午10:59
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Collection extends Base
{
    private Integer id;

    private Integer userId;

    private Integer articleId;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

//    private Article article;

    private User user;

    public Collection(Integer userId, Integer articleId)
    {
        this.userId = userId;
        this.articleId = articleId;
        createdAt = new Date();
        status = 1;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getArticleId()
    {
        return articleId;
    }

    public void setArticleId(Integer articleId)
    {
        this.articleId = articleId;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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
//
//    public Article getArticle()
//    {
//        return article;
//    }

//    public void setArticle(Article article)
//    {
//        this.article = article;
//    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
