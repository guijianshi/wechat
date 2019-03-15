package cn.qiuzhizhushou.wechat.util;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/15
 * Time: 下午6:54
 */
public class DateUtil
{
    public static String today()
    {
        Date date = new Date();
        return DateFormat.getDateInstance().format(date);
    }
}
