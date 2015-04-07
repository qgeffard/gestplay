package org.kgj.pds.playlist.metier;

import org.kgj.pds.playlist.metier.messagingService.ClientHttpMessagingServiceManager;

public class MockedView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xml = "<?xml version='1.0' encoding='UTF-8' ?><xspf:query xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xspf=\"http://xspf.org/ns/0/\" xsi:schemaLocation=\"http://xspf.org/ns/0/ messageViewController.xsd\"><queryId>token</queryId><responseId>token</responseId><userManager><connectedToken>token</connectedToken></userManager><status><succed>succed</succed></status><action><idAction>token</idAction><nameAction>undo</nameAction></action><xspf:playlist version=\"\"><xspf:title>xspf:title</xspf:title><xspf:creator>xspf:creator</xspf:creator><xspf:annotation>xspf:annotation</xspf:annotation><xspf:info>http://tempuri.org</xspf:info><xspf:location>http://tempuri.org</xspf:location><xspf:identifier>http://tempuri.org</xspf:identifier><xspf:image>http://tempuri.org</xspf:image><xspf:date>2001-12-31T12:00:00</xspf:date><xspf:license>http://tempuri.org</xspf:license><xspf:attribution></xspf:attribution><xspf:link rel=\"http://tempuri.org\">http://tempuri.org</xspf:link><xspf:meta rel=\"http://tempuri.org\">xspf:meta</xspf:meta><xspf:extension application=\"http://tempuri.org\"></xspf:extension><xspf:trackList></xspf:trackList></xspf:playlist></xspf:query>";
		ClientHttpMessagingServiceManager.getInstance().send(xml);
	}

}
