package com.sakthiinfotec.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sakthiinfotec.monitor.BeanConfigs;

/**
 * @author Sakthi
 */
@EnableAutoConfiguration
public class AppConfigurationTest {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext ctx = SpringApplication
				.run(new Object[] { AppConfigurationTest.class, BeanConfigs.class }, args);
//		AppConfiguration appConfig = ctx.getBean(AppConfiguration.class);
//		System.out.println("Components:-");
//		for (String component : appConfig.getMonitorSettings().getMonitoringEnabledComponents()) {
//			System.out.println("\tComponent:" + component);
//		}
//		
//		System.out.println("Host List:" + appConfig.getComponents().getHosts());
//		
//		 System.out.println("Server Components:-");
//		 for(AppConfiguration.Components.ServerComponent scm :
//			 appConfig.getComponents().getServerComponents()) {
//		 System.out.println("\tHost:" + scm.getHost());
//		 System.out.println("\tPort:" + scm.getPort());
//		 System.out.println("\tDesc:" + scm.getDescription());
//		 }
	}
	
}
