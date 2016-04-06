package com.sakthiinfotec.monitor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sakthiinfotec.monitor.config.AppConfiguration;
import com.sakthiinfotec.monitor.config.NotificationSettings;

/**
 * TCP Client to send component Down or Up notification in JSON format
 *  
 * @author Sakthi
 */
@Component
public class TCPClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(TCPClient.class.getSimpleName());
	
	@Autowired
	private AppConfiguration config;
	
	/**
	 * Sends notification message to TCP server
	 *  
	 * @param message
	 */
	public void sendMessage(final String message) {
		Socket clientSocket = null;
		NotificationSettings notification = config.getNotificationSettings();
		final String host = notification.getHost();
		int port = notification.getPort();
		try {
			clientSocket = new Socket(host, port);
			DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
			outStream.writeBytes(message + "\r\n");
			outStream.flush();
		} catch (ConnectException ce) {
			LOGGER.error("Error opening connection with server " + host + ":" + port
					+ ". Please check the TCP server is up and running!", ce);
		} catch (IOException ioe) {
			LOGGER.error("Error in communication with server " + host + ":" + port, ioe);
		} finally {
			Utils.closeSocket(clientSocket);
		}
	}

}
