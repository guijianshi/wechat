package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.mapper.SignMapper;
import cn.qiuzhizhushou.wechat.model.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/12
 * Time: 下午7:58
 */
@Service
public class SignService
{
    @Autowired
    SignMapper signMapper;

    public Sign find(int id)
    {

        return signMapper.find(id);
    }

    public Sign findByUidToday(int userId)
    {
        return signMapper.findByUserIdAndDate(userId, new java.sql.Date(1552650363000l));
    }

    public boolean save(Sign sign)
    {
        return signMapper.save(sign);
    }


    public boolean isSigned(int userId)
    {
        return null != findByUidToday(userId);
    }

    public List<Sign> getRankToday()
    {
        return signMapper.getRankToday();
    }

    public List<Sign> fullFormat(List<Sign> signs)
    {
        for (Sign sign: signs) {
            sign.setUser(sign.getUser());
        }
        return signs;
    }
}
