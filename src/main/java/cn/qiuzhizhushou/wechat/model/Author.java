package cn.qiuzhizhushou.wechat.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/5
 * Time: 下午1:34
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author extends Base
{
    private Integer id;

    private String name;

    private String src;

    private String url;

    private String dynasty;

    private String intro;

    private Date createdAt;

    private Date updatedAt;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSrc()
    {
        return src;
    }

    public void setSrc(String src)
    {
        this.src = src;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getDynasty()
    {
        return dynasty;
    }

    public void setDynasty(String dynasty)
    {
        this.dynasty = dynasty;
    }

    public String getIntro()
    {
        return intro;
    }

    public void setIntro(String intro)
    {
        this.intro = intro;
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
}
