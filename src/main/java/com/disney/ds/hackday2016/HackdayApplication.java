package com.disney.ds.hackday2016;


/**
 * Created by peichen on 3/2/16.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class HackdayApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HackdayApplication.class);
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}


	@Bean
	public ServletRegistrationBean dispatcherRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet());
		registration.addUrlMappings("/");
		return registration;
	}

	public static void main(String[] args) {
		SpringApplication.run(HackdayApplication.class, args);
	}
}

