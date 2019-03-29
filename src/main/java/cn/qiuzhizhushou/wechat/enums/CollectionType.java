package cn.qiuzhizhushou.wechat.enums;

import lombok.Getter;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/29
 * Time: 下午5:03
 */
@Getter
public enum CollectionType
{
	ARTICLE(0, "文章"),
	QUOTE(1, "名句"),;
	private int code;

	private String message;

	CollectionType(int code, String message)
	{
		this.code = code;
		this.message = message;
	}
}
