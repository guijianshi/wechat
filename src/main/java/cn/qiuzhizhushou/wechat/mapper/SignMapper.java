package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.wechat.model.Sign;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/12
 * Time: 下午7:57
 */
public interface SignMapper
{
    Sign find(int in);

    Sign findByUserIdAndDate(int userId);
}
