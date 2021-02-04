package com.set.config;

import static org.hibernate.cfg.AvailableSettings.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.AvailableSettings.C3P0_MIN_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_TIMEOUT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;
import java.util.Properties;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.set") })
public class AppConfig {
	@Autowired
	private Environment env;
	private Logger logger = Logger.getLogger("AppConfig.class");

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties props = new Properties();
		// Setting JDBC properties
		props.put(DRIVER, env.getProperty("mysql.driver"));
		props.put(URL, env.getProperty("mysql.url"));
		props.put(USER, env.getProperty("mysql.user"));
		props.put(PASS, env.getProperty("mysql.password"));
		// properties.setProperty("useSSL", "false");
		// Setting Hibernate properties
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

		// Setting C3P0 properties
		props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.set");
		logger.info("hibernate connection");
		/*
		 * factoryBean.setAnnotatedClasses(User.class);
		 * factoryBean.setAnnotatedClasses(TblProfile.class);
		 */
		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		logger.info("hibernate");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}

	@Bean(name = "filterMultipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setMaxUploadSize(maxUploadSizeInMb * 2);
		cmr.setMaxUploadSizePerFile(maxUploadSizeInMb); // bytes
		return cmr;
	}
	// @Bean
	// public CommonsMultipartResolver multipartResolver() {
	// CommonsMultipartResolver multipart = new CommonsMultipartResolver();
	// multipart.setMaxUploadSize(3 * 1024 * 1024);
	// return multipart;
	// }
	//
	// @Bean
	// @Order(0)
	// public MultipartFilter multipartFilter() {
	// MultipartFilter multipartFilter = new MultipartFilter();
	// multipartFilter.setMultipartResolverBeanName("multipartResolver");
	// return multipartFilter;
	// }

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		// Using gmail
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("parnaykumarmishra@gmail.com");
		mailSender.setPassword("8147353831");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");// Prints out everything on screen
		logger.info("in config javaMailProperties");
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

}
