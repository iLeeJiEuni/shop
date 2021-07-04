package com.boot.shop;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Configuration;




@Configuration
@SpringBootApplication
@MapperScan("com.boot.shop.mapper")
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  后台启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
	}
	
	
	
	
	

}
