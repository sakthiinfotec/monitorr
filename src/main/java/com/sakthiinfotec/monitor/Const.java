package com.sakthiinfotec.monitor;

/**
 * Interface to hold application constants
 * 
 * @author Sakthi
 */
public interface Const {

	/* Timeout configurations */
	byte MONITOR_MILLISECONDS = 1;
	short MONITOR_SECONDS = 1000;
	int MONITOR_MINUTES = MONITOR_SECONDS * 60;

	/* Component names */
	String HOSTS = "hosts";
	String SERVER_COMPONENTS = "server-components";
	String SERVICE_COMPONENTS = "service-components";

	/* Notification message properties */
	String LOGGED_AT = "logged_at";
	String SOURCE = "source";
	String FACILITY = "facility";
	String ORG = "org";
	String MESSAGE = "message";
	String META = "meta";
	String SERR = "SERR";
	String SKEY = "SKEY";
	String INGRESS = "ingress";
	String NOTIFICATION = "notification";

	String DOWN = "down";
	String FSLASH = "/";
}
