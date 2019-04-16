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
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/wechat/sign/*")
                .addPathPatterns("/wechat/login/*")
                .addPathPatterns("/wechat/audio/**")
                .addPathPatterns("/wechat/collection/**")
                .excludePathPatterns("/wechat/login/wxlogin")
        ;
    }
}
