package com.sakthiinfotec.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A factory class to create a component to be monitored
 * 
 * @author Sakthi
 */
@Component
public class MonitorFactory implements MonitorFactoryInterface {

	@Autowired
	private HostMonitor hostMonitor;

	@Autowired
	private ServerComponentMonitor serverComponentMonitor;

	@Autowired
	private ServiceComponentMonitor serviceComponentMonitor;

	/**
	 * A factory method to create an instance for monitoring component
	 * 
	 * @return {@link ComponentMonitor}
	 */
	@Override
	public ComponentMonitor createComponentMonitor(final String component) {
		ComponentMonitor target = null;
		switch (component) {
		case Const.HOSTS:
			target = hostMonitor;
			break;
		case Const.SERVER_COMPONENTS:
			target = serverComponentMonitor;
			break;
		case Const.SERVICE_COMPONENTS:
			target = serviceComponentMonitor;
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid component to monitor. Could not create type \'" + component + "\'");
		}
		return target;
	}

}
