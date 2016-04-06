package com.sakthiinfotec.monitor.config;

/**
 * Server component configuration
 * 
 * @author Sakthi
 */
public class ServerComponent {
	private String host;
	private String description;
	private int port;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
