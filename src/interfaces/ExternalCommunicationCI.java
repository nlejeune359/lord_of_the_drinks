package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.interfaces.RequiredCI;

/**
 * Interface de communication entre points d acces et elements externes au reseau pair a pair
 */
public interface ExternalCommunicationCI extends RequiredCI, OfferedCI {

	/**
	 * Envoie de message avec un element externe
	 * 
	 * @param m
	 * @throws Exception
	 */
    public void routeExternalMessage(MessageI m) throws Exception;

    /**
     * Connexion avec un element externe
     * 
     * @param address
     * @param communicationInboundPortURI
     * @throws Exception
     */
    public void connectExternal(AddressI address, String communicationInboundPortURI) throws Exception;
}
