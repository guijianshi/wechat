package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.util.JWTUtil;
import cn.qiuzhizhushou.wechat.util.TokenParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/13
 * Time: 上午9:27
 */
@RestController
@RequestMapping("wechat/jwt")
public class JWTController extends BaseController
{
    @RequestMapping("test")
    public JsonResponse test() throws BusinessException
    {
        TokenParam tokenParam = new TokenParam();

        tokenParam.addClaim("user_id", 1)
                .addClaim("name", "林")
                .setExpiresAt(TokenParam.getAfterDate(null, 0, 0, 0, 0, 0, 20));
        return JsonResponse.success(JWTUtil.createToken(tokenParam));
    }

    @RequestMapping("verify")
    public JsonResponse verify(@RequestParam String token) throws BusinessException
    {
        JWTUtil.verifyToken(token);
        return JsonResponse.success(null);

    }
}
