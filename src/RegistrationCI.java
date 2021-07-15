import java.util.Set;

import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.interfaces.RequiredCI;

public interface RegistrationCI extends RequiredCI, OfferedCI	{
	public Set<ConnectionInfo> registrationInternal(
		P2PAddressI address,
		String communicationInboundPort,
		PositionI initialPosition,
		double initialRange,
		String routingInboundPortURI
	);
	public Set<ConnectionInfo> registrationAccessPoint(
		P2PAddressI address,
		String communicationInboundPort,
		PositionI initialPosition,
		double initialRange,
		String routingInboundPortURI
	);
	public void unregister(P2PAddressI address);
}
