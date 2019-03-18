package cn.qiuzhizhushou.wechat.config;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.util.JWTUtil;
import cn.qiuzhizhushou.wechat.util.Token;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/15
 * Time: 下午4:03
 */
@Configuration
public class TokenInterceptor implements HandlerInterceptor
{
    @Autowired
    JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String token = request.getParameter("token");
        if (null == token || "".equals(token)) {
            throw new BusinessException(EmBusinessError.NO_LOGGING_ERROR);
        }
        DecodedJWT jwt = jwtUtil.verifyToken(token);
        if (!jwt.getClaims().containsKey("user_id")) {
            throw new BusinessException(EmBusinessError.JWT_VERIFY_ERROR);
        }
        Token.setUserId(jwt.getClaims().get("user_id").asInt());
        System.out.println(Token.getUserId());
        return true;
    }
}
