package org.kgj.pds.playlist.Utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.kgj.pds.playlist.persistance.messagingProtocol.Query;

public class QueryMarshaller {
	
	public static Query stringToQuery(String queryString) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext
					.newInstance("org.kgj.pds.playlist.persistance.messagingProtocol");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (Query) unmarshaller
					.unmarshal(new StringReader(queryString));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String queryToString(Query query) {
		JAXBContext jaxbContext;

		StringWriter wrt = new StringWriter();
		try {
			jaxbContext = JAXBContext
					.newInstance("org.kgj.pds.playlist.persistance.messagingProtocol");
			Marshaller mar = jaxbContext.createMarshaller();
			mar.marshal(query, wrt);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return wrt.toString();
	}
	
}
