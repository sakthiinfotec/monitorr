package com.sakthiinfotec.monitor.config;

import java.util.List;

/**
 * Components configuration
 * 
 * @author Sakthi
 */
public class Components {
	private List<HostComponent> hostComponents;

	private List<ServerComponent> serverComponents;

	private List<ServiceComponent> serviceComponents;

	public List<HostComponent> getHostComponents() {
		return hostComponents;
	}

	public void setHostComponents(List<HostComponent> hostComponents) {
		this.hostComponents = hostComponents;
	}

	public List<ServerComponent> getServerComponents() {
		return serverComponents;
	}

	public void setServerComponents(List<ServerComponent> serverComponents) {
		this.serverComponents = serverComponents;
	}

	public List<ServiceComponent> getServiceComponents() {
		return serviceComponents;
	}

	public void setServiceComponents(List<ServiceComponent> serviceComponents) {
		this.serviceComponents = serviceComponents;
	}
}
