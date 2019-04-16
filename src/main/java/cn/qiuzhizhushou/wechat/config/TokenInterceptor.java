package cn.qiuzhizhushou.wechat.config;

import cn.qiuzhizhushou.wechat.util.JWTUtil;
import cn.qiuzhizhushou.wechat.util.Token;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 将token转换成userId
 * Created by IDEA.
 * User: mc
 * Date: 19/3/15
 * Time: 下午4:03
 */
@Configuration
@Slf4j
public class TokenInterceptor implements HandlerInterceptor
{
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String token = request.getParameter("token");
        if (null == token || "".equals(token)) {
            return true;
        }
        DecodedJWT jwt = jwtUtil.verifyToken(token);
        if (!jwt.getClaims().containsKey("user_id")) {
            return true;
        }
        Token.setUserId(jwt.getClaims().get("user_id").asInt());
        log.info("登入用户为" + Token.getUserId());
        return true;
    }
}
