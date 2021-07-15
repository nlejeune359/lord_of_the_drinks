import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.interfaces.RequiredCI;

public interface CommunicationCI extends RequiredCI, OfferedCI{
	public void connect(
		P2PAddressI addresss, 
		String communicationInboundPortURI, 
		String routingInboundPortURI
	);
	public void routeMessage(MessageI m);
	public void ping();
}
