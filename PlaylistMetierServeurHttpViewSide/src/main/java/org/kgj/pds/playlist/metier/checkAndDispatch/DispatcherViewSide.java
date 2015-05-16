package org.kgj.pds.playlist.metier.checkAndDispatch;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.activemq.transport.http.HttpClientTransport;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.messagingProtocol.Query;
import org.kgj.pds.playlist.metier.messagingService.ServeurHttpViewSideMessagingServiceManager;

public class DispatcherViewSide {
	
	private static final Logger logger = Logger.getLogger(DispatcherViewSide.class);
	
	
	public final String WEBAPP_PROTOCOL = "http://";
	public final String WEBAPP_PS_HOSTNAME = "localhost";
	public final String WEBAPP_PS_PORT = "8080";
	public final String WEBAPP_PS_SERVLETNAME = "PlaylistMetierServeurHttpPersistenceSide/setMessage";
	

	public void sendToPS(Query query, Message message) throws JMSException {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(getUrlPersistenceSide(query,message));			
		
		post.addParameter("query", ServeurHttpViewSideMessagingServiceManager.getInstance().queryToString(query));
		
		try {
			int statusRequest = client.executeMethod(post);
			
			if (statusRequest != -1) {
				logger.error("Acknowledge http request : "+ post.getResponseBodyAsString());
	        }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private String getUrlPersistenceSide(Query query, Message message) throws JMSException {
		logger.debug("Construct url of Persistence side webapp");
		
		StringBuilder url = new StringBuilder();
		url.append(WEBAPP_PROTOCOL);
		url.append(WEBAPP_PS_HOSTNAME).append(":");
		url.append(WEBAPP_PS_PORT).append("/");
		url.append(WEBAPP_PS_SERVLETNAME);
		
		String actionParam = "action="+query.getAction().getNameAction();
		String idMessageParam = "idmessage="+message.getJMSMessageID();
		
		url.append("?").append(actionParam);
		url.append("&").append(idMessageParam);
		
		return url.toString();
	}

	public void sendToView(Query query, Message message) {
		ServeurHttpViewSideMessagingServiceManager messagingService = ServeurHttpViewSideMessagingServiceManager.getInstance();
		String queryString = messagingService.queryToString(query);
		messagingService.send(queryString);	
	}
	
	public void sendToView(Query query) {
		ServeurHttpViewSideMessagingServiceManager messagingService = ServeurHttpViewSideMessagingServiceManager.getInstance();
		String queryString = messagingService.queryToString(query);
		messagingService.send(queryString);	
	}

}
