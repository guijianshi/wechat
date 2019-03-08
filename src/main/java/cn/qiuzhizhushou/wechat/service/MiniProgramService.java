package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.util.HttpUtil;
import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/7
 * Time: 下午7:42
 */
@Component
public class MiniProgramService
{
    @Value("${mini-program.appid}")
    private String appid;

    @Value("${mini-program.secret}")
    private String secret;

    private String grantType = "authorization_code";

    public Map<String, String> wxlogin(String code) throws BusinessException
    {
        String url = "https://api.weixin.qq.com/sns/jscode2session?";
        Map<String, String> data = new HashMap<>();
        data.put("appid", appid);
        data.put("secret", secret);
        data.put("js_code", code);
        data.put("grant_type", grantType);
        Map<String, String> map = null;
        try {
            String res = HttpUtil.doGet(url, data);
            // todo 结果处理
            map = (Map) JSONUtils.parse(res);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.HTTP_GET_ERROR);
        }
        return map;
    }
}
