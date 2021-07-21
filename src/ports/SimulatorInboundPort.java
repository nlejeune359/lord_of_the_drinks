package ports;

import java.util.Set;

import components.ConnectionInfo;
import components.SimulatorComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.P2PAddressI;
import interfaces.PositionI;
import interfaces.RegistrationCI;

public class SimulatorInboundPort extends AbstractInboundPort implements RegistrationCI  {
	private static final long serialVersionUID = 1L;

	public SimulatorInboundPort(ComponentI owner) throws Exception
	{
		super(RegistrationCI.class, owner);
		assert(owner instanceof SimulatorComponent);
	}
	
	public SimulatorInboundPort(String uri, ComponentI owner) throws Exception
	{
		super(uri, RegistrationCI.class, owner);
		assert(owner instanceof SimulatorComponent);
	}
	
	@Override
	public Set<ConnectionInfo> registrationInternal(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
		return this.getOwner().handleRequest(c -> {
			return ((SimulatorComponent) c).registrationInternal(address, communicationInboundPort, initialPosition, initialRange, routingInboundPortURI );
		});
	}

	@Override
	public Set<ConnectionInfo> registrationAccessPoint(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
		return this.getOwner().handleRequest(c -> {
			return ((SimulatorComponent) c).registrationAccessPoint(address, communicationInboundPort, initialPosition, initialRange, routingInboundPortURI);
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
