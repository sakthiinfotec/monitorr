package com.sakthiinfotec.monitor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sakthiinfotec.monitor.config.AppConfiguration;
import com.sakthiinfotec.monitor.config.ServerComponent;

/**
 * Monitors list of given servers listening on a port for reachable or not
 * 
 * @author Sakthi
 */
@Component
public class ServerComponentMonitor extends ComponentMonitor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerComponentMonitor.class.getSimpleName());

	/**
	 * Constructor to initialize it's super class with it's component type
	 */
	@Autowired
	public ServerComponentMonitor(final AppConfiguration config) {
		super(Const.SERVER_COMPONENTS);
	}

	/**
	 * Monitors a set of servers listening on a port
	 */
	@Override
	public void monitor() {
		boolean serviceDown;
		String cause = null;
		String serviceDesc = null;
		String host = null;
		int port;
		Socket socket;
		List<ServerComponent> serverComponents = getConfig().getComponents().getServerComponents();
		for (ServerComponent serverComponent : serverComponents) {
			serviceDown = false;
			host = serverComponent.getHost();
			port = serverComponent.getPort();
			serviceDesc = "" + serverComponent.getDescription() + "@" + host + ":" + port;
			LOGGER.debug("[server-component] Trying to connect " + serviceDesc);
			socket = null;
			try {
				socket = new Socket();
				socket.connect(new InetSocketAddress(host, port), getConfig().getMonitorSettings().getComponentConnectionTimeout());
			} catch (IOException e) {
				serviceDown = true;
				cause = e.getMessage();
			} finally {
				Utils.closeSocket(socket);
				String message = null;
				if (serviceDown) {
					message = "Unable to connect service \"" + serviceDesc + "\"";
					message = (null == cause) ? message : message + ". Reason: " + cause;
					markComponentDown(host + ":" + port, message);
				} else {
					message = "Server " + serviceDesc + " is up and running";
					markComponentUp(host + ":" + port, message);
				}
			}
		}
	}

}
