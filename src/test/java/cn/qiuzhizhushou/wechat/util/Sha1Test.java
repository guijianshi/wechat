package cn.qiuzhizhushou.wechat.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/12
 * Time: 下午4:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sha1Test
{
    @Test
    public void SHA1Encode()
    {
        String test = "hello world!";
        String sign = "430ce34d020724ed75a196dfc2ad67c77772d169";
        String encode = Sha1.SHA1Encode(test, null);
        Assert.assertEquals(encode, sign);
    }
}
