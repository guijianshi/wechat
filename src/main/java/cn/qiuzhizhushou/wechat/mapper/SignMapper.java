package cn.qiuzhizhushou.wechat.mapper;

import cn.qiuzhizhushou.wechat.model.Sign;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/12
 * Time: 下午7:57
 */
public interface SignMapper
{
    Sign find(int in);

    Sign findByUserIdAndDate(@Param("userId") int userId, @Param("today") Date date);

    boolean save(Sign sign);

    List<Sign> getRankToday();

    int monthSignNumOfDays(int userId);
}
