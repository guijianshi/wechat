package cn.qiuzhizhushou.wechat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/7
 * Time: 下午6:29
 */
@JsonIgnoreProperties(value = {"handler"})
public abstract class Base implements Serializable
{
}
