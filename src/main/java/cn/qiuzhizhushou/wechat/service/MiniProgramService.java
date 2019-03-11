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
            // todo 结果处理
            map = (Map) JSONUtils.parse(res);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.HTTP_GET_ERROR);
        }
        return map;
    }

    public Object getUserInfo(String data, String key, String iv) throws BusinessException
    {
        try {
            String result = decrypt(data, key, iv);
            return result;
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.SYSTEM_ERROR);
        }
    }

    public Boolean checkRawData(String rawData, String signature)
    {
        return Sha1.checkSHA1(rawData, signature);
    }

    /**
     * AES解密
     *
     * @param data           //密文，被加密的数据
     * @param key            //秘钥
     * @param iv             //偏移量
     * @return
     * @throws Exception
     */
    private String decrypt(String data, String key, String iv) throws Exception
    {
        Security.addProvider(new BouncyCastleProvider());

        //被加密的数据
        byte[] dataByte = Base64.decodeBase64(data);
        //加密秘钥
        byte[] keyByte = Base64.decodeBase64(key);
        //偏移量
        byte[] ivByte = Base64.decodeBase64(iv);
        try {
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return result;
            }
            return null;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidParameterSpecException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | UnsupportedEncodingException
                | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
