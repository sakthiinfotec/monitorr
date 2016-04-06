package com.sakthiinfotec.monitor.config;

import java.util.List;

/**
 * Monitor settings configuration
 * 
 * @author Sakthi
 */
public class MonitorSettings {
	private List<String> monitoringEnabledComponents;
	private int maxContinuousFailureTimes;
	private int componentConnectionTimeout;
	private String serviceRunningStatusString;

	public List<String> getMonitoringEnabledComponents() {
		return monitoringEnabledComponents;
	}

	public void setMonitoringEnabledComponents(List<String> monitoringEnabledComponents) {
		this.monitoringEnabledComponents = monitoringEnabledComponents;
	}

	public int getMaxContinuousFailureTimes() {
		return maxContinuousFailureTimes;
	}

	public void setMaxContinuousFailureTimes(int maxContinuousFailureTimes) {
		this.maxContinuousFailureTimes = maxContinuousFailureTimes;
	}

	public int getComponentConnectionTimeout() {
		return componentConnectionTimeout;
	}

	public void setComponentConnectionTimeout(int componentConnectionTimeout) {
		this.componentConnectionTimeout = componentConnectionTimeout;
	}

	public String getServiceRunningStatusString() {
		return serviceRunningStatusString;
	}

	public void setServiceRunningStatusString(String serviceRunningStatusString) {
		this.serviceRunningStatusString = serviceRunningStatusString;
	}

}
