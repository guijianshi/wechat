package cn.qiuzhizhushou.wechat.model;

import java.util.Date;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/12
 * Time: 下午7:56
 */

public class Sign extends Base
{
    private Integer id;

    private Integer userId;

    private Date createdAt;


    public Sign()
    {
        createdAt = new Date();
    }

    public Sign(int userId)
    {
        createdAt = new Date();
        this.userId = userId;
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

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }
}
