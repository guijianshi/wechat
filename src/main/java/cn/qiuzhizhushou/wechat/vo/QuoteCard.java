package cn.qiuzhizhushou.wechat.vo;

import lombok.Data;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/4/16
 * Time: 下午6:11
 */
@Data
public class QuoteCard
{
    private Integer id;

    private String content;

    private String authorName;

    private Boolean isCollection = false;

    public QuoteCard()
    {
    }
}
