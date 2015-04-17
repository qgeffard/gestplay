package org.kgj.playlist.metier.checkAndDispatch;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.messagingProtocol.Query;
import org.kgj.pds.playlist.metier.messagingService.ServeurHttpPersistenceSideMessagingServiceManager;

public class DispatcherPersistenceSide {
	private static final Logger logger = Logger
			.getLogger(DispatcherPersistenceSide.class);

	public final String WEBAPP_PROTOCOL = "http://";
	public final String WEBAPP_VS_HOSTNAME = "localhost";
	public final String WEBAPP_VS_PORT = "8080";
	public final String WEBAPP_VS_SERVLETNAME = "PlaylistMetierServeurHttpViewSide/setMessage";

	public void dispatch(Query query) {

		if (query.getAction().getNameAction().equals("test")) {
			sendMockedPersistRepToVS(query);
		} else {
			sendToPersistence(query);
		}
	}

	private void sendMockedPersistRepToVS(Query query) {
		String queryString = ServeurHttpPersistenceSideMessagingServiceManager.getInstance().queryToString(query);
		String playlist = "<playlist version=\"1\"><title>title</title><creator>foo</creator><annotation>annotation</annotation><info>http://tempuri.org</info><location>http://tempuri.org</location><identifier>http://tempuri.org</identifier><image>http://tempuri.org</image><date>2001-12-31T12:00:00</date><license>http://tempuri.org</license><attribution><identifier>http://tempuri.org</identifier></attribution><link rel=\"http://tempuri.org\">http://tempuri.org</link><meta rel=\"http://tempuri.org\">meta</meta><extension application=\"http://tempuri.org\" /><trackList><track><location>file:///C:/music/foo.mp3</location><title>Windows Path</title><creator>Creator after</creator><album>Album first</album><duration>2976018</duration></track><track><location>file:///media/music/foo.mp3</location><title>Linux Path</title><creator>Creator after</creator><album>Album first</album><duration>2976018</duration></track><track><location>music/foo.mp3</location><title>Relative Path</title><creator>Creator after</creator><album>Album first</album><duration>2976018</duration></track><track><location>http://www.example.com/music/bar.ogg</location><title>External Example</title><creator>Creator after</creator><album>Album first</album><duration>2976018</duration></track></trackList></playlist>";
		logger.warn(queryString);
		logger.warn(playlist);
		
		String finale = queryString.split("</ns2:query>")[0].concat(playlist).concat("</ns2:query>").toString();
		
		sendToVS(finale);
	}

	private String getUrlViewSide() {
		logger.debug("Construct url of Persistence side webapp");

		StringBuilder url = new StringBuilder();
		url.append(WEBAPP_PROTOCOL);
		url.append(WEBAPP_VS_HOSTNAME).append(":");
		url.append(WEBAPP_VS_PORT).append("/");
		url.append(WEBAPP_VS_SERVLETNAME);

		return url.toString();
	}

	public void sendToPersistence(Query query) {
		ServeurHttpPersistenceSideMessagingServiceManager messageManager = ServeurHttpPersistenceSideMessagingServiceManager
				.getInstance();
		messageManager.send(messageManager.queryToString(query));
	}

	public void sendToVS(Query query) {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(getUrlViewSide());

		post.addParameter("query",
				ServeurHttpPersistenceSideMessagingServiceManager.getInstance()
						.queryToString(query));

		try {
			int statusRequest = client.executeMethod(post);

			if (statusRequest != -1) {
				logger.info("Acknowledge http request : "
						+ post.getResponseBodyAsString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

	public void sendToVS(String queryToString) {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(getUrlViewSide());

		post.addParameter("query", queryToString);

		try {
			int statusRequest = client.executeMethod(post);

			if (statusRequest != -1) {
				logger.info("Acknowledge http request : "
						+ post.getResponseBodyAsString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
