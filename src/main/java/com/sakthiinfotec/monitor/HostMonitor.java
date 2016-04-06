package com.sakthiinfotec.monitor;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sakthiinfotec.monitor.config.AppConfiguration;
import com.sakthiinfotec.monitor.config.HostComponent;

/**
 * Monitors list of given hosts whether reachable or not
 * 
 * @author Sakthi
 */
@Component
public class HostMonitor extends ComponentMonitor {

	private static final Logger LOGGER = LoggerFactory.getLogger(HostMonitor.class.getSimpleName());

	/**
	 * Constructor to initialize it's super class with it's component type
	 */
	@Autowired
	public HostMonitor(final AppConfiguration config) {
		super(Const.HOSTS);
	}

	/**
	 * Monitors a set of hosts
	 */
	@Override
	public void monitor() {
		boolean serverDown;
		String cause = null;
		List<HostComponent> hosts = getConfig().getComponents().getHostComponents();
		for (HostComponent hc : hosts) {
			serverDown = false;
			LOGGER.debug("[host-component] Trying to connect " + hc.getHost() + "@" + hc.getLocation());
			try {
				if (!InetAddress.getByName(hc.getHost())
						.isReachable(getConfig().getMonitorSettings().getComponentConnectionTimeout())) {
					serverDown = true;
					cause = "Host not reachable";
				}
			} catch (IOException e) {
				serverDown = true;
				cause = e.getMessage();
			} finally {
				String message = null;
				if (serverDown) {
					message = "Unable to connect host \"" + hc.getHost() + "(" + hc.getDescription() + ")\" located at "
							+ hc.getLocation() + ". Reason: " + cause;
					markComponentDown(hc.getHost(), message);
				} else {
					message = "Host \"" + hc + "\" is up and running";
					markComponentUp(hc.getHost(), message);
				}
			}
		}
	}

}
