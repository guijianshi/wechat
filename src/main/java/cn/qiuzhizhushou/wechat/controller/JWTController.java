package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.util.JWTUtil;
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
    public JsonResponse test()
    {
        return JsonResponse.success(JWTUtil.createToken());
    }

    @RequestMapping("verify")
    public JsonResponse verify(@RequestParam String token)
    {
        JWTUtil.verifyToken(token);
        return JsonResponse.success(null);

    }
}
