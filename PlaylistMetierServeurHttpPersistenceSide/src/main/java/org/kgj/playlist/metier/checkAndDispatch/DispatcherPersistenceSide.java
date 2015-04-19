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
		String playlist = "<ns2:playlist version=\"1\"><ns2:title>title</ns2:title><ns2:creator>foo</ns2:creator><ns2:annotation>annotation</ns2:annotation><ns2:info>http://tempuri.org</ns2:info><ns2:location>http://tempuri.org</ns2:location><ns2:identifier>http://tempuri.org</ns2:identifier><ns2:image>http://tempuri.org</ns2:image><ns2:date>2001-12-31T12:00:00</ns2:date><ns2:license>http://tempuri.org</ns2:license><ns2:attribution><ns2:identifier>http://tempuri.org</ns2:identifier></ns2:attribution><ns2:link rel=\"http://tempuri.org\">http://tempuri.org</ns2:link><ns2:meta rel=\"http://tempuri.org\">meta</ns2:meta><ns2:extension application=\"http://tempuri.org\" /><ns2:trackList><ns2:track><ns2:location>file:///C:/music/foo.mp3</ns2:location><ns2:title>Windows Path</ns2:title><ns2:creator>Creator after</ns2:creator><ns2:album>Album first</ns2:album><ns2:duration>2976018</ns2:duration></ns2:track><ns2:track><ns2:location>file:///media/music/foo.mp3</ns2:location><ns2:title>Linux Path</ns2:title><ns2:creator>Creator after</ns2:creator><ns2:album>Album first</ns2:album><ns2:duration>2976018</ns2:duration></ns2:track><ns2:track><ns2:location>music/foo.mp3</ns2:location><ns2:title>Relative Path</ns2:title><ns2:creator>Creator after</ns2:creator><ns2:album>Album first</ns2:album><ns2:duration>2976018</ns2:duration></ns2:track><ns2:track><ns2:location>http://www.example.com/music/bar.ogg</ns2:location><ns2:title>External Example</ns2:title><ns2:creator>Creator after</ns2:creator><ns2:album>Album first</ns2:album><ns2:duration>2976018</ns2:duration></ns2:track></ns2:trackList></ns2:playlist>";
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
