package org.kgj.pds.playlist.metier.checkAndDispatch;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.kgj.pds.playlist.metier.messagingProtocol.Query;

@SuppressWarnings("restriction")
public class Dispatcher {
	public final String WEBAPP_PROTOCOL = "http://";
	public final String WEBAPP_PS_HOSTNAME = "localhost";
	public final String WEBAPP_PS_PORT = "8080";
	public final String WEBAPP_PS_SERVLETNAME = "PlaylistMetierServeurHttpPersistenceSide/setmessage";
	

	public void sendToWS(Query query, Message message) throws JMSException {
		// TODO Auto-generated method stub
		try {
			StringBuilder url = new StringBuilder();
			url.append(WEBAPP_PROTOCOL);
			url.append(WEBAPP_PS_HOSTNAME);
			url.append(WEBAPP_PS_PORT);
			url.append(WEBAPP_PS_SERVLETNAME);
			
			String actionParam = "action="+query.getAction().getNameAction();
			String idMessageParam = "idmessage="+message.getJMSMessageID();
			
			url.append("?").append(actionParam);
			url.append("&").append(idMessageParam);
			
			URLConnection connection = new URL(url.toString()).openConnection();
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setDoOutput(true);
			
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.metier.messagingProtocol");
				Marshaller mar = jaxbContext.createMarshaller();
				mar.marshal(query, connection.getOutputStream());
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			InputStream response = connection.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendToView(Query query, Message message) {
		// TODO Auto-generated method stub

	}

}
