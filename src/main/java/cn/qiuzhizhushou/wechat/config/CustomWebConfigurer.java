package cn.qiuzhizhushou.wechat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/15
 * Time: 下午3:53
 */
@Configuration
public class CustomWebConfigurer implements WebMvcConfigurer
{
    @Autowired TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/wechat/sign/*")
                .excludePathPatterns("/wechat/login/wxlogin")
        ;
    }
}
