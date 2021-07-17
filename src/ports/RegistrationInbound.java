package ports;

import java.util.Set;

import components.ConnectionInfo;
import components.SimulatorComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.P2PAddressI;
import interfaces.PositionI;
import interfaces.RegistrationCI;

public class RegistrationInbound extends AbstractInboundPort implements RegistrationCI  {
	private static final long serialVersionUID = 1L;

	public RegistrationInbound(ComponentI owner) throws Exception
	{
		super(RegistrationCI.class, owner);
		assert(owner instanceof SimulatorComponent);
	}
	
	public RegistrationInbound(String uri, ComponentI owner) throws Exception
	{
		super(uri, RegistrationCI.class, owner);
		assert(owner instanceof SimulatorComponent);
	}
	
	@Override
	public Set<ConnectionInfo> registrationInternal(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
		return this.getOwner().handleRequest(c -> {
			return ((SimulatorComponent) c).registerTerminalNode(address, communicationInboundPort, initialPosition, initialRange, routingInboundPortURI );
		});
	}

	@Override
	public Set<ConnectionInfo> registrationAccessPoint(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
		return this.getOwner().handleRequest(c -> {
			return ((SimulatorComponent) c).registerAccessPoint(address, communicationInboundPort, initialPosition, initialRange, routingInboundPortURI);
		});
	}
	
	@Override
	public void unregister(P2PAddressI address) throws Exception 
	{
		this.getOwner().handleRequest(c -> {
			((SimulatorComponent) c).unregister(address); 
			return null;
		});
	}

}
