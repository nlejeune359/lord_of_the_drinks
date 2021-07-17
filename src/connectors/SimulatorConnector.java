package connectors;

import java.util.Set;

import components.ConnectionInfo;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.P2PAddressI;
import interfaces.PositionI;
import interfaces.RegistrationCI;

public class SimulatorConnector extends AbstractConnector implements RegistrationCI {

	@Override
	public Set<ConnectionInfo> registrationInternal(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
		return ((RegistrationCI) this.offering).registrationInternal(address, communicationInboundPort, initialPosition, initialRange, routingInboundPortURI);
	}

	@Override
	public Set<ConnectionInfo> registrationAccessPoint(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
		return ((RegistrationCI) this.offering).registrationAccessPoint(address, communicationInboundPort, initialPosition, initialRange, routingInboundPortURI);
	}

	@Override
	public void unregister(P2PAddressI address) throws Exception {
		((RegistrationCI)this.offering).unregister(address);
	}

}
