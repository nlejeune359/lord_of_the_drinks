package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.interfaces.RequiredCI;

/**
 * Interface de communication entre les elements du reseau pair a pair
 */
public interface CommunicationCI extends RequiredCI, OfferedCI{

	/**
	 * Connect to another component
	 *
	 * @param address
	 * @param communicationInboundPortURI
	 * @param routingInboundPortURI
	 * @throws Exception
	 */
	public void connect(
		P2PAddressI address,
		String communicationInboundPortURI, 
		String routingInboundPortURI
	) throws Exception;

	/**
	 * Send message m
	 * @param m
	 * @throws Exception
	 */
	public void routeMessage(MessageI m) throws Exception;

	/**
	 * ping request
	 * @throws Exception
	 */
	public void ping() throws Exception;
}
