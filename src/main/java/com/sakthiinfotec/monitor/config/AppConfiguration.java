package com.sakthiinfotec.monitor.config;

/**
 * Application configuration
 * 
 * @author Sakthi
 */
public class AppConfiguration {

	private Components components;
	private MonitorSettings monitorSettings;
	private NotificationSettings notificationSettings;

	public Components getComponents() {
		return components;
	}

	public void setComponents(Components components) {
		this.components = components;
	}

	public MonitorSettings getMonitorSettings() {
		return monitorSettings;
	}

	public void setMonitorSettings(MonitorSettings monitorSettings) {
		this.monitorSettings = monitorSettings;
	}

	public NotificationSettings getNotificationSettings() {
		return notificationSettings;
	}

	public void setNotificationSettings(NotificationSettings notificationSettings) {
		this.notificationSettings = notificationSettings;
	}

}
