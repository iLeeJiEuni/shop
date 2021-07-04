package com.boot.shop.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {
	@Value("${server.resurl}")
	private String serverResurl;
	@Value("${server.upload}")
	private String serverUpload;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//第一个方法设置访问路径前缀，第二个方法设置资源路径
		registry.addResourceHandler(serverResurl).addResourceLocations(serverUpload);
	}
}
