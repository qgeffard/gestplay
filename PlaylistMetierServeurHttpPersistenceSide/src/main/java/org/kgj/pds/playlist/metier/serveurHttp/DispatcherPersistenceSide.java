package org.kgj.pds.playlist.metier.serveurHttp;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.messagingProtocol.Query;
import org.kgj.pds.playlist.metier.messagingService.ServeurHttpPersistenceSideMessagingServiceManager;

public class DispatcherPersistenceSide {
	private static final Logger logger = Logger.getLogger(DispatcherPersistenceSide.class);
	
	public final String WEBAPP_PROTOCOL = "http://";
	public final String WEBAPP_VS_HOSTNAME = "localhost";
	public final String WEBAPP_VS_PORT = "8080";
	public final String WEBAPP_VS_SERVLETNAME = "PlaylistMetierServeurHttpViewSide/setMessage";
	
	private String getUrlViewSide(){
		logger.debug("Construct url of Persistence side webapp");
		
		StringBuilder url = new StringBuilder();
		url.append(WEBAPP_PROTOCOL);
		url.append(WEBAPP_VS_HOSTNAME).append(":");
		url.append(WEBAPP_VS_PORT).append("/");
		url.append(WEBAPP_VS_SERVLETNAME);
		
		return url.toString();
	}
	public void sendToPersistence(Query query){
		ServeurHttpPersistenceSideMessagingServiceManager messageManager = ServeurHttpPersistenceSideMessagingServiceManager.getInstance();
		messageManager.send(messageManager.queryToString(query));
	}

	public void sendToVS(Query query) {
		
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(getUrlViewSide());			
		
		post.addParameter("query", ServeurHttpPersistenceSideMessagingServiceManager.getInstance().queryToString(query));
		
		try {
			int statusRequest = client.executeMethod(post);
			
			if (statusRequest != -1) {
				logger.info("Acknowledge http request : "+ post.getResponseBodyAsString());
	        }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}
	
	public void sendToVS(String queryToString) {
		
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(getUrlViewSide());			
		
		post.addParameter("query",queryToString);
		
		try {
			int statusRequest = client.executeMethod(post);
			
			if (statusRequest != -1) {
				logger.info("Acknowledge http request : "+ post.getResponseBodyAsString());
	        }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
