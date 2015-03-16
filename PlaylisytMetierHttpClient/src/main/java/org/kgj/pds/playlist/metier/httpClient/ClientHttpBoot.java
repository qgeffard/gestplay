package org.kgj.pds.playlist.metier.httpClient;
import org.kgj.pds.playlist.metier.messagingService.ClientHttpMessagingServiceManager;

/**
 * Class used to init all ClientHttp side
 * @author Doox
 *
 */
public class ClientHttpBoot 
{
	
    public static void main( String[] args )
    {
        startMessagingService();
        //...
    }
    
    /**
     * Demarre le service de messagerie grace a la methode getInstance pour creer le singleton
     */
	private static void startMessagingService() {
		ClientHttpMessagingServiceManager.getInstance();
	}


	
}
