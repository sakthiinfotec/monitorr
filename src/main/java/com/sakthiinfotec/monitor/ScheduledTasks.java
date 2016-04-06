package com.sakthiinfotec.monitor;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sakthiinfotec.monitor.config.AppConfiguration;

/**
 * Spring scheduled tasks class
 * 
 * @author Sakthi
 */
@Component
public class ScheduledTasks {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class.getSimpleName());

	@Autowired
	private AppConfiguration appConfig;

	@Autowired
	private MonitorFactory factory;

	@PostConstruct
	public void init() {
		List<String> componentsList = appConfig.getMonitorSettings().getMonitoringEnabledComponents();
		LOGGER.info("Enabled components: " + componentsList);
		if (componentsList.size() == 0) {
			LOGGER.error("\nThere is no component enabled to monitor it seems!."
					+ "\nPlease enable the components by adding/uncommenting under monitor-settings->monitoring-enabled-components"
					+ " property in application.yml configuration file.\nExiting application for now :-).");
			System.exit(-1);
		}
	}

	/**
	 * Scheduled method starts component monitoring threads periodically
	 */
	@Scheduled(fixedRate = Const.MONITOR_MINUTES * 2)
	public void monitorComponents() {
		ComponentMonitor componentMonitor;
		for (String component : appConfig.getMonitorSettings().getMonitoringEnabledComponents()) {
			LOGGER.debug("Monitoring: " + component);
			componentMonitor = factory.createComponentMonitor(component);
			new ComponentMonitorThread(componentMonitor).start();
		}
	}

}
