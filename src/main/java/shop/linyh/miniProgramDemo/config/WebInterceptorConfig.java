package shop.linyh.miniProgramDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import shop.linyh.miniProgramDemo.interceptor.AuthInterceptor;

import javax.annotation.Resource;

@Configuration
public class WebInterceptorConfig extends WebMvcConfigurationSupport {

    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**");
    }
}
