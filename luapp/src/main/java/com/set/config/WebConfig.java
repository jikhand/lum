package com.set.config;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.set" })
public class WebConfig extends WebMvcConfigurerAdapter {
	private Logger logger = Logger.getLogger("WebConfig.class");

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/info/**").allowedOrigins("Access-Control-Allow-Origin", "*")
				.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
				.allowedHeaders("X-Auth-Token", "Content-Type").exposedHeaders("custom-header1", "custom-header2")
				.allowCredentials(false).maxAge(4800);
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		logger.info("Message converter");
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jsonMessageConverter = (MappingJackson2HttpMessageConverter) converter;
				ObjectMapper objectMapper = jsonMessageConverter.getObjectMapper();
				objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
				break;
			}
		}
	}

	@Bean
	public InternalResourceViewResolver resolver() {
		logger.info("view resolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
