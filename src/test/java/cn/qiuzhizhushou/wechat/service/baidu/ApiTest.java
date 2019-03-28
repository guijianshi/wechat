package cn.qiuzhizhushou.wechat.service.baidu;

import cn.qiuzhizhushou.wechat.WechatApplicationTests;
import cn.qiuzhizhushou.wechat.error.BusinessException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;

@Component
public class ApiTest extends WechatApplicationTests
{

    @Autowired
    private Api api;

    @Test
    public void tts() throws BusinessException
    {
        api.tts("君不见，黄河之水天上来，奔流到海不复回。\n" +
                "君不见，高堂明镜悲白发，朝如青丝暮成雪。", "将进酒.mp3");
    }
}