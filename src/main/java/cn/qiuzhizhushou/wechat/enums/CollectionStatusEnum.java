package cn.qiuzhizhushou.wechat.enums;

import lombok.Getter;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/4/16
 * Time: 上午11:08
 */
@Getter
public enum CollectionStatusEnum
{
    ACTIVE(1, "有效"),
    CANCEL(0, "取消");

    private int code;

    private String message;

    CollectionStatusEnum(int code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
