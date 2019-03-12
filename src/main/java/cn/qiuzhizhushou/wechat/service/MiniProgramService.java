package cn.qiuzhizhushou.wechat.service;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.model.User;
import cn.qiuzhizhushou.wechat.util.HttpUtil;
import cn.qiuzhizhushou.wechat.util.Sha1;
import com.alibaba.druid.support.json.JSONUtils;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
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
            map = (Map) JSONUtils.parse(res);
            checkSession(map);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.HTTP_GET_ERROR);
        }
        return map;
    }

    public void checkSession(Map<String, String> sessionData) throws BusinessException
    {
        if (!sessionData.containsKey("session_key") || !sessionData.containsKey("openid")) {
            throw new BusinessException(EmBusinessError.WECHAT_SESSION_FORMAT_ERROR);
        }
    }

    public User getUserInfo(String rawData, String sessionKey, String signature, String openid) throws BusinessException
    {
        if (!Sha1.checkSHA1(rawData.concat(sessionKey), signature)) {
            throw new BusinessException(EmBusinessError.WECHAT_DATA_ILLEGAL_ERROR);
        }
        Map<String, Object> userMap = (Map) JSONUtils.parse(rawData);
        User user = new User();
        user.setOpenid(openid);
        user.setAvatarUrl((String) userMap.get("avatarUrl"));
        user.setNickname((String)userMap.get("nickName"));
        user.setGender((Integer) userMap.get("gender"));
        user.setCountry((String)userMap.get("country"));
        user.setProvince((String)userMap.get("province"));
        user.setCity((String)userMap.get("city"));
        user.setSession(sessionKey);
        return user;
    }
}
