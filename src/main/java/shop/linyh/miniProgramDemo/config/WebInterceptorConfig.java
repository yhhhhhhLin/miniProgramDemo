package shop.linyh.miniProgramDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import shop.linyh.miniProgramDemo.interceptor.AuthInterceptor;


@Configuration
public class WebInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**");
    }
}
