package com.sakthiinfotec.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sakthiinfotec.monitor.config.AppConfiguration;

/**
 * Main spring scheduling application
 * 
 * @author Sakthi
 */
@SpringBootApplication
@EnableScheduling
public class Application {

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(new Object[] { Application.class, BeanConfigs.class }, args);
	}
}

/**
 * Bean configurations
 * 
 * @author Sakthi
 */
@Configuration
class BeanConfigs {

	@Bean
	@ConfigurationProperties
	public AppConfiguration config() {
		return new AppConfiguration();
	}
}