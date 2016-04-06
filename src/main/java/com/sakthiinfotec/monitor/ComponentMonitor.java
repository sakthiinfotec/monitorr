package com.sakthiinfotec.monitor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sakthiinfotec.monitor.config.AppConfiguration;

/**
 * Tracks the status of component and send down or up notification if the
 * continuous failure reaches
 * {@link AppConfiguration.getMonitorSettings().getMaxContinuousFailureTimes()}.
 * 
 * @author Sakthi
 */
public abstract class ComponentMonitor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentMonitor.class.getSimpleName());

	private String componentType;

	private static Map<String, Integer> downTrackerMap = new ConcurrentHashMap<String, Integer>();

	@Autowired
	private AppConfiguration config;

	@Autowired
	private TCPClient tcpClient;

	/**
	 * Constructor to initialize component type
	 * 
	 * @param componentType
	 */
	public ComponentMonitor(final String componentType) {
		this.componentType = componentType;
	}

	/**
	 * To retrieve component type
	 * 
	 * @return String
	 */
	public String getComponentType() {
		return componentType;
	}

	/**
	 * Return application configuration
	 * 
	 * @return {@link AppConfiguration}
	 */
	public AppConfiguration getConfig() {
		return config;
	}

	/**
	 * Set application configuration
	 */
	public void setConfig(AppConfiguration config) {
		this.config = config;
	}

	/**
	 * Makes a unique key to represent componentType+instance with "down" suffix
	 * 
	 * @param componentInstance
	 * @return String
	 */
	private String makeDownKey(final String componentInstance) {
		return getComponentType() + Const.FSLASH + componentInstance + Const.FSLASH + Const.DOWN;
	}

	/**
	 * Marks a particular component as down and notifies with a message if it
	 * fails continuously given threshold i.e
	 * {@link AppConfiguration.getMonitorSettings().getMaxContinuousFailureTimes()}
	 * .
	 * 
	 * @param componentInstance
	 * @param message
	 */
	public void markComponentDown(final String componentInstance, final String message) {
		final String key = makeDownKey(componentInstance);
		Integer downCount = downTrackerMap.get(key);
		downCount = (downCount == null) ? 0 : downCount;
		if (downCount == config.getMonitorSettings().getMaxContinuousFailureTimes()) {
			sendDownNotification(message);
		} else if (downCount > config.getMonitorSettings().getMaxContinuousFailureTimes()) {
			return;
		}
		downCount += 1;
		downTrackerMap.put(key, downCount);
		LOGGER.error(
				"Component - <" + getComponentType() + "," + componentInstance + "> failed attempt - #" + downCount);
	}

	/**
	 * Marks a particular component as up and notifies the user if it already
	 * failed continuously given threshold i.e
	 * {@link AppConfiguration.getMonitorSettings().getMaxContinuousFailureTimes()}
	 * .
	 * 
	 * @param componentInstance
	 * @param message
	 */
	public void markComponentUp(final String componentInstance, final String message) {
		final String key = makeDownKey(componentInstance);
		final Integer downCount = downTrackerMap.get(key);
		if (null != downCount) {
			downTrackerMap.remove(key);
			if (downCount >= config.getMonitorSettings().getMaxContinuousFailureTimes())
				sendUpNotification(message);
		}
	}

	/**
	 * Sends component down notification
	 * 
	 * @param message
	 */
	public void sendDownNotification(final String message) {
		tcpClient.sendMessage(Utils.createMessage("DownAlert: " + message).toString());
	}

	/**
	 * Sends component up notification
	 * 
	 * @param message
	 */
	public void sendUpNotification(final String message) {
		tcpClient.sendMessage(Utils.createMessage("RESUMED: " + message).toString());
	}

	/**
	 * An abstract method that sub classes need to implement
	 */
	protected abstract void monitor();
}
