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
public class ArticleCollection extends Base
{
	private Integer id;

	private Integer userId;

	private Integer targetId;

	private Integer type;

	private Integer status;

	private Date createdAt;

	private Date updatedAt;

	private Article article;

	private User user;

	public ArticleCollection()
	{
	}

	public ArticleCollection(int userId, int targetId, int type)
	{
		this.userId = userId;
		this.targetId = targetId;
		this.type = type;
		createdAt = new Date();
		updatedAt = new Date();
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

	public Integer getTargetId()
	{
		return targetId;
	}

	public void setTargetId(Integer targetId)
	{
		this.targetId = targetId;
	}

	public Integer getType()
	{
		return type;
	}

	public void setType(Integer type)
	{
		this.type = type;
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

	public Article getArticle()
	{
		return article;
	}

	public void setArticle(Article article)
	{
		this.article = article;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public boolean isCollected()
	{
		return status == 1;
	}

	public void setActive()
	{
		status = 1;
	}

	public void setCancel()
	{
		status = 2;
	}
}
