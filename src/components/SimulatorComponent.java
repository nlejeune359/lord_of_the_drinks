package components;

import java.util.HashSet;
import java.util.Set;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import interfaces.PositionI;
import interfaces.RegistrationCI;
import ports.SimulatorInboundPort;
import interfaces.P2PAddressI;

/**
 * Simulateur du reseau pair a pair
 * Enregistre tous les noeuds du reseau et leur indique leur liste de voisins
 */
@OfferedInterfaces(offered = {RegistrationCI.class})
public class SimulatorComponent extends AbstractComponent implements RegistrationCI
{
	protected SimulatorInboundPort inboundPort;
	public static final String REGISTRATION_NODE_INBOUND_PORT_URI = "s-in-uri";
	private Set<SimulatorConnectionInfo> node = new HashSet<>();

	protected SimulatorComponent() throws Exception {
		super(1, 0);
		this.inboundPort = new SimulatorInboundPort(REGISTRATION_NODE_INBOUND_PORT_URI, this);
		this.inboundPort.publishPort();
	}

	
	@Override
	public synchronized Set<ConnectionInfo> registrationInternal(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception
	{
		SimulatorConnectionInfo ci = new SimulatorConnectionInfo(
			address,
			communicationInboundPort, 
			routingInboundPortURI, 
			initialPosition,
			initialRange,
			SimulatorConnectionInfo.NodeType.INTERNAL
		);
		return this.getNeighbours(ci);
	}

	@Override
	public synchronized Set<ConnectionInfo> registrationAccessPoint(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception
	{
		SimulatorConnectionInfo ci = new SimulatorConnectionInfo(
			address, 
			communicationInboundPort, 
			routingInboundPortURI, 
			initialPosition,
			initialRange,
			SimulatorConnectionInfo.NodeType.ACCESS_POINT
		);		
		return this.getNeighbours(ci);
	}
	
	/**
	 * @param ci
	 * @return liste voisins a portee de ci
	 * 
	 * @throws Exception
	 */
	private synchronized Set<ConnectionInfo> getNeighbours(SimulatorConnectionInfo ci) throws Exception {
		Set<ConnectionInfo> res = new HashSet<ConnectionInfo>();
		for(SimulatorConnectionInfo c : this.node)
		{
			if(c.getNodeType() == SimulatorConnectionInfo.NodeType.ACCESS_POINT || ci.getInitialPosition().distance(c.getInitialPosition()) <= ci.getInitialRange()) {
				ConnectionInfo connInf = new ConnectionInfo(
						c.getAddress(),
						c.getCommunicationInboundPortURI(),
						c.getRoutingInboundPortURI()
				);
				res.add(connInf);
			}
		}
		node.add(ci);
		return res;
	}

	@Override
	public synchronized void unregister(P2PAddressI address) throws Exception
	{
		for(SimulatorConnectionInfo c : this.node)
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
