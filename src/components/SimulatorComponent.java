package components;

import java.util.HashSet;
import java.util.Set;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import interfaces.PositionI;
import interfaces.RegistrationCI;
import ports.RegistrationInbound;
import interfaces.P2PAddressI;

@OfferedInterfaces(offered = {RegistrationCI.class})
public class SimulatorComponent extends AbstractComponent 
{
	protected RegistrationInbound inboundPort;
	public static final String REGISTRATIONNODEINBOUNDPORTURI = "rip-uri";
	
	private Set<ConnectionInfo> node = new HashSet<>();

	protected SimulatorComponent(int nbThreads, int nbSchedulableThreads) throws Exception {
		super(nbThreads, nbSchedulableThreads);
		this.inboundPort = new RegistrationInbound(REGISTRATIONNODEINBOUNDPORTURI, this);
		this.inboundPort.publishPort();
	}
	
	public synchronized Set<ConnectionInfo> registerTerminalNode(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception
	{
		ConnectionInfo ci = new ConnectionInfo(
			address, 
			communicationInboundPort, 
			routingInboundPortURI, 
			initialPosition,
			initialRange,
			NodeType.INTERNAL
		);
		return this.getInPortee(ci);	
	}

	public synchronized Set<ConnectionInfo> registerAccessPoint(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception
	{
		ConnectionInfo ci = new ConnectionInfo(
			address, 
			communicationInboundPort, 
			routingInboundPortURI, 
			initialPosition,
			initialRange,
			NodeType.ACCESSPOINT
		);		
		return this.getInPortee(ci);
	}
	
	private synchronized Set<ConnectionInfo> getInPortee(ConnectionInfo ci) throws Exception {
		Set<ConnectionInfo> res = new HashSet<ConnectionInfo>();
		for(ConnectionInfo c : this.node) 
		{
			if(ci.getPos().distance(c.getPos()) <= ci.getRange()) {
				res.add(c);
			}
		}
		node.add(ci);
		return res;
	}
	
	public synchronized void unregister(P2PAddressI address) throws Exception
	{
		for(ConnectionInfo c : this.node)
		{
			if(c.getAddress().equals(address)) {
				node.remove(c);
				break;
			}
		}
	}	
	
	@Override
	public synchronized void shutdown() throws ComponentShutdownException
	{
		try {
			this.inboundPort.unpublishPort();
		}  catch (Exception e) {
			throw new ComponentShutdownException(e);
		}
		super.shutdown();
	}
}
