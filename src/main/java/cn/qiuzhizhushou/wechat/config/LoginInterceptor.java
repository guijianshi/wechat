package cn.qiuzhizhushou.wechat.config;

import cn.qiuzhizhushou.wechat.error.BusinessException;
import cn.qiuzhizhushou.wechat.error.EmBusinessError;
import cn.qiuzhizhushou.wechat.util.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断是否登入
 * Created by IDEA.
 * User: mc
 * Date: 19/4/16
 * Time: 下午7:31
 */
@Configuration
@Slf4j
public class LoginInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (!Token.isLogin()) {
            throw new BusinessException(EmBusinessError.NO_LOGGING_ERROR);
        }
        return true;
    }
}
