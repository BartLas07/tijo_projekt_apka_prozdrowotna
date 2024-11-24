package com.example.apka_prozdrowotna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


import java.util.Collections;

@SpringBootApplication
public class ApkaProzdrowotnaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApkaProzdrowotnaApplication.class, args);
	}


	@Bean
	public FilterRegistrationBean<JwtFilter> filterRegistrationBean() {
		FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/hello/*"));
		return filterRegistrationBean;
	}
}
