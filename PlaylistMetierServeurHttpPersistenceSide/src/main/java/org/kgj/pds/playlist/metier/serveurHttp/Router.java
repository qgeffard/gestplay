package org.kgj.pds.playlist.metier.serveurHttp;

import org.kgj.pds.playlist.metier.messagingService.ServeurHttpPersistenceSideMessagingServiceManager;

public class Router {
	
	public static void sendToPersistence(){
		ServeurHttpPersistenceSideMessagingServiceManager.getInstance().send("");
	}
}
