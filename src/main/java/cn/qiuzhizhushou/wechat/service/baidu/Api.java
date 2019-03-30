package cn.qiuzhizhushou.wechat.service.baidu;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class Api
{
    @Value("${baidu-api.appid}")
    private String appid;
    @Value("${baidu-api.appkey}")
    private String appkey;

    @Value("${baidu-api.secret}")
    private String secret;

    /**
     * 文字转语音
     * @param content 需要转换的文字
     * @return 二进制数组
     */
    public byte[] tts(String content) throws BusinessException
    {
        AipSpeech client = new AipSpeech(appid, appkey, secret);
        HashMap<String, Object> options = new HashMap<String, Object>();
        TtsResponse res = client.synthesis(content, "zh", 1, options);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }
        if (data != null) {
            return data;
        } else {
            throw new BusinessException(EmBusinessError.BAIDU_TTS_ERROR);
        }
    }

    public void tts(String content, String filename) throws BusinessException
    {
        byte[] data = this.tts(content);
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @link http://ai.baidu.com/docs#/TTS-Online-Java-SDK/top
     */
    public class Option
    {
        private String spd;

        private String pit;

        private String per;

        private String vol;
    }
}
