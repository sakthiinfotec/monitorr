package com.sakthiinfotec.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sakthiinfotec.monitor.config.AppConfiguration;
import com.sakthiinfotec.monitor.config.ServiceComponent;

/**
 * Monitors list of given services running or not
 * 
 * @author Sakthi
 */
@Component
public class ServiceComponentMonitor extends ComponentMonitor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceComponentMonitor.class.getSimpleName());

	/**
	 * Constructor to initialize it's super class with it's component type
	 */
	@Autowired
	public ServiceComponentMonitor(final AppConfiguration config) {
		super(Const.SERVICE_COMPONENTS);
	}

	/**
	 * Monitors a set of background services
	 */
	@Override
	public void monitor() {
		boolean serviceDown;
		String cause = null;
		String serviceDesc = null;
		String host = null;
		String name = null;
		List<ServiceComponent> serviceComponents = getConfig().getComponents().getServiceComponents();
		for (ServiceComponent service : serviceComponents) {
			serviceDown = true;
			host = service.getHost();
			name = service.getName();
			serviceDesc = "" + service.getDescription() + "@" + host;
			LOGGER.debug("[service-component] Trying to connect " + serviceDesc);
			BufferedReader reader = null;
			try {
				Process process = Runtime.getRuntime().exec("service " + name + " status");
				reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					// Unix's grep like, check a running status of a service
					if (line.indexOf(getConfig().getMonitorSettings().getServiceRunningStatusString()) > -1) {
						serviceDown = false;
						break;
					}
				}
				process.waitFor();
			} catch (IOException | InterruptedException e) {
				serviceDown = true;
				cause = e.getMessage();
			} finally {
				Utils.closeReader(reader);
				String message = null;
				if (serviceDown) {
					message = "Unable to connect service \"" + serviceDesc + "\"";
					message = (null == cause) ? message : message + ". Reason: " + cause;
					markComponentDown(name + "@" + host, message);
				} else {
					message = "Service " + serviceDesc + " is up and running";
					markComponentUp(name + "@" + host, message);
				}
			}
		}
	}

}
