package com.sakthiinfotec.monitor;

/**
 * Component monitoring thread class.
 * 
 * @author Sakthi
 */
public class ComponentMonitorThread extends Thread {

	private ComponentMonitor componentMonitor;

	/**
	 * Constructor initialize the component to be monitored.
	 * 
	 * @param componentMonitor
	 */
	public ComponentMonitorThread(final ComponentMonitor componentMonitor) {
		this.componentMonitor = componentMonitor;
	}

	/**
	 * Initiate monitor thread.
	 */
	@Override
	public void run() {
		componentMonitor.monitor();
	}

}
