package cn.qiuzhizhushou.wechat.enums;

import lombok.Getter;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/4/1
 * Time: 下午5:30
 */
@Getter
public enum CollectionTypeEnum
{
	ARTICLE(1, "文章"),
	QUOTE(2, "名言"),;

	private Integer code;

	private String message;

	CollectionTypeEnum(Integer code, String message)
	{
		this.code = code;
		this.message = message;
	}
}
