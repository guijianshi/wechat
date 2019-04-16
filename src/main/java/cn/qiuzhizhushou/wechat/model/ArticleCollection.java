package cn.qiuzhizhushou.wechat.model;

import cn.qiuzhizhushou.wechat.enums.CollectionStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/21
 * Time: 上午10:59
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
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
		status = CollectionStatusEnum.ACTIVE.getCode();
	}

	public boolean isCollected()
	{
		return status == CollectionStatusEnum.ACTIVE.getCode();
	}

	public void setActive()
	{
		status = CollectionStatusEnum.ACTIVE.getCode();
	}

	public void setCancel()
	{
		status = CollectionStatusEnum.CANCEL.getCode();
	}
}
