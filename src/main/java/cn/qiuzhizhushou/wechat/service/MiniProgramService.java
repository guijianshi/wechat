package cn.qiuzhizhushou.wechat.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/7
 * Time: 下午7:42
 */
public class MiniProgramService
{
    public Map<String, String> login(String code)
    {
        String url = "https://api.weixin.qq.com/sns/jscode2session?";
        HashMap<String, String> data = new HashMap<>();
        return  data;
    }
}
