package com.sakthiinfotec.monitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A utility class
 * 
 * @author Sakthi
 */
public final class Utils {

	/**
	 * Closes reader object
	 * 
	 * @param reader
	 */
	public static void closeReader(Reader reader) {
		if(null != reader)
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Closes input stream object
	 * 
	 * @param inputStream
	 */
	public static void closeInputStream(InputStream inputStream) {
		if(null != inputStream)
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Closes server socket object
	 * 
	 * @param socket
	 */
	public static void closeSocket(ServerSocket socket) {
		if (null != socket)
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Closes client socket object
	 * 
	 * @param socket
	 */
	public static void closeSocket(Socket socket) {
		if (null != socket)
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Creates a JSON message
	 * 
	 * @param message
	 * @return {@link JsonNode}
	 */
	public static JsonNode createMessage(final String message) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		long currentTime = calendar.getTimeInMillis() / 1000;
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();
		((ObjectNode) rootNode).put(Const.LOGGED_AT, currentTime);
		((ObjectNode) rootNode).put(Const.FACILITY, "cbe");
		((ObjectNode) rootNode).put(Const.ORG, "sakthiinfotec");
		((ObjectNode) rootNode).put(Const.SOURCE, Const.NOTIFICATION);
		((ObjectNode) rootNode).put(Const.META, Const.SERR);
		((ObjectNode) rootNode).put(Const.SKEY, Const.INGRESS);
		((ObjectNode) rootNode).put(Const.MESSAGE, message);
		return rootNode;
	}

}
