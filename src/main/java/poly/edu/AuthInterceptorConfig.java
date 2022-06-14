package poly.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import poly.edu.interceptors.AuthInterceptor;

@Configuration
public class AuthInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor interceptor;

    @Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
			.addPathPatterns("/cart", "/product/like", "/product/shoppingcart",
					"/favorite", "/order/admin",
					"/admin/**")
			.excludePathPatterns( "/index");
			
	}
}
