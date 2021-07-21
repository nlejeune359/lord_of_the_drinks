package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.interfaces.RequiredCI;

public interface CommunicationCI extends RequiredCI, OfferedCI{

	/**
	 * Received a connect request of another component
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
	 * Called when m is received
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
