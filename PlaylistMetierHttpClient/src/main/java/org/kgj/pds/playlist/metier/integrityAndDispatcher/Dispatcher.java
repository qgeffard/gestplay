package org.kgj.pds.playlist.metier.integrityAndDispatcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.kgj.pds.playlist.metier.generated.Query;

public class Dispatcher {

	public void sendToWS(Query query) {
		// TODO Auto-generated method stub
		try {
			URLConnection connection = new URL("http://localhost:8080/PlaylistMetierServeurHttp/serveurHttp?action="+query.getAction().getNameAction()).openConnection();
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setDoOutput(true);
			
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.metier.generated");
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

	public void sendToView(Query query) {
		// TODO Auto-generated method stub
		
	}


}
