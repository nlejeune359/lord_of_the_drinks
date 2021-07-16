package components;

import java.util.HashSet;
import java.util.Set;

import fr.sorbonne_u.components.AbstractComponent;
import interfaces.PositionI;
import interfaces.RegistrationCI;
import interfaces.P2PAddressI;
import interfaces.ConnectionInfo;

public class SimulatorComponent
extends AbstractComponent 
implements RegistrationCI
{
	
	private Set<ConnectionInfo> node =  new HashSet<ConnectionInfo>();

	protected SimulatorComponent(int nbThreads, int nbSchedulableThreads) {
		super(nbThreads, nbSchedulableThreads);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<ConnectionInfo> registrationInternal(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) {
		node.add(new ConnectionInfo(
			address, 
			communicationInboundPort, 
			routingInboundPortURI, 
			initialPosition,
			initialRange,
			NodeType.INTERNAL
		));
		return this.node;
	}

	@Override
	public Set<ConnectionInfo> registrationAccessPoint(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) {
		// TODO Auto-generated method stub
		return this.node;
	}

	@Override
	public void unregister(P2PAddressI address) {
		for(ConnectionInfo info : this.node) {
			if(info.getAddress().equals(address))
				node.remove(info);
		}
		
	}
	
	
}
