package egen.io.apimodule;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import egen.io.apimodule.interceptors.AdminInterceptor;
import egen.io.apimodule.interceptors.LoggingInterceptor;

@Configuration
@ComponentScan
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] links={"/users/**","/users","/admin/**","/admin","/movies/**","/movies","/comments/**","/comments","/ratings/**","/ratings"};
		String[] adminlinks={"/admin/**","/admin"};
		registry.addInterceptor(new LoggingInterceptor()).addPathPatterns(links);
		registry.addInterceptor(new AdminInterceptor()).addPathPatterns(adminlinks);
	 } 
}
