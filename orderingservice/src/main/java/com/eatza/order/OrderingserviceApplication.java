package com.eatza.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.eatza.order.config.JwtFilter;

@EnableFeignClients
//@EnableHystrixDashboard
@EnableTransactionManagement
//@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class OrderingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderingserviceApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean jwtFilterBean() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/order/*");

		return registrationBean;
	}

}
