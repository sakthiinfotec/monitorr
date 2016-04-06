package com.sakthiinfotec.monitor;

/**
 * A monitor factory interface
 *  
 * @author Sakthi
 */
public interface MonitorFactoryInterface {
	
	/**
	 * An method to create monitoring target
	 * 
	 * @param componentMonitor
	 * @return ComponentMonitor
	 */
	ComponentMonitor createComponentMonitor(String componentMonitor);
}
