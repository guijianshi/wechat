package cn.qiuzhizhushou.wechat.controller;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.response.JsonResponse;
import cn.qiuzhizhushou.wechat.util.JWTUtil;
import cn.qiuzhizhushou.wechat.util.Token;
import cn.qiuzhizhushou.wechat.util.TokenParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    @Autowired
    JWTUtil jwtUtil;

    @RequestMapping("test")
    public JsonResponse test() throws BusinessException
    {
        TokenParam tokenParam = new TokenParam();

        tokenParam.addClaim("user_id", Integer.valueOf("1"))
//                .addClaim("name", "林")
                .setExpiresAt(TokenParam.getAfterDate(null, 0, 0, 0, 0, 10, 20));
//        JWTUtil jwtUtil = new JWTUtil();
        return JsonResponse.success(jwtUtil.createToken(tokenParam));
    }

    @RequestMapping("verify")
    public JsonResponse verify(@RequestParam String token) throws BusinessException
    {
//        JWTUtil jwtUtil = new JWTUtil();
        jwtUtil.verifyToken(token);
        return JsonResponse.success(Token.getUserId());
    }

    @RequestMapping("test2")
    public JsonResponse test2()
    {
        return JsonResponse.success(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
    }
}
